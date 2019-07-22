package by.itacademy.database.dto;

import by.itacademy.database.entity.Contacts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String login;
    private String password;
    private String fullName;
    private Contacts contacts;
}
