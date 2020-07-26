package simplePayload;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class Serialization {

    @Test
    public void POJO_to_JSON() throws JsonProcessingException {

        baseURI = "http://localhost:3000";
    	
    	Response response = null;

        Users user = new Users("Hikaru", "Nakamura", 2727, 21);
        
        ObjectMapper objMap = new ObjectMapper();
        
        String data = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(user);
	
        response = 	given()
            			.contentType("application/json")
            			.body(data).
            		when()
            			.post("/users");
        
        System.out.println("Response: "+response.asString());
        
    }

}
