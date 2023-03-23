package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try(Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate("USE usersdb");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (id BIGINT AUTO_INCREMENT, name  VARCHAR(255),lastName  VARCHAR(255),age TINYINT UNSIGNED, PRIMARY KEY (id)  )");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы");
            e.printStackTrace();

        }
    }

    public void dropUsersTable() {

        try(Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate("DROP table Users");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы. Таблица не существует");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try(PreparedStatement pStatement = Util.getConnection().prepareStatement("INSERT users(name,lastname,age) values (?,?,?)");) {
            pStatement.setString(1,name);
            pStatement.setString(2,lastName);
            pStatement.setByte(3,age);
            pStatement.executeUpdate();
            System.out.println("User c именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения пользователя " + name);
        }
        System.out.println("User с именем - " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {

        try(PreparedStatement pStatement = Util.getConnection().prepareStatement("DELETE FROM users where id = ?")) {
            pStatement.setLong(1,id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя " + id);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        User user;

        try(Statement statement = Util.getConnection().createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            for(User u:users) {
                System.out.println(u);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения списка всех пользователей");
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement statement = Util.getConnection().createStatement();) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления всех пользователей");
        }
    }
}
