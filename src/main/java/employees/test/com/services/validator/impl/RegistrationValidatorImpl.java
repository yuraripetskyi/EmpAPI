package employees.test.com.services.validator.impl;

import employees.test.com.dto.AuthenticateDTO;
import employees.test.com.exception.RegistrationException;
import employees.test.com.services.validator.RegistrationValidator;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class RegistrationValidatorImpl implements RegistrationValidator {

    private static final int MIN_PASSWORD_LENGTH = 6;

    @Override
    public void validate(AuthenticateDTO authenticateDTO) {
        String name = authenticateDTO.getUsername();
        if (isNull(name) || name.equals("")) {
            throw new RegistrationException(String.format("Can't create admin with {%s} username", name));
        } else if (isNull(authenticateDTO.getPassword()) || authenticateDTO.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Can't create admin with such password");
        }
    }
}
