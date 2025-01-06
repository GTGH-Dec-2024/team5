import java.util.ArrayList;
import java.util.List;

public class ReservationDb {
	//Constructor
	private static List<Reservation> allReservations = new ArrayList<>();

	
	//Getters and Setters
	public static List<Reservation> getAllReservations() {
		return allReservations;
	}

	public static void setAllReservations(List<Reservation> allReservations) {
		ReservationDb.allReservations = allReservations;
	}
	
	
	//Add reservation on the reservationDb list
	public static void addReservation(Reservation reservation) {
		allReservations.add(reservation);
	}
	
	//Add reservation from the reservationDb list
	public static void removeReservation(Reservation reservation) {
		allReservations.remove(reservation);
	}
	
	//Event reservations
	public static List<Reservation> EventsReservations(Event event){
		List<Reservation> result = new ArrayList<>();
		for (Reservation reservation : allReservations) {
			if (reservation.getEvent().equals(event)) {
				result.add(reservation);
			}
		}
		return result;
	}
	
	
	//Visitor reservations
	public static List<Reservation> VisitorsReservations(Visitor visitor){
		List<Reservation> result = new ArrayList<>();
		for (Reservation reservation : allReservations) {
			if (reservation.getVisitor().equals(visitor)) {
				result.add(reservation);
			}
		}
		return result;
	}
	
	
	//print allReservations List
	public static void printAllReservations() {
		System.out.println("All Reservations:");
		for (Reservation reservation : allReservations) {
			System.out.println(reservation);
		}
	}
	
}
