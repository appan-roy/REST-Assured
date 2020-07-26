package chaining;

import org.json.simple.JSONObject;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class APIChaining_POST {
	
	@Test
	public void createUsers() {
		
		baseURI = "http://localhost:3000";
		
		int rating [] = {1555, 1623, 1743, 1859, 1420};
		
		for(int i=0; i<5; i++) {
			
			Response res = given().contentType(ContentType.JSON).log().all().get("/students/"+(i+1));
			
			String fname = res.then().extract().path("firstName");
			String lname = res.then().extract().path("lastName");
			
			JSONObject json = new JSONObject();
			
			json.put("firstName", fname);
			json.put("lastName", lname);
			json.put("fideRating", rating[i]);
			
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
		
	}

}
