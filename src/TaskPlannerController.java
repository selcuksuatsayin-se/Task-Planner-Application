import javax.swing.*;
import java.time.LocalDate;

public class TaskPlannerController {
    private TaskModel model;
    private TaskPlannerView view;

    public TaskPlannerController(TaskModel model, TaskPlannerView view) {
        this.model = model;
        this.view = view;

        model.addObserver(view);

        view.updateTaskList(model.getAllTasks());
        view.updateNotifications(model.getAllTasks());

        view.getAddTaskButton().addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Task Name:");
            String description = JOptionPane.showInputDialog("Task Description:");
            String category = JOptionPane.showInputDialog("Task Category:");
            String deadline = JOptionPane.showInputDialog("Task Deadline (yyyy-mm-dd):");

            if (name != null && description != null && category != null && deadline != null) {
                Task task = new Task(0, name, description, category, LocalDate.parse(deadline));
                model.addTask(task);
                view.updateTaskList(model.getAllTasks());
                view.updateNotifications(model.getAllTasks());
            }
        });

        view.getDeleteTaskButton().addActionListener(e -> {
            Task selectedTask = view.getSelectedTask();
            if (selectedTask != null) {
                model.deleteTask(selectedTask.getId());
                view.updateTaskList(model.getAllTasks());
                view.updateNotifications(model.getAllTasks());
            } else {
                JOptionPane.showMessageDialog(view, "No task selected!");
            }
        });

        view.getEditTaskButton().addActionListener(e -> {
            Task selectedTask = view.getSelectedTask();
            if (selectedTask != null) {
                String name = JOptionPane.showInputDialog("Task Name:", selectedTask.getName());
                String description = JOptionPane.showInputDialog("Task Description:", selectedTask.getDescription());
                String category = JOptionPane.showInputDialog("Task Category:", selectedTask.getCategory());
                String deadline = JOptionPane.showInputDialog("Task Deadline (yyyy-mm-dd):", selectedTask.getDeadline());

                if (name != null && description != null && category != null && deadline != null) {
                    selectedTask.setName(name);
                    selectedTask.setDescription(description);
                    selectedTask.setCategory(category);
                    selectedTask.setDeadline(LocalDate.parse(deadline));

                    model.updateTask(selectedTask);
                    view.updateTaskList(model.getAllTasks());
                    view.updateNotifications(model.getAllTasks());
                }
            } else {
                JOptionPane.showMessageDialog(view, "No task selected!");
            }
        });
    }
}
