package tech.inno.tretyakov.JavaPart2Task04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class JavaPart2Task04Application {

	public static UsersRepo usersRepo;
	public static void main(String[] args) throws IOException, NoSuchMethodException {

		ApplicationContext ctx = SpringApplication.run(JavaPart2Task04Application.class, args);

		UserWorker userWorker = ctx.getBean(UserWorker.class);
		usersRepo = ctx.getBean(UsersRepo.class);

		Set<Users> userList;
		userList = userWorker.perform();

		System.out.println("==================================================");
		userList.forEach(System.out::println);

	}
}
