package ReservationConsoleAppForRailway;

import java.util.*;

public class ReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Train> trainList = new ArrayList<>();
    static ArrayList<Ticket> ticketList = new ArrayList<>();

    public static void main(String[] args) {
        // Add sample trains
        trainList.add(new Train(101, "Express Line", 100));
        trainList.add(new Train(102, "Coastal Runner", 50));
        trainList.add(new Train(103, "Mountain Express", 70));

        while (true) {
            System.out.println("\n--- Railway Reservation System ---");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Passenger");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String option = sc.next();

            switch (option) {
                case "1" -> adminLogin();
                case "2" -> passengerLogin();
                case "3" -> {
                    System.out.println("Thank you for using the system.");
                    return;
                }
                default -> System.out.println(" Invalid option. Try again.");
            }
        }
    }

    
 // --- Admin Login ---
    public static void adminLogin() {
        sc.nextLine(); // clear buffer
        int attempts = 0;
        while (true) {
            System.out.print("Enter Admin username: ");
            String user = sc.nextLine();
            System.out.print("Enter Admin password: ");
            String pass = sc.nextLine();

            if (user.equals("admin") && pass.equals("admin123")) {
                System.out.println(" Admin login successful!");
                adminMenu();
                return;
            } else {
                System.out.println(" Invalid credentials.");
                attempts++;

                System.out.print("Do you want to try again? (yes/no): ");
                String choice = sc.nextLine();
                if (!choice.equalsIgnoreCase("yes")) {
                    System.out.println(" Returning to main menu...");
                    return;
                }
            }
        }
    }


    // --- Passenger Login ---
    public static void passengerLogin() {
        sc.nextLine(); // clear buffer
        while (true) {
            System.out.print("Enter Passenger name: ");
            String name = sc.nextLine();
            System.out.print("Enter Passenger password: ");
            String pass = sc.nextLine();

            if (name.length() > 0 && pass.length() > 0) {
                System.out.println(" Welcome, " + name + "!");
                passengerMenu(name);
                return;
            } else {
                System.out.println(" Invalid input. Try again.");
            }
        }
    }

    // --- Admin Menu ---
    public static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. View All Trains");
            System.out.println("2. View All Tickets");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            String choice = sc.next();

            switch (choice) {
                case "1" -> displayTrains(trainList);
                case "2" -> displayTickets(ticketList);
                case "3" -> {
                    System.out.println(" Logged out successfully.\n");
                    return;
                }
                default -> System.out.println(" Invalid option.");
            }
        }
    }

    // --- Passenger Menu ---
    public static void passengerMenu(String passengerName) {
        while (true) {
            System.out.println("\n--- Passenger Menu ---");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View Available Trains");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            String choice = sc.next();

            switch (choice) {
                case "1" -> bookTicket(passengerName);
                case "2" -> cancelTicket(passengerName);
                case "3" -> displayTrains(trainList);
                case "4" -> {
                    System.out.println(" Logged out successfully.\n");
                    return;
                }
                default -> System.out.println(" Invalid option.");
            }
        }
    }

    // --- Book Ticket ---
    public static void bookTicket(String passengerName) {
        System.out.println("\nAvailable Trains:");
        displayTrains(trainList);

        System.out.print("Enter train number: ");
        int trainNo = sc.nextInt();

        Train selectedTrain = findTrainByNumber(trainNo);
        if (selectedTrain == null) {
            System.out.println(" Train not found.");
            return;
        }

        System.out.println("Train Name: " + selectedTrain.getTrainName());
        System.out.println("Available Seats: " + selectedTrain.getAvailableSeats());

        int seats;
        while (true) {
            System.out.print("Enter number of seats to book: ");
            seats = sc.nextInt();
            if (seats > 0) break;
            System.out.println("Please enter a valid number of seats.");
        }

        if (selectedTrain.bookSeat(seats)) {
            Ticket ticket = new Ticket(passengerName, trainNo, seats);
            ticketList.add(ticket);
            System.out.println(" Ticket booked successfully!");
        } else {
            System.out.println(" Not enough available seats.");
        }
    }

    // --- Cancel Ticket ---
    public static void cancelTicket(String passengerName) {
        sc.nextLine(); 
        System.out.print("Are you sure you want to cancel the ticket? (yes/no): ");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Cancellation aborted.");
            return;
        }

        Ticket ticketToCancel = null;
        for (Ticket t : ticketList) {
            if (t.getPassengerName().equalsIgnoreCase(passengerName)) {
                ticketToCancel = t;
                break;
            }
        }

        if (ticketToCancel != null) {
            Train train = findTrainByNumber(ticketToCancel.getTrainNumber());
            if (train != null) {
                train.cancelSeat(ticketToCancel.getSeatsBooked());
            }
            ticketList.remove(ticketToCancel);
            System.out.println(" Ticket cancelled successfully.");
        } else {
            System.out.println(" Ticket not found for this passenger.");
        }
    }

    // --- Display Trains ---
    public static void displayTrains(ArrayList<Train> trains) {
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }

        System.out.println("\n--- Train List ---");
        for (Train t : trains) {
            t.displayInfo();
            System.out.println("------------------------");
        }
    }

    // --- Display Tickets ---
    public static void displayTickets(ArrayList<Ticket> tickets) {
        if (tickets.isEmpty()) {
            System.out.println("No tickets have been booked yet.");
            return;
        }

        System.out.println("\n--- Booked Tickets ---");
        for (Ticket t : tickets) {
            t.displayTicket();
            System.out.println("------------------------");
        }
    }

    // --- Find Train By Number ---
    public static Train findTrainByNumber(int trainNo) {
        for (Train t : trainList) {
            if (t.getTrainNumber() == trainNo) {
                return t;
            }
        }
        return null;
    }
}
