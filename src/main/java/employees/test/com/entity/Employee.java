package employees.test.com.entity;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Integer id;
    private String name;
    private boolean active;
    private Integer depId;

    public Employee(String name, boolean active, Integer depId) {
        this.name = name;
        this.active = active;
        this.depId = depId;
    }
}