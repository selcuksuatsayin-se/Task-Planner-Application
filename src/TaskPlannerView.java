import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class TaskPlannerView extends JFrame implements Observer {


    private TaskFilterStrategy taskFilterStrategy;
    private TaskModel model;
    private JLabel dayLabel, dateLabel, birthdayMessageLabel;
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private DefaultListModel<String> notificationsListModel;
    private JList<String> notificationsList;
    private JButton addTaskButton, deleteTaskButton, editTaskButton;


    public TaskPlannerView(TaskModel model, LocalDate birthDate, List<Task> tasks) {


        this.model = model;


        setTitle("Task Planner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        dayLabel = new JLabel();
        dateLabel = new JLabel();
        birthdayMessageLabel = new JLabel();
        JPanel messagePanel = new JPanel(new GridLayout(3, 1));
        messagePanel.add(dayLabel);
        messagePanel.add(dateLabel);
        messagePanel.add(birthdayMessageLabel);


        new MessageUpdater(birthdayMessageLabel, birthDate, tasks);

        notificationsListModel = new DefaultListModel<>();
        notificationsList = new JList<>(notificationsListModel);
        JPanel notificationsPanel = new JPanel(new BorderLayout());
        notificationsPanel.add(new JLabel("Notifications"), BorderLayout.NORTH);
        notificationsPanel.add(new JScrollPane(notificationsList), BorderLayout.CENTER);

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JPanel taskListPanel = new JPanel(new BorderLayout());
        taskListPanel.add(new JLabel("Task List"), BorderLayout.NORTH);
        taskListPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        addTaskButton = new JButton("Add Task");
        deleteTaskButton = new JButton("Delete Task");
        editTaskButton = new JButton("Edit Task");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTaskButton);
        buttonPanel.add(deleteTaskButton);
        buttonPanel.add(editTaskButton);

        setLayout(new BorderLayout());
        add(messagePanel, BorderLayout.NORTH);
        add(notificationsPanel, BorderLayout.WEST);
        add(taskListPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void setTaskFilterStrategy(TaskFilterStrategy strategy) {
        this.taskFilterStrategy = strategy;
    }


    public void applyTaskFilter(List<Task> tasks) {
        if (taskFilterStrategy != null) {
            List<Task> filteredTasks = taskFilterStrategy.filter(tasks);
            updateTaskList(filteredTasks);
        }
    }


    public void updateTaskList(List<Task> tasks) {
        taskListModel.clear();
        for (Task task : tasks) {
            taskListModel.addElement(task);
        }
    }


    public void updateNotifications(List<Task> tasks) {
        notificationsListModel.clear();
        LocalDate today = LocalDate.now();

        for (Task task : tasks) {
            if (task.getDeadline().equals(today.plusDays(1))) {
                notificationsListModel.addElement("Reminder: Task \"" + task.getName() + "\" is due tomorrow!");
            }
        }
    }


    @Override
    public void update() {
        List<Task> tasks = model.getAllTasks();
        updateTaskList(tasks);
        updateNotifications(tasks);
    }


    public Task getSelectedTask() {
        return taskList.getSelectedValue();
    }


    public JButton getAddTaskButton() {
        return addTaskButton;
    }


    public JButton getDeleteTaskButton() {
        return deleteTaskButton;
    }


    public JButton getEditTaskButton() {
        return editTaskButton;
    }
}
