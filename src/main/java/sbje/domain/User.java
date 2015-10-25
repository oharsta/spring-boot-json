package sbje.domain;

import javax.persistence.*;

@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  @Column(nullable = false)
  @Convert(converter = UserDataConverter.class)
  private UserData data;

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
