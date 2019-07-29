package by.itacademy.database.dto;

import by.itacademy.database.entity.Contacts;
import by.itacademy.database.validator.FieldMatch;
import by.itacademy.database.validator.UniqueLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
public class RegistrationDto {

    @UniqueLogin
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String mail;
    @Pattern(regexp = "\\+375\\((25|29|33|44)\\)\\d{3}-\\d{2}-\\d{2}",
            message = "Must be in format +375(XX)XXX-XX-XX")
    private String tel;
}
