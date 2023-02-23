package com.NavjotSingh.LearnAPIAutomation;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class JiraAPI {

	String authorization = "Basic bmF2am90LnJlaGFsMTJAZ21haWwuY29tOkFUQVRUM3hGZkdGMHUxRTVORzBuZWZUcllBNklVQkhIRWRLcjhDSFZWbUtoT3E0OTJIWGpIOW1SWXVlM0xEc2xoSGh4QzFLc0s4Vmh5V1dIYWt3Y1dudnpmQkFiamF1OFhTSVJDYkhoSy1CZDlwb1FFOHdCYjFpbldHRmxtQlVkOVBaQU1iTlJDaDZ6Q3hVelhQcEVERnVQRWlBbzJ6R0o2U3dMeEtBaGxWWFl5VmxUUTUwUmlVOD00MjNCRDE1QQ==";
	
	@BeforeMethod
	public void setup() {
		baseURI = "https://navjotsingh.atlassian.net";
	}

	@Test
	public void getCreateIssueMetadata() {
		Response response =  given().header("Authorization", authorization)
		.when().get("/rest/api/3/issue/createmeta");
		String storyId = response.getBody().jsonPath().get("projects[0].issuetypes[1].id");
		System.out.println(storyId);
	}
	
	@Test
	public void createIssue() {
		String body = "{\r\n"
				+ "\"fields\": {\r\n"
				+ "\"assignee\": {\r\n"
				+ "\"id\": \"6325e3561f3f6665a605e2c5\"\r\n"
				+ "},\r\n"
				+ "\"description\": {\r\n"
				+ "\"content\": [\r\n"
				+ "{\r\n"
				+ "\"content\": [\r\n"
				+ "{\r\n"
				+ "\"text\": \"This issue is created using postman\",\r\n"
				+ "\"type\": \"text\"\r\n"
				+ "}\r\n"
				+ "],\r\n"
				+ "\"type\": \"paragraph\"\r\n"
				+ "}\r\n"
				+ "],\r\n"
				+ "\"type\": \"doc\",\r\n"
				+ "\"version\": 1\r\n"
				+ "},\r\n"
				+ "\"issuetype\": {\r\n"
				+ "\"id\": 10001\r\n"
				+ "},\r\n"
				+ "\"labels\": [\r\n"
				+ "\"postman\"\r\n"
				+ "],\r\n"
				+ "\"project\": {\r\n"
				+ "\"id\": \"10000\"\r\n"
				+ "},\r\n"
				+ "\"reporter\": {\r\n"
				+ "\"id\": \"6325e3561f3f6665a605e2c5\"\r\n"
				+ "},\r\n"
				+ "\"summary\": \"Created using postman\"\r\n"
				+ "}\r\n"
				+ "}";
		
		given().header("Authorization", authorization).header("Content-Type", "application/json").body(body)
		.when().post("/rest/api/3/issue")
		.then().log().all();
	}

}
