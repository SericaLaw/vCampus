package team.yummy.vCampus.util;

/**
 * 日志记录类，用于打印程序运行过程中不同模块的输出
 * @author Serica
 */
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
