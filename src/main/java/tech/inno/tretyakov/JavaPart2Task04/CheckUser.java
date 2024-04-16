package tech.inno.tretyakov.JavaPart2Task04;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static tech.inno.tretyakov.JavaPart2Task04.LogSaver.saveToLog;

@Component
public class CheckUser implements DataChecker {

    public <T> void check(T userData) {
        Users user = (Users) userData;

        // Сделать первые буквы ФИО большими
        user.setFio(Arrays.stream(user.getFio().split(" "))
                    .map(StringUtils::capitalize)
                    .collect(Collectors.joining(" ")));

        // Найти входы без указания времени и записать информацию в лог
        user.getLogins()
                .stream()
                .filter(it->it.getAccess_time() == null)
                .toList()
                .forEach(it-> saveToLog(it.getUser().getUsername() + " " + it.getLogFileName()));

        // Удалить информацию о входах без указания времени, чтобы не писать в БД
        user.getLogins().removeIf(login -> login.getAccess_time() == null);

        // Проверить значение типа приложения и при необходимости заменить
        user.getLogins()
                .stream()
                .filter(it->!it.getApplication().matches("web|mobile"))
                .peek(it-> it.setApplication("other:" + it.getApplication()))
                .findFirst();
    }
}