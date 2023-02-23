package com.NavjotSingh.LearnAPIAutomation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class GetListOfBooks {

	String accessToken = "f6757b188858c79413f0effe09df9e81f27ca2411c8b952e96459f456898f2eb";
	String orderId;
	
	@BeforeMethod
	public void setup() {
		baseURI = "https://simple-books-api.glitch.me";
	}

	@Test
	public void getListOfBooks() {
		given().param("type", "fiction")
		.and().param("limit", 20)
		.when().get("/books")
		.then().statusCode(200).log().all();
	}

	@Test
	public void getSingleBook() {
		given().pathParam("bookId", 4)
		.when().get("/books/{bookId}")
		.then().assertThat().statusCode(200)
		.and().log().all()
		.and().assertThat().body("name", equalTo("The Midnight Library"))
		.and().assertThat().body("id",notNullValue())
		.and().assertThat().body("author",equalTo("Matt Haig"))
		.and().assertThat().body("available",equalTo(true));
	}
	
	@Test
	public void submitAnOrder() {
		String body = "{\"bookId\": 5, \"customerName\": \"Navjot\"}";
		Response response = given().header("Authorization",accessToken)
		.header("Content-Type", "application/json")
		.body(body)
		.when().post("/orders");
		orderId = response.getBody().jsonPath().get("orderId");
		System.out.println(orderId);
		response.then().log().all();
	}
	
	
	@Test(dependsOnMethods = {"submitAnOrder"})
	public void deleteAnOrder() {
		given().header("Authorization",accessToken)
		.pathParam("orderId", orderId)
		.when().delete("/orders/{orderId}")
		.then().log().all();
	}
	
	
	@Test
	public void apiAuthentication() {
		String requestBody = "{\"clientName\": \"Navjot\",\"clientEmail\": \"Navjot2@gmail.com\"}";
		Response response =  given()
		.header("Content-Type", "application/json")
		.body(requestBody  )
		.when().post("/api-clients/");
		accessToken = response.getBody().jsonPath().get("accessToken");
		response.then().log().all();
		System.out.println(accessToken);
	}

}
