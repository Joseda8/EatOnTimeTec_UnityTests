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


public class Person {

	private String user_id = "30511";
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    @Ignore
    public void sign_up() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"idUser\":30511, \"name\":\"Jose\", \"phone\":\"88888888\", "
        		+ "\"province\": {\"idProvince\":3, \"provinceName\":\"Cartago\"}, \"email\":\"jd@gmail.com\"}")
        .when()
        .post("/EatOnTimeTECAPI/person/signup");
            
        String body = res.getBody().asString();
        assertTrue(body.equals("Usuario registrado exitosamente"));
    }
    
    @Test
    @Ignore
    public void assign_user_preferences() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("[\"Desayuno\"]")
        .when()
        .post("/EatOnTimeTECAPI/person/assign/preferences/"
        		+ user_id);
            
        String body = res.getBody().asString();
        assertTrue(body.equals("Preferencias asignadas exitosamente"));
    }
    
    @Test
    @Ignore
    public void get_user_preferences() throws JSONException {  
    	String preferences = get("/EatOnTimeTECAPI/person/preferences/"
    			+ user_id).asString();
        System.out.println(preferences);
    	JSONArray jsonArr = new JSONArray(preferences);		
    	assertFalse(jsonArr.length()==0);
    }
    
    @Test
    @Ignore
    public void get_preferences() throws JSONException {  
    	String preferences = get("/EatOnTimeTECAPI/person/default/preferences").asString();
        System.out.println(preferences);
    	JSONArray jsonArr = new JSONArray(preferences);		
    	assertFalse(jsonArr.length()==0);
    }
}
