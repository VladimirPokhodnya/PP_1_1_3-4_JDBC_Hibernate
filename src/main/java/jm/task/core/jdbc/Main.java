package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Владимир","Иванов", (byte)18);
        userService.saveUser("Иван","Сидоров", (byte)43);
        userService.saveUser("Андрей","Петров", (byte)25);
        userService.saveUser("Елена","Спичкина", (byte)38);


        userService.getAllUsers();

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
