package localAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GET {

	@Test
	public void getUsers() {
		
		baseURI = "http://localhost:3000/";
		
		given()
			.get("/users").
		then()
			.statusCode(200)
			.log().all();
		
	}
	
	@Test
	public void getSpecificTool() {
		
		baseURI = "http://localhost:3000/";
		
		given()
			.param("toolName", "UiPath")
			.get("/tools").
		then()
			.statusCode(200)
			.log().all();
		
	}
	
}
