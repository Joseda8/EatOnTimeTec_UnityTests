package test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;

public class Login {
	private String user_id = "154845451";
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    public void login() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"user_id\":"
        		+ user_id
        		+ ", \"password\":\"password\"}")
        .when()
        .post("/EatOnTimeTECAPI/login");
            
        String body = res.getBody().asString();
        System.out.println(body);
        assertTrue(body.equals("Inicio de sesion exitoso"));
    }
}
