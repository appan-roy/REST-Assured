package localAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PATCH {

	@Test
	public void partialUpdateUser() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject json = new JSONObject();

		json.put("fideRating", 2630);
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.patch("/users/16").
		then()
			.statusCode(200)
			.log().all();

	}
	
}
