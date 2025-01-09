package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class VisitorServices {

	//Constructor
	private List<Visitor> visitors = new ArrayList<Visitor>();
	
	
	//Getters and Setters
	public List<Visitor> getAllVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}


	
	//Methods
	
	
	//Remove visitor from the list
	public List<Visitor> removeVisitor(String email) {
		visitors.removeIf(visitor -> visitor.getEmail().equals(email));
		return visitors;
	}
	
	
	//Add visitor to the list
	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
	}
	
	
	//Update visitor's informations
	public List<Visitor> updateVisitor(String newName,String newSurname,String email) {
		for (Visitor visitor : visitors) {
			if(visitor.getEmail().equals(email)) {
				if(newName !=null) 
					visitor.setName(newName); 
				
				if(newSurname !=null) 
					visitor.setSurname(newSurname); 
			}
		}
		return visitors;
	
	}

	
	
	public List<Visitor> findVisitorByEmail(String email) {
		for (Visitor visitor : visitors) {
			if(visitor.getEmail().equals(email))
				return visitors;
		}
		
		System.out.println("This email" + email + "does not corresponds to a visitor");
		return null;
		
	}
	
	
	
}
