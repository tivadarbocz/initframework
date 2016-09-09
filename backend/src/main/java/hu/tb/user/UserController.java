package hu.tb.user;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //private  List<User> userDb = new ArrayList<>();

    public UserController() {
        /*User user = new User(){{
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
        userDb.add(user2);*/
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
     public LoginResponse login(@RequestBody final UserMapper userMapper) throws ServletException {

        User tmpUser =  userMapper.process();
        if(userService.userCanLogin(userMapper.process())){
            return new LoginResponse(Jwts.builder().setSubject(tmpUser.getUserName())
                    .claim("roles", tmpUser.getUserName()).setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "secretkey").compact());
        }
        else{
            throw new ServletException("Invalid login");
        }
    }

    @RequestMapping(value ="/", method = RequestMethod.POST)
    public User getUserByUserName(@RequestParam("userName") String userName){
        return userService.getUserByUserName(userName);
    }

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public Iterable<User> getAllUser(){
        return userService.getAllUser();
    }

    @SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }
}

