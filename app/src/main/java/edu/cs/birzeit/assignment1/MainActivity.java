package edu.cs.birzeit.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                if(position == 0){
                    Intent intent = new Intent(MainActivity.this, TasksActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                     // Open AddTaskActivity when the button is clicked
                     Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                     startActivity(intent);
                }
            }
        };
        ListView listView = (ListView)findViewById(R.id.task_menu);
        listView.setOnItemClickListener(itemClickListener);
    }
}