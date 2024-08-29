package com.qa.testrailmanager;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Class to work with the TestRail API methods.

public class TestRailManager {

    public static String TEST_RUN_ID = "18";
    public static String TEST_RAIL_USERNAME = "your_email";
    public static String TEST_RAIL_PASSWORD = "your_password";
    public static String TEST_RAIL_ENGINE_URL = "https://rajatjuyal.testrail.io/";
    public static int TEST_RAIL_PASS_STATUS = 1;
    public static int TEST_RAIL_FAIL_STATUS = 5;

    public static void addResultsForTestCase(String testcaseID, int status, String error){
        String testrunID = TEST_RUN_ID;
        APIClient client = new APIClient(TEST_RAIL_ENGINE_URL);
        client.setUser(TEST_RAIL_USERNAME);
        client.setPassword(TEST_RAIL_PASSWORD);
        Map<String,Object> data = new HashMap<>();
        data.put("status_id", status);
        data.put("comment","this is the comments"+error);
        try {
            client.sendPost("add_result_for_case/"+testrunID+"/"+testcaseID, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (APIException e) {
            throw new RuntimeException(e);
        }

    }
}
