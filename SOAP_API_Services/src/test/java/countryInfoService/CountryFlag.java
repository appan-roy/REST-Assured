package countryInfoService;

import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class CountryFlag {

	static String projDir = System.getProperty("user.dir");
	
	static String xmlPath = projDir+"/SOAP XML Files/CountryInfoService/CountryFlag.xml";
	
	static String driverPath = projDir+"/Drivers/chromedriver.exe";
	
	@Test
	public void getCountryFlagImage() throws Exception {

		
		// Get the country flag URL
		
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
		
		String countryFlagURL = xml.getString("CountryFlagResult");
		
		
		// Open browser and check the country flag image
		
		System.setProperty("webdriver.chrome.driver",driverPath);
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get(countryFlagURL);
		
		Thread.sleep(5000);
		
		driver.quit();
		
	}

}
