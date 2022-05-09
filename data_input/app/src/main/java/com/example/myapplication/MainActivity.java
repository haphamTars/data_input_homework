package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatePicker();
        dateButton = findViewById(R.id.select_date);
        dateButton.setText(getTodaysDate());

        Spinner spinner = findViewById(R.id.province_spinner_option);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

        Spinner spinner2 = findViewById(R.id.district_spinner_option);
        if (spinner2 != null) {
            spinner2.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.districts_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        if (spinner2 != null) {
            spinner2.setAdapter(adapter2);
        }
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }


    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog= new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void openDatePicker(View view){
        datePickerDialog.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void checkPolicy(View view){
        int isValid = 1;
        TextView name = findViewById(R.id.full_name_enter_text);
        TextView studentID = findViewById(R.id.student_id_enter_text);
        TextView idCard = findViewById(R.id.id_card_enter_text);
        TextView phone = findViewById(R.id.phone_number_enter_text);
        TextView email = findViewById(R.id.email_enter_text);
        TextView curAdress = findViewById(R.id.address_enter_text);
        RadioGroup radioGroup = findViewById(R.id.radio_study_option);

        if(name.getText().toString().equals("")) isValid = 0;
        if(studentID.getText().toString().equals("")) isValid = 0;
        if(idCard.getText().toString().equals("")) isValid = 0;
        if(phone.getText().toString().equals("")) isValid = 0;
        if(email.getText().toString().equals("")) isValid = 0;
        if(curAdress.getText().toString().equals("")) isValid = 0;
        if(radioGroup.getCheckedRadioButtonId() == -1) isValid = 0;

        if(isValid == 0){
            Toast toast = Toast.makeText(this, "You haven't completed this form!", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "You succesfully complete required form!", Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}