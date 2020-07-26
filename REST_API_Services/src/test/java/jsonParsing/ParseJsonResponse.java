package jsonParsing;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ParseJsonResponse {

    @Test
    public void testJsonParsing() {
        
    	baseURI = "http://dummy.restapiexample.com/api/v1";
        
    	Response response = null;

        try {
            response = 	given()
            			.when()
            				.get("/employees");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response :" + response.asString());
        
        System.out.println("Status Code :" + response.getStatusCode());
        
        // Creation of JsonPath object
        JsonPath jp = new JsonPath(response.asString());
        
        // Get Number of Records(Employees) in the Response
        String empCount = jp.get("data.size()").toString();
        System.out.println("Total number of employee is "+empCount);
        
        // Validate Number of Records(Employees) in the Response
        expect().body("data", hasSize(24)).when().get("/employees");
        
        // Print all of the employee names
        String empName = jp.getString("data.employee_name");
        System.out.println("The employee name is "+empName);

        // To get the name of the sixth employee
        String sixthEmp = jp.getString("data.employee_name[5]");
        System.out.println("The name of the sixth employee is "+sixthEmp);
        
        // To validate if the 10th employee salary is greater than 100000
        String tenthEmpSalary = jp.getString("data.employee_salary[9]");
        
        if(Integer.parseInt(tenthEmpSalary) > 100000) {
        	System.out.println("The tenth employee salary is greater than 100000");
        }else if(Integer.parseInt(tenthEmpSalary) == 100000) {
        	System.out.println("The tenth employee salary is equal to 100000");
        }else {
        	System.out.println("The tenth employee salary is less than 100000");
        }

    }
    
}
