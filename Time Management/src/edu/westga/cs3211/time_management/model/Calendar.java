package edu.westga.cs3211.time_management.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Store and manage a collection of events.
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bendrosian, Tristen Rivera
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
		event.setID(this.events.indexOf(event));
	}

	/**
	 * Updates an event in the calendar
	 * 
	 * @precondition event != null
	 * @postcondition
	 *
	 * @param originalEvent the event
	 * @param modifiedEvent the event to modify t
	 */
	public void updateEvent(Event originalEvent, Event modifiedEvent) {
		if (originalEvent == null || modifiedEvent == null) {
			throw new IllegalArgumentException(EVENT_CANNOT_BE_NULL);
		}
		originalEvent.updateEvent(modifiedEvent);
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
					&& !event.getStartTime().isAfter(current.getEndTime()) && current.getID() != event.getID()
					&& !conflicts.contains(current)) {
				conflicts.add(current);
			}
			if (!event.getEndTime().isBefore(current.getStartTime())
					&& !event.getEndTime().isAfter(current.getEndTime()) && current.getID() != event.getID()
					&& !conflicts.contains(current)) {
				conflicts.add(current);
			}
		}

		return conflicts;
	}
}
