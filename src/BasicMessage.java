import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BasicMessage implements Message {
    private LocalDate currentDate;

    public BasicMessage() {
        this.currentDate = LocalDate.now();
    }

    @Override
    public String getMessage() {
        String day = currentDate.getDayOfWeek().toString();
        String date = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return day + ", " + date;
    }
}
