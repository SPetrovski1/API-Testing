package fruz.udemy.test;

import org.testng.annotations.BeforeClass;

import com.jayway.restassured.RestAssured;

public abstract class BasicTest {
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "http://localhost:8002";
	}

}
