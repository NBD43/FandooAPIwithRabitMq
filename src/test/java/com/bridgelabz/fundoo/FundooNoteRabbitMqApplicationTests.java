package com.bridgelabz.fundoo;

import static org.junit.Assert.assertEquals;
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
	public void userRegistration() {
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
	public void userLogin() {
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
	public void LoginFailed() {
		User user=new User();
		LoginDTO logindto=new LoginDTO();
		when(modelMapper.map(logindto, User.class)).thenReturn(user);
		logindto.setEmailId(null);
		logindto.setPassword(null);
		System.out.println(logindto);
		
		 assertNull(user.getEmailId());
		 assertNull(user.getPassword());
//		assertEquals("dahiphale.nilesh@gmail.com", logindto.getEmailId());
//		assertEquals("nilesH@123", logindto.getPassword());
		
		
	}
	@Test
	public void forgotPassword() {
	User user = new User();
	UserDTO userdto = new UserDTO();
	when(modelMapper.map(userdto, User.class)).thenReturn(user);
	userdto.setEmailId("dahiphale.nilesh@gmail.com");
	when(passwordEncoder.encode(userdto.getPassword())).thenReturn(userdto.getPassword());
	assertEquals("dahiphale.nilesh@gmail.com", userdto.getEmailId());
	}


}
