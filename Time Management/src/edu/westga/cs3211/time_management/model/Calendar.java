package edu.westga.cs3211.time_management.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Store and manage a collection of events.
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bedrosian, Nolan Williams,
 *         Kevin Flynn, Victoria Jenkins, Laura Smedley, Jonathan Nicholl,
 *         Brandon Walker
 */
public class Calendar {

	private static final String EVENT_CANNOT_BE_NULL = "Event cannot be null";
	private ArrayList<Event> events;

	/**
	 * Return the collection of events in the calendar
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the collection of events in the calendar
	 */
	public ArrayList<Event> getEvents() {
		return this.events;
	}

	/**
	 * Create a new initially empty Calendar
	 * 
	 * @precondition none
	 * @postcondition getEvents().size() == 0
	 */
	public Calendar() {
		this.events = new ArrayList<Event>();
	}

	/**
	 * Add a new event to the calendar
	 * 
	 * @precondition event != null
	 * @postcondition getEvents().size() == getEvents().size()@pre + 1
	 * 
	 * @param event event to be added to the calendar
	 */
	public void addEvent(Event event) {
		if (event == null) {
			throw new IllegalArgumentException(EVENT_CANNOT_BE_NULL);
		}

		this.events.add(event);
	}

	
	/**
	 * Updates an event in the calendar
	 * 
	 * @precondition event != null
	 * @postcondition
	 *
	 * @param event the event
	 * @param name the name
	 * @param start the start
	 * @param end the end
	 * @param location the location
	 * @param description the description
	 * @param visibility the visibility
	 */
	public void updateEvent(Event event, String name, LocalDateTime start, LocalDateTime end, String location,
			String description, Visibility visibility) {
		if (event == null) {
			throw new IllegalArgumentException(EVENT_CANNOT_BE_NULL);
		}
		event.setName(name);
		event.setStartTime(start);
		event.setEndTime(end);
		event.setLocation(location);
		event.setDescription(description);
		event.setVisibility(visibility);
	}

	/**
	 * Remove an event from the calendar
	 * 
	 * @precondition event != null
	 * @postcondition getEvents().size() == getEvents().size()@pre - 1
	 * 
	 * @param event event to be removed from the calendar
	 */
	public void removeEvent(Event event) {
		if (event == null) {
			throw new IllegalArgumentException(EVENT_CANNOT_BE_NULL);
		}
		this.events.remove(event);
	}

	/**
	 * Gets the desired event
	 * 
	 * @param event the event
	 * @precondition event != null
	 * @return the event
	 */
	public Event getEvent(Event event) {
		if (event == null) {
			throw new IllegalArgumentException(EVENT_CANNOT_BE_NULL);
		}
		int indexOfEvent = this.events.indexOf(event);
		return this.events.get(indexOfEvent);
	}

	/**
	 * Finds and returns the list of events in the calendar that would conflict with
	 * the specified event
	 * 
	 * @precondition event != null
	 * @postcondition none
	 * 
	 * @param event the event to check for conflicts
	 * 
	 * @return the list of events in the calendar that would conflict with the
	 *         specified event
	 */
	public List<Event> declareConflicts(Event event) {
		if (event == null) {
			throw new IllegalArgumentException(EVENT_CANNOT_BE_NULL);
		}
		List<Event> conflicts = new ArrayList<Event>();

		for (Event current : this.events) {
			if (!event.getStartTime().isBefore(current.getStartTime())
					&& !event.getStartTime().isAfter(current.getEndTime())) {
				conflicts.add(current);
			}
			if (!event.getEndTime().isBefore(current.getStartTime())
					&& !event.getEndTime().isAfter(current.getEndTime())) {
				conflicts.add(current);
			}
		}

		return conflicts;
	}
}
