package localAPI;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DELETE {

	@Test
	public void deleteUser() {
		
		baseURI = "http://localhost:3000/";
		
		when()
			.delete("/users/14").
		then()
			.statusCode(200)
			.log().all();

	}

}
