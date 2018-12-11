package edu.westga.cs3211.time_management.test.calendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

/*Tests the get event function in the time management app
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bendrosian, Tristen Rivera
 */
public class TestGetEvent {

	@Test
	public void testGetNullEvent() {
		Calendar myCalendar = new Calendar();

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			myCalendar.getEvent(null);
		});
	}

	@Test
	public void testGetEvent() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);

		myCalendar.addEvent(myEvent);

		assertEquals(true, myCalendar.getEvent(myEvent).equals(myEvent));
	}

	@Test
	public void testGetFirstEventInListOfthree() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Event myEvent1 = new Event("Name1", start, end, "school1", "homework1", Visibility.PUBLIC);
		Event myEvent2 = new Event("Name2", start, end, "school2", "homework2", Visibility.PUBLIC);

		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent1);
		myCalendar.addEvent(myEvent2);

		assertEquals(true, myCalendar.getEvent(myEvent).equals(myEvent));
	}

	@Test
	public void testGetSecondEventInListOfthree() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Event myEvent1 = new Event("Name1", start, end, "school1", "homework1", Visibility.PUBLIC);
		Event myEvent2 = new Event("Name2", start, end, "school2", "homework2", Visibility.PUBLIC);

		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent1);
		myCalendar.addEvent(myEvent2);

		assertEquals(true, myCalendar.getEvent(myEvent1).equals(myEvent1));
	}

	@Test
	public void testGetThirdEventInListOfthree() {
		Calendar myCalendar = new Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Event myEvent1 = new Event("Name1", start, end, "school1", "homework1", Visibility.PUBLIC);
		Event myEvent2 = new Event("Name2", start, end, "school2", "homework2", Visibility.PUBLIC);

		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent1);
		myCalendar.addEvent(myEvent2);

		assertEquals(true, myCalendar.getEvent(myEvent2).equals(myEvent2));
	}

}
