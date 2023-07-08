import java.util.Scanner; // Import the Scanner class

/**
* The program is a dice game that prints out if you win, loss or neither and total statistics at the end when you quit. 
*
* 1. Declare constants
* 2. Initializes arrays and ARRAY_SIZE placeholder
* 3. Initializes the selectedInt variable
* 4. Initializes the odd/even variables
* 5. Prints out of welcome message and rules
* 6. Opens a scanner object for input
* 7. Initializes a while loop with validation for input, including try/catch for memory restrictions. If all passes, the arrays are created in memory
* 8. If the input is fine all the way the while loop breaks, input is closed 
* 9. Populates random array with random numbers
* 10. Populates the arranged array with arranged numbers
* 11. Prints it all out
 *
 * @author Benjamin Albarzendji, benalb-2
 */

class Main {
  public static void main(String[] args) {

    // Declare constants
    final int MAX_LENGTH = Integer.MAX_VALUE;
    final int MINIMUM_LENGTH = 0;
    final int MAX_NUMBER = 1000;
    final int MIN_NUMBER = 0;

    // Initializes the array variables and array size
    int[] randomArray = null;
    int[] arrangedArray = null;
    int ARRAY_SIZE = 0;

    // Initializes the selectedInt variable
    int selectedInt = 0;

    // Initializes odd/even variables
    int odd = 0;
    int even = 0;

    // Opens a Scanner object for input
    Scanner input = new Scanner(System.in);

    // While loop conntrol variable
    boolean loopExit = false;

    // While Loop to check for correct input
    while (loopExit == false) {

      // Print out the input query
      System.out.printf("How many random numbers in the range 0 - 999 are desired? ");

      // Query for integer with integer input validation check
      if (input.hasNextInt()) {
        selectedInt = input.nextInt();
      } else {
        System.out.printf("\nThe number must be between 0 - %d, inclusive. Please note while that is the maximum amount of memory, in reality you cannot choose such a high number.\n\n", MAX_LENGTH);
        input.next();
        continue;
      }

      // Numeric Validation check
      if (selectedInt > MAX_LENGTH || MINIMUM_LENGTH > selectedInt) {
        System.out.printf("The number must be between 0 - %d, inclusive. Please note while that is the maximum amount of memory, in reality you cannot choose such a high number.\n\n", MAX_LENGTH);
        continue;
      }

      // Giving the selectedInt value to ARRAY_SIZE
      ARRAY_SIZE = selectedInt;

      // Creates the array size, with a try catch in case of memory error
      try {
        randomArray = new int[ARRAY_SIZE];
        arrangedArray = new int[ARRAY_SIZE];

      } catch (OutOfMemoryError e) {

        // If the array is too big print error
        System.out.println(e);
        System.out.println("There is not enough memory to create the arrays of this size. Please try a lower number.\n");
        System.out.printf("\nThe number must be between 0 - %d, inclusive. Please note while that is the maximum amount of memory, in reality you cannot choose such a high number.\n\n", MAX_LENGTH);
        continue;

      }
      // If validation is fine, loop breaks via boolean change
      loopExit = true;

      
    }

      // Close input to avoid memory leak
      input.close();
    
    // Populates the array with random numbers
    for (
        int counter = 0; counter < ARRAY_SIZE; counter++) {
      randomArray[counter] = (int) (Math.random() * MAX_NUMBER + MIN_NUMBER);
    }

    // Arranged array populating
    for (int counter = 0; counter < randomArray.length; counter++) {
      arrangedArray[counter] = (randomArray[counter]);
    }

    // Arranged Array sorting
    // Bubble Sort
    for (int i = 0; i < arrangedArray.length; i++) {
      // Iterate through the subarray and compare adjacent elements
      for (int j = 0; j < arrangedArray.length - 1; j++) {
        // Swap elements if left element is greater than right element
        if (arrangedArray[j] > arrangedArray[j + 1]) {
          int temp = arrangedArray[j];
          arrangedArray[j] = arrangedArray[j + 1];
          arrangedArray[j + 1] = temp;
        }
      }
    }

    // Prints out the numbers

    // Random Numbers
    System.out.println("\nHere are the random numbers:");
    // Calculates the odd / even occurences
    for (int x : randomArray) {
      int tempTernary = ((x % 2) == 0) ? even++ : odd++;
      System.out.printf("%d ", x);
    }
    // New Line
    System.out.println("\n");

    // Arranged Numbers
    System.out.println("Here are the random numbers arranged");

    // Even numbers check & print
    if (even == 0) {
      System.out.print("No Even numbers ");
    } else {
      for (int x : arrangedArray) {
        if (x % 2 == 0) {
          System.out.printf("%d ", x);
        }

      }
    }

    // Prints the "-""
    System.out.print("- ");

    // Odd numbers check & print
    if (odd == 0) {
      System.out.print("No Odd numbers ");
    } else {
      // Descending print of odd numbers
      for (int x = arrangedArray.length - 1; x >= 0; x--) {
        if (arrangedArray[x] % 2 == 1) {
          System.out.printf("%d ", arrangedArray[x]);
        }

      }
    }

    // New Line
    System.out.println("\n");

    // Prints of the total, even and odd numbers
    System.out.printf("Of the above %d numbers, %d were even and %d odd%n", ARRAY_SIZE, even, odd);

  }
}