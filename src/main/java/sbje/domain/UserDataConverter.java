package sbje.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Converter
public class UserDataConverter implements AttributeConverter<UserData, String> {

  private static ObjectMapper objectMapper = new ObjectMapper();

  @Override
  @NotNull
  public String convertToDatabaseColumn(@NotNull UserData userData) {
    try {
      return objectMapper.writeValueAsString(userData);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @NotNull
  public UserData convertToEntityAttribute(@NotNull String dbData) {
    try {
      return objectMapper.readValue(dbData,UserData.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
