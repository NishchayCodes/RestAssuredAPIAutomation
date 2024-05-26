import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;
public class RestApiDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
       //Add Place(POST)
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body(payload.AddPlace()).when().post("maps/api/place/add/json").
		then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server","Apache/2.4.52 (Ubuntu)")
		.extract().response().asString();
		JsonPath js=new JsonPath(response);
		String placeid=js.getString("place_id");
		System.out.println(placeid);
		
		//Update place(PUT)
		String newAddress="Aadarsh nagar , Chandausi";
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
		body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

		//Get Place(GET)
		String getPlaceResponse=given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeid)
		.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(getPlaceResponse);
		String actualAdress=js1.getString("address");
		System.out.println(actualAdress);
		Assert.assertEquals(actualAdress,newAddress);
		
	}

}
