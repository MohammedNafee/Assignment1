package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TaskDetails extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "task_id";
    private Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        // Get the task ID from the intent
        int taskId = getIntent().getIntExtra(EXTRA_TASK_ID, -1);

        // Retrieve the task details from your TaskManager or data source
        TaskController taskController = new TaskController(this);
        task = taskController.getTasks().get(taskId);

        updateUI();


    }

    private void updateUI(){
        TextView titleTextView = findViewById(R.id.txtTitle);
        titleTextView.setText("Title: "+task.getTitle());

        TextView DescTextView = findViewById(R.id.txtDesc);
        DescTextView.setText("Description: "+task.getDescription());

        TextView DueDateTextView = findViewById(R.id.txtDueDate);
        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd", Locale.US);
        DueDateTextView.setText("Due Date: "+date.format(task.getDueDate()));

        TextView StatusTextView = findViewById(R.id.txtStatus);
        StatusTextView.setText("Status: "+task.getStatus());
    }
}