package com.NavjotSingh.LearnAPIAutomation;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class InstagramAPI {

	@BeforeMethod
	public void setup() {
		baseURI = "https://instagram28.p.rapidapi.com";
	}

	@Test
	public void userInfo() {
		given().param("user_name", "navjot.rehal12")
		.header("X-RapidAPI-Key", "d76f19b464msh51d6a0d6017f9d5p13a955jsnb8aba80adfec").and()
		.header("X-RapidAPI-Host", "instagram28.p.rapidapi.com")
		.when().get("/user_info")
		.then().log().all();
	}
	
	@Test
	public void GetUserNameByUserId() {
		given().param("user_id", "1437938106")
		.header("X-RapidAPI-Key", "d76f19b464msh51d6a0d6017f9d5p13a955jsnb8aba80adfec").and()
		.header("X-RapidAPI-Host", "instagram28.p.rapidapi.com")
		.when().get("/username")
		.then().log().all();
	}

}
