/**
 * cs1410
 * Driving Cost Calculator
 * @author jamesgibb
 *
 */
package a1;
public class DrivingCostCalculator {

	/**
	 * In this method we calculate the cost of the trip using gas. Takes three different double parameters : milesToDrive, milesPerGallon, and dollarsPerGallon.
	 * @param milesToDrive - this parameter is used to store the miles driven.
	 * @param milesPerGallon - stores the miles per gallon used of Gas on the trip.
	 * @param dollarsPerGallon - the dollar amount used per gallon used. 
	 * @returns costOfTripGas - a double that shows how much the trip cost in dollars for Gas.
	 */
	public static double calculateGasTripCost(double milesToDrive, double milesPerGallon, double dollarsPerGallon) {
		double costOfTripGas = 0.0;
		double numberOfGallonsUsed = 0.0;
		numberOfGallonsUsed = milesToDrive / milesPerGallon;
		costOfTripGas = numberOfGallonsUsed * dollarsPerGallon;
		return costOfTripGas;
	}

	/**
	 * @param milesToDrive - this parameter is used to store the miles driven.
	 * @param milesPerKWh - stores the miles per killowatt used on the trip each hour.
	 * @param dollarsPerKWh - dollar amount used per killowat used
	 * @return costOfTripElectric- a double that shows how much the trip cost in dollars Gas.
	 */
	public static double calculateElectricTripCost(double milesToDrive, double milesPerKWh, double dollarsPerKWh) {
		double numberOfKWUsed = 0.0;
		double costOfTripElectric = 0.0;
		numberOfKWUsed = milesToDrive / milesPerKWh;
		costOfTripElectric = numberOfKWUsed * dollarsPerKWh;
		return costOfTripElectric;
	}

	/**
	 * The main method stores a number of variable constants that help calculate the cost of the trips. It also 
	 * formats the output. 
	 * @param args
	 */
	public static void main(String[] args) {
		double milesDriven = 100;
		double mpg = 28.0;
		double costOfGas = 2.75;
		double electronicEfficiencyPerHour = 4.0;
		double costOfElectricity = 10.6;
		String formattedNumElectric = String.format("%.2f",
				calculateElectricTripCost(milesDriven, electronicEfficiencyPerHour, costOfElectricity));
		String formattedNumGas = String.format("%.2f", calculateGasTripCost(milesDriven, mpg, costOfGas));
		displayBanner();
		System.out.println("The cost of a " + milesDriven + "mile trip by gas is " + formattedNumGas
				+ " and by electric is " + formattedNumElectric);
	}

	/**
	 * Formats and displays the banner used at the beginning of the cost calculator to improve the appearance 
	 */
	public static void displayBanner() {
		System.out.println("+-------------------------+");
		System.out.println("|                         |");
		System.out.println("| Driving Cost Calculator |");
		System.out.println("| *********************** |");
		System.out.println("+-------------------------+");
	}

}
