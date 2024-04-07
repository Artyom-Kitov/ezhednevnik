package ru.nsu.fit.ezhednevnik.dao;

import org.springframework.stereotype.Component;
import ru.nsu.fit.ezhednevnik.models.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class TaskDAO {
    private static long idCounter = 0;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ezhednevnik";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "rootroot";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> index() {
        List<Task> tasks = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM tasks";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Task task = new Task();

                task.setId(resultSet.getLong("id"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setPriority(resultSet.getString("priority"));
                task.setCreatedAt(resultSet.getDate("created_at"));
                task.setDeadline(resultSet.getDate("deadline"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public List<Task> save(Task task) {
        List<Task> tasks = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO tasks VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, task.getId());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setString(4, task.getPriority());
            preparedStatement.setString(5, task.getStatus());
            preparedStatement.setDate(6, new Date(task.getCreatedAt().getTime()));
            preparedStatement.setDate(7, new Date(task.getDeadline().getTime()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
