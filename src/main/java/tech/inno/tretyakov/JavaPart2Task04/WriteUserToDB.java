package tech.inno.tretyakov.JavaPart2Task04;

import org.springframework.stereotype.Component;

import java.util.Set;

import static tech.inno.tretyakov.JavaPart2Task04.JavaPart2Task04Application.usersRepo;

@Component
public class WriteUserToDB implements DataWriter{

    //UsersRepo usersRepo = applicationContext.getBean(UsersRepo.class);

    @Override
    @LogTransformation
    public <T> void write(T userData) {
        Set<Users> userList = (Set<Users>)userData;
        userList.forEach(it->usersRepo.save(it));
        System.out.println("All users are written to DB");
    }
}
