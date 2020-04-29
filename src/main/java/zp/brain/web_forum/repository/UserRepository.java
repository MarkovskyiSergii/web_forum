package zp.brain.web_forum.repository;

import org.springframework.data.repository.CrudRepository;
import zp.brain.web_forum.model.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> getUserEntityByUserName(String name);

    UserEntity findByEmail(String email);

    UserEntity findByUserName(String userName);


}
