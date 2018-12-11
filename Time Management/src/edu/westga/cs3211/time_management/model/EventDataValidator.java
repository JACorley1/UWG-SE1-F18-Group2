package edu.westga.cs3211.time_management.model;

import java.time.LocalDateTime;

/**
 * Validate Event information.
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bendrosian, Tristen Rivera
 */
public class EventDataValidator {

	/**
	 * Checks if the even name is valid
	 * 
	 * @precondition name must be < 60 characters and name cannot be empty
	 * @postcondition none
	 * 
	 * @param name the name of the event
	 * @return true if the name is valid
	 */
	public static boolean checkName(String name) {
		boolean result = true;
		if (name == null) {
			result = false;
		} else if (name.length() >= 60) {
			result = false;
		} else if (name.isEmpty()) {
			result = false;
		}
		return result;
	}

	/**
	 * Checks the start time, determining if it is before the current system time.
	 * This is used when displaying user warning when creating events startng in the
	 * past.
	 * 
	 * @param startTime the starting time of the event
	 * 
	 * @precondition startTime != null
	 * @throws NullPointerException if startTime is null
	 * 
	 * @return true if the start time is before the current time, false otherwise.
	 */
	public static boolean checkStartTime(LocalDateTime startTime) {
		if (startTime == null) {
			return false;
		}

		return !startTime.isBefore(LocalDateTime.now());
	}

	/**
	 * Checks the end time, determining if it is after the starting time.
	 * This is used when displaying user warning when creating events with invalid event times.
	 * 
	 * 
	 * @precondition startTime != null
	 * 
	 * @param startTime The starting time of the event
	 * @param endTime   the ending time of the event
	 * 
	 * @return true if endTime is a valid time after startTime false if endTime is
	 *         not or is not after startTime
	 */
	public static boolean checkEndTime(LocalDateTime startTime, LocalDateTime endTime) {
		if (startTime == null) {
			throw new IllegalArgumentException("startTime cannot be null");
		}
		if (endTime == null) {
			return false;
		}
		return endTime.isAfter(startTime);
	}

}
