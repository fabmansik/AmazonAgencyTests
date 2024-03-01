package milan.somyk.AWS.test.repository;

import milan.somyk.AWS.test.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    @Query("select exists(select username from User where username = ?0)")
    boolean checkUser(String username);
}
