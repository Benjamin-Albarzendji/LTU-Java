import java.util.Scanner;
import java.util.Date;

/**
 * The program simulates a Cash Register
 *
 *
 * 1. Declares a global userInput scanner with a delimiter.
 * 2. Declares constants and variables
 * 3. Creates the arrays
 * 4. While loop that prompts the menu with choices
 * 5. Methods for menu, input, checkFull, insertArticles, removeArticle,
 * printArticles, sellArticle, printSales and sortedTable.
 * 
 * @author Benjamin Albarzendji, benalb-2
 */

public class Main {
  // Global scanner object in the Main Class
  private static Scanner userInput = new Scanner(System.in);

  // Main function
  public static void main(String[] args) {

    // CONSTANTS FOR THE INITIAL ARRAY
    final int INITIAL_ARRAY = 10;
    final int INDEX_COUNT = 3;

    // Variable for the article number and article count
    int articleNumber = 1000;
    int noOfArticles = 0;

    // Constant for max sales
    final int MAX_SALES = 1000;

    // Initial array.
    // Index 0 - Article Number
    // Index 1 - Quantity
    // Index 2 - Price
    int articles[][] = new int[INITIAL_ARRAY][INDEX_COUNT];

    // Date Array
    Date[] salesDate = new Date[MAX_SALES];

    // Sales Array
    int sales[][] = new int[MAX_SALES][INDEX_COUNT];

    // Boolean to ensure program runs continously
    boolean quitProgram = false;

    while (quitProgram == false) {

      // Prompts the menu for the user
      int userChoice = menu();

      // If user quits
      if (userChoice == -1) {
        quitProgram = true;
        return;
      }

      // Choice to Insert article
      if (userChoice == 1) {
        System.out.print("How many articles?: ");
        noOfArticles = input();
        articles = insertArticles(articles, articleNumber, noOfArticles);
        articleNumber = articleNumber + noOfArticles;
      }
      // Choice to remove article
      if (userChoice == 2) {
        removeArticle(articles);
      }

      // Print articles
      if (userChoice == 3) {
        printArticles(articles);
      }
      // Register A sale
      if (userChoice == 4) {
        sellArticle(sales, salesDate, articles);
      }
      // Print Sales
      if (userChoice == 5) {
        printSales(sales, salesDate);
      }
    }
  }

