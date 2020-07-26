package data_driven_testing;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import utility.Excel_Utils;
import static io.restassured.RestAssured.*;

public class POST_ExcelData{

	static String projDir = System.getProperty("user.dir");
	
	static String excelPath = projDir+"/test data/Data.xlsx";
	
	static String sheetName = "Grandmasters";
	
	@Test
	public void createUsers() throws Exception {
		
		baseURI = "http://localhost:3000/";
		
		JSONObject json = new JSONObject();
		
		Excel_Utils excel = new Excel_Utils(excelPath, sheetName);
		
		int rows = excel.getRowCount();
		
		for(int i=1; i<rows; i++) {
			
			Object fname = excel.getCellData(i, 0);
			Object lname = excel.getCellData(i, 1);
			
			// Casting the object into a string for FideRating
			String rating = (String) excel.getCellData(i, 2);
			
			// Parsing the string into integer for FideRating
			int fideRating = Integer.parseInt(rating);
			
			json.put("firstName", fname);
			json.put("lastName", lname);
			json.put("fideRating", fideRating);
			
			given()
				.header("Content-Type", "application/json")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(json.toJSONString()).
			when()
				.post("/users").
			then()
				.statusCode(201)
				.log().all();
			
		}
			
	}
	
}
