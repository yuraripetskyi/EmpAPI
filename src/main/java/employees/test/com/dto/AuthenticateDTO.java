package employees.test.com.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateDTO {
    private String username;
    private String password;
}
