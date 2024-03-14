import java.util.ArrayList;
import java.util.Scanner;

public class PlaneManagement {
    private static int[][] seats; // Array to keep track of seat availability (0 = available, 1 = sold)
    private static ArrayList<Ticket> tickets = new ArrayList<>(); // ArrayList to store all tickets sold in the session

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        initializeSeats();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Buy a seat");
            System.out.println("2. Cancel a seat");
            System.out.println("3. Find the first available seat");
            System.out.println("4. Show seating plan");
            System.out.println("5. Print tickets information");
            System.out.println("6. Search ticket");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    buySeat();
                    break;
                case 2:
                    cancelSeat();
                    break;
                case 3:
                    findFirstAvailable();
                    break;
                case 4:
                    showSeatingPlan();
                    break;
                case 5:
                    printTicketsInformation();
                    break;
                case 6:
                    searchTicket();
                    break;
                case 0:
                    System.out.println("Exiting the Plane Management application");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public static void initializeSeats() {
        // Initialize seats array with 4 rows and varying seat capacities
        seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];
    }

    public static void buySeat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        System.out.print("Enter seat number (1-" + seats[row].length + "): ");
        int seatNumber = scanner.nextInt() - 1;

        if (row < 0 || row >= seats.length || seatNumber < 0 || seatNumber >= seats[row].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        if (seats[row][seatNumber] == 1) {
            System.out.println("Seat is already sold.");
        } else {
            int price;
            if (seatNumber < 5) {
                price = 200;
            } else if (seatNumber < 9) {
                price = 150;
            } else {
                price = 180;
            }

            System.out.print("Enter person's name: ");
            String name = scanner.next();
            System.out.print("Enter person's surname: ");
            String surname = scanner.next();
            System.out.print("Enter person's email: ");
            String email = scanner.next();

            Person person = new Person(name, surname, email);
            Ticket ticket = new Ticket(Character.toString(rowLetter), seatNumber + 1, price, person);
            tickets.add(ticket);

            seats[row][seatNumber] = 1;
            System.out.println("Seat successfully purchased. Price: £" + price);
        }
    }

    public static void cancelSeat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row letter (A-D) of the seat to cancel: ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        System.out.print("Enter seat number (1-14): ");
        int seatNumber = scanner.nextInt() - 1;

        if (seatNumber < 0 || seatNumber >= seats[rowLetter - 'A'].length) {
            System.out.println("Invalid seat number.");
            return;
        }

        if (seats[rowLetter - 'A'][seatNumber] == 0) {
            System.out.println("Seat is already available.");
        } else {
            seats[rowLetter - 'A'][seatNumber] = 0;

            // Remove the corresponding ticket from the tickets ArrayList
            for (Ticket ticket : tickets) {
                if (ticket.getRow().charAt(0) == rowLetter && ticket.getSeat() == seatNumber + 1) {
                    tickets.remove(ticket);
                    System.out.println("Seat successfully cancelled.");
                    return;
                }
            }
            System.out.println("Ticket not found for the given seat.");
        }
    }

    public static void findFirstAvailable() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.println("First available seat: Row " + (char) ('A' + i) + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seat found.");
    }

    public static void showSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int i = 0; i < seats.length; i++) {
            System.out.print((char) ('A' + i) + ": ");
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    public static void printTicketsInformation() {
        System.out.println("Tickets Information:");
        int totalAmount = 0;
    
        for (Ticket ticket : tickets) {
            ticket.printTicketInfo();
            totalAmount += ticket.getPrice();
            System.out.println();
        }
    
        System.out.println("Total amount for tickets sold during the session: £" + totalAmount);
    }
    

    public static void searchTicket() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row letter (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        System.out.print("Enter seat number (1-14): ");
        int seatNumber = scanner.nextInt() - 1;
    
        if (seatNumber < 0 || seatNumber >= seats[rowLetter - 'A'].length) {
            System.out.println("Invalid seat number.");
            return;
        }
    
        if (seats[rowLetter - 'A'][seatNumber] == 1) {
            // Seat is sold, find corresponding ticket
            for (Ticket ticket : tickets) {
                if (ticket.getRow().charAt(0) == rowLetter && ticket.getSeat() == seatNumber + 1) {
                    System.out.println("Ticket Information:");
                    ticket.printTicketInfo();
                    return;
                }
            }
        }
    
        // If no ticket found for the given seat, display that the seat is available
        System.out.println("This seat is available.");
    }
    
}
