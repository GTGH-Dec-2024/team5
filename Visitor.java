import java.util.ArrayList;
import java.util.List;

public class Visitor {

	//Properties
	private String name;
	private String surname;
	private String email;

	
	//Constructor
	public Visitor(String name,String surname,String email) {
		
		this.name = name;
		this.surname = surname ;
		this.email = email ;
	}
	
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	//Methods
	
	//Search Event
	public void searchEvent() {
		
	}
	
	//Make Reservation
	public boolean makeReservation(Event event) {
		if(event.getCountVisitors() < event.getMaxCapacity()) {  //check for available seats
			Reservation reservation = new Reservation (this,event); 
			ReservationDb.addReservation(reservation);
			event.addToCountVisitors();
			System.out.println(name + " " + surname + "made reservation for the Event: " + event.getTitle());
			return true;
		}else {
			System.out.println("Sorry, there is no available seats for this Event. ");
		return false;
		}
	}
	
	//Cancel Reservation
	public void cancelReservation() {
		List<Reservation> visitorReservation = ReservationDb.VisitorsReservations(this);
		
		
		
	}
	
}