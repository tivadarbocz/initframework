package hu.tb.user;

import hu.tb.security.Crypto;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Tivadar Bocz on 2016.09.06..
 */
@Getter
@Setter
public class UserMapper {
    private static Logger logger = LoggerFactory.getLogger(Crypto.class);

    String authorization;
    String userName;
    String password;

    public User process() {
        User user = new User();
        try{
            //ObjectMapper mapper = new ObjectMapper();
            //String str = Crypto.base64Decode(authorization);
            String[] tmp = Crypto.base64Decode(authorization).split(",");
            userName = tmp[0];
            password = tmp[1];
            user.setUserName(userName.split(":")[1].replace("'", ""));
            user.setPassword(password.split(":")[1].replace("'", ""));
        }catch (Exception e){
            logger.error("Can not parse the login data");
        }
        return user;
    }
}
