package sbje;

import sbje.domain.User;
import sbje.domain.UserData;
import sbje.repositories.UserRepository;

import java.util.Arrays;

public class UserLoader {
  public UserLoader(UserRepository userRepository) {
    userRepository.deleteAll();
    userRepository.save(new User(new UserData("John Doe", Arrays.asList("reading", "running"))));
    userRepository.save(new User(new UserData("Jane Doe", Arrays.asList("reading", "movies"))));
    userRepository.save(new User(new UserData("Butch Groo", Arrays.asList("tennis", "boxing"))));
  }
}
