package reqres.in;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

public class PATCH {

	@Test
	public void partialUpdateUser() {
		
		String req = "https://reqres.in/api/users/2";
		
		JSONObject json = new JSONObject();
		json.put("name", "Bobby");
		json.put("job", "Cricketer");
		System.out.println(json);
		
		given()
			.header("Content-Type", "application/json")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(json.toJSONString()).
		when()
			.patch(req).
		then()
			.statusCode(200)
			.log().all();
		
	}

}
