package com.example.appuser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHandler {
    public static Connection connectDB() {

        Connection connection = null;
        try {

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres?createDatabaseIfNotExist=true",
                    "postgres", "postgres");
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user(" +
                                                                              "id SERIAL PRIMARY KEY," +
                                                                              "username VARCHAR(128)," +
                                                                              "password VARCHAR(128)" +
                                                                              " );");
            preparedStatement.executeUpdate();
        } catch (Exception message) {
            System.out.println(message);
        }
        return connection;
    }

    public static int addUser(User user) throws SQLException {
        int result = 0;
        Connection connect = UserDaoHandler.connectDB();
        PreparedStatement preparedStatement
                = connect.prepareStatement(
                "insert into user(username,password) values (?,?)");

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        result = preparedStatement.executeUpdate();

        connect.close();
        return result;
    }

    public static int updateUser(User user)
            throws SQLException {
        int result = 0;

        Connection connect = UserDaoHandler.connectDB();

        PreparedStatement preparedStatement
                = connect.prepareStatement(
                "update user set username=?,password=? where id=?");

        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());

        result = preparedStatement.executeUpdate();

        connect.close();
        return result;
    }

    public static int deleteUser(int id) throws SQLException {
        int result = 0;

        Connection connect = UserDaoHandler.connectDB();

        PreparedStatement preparedStatement
                = connect.prepareStatement(
                "delete from USER where id =?");
        preparedStatement.setInt(1, id);

        result = preparedStatement.executeUpdate();

        connect.close();

        return result;
    }

    public static User getUserById(int id)
            throws SQLException {
        User user = new User();

        Connection connect = UserDaoHandler.connectDB();

        PreparedStatement preparedStatement
                = connect.prepareStatement(
                "select * from USER where id=?");

        preparedStatement.setInt(1, id);


        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
        }

        connect.close();
        return user;
    }

    public static List<User> getAllUsers(int start,
                                         int total)
            throws SQLException {
        List<User> list = new ArrayList<User>();

        Connection connect = UserDaoHandler.connectDB();

        PreparedStatement preparedStatement
                = connect.prepareStatement(
                "select * from user limit " + (start - 1)
                + "," + total);
        ResultSet resultSet
                = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            list.add(user);
        }

        connect.close();
        return list;
    }
}
