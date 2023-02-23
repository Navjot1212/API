package com.NavjotSingh.LearnAPIAutomation;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PhoneAPI {

	@BeforeMethod
	public void setup() {
		baseURI = "https://phonenumbervalidatefree.p.rapidapi.com";
	}

	@Test
	public void validatePhoneNumber() {
		given().param("number", "+14372174447").and()
		.param("country", "CA")
		.header("X-RapidAPI-Key", "d76f19b464msh51d6a0d6017f9d5p13a955jsnb8aba80adfec").and()
		.header("X-RapidAPI-Host", "phonenumbervalidatefree.p.rapidapi.com")
		.when().get("/ts_PhoneNumberValidateTest.jsp")
		.then().log().all();
	}

}
