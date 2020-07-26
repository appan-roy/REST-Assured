package fileUpload;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import java.io.File;

public class UploadMultiPartFile {

    @Test
    public void uploadFile() {
    	
        File file = new File("E:\\Images\\godfather.jpg"); //Specify your own location and file

        baseURI = "http://localhost:3000";

        Response response = given()
        						.multiPart(file).
        					when()
        						.post("/uploadFile");

        System.out.println(response.getStatusCode());
        
        System.out.println(response.asString());
        
    }
    
}
