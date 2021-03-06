package zp.brain.web_forum.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "APP_ROLE", uniqueConstraints = @UniqueConstraint(name = "APP_Role_UK", columnNames = "role_name"))
@Data
@NoArgsConstructor
public class AppRoleEntity {

    public static final Long ROLE_ADMIN = 1L;
    public static final Long ROLE_USER = 2L;
    public static final Long ROLE_BAN = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name", length = 20)
    private String roleName;

    public AppRoleEntity(String roleName) {
        this.roleName = roleName;
    }
}
