package sbje.domain;

import java.util.List;

public class UserData {

  private String name;
  private List<String> hobbies;

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
