package pe.entity.microservice.customer.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.entity.microservice.customer.example.model.User;

/**
 * User Repository
 * 
 * @author lpazd
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.uuid =:uuid")
	User findByUuid(@Param("uuid") String uuid);

	@Query("SELECT u FROM User u WHERE u.userName =:userName")
	User findByUserName(@Param("userName") String userName);
	
	@Query("SELECT u FROM User u WHERE u.userName =:userName and u.password =:password")
	User findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
