package jsonBlobApi;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class tests {
	public String link = null;
	
	@Test
	public void testBlogPost()
	{
		RestAssured.baseURI = "https://jsonblob.com/api"; 
		String requestBody = "{\"content\": \"Blog post content goes here..\"}";
 
        Response response = null;
 
        try {
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/jsonBlob");
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        
        //Verify if the content text is correct
        System.out.println("Does Reponse contains 'Blog post content'? :" + response.asString().contains("Blog post content"));
       
        //Verify if the response code is correct
        assertEquals(201, response.getStatusCode());
        
        link = response.getHeader("location");
        System.out.println(link);
    }
	
	@Test
	public void testUpdateblog()
	{
		
	}
		
}
