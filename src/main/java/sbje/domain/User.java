package sbje.domain;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "users")
@TypeDef(name = "jsonb", typeClass = JSONBUserType.class, parameters = {
    @Parameter(name = JSONBUserType.CLASS, value = "sbje.domain.UserData")})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
  @SequenceGenerator(name = "users_id_seq", sequenceName = "users_id_seq", allocationSize = 1)
  private Long id;

  @Column(nullable = false)
//  @Convert(converter = UserDataConverter.class)
  @Type(type = "jsonb")
  private UserData data;

  //https://github.com/pires/hibernate-postgres-jsonb/blob/master/src/main/java/com/github/pires/example/model/JSONBEntity.java

  public User() {
  }

  public User(UserData data) {
    this.data = data;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserData getData() {
    return data;
  }

  public void setData(UserData data) {
    this.data = data;
  }
}
