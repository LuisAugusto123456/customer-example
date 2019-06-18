package pe.entity.microservice.customer.example.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import pe.entity.microservice.customer.example.util.CommonTestUtil;

/**
 * User Test
 * 
 * @author lpazd
 *
 */
public class UserTest {

	private Long id;
	private String uuid;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Integer age;
	private Date birthDate;
	private String createdUser;
	private Date createdDate;
	private String lastModifiedUser;
	private Date lastModifiedDate;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void initSetup() throws ParseException {
		id = 0L;
		uuid = "uuid";
		userName = "username";
		password = "password";
		firstName = "firstName";
		lastName = "lastName";
		age = 32;
		birthDate = new SimpleDateFormat("dd/MM/yyyy").parse("13/04/1987");
		createdUser = "createdUser";
		createdDate = new Date();
		lastModifiedUser = "lastModifiedUser";
		lastModifiedDate = new Date();
	}

	@Test
	public void typeAnnotations() {
		CommonTestUtil.assertType(User.class, Entity.class, Table.class);
	}

	@Test
	public void testAllFieldAnnotations_givenTheUserFieldAnnotations_whenEvaluatingTheCorrectAnnotations_thenReturnsTrueIfEquals() {
		CommonTestUtil.assertField(User.class, "id", Id.class, Column.class,GeneratedValue.class);
		CommonTestUtil.assertField(User.class, "uuid", Column.class, NotNull.class);
		CommonTestUtil.assertField(User.class, "userName", Column.class, NotNull.class);
		CommonTestUtil.assertField(User.class, "password", Column.class, NotNull.class);
		CommonTestUtil.assertField(User.class, "firstName", Column.class, NotNull.class);
		CommonTestUtil.assertField(User.class, "lastName", Column.class, NotNull.class);
		CommonTestUtil.assertField(User.class, "age", Column.class, NotNull.class);
		CommonTestUtil.assertField(User.class, "birthDate", Column.class, NotNull.class);
		CommonTestUtil.assertField(BaseEntity.class, "createdUser", Column.class, NotNull.class);
		CommonTestUtil.assertField(BaseEntity.class, "createdDate", Column.class, NotNull.class, Type.class);
		CommonTestUtil.assertField(BaseEntity.class, "lastModifiedUser", Column.class, NotNull.class);
		CommonTestUtil.assertField(BaseEntity.class, "lastModifiedDate", Column.class, NotNull.class, Type.class);
	}

	@Test
	public void testMethodAnnotations_givenTheUserGettersAndSettersAnnotations_whenEvaluatingTheCorrectAnnotations_thenReturnsTrueIfEquals() {
		CommonTestUtil.assertMethod(User.class, "getId");
		CommonTestUtil.assertMethod(User.class, "getUuid");
		CommonTestUtil.assertMethod(User.class, "getUserName");
		CommonTestUtil.assertMethod(User.class, "getPassword");
		CommonTestUtil.assertMethod(User.class, "getFirstName");
		CommonTestUtil.assertMethod(User.class, "getLastName");
		CommonTestUtil.assertMethod(User.class, "getAge");
		CommonTestUtil.assertMethod(User.class, "getBirthDate");
		CommonTestUtil.assertMethod(BaseEntity.class, "getCreatedDate");
		CommonTestUtil.assertMethod(BaseEntity.class, "getCreatedUser");
		CommonTestUtil.assertMethod(BaseEntity.class, "getLastModifiedUser");
		CommonTestUtil.assertMethod(BaseEntity.class, "getLastModifiedDate");
	}

	@Test
	public void testTableName_givenTheTableName_whenEvaluatingTheCorrectName_thenReturnsTrue() {
		Assert.assertEquals("TBL_USER", (User.class.getAnnotation(Table.class)).name());
	}

	@Test
	public void testConstructor_givenTheInitialValuesFromUser_whenCallingTheConstructorWithParameters_thenReturnsTrueIfObjectNotNull() {
		User user = new User(uuid, userName, password, firstName, lastName, age, birthDate, createdUser,
				createdDate, lastModifiedUser, lastModifiedDate);
		Assert.assertNotEquals(null, user);
	}

	@Test
	public void testConstructor_givenNoValues_whenCallingTheDefaultConstructor_thenReturnsTrueIfObjectNotNull() {
		User user = new User();
		Assert.assertNotEquals(null, user);
	}

	@Test
	public void testGetterAndSetterMethods_givenTheInitialValuesFromUser_whenCallingTheGetterAndSetterMethods_thenReturnsTrueIfTheValueFromTheGetterIsTheSameValueFromTheSetter() {
		User user = new User();
		user.setId(id);
		Assert.assertEquals(id, user.getId());
		user.setUuid(uuid);
		Assert.assertEquals(uuid, user.getUuid());
		user.setUserName(userName);
		Assert.assertEquals(userName, user.getUserName());
		user.setPassword(password);
		Assert.assertEquals(password, user.getPassword());
		user.setFirstName(firstName);
		Assert.assertEquals(firstName, user.getFirstName());
		user.setLastName(lastName);
		Assert.assertEquals(lastName, user.getLastName());
		user.setAge(age);
		Assert.assertEquals(age, user.getAge());
		user.setBirthDate(birthDate);
		Assert.assertEquals(birthDate, user.getBirthDate());
		user.setCreatedUser(createdUser);
		Assert.assertEquals(createdUser, user.getCreatedUser());
		user.setCreatedDate(createdDate);
		Assert.assertEquals(createdDate, user.getCreatedDate());
		user.setLastModifiedUser(lastModifiedUser);
		Assert.assertEquals(lastModifiedUser, user.getLastModifiedUser());
		user.setLastModifiedDate(lastModifiedDate);
		Assert.assertEquals(lastModifiedDate, user.getLastModifiedDate());
		
		user.setCreatedUser(null);
		user.setCreatedDate(null);
		user.setLastModifiedUser(null);
		user.setLastModifiedDate(null);
		user.setCreationDate();
		user.setLastModifiedUser(null);
		user.setLastModifiedDate(null);
		user.setChangeDate();
	}
}
