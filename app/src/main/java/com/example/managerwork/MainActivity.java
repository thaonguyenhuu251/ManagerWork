package com.example.managerwork;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private static Button date, time;
    private static TextView set_date, set_time;
    private static EditText work, content;
    private static int hour, min;
    private String date1, time1;
    ArrayList<JobInWeek> arrJob = new ArrayList<JobInWeek>();
    ArrayAdapter<JobInWeek> adapter=null;
    ListView lvWork;

    Calendar c;
    int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        min = c.get(Calendar.MINUTE);

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        date = findViewById(R.id.btnDate);
        time = findViewById(R.id.btnTime);
        set_date = findViewById(R.id.txtDateSet);
        set_time = findViewById(R.id.txtTimeSet);

        work = findViewById(R.id.edtWork);
        content = findViewById(R.id.edtContent);
        lvWork = findViewById(R.id.lvWork);

        setTime();
        addWork();
        getFormWidgets();
    }

    private void setTime() {
        date1 = month + "/" + day
                + "/" + year;
        set_date.setText(date1);
        time1 = hour + ":" + min;
        set_time.setText(time1);

        date.setOnClickListener(v -> {
            DatePickerDialog dd = new DatePickerDialog(MainActivity.this,
                    (view, year, monthOfYear, dayOfMonth) -> {
                        date1 = monthOfYear + "/" + dayOfMonth
                                + "/" + year;
                        set_date.setText(date1);
                    }, year, month, day);
            dd.show();
        });

        time.setOnClickListener(arg0 -> {
            TimePickerDialog td = new TimePickerDialog(MainActivity.this,
                    (view, hourOfDay, minute) -> {
                        time1 = hourOfDay + ":" + minute;
                        set_time.setText(time1);
                    },
                    hour, min,
                    DateFormat.is24HourFormat(MainActivity.this)
            );
            td.show();
        });
    }

    private void addWork() {
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        lvWork.setOnItemClickListener(new MyListViewEvent());
        lvWork.setOnItemLongClickListener(new MyListViewEvent());
    }

    private void getFormWidgets() {
        adapter = new ArrayAdapter<JobInWeek>(this, android.R.layout.simple_list_item_1, arrJob);
        lvWork.setAdapter(adapter);
    }

    public void processAddJob() {
        String title = work.getText().toString();
        String description = content.getText().toString();
        JobInWeek job = new JobInWeek(title, description, date1, time1);
        arrJob.add(job);
        adapter.notifyDataSetChanged();
        work.setText("");
        content.setText("");
        work.requestFocus();
    }

    @Override
    public void onClick(View v) {
        processAddJob();
    }

    private class MyListViewEvent implements
            AdapterView.OnItemClickListener,
            AdapterView.OnItemLongClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Toast.makeText(MainActivity.this, arrJob.get(i).getDesciption(), Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            arrJob.remove(i);
            adapter.notifyDataSetChanged();
            return false;
        }
    }
}
