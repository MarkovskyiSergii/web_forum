package zp.brain.web_forum.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import zp.brain.web_forum.validators.PasswordMatches;
import zp.brain.web_forum.validators.ValidEmail;
import zp.brain.web_forum.validators.ValidLogin;
import zp.brain.web_forum.validators.ValidPassword;


@Data
@NoArgsConstructor
@PasswordMatches(message = "Password is not matching")
public class UserDTO {

    @ValidLogin(message = "Login not valid. Use only english letters with/without numbers, min 4 max 15 character")
    private String userName;

    @ValidEmail(message = "Email not valid. Example of email - asdf@gmail.com")
    private String email;

    @ValidPassword(message ="Password not valid. Must contains the of at least one low and uppercase letter, " +
            "one digit and consist min 8 character." )
    private String password;

    private String matchingPassword;
}


