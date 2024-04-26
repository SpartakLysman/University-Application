package ua.com.foxminded.universitycms.controllerTest;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ua.com.foxminded.universitycms.service.CourseService;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {

	@Mock
	private CourseService courseService;

//	@MockBean
//	private Course course;

	@Before
	public void setUp() {

		courseService = mock(CourseService.class);

	}

	@Test
	public void CreateTest() {
	}

}
