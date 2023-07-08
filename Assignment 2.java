import java.util.Scanner;

/**
* The program calculates production and value produced by solar panels in the months of June and July.
*
* 1. Create constants
* 2. Create variables
* 3. Ask user for month & date
* 4. Ask user for sunrise hours and minutes
* 5. Ask user for sunset hours and minutes
* 6. Calculates the combined hours between sunrise and sunset
* 7. Calculates production of electricity
* 8. Calculates value of electricity
* 9. Printing it all out
 *
 * @author Benjamin Albarzendji, benalb-2
 */

class Main {
  public static void main(String[] args) {
    // Step 1: Declaring Constants as specified in the assignment

    // The solar cell efficiency
    final float EFFICIENCY = 0.2f;

    // The electricity price
    final float ELECTRICITY_PRICE = 0.9f;

    // Number of panels
    final int NUM_OF_PANELS = 26;

    // Panel Height (in meters)
    final int PANEL_HEIGHT = 1;

    // Panel Width (in meters)
    final float PANEL_WIDTH = 1.7f;

    // Panel Area (in meters)
    final float PANEL_AREA = PANEL_HEIGHT * PANEL_WIDTH;

    // Solar Radiation (in m^2)
    final int SOLAR_RADIATION = 166;

    // Maximum days in June
    final int DAYS_IN_JUNE = 30;
    final int JUNE = 6;

    // Maximum days in July
    final int DAYS_IN_JULY = 31;
    final int JULY = 7;

    // Step 2: Declaring variables to be used later

    // Hours
    double hourSunrise;
    double hourSunset;

    // Minutes
    double minuteSunrise;
    double minuteSunset;

    // Month
    int month;

    // Day
    int day;

    // Step 3: Ask user for month and date
    
    // Initializing the Scanner object
    Scanner userInput = new Scanner(System.in);

    // Setting the delimiter
    userInput.useDelimiter("-|:|\\s");

    // Prompting user for the date
    System.out.print("Enter today's date [mm-dd]> ");
    month = userInput.nextInt();
    day = userInput.nextInt();

    // Validation for Month & Day
    if (month == JUNE || month == JULY) {

      if (month == JUNE) {
        if (day < 1 || day > DAYS_IN_JUNE) {
          System.out.println("The specified day is not in June, 1-30 allowed.");
          System.exit(0);
        }
      }

      if (month == JULY) {
        if (day < 1 || day > DAYS_IN_JULY) {
          System.out.println("The specified day is not in July, 1-31 allowed.");
          System.exit(0);
        }
      }
    }

    else {
      System.out.println("Wrong Month! Only June & July are allowed");
      System.exit(0);
    }

    // Step 4: Prompting user for the sunrise
    System.out.print("Enter the time of sunrise [hh: mm]> ");
    hourSunrise = userInput.nextInt();
    minuteSunrise = userInput.nextInt();

    // Validation of the hours and minutes of the sunrise
    if (hourSunrise < 0 || hourSunrise > 23) {
      System.out.print("Only hours between 0-23 allowed");
      System.exit(0);
    }

    if (minuteSunrise < 0 || minuteSunrise > 59) {
      System.out.print("Only minutes between 0-59 allowed");
      System.exit(0);
    }

    // Step 5: Prompting user for the sunset
    System.out.print("Enter the time of sunset [hh: mm]> ");
    hourSunset = userInput.nextInt();
    minuteSunset = userInput.nextInt();

    // Validation of the hours and minutes of the sunset
    if (hourSunset < 0 || hourSunset > 23) {
      System.out.print("Only hours between 0-23 allowed");
      System.exit(0);
    }

    if (minuteSunset < 0 || minuteSunset > 59) {
      System.out.print("Only minutes between 0-59 allowed");
      System.exit(0);
    }

    // Step 6: Calculate the combined hours for both sunrise and sunset
    double sunriseFinalHours;
    double sunsetFinalHours;

    sunriseFinalHours = hourSunrise + (minuteSunrise / 60);
    sunsetFinalHours = hourSunset + (minuteSunset / 60);

    // Validating that sunrise is not greater than sunset.
    if (sunriseFinalHours > sunsetFinalHours) {
      System.out.print("Sunrise is after sunset");
    }

    // Setting the final hours
    double finalHours;
    finalHours = sunsetFinalHours - sunriseFinalHours;

    // Step 7: Calcualte production, eq 1 above
    double production;
    production = SOLAR_RADIATION * EFFICIENCY * PANEL_AREA * finalHours * NUM_OF_PANELS / 1000;

    // Step 8: Calculate money
    double finalPrice;
    finalPrice = production * ELECTRICITY_PRICE;

    // Step 9 Printing it out all:
    // Printing line
    System.out.println("==========================================");

    // Printing Sun Hours
    System.out.printf("Sun hours: %.2f hours %n", finalHours);

    // Printing production and value
    System.out.printf("The production on %d/%d is: %.2f kWh to a value of: SEK %.2f %n", day, month, production, finalPrice);
  }
}
