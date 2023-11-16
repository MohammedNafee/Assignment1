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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        TaskController taskController = new TaskController(this);
        List<Task> tasks = taskController.getTasks();

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
        ListView listView = findViewById(R.id.taskListView);
        listView.setAdapter(adapter);

        // Set an item click listener for the ListView
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


    }

}