import java.util.Scanner;

public class AirlineReservationSystem {
    private static boolean[] seats = new boolean[10];
    private static String[] seatNames = new String[10]; // Stores names of seat holders

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            // Display menu
            System.out.println("\n===== Airline Reservation System =====");
            System.out.println("1. Reserve a seat manually");
            System.out.println("2. Auto assign a seat");
            System.out.println("3. View all seats");
            System.out.println("4. View summary");
            System.out.println("5. Reset all reservations");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    reserveSeat(input);
                    break;
                case 2:
                    autoAssignSeat(input);
                    break;
                case 3:
                    viewSeats();
                    break;
                case 4:
                    viewSummary();
                    break;
                case 5:
                    resetSeats();
                    break;
                case 6:
                    System.out.print("Are you sure you want to exit? (y/n): ");
                    String confirm = input.next();
                    if (confirm.equalsIgnoreCase("y")) {
                        System.out.println("Exiting... Thank you!");
                        input.close();
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void reserveSeat(Scanner input) {
        System.out.print("Available seats: ");
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.print("\nEnter seat number (1-10): ");
        int seatNum = input.nextInt();

        if (seatNum < 1 || seatNum > 10) {
            System.out.println("Invalid seat number. Please choose between 1 and 10.");
            return;
        }

        if (!seats[seatNum - 1]) {
            System.out.print("Enter your name: ");
            String name = input.next();
            seats[seatNum - 1] = true;
            seatNames[seatNum - 1] = name;
            System.out.println("Seat " + seatNum + " reserved for " + name + ".");
        } else {
            System.out.println("Sorry, this seat is already reserved.");
        }
    }

    private static void autoAssignSeat(Scanner input) {
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i]) {
                System.out.print("Enter your name: ");
                String name = input.next();
                seats[i] = true;
                seatNames[i] = name;
                System.out.println("Seat " + (i + 1) + " has been automatically assigned to " + name + ".");
                return;
            }
        }
        System.out.println("Sorry, no available seats.");
    }

    private static void viewSeats() {
        System.out.println("\n===== Seat Status =====");
        for (int i = 0; i < seats.length; i++) {
            System.out.print("Seat " + (i + 1) + ": ");
            if (seats[i]) {
                System.out.println("Reserved by " + seatNames[i]);
            } else {
                System.out.println("Available");
            }
        }
    }

    private static void viewSummary() {
        int reserved = 0;
        for (boolean seat : seats) {
            if (seat) reserved++;
        }
        System.out.println("\n===== Summary =====");
        System.out.println("Total Seats: 10");
        System.out.println("Reserved Seats: " + reserved);
        System.out.println("Available Seats: " + (seats.length - reserved));
    }

    private static void resetSeats() {
        for (int i = 0; i < seats.length; i++) {
            seats[i] = false;
            seatNames[i] = null;
        }
        System.out.println("All reservations have been cleared.");
    }
}