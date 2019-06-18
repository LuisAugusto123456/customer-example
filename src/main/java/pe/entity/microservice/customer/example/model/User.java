package pe.entity.microservice.customer.example.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_USER")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "USER_ID", length = 11)
	private Long id;

	@Column(name = "UUID", length = 50)
	@NotNull
	private String uuid;

	@Column(name = "USER_NAME", length = 255)
	@NotNull
	private String userName;

	@Column(name = "PASSWORD", length = 255)
	@NotNull
	private String password;

	@Column(name = "FIRST_NAME", length = 255)
	@NotNull
	private String firstName;

	@Column(name = "LAST_NAME", length = 255)
	@NotNull
	private String lastName;

	@Column(name = "AGE", length = 11)
	@NotNull
	private Integer age;

	@Column(name = "BIRTH_DATE", length = 6)
	@NotNull
	private Date birthDate;

	/**
	 * Constructor User
	 */
	public User() {
		// Construct default
	}

	/**
	 * Constructor User
	 * 
	 * @param uuid             uuid
	 * @param userName         user name
	 * @param password         password
	 * @param firstName        first name
	 * @param lastName         last name
	 * @param age              age
	 * @param birthDate        birth date
	 * @param createdUser      created user
	 * @param createdDate      created date
	 * @param lastModifiedUser last modified user
	 * @param lastModifiedDate last modified date
	 */
	public User(@NotNull String uuid, @NotNull String userName, @NotNull String password,
			@NotNull String firstName, @NotNull String lastName, @NotNull Integer age, @NotNull Date birthDate,
			String createdUser, Date createdDate, String lastModifiedUser, Date lastModifiedDate) {
		super(createdUser, createdDate, lastModifiedUser, lastModifiedDate);
		this.uuid = uuid;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.birthDate = birthDate;
	}

	/**
	 * Get id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set id
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * Get user name
	 * 
	 * @return user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Ser user name
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
	 * Get first name
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Set first name
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get last name
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Set last name
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
	 * Get birth date
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Set birth date
	 * 
	 * @param birthDate birth date
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

}
