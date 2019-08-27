package com.bridgelabz.fundoo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.bridgelabz.fundoo.note.dto.NotesDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.response.ResponseToken;
import com.bridgelabz.fundoo.user.model.MailModel;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserRepo;
import com.bridgelabz.fundoo.user.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FundooUserNoteTest {
	
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
	public void noteShouldBeCreated() {
		Note note=new Note();
		NotesDto noteDto=new NotesDto();
		noteDto.setTitle("title");
		noteDto.setDescription("description");
		assertNotNull("title", noteDto.getTitle());
		assertNotNull("description", noteDto.getDescription());
		
	}
	
	@Test
	public void noteShouldNotBeCreated() {
		Note note=new Note();
		NotesDto noteDto=new NotesDto();
		
		assertNull("title", noteDto.getTitle());
		assertNull("description", noteDto.getDescription());
		
	}

}
