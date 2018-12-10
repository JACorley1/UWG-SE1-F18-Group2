package edu.westga.cs3211.time_management.test.calendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

public class TestUpdateEvent {

	@Test
	public void testUpdateNullOriginalEvent() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event modifiedEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			myCalendar.updateEvent(null, modifiedEvent);
		});

	}

	@Test
	public void testUpdateNullModifiedEvent() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event originalEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			myCalendar.updateEvent(originalEvent, null);
		});

	}

	@Test
	public void testUpdateEvent() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event originalEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);

		myCalendar.addEvent(originalEvent);

		Event modifiedEvent = new Event("New name", start.plusDays(1), end.plusDays(1), "New school", "New homework",
				Visibility.PRIVATE);
		myCalendar.updateEvent(originalEvent, modifiedEvent);

		assertEquals(true, originalEvent.getName().equals(modifiedEvent.getName()));
		assertEquals(true, originalEvent.getStartTime().equals(modifiedEvent.getStartTime()));
		assertEquals(true, originalEvent.getEndTime().equals(modifiedEvent.getEndTime()));
		assertEquals(true, originalEvent.getLocation().equals(modifiedEvent.getLocation()));
		assertEquals(true, originalEvent.getDescription().equals(modifiedEvent.getDescription()));
		assertEquals(true, originalEvent.getVisibility().equals(modifiedEvent.getVisibility()));
	}

}
