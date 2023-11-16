package edu.cs.birzeit.assignment1;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

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
        tasks.add(task);
        saveTasks(tasks);
    }

    private void saveTasks(List<Task> tasks) {
        String strTasksGson = gson.toJson(tasks);
        editor = prefs.edit();
        editor.putString(TasksKey, strTasksGson);
        editor.commit();
    }

    //returns the list of tasks from SharedPreferences.
    public List<Task> getTasks() {
        String strGson = prefs.getString(TasksKey, null);

        if (strGson != null) {
            Type type = new TypeToken<List<Task>>() {}.getType();
            return gson.fromJson(strGson, type);
        } else {
            return new ArrayList<>();
        }
    }
}
