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

public class FriendTest {
	String name = "Daniel";
	String phone = "65458741";
	String user_id = "116920112";
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    @Ignore
    public void get_people_by_name() throws JSONException {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"name\":\""
        		+ name
        		+ "\"}")
        .when()
        .post("/EatOnTimeTECAPI/friend/find/name");
        
        String body = res.getBody().asString();
        System.out.println(body);
    }
    
    @Test
    @Ignore
    public void get_people_by_phone() throws JSONException {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"phone\":\""
        		+ phone
        		+ "\"}")
        .when()
        .post("/EatOnTimeTECAPI/friend/find/phone");
        
        String body = res.getBody().asString();
        System.out.println(body);
    }
    
    @Test
    @Ignore
    public void add_friend() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"idUser\": \"365434553\", \"idFriend\": \"116920112\"}")
        .when()
        .post("/EatOnTimeTECAPI/friend/add");
            
        String body = res.getBody().asString();
        System.out.println(body);
        assertTrue(body.equals("Amigo agregado exitosamente"));
    }
    
    @Test
    public void get_user_friends() throws JSONException {  
        String friends = get("/EatOnTimeTECAPI/friend/get/"
        		+ user_id).asString();
    	
        System.out.println(friends);
    	JSONArray jsonArr = new JSONArray(friends);		
    	assertFalse(jsonArr.length()==0);
    }
}
