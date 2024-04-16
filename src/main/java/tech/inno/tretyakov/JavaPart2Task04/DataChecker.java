package tech.inno.tretyakov.JavaPart2Task04;

import org.springframework.stereotype.Component;

public interface DataChecker {
    <T> void check(T data);
}
