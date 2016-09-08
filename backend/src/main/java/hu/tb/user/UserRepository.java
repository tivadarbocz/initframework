package hu.tb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tivadar Bocz on 2016.09.08..
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
