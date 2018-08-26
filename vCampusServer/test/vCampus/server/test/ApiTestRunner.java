package vCampus.server.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ApiTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ApiTest.class);
        for(Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
