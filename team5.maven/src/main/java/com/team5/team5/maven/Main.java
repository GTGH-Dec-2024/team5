package com.team5.team5.maven;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//List<Event> notApprovedEvents= new ArrayList<Event>();
		
		Organizer org1 = new Organizer(15476,"Mary","Smith","partyAnimal");
//		Event e1 = new Event("party","ball","a party ","house",30,15,"January",2025,21,30,org1);
//		System.out.println(e1);
		org1.addEvent("party","ball","a party ","house",30,15,"January",2025,21,30);
		org1.addEvent("disco","dance","a party ","house",35,14,"January",2025,14,30);
		org1.deleteEvent("party");
		//org1.deleteEvent("disco");
		//Organizer org2 = new Organizer(5872,"John","Watchon","org");
		//org2.deleteEvent("party");
		//org1.showList();
		//org2.showList();
		EventsDb.showListOfEvents();
	}

}
