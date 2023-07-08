import java.util.Scanner; // Import the Scanner class

/**
 * The program is used to illustrate several mathemathical methods. 
 *
 *
 * 1. Declares a global userInput scanner with a delimiter.
 * 2. Prints out the banner/header for the area and volume methods
 * 3. Prompts user for input
 * 4. Loops through the user inputs and prints out the results
 * 5. Prints out the banner/header for the fractional methods
 * 6. Loops through the user inputs and prints out the results
 * @author Benjamin Albarzendji, benalb-2
 */

public class Main {
  // Declares the userInput variable for the entire class
  private static Scanner userInput = new Scanner(System.in);

  public static void main(String[] args) {

    // Sets the delimiter for the userInput Scanner
    userInput.useDelimiter("\\s");

    // Prints out the area and volume methods banner
    System.out.println("----------------------------------");
    System.out.println("# Test of area and volume methods");
    System.out.println("----------------------------------");
    System.out.print("> ");

    // Assigns the first value to radius due to Scanner limitations
    String radius = userInput.next();

    // Loop sentinel value for the area methods
    boolean areaLoop = true;

    // The while loop for the area methods
    while (areaLoop == true) {

      // Sends the radius value to the radiusInputChecker
      int radiusInputChecker = input(radius);

      // Breaks if the radiusInputChecker is "-1"
      if (radiusInputChecker == -1) {
        break;
      }

      // Tries to get the next integer if radiusInputChecker is "-2", i.e a character
      if (radiusInputChecker == -2) {
        if (userInput.hasNext()) {
          radius = userInput.next();
          System.out.println("Restarting the loop");
          continue;
        }

      } // Sets the value of the integer for radius to be calculated later
      int radiusForCalculation = radiusInputChecker;

      // Sets the height variable to later be checked
      String height = userInput.next();

      // Sends the height value to the heightInputChecker
      int heightInputChecker = input(height);

      // Breaks if the heightInputChecker is "-1"
      if (heightInputChecker == -1) {
        break;
      }

      // Tries to get the next integer if heightInputChecker is "-2", i.e a character
      if (heightInputChecker == -2) {
        if (userInput.hasNext()) {
          height = userInput.next();
          System.out.println("Restarting the loop");
          continue;
        }

      } // Sets the value of the integer for height to be calculated later
      int heightForCalculation = heightInputChecker;

      // Prints out the radius and height value
      System.out.printf("r = %d h = %d%n", radiusForCalculation, heightForCalculation);

      // Prints out the Circle Area
      System.out.printf("Circle Area: %.2f%n", area(radiusForCalculation));

      // Prints out the Cone Area
      System.out.printf("Cone Area: %.2f%n", area(radiusForCalculation, heightForCalculation));

      // Prints out the Cone Volume
      System.out.printf("Cone Volume: %.2f%n%n", volume(radiusForCalculation, heightForCalculation));

      // Prepares the radius for the next loop
      if (userInput.hasNext() == true) {
        radius = userInput.next();

      } else {
        break;
      }

    }

    // Prints the banner for the fractional methods
    System.out.println("----------------------------------");
    System.out.println("# Test of the fractional methods");
    System.out.println("----------------------------------");
    System.out.print("> ");

    // Prompts for the fraction values while storing the first one in the numerator
    String numerator = userInput.next();

    // Loop sentinel value for the area methods
    boolean fractionLoop = true;

    // The while loop for the area methods
    while (fractionLoop == true) {

      // Sends the radius value to the numeratorInputChecker
      int numeratorInputChecker = input(numerator);

      // Breaks if the numeratorInputChecker is "-1"
      if (numeratorInputChecker == -1) {
        break;
      }

      // Tries to get the next integer if numeratorInputChecker is "-2", i.e a
      // character
      if (numeratorInputChecker == -2) {
        if (userInput.hasNext()) {
          numerator = userInput.next();
          System.out.println("Restarting the loop");
          continue;
        }
      }

      // Sets the value of the integer for radius to be calculated later
      int numeratorForCalculation = numeratorInputChecker;

      // Sets the denominator variable to later be checked
      String denominator = userInput.next();

      // Sends the denominator value to the denominatorInputChecker
      int denominatorInputChecker = input(denominator);

      // Breaks if the denominatorInputChecker is "-1"
      if (denominatorInputChecker == -1) {
        break;
      }

      // Tries to get the next integer if denominatorInputChecker is "-2", i.e a
      // character
      if (denominatorInputChecker == -2) {
        if (userInput.hasNext()) {
          denominator = userInput.next();
          System.out.println("Restarting the loop");
          continue;
        }

      } // Sets the value of the integer for denominator to be calculated later
      int denominatorForCalculation = denominatorInputChecker;

      int[] arrayToPrint = fraction(numeratorForCalculation, denominatorForCalculation);

      // Prints out the fraction
      System.out.printf("%d/%d = ", numeratorForCalculation, denominatorForCalculation);

      // Calls the printFraction method
      printFraction(arrayToPrint);

      // Prepares the numerator for the next loop
      if (userInput.hasNext() == true) {
        numerator = userInput.next();

      } else {
        break;
      }

    }
  }

