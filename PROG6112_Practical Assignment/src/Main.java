import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // display the menu
        System.out.println("LATEST SERIES - 2025");
        System.out.println("****************************************");

        System.out.println("Enter [1] to launch menu or any other key to exit");

        // read user input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.compareTo("1") == 0) {
            System.out.println("Launching main menu");
            
            // create Series object and show menu
            Series seriesApp = new Series();
            seriesApp.showMenu();

        } else {
            System.out.println("Exiting the application");
        }
        scanner.close();
    }
}