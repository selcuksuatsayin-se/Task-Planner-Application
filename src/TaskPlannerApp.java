import java.time.LocalDate;
import java.util.List;

public class TaskPlannerApp {
    public static void main(String[] args) {

        TaskModel model = new TaskModel();

        LocalDate birthDate = LocalDate.of(2025, 1, 14);
        List<Task> tasks = model.getAllTasks();

        TaskPlannerView view = new TaskPlannerView(model, birthDate, tasks);
        view.setTaskFilterStrategy(new WorkTaskFilter());
        view.applyTaskFilter(model.getAllTasks());

        view.setTaskFilterStrategy(new HomeTaskFilter());
        view.applyTaskFilter(model.getAllTasks());

        new TaskPlannerController(model, view);
    }
}