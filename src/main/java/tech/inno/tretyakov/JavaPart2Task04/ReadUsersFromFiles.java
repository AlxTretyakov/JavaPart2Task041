package tech.inno.tretyakov.JavaPart2Task04;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ReadUsersFromFiles implements DataReader {
    private String pathinput;
    private final HashMap<Users, Users> listOfUsers = new HashMap<>();

    public ReadUsersFromFiles(@Value("${spring.application.pathinput}") String path) {
        this.pathinput = path;
    }

    // Метод для чтения информации о входах клиентов из текстового файла
    private void readUsersFromFile(Path filePath){
        Users user;
        Logins login;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line = reader.readLine();
            String[] strings;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date loginTime = null;
            while (line != null) {
                strings = line.split(";");
                user = new Users(strings[0], strings[1]);
                if (!listOfUsers.containsKey(user)){
                    listOfUsers.put(user, user);
                } else {
                    user = listOfUsers.get(user);
                }

                try {
                    loginTime = dateFormat.parse(strings[2]);
                } catch (Exception e) {
                    loginTime = null;
                }

                login = new Logins(loginTime != null ? new java.sql.Timestamp(loginTime.getTime()) : null, strings[3], user, filePath.toString());
                user.getLogins().add(login);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    // Метод для чтения списка файлов, имеющихся в каталоге
    public List<Path> getListOfFiles (String pathString) throws IOException {
        Path path = Paths.get(pathString);
        List<Path> listOfFiles;
        try (Stream<Path> walk = Files.walk(path)) {
            listOfFiles = walk
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }
        return listOfFiles;
    }

    @Override
    public Set<Users> read(){
        try {
            List<Path> listOfFiles = getListOfFiles(pathinput);
            listOfFiles.forEach(this::readUsersFromFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listOfUsers.keySet();
    }
}

