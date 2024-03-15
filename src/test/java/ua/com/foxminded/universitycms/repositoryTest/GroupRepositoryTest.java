package ua.com.foxminded.universitycms.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import ua.com.foxminded.universitycms.model.Group;
import ua.com.foxminded.universitycms.repository.GroupRepository;

@SpringBootTest(classes = { GroupRepository.class })
public class GroupRepositoryTest {

	@Mock
	private GroupRepository groupRepository;

	@Test
	public void testDeleteGroup() {
		Group groupToDelete = new Group();

		when(groupRepository.deleteGroup(groupToDelete)).thenReturn(true);

		boolean result = groupRepository.deleteGroup(groupToDelete);

		verify(groupRepository).deleteGroup(groupToDelete);
		assertTrue(result);
	}

	@Test
	public void testFindByIdWithCourses() {
		Long groupId = 1L;

		when(groupRepository.findByIdWithCourses(groupId)).thenReturn(Optional.of(new Group()));

		Optional<Group> group = groupRepository.findByIdWithCourses(groupId);

		verify(groupRepository).findByIdWithCourses(groupId);
		assertTrue(group.isPresent());
	}

	@Test
	public void testFindByTitle() {
		String title = "Math Group";

		when(groupRepository.findByTitle(title)).thenReturn(Arrays.asList(new Group()));

		List<Group> groups = groupRepository.findByTitle(title);

		verify(groupRepository).findByTitle(title);
		assertNotNull(groups);
		assertFalse(groups.isEmpty());
	}
}
