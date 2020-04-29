package zp.brain.web_forum.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_ROLE", uniqueConstraints = @UniqueConstraint(name = "User_Role_UK",
        columnNames = {"user_id", "role_id"}))
@Data
@NoArgsConstructor
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private AppRoleEntity role;

    public UserRoleEntity(UserEntity user, AppRoleEntity role) {
        this.user = user;
        this.role = role;
    }
}
