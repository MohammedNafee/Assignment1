package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent = getIntent();
        int id = (int)intent.getExtras().get("task_id");

       // Task task = Task.tasks[id];

        TextView txtTitle= (TextView)findViewById(R.id.txtTitle);
        TextView txtDesc= (TextView)findViewById(R.id.txtDesc);
        TextView txtDueDate = (TextView)findViewById(R.id.txtDueDate);

      //  txtTitle.setText(task.getName());
      //  txtDesc.setText(task.getDescription());
      //  txtDueDate.setText(task.getName());
    }
}