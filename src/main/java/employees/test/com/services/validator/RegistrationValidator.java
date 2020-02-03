package employees.test.com.services.validator;

import employees.test.com.dto.AuthenticateDTO;

public interface RegistrationValidator {
    void validate(AuthenticateDTO authenticateDTO);
}
