package test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import io.restassured.RestAssured;

public class MenuTest {
	
	private String id_dish = "11";
	private String user_id = "154845451";
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    
    @Test
    @Ignore
    public void get_menu() throws JSONException {  
    	String dishes = get("/EatOnTimeTECAPI/menu/get").asString();
        System.out.println(dishes);
    	JSONArray jsonArr = new JSONArray(dishes);		
    	assertFalse(jsonArr.length()==0);
    }
    
    @Test
    @Ignore
    public void get_current_menu_id() throws JSONException {  
    	String id = get("/EatOnTimeTECAPI/menu/id/current").asString();
        System.out.println(id);
    }
    
    @Test
    @Ignore
    public void get_all_dishes() throws JSONException {  
    	String dishes = get("/EatOnTimeTECAPI/menu/dish/all").asString();
        System.out.println(dishes);
    	JSONArray jsonArr = new JSONArray(dishes);		
    	assertFalse(jsonArr.length()==0);
    }
    
    @Test
    @Ignore
    public void add_dishes() {    	
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("[\"Chifrijo\"]")
        .when()
        .post("/EatOnTimeTECAPI/menu/add/dish/all");
            
        String body = res.getBody().asString();
        assertTrue(body.equals("Platillos agregados exitosamente"));
    }
    
    @Test
    @Ignore
    public void remove_dish() {    	
        io.restassured.response.Response res = given()
        .when()
        .delete("/EatOnTimeTECAPI/menu/remove/"
        		+ id_dish);
        
        String body = res.getBody().asString();
    	System.out.println(body);
        assertTrue(body.equals("Eliminacion realizada satisfactoriamente"));
    }
    
    @Test
    @Ignore
    public void get_recommendations() throws JSONException {    	
    	String recommendations = get("/EatOnTimeTECAPI/menu/get/recommendations/"
    			+ user_id).asString();
        System.out.println(recommendations);
    	JSONArray jsonArr = new JSONArray(recommendations);		
    	assertFalse(jsonArr.length()==0);
    }
    
    
    @Test
    @Ignore
    public void publish() throws JSONException {    	
    	int response = post("/EatOnTimeTECAPI/menu/publish").statusCode();
        assertTrue(response==200);
    }
}