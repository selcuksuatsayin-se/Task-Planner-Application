import java.util.List;
import java.util.stream.Collectors;

public class HomeTaskFilter implements TaskFilterStrategy {
    @Override
    public List<Task> filter(List<Task> tasks) {
        return tasks.stream()
                .filter(task -> "Home".equals(task.getCategory()))
                .collect(Collectors.toList());
    }
}