package data_driven_testing;

import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utility.Data_Provider;

import org.testng.Assert;
import org.testng.annotations.Test;


public class POST_DataProvider extends Data_Provider{
	
	@Test(dataProvider="Data for POST")
	public void validateCapital(String countryName, String countryCode, String expected_capital) throws Exception {
		
		baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
				
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
