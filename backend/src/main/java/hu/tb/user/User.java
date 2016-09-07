package hu.tb.user;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Tivadar Bocz on 2016.09.07..
 */
@Getter
@Setter
//@Entity
public class User {
    private String userName;
    private String password;
    private String email;
    private boolean disabled;
    private String role;
}
