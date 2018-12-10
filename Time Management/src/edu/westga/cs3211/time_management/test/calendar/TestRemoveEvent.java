package edu.westga.cs3211.time_management.test.calendar;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

public class TestRemoveEvent {

	@Test
	public void testRemoveNullEvent() {
		Calendar myCalendar = new Calendar();

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			myCalendar.removeEvent(null);
		});
	}
	
	@Test
	public void testRemoveOneEvent() {
		Calendar myCalendar = new  Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		
		myCalendar.addEvent(myEvent);
		myCalendar.removeEvent(myEvent);
		
		assertEquals(0, myCalendar.getEvents().size());
		assertEquals(false, myCalendar.getEvents().contains(myEvent));

	}
	
	@Test
	public void testRemoveFirstEventInListOf3() {
		Calendar myCalendar = new  Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Event myEvent1 = new Event("Name1", start, end, "school1", "homework1", Visibility.PUBLIC);
		Event myEvent2 = new Event("Name2", start, end, "school2", "homework2", Visibility.PUBLIC);
		
		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent1);
		myCalendar.addEvent(myEvent2);
		
		myCalendar.removeEvent(myEvent);
		assertEquals(2, myCalendar.getEvents().size());
		assertEquals(false, myCalendar.getEvents().contains(myEvent));
	}
	
	@Test
	public void testRemoveSecondEventInListOf3() {
		Calendar myCalendar = new  Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Event myEvent1 = new Event("Name1", start, end, "school1", "homework1", Visibility.PUBLIC);
		Event myEvent2 = new Event("Name2", start, end, "school2", "homework2", Visibility.PUBLIC);
		
		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent1);
		myCalendar.addEvent(myEvent2);
		
		myCalendar.removeEvent(myEvent1);
		assertEquals(2, myCalendar.getEvents().size());
		assertEquals(false, myCalendar.getEvents().contains(myEvent1));
	}
	
	@Test
	public void testRemoveThirdEventInListOf3() {
		Calendar myCalendar = new  Calendar();
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event myEvent = new Event("Name", start, end, "school", "homework", Visibility.PUBLIC);
		Event myEvent1 = new Event("Name1", start, end, "school1", "homework1", Visibility.PUBLIC);
		Event myEvent2 = new Event("Name2", start, end, "school2", "homework2", Visibility.PUBLIC);
		
		myCalendar.addEvent(myEvent);
		myCalendar.addEvent(myEvent1);
		myCalendar.addEvent(myEvent2);
		
		myCalendar.removeEvent(myEvent2);
		assertEquals(2, myCalendar.getEvents().size());
		assertEquals(false, myCalendar.getEvents().contains(myEvent2));
	}
	

}
