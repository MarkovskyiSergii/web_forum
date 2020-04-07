package zp.brain.web_forum.repository;

import org.springframework.data.repository.CrudRepository;
import zp.brain.web_forum.model.UserRoleEntity;

public interface UserRoleRepository extends CrudRepository<UserRoleEntity,String> {
}
