package utility;

import org.testng.annotations.DataProvider;

public class Data_Provider {
	
	@DataProvider(name="Data for POST")
	public Object [][] dataPost() {
		
		return new Object[][] {
			
			{"United States", "US", "Washington"},
			{"Brazil", "BR", "Brasilia"},
			{"Spain", "ES", "Madrid"},
			{"South Africa", "ZA", "Cape Town"},
			{"India", "IN", "New Delhi"},
			{"Australia", "AU", "Canberra"}
			
		};
		
	}
	
}
