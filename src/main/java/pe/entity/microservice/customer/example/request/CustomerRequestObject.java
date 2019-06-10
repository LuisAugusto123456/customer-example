package pe.entity.microservice.customer.example.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Customer Request Object
 * @author lpazd
 *
 */
@JsonInclude(Include.NON_NULL)
public class CustomerRequestObject {

	/**
	 * user name
	 */
	@JsonProperty("user_name")
	private String userName;

	/**
	 * password
	 */
	@JsonProperty("password")
	private String password;

	/**
	 * first name
	 */
	@JsonProperty("first_name")
	private String firstName;

	/**
	 * last name
	 */
	@JsonProperty("last_name")
	private String lastName;

	/**
	 * age
	 */
	@JsonProperty("age")
	private Integer age;

	/**
	 * birth date
	 */
	@JsonProperty("birth_date")
	private String birthDate;

	/**
	 * Customer Request Object
	 * 
	 * @param userName  user name
	 * @param password  password
	 * @param firstName first name
	 * @param lastName  last name
	 * @param age       age
	 * @param birthDate birth date
	 */
	public CustomerRequestObject(String userName, String password, String firstName, String lastName, Integer age,
			String birthDate) {
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.birthDate = birthDate;
	}

	/**
	 * Get User Name
	 * 
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set User Name
	 * 
	 * @param userName user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Get password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set password
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Get First Name
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set First Name
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get Last Name
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set Last Name
	 * 
	 * @param lastName last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get age
	 * 
	 * @return age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Set age
	 * 
	 * @param age age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Get Birth Date
	 * 
	 * @return birth date
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Set Birth Date
	 * 
	 * @param birthDate birth date
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

}
