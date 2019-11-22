package test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import io.restassured.RestAssured;

public class DishTest {
	
	private String name_dish = "pinto";
	private int id_dish = 11;
	
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    public void getIdDish() {  
    	int result;
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"name\":\""
        		+ name_dish
        		+ "\"}")
        .when()
        .get("/EatOnTimeTECAPI/dish/get/id");
        
        String body = res.getBody().asString();
    	result = Integer.parseInt(body);	
    	assertTrue(result==id_dish);
    }
    
    @Test
    @Ignore
    public void update_dish() {
    	String id = Integer.toString(id_dish);
    	given().contentType("application/json")
        .body("{\"name\":\"Pinto\", \"points\": 14, \"ingredients\":\"Arroz, frijoles\", "
        		+ "\"calories\":120, \"price\":1000, \"preference\": 1}")
        .when()
        .put("/EatOnTimeTECAPI/dish/update/"
        		+ id);
    }
    
    @Test
    @Ignore
    public void postDish() {
        io.restassured.response.Response res = given()
        .contentType("application/json")
        .body("{\"name\":\"Pinto\", \"points\": 14, \"ingredients\":\"Arroz, frijoles\", "
        		+ "\"calories\":120, \"price\":500, \"preference\": 1}")
        .when()
        .post("/EatOnTimeTECAPI/dish/add");
            
        String body = res.getBody().asString();
        assertTrue(body.equals("Platillo creado exitosamente"));
    }
    
    @Test
    @Ignore
    public void remove_dish() {    	
        io.restassured.response.Response res = given()
        .when()
        .delete("/EatOnTimeTECAPI/dish/remove/"
        		+ id_dish);
        
        String body = res.getBody().asString();
    	System.out.println(body);
    }
}