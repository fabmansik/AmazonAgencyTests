package milan.somyk.AWS.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String id;
    @NotBlank(message = "username required")
    @Size(min = 3, max = 20, message = "username: min: {min}, max: {max} characters")
    private String username;
    @NotBlank(message = "password required")
    @Pattern(regexp = "^(?=.*\\d).{4,8}$", flags = Pattern.Flag.UNICODE_CASE, message = "invalid password")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    private String role;
}
