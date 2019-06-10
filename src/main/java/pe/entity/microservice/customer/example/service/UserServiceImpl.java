package pe.entity.microservice.customer.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.entity.microservice.customer.example.model.User;
import pe.entity.microservice.customer.example.repository.UserRepository;

/**
 * User Service Impl
 * 
 * @author lpazd
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	/**
	 * user repository
	 */
	@Autowired
	private UserRepository userRepository;

	/**
	 * save
	 * 
	 * @param user user
	 * @return user
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public User save(User user) {
		return userRepository.save(user);
	}

	/**
	 * find by uuid
	 * 
	 * @param uuid uuid
	 * @return user
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findByUuid(String uuid) {
		return userRepository.findByUuid(uuid);
	}

	/**
	 * find by user name
	 * 
	 * @param userName user name
	 * @return user
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	/**
	 * find by user name and password
	 * 
	 * @param userName user name
	 * @param password password
	 * @return user
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public User findByUserNameAndPassword(String userName, String password) {
		return userRepository.findByUserNameAndPassword(userName, password);
	}

	/**
	 * find all
	 * 
	 * @return list user
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
}
