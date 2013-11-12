package fr.videoclub;
import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals = new ArrayList<Rental>();
	double thisAmount = 0;
	Rental each;
	
	public Customer(String name) {
		_name = name;
	}

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	}

	public String statement() 
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;		
		String resultStatement = "Rental Record for " + getName() + "\n";
		
		for (Rental rental : _rentals) 
		{
			double thisAmount = rental.getCharge();
			// add frequent renter points
			frequentRenterPoints++;
			// add bonus for a two day new release rental
			if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
					&& rental.getDaysRented() > 1)
				frequentRenterPoints++;

			// show figures for this rental
			resultStatement += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;

		}
		// add footer lines
		resultStatement += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		resultStatement += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return resultStatement;

	}
	

}
