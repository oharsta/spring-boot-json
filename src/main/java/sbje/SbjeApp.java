package sbje;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.http.MediaType;
import sbje.domain.User;
import sbje.repositories.UserRepository;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication()
public class SbjeApp {

  public static void main(String[] args) {
    SpringApplication.run(SbjeApp.class, args);
  }

  @Configuration
  @ConfigurationProperties(prefix = "spring.datasource")
  public static class JpaConfig extends HikariConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
      return new HikariDataSource(this);
    }

  }

  @Bean
  @Profile({"test"})
  @Autowired
  public UserLoader userLoader(UserRepository userRepository) {
    return new UserLoader(userRepository);
  }


  @Configuration
  public static class RestMvcConfiguration extends RepositoryRestMvcConfiguration {

    @Override
    public RepositoryRestConfiguration config() {
      RepositoryRestConfiguration config = super.config();
      config.setDefaultMediaType(MediaType.APPLICATION_JSON);
      config.setReturnBodyOnCreate(true);
      config.exposeIdsFor(User.class);
      return config;
    }
  }
}
