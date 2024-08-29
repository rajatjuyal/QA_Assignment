import com.qa.testrailmanager.TestRailManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Github_API_Automation {

    private static String testcaseid;

//   Failure Cases - start

    @Test
    private static void NoTokenProvided(){
        testcaseid = "2268";
        try{

            RestAssured.baseURI="https://api.github.com";
            Response response = given().header("Content-Type","application/json").log().all()
                    .get("/user");
            response.prettyPrint();
            Assert.assertEquals(response.statusCode(),401,"response status code is unexpected as"+response.statusCode());

        }catch (Exception e){
            System.out.println("Report Dashboard is not reachable : "+e);
        }
    }

    @Test
    private static void InvalidTokenProvided(){
        testcaseid = "2269";
        try{

            RestAssured.baseURI="https://api.github.com";
            Response response = given().header("Content-Type","application/json").header("Authorization","ghp_Dx4E8qAuos4J97PUC45vmh10s").log().all()
                    .get("/user");
            response.prettyPrint();
            Assert.assertEquals(response.statusCode(),401,"response status code is unexpected as"+response.statusCode());
        }catch (Exception e){
            System.out.println("Report Dashboard is not reachable : "+e);
        }
    }

    @Test
    private static void ForbiddcenTokenProvided(){
        testcaseid = "2270";
        try{

            //To Do -- replace authorization token with a token of no privilege
            RestAssured.baseURI="https://api.github.com";
            Response response = given().header("Content-Type","application/json").header("Authorization","token_with_no_permission").log().all()
                    .get("/user");
            response.prettyPrint();
            Assert.assertEquals(response.statusCode(),403,"response status code is unexpected as"+response.statusCode());

        }catch (Exception e){
            System.out.println("Report Dashboard is not reachable : "+e);
        }
    }

//   Failure Cases - end

//   Success Cases - start

    @Test
    private static void GetUserValidUserToken(){
        testcaseid = "2266";
        try{
            RestAssured.baseURI="https://api.github.com";
            Response response = given().header("Content-Type","application/json").header("Authorization","Bearer valid_user_token").log().all()
                    .get("/user");
            response.prettyPrint();
            Assert.assertEquals(response.statusCode(),200,"response status code is unexpected as"+response.statusCode());

        }catch (Exception e){
            System.out.println("Report Dashboard is not reachable : "+e);
        }
    }
    @Test
    private static void UpdateUserBioWithValidUserToken(){
        testcaseid = "2267";
        try{
            String requestBody = "{\"bio\": \"Hello\"}";

            RestAssured.baseURI="https://api.github.com";
            Response response = given().header("Content-Type","application/json").header("Authorization","Bearer valid_user_token").body(requestBody).log().all()
                    .patch("/user");
            response.prettyPrint();
            Assert.assertEquals(response.statusCode(),200,"response status code is unexpected as"+response.statusCode());
        }catch (Exception e){
            System.out.println("Report Dashboard is not reachable : "+e);
        }
    }

//   Success Cases - end

//Method for uploading the results to TestRail test management tool

    @AfterMethod
    public void addResultsToTestRail(ITestResult result){
        if(result.getStatus()==ITestResult.SUCCESS){
            TestRailManager.addResultsForTestCase(testcaseid,TestRailManager.TEST_RAIL_PASS_STATUS,"");
            } else if (result.getStatus()==ITestResult.FAILURE) {
            TestRailManager.addResultsForTestCase(testcaseid,TestRailManager.TEST_RAIL_FAIL_STATUS,"test got failed"
                    +result.getTestName()+""+"failed");
            
        }

    }
}