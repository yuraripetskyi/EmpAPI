package employees.test.com.dto;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEmployeeDTO {
    @NonNull
    private String name;
    @NonNull
    private Boolean active;
    @NonNull
    private Integer depId;
}
