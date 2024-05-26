import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js=new JsonPath(payload.CoursePrice());
		//print no. of courses returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		
		//print PurchaseAmount
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//print title of first course
		String FirstCourseTitle=js.get("courses[0].title");
		System.out.println(FirstCourseTitle);
		
		//print all course titles and their respective prices
		for(int i=0;i<count;i++) {
			String courseTitles=js.get("courses["+i+"].title");
			System.out.println(courseTitles);
			
			System.out.println(js.get("courses["+i+"].price").toString());
			
			
			
			}
		//print copies sold by RPA
		for(int i=0;i<count;i++) {
			String courseTitle=js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
				
			}
			
		}

	}

}
