package reqres.in;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DELETE {

	@Test
	public void deleteUser() {
		
		String req = "https://reqres.in/api/users/2";
		
		when()
			.delete(req).
		then()
			.statusCode(204)
			.log().all();
		
	}

}
