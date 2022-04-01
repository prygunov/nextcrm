package net.artux.nextcrm.model.user;

import lombok.Data;
import lombok.Getter;
import net.artux.nextcrm.model.CDto;

@Data
@Getter
public class UserDto extends CDto {

    private String firstname;
    private String lastname;
    private String login;
    private boolean approved;
    private String role;

}
