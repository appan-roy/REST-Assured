package reqres.in;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PUT {

	@Test
	public void updateUser() {
		
		String req = "https://reqres.in/api/users/2";
		
		JSONObject json = new JSONObject();
		json.put("name", "Bobby");
		json.put("job", "Footballer");
		System.out.println(json);
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.put(req).
		then()
			.statusCode(200)
			.log().all();
		
	}

}
