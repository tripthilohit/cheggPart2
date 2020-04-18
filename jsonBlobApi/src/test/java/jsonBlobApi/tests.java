package jsonBlobApi;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class tests {
	public String link;
	
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
  
        //Verify if the blog was created
        System.out.println("Does Reponse contains 'Blog post content'? :" + response.asString().contains("Blog post content"));
       
        //Verify if the response code is correct
        assertEquals(201, response.getStatusCode());
        
        link = response.getHeader("location");
    }
	
	@Test
	public void testBlogUpdateAndDelete()
	{
		String requestBody = "{\"content\": \"Blog post updated content goes here..\"}";
 
        Response response = null;
        
        try {
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Verify if the response code is correct
        System.out.println("Does Reponse contains 'Blog post updated content'? :" + response.asString().contains("Blog post updated content"));
       
        //Verify if the blog was updated correctly
        assertEquals(200, response.getStatusCode());
        
        try {
            response = RestAssured.delete(link);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Verify if the response code is correct
        assertEquals(200, response.getStatusCode());
        
        try {
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get(link);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Confirming if the blog was deleted
        assertEquals(404, response.getStatusCode());
        
	}	
		
}
