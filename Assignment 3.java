import java.util.Scanner;

/**
* The program is a dice game that prints out if you win, loss or neither and total statistics at the end when you quit. 
*
* 1. Create variables for the three dices
* 2. Create boolean variables if the dice have been thrown
* 3. Creates variables for wins, losses, sum and rounds and gameover
* 4. Creates a scanner input
* 5. Prints out of welcome message and rules
* 6. Initializes a while loop for the game
* 7. Prints out instructions
* 8. Validates the user input in ensuring correct values, no double inputs for the dice, etc. 
* 9. When validation is done, gives a random value between 1-6 to chosen dice
* 10. When all dice have been assigned a random value, prints out message with statistics and if the user has won or lost or neither. 
* 11. Repeat. If the game quits with > 0 rounds, it prints out wins and losses and game over, otherwise only Game over.
 *
 * @author Benjamin Albarzendji, benalb-2
 */

class Main {
  public static void main(String[] args) {

    // Placeholder values for the dice rolls
    int diceOne = 0;
    int diceTwo = 0;
    int diceThree = 0;

    // Control values for the dice roll
    boolean isDice1Rolled = false;
    boolean isDice2Rolled = false;
    boolean isDice3Rolled = false;

    // Placeholder values for wins and losses, sums and rounds
    int wins = 0;
    int losses = 0;
    int sum = 0;
    int rounds = 0;

    // Control value for when the game is over for the while loop
    boolean isGameOver = false;

    // Initialize the scanner
    Scanner userInput = new Scanner(System.in);

    System.out.println("Welcome to the dice game 12. You must roll 1-3 dice and try to get the sum of 12 ...\n");

    while (isGameOver == false) {

      // Print out the instructions
      System.out.print("Enter which dice you want to roll [1,2,3] (exit with q): ");

      // Get user input in the form of a String type
      String inputValue = userInput.next();

      // Validate the user input via the .equals method rather than == operator as we
      // are dealing with strings.
      if (!inputValue.equals("q") && !inputValue.equals("1") && !inputValue.equals("2") && !inputValue.equals("3")) {
        System.out.println("Sorry, that is an invalid entry. Try again. Valid entries are 1, 2, 3, and q\n");

        // Continues the loop to the next iteration
        continue;
      }

      // Breaks the loop if "q" is inserted
      if (inputValue.equals("q")) {
        // Ensures the while loop is set to not run again (while the break statement at
        // the bottom essentially does the same
        isGameOver = true;

        // Prints out wins and losses if more than 1 round else only "Game over!"
        if (rounds > 0) {
          System.out.printf("#win: %d #loss: %d %n", wins, losses);
          System.out.println("Game over!");
        } else {
          System.out.println("Game over!");
        }

        // Closes userInput for memory reasons as it is not going to be used again.
        userInput.close();

        // Shutsdown the while loop
        break;
      }

      // Ensures that no dice can be rolled more tha once
      if (inputValue.equals("1") && isDice1Rolled == true) {
        System.out.println("Sorry you have already rolled that dice. Try again \n");
        continue;
      }
      if (inputValue.equals("2") && isDice2Rolled == true) {
        System.out.println("Sorry you have already rolled that dice. Try again \n");
        continue;
      }
      if (inputValue.equals("3") && isDice3Rolled == true) {
        System.out.println("Sorry you have already rolled that dice. Try again \n");
        continue;
      }

      // Gets a random value for the dice crom 1-6
      int diceValue = (int) (Math.random() * 6 + 1);

      // Switch statement for clean code rather than multiple IFs
      switch (inputValue) {
        case "1":
          diceOne = diceValue;
          isDice1Rolled = true;
          break;
        case "2":
          diceTwo = diceValue;
          isDice2Rolled = true;
          break;
        case "3":
          diceThree = diceValue;
          isDice3Rolled = true;
          break;
      }
      // Adds the diceValue to the sum
      sum += diceValue;

      // A sum counter to increment the win before publishing to ensure correct
      // display of values
      if (isDice1Rolled == true && isDice2Rolled == true && isDice3Rolled == true && sum == 12) {
        wins++;
      }
      if (isDice1Rolled == true && isDice2Rolled == true && isDice3Rolled == true && sum > 12) {
        losses++;
      }

      System.out.printf("%d, %d, %d sum: %d #win: %d #loss: %d %n", diceOne, diceTwo, diceThree, sum, wins, losses);

      // Checks if all dices have been rolled
      if (isDice1Rolled == true && isDice2Rolled == true && isDice3Rolled == true) {

        if (sum < 12) {
          System.out.println("You neither won nor lost the game \n");
          
        }

        if (sum == 12) {
          System.out.println("You won!! \n");
          
        }

        if (sum > 12) {
          System.out.println("You lost!! \n");
          
        }
        // Prints out next round message
        System.out.println("Next round!\n");


        // Restores values for next round
        diceOne = 0;
        diceTwo = 0;
        diceThree = 0;

        isDice1Rolled = false;
        isDice2Rolled = false;
        isDice3Rolled = false;

        sum = 0;

        // Increments rounds counter
        rounds++;
      }

    }

  }
}