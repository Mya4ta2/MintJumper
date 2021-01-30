package mint.runner.type;

import java.util.ArrayList;
import java.util.HashMap;

public class Event {
    public final ArrayList<Runnable> tasks = new ArrayList<>();

    public void run() {
        for (Runnable task : tasks) {
            task.run();
        }
    }

    public void add(Runnable runnable) {
        tasks.add(runnable);
    }
}
