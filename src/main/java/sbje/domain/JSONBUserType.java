package sbje.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
import org.postgresql.util.PGobject;

import java.io.IOException;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Properties;

public class JSONBUserType implements UserType, ParameterizedType {

  public static final String CLASS = "CLASS";
  private static ObjectMapper objectMapper = new ObjectMapper();
  private Class forClass;

  @Override
  public int[] sqlTypes() {
    return new int[]{Types.JAVA_OBJECT};
  }

  @Override
  public Class returnedClass() {
    return this.forClass;
  }

  @Override
  public boolean equals(Object x, Object y) throws HibernateException {
    return Objects.equals(x, y);
  }

  @Override
  public int hashCode(Object x) throws HibernateException {
    return Objects.hashCode(x);
  }

  @Override
  public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
    try {
      final String json = rs.getString(names[0]);
      return json == null
          ? null
          : objectMapper.readValue(json,  this.forClass);
    } catch (IOException ex) {
      throw new HibernateException(ex);
    }
  }

  @Override
  public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
    try {
      final String json = value == null ? null : objectMapper.writeValueAsString(value);
      // otherwise PostgreSQL won't recognize the type
      PGobject pgo = new PGobject();
      pgo.setType("jsonb");
      pgo.setValue(json);
      st.setObject(index, pgo);
    } catch (JsonProcessingException ex) {
      throw new HibernateException(ex);
    }
  }

  @Override
  public Object deepCopy(Object value) throws HibernateException {
    return value;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object value) throws HibernateException {
    return (Serializable) value;
  }

  @Override
  public Object assemble(Serializable cached, Object owner) throws HibernateException {
    return cached;
  }

  @Override
  public Object replace(Object original, Object target, Object owner) throws HibernateException {
    return original;
  }

  @Override
  public void setParameterValues(Properties parameters) {
    try {
      this.forClass = Class.forName( parameters.getProperty(CLASS));
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
