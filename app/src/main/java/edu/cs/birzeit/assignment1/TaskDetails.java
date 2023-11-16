package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskDetails extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "task_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        // Get the task ID from the intent
        int taskId = getIntent().getIntExtra(EXTRA_TASK_ID, -1);

        // Retrieve the task details from your TaskManager or data source
        TaskController taskController = new TaskController(this);
        Task task = taskController.getTasks().get(taskId);

        // Display task details in UI elements

        TextView titleTextView = findViewById(R.id.txtTitle);
        titleTextView.setText(task.getTitle());

        TextView DescTextView = findViewById(R.id.txtDesc);
        DescTextView.setText(task.getDescription());

        TextView DueDateTextView = findViewById(R.id.txtDueDate);
        SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
        DueDateTextView.setText(date.format(task.getDueDate()));

        TextView StatusTextView = findViewById(R.id.txtStatus);
        StatusTextView.setText(task.getStatus());
    }
}