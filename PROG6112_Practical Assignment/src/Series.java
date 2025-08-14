import java.util.Scanner;
import java.util.ArrayList;

public class Series {
    private ArrayList<SeriesModel> seriesList; // list to store all the series

    public Series() {
        seriesList = new ArrayList<>(); // make a new empty list
    }

    public void showMenu() {
        boolean running = true;
        Scanner scanner = new Scanner(System.in); // scanner to get user input


        while (running) {
            System.out.println("Please select one of the following menu items");
            System.out.println("(1) Capture a new series");
            System.out.println("(2) Search for a new series");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application");
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear the buffer

            if (choice == 1) {
                System.out.println("Capture a new series");
                CaptureNewSeries(scanner); // call the method to add new series


            } else if (choice == 2) {
                System.out.println("Search for a series");
                SearchForSeries(scanner); // search for existing series

            } else if (choice == 3) {
                System.out.println("Update series age restriction");
                UpdateSeries(scanner); // update series info

            } else if (choice == 4) {
                System.out.println("Delete a series");
                DeleteSeries(scanner); // delete a series

            } else if (choice == 5) {
                System.out.println("Print series report - 2025");
                SeriesReport(scanner); // show all series

            } else if (choice == 6) {
                System.out.println("Exit Application");
                ExitSeriesApplication(scanner); // close the program
                running = false; // exit the loop
            }

        }
        scanner.close(); // close scanner when done


    }

    public void CaptureNewSeries(Scanner scanner) {
        System.out.println("CAPTURE A NEW SERIES");
        System.out.println("********************************");

        System.out.println("Enter the series id: ");
        String seriesId = scanner.nextLine(); // get the id from user

        System.out.println("Enter the series name: ");
        String seriesName = scanner.nextLine(); // get the name from user

        String seriesAge = getValidAgeRestriction(scanner); // get valid age

        System.out.println("Enter the number of episodes: ");
        String seriesNumberOfEpisodes = scanner.nextLine(); // get episode count

        // create new SeriesModel object
        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, seriesAge, seriesNumberOfEpisodes);

        seriesList.add(newSeries); // add the new series to our list

        System.out.println("Series processed successfully!!!");
        System.out.println("Enter (1) to launch menu or any other key to exit");

    }

    private String getValidAgeRestriction(Scanner scanner) {

        int ageRestriction = 0;
        boolean validInput = false;

        System.out.println("Enter the age restriction: ");

        while (!validInput) {
            if (scanner.hasNextInt()) {
                ageRestriction = scanner.nextInt();
                scanner.nextLine(); // clear the buffer

                if (ageRestriction >= 2 && ageRestriction <= 15) {
                    validInput = true; // age is good
                } else {
                    System.out.println("Invalid age! Must be 2-15. Re-enter age:");
                }
            } else {
                // user entered Ten and not 10
                String invalidInput = scanner.next();
                System.out.println("Invalid input! Numbers only. Re-enter age:");
            }
        }
        // Convert the valid age to String and return it
        return String.valueOf(ageRestriction);
    }

    public void SearchForSeries(Scanner scanner){
        System.out.println("Enter the series id to search:" );
        String searchId = scanner.nextLine(); // get the id to search for

        boolean found = false; // flag to check if we found it

        for (int i = 0; i <seriesList.size() ; i++) {
            if (searchId.equals(seriesList.get(i).SeriesId)){
                found = true; // we found it!

                // Print series details
                System.out.println("Series ID: " + seriesList.get(i).SeriesId);
                System.out.println("Series Name: " + seriesList.get(i).SeriesName);
                System.out.println("Series Age Restriction: " + seriesList.get(i).SeriesAge);
                System.out.println("Series Number of Episodes: " + seriesList.get(i).SeriesNumberOfEpisodes);
                System.out.println("Enter (1) to launch menu or any other key to exit");
                break; // stop looking once we find it

            }
        }
        if (!found){
            System.out.println("Series with Series Id: " + searchId + " was not found!");
            System.out.println("Enter (1) to launch menu or any other key to exit");
        }


    }
    public void UpdateSeries(Scanner scanner){
        System.out.println("Enter the series ID to update: ");
        String updateId = scanner.nextLine(); // get the id to update

        boolean found = false; // flag to check if we found it

        for (int i = 0; i < seriesList.size(); i++) {
            if (updateId.equals(seriesList.get(i).SeriesId)){
                found = true; // found the series to update

                System.out.println("Enter the series name: ");
                String newName = scanner.nextLine(); // get new name

                String newAge = getValidAgeRestriction(scanner); // get new age
                String newEpisodes = scanner.nextLine(); // get new episode count

                // update the series info
                seriesList.get(i).SeriesName = newName;
                seriesList.get(i).SeriesAge = newAge;
                seriesList.get(i).SeriesNumberOfEpisodes = newEpisodes;

                System.out.println("Series updated successfully! ");
                break; // stop looking
            }

        }
        if (!found){
            System.out.println("Series with Series Id: " + updateId + " was not found!");
        }
        System.out.println("Enter (1) to launch menu or any other key to exit");

    }
    public void DeleteSeries(Scanner scanner){
        System.out.println("Enter the series Id to delete: ");
        String deleteId = scanner.nextLine(); // get the id to delete

        boolean found = false; // flag to check if we found it

        for (int i = 0; i < seriesList.size(); i++) {
            if (deleteId.equals(seriesList.get(i).SeriesId)){
                found = true; // found the series to delete

                // Ask for confirmation
                System.out.println("Are you sure you want to delete series " + deleteId + " from the system? Yes (y) to delete.");
                String confirmation = scanner.nextLine(); // get user confirmation

                if (confirmation.equals("Y") || confirmation.equals("y")){
                    seriesList.remove(i); // remove the series from the Arraylist
                    System.out.println("Series with series Id: " + deleteId + " WAS deleted!");
                    System.out.println("*******************************************");
                }
                break; // stop looking
            }

        }

        if (!found){
            // series not found - do nothing
        }
        System.out.println("Enter (1) to launch menu or any other key to exit");
    }
    public void SeriesReport(Scanner scanner){

        System.out.println("SERIES REPORT - 2025");
        System.out.println("**********************************");

        // loop through all series and display them
        for (int i = 0; i < seriesList.size(); i++) {
            System.out.println("Series " + (i + 1)); // show series number
            System.out.println("**********************************");
            System.out.println("SERIES ID:" + seriesList.get(i).SeriesId);
            System.out.println("SERIES NAME:" + seriesList.get(i).SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + seriesList.get(i).SeriesAge);
            System.out.println("NUMBER OF EPISODES: " + seriesList.get(i).SeriesNumberOfEpisodes);
            System.out.println("**********************************");
        }

        System.out.println("Enter (1) to launch menu or any other key to exit");

    }
    public void ExitSeriesApplication(Scanner scanner){
        System.out.println("Thank you for using the Series Application!");
        int i = 0;
        System.exit(i); // closing the application

    }

    public void addSeries(SeriesModel seriesModel) {
        seriesList.add(seriesModel); // add a series to the list
    }


    public SeriesModel SearchSeries(String number) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (number.equals(seriesList.get(i).SeriesId)) {
                return seriesList.get(i); // return the series if found
            }
        }
        return null; // return null if not found
    }

    public boolean deleteSeries(String seriesId) {
        for (int i = 0; i < seriesList.size(); i++) {
            if (seriesId.equals(seriesList.get(i).SeriesId)) {
                seriesList.remove(i); // remove the series
                return true; // return true if deleted
            }
        }
        return false; // return false if not found
    }
}











