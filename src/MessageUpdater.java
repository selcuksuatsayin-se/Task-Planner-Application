import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class MessageUpdater {
    private JLabel messageLabel;
    private LocalDate birthDate;
    private List<Task> tasks;

    public MessageUpdater(JLabel messageLabel, LocalDate birthDate, List<Task> tasks) {
        this.messageLabel = messageLabel;
        this.birthDate = birthDate;
        this.tasks = tasks;

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshMessage();
            }
        });
        timer.start();
    }

    private void refreshMessage() {
        Message basicMessage = new BasicMessage();
        Message birthdayMessage = new BirthdayMessage(basicMessage, birthDate);
        Message notificationsMessage = new NotificationsMessage(birthdayMessage, tasks);

        messageLabel.setText(notificationsMessage.getMessage());
    }
}
