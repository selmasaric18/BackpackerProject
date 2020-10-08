package hw1;

/**
 * Represents a place name along with a cost per night for lodging.
 * 
 * @author Selma Saric
 */
public class Location {

	/**
	 * The cost to mail a postcard from a location is this fraction times the
	 * location's lodging cost (rounded to the nearest integer).
	 */
	public static final double RELATIVE_COST_OF_POSTCARD = 0.05;

	/**
	 * Instance variable that holds the name of the country the backpacker is in.
	 */
	private String nameOfCountry;

	/**
	 * Instance variable that holds the cost to stay at a certain place per night.
	 */
	private int costOfLodging;

	/**
	 * Constructs a new Location with the given name and lodging cost per night.
	 * 
	 * @param givenName        name of location
	 * @param givenLodgingCost lodging cost per night
	 */
	public Location(String givenName, int givenLodgingCost) {
		nameOfCountry = givenName;
		costOfLodging = givenLodgingCost;
	}

	/**
	 * Returns this location's name.
	 * 
	 * @return returns the name of the location
	 */
	public String getName() {
		return nameOfCountry;
	}

	/**
	 * Returns this location's lodging cost per night.
	 * 
	 * @return cost of lodging per night
	 */
	public int lodgingCost() {
		return costOfLodging;
	}

	/**
	 * Returns the cost to send a postcard from this location. The value is a
	 * percentage of the lodging cost specified by the constant
	 * RELATIVE_COST_OF_POSTCARD, rounded to the nearest integer.
	 * 
	 * @return the cost to send a postcard from this location
	 */
	public int costToSendPostcard() {
		int cost = (int) Math.round(costOfLodging * RELATIVE_COST_OF_POSTCARD);
		return cost;
	}

	/**
	 * Returns the number of nights of lodging in this location that a backpacker
	 * could afford with the given amount of money.
	 * 
	 * @param funds given amount of money
	 * @return the number of nights of lodging in this location the backpacker could
	 *         afford
	 */
	public int maxLengthOfStay(int funds) {
		return funds / costOfLodging;
	}

	/**
	 * Returns the number of postcards that a backpacker could afford to send from
	 * this location with the given amount of money.
	 * 
	 * @param funds given amount of money
	 * @return the number of postcards the backpacker could afford to send from this
	 *         location
	 */
	public int maxNumberOfPostcards(int funds) {
		int cost = (int) Math.round(costOfLodging * RELATIVE_COST_OF_POSTCARD);
		return funds / cost;
	}
}
