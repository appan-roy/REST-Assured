package complexPayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FinalPayload {

	public static void main(String[] args) throws JsonProcessingException {
		
		Issuetype issue = new Issuetype("Bug");
		
		Project prj = new Project("TEST");
		
		Payload payload = new Payload(prj, "Test summary", "Test Description", issue);
		
		Fields field = new Fields(payload);
		
		ObjectMapper objMap = new ObjectMapper();
		
		String jsonData = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(field);
		
		System.out.println(jsonData);
		
	}

}
