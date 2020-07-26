package schema;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class JsonSchema {

    @Test
    public void testJsonSchema() {

    	baseURI = "http://localhost:3000/";

    	given()
		.when()
    		.get("/users/")
    	.then()
    		.assertThat()
    		.body(matchesJsonSchemaInClasspath("JsonSchemaFile.json"));	// Add the "JsonSchemaFile.json" file to project classpath i.e., [src/main/java] //

    }

}
