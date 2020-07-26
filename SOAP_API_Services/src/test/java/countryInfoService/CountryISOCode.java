package countryInfoService;

import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;


public class CountryISOCode {

	static String projDir = System.getProperty("user.dir");
	
	static String xmlPath = projDir+"/SOAP XML Files/CountryInfoService/CountryISOCode.xml";
	
	@Test
	public void getCountryISOCode() throws Exception {
		
		FileInputStream fis = new FileInputStream(xmlPath);
		
		baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		
		Response res =
				
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
		
		XmlPath xml = new XmlPath(res.asString());
		
		String countryISOCode = xml.getString("CountryISOCodeResult");
		
		System.out.println("The country ISO code is "+countryISOCode);
		
	}

}
