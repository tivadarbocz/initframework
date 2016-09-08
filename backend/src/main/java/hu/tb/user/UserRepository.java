package hu.tb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Tivadar Bocz on 2016.09.08..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

    @Query(value = "SELECT u FROM User u WHERE u.userName = :userName")
    User getUserByUserName(@Param("userName") String userName);
}
