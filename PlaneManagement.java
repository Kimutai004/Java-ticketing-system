import java.util.Scanner;

public class PlaneManagement {
    private static int[][] seats; // Array to keep track of seat availability (0 = available, 1 = sold)

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

        int price;
            if (seatNumber < 5) {
                price = 200;
            } else if (seatNumber < 9) {
                price = 150;
            } else {
                price = 180;
            }

        if (seats[row][seatNumber] == 1) {
            System.out.println("Seat is already sold.");
        } else {
            seats[row][seatNumber] = 1;
            System.out.println("Seat successfully purchased.  Price: £" + price);
        }
    }

    public static void cancelSeat() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row number (A-D): ");
        char rowLetter = scanner.next().toUpperCase().charAt(0);
        int row = rowLetter - 'A';
        System.out.print("Enter seat number (1-" + seats[row].length + "): ");
        int seatNumber = scanner.nextInt() - 1;

        if (row < 0 || row >= seats.length || seatNumber < 0 || seatNumber >= seats[row].length) {
            System.out.println("Invalid row or seat number.");
            return;
        }

        if (seats[row][seatNumber] == 0) {
            System.out.println("Seat is already available.");
        } else {
            seats[row][seatNumber] = 0;
            System.out.println("Seat successfully cancelled.");
        }
    }

    public static void findFirstAvailable() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    System.out.println("First available seat: Row " + (char)('A' + i) + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seat found.");
    }

    public static void showSeatingPlan() {
        System.out.println("Seating Plan:");
        for (int i = 0; i < seats.length; i++) {
            System.out.print((char)('A' + i) + ": ");
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
        // Method implementation
    }

    public static void searchTicket() {
        // Method implementation
    }
}
