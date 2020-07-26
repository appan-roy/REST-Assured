package localAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PUT {

	@Test
	public void updateUser() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject json = new JSONObject();
		
		json.put("firstName", "Adolf");
		json.put("lastName", "Anderssen");
		json.put("fideRating", 2571);
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.put("/users/16").
		then()
			.statusCode(200)
			.log().all();

	}
	
}
