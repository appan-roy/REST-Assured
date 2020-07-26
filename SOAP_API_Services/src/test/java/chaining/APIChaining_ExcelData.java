package chaining;

import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utility.Excel_Utils;

import org.testng.Assert;
import org.testng.annotations.Test;


public class APIChaining_ExcelData {
	
	static String projDir = System.getProperty("user.dir");
	
	static String excelPath = projDir+"/test data/Data.xlsx";
	
	static String sheetName = "CountryList";
	
	@Test
	public void validateCapital() throws Exception {
		
		baseURI = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
		
		Excel_Utils excel = new Excel_Utils(excelPath, sheetName);
		
		int rowNum = excel.getRowCount();
		
		for(int i=1; i<rowNum; i++) {
				
			String countryName = (String) excel.getCellData(i, 0);
			
			String expectedCountryCode = (String) excel.getCellData(i, 1);
			
			String expectedCapitalCity = (String) excel.getCellData(i, 2);
			
			
			
			// FIRST REQUEST //
			String requestCountryCode =
					 
				 	"<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +
				 			"<Body>\r\n" +
				 				"<CountryISOCode xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n" +
				 					"<sCountryName>"+countryName+"</sCountryName>\r\n" +
				 				"</CountryISOCode>\r\n" +
				 			"</Body>\r\n" +
			        "</Envelope>";

			Response responseCountryCode = null;
					
			responseCountryCode =
					
					given()
						.header("Content-Type", "text/xml")
						.and()
						.body(requestCountryCode).
					when()
						.post().
					then()
						.statusCode(200)
						.log().all()
						.extract().response();
			
			XmlPath XmlCountryCodeResult = new XmlPath(responseCountryCode.asString());
			
			String countryCode = XmlCountryCodeResult.getString("CountryISOCodeResult");
			
			Assert.assertEquals(countryCode, expectedCountryCode);
			
			System.out.println("The country code of "+countryName+" is "+countryCode);
			
			
			
			// SECOND REQUEST //
			String requestCapitalCity =
					 
				 	"<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" +
				 			"<Body>\r\n" +
				 				"<CapitalCity xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n" +
				 					"<sCountryISOCode>"+countryCode+"</sCountryISOCode>\r\n" +
				 				"</CapitalCity>\r\n" +
				 			"</Body>\r\n" +
			        "</Envelope>";

			Response responseCapitalCity = null;
			
			responseCapitalCity =
					
					given()
						.header("Content-Type", "text/xml")
						.and()
						.body(requestCapitalCity).
					when()
						.post().
					then()
						.statusCode(200)
						.log().all()
						.extract().response();
			
			XmlPath XmlCapitalCityResult = new XmlPath(responseCapitalCity.asString());
			
			String capitalCity = XmlCapitalCityResult.getString("CapitalCityResult");
			
			Assert.assertEquals(capitalCity, expectedCapitalCity);
			
			System.out.println("The capital city of "+countryName+" is "+capitalCity);
			
		}

	}

}
