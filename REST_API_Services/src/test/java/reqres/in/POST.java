package reqres.in;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class POST {

	@Test
	public void createUser() {
		
		String req = "https://reqres.in/api/users";
		
		JSONObject json = new JSONObject();
		
		json.put("name", "Bobby");
		
		json.put("job", "Chess player");
		
		System.out.println(json);
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.post(req).
		then()
			.statusCode(201)
			.log().all();
		
	}

}
