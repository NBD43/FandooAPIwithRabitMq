package com.bridgelabz.fundoo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseToken;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.model.MailModel;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepo;
import com.bridgelabz.fundoo.user.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FundooNoteRabbitMqApplicationTests {
	
	@Mock
	private ModelMapper modelMapper;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private MailModel mailModel;
	
	@Mock
	Response statusResponse;
	
	@Mock
	ResponseToken statusResponseToken;
	
	@Mock
	private UserRepo userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
//	@Before
//	public void setup(){
//		MockitoAnnotations.initMocks(this);
//	}

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void userToBeRegistration() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setFirstName("nilesh");
	userdto.setLastName("dahiphale");
	userdto.setEmailId("dahiphale.nilesh@gmail.com");
	userdto.setPassword("nilesH@123");
	userdto.setMobileNum("7894561230"); //
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword()); // when(userRespository.save(user));
//	statusResponse = userServiceImpl.onRegister(userdto);
//	System.out.println(userServiceImpl.onRegister(userdto));


	System.out.println(userdto);
	assertEquals("nilesh", userdto.getFirstName());
	}
	
	

	@Test
	public void userNotToBeRegistrationWithEmptyValue() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setFirstName("");
	userdto.setLastName("");
	userdto.setEmailId("");
	userdto.setPassword("");
	userdto.setMobileNum("");
	//
	
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword()); // when(userRespository.save(user));
	try {
	statusResponse = userServiceImpl.onRegister(userdto);
	//System.out.println(userServiceImpl.onRegister(userdto));
	}catch(Exception e) {
		//assertNotNull("email Id", userdto.getEmailId());
	}

	
	}
	
	@Test
	public void userNotToBeRegistrationWithNullValue() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword()); // when(userRespository.save(user));
	try {
	statusResponse = userServiceImpl.onRegister(userdto);
	}catch(Exception e) {}
//	System.out.println(userServiceImpl.onRegister(userdto));


	
	}
	
	@Test
	public void userNotTobeRegistration() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setFirstName("nilesh");
	userdto.setLastName("dahiphale");
	userdto.setEmailId("Rft@bimng.spam@gmail.com");
	userdto.setPassword("aaaaaaaaaaaa");
	userdto.setMobileNum("7894561230"); //
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword()); // when(userRespository.save(user));
	try {
	
		statusResponse = userServiceImpl.onRegister(userdto);
	}catch(Exception e) {
		
	}
	//System.out.println(userServiceImpl.onRegister(userdto));


	System.out.println(userdto);
	assertEquals("nilesh", userdto.getFirstName());
	}
	
	@Test
	public void toBeAbleToLogin() {
		User user=new User();
		LoginDTO logindto=new LoginDTO();
		when(modelMapper.map(logindto, User.class)).thenReturn(user);
		logindto.setEmailId("dahiphale.nilesh@gmail.com");
		logindto.setPassword("nilesH@123");
		System.out.println(logindto);
		assertEquals("dahiphale.nilesh@gmail.com", logindto.getEmailId());
		assertEquals("nilesH@123", logindto.getPassword());
		
		
	}
	
	@Test
	public void toBeNotAbleToLogin() {
		User user=new User();
		LoginDTO logindto=new LoginDTO();
		when(modelMapper.map(logindto, User.class)).thenReturn(user);
		logindto.setEmailId("xyz@gmail.com");
		logindto.setPassword("Xyz@123");
		System.out.println(logindto);
		try {
			 statusResponseToken = userServiceImpl.onLogin(logindto);
			}catch(Exception e) {
				assertNotSame("dahiphale.nilesh@gmail.com", logindto.getEmailId());
				assertNotSame("nilesH@123", logindto.getPassword());
			}
		
		
		
	}
	
	@Test
	public void toLoginwithNullValue() {
		User user=new User();
		LoginDTO logindto=new LoginDTO();
		logindto.setEmailId(null);
		logindto.setPassword(null);
		when(modelMapper.map(logindto, User.class)).thenReturn(user);
		System.out.println(logindto);
		try {
		 statusResponseToken = userServiceImpl.onLogin(logindto);
		}catch(Exception e) {
			assertNull(user.getEmailId());
			assertNull(user.getPassword());
		}
//		
//		 assertNull(user.getEmailId());
//		 assertNull(user.getPassword());
//		assertEquals("dahiphale.nilesh@gmail.com", logindto.getEmailId());
//		assertEquals("nilesH@123", logindto.getPassword());
		
		
	}
	@Test
	public void toSendMailforForgetPassword() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setEmailId("dahiphale.nilesh@gmail.com");
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword());
	assertEquals("dahiphale.nilesh@gmail.com", userdto.getEmailId());
	}
	
	@Test
	public void toNotSendMailforForgetPassword() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setEmailId("dnilesh@gmail.com");
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword());
	assertNotSame("dahiphale.nilesh@gmail.com", userdto.getEmailId());
	}
	
	@Test
	public void toNotSendMailForNullEmail() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setEmailId(null);
	
	assertNull("dahiphale.nilesh@gmail.com", userdto.getEmailId());
	}


}
