import java.util.ArrayList;
import java.util.List;

public class Organizer {
	private int afm ;
	private String name;
	private String surname;
	private String description;
	private List<Event> notApprovedEvents= new ArrayList<Event>();
	private List<Event> deletedEvents= new ArrayList<Event>();
	
	
	//Constractor
	public Organizer(int afm, String name, String surname, String description) {
		this.afm = afm;
		this.name = name;
		this.surname = surname;
		this.description = description;
		
		//Getters + Setters
	}
	public int getAfm() {
		return afm;
	}
	public void setAfm(int afm) {
		this.afm = afm;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	//ToString
	@Override
	public String toString() {
		return "Organizer [afm=" + afm + ", name=" + name + ", surname=" + surname + ", description=" + description
				+ "]";
	}
	
	
	
	//add a new event and put it in the list with the not approved events 
	public void addEvent(String title, String theme, String description, String location, int maxCapacity,int day,
			String month, int year, int hour, int minute) {
		Event newEvent = new Event(title,theme,description,location,maxCapacity,day,month,year,hour,minute,this);
		notApprovedEvents.add(newEvent);
		System.out.println("Not approved Events: "+notApprovedEvents);
		
		
	}
	//looks for an event with the specific title this organizer had previously added in the list and then it gets deleted and put in the list with the deleted events
	public void deleteEvent(String title) {
		for(Event e : notApprovedEvents) {
			if(title.equals(e.getTitle()) && e.getOrganizer() == this) {
				notApprovedEvents.remove(e);
				deletedEvents.add(e);
			}
		}
		System.out.println(notApprovedEvents);
		System.out.println("Deleted Events: "+deletedEvents);
	}
	//Shows the list of events the specific organizer has made 
	public void showList() {
		for(Event e : notApprovedEvents) {
			if(e.getOrganizer()== this) {
				System.out.println(e);
			}
		}
	}
	
}
