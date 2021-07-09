package jsonDataExtraction;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class JsonDataExtractionDiffWays {

	String accessToken = "NtVFclFyYg55Pj2b";

	@Test
	public void getMyProfile() {

		baseURI = "https://lichess.org";

		Response res = null;

		res = given().auth().oauth2(accessToken).header("Content-Type", "application/json").when().get("/api/account")
				.then().statusCode(200).and().log().all().extract().response();

		JsonPath jp = new JsonPath(res.asString());

		// validate id

		// 1
		Assert.assertEquals(res.getBody().jsonPath().get("id"), "knightrider90");

		// 2
		Assert.assertEquals(res.then().extract().path("id"), "knightrider90");

		// 3
		Assert.assertEquals(jp.get("id").toString(), "knightrider90");

		// validate username

		// 1
		Assert.assertEquals(res.getBody().jsonPath().get("username"), "KnightRider90");

		// 2
		Assert.assertEquals(res.then().extract().path("username"), "KnightRider90");

		// 3
		Assert.assertEquals(jp.get("username").toString(), "KnightRider90");

		// validate profile country

		// 1
		Assert.assertEquals(res.getBody().jsonPath().get("profile.country"), "IN");

		// 2
		Assert.assertEquals(res.then().extract().path("profile.country"), "IN");

		// 3
		Assert.assertEquals(jp.get("profile.country").toString(), "IN");

		// validate profile location

		// 1
		Assert.assertEquals(res.getBody().jsonPath().get("profile.location"), "Kolkata");

		// 2
		Assert.assertEquals(res.then().extract().path("profile.location"), "Kolkata");

		// 3
		Assert.assertEquals(jp.get("profile.location").toString(), "Kolkata");

		// validate language

		// 1
		Assert.assertEquals(res.getBody().jsonPath().get("language"), "en-GB");

		// 2
		Assert.assertEquals(res.then().extract().path("language"), "en-GB");

		// 3
		Assert.assertEquals(jp.get("language").toString(), "en-GB");

		// validate url

		// 1
		Assert.assertEquals(res.getBody().jsonPath().get("url"), "https://lichess.org/@/KnightRider90");

		// 2
		Assert.assertEquals(res.then().extract().path("url"), "https://lichess.org/@/KnightRider90");

		// 3
		Assert.assertEquals(jp.get("url").toString(), "https://lichess.org/@/KnightRider90");

	}

}