  // The Menu method
  public static int menu() {

    // Variables for userInput
    int userChoice = 0;
    int inputToInteger = 0;

    while (true) {
      // Prints out the menu options
      System.out.println("1. Insert articles");
      System.out.println("2. Remove an article");
      System.out.println("3. Display a list of articles");
      System.out.println("4. Register a sale");
      System.out.println("5. Display order history");
      System.out.println("6. Sort and display order history table");
      System.out.println("q. Quit");
      System.out.print("Ditt val: ");

      // Gets the user choice via the input method
      String input = userInput.next();

      // Returns -1 for quitting
      if (input.equals("q")) {
        return -1;
      }

      // Try catch block to convert the string to an integer
      try {
        inputToInteger = Integer.parseInt(input);
      }

      catch (NumberFormatException e) {
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

  public static int input() {

    // Declaring the inputToInteger variable
    int inputToInteger = 0;

    // Boolean to ensure while loop functions
    boolean whileLoopQuit = false;

    // While loop to make sure input is correct and repeats
    while (whileLoopQuit == false) {

      // Queries for amount of input

      // Queries the user for input
      String input = userInput.next();

      // Try catch block to convert the string to an integer
      try {
        inputToInteger = Integer.parseInt(input);
      }

      catch (NumberFormatException e) {
        System.out.println("Do not use letters, only numbers.");
        System.out.println("");
        continue;
      }

      // Quits the while Loop
      whileLoopQuit = true;
    }
    return inputToInteger;
  }

  public static int[][] checkFull(int[][] articles, int noOfArticles) {

    int counter = 0;
    // Checks if article is full, else returns old array
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == 0) {
        counter += 1;
      }
    }
    if (counter >= noOfArticles) {
      return articles;
    }

    // Creates a new Array with a bigger size
    int newArray[][] = new int[articles.length + (noOfArticles - counter)][3];

    // Populates the array
    for (int i = 0; i < articles.length; i++) {

      newArray[i][0] = articles[i][0];
      newArray[i][1] = articles[i][1];
      newArray[i][2] = articles[i][2];
    }

    // Returns new array
    return newArray;

  }

  public static int[][] insertArticles(int[][] articles, int articleNumber, int noOfArticles) {

    // Check if array is full first
    int arrayChecked[][] = checkFull(articles, noOfArticles);

    // Adds on the first empty position
    for (int i = 0; i < arrayChecked.length; i++) {
      if (arrayChecked[i][0] == 0) {
        for (int j = i; j < i + noOfArticles; j++) {
          if (arrayChecked[j][0] != 0) {
            noOfArticles -= 1;
            break;
          }
          arrayChecked[j][0] = articleNumber;
          arrayChecked[j][1] = (int) (Math.random() * 10 + 1);
          arrayChecked[j][2] = (int) (Math.random() * (1000 - 100)) + 100;
          articleNumber += 1;
        }
        break;
      }
    }
    System.out.println("");
    return arrayChecked;
  }

  public static void removeArticle(int[][] articles) {
    // Queries user for article number
    System.out.print("What article number?: ");
    int userInput = input();

    boolean sortArray = false;

    // Checks if article exists and removes it
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == userInput) {
        articles[i][0] = 0;
        articles[i][1] = 0;
        articles[i][2] = 0;
        System.out.println("Article removed!");
        System.out.println("");
        return;
      }
    }
    // If article not found
    System.out.println("Article number not found");
    System.out.println("");
  }

  public static void printArticles(int[][] articles) {

    // Array sort in ascending order
    int rows = articles.length;
    int columns = articles[0].length;
    int[] temp;

    for (int i = 0; i < rows; i++) {
      for (int j = i + 1; j < rows; j++) {
        if (articles[i][0] > articles[j][0]) {
          temp = articles[i];
          articles[i] = articles[j];
          articles[j] = temp;
        }
      }
    }

    // Prints out the articles
    for (int i = 0; i < articles.length; i++) {
      if (articles[i][0] == 0) {
        continue;
      }
      System.out.printf("Article Number: %d Quantity: %d Price: %d%n", articles[i][0], articles[i][1], articles[i][2]);

    }
    System.out.println("");
  }

  public static void sellArticle(int[][] sales, Date[] salesDate, int[][] articles) {
    // Declaring variables
    int userChoiceArticleNumber = 0;
    int indexPosition = 0;
    int userChoiceQuantity = 0;

    // Prompts user queries for article loop
    boolean articleLoop = true;

    while (articleLoop == true) {
      System.out.print("What article number would you like to sell?: ");
      userChoiceArticleNumber = input();
      for (int i = 0; i < articles.length; i++) {
        if (articles[i][0] == userChoiceArticleNumber) {
          indexPosition = i;
          articleLoop = false;

        }
      }
      if (articleLoop == false) {
        System.out.println("");
        break;
      }
      System.out.println("Article number not found!");
    }

    boolean quantityLoop = true;

    // User query while loop
    while (quantityLoop == true) {
      // Prompts user query for quantity
      System.out.println("How many would you like to sell? ");
      userChoiceQuantity = input();

      // Check if quantity is enough
      if (userChoiceQuantity <= articles[indexPosition][1]) {
        quantityLoop = false;
        break;
        // If quantity too high
      } else {
        System.out.println("Quantity is too high");
        System.out.println("");
      }
    }

    // Sales array population

    // Article number
    sales[indexPosition][0] = userChoiceArticleNumber;
    // Quantity
    sales[indexPosition][1] = sales[indexPosition][1] + userChoiceQuantity;
    // Sales Price
    sales[indexPosition][2] = articles[indexPosition][2];

    // Articles array quantity reduction
    articles[indexPosition][1] = articles[indexPosition][1] - userChoiceQuantity;

    // salesDates array
    Date currentDate = new Date();
    salesDate[indexPosition] = currentDate;

    // Removes residual listing
    if (articles[indexPosition][1] == 0) {
      articles[indexPosition][0] = 0;
      articles[indexPosition][2] = 0;
    }

    System.out.println("");
  }

  public static void printSales(int[][] sales, Date[] salesDate) {

    // Prints out the articles
    for (int i = 0; i < sales.length; i++) {
      if (sales[i][0] == 0) {
        continue;
      }
      System.out.printf("Date: %tD Article Number: %d Quantity: %d Total: %d%n", salesDate[i], sales[i][0],
          sales[i][1], sales[i][2] * sales[i][1]);

    }
    System.out.println("");
  }

  public static void sortedTable(int[][] sales, Date[] salesDate) {

    // Prints out the articles
    for (int i = 0; i < sales.length; i++) {
      if (sales[i][0] == 0) {
        continue;
      }
      System.out.printf("Date: %tD Article Number: %d Quantity: %d Total: %d%n",
          salesDate[i], sales[i][0],
          sales[i][1], sales[i][2] * sales[i][1]);

    }

    System.out.println("");
  }

}
