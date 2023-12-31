package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class StatusChange extends AppCompatActivity {
    private TextView txtTitle, txtDescription, txtDueDate;
    private Button btnChangeStatus;
    private TaskController taskController;
    private Task selectedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_change);

        // Initialize UI elements
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtDueDate = findViewById(R.id.txtDueDate);
        btnChangeStatus = findViewById(R.id.btnChangeStatus);

        int taskId = getIntent().getIntExtra(TaskDetails.EXTRA_TASK_ID, -1);

        if (taskId != -1) {
            taskController = new TaskController(this);
            selectedTask = taskController.getTaskById(taskId);

            if (selectedTask != null) {
                updateUI();

                // Set click listener for the Change Status button
                btnChangeStatus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onChangeStatusClick(v);
                    }
                });
            } else {
                Log.e("StatusChangeActivity", "Task not found for ID: " + taskId);
            }
        } else {
            Log.e("StatusChangeActivity", "Invalid task ID provided");
        }

    }

    private void updateUI() {

        txtTitle.setText("Title: " + selectedTask.getTitle());
        txtDescription.setText("Description: " + selectedTask.getDescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String formattedDueDate = dateFormat.format(selectedTask.getDueDate());
        txtDueDate.setText("Due Date: " + formattedDueDate);
    }

    public void onChangeStatusClick(View view) {
        // Check the current status
        String currentStatus = selectedTask.getStatus();

        // Toggle the status
        String newStatus = "due".equals(currentStatus) ? "done" : "due";

        updateTaskStatus(selectedTask.getId(), newStatus);

        refreshTaskDetails();
    }

    private void updateTaskStatus(int taskId, String newStatus) {
        taskController.updateTaskStatus(taskId, newStatus);
    }

    private void refreshTaskDetails() {
        selectedTask = taskController.getTaskById(selectedTask.getId());
        updateUI();
    }

}