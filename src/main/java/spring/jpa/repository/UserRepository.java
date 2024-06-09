package spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.jpa.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByLogin(String login);
}
