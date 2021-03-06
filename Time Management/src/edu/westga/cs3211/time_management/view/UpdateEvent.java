package edu.westga.cs3211.time_management.view;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Event;
import edu.westga.cs3211.time_management.model.EventDataValidator;
import edu.westga.cs3211.time_management.model.Visibility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class UpdateEvent to update the selected event with the new user input
 * details.
 * 
 * @author Jonathan Corley, Lucas Carlson, Carson Bendrosian, Tristen Rivera
 */
public class UpdateEvent {

	@FXML
	private Label nameLabel;

	@FXML
	private Label startTimeLabel;

	@FXML
	private Label endTimeLabel;

	@FXML
	private Label locationLabel;

	@FXML
	private Label descriptionLabel;

	@FXML
	private TextField nameText;

	@FXML
	private DatePicker startTimeDate;

	@FXML
	private DatePicker endTimeDate;

	@FXML
	private TextField locationText;

	@FXML
	private TextField descriptionText;

	@FXML
	private ComboBox<Visibility> visibilityList;

	@FXML
	private Label visibilityLabel;

	private Calendar calendar;

	private Event selectedEvent;

	/**
	 * Update event.
	 *
	 * @param event the event
	 */
	@FXML
	void updateEvent(ActionEvent event) {
		String errorText = "";
		String name = this.nameText.getText();
		if (!EventDataValidator.checkName(name)) {
			errorText += "Name is invalid" + System.lineSeparator();
		}
		LocalDateTime startTime = LocalDateTime.of(this.startTimeDate.getValue(), LocalTime.of(9, 0));
		LocalDateTime endTime = LocalDateTime.of(this.endTimeDate.getValue(), LocalTime.of(5, 0));
		if (!EventDataValidator.checkStartTime(startTime)) {
			errorText += "Start time is invalid" + System.lineSeparator();
		} else if (!EventDataValidator.checkEndTime(startTime, endTime)) {
			errorText += "End time is invalid" + System.lineSeparator();
		}
		if (!errorText.isEmpty()) {
			this.displayErrorMessage(errorText);
			return;
		}
		String location = this.locationText.getText();
		if (location == null) {
			location = "";
		}
		String description = this.descriptionText.getText();
		if (description == null) {
			description = "";
		}
		Visibility visibility = this.visibilityList.getValue();
		Event updatedEvent = new Event(name, startTime, endTime, location, description, visibility);
		updatedEvent.setID(this.selectedEvent.getID());
		String conflictingEventText = this.generateConflictingEventsText(updatedEvent);
		Alert alert = this.generateAlert(updatedEvent, conflictingEventText);
		this.showAlert(event, updatedEvent, alert);
	}

	private void showAlert(ActionEvent event, Event updatedEvent, Alert alert) {
		Optional<ButtonType> result = alert.showAndWait();

		if (result.isPresent() && result.get() == ButtonType.OK) {
			this.calendar.updateEvent(this.selectedEvent, updatedEvent);
			((Node) (event.getSource())).getScene().getWindow().hide();
		}
	}

	private Alert generateAlert(Event updatedEvent, String conflictText) {
		String eventText = updatedEvent.toStringFull();
		String eventSummaryAndConflictText = "NEW EVENT DETAILS" + System.lineSeparator() + eventText
				+ System.lineSeparator() + "CONFLICTING EVENTS" + conflictText;
		Alert alert = new Alert(AlertType.CONFIRMATION, eventSummaryAndConflictText);
		alert.setTitle("Update the event?");
		return alert;
	}

	private String generateConflictingEventsText(Event updatedEvent) {
		String conflictingEventText = "";
		List<Event> conflictingEvents = this.calendar.declareConflicts(updatedEvent);
		for (Event currEvent : conflictingEvents) {
			conflictingEventText += currEvent.toString() + System.lineSeparator();
		}
		return conflictingEventText;
	}

	/**
	 * Cancel.
	 *
	 * @param event the event
	 */
	@FXML
	void cancel(ActionEvent event) {
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	/**
	 * Initialize.
	 */
	@FXML
	void initialize() {
		assert this.visibilityLabel != null : "fx:id=\"visibilityLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.locationText != null : "fx:id=\"locationText\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.startTimeDate != null : "fx:id=\"startTimeDate\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.endTimeDate != null : "fx:id=\"endTimeDate\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.endTimeLabel != null : "fx:id=\"endTimeLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.startTimeLabel != null : "fx:id=\"startTimeLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.visibilityList != null : "fx:id=\"visibilityList\" was not injected: check your FXML file 'UpdateEvent.fxml'.";
		assert this.nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'UpdateEvent.fxml'.";

		this.visibilityList.setItems(FXCollections.observableArrayList());
		this.visibilityList.getItems().add(Visibility.PUBLIC);
		this.visibilityList.getItems().add(Visibility.PRIVATE);
		this.visibilityList.getItems().add(Visibility.FRIENDS_ONLY);
	}

	/**
	 * Sets the calendar.
	 *
	 * @param calendar the new calendar
	 */
	public void setCalendar(Calendar calendar) {
		if (calendar == null) {
			throw new IllegalArgumentException("Calendar provided was null");
		}
		this.calendar = calendar;
	}

	/**
	 * Sets the selected event.
	 *
	 * @param event the new selected event
	 */
	public void setSelectedEvent(Event event) {
		this.selectedEvent = event;
		this.nameText.setText(this.selectedEvent.getName());
		this.startTimeDate.setValue(this.selectedEvent.getStartTime().toLocalDate());
		this.endTimeDate.setValue(this.selectedEvent.getEndTime().toLocalDate());
		this.locationText.setText(this.selectedEvent.getLocation());
		this.descriptionText.setText(this.selectedEvent.getDescription());
		this.visibilityList.setValue(this.selectedEvent.getVisibility());

	}

	/**
	 * Display error message.
	 *
	 * @param errorMessage the error message
	 */
	private void displayErrorMessage(String errorMessage) {
		Alert alert = new Alert(AlertType.ERROR, errorMessage);
		alert.showAndWait();
	}

}
