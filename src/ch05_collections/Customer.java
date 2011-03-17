package ch05_collections;

/**
 * Beispielklasse zur Demonstration einzelner Map-Funktionalitäten
 * 
 * @author Michael Inden
 * 
 * Copyright 2011 by Michael Inden 
 */
final class Customer
{
	private String firstName;
    private String name;
    
    private String city;
    private int    age;
    
    Customer (final String strFirstName, final String strName, final String strCity, final int nAge)
    {
        firstName = strFirstName;
        name = strName;
        
        city = strCity;
        
        age = nAge;
    }

    @Override
    public String toString()
    {
        return "Customer [age=" + age + ", city=" + city + ", firstName=" + firstName + ", name=" + name
               + "]";
    }
}