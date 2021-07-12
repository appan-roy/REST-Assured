package authentication;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class OAuth2_Lichess {

	String accessToken = "NtVFclFyYg55Pj2b";

	@Ignore@Test
	public void getMyProfile() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/json").when()
				.get("/api/account").then().statusCode(200).and().log().all().extract().response();

		// validate id
		Assert.assertEquals(res.getBody().jsonPath().get("id"), "knightrider90");

		// validate username
		Assert.assertEquals(res.getBody().jsonPath().get("username"), "KnightRider90");

		// validate profile country
		Assert.assertEquals(res.getBody().jsonPath().get("profile.country"), "IN");

		// validate profile location
		Assert.assertEquals(res.getBody().jsonPath().get("profile.location"), "Kolkata");

		// validate language
		Assert.assertEquals(res.getBody().jsonPath().get("language"), "en-GB");

		// validate url
		Assert.assertEquals(res.getBody().jsonPath().get("url"), "https://lichess.org/@/KnightRider90");

	}

	@Ignore@Test
	public void getMyEmailAddress() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/json").when()
				.get("/api/account/email").then().statusCode(200).and().log().all().extract().response();

		// validate email
		Assert.assertEquals(res.getBody().jsonPath().get("email"), "appan12345@gmail.com");

	}

	@Ignore@Test
	public void getMyPreferences() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/json").when()
				.get("/api/account/preferences").then().statusCode(200).and().log().all().extract().response();

		// validate preferences
		Assert.assertEquals(res.getBody().jsonPath().get("prefs.dark"), false);

	}

	@Ignore@Test(priority = 1)
	public void getMyKidModeStatus() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/json").when()
				.get("/api/account/kid").then().statusCode(200).and().log().all().extract().response();

		// validate kid mode status
		Assert.assertEquals(res.getBody().jsonPath().get("kid"), false);

	}

	@Ignore@Test(priority = 2)
	public void setMyKidModeStatusTrue() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/json")
				.queryParam("v", true).when().post("/api/account/kid").then().statusCode(200).and().log().all()
				.extract().response();

		// validate kid mode status
		Assert.assertEquals(res.getBody().jsonPath().get("ok"), true);

	}

	@Ignore@Test
	public void getRealTimeUserStatus() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/json")
				.queryParams("ids", "knightrider90").when().get("/api/users/status").then().statusCode(200).and().log()
				.all().extract().response();

		// validate user status
		Assert.assertEquals(res.getBody().jsonPath().get("[0].id"), "knightrider90");
		Assert.assertEquals(res.getBody().jsonPath().get("[0].name"), "KnightRider90");

	}

	@Ignore@Test
	public void getAllTop10() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/vnd.lichess.v3+json")
				.when().get("/players").then().statusCode(200).and().log().all().extract().response();

	}

	@Test
	public void getOneLeaderboard() {

		baseURI = "https://lichess.org";

		Response res = given().auth().oauth2(accessToken).header("Content-Type", "application/vnd.lichess.v3+json")
				.pathParam("nb", 5).pathParam("perfType", "bullet").when().get("/player/top/{nb}/{perfType}").then().statusCode(200)
				.and().log().all().extract().response();

	}

}
