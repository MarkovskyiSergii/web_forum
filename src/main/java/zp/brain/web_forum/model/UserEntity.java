package zp.brain.web_forum.model;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zp.brain.web_forum.util.PasswordMatches;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "USERS")

@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "encrypted_password")
    private String encryptedPassword;

    @Column(name = "email")

    private String email;

    @Column(name = "enabled", length = 1)
    private boolean enabled = false;

    @Column(name = "registration_time")
    private Timestamp timeRegistration;
}
