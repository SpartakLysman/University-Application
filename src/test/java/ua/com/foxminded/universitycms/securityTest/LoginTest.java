package ua.com.foxminded.universitycms.securityTest;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(StudentController.class)
public class LoginTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private StudentRepository studentRepository;
//
//	@MockBean
//	private StudentService studentService;
//
//	@InjectMocks
//	private StudentController studentController;
//
//	@Test
//	public void testLoginPage() throws Exception {
//		mockMvc.perform(MockMvcRequestBuilders.get("/login")).andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.view().name("login"));
//	}
//
//	@Test
//	public void testRegistrationSuccess() throws Exception {
//	    when(studentRepository.findByLoginAndPassword("log", "pass").get().thenReturn(null);
//	    
//	    mockMvc.perform(post("/register/save")
//	            .param("firstname", "testuser123")
//	            .param("lastname", "testuser123")
//	            .param("login", "testuser123")
//	            .param("password", "testpassword"))
//	            .andExpect(MockMvcResultMatchers.status().isOk())
//	            .andExpect(MockMvcResultMatchers.view().name("login"));
//	    
//	    verify(studentRepository, times(1)).save(any(Student.class));
//	    verify(studentRepository, times(1)).delete(any(Student.class));
//	}
//
//	@Test
//	@WithMockUser(roles = "ADMIN")
//	public void testDeleteStudent_AdminAccess() throws Exception {
//		Student testStudent = new Student();
//		testStudent.setId(1L); // Set a valid ID
//		testStudent.setName("TestStd");
//		testStudent.setSurname("TestStd");
//		testStudent.setLogin("teststd2");
//		testStudent.setPassword("test");
//
//		when(studentService.findById(testStudent.getId()).thenReturn(true);
//
//		mockMvc.perform(post("/user/deleteStudent").param("studentId", String.valueOf(testStudent.getId())))
//				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/user/student-management"))
//				.andExpect(flash().attributeExists("successMessage"))
//				.andExpect(flash().attribute("successMessage", "Account deleted successfully!"));
//
//		verify(studentService, times(1)).delete(testStudent);
//	}
}
