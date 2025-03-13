import java.util.List;
import java.util.stream.Collectors;

public class WorkTaskFilter implements TaskFilterStrategy {
    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> "Work".equals(task.getCategory()))
                .collect(Collectors.toList());
    }
}
