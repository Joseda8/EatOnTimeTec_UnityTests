package test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import io.restassured.RestAssured;

public class Order {
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    public void create_order() throws JSONException {
        given()
        .contentType("application/json")
        .body("{\"users\": [116920112, 515111215], \"dishes\": [\"Arroz con Pollo\", \"Ensalada César\"], "
        		+ "\"observations\": [\"Sin papas\", \"No observation\"]}")
        .when()
        .post("EatOnTimeTECAPI/order/place");
    }
    
    @Test
    @Ignore
    public void rate_order() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"idOrder\": 920, \"rating\": 1}")
        .when()
        .post("/EatOnTimeTECAPI/order/rate");
            
        String body = res.getBody().asString();
        assertTrue(body.equals("Orden calificada exitosamente"));
    }
}
