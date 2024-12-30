package com.team5.team5.maven;

import java.util.ArrayList;
import java.util.List;

public class EventsDb {
	private static List<Event> listOfEvents= new ArrayList<Event>();
	
	
	//Getters - Setters
	public static List<Event> getListOfEvents() {
		return listOfEvents;
	}

	public void setListOfEvents(List<Event> listOfEvents) {
		this.listOfEvents = listOfEvents;
	}
	//Adds an event to the listOfEvents with status notApprovedAdded
	public static void addToList(Event e) {
		listOfEvents.add(e);
	}
	//When you delete an event it actually changes the events status from approved or notApprovedAdded to notApprovedDeleted
	public static void deleteFromEvent(Event e) {
		e.setStatus("notApprovedDeleted");
	}
	//Print of the list Of Events
	public static void showListOfEvents() {
		System.out.println(listOfEvents);
	}
}
