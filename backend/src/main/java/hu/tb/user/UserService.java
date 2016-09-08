package hu.tb.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tivadar Bocz on 2016.09.08..
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id){
        return userRepository.findOne(id);
    }

    public User updateUserById(Long id, User u){
        User user = userRepository.findOne(id);
        return user = u;
    }

    public void deleteUserById(Long id){
        userRepository.delete(id);
    }

    public Iterable<User> updateUserById(){
        return userRepository.findAll();
    }

}
