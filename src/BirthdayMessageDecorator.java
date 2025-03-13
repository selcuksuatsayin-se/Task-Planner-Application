import java.time.LocalDate;

public class BirthdayMessageDecorator extends MessageDecorator {
    private String birthDate;

    public BirthdayMessageDecorator(MessageDecorator message, String birthDate) {
        super(message);
        this.birthDate = birthDate;
    }

    @Override
    public String getMessage() {
        String baseMessage = super.getMessage();
        LocalDate today = LocalDate.now();

        LocalDate parsedBirthDate = LocalDate.parse(birthDate);
        if (today.getDayOfMonth() == parsedBirthDate.getDayOfMonth() &&
                today.getMonth() == parsedBirthDate.getMonth()) {
            return baseMessage + "\nHappy Birthday!";
        }

        return baseMessage;
    }
}
