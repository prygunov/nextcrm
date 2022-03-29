package net.artux.nextcrm.model.user;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Data
@Getter
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String login;

    private boolean approved;
    private Long role_id;

}
