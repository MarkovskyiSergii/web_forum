package zp.brain.web_forum.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator implements ConstraintValidator<ValidLogin, String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9]{3,15}$";// only english letters and numbers, min 4 max 15 character

    @Override
    public void initialize(ValidLogin constraintAnnotation) {

    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext context) {
        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login.trim());
        return matcher.matches();
    }
}
