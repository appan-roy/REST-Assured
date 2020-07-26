package data_driven_testing;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DELETE_Parameters{
	
	// Parameters will be accepted from testng.xml file. Run the testng.xml file instead of running this class.
	
	@Parameters({"userId"})
	@Test
	public void deleteUser(int userId) {
		
		baseURI = "http://localhost:3000/";
		
		when()
			.delete("/users/"+userId).
		then()
			.statusCode(200)
			.log().all();

	}

}
