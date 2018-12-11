package edu.westga.cs3211.time_management.test.event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

/*Tests the event updater in the time management app
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bendrosian, Tristen Rivera
 */
public class TestUpdateEvent {

	@Test
	public void testUpdateNullEvent() {
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);

		Event originalEvent = new Event("Bob", start, end, "location", "description", Visibility.PUBLIC);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			originalEvent.updateEvent(null);
		});
	}

	@Test
	public void testUpdateOneEvent() {
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);

		Event originalEvent = new Event("Bob", start, end, "location", "description", Visibility.PUBLIC);
		Event modifiedEvent = new Event("New Bob", start.plusDays(4), end.plusDays(4), "New location",
				"New description", Visibility.PUBLIC);

		originalEvent.updateEvent(modifiedEvent);

		assertEquals(true, originalEvent.getName().equals(modifiedEvent.getName()));
		assertEquals(true, originalEvent.getStartTime().equals(modifiedEvent.getStartTime()));
		assertEquals(true, originalEvent.getEndTime().equals(modifiedEvent.getEndTime()));
		assertEquals(true, originalEvent.getLocation().equals(modifiedEvent.getLocation()));
		assertEquals(true, originalEvent.getDescription().equals(modifiedEvent.getDescription()));
		assertEquals(true, originalEvent.getVisibility().equals(modifiedEvent.getVisibility()));
	}

}
