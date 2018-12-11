package edu.westga.cs3211.time_management.test.event;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.Visibility;

/*Tests the event full toString in the time management app
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bendrosian, Tristen Rivera
 */
public class TestToStringFull {

	@Test
	void test() {
		LocalDateTime start = LocalDateTime.now().plusDays(1);
		LocalDateTime end = start.plusDays(1);
		Event event = new Event("Bob", start, end, "location", "description", Visibility.PUBLIC);

		String result = event.toStringFull();

		String expectedString = "";
		expectedString += "Name: Bob" + System.lineSeparator();
		expectedString += "Start time: " + start + System.lineSeparator();
		expectedString += "End time: " + end + System.lineSeparator();
		expectedString += "Location: location" + System.lineSeparator();
		expectedString += "Description: description" + System.lineSeparator();
		expectedString += "Visibility: Public" + System.lineSeparator();

		assertEquals(expectedString, result);
	}

}
