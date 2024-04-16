package tech.inno.tretyakov.JavaPart2Task04;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogSaver {
    public static void saveToLog(String stringForLog){
        Logger logger = Logger.getLogger("UserLog");
        try{
            FileHandler fileHandler = new FileHandler("log.txt", true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.info(stringForLog);
        } catch (SecurityException | IOException e) {
            logger.log(Level.SEVERE, "Ошибка при записи в лог!", e);
        }
    }
}
