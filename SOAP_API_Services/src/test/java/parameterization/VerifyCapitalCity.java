package parameterization;

import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;


public class VerifyCapitalCity {
	
	@Test
	public void validateCapital() throws Exception {
		
		baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		
		String countryName = "India";
		
		String countryCode = "IN";
		
		String expected_capital = "New Delhi";
		
		String requestBody =
				 
	 	"<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +
	 			"<Body>\r\n" +
	 				"<CapitalCity xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n" +
	 					"<sCountryISOCode>"+countryCode+"</sCountryISOCode>\r\n" +
	 				"</CapitalCity>\r\n" +
	 			"</Body>\r\n" +
        "</Envelope>";
		
		
		Response res = null;
				
		res =	given()
					.header("Content-Type", "text/xml")
					.and()
					.body(requestBody).
				when()
					.post().
				then()
					.statusCode(200)
					.log().all()
					.extract().response();
		
		XmlPath xml = new XmlPath(res.asString());
		
		String actual_capital = xml.getString("CapitalCityResult");
		
		Assert.assertEquals(actual_capital, expected_capital);
		
		System.out.println("The capital city of "+countryName+" is "+actual_capital);
		
	}

}
