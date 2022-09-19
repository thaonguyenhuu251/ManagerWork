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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static Button date, time;
    private static TextView set_date, set_time;
    private static EditText work, content;
    private static int hour, min;
    private String date1, time1;

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
        date = (Button) findViewById(R.id.btnDate);
        time = (Button) findViewById(R.id.btnTime);
        set_date = (TextView) findViewById(R.id.txtDateSet);
        set_time = (TextView) findViewById(R.id.txtTimeSet);

        work = (EditText) findViewById(R.id.edtWork);
        content = (EditText) findViewById(R.id.edtContent);

        setTime();
        addWork();
    }

    private void setTime() {
        date1 = String.valueOf(month) + "/" + String.valueOf(day)
                + "/" + String.valueOf(year);
        set_date.setText(date1);
        time1 = String.valueOf(hour) + ":" + String.valueOf(min);
        set_time.setText(time1);

        date.setOnClickListener(v -> {
            DatePickerDialog dd = new DatePickerDialog(MainActivity.this,
                    (DatePickerDialog.OnDateSetListener) (view, year, monthOfYear, dayOfMonth) -> {
                        date1 = String.valueOf(monthOfYear) + "/" + String.valueOf(dayOfMonth)
                                + "/" + String.valueOf(year);
                        set_date.setText(date1);
                    }, year, month, day);
            dd.show();
        });

        time.setOnClickListener(arg0 -> {
            TimePickerDialog td = new TimePickerDialog(MainActivity.this,
                    (TimePickerDialog.OnTimeSetListener) (view, hourOfDay, minute) -> {
                        time1 = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                        set_time.setText(time1);
                    },
                    hour, min,
                    DateFormat.is24HourFormat(MainActivity.this)
            );
            td.show();
        });
    }

    private void addWork() {
        ListView listView = (ListView) findViewById(R.id.lvWork);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        ArrayList<String> arrList = null;
        ArrayAdapter<String> adapter = null;
    }

}
