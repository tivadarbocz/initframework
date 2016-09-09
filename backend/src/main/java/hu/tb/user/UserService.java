package hu.tb.user;

import hu.tb.security.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tivadar Bocz on 2016.09.08..
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id){
        return userRepository.findOne(id);
    }

    public User getUserByUserName(String userName){
        return userRepository.getUserByUserName(userName);
    }

    public User updateUserById(Integer id, User u){
        User user = userRepository.findOne(id);
        return user = u;
    }

    public void deleteUserById(Integer id){
        userRepository.delete(id);
    }

    public Iterable<User> updateUserById(){
        return userRepository.findAll();
    }

    /**
     *
     * @param tmpUser set userName and password
     * @return true is the userName and password pair is correct
     */
    public boolean userCanLogin(User tmpUser){
        User u = userRepository.getUserByUserName(tmpUser.getUserName());
        if(tmpUser.getUserName().equals(u.getUserName()) && Crypto.md5Encode(tmpUser.getPassword()).equals(u.getPassword())){
            return true;
        }
        else{
            return false;
        }
    }

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }
}
