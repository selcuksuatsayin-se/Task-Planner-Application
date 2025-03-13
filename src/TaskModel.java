import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskModel {

    private Connection connection;
    private List<Observer> observers = new ArrayList<>();


    public TaskModel() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/task_planner", "your_username", "your_password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }


    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("category"),
                        rs.getDate("deadline").toLocalDate()
                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }


    public void addTask(Task task) {
        String query = "INSERT INTO tasks (name, description, category, deadline) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getCategory());
            pstmt.setDate(4, Date.valueOf(task.getDeadline()));
            pstmt.executeUpdate();
            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteTask(int taskId) {
        String query = "DELETE FROM tasks WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, taskId);
            pstmt.executeUpdate();
            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateTask(Task task) {
        String query = "UPDATE tasks SET name = ?, description = ?, category = ?, deadline = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, task.getName());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getCategory());
            pstmt.setDate(4, Date.valueOf(task.getDeadline()));
            pstmt.setInt(5, task.getId());
            pstmt.executeUpdate();
            notifyObservers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
