package vCampus.server.util;

public class Logger {
    private String moduleName = null;

    public Logger(String moduleName) {
        this.moduleName = moduleName;
    }

    public void log(String message) {
        String logMessage = String.format("[ %s ] %s \n", this.moduleName, message);
        System.out.println(logMessage);
    }
}
