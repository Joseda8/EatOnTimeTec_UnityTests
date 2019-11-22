package test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import io.restassured.RestAssured;
 
public class TestClass {
    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }
    
    @Test
    public void testHello() {
        get("/EatOnTimeTECAPI/hello/w")
        .then()
        .body("provinceName", equalTo("Limon"));
    }

    @Test
    @Ignore
    public void testString() {
    	String json = get("/EatOnTimeTECAPI/hello/w").asString();
    	System.out.println(json);
    }

}