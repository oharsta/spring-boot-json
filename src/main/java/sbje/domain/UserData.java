package sbje.domain;

import java.io.Serializable;
import java.util.List;

public class UserData implements Serializable {

  private String name;
  private List<String> hobbies;

  public UserData() {
  }

  public UserData(String name, List<String> hobbies) {
    this.name = name;
    this.hobbies = hobbies;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getHobbies() {
    return hobbies;
  }

  public void setHobbies(List<String> hobbies) {
    this.hobbies = hobbies;
  }
}
