package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

public class TasksActivity extends AppCompatActivity {
    private ListView listView;
    private TaskController taskController;
    private List<Task> tasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        listView = findViewById(R.id.taskListView);
        taskController = new TaskController(this);
        tasks = taskController.getTasks();

        String[] taskTitles = new String[0];
        if(tasks.isEmpty()){
            TextView emptyMessage = findViewById(R.id.emptyMessageTextView);
            emptyMessage.setVisibility(View.VISIBLE);
        }else {
            taskTitles = new String[tasks.size()];
            for (int i = 0; i < tasks.size(); i++) {
                taskTitles[i] = tasks.get(i).toString();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                taskTitles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected task
                Task selectedTask = tasks.get(position);

                // Open TaskDetails Activity with the task ID
                Intent intent = new Intent(TasksActivity.this, TaskDetails.class);
                intent.putExtra(TaskDetails.EXTRA_TASK_ID, selectedTask.getId());
                startActivity(intent);
            }
        });

        if (getIntent().getBooleanExtra("open_status_change", false)) {
            // Open StatusChange Activity
            Intent statusChangeIntent = new Intent(TasksActivity.this, StatusChange.class);
            startActivity(statusChangeIntent);

            // Clear the extra to prevent reopening on rotation or other lifecycle events
            getIntent().removeExtra("open_status_change");
        }


    }
}