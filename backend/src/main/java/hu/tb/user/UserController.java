package hu.tb.user;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/user")
public class UserController {

    private  List<User> userDb = new ArrayList<>();

    public UserController() {
        User user = new User(){{
            setUserName("tom");
            setPassword("password");
            setRole("user");
        }};
        User user2 = new User(){{
            setUserName("sally");
            setPassword("password");
            setRole("admin");
        }};
        userDb.add(user);
        userDb.add(user2);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestBody final UserMapper userMapper) throws ServletException {

        User user =  userMapper.process();
        if(user.getUserName() != null){
            User u =
                    userDb.stream().filter(i ->
                            i.getUserName().equals(user.getUserName()) &&
                                    i.getPassword().equals(user.getPassword())).collect(toList()).get(0);
            if(u != null) {
                return new LoginResponse(Jwts.builder().setSubject(user.getUserName())
                        .claim("roles", user.getUserName()).setIssuedAt(new Date())
                        .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
            }
            else{
                throw new ServletException("Invalid login");
            }
        }
        else{
            throw new ServletException("Invalid login");
        }
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}

