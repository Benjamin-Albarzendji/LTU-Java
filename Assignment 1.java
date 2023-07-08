/**
* The program prints out a table of values relating to Current, Voltage, Charging Power and Charging Time. 
* 0. A decision was made to use "double" variable types for a higher floating point precision and to avoid converting errors and transformation of types. While this method requires more bytes, modern computers have plenty of bytes to spares and thus it should have no significant impact on processing.
* 1. Initializes the constant variables with provided values.
* 2. Calculates the Charging Power of the two different electric cars with all the relevant voltages and currents. Stores the values in variables of type "double". The variables were stored as constants as they would be changed later on.
* 3. Same reasoning as above, only difference is now that the Charging Time is calculated and stored in variables of type "Double".
* 4. A succession of formatted prints. The first one prints out the Battery capacity. The second one prints the table headers with appropiate spacing. The remaining formatted prints are five that account for every scenario of Current, Voltage, Charging Power and Charging Time. 
 *
 * @author Benjamin Albarzendji, benalb-2
*/

class Main {
    public static void main(String[] args) {
      // Initializing constant variables of type double with information provided from assignment.
       final double BATTERY_CAPACITY = 35.8d;
       final double VOLTS_230 = 230.0d;
       final double VOLTS_400 = 400.0d;
       final double CURRENT_10 = 0.01d;
       final double CURRENT_16 = 0.016d;
       final double CURRENT_32 = 0.032d;

      // Calculates the Charging Power for five different scenarios and stores them in variables of type "double"
      double chargingPower_230V_10 = (VOLTS_230 * CURRENT_10);
      double chargingPower_230V_16 = (VOLTS_230 * CURRENT_16);
      double chargingPower_400V_10 = (VOLTS_400 * CURRENT_10 * Math.pow(3,(0.5)));
      double chargingPower_400V_16 = (VOLTS_400 * CURRENT_16 * Math.pow(3,(0.5)));
      double chargingPower_400V_32 = (VOLTS_400 * CURRENT_32 * Math.pow(3,(0.5)));

    // Calculates the Charging Time for five different scenarios and stores them in variables of type "double"
      double chargingTime_230V_10=((BATTERY_CAPACITY) / (VOLTS_230 * CURRENT_10));
      double chargingTime_230V_16=((BATTERY_CAPACITY) / (VOLTS_230 * CURRENT_16));
      double chargingTime_400V_10=((BATTERY_CAPACITY) / (VOLTS_400 * CURRENT_10 *                   Math.pow(3,(0.5))));
      double chargingTime_400V_16=((BATTERY_CAPACITY) / (VOLTS_400 * CURRENT_16 *                   Math.pow(3,(0.5))));
      double chargingTime_400V_32=((BATTERY_CAPACITY) / (VOLTS_400 * CURRENT_32 *                   Math.pow(3,(0.5))));


//Prints out the battery capacity
      System.out.printf("Battery: %.1f (kwh)%n", BATTERY_CAPACITY);

//Prints out the table headers
      System.out.printf("%-12s %-12s %-12s %-12s%n", "Current(A)", "Voltage(V)",                   "Charging Power(kW)", "Charging Time (h)");

//Prints out scenario 1
      System.out.printf("%.1f %17.1f %15.1f %12.2f%n", CURRENT_10*1000, VOLTS_230,                  chargingPower_230V_10, chargingTime_230V_10);

//Prints out scenario 2
      System.out.printf("%.1f %17.1f %16.2f %10.2f%n", CURRENT_16*1000, VOLTS_230,                  chargingPower_230V_16, chargingTime_230V_16);

    //Prints out scenario 3
      System.out.printf("%.1f %17.1f %16.2f %10.2f%n", CURRENT_10*1000, VOLTS_400,                  chargingPower_400V_10, chargingTime_400V_10);

    //Prints out scenario 4
      System.out.printf("%.1f %17.1f %17.2f %9.2f%n", CURRENT_16*1000, VOLTS_400,                   chargingPower_400V_16, chargingTime_400V_16);
//Prints out scenario 5
      System.out.printf("%.1f %17.1f %17.2f %9.2f%n", CURRENT_32*1000, VOLTS_400,                   chargingPower_400V_32, chargingTime_400V_32);
    }
  }