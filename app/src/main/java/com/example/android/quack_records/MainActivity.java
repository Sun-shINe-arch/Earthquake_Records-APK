package com.example.android.quack_records;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private String selectedCountry;

    private Spinner mCountrySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText minMagnitudeInput = findViewById(R.id.min_magnitude);
        EditText startDateInput = findViewById(R.id.start_date);
        EditText endDateInput = findViewById(R.id.end_date);
        Button submitButton = findViewById(R.id.submit_button);

        startDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startDateInput);
            }
        });

        endDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(endDateInput);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minMagnitude = minMagnitudeInput.getText().toString().trim();
                String startDate = startDateInput.getText().toString().trim();
                String endDate = endDateInput.getText().toString().trim();

                if (minMagnitude.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String dynamicUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson"
                        + "&starttime=" + startDate
                        + "&endtime=" + endDate
                        + "&minmagnitude=" + minMagnitude + "&limit=10000";

                Intent newIntent = new Intent(MainActivity.this, SActivity.class);
                newIntent.putExtra("url", dynamicUrl);
                newIntent.putExtra("selectedCountry", selectedCountry);
                startActivity(newIntent);
            }
        });
    }

    public void showDatePickerDialog(View view) {
        int viewId = view.getId();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                (view1, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    String selectedDate = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDayOfMonth;
                    if (viewId == R.id.start_date) {
                        EditText startDateEditText = findViewById(R.id.start_date);
                        startDateEditText.setText(selectedDate);
                    } else if (viewId == R.id.end_date) {
                        EditText endDateEditText = findViewById(R.id.end_date);
                        endDateEditText.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}