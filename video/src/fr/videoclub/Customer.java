package fr.videoclub;
import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals = new ArrayList<Rental>();
	
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
		
		String resultStatement = "Rental Record for " + getName() + "\n";
		
		for (Rental rental : _rentals) 
		{
			// show figures for this rental
			resultStatement += "\t" + rental.getMovie().getTitle() + "\t"
					+ String.valueOf(rental.getCharge()) + "\n";
		}
		// add footer lines
		resultStatement += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
		resultStatement += "You earned " + String.valueOf(getTotalFrequentRenterPoints())
				+ " frequent renter points";
		return resultStatement;

	}
	
	private int getTotalFrequentRenterPoints()
	{
		int result = 0;
		for(Rental rental : _rentals)
		{
			result += rental.getFrequentRenterPoints(rental);
		}
		return result;
		
	}
	
	private double getTotalCharge()
	{
		double result = 0.0;
		for(Rental rental : _rentals)
		{
			result += rental.getCharge();
		}
		return result;
	}
	

}
