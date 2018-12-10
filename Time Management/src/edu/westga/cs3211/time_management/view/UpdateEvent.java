package edu.westga.cs3211.time_management.view;

import java.time.LocalDate;

import edu.westga.cs3211.time_management.model.Calendar;
import edu.westga.cs3211.time_management.model.Visibility;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

	
	
	
	@FXML
	void updateEvent(ActionEvent event) {

	}

	@FXML
	void cancel(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	
	  @FXML
	    void initialize() {
	        assert visibilityLabel != null : "fx:id=\"visibilityLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert locationText != null : "fx:id=\"locationText\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert startTimeDate != null : "fx:id=\"startTimeDate\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert locationLabel != null : "fx:id=\"locationLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert descriptionText != null : "fx:id=\"descriptionText\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert endTimeDate != null : "fx:id=\"endTimeDate\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert endTimeLabel != null : "fx:id=\"endTimeLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert startTimeLabel != null : "fx:id=\"startTimeLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert visibilityList != null : "fx:id=\"visibilityList\" was not injected: check your FXML file 'AddEvent.fxml'.";
	        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'AddEvent.fxml'.";

	        this.visibilityList.setItems(FXCollections.observableArrayList());
	        this.visibilityList.getItems().add(Visibility.PUBLIC);
	        this.visibilityList.getItems().add(Visibility.PRIVATE);
	        this.visibilityList.getItems().add(Visibility.FRIENDS_ONLY);
	        this.visibilityList.setValue(Visibility.PUBLIC);
	        this.startTimeDate.setValue(LocalDate.now());
	        this.endTimeDate.setValue(LocalDate.now());
	    }
	
	  public void setCalendar(Calendar calendar) {
			if(calendar == null) {
				throw new IllegalArgumentException("Calendar provided was null");
			}
			this.calendar = calendar;
		}
	  
}
