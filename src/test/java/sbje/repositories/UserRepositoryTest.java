package sbje.repositories;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import sbje.SbjeApp;
import sbje.domain.User;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SbjeApp.class)
@WebIntegrationTest(randomPort = true, value = {"spring.profiles.active=test"})
@Transactional //rollback commits
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @org.junit.Test
  public void testFindUsersByHobby() throws Exception {
    List<User> users = userRepository.findUsersByHobby("reading");
    assertEquals(2, users.size());
  }

  @org.junit.Test
  public void testUpdateUser() throws Exception {
    User user = userRepository.findUsersByHobby("reading").get(0);
    user.getData().setName("changed");
    userRepository.saveAndFlush(user);
    User savedUser = userRepository.findOne(user.getId());
    assertEquals("changed", savedUser.getData().getName());
  }
}