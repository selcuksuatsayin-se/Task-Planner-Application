import java.time.LocalDate;

public class BirthdayMessage extends MessageDecorator {
    private LocalDate birthDate;

    public BirthdayMessage(Message message, LocalDate birthDate) {
        super(message);
        this.birthDate = birthDate;
    }

    @Override
    public String getMessage() {
        String baseMessage = super.getMessage();
        LocalDate today = LocalDate.now();

        if (today.getMonth() == birthDate.getMonth() && today.getDayOfMonth() == birthDate.getDayOfMonth()) {
            return baseMessage + " - Happy Birthday!";
        }
        return baseMessage;
    }
}
