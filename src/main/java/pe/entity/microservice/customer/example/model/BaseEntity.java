package pe.entity.microservice.customer.example.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

/**
 * Base Entity
 * 
 * @author lpazd
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * admin
	 */
	private static final String ADMIN = "ADMIN";

	/**
	 * created user
	 */
	@Column(name = "CREATED_USER", length = 255)
	@NotNull
	private String createdUser;

	/**
	 * created date
	 */
	@Column(name = "CREATED_DATE")
	@Type(type = "timestamp")
	@NotNull
	private Date createdDate;

	/**
	 * last modified user
	 */
	@Column(name = "LAST_MODIFIED_USER", length = 255)
	@NotNull
	private String lastModifiedUser;

	/**
	 * last modified date
	 */
	@Column(name = "LAST_MODIFIED_DATE")
	@Type(type = "timestamp")
	@NotNull
	private Date lastModifiedDate;

	/**
	 * Base Entity
	 */
	public BaseEntity() {
		super();
		this.createdUser = "";
		this.createdDate = new Date();
		this.lastModifiedUser = "";
		this.lastModifiedDate = new Date();
	}

	/**
	 * Base Entity
	 * 
	 * @param createdUser      created user
	 * @param createdDate      created date
	 * @param lastModifiedUser last modified user
	 * @param lastModifiedDate last modified date
	 */
	public BaseEntity(String createdUser, Date createdDate, String lastModifiedUser, Date lastModifiedDate) {
		super();
		this.createdUser = createdUser;
		this.createdDate = createdDate;
		this.lastModifiedUser = lastModifiedUser;
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Get created user
	 * 
	 * @return created User
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * Set created user
	 * 
	 * @param createdUser created user
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * Get created date
	 * 
	 * @return created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Set created date
	 * 
	 * @param createdDate created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Get last modified user
	 * 
	 * @return last modified user
	 */
	public String getLastModifiedUser() {
		return lastModifiedUser;
	}

	/**
	 * Set last modified user
	 * 
	 * @param lastModifiedUser last modified user
	 */
	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	/**
	 * Get last modified date
	 * 
	 * @return last modified date
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * Set last modified date
	 * 
	 * @param lastModifiedDate last modified date
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * Set creation date
	 */
	@PrePersist
	public void setCreationDate() {
		Date currentDate = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00")).getTime();
		if (createdUser == null) {
			this.createdUser = ADMIN;
		}
		if (createdDate == null) {
			this.createdDate = currentDate;

		}
		if (lastModifiedUser == null) {
			this.lastModifiedUser = ADMIN;
		}
		if (lastModifiedDate == null) {
			this.lastModifiedDate = currentDate;
		}
	}

	/**
	 * Set change date
	 */
	@PreUpdate
	public void setChangeDate() {
		Date currentDate = Calendar.getInstance(TimeZone.getTimeZone("America/Lima")).getTime();
		if (lastModifiedUser == null) {
			this.lastModifiedUser = ADMIN;
		}
		if (lastModifiedDate == null) {
			this.lastModifiedDate = currentDate;
		}
	}

}
