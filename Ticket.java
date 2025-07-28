package ReservationConsoleAppForRailway;

public class Ticket {
	
	
	    private String passengerName;
	    private int trainNumber;
	    private int seatsBooked;

	    public Ticket(String passengerName, int trainNumber, int seatsBooked) {
	        this.passengerName = passengerName;
	        this.trainNumber = trainNumber;
	        this.seatsBooked = seatsBooked;
	    }

	    public int getTrainNumber() {
	        return trainNumber;
	    }

	    public int getSeatsBooked() {
	        return seatsBooked;
	    }

	    public String getPassengerName() {
	        return passengerName;
	    }

	    public void displayTicket() {
	        System.out.println("Passenger: " + passengerName);
	        System.out.println("Train Number: " + trainNumber);
	        System.out.println("Seats Booked: " + seatsBooked);
	    }
	


}
