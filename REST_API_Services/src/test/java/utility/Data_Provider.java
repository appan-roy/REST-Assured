package utility;

import org.testng.annotations.DataProvider;

public class Data_Provider {

	@DataProvider(name="Data for POST")
	public Object [][] dataPost() {
		
		Object [][] data = new Object [2][3];
		
		data [0][0] = "Adolf";
		data [0][1] = "Anderssen";
		data [0][2] = 2596;
		
		data [1][0] = "Paul";
		data [1][1] = "Morphy";
		data [1][2] = 2578;
		
		return data;
		
	}
	
	@DataProvider(name="Data for POST 2")
	public Object [][] dataPost2() {
		
		return new Object[][] {
			
			{"Judit", "Polgar", 2747},
			{"Frank", "Marshall", 2723}
			
		};
		
	}
	
	@DataProvider(name="Data for DELETE")
	public Object [] dataDelete() {
		
		return new Object[] {
		
				16, 17
				
		};
		
	}
	
}
