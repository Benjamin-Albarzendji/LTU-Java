import java.util.Scanner;
import java.lang.String;
/*
* Name: Benjamin Albarzendji  
* LTU username: benalb-2
* Replit link: https://replit.com/@ltu-d0017d-v23/Exam-2023-05-31-benalb2#Main.java
*/

/* What can you use? You can use the String class and Math Class.
   What you cannot use? No Arraylists, No Vectors, and related classes have an inbuilt ability to sort and rearrange items. Arrays.sort() function should not be used at all to sort arrays.
*/

// Main Class
class Main {

  // Global scanner object in the Main Class
  private static Scanner userInput = new Scanner(System.in);

  /**
   * MAIN
   */
  public static void main(String[] args) {

    // Constants for the arrays
    final int MAXIMUM_CARS = 10;
    final int INDEX_COUNT = 3;

    // Price CONSTANT
    final int PRICE = 120;

    // Fleet Array
    // 1. Numberplate
    // 2. Model
    // 3. Status
    String[][] fleet = new String[MAXIMUM_CARS][INDEX_COUNT];

    // Rental Array - larger size due to it being a log rather than a storage of
    // cars like the fleet array.
    // 1. Model Number
    // 2. Pickup
    // 3. Name
    // 4. Return
    // 5. Cost
    String[][] rentals = new String[1000][5];

    // While loop to run the main program via tha menu
    while (true) {

      // Get the user choice for menu method
      int userChoice = menu();

      // Quit program if -1
      if (userChoice == -1) {
        System.out.println("Logging out...");
        return;
      }

      // Add a car to fleet
      if (userChoice == 1) {
        insertCartoFleet(fleet);

      }
      // Rent a car
      if (userChoice == 2) {
        rentCar(fleet, rentals);

      }
      // Return a car
      if (userChoice == 3) {
        returnCar(fleet, rentals, PRICE);

      }

      // Print fleet
      if (userChoice == 4) {
        printFleet(fleet);
      }

      // Rental Summary
      if (userChoice == 5) {
        rentalSummary(rentals);
      }
    }
  }

  /**
   * MENU METHOD
   * The menu method provides interactivity and navigability within the
   * application
   */
  public static int menu() {

    // Variables for userInput
    int userChoice = 0;
    int inputToInteger = 0;

    while (true) {
      // Prints out the menu options
      System.out.println("----------------------------------");
      System.out.println("# LTU Rent-a-car");
      System.out.println("----------------------------------");
      System.out.println("1. Add car to fleet");
      System.out.println("2. Rent a car");
      System.out.println("3. Return a car");
      System.out.println("4. Print car fleet");
      System.out.println("5. Print rental summary");
      System.out.println("q. End program");
      System.out.print("> Enter your option: ");

      // Gets the user choice via the input method
      String input = userInput.next();

      // Returns -1 for quitting
      if (input.equals("q")) {
        System.out.println("");
        return -1;
      }

      // Try catch block to convert the string to an integer
      try {
        inputToInteger = Integer.parseInt(input);
      }

      catch (NumberFormatException e) {
        System.out.println("");
        System.out.println("The only appropiate non-number choice is 'q' Try again.");
        System.out.println("");
        continue;
      }

      if (inputToInteger < 1 || inputToInteger > 6) {
        System.out.println("The only appropiate number choice is between 1-6. Try again.");
        System.out.println("");
        continue;

      }
      userChoice = inputToInteger;
      break;
    }

    // Returns the userChoice in the form of an integer
    System.out.println("");
    return userChoice;
  }

  /**
   * INPUT METHOD FOR INSERTING CAR TO FLEET ARRAY
   * Utilizes several methods to insert a car to the fleet array.
   * Returns nothing.
   */

