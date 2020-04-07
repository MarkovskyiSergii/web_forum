package zp.brain.web_forum.DTO;


import lombok.Data;
import lombok.NoArgsConstructor;
import zp.brain.web_forum.util.PasswordMatches;
import zp.brain.web_forum.util.ValidEmail;
import zp.brain.web_forum.util.ValidPassword;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@PasswordMatches
public class UserDTO {

        @NotBlank
        private String userName ;

        @ValidEmail
        private String email;

        @ValidPassword ()
        private String password;

        @NotBlank
        private String matchingPassword;


}
