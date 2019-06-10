package pe.entity.microservice.customer.example.service;

import java.util.List;

import pe.entity.microservice.customer.example.model.User;

/**
 * User Service
 * 
 * @author lpazd
 *
 */
public interface UserService {

	/**
	 * save
	 * 
	 * @param user user
	 * @return user
	 */
	User save(User user);

	/**
	 * find by uuid
	 * 
	 * @param uuid uuid
	 * @return user
	 */
	User findByUuid(String uuid);

	/**
	 * find by user name
	 * 
	 * @param userName user name
	 * @return user
	 */
	User findByUserName(String userName);

	/**
	 * find by user name and password
	 * 
	 * @param userName user name
	 * @param password password
	 * @return user
	 */
	User findByUserNameAndPassword(String userName, String password);

	/**
	 * find all
	 * 
	 * @return list user
	 */
	List<User> findAll();
}
