package com.example.demo.services;

import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.users.Event;
import com.example.demo.users.QRCodeGenerator;
import com.example.demo.users.Reservation;
import com.example.demo.users.Visitor;
import com.google.zxing.WriterException;

@Service
public class ReservationServices implements ReservationServiceInterface {

	@Autowired
	private VisitorServices visitorservices;

	@Autowired
	private EventServices eventservices;

	private List<Reservation> allReservations = new ArrayList<Reservation>();

	// Getters and Setters
	public List<Reservation> getAllReservations() {
		return allReservations;
	}

	private Integer UniqReservationID() {
		return allReservations.stream().mapToInt(Reservation::getID).max().orElse(0) + 1;
	}

	// Methods

	public Reservation makeReservation(Integer visitorID, Integer eventID) {
		for (Visitor visitor : visitorservices.getAllVisitors()) {
			if (visitor.getID().equals(visitorID)) {
				for (Event event : eventservices.getAllEvents()) {
					if (event.getId().equals(eventID) && event.getStatus().equals("Approved")) {
						if (takeListSize(eventID).size() < event.getMaxCapacity()) {
							Reservation reservation = new Reservation(visitor, event, UniqReservationID());
							allReservations.add(reservation);
							System.out.println("New reservation created: " + reservation);
							return reservation;
						} else {
							System.out.println("No available seats for the event: " + event.getTitle());
							return null;
						}
					}
				}
			}
		}

		System.out.println("Visitor or Event not found.");
		return null;
	}

	// Remove a reservation from the list using reservationID

	public List<Reservation> removeReservation(Integer reservationID, Integer visitorID) {
		for (Reservation reservation : allReservations) {
			if (reservation.getID().equals(reservationID))
				for (Visitor visitor : visitorservices.getAllVisitors()) {
					if (visitor.getID().equals(visitorID))

						allReservations.remove(reservation);

					System.out.println("Reservation with ID: " + reservationID + " has been removed.");
					return allReservations;
				}
		}

		System.out.println("No reservation found with ID: " + reservationID);
		return allReservations;
	}

	// Remove all reservations for specific eventId

	public List<Reservation> removeAllReservationsForSpecificEvent(Integer eventID) {

		allReservations.removeIf(reservation -> reservation.getEvent().getId().equals(eventID));
		return allReservations;
	}

	// Remove all reservations for specific visitorId

	@Override
	public List<Reservation> removeAllReservationsForSpecificVisitor(Integer visitorID) {

		return allReservations.stream().filter(reservation -> reservation.getVisitor().getID().equals(visitorID))
				.collect(Collectors.toList());
	}

	// Update reservation, update visitor's data

	public Reservation updateReservationVisitorDetails(Integer reservationID, String newName, String newSurname,
			String newEmail) {
		for (Reservation reservation : allReservations) {
			if (reservation.getID().equals(reservationID)) {
				Visitor visitor = reservation.getVisitor();

				if (newName != null && !newName.isEmpty()) {
					visitor.setName(newName);
				}

				if (newSurname != null && !newSurname.isEmpty()) {
					visitor.setSurname(newSurname);
				}

				if (newEmail != null && !newEmail.isEmpty()) {
					visitor.setEmail(newEmail);
				}

				System.out.println(
						"Reservation with ID: " + reservationID + " has been updated with new visitor details.");
				return reservation;
			}
		}

		System.out.println("No reservation found with ID: " + reservationID);
		return null;
	}

	// Take the List's size
	public List<Reservation> takeListSize(Integer eventID) {
		return allReservations.stream().filter(reservation -> reservation.getEvent().getId().equals(eventID))
				.collect(Collectors.toList());

	}

	// Get reservations by event

	public List<Reservation> getReservationsByEvent(Integer eventID) {
		return allReservations.stream().filter(reservation -> reservation.getEvent().getId().equals(eventID))
				.collect(Collectors.toList());
	}

	// Get reservations by visitor

	public List<Reservation> getReservationsByVisitor(Integer visitorID) {
		return allReservations.stream().filter(reservation -> reservation.getVisitor().getID().equals(visitorID))
				.collect(Collectors.toList());
	}

	public String generateQrCodeForReservation(Integer reservationID) {

		String path = String.format("static/images/reservation_%d.png", reservationID);

		for (Reservation reservation : allReservations) {
			if (reservation.getID().equals(reservationID)) {
				try {

					String qrContent = String.format("Reservation ID: %d\nVisitor: %s %s\nEvent: %s\nDate: %s",
							reservation.getID(), reservation.getVisitor().getName(),
							reservation.getVisitor().getSurname(), reservation.getEvent().getTitle(),
							reservation.getEvent().getDay());

					QRCodeGenerator.generateQRCodeImage(qrContent, 250, 250, path);

					System.out.println("QR Code generated for reservation ID: " + reservationID);
					return path;

				} catch (WriterException | IOException e) {
					e.printStackTrace();
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating QR code");
				}
			}
		}

		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation with ID " + reservationID + " not found");
	}

}
