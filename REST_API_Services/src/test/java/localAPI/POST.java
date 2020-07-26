package localAPI;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class POST {

	@Test
	public void createUser() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject json = new JSONObject();
		
		json.put("firstName", "Paul");
		json.put("lastName", "Morphy");
		json.put("fideRating", 2587);
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.post("/users").
		then()
			.statusCode(201)
			.log().all();

	}
	
	@Test
	public void createTool() {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject json = new JSONObject();
		
		json.put("toolName", "Tosca");
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.post("/tools").
		then()
			.statusCode(201)
			.log().all();

	}
	
}
