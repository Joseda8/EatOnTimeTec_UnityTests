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

public class AdminTest {
	
	String user_id = "154845451";
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    public void get_roles() throws JSONException {  
        String roles = get("/EatOnTimeTECAPI/admin/roles").asString();
    	
        System.out.println(roles);
    	JSONArray jsonArr = new JSONArray(roles);		
    	assertFalse(jsonArr.length()==0);
    }
    
    @Test
    @Ignore
    public void assign_role() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"roles\": [\"sysAdmin\", \"menuAdmin\"], \"user_id\": "
        		+ user_id
        		+ "}")
        .when()
        .post("/EatOnTimeTECAPI/admin/assign/roles");
            
        String body = res.getBody().asString();
        System.out.println(body);
        assertTrue(body.equals("Roles asignados exitosamente"));
    }
}