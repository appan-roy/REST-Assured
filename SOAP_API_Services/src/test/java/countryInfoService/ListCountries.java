package countryInfoService;

import static io.restassured.RestAssured.*;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;


public class ListCountries {

	static String projDir = System.getProperty("user.dir");
	
	static String xmlPath = projDir+"/SOAP XML Files/CountryInfoService/ListCountries.xml";
	
	@Test
	public void getCountries() throws Exception {
		
		FileInputStream fis = new FileInputStream(xmlPath);
		
		baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		
		given()
			.header("Content-Type", "text/xml")
			.and()
			.body(IOUtils.toString(fis, "UTF-8")).
		when()
			.post().
		then()
			.statusCode(200)
			.log().all()
			.extract().response();
			
	}

}
