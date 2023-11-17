package edu.cs.birzeit.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private static int taskIdCounter = 1; // Initial ID counter
    //the name of the SharedPreferences file
    private static final String PrefsName = "TaskPrefs";
    //the key for storing Tasks
    private static final String TasksKey = "tasks";
    private SharedPreferences prefs;
    SharedPreferences.Editor editor;
    private Gson gson;

    public TaskController(Context context) {
        prefs = context.getSharedPreferences(PrefsName, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void addTask(Task task) {
        List<Task> tasks = getTasks();
        task.setId(generateUniqueId());
        tasks.add(task);
        saveTasks(tasks);
    }

    private static int generateUniqueId() {
        return taskIdCounter++;
    }

    private void saveTasks(List<Task> tasks) {
        String strTasksGson = gson.toJson(tasks);
        editor = prefs.edit();
        editor.putString(TasksKey, strTasksGson);
        editor.commit();
    }

    public List<Task> getTasks() {
        String strGson = prefs.getString(TasksKey, null);

        if (strGson != null) {
            Type type = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(strGson, type);
        } else {
            return new ArrayList<>();
        }
    }

    public void updateTaskStatus(int taskId, String newStatus) {
        List<Task> tasks = getTasks();

        for (Task task : tasks) {
            if (task.getId() == taskId) {
                // Update the task status
                task.setStatus(newStatus);
                saveTasks(tasks);
                return;
            }
        }
    }
}
