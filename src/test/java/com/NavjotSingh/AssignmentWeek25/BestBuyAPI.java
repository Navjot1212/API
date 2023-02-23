package com.NavjotSingh.AssignmentWeek25;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class BestBuyAPI {
	int productId;
	
	@BeforeMethod
	public void setup() {
		baseURI = "http://localhost:3030";
	}

	@Test
	public void getProducts() {
//		given().when().get("/products").then().log().all();
		Response response = given().when().get("/products");
		productId = response.getBody().jsonPath().get("data[0].id");
		System.out.println("Product ID of first Item : "+productId);
	}
	
	@Test(dependsOnMethods = { "getProducts" })
	public void getProductById() {
		Response response =  given().pathParam("id", productId)
		.when().get("/products/{id}");
		int productId = response.getBody().jsonPath().get("id");
		String name = response.getBody().jsonPath().get("name");
		response.then().assertThat().statusCode(200)
		.and().log().all()
		.and().assertThat().body("id",equalTo(productId))
		.and().assertThat().body("name", equalTo(name));
		System.out.println("Product ID is "+productId);
		System.out.println("Name of product is "+name);
	}
	
	
	@Test
	public void createProducts() {
		String body = "{\"name\": \"Iphone\",\"type\": \"HardGood\",\"upc\": \"141999\",\"description\": \"Same as Iphone 13 Pro Max\",\"model\": \"14 Pro Max\"}";
		Response response = given().header("Content-Type", "application/json").body(body)
		.when().post("/products");
		productId = response.getBody().jsonPath().get("id");
		System.out.println(productId);
		response.then().log().all();
	}
	
	
	@Test(dependsOnMethods = "createProducts")
	public void deleteProducts() {
		given().pathParam("id", productId)
		.when().delete("/products/{id}")
		.then().log().all();
	}

}
