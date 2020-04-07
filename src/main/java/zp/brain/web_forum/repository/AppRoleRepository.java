package zp.brain.web_forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zp.brain.web_forum.model.AppRoleEntity;

import java.util.List;

public interface AppRoleRepository extends CrudRepository<AppRoleEntity, Long> {

    @Query("Select ur.role.roleName from UserRoleEntity ur where ur.user.userId = ?1")
    List<String> getRoleNames(Long userId);

    @Query("Select ar.roleName from AppRoleEntity ar where ar.roleId = ?1")
    AppRoleEntity getAppRoleByRoleId(Long roleId);
}
