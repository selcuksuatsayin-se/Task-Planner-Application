import java.time.LocalDate;
import java.util.List;

public class NotificationsMessage extends MessageDecorator {
    private List<Task> tasks;

    public NotificationsMessage(Message message, List<Task> tasks) {
        super(message);
        this.tasks = tasks;
    }

    @Override
    public String getMessage() {
        String baseMessage = super.getMessage();
        StringBuilder notifications = new StringBuilder();

        for (Task task : tasks) {
            if (task.getDeadline().minusDays(1).equals(LocalDate.now())) {
                notifications.append("Reminder: Task '").append(task.getName()).append("' is due tomorrow. ");
            }
        }

        if (notifications.length() > 0) {
            return baseMessage + " - " + notifications;
        }
        return baseMessage;
    }
}
