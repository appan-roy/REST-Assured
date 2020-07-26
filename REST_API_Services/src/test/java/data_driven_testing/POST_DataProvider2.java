package data_driven_testing;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import utility.Data_Provider;
import static io.restassured.RestAssured.*;

public class POST_DataProvider2 extends Data_Provider{

	@Test(dataProvider="Data for POST 2")
	public void createUser(String fname, String lname, int rating) {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject json = new JSONObject();
		
		json.put("firstName", fname);
		json.put("lastName", lname);
		json.put("fideRating", rating);
		
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
