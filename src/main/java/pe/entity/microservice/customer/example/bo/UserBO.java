package pe.entity.microservice.customer.example.bo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import pe.entity.microservice.customer.example.enums.EResponse;
import pe.entity.microservice.customer.example.model.User;
import pe.entity.microservice.customer.example.response.CustomerResponseObject;
import pe.entity.microservice.customer.example.response.GenericResponseObject;
import pe.entity.microservice.customer.example.response.KpiResponseObject;
import pe.entity.microservice.customer.example.response.UserResponseObject;
import pe.entity.microservice.customer.example.security.jwt.JwtAuthenticationRequest;
import pe.entity.microservice.customer.example.security.jwt.JwtUtil;
import pe.entity.microservice.customer.example.service.UserService;

/**
 * User BO
 * 
 * @author lpazd
 *
 */
@Component
public class UserBO {

	/**
	 * logger bo
	 */
	private static final Logger LOGGER_BO = LogManager.getLogger(UserBO.class);

	/**
	 * secret
	 */
	@Value("${jwt.secret}")
	private String secret;

	/**
	 * Expiration Access Token
	 */
	@Value("${expiration.access.token}")
	private Long expirationAccessToken;
	
	/**
	 * Expiration Refresh Token
	 */
	@Value("${expiration.refresh.token}")
	private Long expirationRefreshToken;
	
	/**
	 * user service
	 */
	@Autowired
	private UserService userService;

	/**
	 * Auth BO
	 */
	@Autowired
	private AuthBO authBO;

	/**
	 * Save User
	 * 
	 * @param userName  user name
	 * @param password  password
	 * @param firstName first name
	 * @param lastName  last name
	 * @param age       age
	 * @param birthdate birthdate
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 10)
	public ResponseEntity<Object> saveUser(String userName, String password, String firstName, String lastName,
			Integer age, String birthdate) {
		try {
			LOGGER_BO.info("validamos si user name existe");
			if (ObjectUtils.isEmpty(userService.findByUserName(userName))) {
				Date currentDate = new Date();
				LOGGER_BO.info("registramos usuario");
				User user = userService.save(new User(UUID.randomUUID().toString(), userName, password, firstName,
						lastName, age, new SimpleDateFormat("dd/MM/yyyy").parse(birthdate), userName, currentDate,
						userName, currentDate));
				LOGGER_BO.info("Obtenemos tokens");
				JwtAuthenticationRequest authenticationRequest = authBO.getJwtAuthenticationRequest(user.getUuid(),
						user.getUserName());
				JwtUtil.setSecret(secret);
				JwtUtil.setExpirationAccessToken(expirationAccessToken);
				JwtUtil.setExpirationRefreshToken(expirationRefreshToken);
				return ResponseEntity
						.ok(new UserResponseObject(user.getUuid(), JwtUtil.prepareToken(authenticationRequest),
								JwtUtil.prepareRefreshToken(authenticationRequest, 1)));
			}
			LOGGER_BO.warn("user name existe");
			return ResponseEntity.badRequest().body(new GenericResponseObject(EResponse.USER_NAME_EXIST.getCode(),
					EResponse.USER_NAME_EXIST.getKeyMessage()));
		} catch (Exception e) {
			LOGGER_BO.error("Error interno del servidor - saveUser: " + e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponseObject(
					EResponse.NON_SPECIFIC.getCode(), EResponse.NON_SPECIFIC.getKeyMessage()));
		}
	}

	@Transactional(rollbackFor = Exception.class, timeout = 10)
	public ResponseEntity<Object> kpi() {
		try {
			LOGGER_BO.info("obtenemos todos los clientes - kpi");
			List<User> lstUser = userService.findAll();
			if (ObjectUtils.isEmpty(lstUser)) {
				LOGGER_BO.info("No se obtuvo resultado en la busqueda - kpi");
				return ResponseEntity.noContent().build();
			}
			LOGGER_BO.info("calculando promedio");
			double average = average(lstUser);
			LOGGER_BO.info("calculando desvicacion estandar");
			double standardDeviation = standardDeviation(lstUser, average);
			return ResponseEntity.ok(new KpiResponseObject(average, standardDeviation));
		} catch (Exception e) {
			LOGGER_BO.error("Error interno del servidor - kpi: " + e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponseObject(
					EResponse.NON_SPECIFIC.getCode(), EResponse.NON_SPECIFIC.getKeyMessage()));
		}
	}

	/**
	 * Average
	 * 
	 * @param lstUser list user
	 * @return average
	 */
	public double average(List<User> lstUser) {
		return lstUser.stream().mapToDouble(user -> user.getAge()).average().getAsDouble();
	}

	/**
	 * Standard Deviation
	 * 
	 * @param lstUser list user
	 * @param average average
	 * @return standard deviation
	 */
	public double standardDeviation(List<User> lstUser, double average) {
		double suma = lstUser.stream().mapToDouble(user -> Math.pow((user.getAge() - average), 2)).sum();
		return Math.pow(suma / lstUser.size(), 0.5);
	}

	/**
	 * Customer List
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class, timeout = 10)
	public ResponseEntity<Object> customerList() {
		try {
			LOGGER_BO.info("obtenemos todos los clientes - listCustomers");
			List<User> lstUser = userService.findAll();
			if (ObjectUtils.isEmpty(lstUser)) {
				LOGGER_BO.info("No se obtuvo resultado en la busqueda - listCustomers");
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(
					lstUser.stream().map(this::mapperCustomerFrom).collect(Collectors.toCollection(ArrayList::new)));
		} catch (Exception e) {
			LOGGER_BO.error("Error interno del servidor - listCustomers: " + e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericResponseObject(
					EResponse.NON_SPECIFIC.getCode(), EResponse.NON_SPECIFIC.getKeyMessage()));
		}
	}

	/**
	 * Mapper Customer From
	 * 
	 * @param user user
	 * @return customer from
	 */
	private CustomerResponseObject mapperCustomerFrom(User user) {
		int deathDateMin = 60;
		int deathDateMax = 100;
		int random = (int) Math.floor(Math.random() * (deathDateMax - deathDateMin + 1) + deathDateMin);
		int num = user.getAge() < random ? random - user.getAge() : deathDateMax - user.getAge();
		return new CustomerResponseObject(user.getUuid(), user.getFirstName(), user.getLastName(), user.getAge(),
				new SimpleDateFormat("MM/dd/yyyy").format(user.getBirthDate()),
				new SimpleDateFormat("MM/dd/yyyy").format(sumDate(new Date(), num)));
	}

	/**
	 * Sum Date
	 * 
	 * @param date date
	 * @param num  num
	 * @return date
	 */
	private Date sumDate(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, num);
		return calendar.getTime();

	}
}
