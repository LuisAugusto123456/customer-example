package pe.entity.microservice.customer.example.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Customer Response Object
 * 
 * @author lpazd
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponseObject {

	/**
	 * uuid
	 */
	@JsonProperty("uuid")
	private String uuid;

	/**
	 * firstName
	 */
	@JsonProperty("firstName")
	private String firstName;

	/**
	 * lastName
	 */
	@JsonProperty("lastName")
	private String lastName;

	/**
	 * age
	 */
	@JsonProperty("age")
	private Integer age;

	/**
	 * birthDate
	 */
	@JsonProperty("birthDate")
	private String birthDate;

	/**
	 * deathDate
	 */
	@JsonProperty("deathDate")
	private String deathDate;

	/**
	 * Customer Response Object
	 * 
	 * @param uuid      uuid
	 * @param firstName firstName
	 * @param lastName  lastName
	 * @param age       age
	 * @param birthDate birthDate
	 * @param deathDate deathDate
	 */
	public CustomerResponseObject(String uuid, String firstName, String lastName, Integer age, String birthDate,
			String deathDate) {
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.birthDate = birthDate;
		this.deathDate = deathDate;
	}

	/**
	 * Get Uuid
	 * 
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Set Uuid
	 * 
	 * @param uuid uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	 * Sert age
	 * 
	 * @param age age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Get Birth Date
	 * 
	 * @return birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * Set Birth Date
	 * 
	 * @param birthDate birthDate
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * Get Death Date
	 * 
	 * @return deathDate
	 */
	public String getDeathDate() {
		return deathDate;
	}

	/**
	 * Set Death Date
	 * 
	 * @param deathDate deathDate
	 */
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}

}
