package ua.com.foxminded.universitycms.controllerTest;

//@WebMvcTest(AdminController.class)
public class AdminControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	private StudentService userService;
//
//	@MockBean
//	private GroupService groupService;
//
//	@Test
//	@WithMockUser(roles = "ADMIN")
//	void testSomeMethodToTest() {
//
//	}
//
//	@Test
//	@WithMockUser(roles = "ADMIN")
//	public void testDeleteStudent_AdminAccess() throws Exception {
//
//		Student testStudent = new Student();
//		testStudent.setName("Jamila");
//		testStudent.setSurname("Ali");
//		testStudent.setLogin("jamilLog");
//		testStudent.setPassword("jamilPass");
//
//		when(groupService.getGroupById(anyLong())).thenReturn(new Group());
//
//		when(userService.save(any(Student.class))).thenReturn(testStudent);
//
//		mockMvc.perform(post("/user/deleteStudent").param("userId", String.valueOf(testStudent.getId())))
//				// Validate the response
//				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/user/student-management"))
//				.andExpect(flash().attributeExists("successMessage"))
//				.andExpect(flash().attribute("successMessage", "Account deleted successfully!"));
//
//		verify(userService).existsByLogin(testStudent.getLogin());
//
//		verify(userService).delete(testStudent);
//	}
}
