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

public class CookTest {

	String user_id = "154845451";
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    @Ignore
    public void get_orders() throws JSONException {  
        String orders = get("/EatOnTimeTECAPI/cook/orders/515111215").asString();
    	
        System.out.println(orders);
    	JSONArray jsonArr = new JSONArray(orders);		
    	assertFalse(jsonArr.length()==0);
    }
    
    @Test
    public void change_status() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .when()
        .post("EatOnTimeTECAPI/cook/update/status/920");
            
        String body = res.getBody().asString();
        System.out.println(body);
        assertTrue(body.equals("Status Actualizado"));
    }
}
