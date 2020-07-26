package data_driven_testing;

import org.testng.annotations.Test;
import utility.Data_Provider;
import static io.restassured.RestAssured.*;

public class DELETE_DataProvider extends Data_Provider{
	
	@Test(dataProvider="Data for DELETE")
	public void deleteUser(int userId) {
		
		baseURI = "http://localhost:3000/";
		
		when()
			.delete("/users/"+userId).
		then()
			.statusCode(200)
			.log().all();

	}

}