  public static void insertCartoFleet(String[][] fleet) {
    String regNum;
    String modelMake;
    String status = "Available";

    while (true) {
      // Get the registrationNumber
      regNum = registrationNumberInput(0);

      // If user wants to quit from model input
      if (regNum.equals("q")) {
        return;
      }

      // Check if if exists in the array
      boolean carRegistered = isCarInFleet(fleet, regNum);

      if (carRegistered == true) {
        System.out.println("This vehicle is already registered, try another plate or 'q' for quit.");
        continue;
      }

      // Get the model make
      modelMake = modelAndNameInput(0);

      // Break the loop
      break;

    }

    // Finds the first available spot in the array
    for (int i = 0; i < fleet.length; i++) {
      if (fleet[i][0] == null) {
        fleet[i][0] = regNum;
        fleet[i][1] = modelMake;
        fleet[i][2] = status;
        System.out.println("");
        break;
      }
    }

  }

  /**
   * RENTAL SUMMARY METHOD
   * This method prints out the rental summary from the rental array
   * Returns nothing
   */

  public static void rentalSummary(String[][] rentals) {
    // Int variables to be displayed later
    int totalRentals = 0;
    int totalRevenue = 0;

    // Headers for the print function
    String nameHeader = "Name";
    String numberPlateHeader = "Numberplate";
    String pickupHeader = "Pickup";
    String returnHeader = "Return";
    String costHeader = "Cost";

    // Printing out headers
    System.out.println("LTU Rent-a-car rental summary:\n");
    System.out.println("Rentals:");

    System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", nameHeader, numberPlateHeader, pickupHeader, returnHeader,
        costHeader);

    // Looping through the rentals arrays
    for (int i = 0; i < rentals.length; i++) {
      if (rentals[i][0] != null) {
        totalRentals += 1;

        // Ensuring a try catch is there to prevent crashing from parsing empty slots
        try {
          totalRevenue += Integer.parseInt(rentals[i][4]);
        } catch (NumberFormatException e) {

        }
        System.out.printf("%-15s %-15s %-15s %-15s %-15s%n", rentals[i][2],
            rentals[i][0], rentals[i][1], rentals[i][3], rentals[i][4]);
      }
    }

