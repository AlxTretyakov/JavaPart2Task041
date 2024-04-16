package tech.inno.tretyakov.JavaPart2Task04;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import static tech.inno.tretyakov.JavaPart2Task04.LogSaver.saveToLog;


@Component
public class UserWorker {
    @Setter
    @Getter
    @Autowired
    DataReader dataReader;
    @Setter
    @Getter
    @Autowired
    DataWriter dataWriter;

    @Setter
    @Getter
    @Autowired
    DataChecker dataChecker;
    public Set<Users> perform() throws IOException, NoSuchMethodException {
        Set<Users> userList = new HashSet<>();
        userList = dataReader.read();
        if (userList.size() > 0) {
            userList.forEach(dataChecker::check);

            Method m = dataWriter.getClass().getMethod("write", Object.class);
            if (m.isAnnotationPresent(LogTransformation.class)) {
                saveToLog("Запись данных в БД. Время: " + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
            }

            dataWriter.write(userList);
        }
        return userList;

    }

}
