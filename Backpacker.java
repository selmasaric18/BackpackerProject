package hw1;

/**
 * Models calculations for a student traveling around Europe with a limited
 * amount of money
 * 
 * @author Selma Saric
 */

public class Backpacker {

	/**
	 * Proportionality constant when calling home for more money: the amount of
	 * money received is this constant times the number of postcards the backpacker
	 * has sent home (since the last time she called home for money).
	 */
	public static final int SYMPATHY_FACTOR = 30;

	/**
	 * Instance variable holding the amount of money the backpacker currently has.
	 */
	private int currentFunds;
	/**
	 * Instance variable holding the current location the backpacker is staying at.
	 */
	private Location currentLocation;
	/**
	 * Instance variable holding the number of nights the backpacker stays in one
	 * location.
	 */
	private int actualNights;
	/**
	 * Instance variable holding the list of cities that the backpacker travels to.
	 */
	private String listOfCities;
	/**
	 * Instance variable holding the amount of nights the backpacker can stay in a
	 * certain location based off of her funds.
	 */
	private int availableNights;
	/**
	 * Instance variable holding the amount of postcards the backpacker has sent to
	 * her parents.
	 */
	private int postcardCounter;
	/**
	 * Instance variable holding the amount of nights the backpacker has spent in
	 * the train station.
	 */
	private int nightsInTrainStation;

	/**
	 * Constructs a backpacker starting out with the given amount of money in the
	 * given location. The journal is initially a string consisting of
	 * "locationname(start)", where locationname is the name of the starting
	 * location.
	 * 
	 * @param initialFunds    initial amount of money backpacker has
	 * @param initialLocation initial location of backpacker
	 */
	public Backpacker(int initialFunds, Location initialLocation) {
		currentFunds = initialFunds;
		currentLocation = initialLocation;
		listOfCities = initialLocation.getName() + "(start)";
		nightsInTrainStation = (actualNights - availableNights);
	}

	/**
	 * Returns the name of the backpacker's current location
	 * 
	 * @return name of backpacker's location
	 */
	public String getCurrentLocation() {
		return currentLocation.getName();
	}

	/**
	 * Returns the amount of money the backpacker currently has.
	 * 
	 * @return amount of money backpacker currently has
	 */
	public int getCurrentFunds() {
		return currentFunds;
	}

	/**
	 * Returns the backpacker's journal. The journal is a string of comma-separated
	 * values of the form locationname(number_of_nights) containing the cities
	 * visited by the backpacker, in the order visited, along with the number of
	 * nights spent in each. First value always has the form locationname(start) for
	 * the starting location.
	 * 
	 * @return the backpacker's journal.
	 */
	public String getJournal() {
		return listOfCities;
	}

	/**
	 * Returns true if the backpacker does not have enough money to send a postcard
	 * from the current location.
	 * 
	 * @return true if backpacker cannot afford to send a postcard
	 */
	public boolean isSOL() {
		return getCurrentFunds() < currentLocation.costToSendPostcard();
	}

	/**
	 * Returns the number of nights the backpacker has spent in a train station when
	 * visiting a location without enough money for lodging.
	 * 
	 * @return the number of nights the backpacker spent in a train station when
	 *         visiting a location without money for lodging
	 */
	public int getTotalNightsInTrainStation() {
		return nightsInTrainStation;
	}

	/**
	 * Simulates a visit by this backpacker to the given location for the given
	 * number of nights. The backpacker's funds are reduced based on the number of
	 * nights of lodging purchased. When the funds are not sufficient for numNights
	 * nights of lodging in the location, the number of nights spent in the train
	 * station is updated accordingly. The journal is updated by appending a comma,
	 * the location name, and the number of nights in parentheses.
	 * 
	 * @param c         given location
	 * @param numNights given number of nights
	 */
	public void visit(Location c, int numNights) {
		currentLocation = c;
		actualNights = numNights;
		int costToStay = 0;
		availableNights = currentFunds / currentLocation.lodgingCost();
		if (availableNights > actualNights) {
			costToStay = currentLocation.lodgingCost() * actualNights;
		} else if (availableNights < actualNights) {
			costToStay = currentLocation.lodgingCost() * availableNights;
		}
		currentFunds = currentFunds - costToStay;
		listOfCities = listOfCities + (", " + currentLocation.getName() + "(" + actualNights + ")");
		nightsInTrainStation += (numNights - (costToStay / currentLocation.lodgingCost()));

	}

	/**
	 * Sends the given number of postcards, if possible, reducing the backpacker's
	 * funds appropriately and increasing the count of postcards sent. If there is
	 * not enough money, sends as many postcards as possible without allowing the
	 * funds to go below 0.
	 * 
	 * @param howMany the number of postcards able to be sent based off of the
	 *                backpacker's given amount of money.
	 */
	public void sendPostcardsHome(int howMany) {
		if (howMany * currentLocation.costToSendPostcard() < currentFunds) {
			currentFunds = currentFunds - (currentLocation.costToSendPostcard() * howMany);
			postcardCounter = postcardCounter + howMany;
		} else if (howMany * currentLocation.costToSendPostcard() > currentFunds) {
			int availablePostcardsToSend = currentFunds / currentLocation.costToSendPostcard();
			int actualPostcardsSent = Math.min(availablePostcardsToSend, howMany);
			currentFunds = currentFunds - (actualPostcardsSent * currentLocation.costToSendPostcard());
			postcardCounter = postcardCounter + actualPostcardsSent;
		}
	}

	/**
	 * Increases the backpacker's funds by an amount equal to SYMPATHY_FACTOR times
	 * the number of postcards sent since the last call to this method, and resets
	 * the count of the number of postcards sent back to zero.
	 */
	public void callHomeForMoney() {
		currentFunds = currentFunds + postcardCounter * SYMPATHY_FACTOR;
		postcardCounter = 0;
	}
}
