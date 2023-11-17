package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final EditText edtTaskTitle = findViewById(R.id.edtTaskTitle);
        final EditText edtTaskDescription = findViewById(R.id.edtTaskDescription);
        final DatePicker taskDueDatePicker = findViewById(R.id.taskDueDatePicker);
        final Spinner taskStatusSpinner = findViewById(R.id.taskStatusSpinner);
        Button btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get task details from UI elements
                String title = edtTaskTitle.getText().toString();
                String description = edtTaskDescription.getText().toString();

                // Convert DatePicker values to Date
                int year = taskDueDatePicker.getYear();
                int month = taskDueDatePicker.getMonth();
                int day = taskDueDatePicker.getDayOfMonth();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);

                Date dueDate = calendar.getTime();

                String status = taskStatusSpinner.getSelectedItem().toString();

                // Create a new task with the obtained details
                Task newTask = new Task(title, description, dueDate, status);

                // Add the new task to your TaskManager or data source
                TaskController taskController = new TaskController(AddTaskActivity.this);
                taskController.addTask(newTask);

                // Finish the activity to return to TaskActivity
                finish();
            }
        });
    }
}