  // The input checker method
  public static int input(String input) {

    // Returns a -1 if its "q"
    if (input.equals("q")) {
      return -1;
    }

    // Try catch to parse the input to a double. If fail return a "-2" integer
    try {
      // Parsing to double first
      double inputToDouble = Double.parseDouble(input);

      // Casting double to integer
      int inputToInteger = (int) inputToDouble;

      // Returning the absolute value of the integer
      return Math.abs(inputToInteger);

    } catch (NumberFormatException e) {
      return -2;
    }
  }

  // Circle Area method
  public static double area(int radius) {

    return (Math.PI * Math.pow(radius, 2));
  }

  // Cone area method
  public static double area(int radius, int height) {

    return (Math.PI * radius * pythagoras(radius, height));

  }

  // Pythagoras method
  public static double pythagoras(int sideA, int sideB) {

    return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));

  }

  // Cone Volume method
  public static double volume(int radius, int height) {
    return ((Math.PI * Math.pow(radius, 2) * height) / 3);
  }

  // Fraction method
  public static int[] fraction(int numerator, int denominator) {

    // Returns null if denominator is 0
    if (denominator == 0) {
      return new int[] { -1, -1, -1 };
    }

    // Returns a {0,0,0} list if the numerator is 0
    if (numerator == 0) {
      return new int[] { 0, 0, 0 };
    }

    // Creates the array of size 3
    int[] array = new int[3];

    // Calculates first array spot
    array[0] = numerator / denominator;

    // Returns a list with the original numerator and denominator if there is not a
    // whole integer from the initial division
    if (array[0] == 0) {
      return new int[] { 0, numerator, denominator };
    }

    // Calculates the remainder for the second array spot
    array[1] = numerator % denominator;
    
    // Calculates the remainder for the third array spot
    array[2] = denominator;

// Establishing the GCD
    int gcd = gcd(array[1], array[2]);
    System.out.println("This is the GCD:");
    System.out.println(gcd);
    
    // Reducing array[1] and array[2]
    array[1] = array[1] / gcd;

    // Makes sure the denominator is 0 if the numerator is 0
    if (array[1] == 0) {
      array[2] = 0;
      return array;
    }
    
    array[2] = array[2] / gcd;
    

    // Returns the array
    return array;

  }

  // GCD Method
  public static int gcd(int a, int b) {
    // Making sure a is larger than b, otherwise switch places
    if (b == 0) {
        return a;
    } else {
        return gcd(b, a % b);
    }
  }

  // Prints the fractions
  public static void printFraction(int[] parts) {

    // If the integer is 0
    if (parts[0] == 0) {
      System.out.printf("%d/%d%n", parts[1], parts[2]);

    }

    // If the division is null "Error"
    else if (parts[0] == -1 && parts[1] == -1 && parts[2] == -1) {
      System.out.print("\"Error\"\n");
    }

    // If it's just a whole integer
    else if (parts[1] == 0) {
      System.out.print(parts[0]);
      System.out.print("\n");
    }

    // A normal fraction
    else {
      System.out.printf("%d %d/%d %n", parts[0], parts[1], parts[2]);
    }

  }
}
