package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        List<User> users = new ArrayList<>();
        users.add(new User("Николай", "Щеглов", (byte) 34));
        users.add(new User("Олег", "Кротов", (byte) 16));
        users.add(new User("Светлана", "Кучина", (byte) 41));
        users.add(new User("Геннадий", "Ставкин", (byte) 27));

        userService.createUsersTable();
        for (User user: users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных");
        }
        for (User user: userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
