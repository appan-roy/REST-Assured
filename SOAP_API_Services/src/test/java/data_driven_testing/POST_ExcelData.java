package data_driven_testing;

import static io.restassured.RestAssured.*;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import utility.Excel_Utils;

import org.testng.Assert;
import org.testng.annotations.Test;


public class POST_ExcelData {

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
			
			String countryCode = (String) excel.getCellData(i, 1);
			
			String expected_capital = (String) excel.getCellData(i, 2);
			
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

}
