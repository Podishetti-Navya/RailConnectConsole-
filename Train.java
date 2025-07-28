package ReservationConsoleAppForRailway;

public class Train {

	private int trainNumber;
	private String trainName;
	private int totalSeats;
	private int availableSeats;

	public Train(int trainNumber, String trainName, int totalSeats) {
	        this.trainNumber = trainNumber;
	        this.trainName = trainName;
	        this.totalSeats = totalSeats;
	        this.availableSeats = totalSeats;
	    }

	
	public int getTrainNumber() {
		return trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	
	public boolean bookSeat(int seatsRequested) {
		if (availableSeats >= seatsRequested) {
			availableSeats -= seatsRequested;
			return true;
		} else {
			return false;
		}
	}

	public void cancelSeat(int seatsCancelled) {
		availableSeats += seatsCancelled;
	}

	public void displayInfo() {
		System.out.println("Train Number: " + trainNumber);
		System.out.println("Train Name: " + trainName);
		System.out.println("Available Seats: " + availableSeats);
	}

}
