package sbje.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import sbje.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "select * from users where data->'hobbies' #-# :hobby", nativeQuery = true)
  List<User> findUsersByHobby(@Param("hobby") String hobby);
}