    // Printing out bottom
    System.out.printf("%nTotal Number of rentals: %d%n", totalRentals);
    System.out.printf("Total Revenue %d SEK%n", totalRevenue);
    System.out.println("");

  }

  /**
   * PRINT METHOD FOR THE FLEET
   * This method prints out the fleet summary.
   * Returns nothing
   */

  public static void printFleet(String[][] fleet) {
    int totalCars = 0;
    int availableCars = 0;

    // Headers
    String modelHeader = "Model";
    String numberHeader = "Numberplate";
    String statusHeader = "Status";

    // Printing out headers
    System.out.println("LTU Rent-a-car car fleet:\n");
    System.out.println("Fleet:");

    System.out.printf("%-20s %-20s %-10s%n", modelHeader, numberHeader, statusHeader);

    // Looping through the array
    for (int i = 0; i < fleet.length; i++) {
      if (fleet[i][0] != null) {
        totalCars += 1;
        if (fleet[i][2].equals("Available")) {
          availableCars += 1;
        }
        System.out.printf("%-20s %-20s %-10s%n", fleet[i][1], fleet[i][0], fleet[i][2]);
      }
    }

    // Printing out bottom

    System.out.printf("%nTotal Number of cars: %d%n", totalCars);
    System.out.printf("Total Number of available cars: %d%n", availableCars);
    System.out.println("");

  }

  /**
   * RETURN CAR METHOD
   * This method is utilized for returning a car. Mutates both the rentals and
   * fleet arrays
   * No return
   */

  public static void returnCar(String[][] fleet, String[][] rentals, int PRICE) {
    String regNum;
    String returnTime;
    String pickUpTime = "0";
    String cost;
    int indexLocationRentals = 0;
    int indexLocationFLeet = 0;
    int costInt = 0;

    while (true) {
      // Query for user registration number:
      regNum = registrationNumberInput(0);

      // If user wants to quit from method input
      if (regNum.equals("q")) {
        return;
      }

      // Check if exists
      boolean carExists = isCarInFleet(fleet, regNum);
      if (carExists == false) {
        System.out.println("This registration number does not exist, try another one or or 'q' for quit");
        System.out.println("");
        continue;
      }

      // Check if car is available
      boolean carAvailable = isCarAvailable(fleet, regNum);
      if (carAvailable == true) {
        System.out
            .println("This car is available, you cannot return a car that has not been rented out. 'q' for quit");
        System.out.println("");
        continue;
      }

      // Gets the return time
      returnTime = pickUpAndReturnTimeTime(1);

      // Gets the pick up time
      for (int i = 0; i < rentals.length; i++) {
        if (rentals[i][0] != null) {
          if (rentals[i][0].equals(regNum)) {
            pickUpTime = rentals[i][1];
          }
        }
      }

      // Validates the pickup time with the return time
      int totalHours = timeValidation(pickUpTime, returnTime);

      // Overnight return
      if (totalHours == -1) {
        System.out.println(" You can only return a car after you rented it. We do not allow overnight returns.");
        System.out.println("");
        continue;
      }
      // The cost
      costInt = totalHours * PRICE;
      cost = String.valueOf(costInt);

      // If total hours is 0 = base cost, i.e 1 hours
      if (totalHours == 0) {
        costInt = PRICE * 1;
        cost = String.valueOf(costInt);
      }

      // Populate the rentals arrays
      for (int i = 0; i < rentals.length; i++) {
        if (rentals[i][0] != null) {
          if (rentals[i][0].equals(regNum)) {
            indexLocationRentals = i;
            rentals[i][3] = returnTime;
            rentals[i][4] = cost;
          }

        }
      }

      // Makes car available again in the fleet array
      for (int i = 0; i < fleet.length; i++) {
        if (fleet[i][0] != null) {
          if (fleet[i][0].equals(regNum)) {
            indexLocationFLeet = i;
            fleet[i][2] = "Available";
          }
        }

      }

      // Prints it all out
      System.out.println("===================================");
      System.out.println("LTU Rent-a-car");
      System.out.println("===================================");

      System.out.printf("Name: %s%n", rentals[indexLocationRentals][2]);
      System.out.printf("Car: %s (%s)%n", fleet[indexLocationFLeet][1], fleet[indexLocationFLeet][0]);
      System.out.printf("Time: %s-%s (%d hours)%n", pickUpTime, returnTime, totalHours);
      System.out.printf("Total Cost: %s SEK%n", rentals[indexLocationRentals][4]);

      System.out.println("===================================");

      break;
    }
  }

  /**
   * RENT CAR METHOD
   * This method is utilized for renting a car. It mutates both the fleet and
   * rental arrays.
   */
  public static void rentCar(String[][] fleet, String[][] rentals) {
    String regNum;
    String pickUpTime;
    String renterName;

    while (true) {

      // Query for user registration number:
      regNum = registrationNumberInput(1);

      // If user wants to quit from method input
      if (regNum.equals("q")) {
        return;
      }

      // Check if exists
      boolean carExists = isCarInFleet(fleet, regNum);
      if (carExists == false) {
        System.out.println("This registration number does not exist, try another one or or 'q' for quit");
        System.out.println("");
        continue;
      }

      // Check if car is available
      boolean carAvailable = isCarAvailable(fleet, regNum);
      if (carAvailable == false) {
        System.out.println("This car is not available, try renting another one or 'q' for quit");
        System.out.println("");
        continue;
      }
      // Gets the pickUpTime
      pickUpTime = pickUpAndReturnTimeTime(0);

      // Gets the renters name
      renterName = modelAndNameInput(1);

      // Makes the car unavailable
      for (int i = 0; i < fleet.length; i++) {
        if (fleet[i][0] != null) {
          if (fleet[i][0].equals(regNum)) {
            fleet[i][2] = "Rented";
          }
        }
      }

      // Populates the rental list
      for (int i = 0; i < rentals.length; i++) {
        if (rentals[i][0] == null) {
          rentals[i][0] = regNum;
          rentals[i][1] = pickUpTime;
          rentals[i][2] = renterName;

          // To remove the null from the array where it is populated on a row
          rentals[i][3] = " ";
          rentals[i][4] = " ";
          break;
        }
      }
      break;
    }
    System.out.println("");
    System.out.printf("The car with registration number %s was rented at %s by %s", regNum, pickUpTime, renterName);
    System.out.println("");

  }

  /**
   * THE PICK UP AND RETURN TIME METHOD
   * This method is used a validator for the pickup and return times provided by
   * the uer.
   */

  public static String pickUpAndReturnTimeTime(int choice) {
    String toReturn;

    while (true) {

      if (choice == 0) {

        System.out.print("> Enter the time of pickup: ");
      }

      if (choice == 1) {

        System.out.print("> Enter the time of return: ");
      }
      // Scanner delimiter
      userInput.useDelimiter(":|\\s");

      // Seperates the chosen time
      String firstIntString = userInput.next();
      String secondIntString = userInput.next();

      if (firstIntString.length() > 2 || secondIntString.length() > 2) {
        System.out.println("Please write the time im the format of [HH:MM]");
        System.out.println("");
      }

      // Checking that the hours and minutes are valid
      try {
        // Hours
        int hours = Integer.parseInt(firstIntString);
        if (hours > 23) {
          System.out.println(
              "There are only 24 hours available per day, 0 inclusive. Please write the time im the format of [HH:MM]");
          System.out.println("");

          // Clear buffer from scanner
          userInput.nextLine();
          continue;
        }
      } catch (NumberFormatException | java.lang.StringIndexOutOfBoundsException e) {
        System.out.println(
            "There are only 24 hours available per day, 0 inclusive. Please write the time im the format of [HH:MM]");

        continue;
      }
      // Minutes
      try {
        int minutes = Integer.parseInt(secondIntString);
        if (minutes > 59) {
          System.out.println(
              "There are only 60 minutes available per hours, 0 inclusive. Please write the time im the format of [HH:MM]");
          System.out.println("");

          // Clear buffer from scanner
          userInput.nextLine();
          continue;
        }
      } catch (NumberFormatException | java.lang.StringIndexOutOfBoundsException e) {
        System.out.println(
            "There are only 60 minutes available per hours, 0 inclusive. Please write the time im the format of [HH:MM]");
        System.out.println("");

        continue;

      }

      // Combines the strings after validation
      toReturn = firstIntString + ":" + secondIntString;
      break;

    }

    return toReturn;

  }

  /**
   * METHOD FOR MODEL INPUT
   * A simple method that takes input for models and name inputs with minimal
   * validation needed.
   */

  public static String modelAndNameInput(int choice) {

    String toReturn;
    if (choice == 0) {
      System.out.print("> Enter make and model: ");
    } else {
      System.out.print("> Enter the renter's name: ");
    }
    while (true) {

      // Get user choice
      String userChoice = userInput.nextLine();

      if (userChoice.length() != 0) {

        toReturn = userChoice;
        break;
      }

    }
    return toReturn;
  }

  /**
   * METHOD FOR REGISTRATION NUMBER INPUT
   * A method used for the validation of the registration number.
   */

  public static String registrationNumberInput(int choice) {

    String toReturn;

    while (true) {
      if (choice == 0) {
        System.out.print("> Enter registration number: ");
      }

      if (choice == 1) {
        System.out.print("> Enter the car's registration number: ");
      }

      // Get user choice
      String userChoice = userInput.next();

      if (userChoice.equals("q")) {
        toReturn = "q";
        return toReturn;
      }

      // Validation for length and structure.
      if (userChoice.length() > 6 || userChoice.length() < 6) {
        System.out.println(
            "Registration number cannot be longer than 6 characters and must start with 3 letters and end with 3 digits");
        System.out.println("");

        // To clear the scanner buffer in case someone entered car model rather than a
        // registry number. Otherwise spammed with multiple errors due to scanner buffer
        try {
          userInput.nextLine();
          continue;
        } catch (java.lang.StringIndexOutOfBoundsException e) {
          continue;
        }
      }

      // Boolean due to double breaking in a loop within a loop
      boolean continueLoop = false;

      // Letter validation
      for (int i = 0; i < 3; i++) {
        if (Character.isDigit(userChoice.charAt(i)) == true) {
          System.out.println(
              "Registration number cannot be longer than 6 characters and must start with 3 letters and with 3 digits.");
          System.out.println("");

          continueLoop = true;
          break;
        }
        ;
      }

      // To ensure loop runs again if letter validation fails
      if (continueLoop == true) {
        continue;
      }

      // Digit Validation
      for (int i = 3; i < 6; i++) {
        if (Character.isDigit(userChoice.charAt(i)) != true) {
          System.out.println(
              "Registration number cannot be longer than 6 characters and must start with 3 letters and with 3 digits.");
          System.out.println("");

          continueLoop = true;
          break;
        }
        ;
      }

      // To ensure loop runs again if letter validation fails
      if (continueLoop == true) {
        continue;
      }

      // Ensures it is always uppercase
      toReturn = (userChoice.toUpperCase());

      // Break while loop
      break;

    }
    return toReturn;

  }

  /**
   * METHOD FOR CHECKING IF CAR EXISTS IN FLEET
   * This method checks if the car is in the fleet
   */

  public static boolean isCarInFleet(String[][] fleet, String regNumber) {

    // Checks if the string is found in the fleet array, if so, returns true
    for (int i = 0; i < fleet.length; i++) {
      if (fleet[i][0] != null) {
        if (fleet[i][0].equals(regNumber)) {
          return true;
        }
      }
    }
    // Otherwise return false
    return false;
  }

  /**
   * METHOD FOR CHECKING IF CAR IS AVAILABLE
   * This method checks if the car is available in the fleet.
   */

  public static boolean isCarAvailable(String[][] fleet, String regNumber) {

    // Checks if the string is found in the fleet array, if so, returns true
    for (int i = 0; i < fleet.length; i++) {
      if (fleet[i][0] != null) {
        if (fleet[i][0].equals(regNumber)) {
          if (fleet[i][2].equals("Available")) {
            return true;
          }

        }
      }
    }
    // Otherwise return false
    return false;

  }

  /**
   * TIME VALIDATION METHOD
   * This method is used a time validation to not only count hours but to ensure
   * that a car is not returned before it was rented.
   */

  public static int timeValidation(String pickUpTime, String returnTime) {
    // Split the strings
    String[] pickUpTimeParts = pickUpTime.split(":");
    String[] returnTimeParts = returnTime.split(":");

    String pickUpHoursString = pickUpTimeParts[0];
    String pickUpMinutesString = pickUpTimeParts[1];

    String returnHoursString = returnTimeParts[0];
    String returnMinutesString = returnTimeParts[1];

    // Transforms it all to integers
    int pickUpHours = Integer.parseInt(pickUpHoursString);
    int pickUpMinutes = Integer.parseInt(pickUpMinutesString);
    int returnHours = Integer.parseInt(returnHoursString);
    int returnMinutes = Integer.parseInt(returnMinutesString);

    // If the return was overnight
    if (pickUpHours > returnHours) {
      return -1;
    }
    // Calculate the total hours for cost
    int finalHours = returnHours - pickUpHours;

    // Any minute over the hour limit, i.e an extra minute is +1 h
    if ((returnMinutes - pickUpMinutes) > 0) {
      finalHours += 1;

    }
    return finalHours;
  }

}