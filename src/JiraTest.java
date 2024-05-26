import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

public class JiraTest {

	public static void main(String[] args) {
		
		RestAssured.baseURI="http://localhost:8080";
		
		SessionFilter session=new SessionFilter();
		
		String response=given().header("Content-Type","application/json").
				body("{ \"username\": \"nishchayanand4111998\", \"password\": \"Nishu123@#\" }").log().all()
				.filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		
		given().pathParam("key","10007").log().all().header("Content-Type","application/json").
		body("{\r\n"
				+ "    \"body\": \"Adding comment from RestAPI\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all().assertThat().statusCode(201);

	}

}
