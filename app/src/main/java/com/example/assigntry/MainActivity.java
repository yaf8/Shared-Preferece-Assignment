package com.example.assigntry;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText edtID, edtFullName;

    @SuppressLint("StaticFieldLeak")
    public static StudentAdapter adapter;
    public static Gson gson;
    public static ArrayList<StudentModal> StudentModalArrayList;
    public static final String STUD_PREF_NAME = "Stud_Prefs";
    public static final String STUD_PREF_KEY = "stud_key";
    public static LayoutInflater liBlue, liRed, liGreen;
    @SuppressLint("StaticFieldLeak")
    public static View blueToastLayout, redToastLayout, greenToastLayout;
    @SuppressLint("StaticFieldLeak")
    public static TextView blueToastMessage, redToastMessage, greenToastMessage;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edtID);
        edtFullName = findViewById(R.id.edtFullName);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnDeleteAll = findViewById(R.id.btnDeleteAll);
        Button btnDisplay = findViewById(R.id.btnDisplay);


        loadData();


        liBlue = getLayoutInflater();
        liRed = getLayoutInflater();
        liGreen = getLayoutInflater();

        blueToastLayout = liBlue.inflate(R.layout.blue_toast, findViewById(R.id.blue_toast_layout));
        redToastLayout = liRed.inflate(R.layout.red_toast,  findViewById(R.id.red_toast_layout));
        greenToastLayout = liGreen.inflate(R.layout.green_toast, findViewById(R.id.green_toast_layout));

        blueToastMessage = blueToastLayout.findViewById(R.id.blueToastMessage);
        redToastMessage = redToastLayout.findViewById(R.id.redToastMessage);
        greenToastMessage = greenToastLayout.findViewById(R.id.greenToastMessage);

        btnSave.setOnClickListener(v -> {
            if (!(edtID.getText().toString().isEmpty()) && !(edtFullName.getText().toString()).isEmpty()) {
                StudentModalArrayList.add(new StudentModal(edtID.getText().toString(), edtFullName.getText().toString()));
                adapter.notifyItemInserted(StudentModalArrayList.size());
                saveData();
            }
            else{
                blueToastMessage.setText("Id or Name is EMPTY!");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(blueToastLayout);
                toast.show();
            }
        });

        btnDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);
        });

        btnDeleteAll.setOnClickListener(v -> deleteAll());

        btnUpdate.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, UpdateActivity.class);
            startActivity(intent1);
        });

        btnSearch.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, SearchActivity.class);
            startActivity(intent2);
        });
        btnDelete.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, DeleteActivity.class);
            startActivity(intent3);
        });
    }

    @SuppressLint("SetTextI18n")
    private void deleteAll() {
        StudentModalArrayList.clear();

        SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        gson = new Gson();
        String json = gson.toJson(StudentModalArrayList);
        editor.putString(MainActivity.STUD_PREF_KEY, json);
        editor.apply();

        redToastMessage.setText("Data Cleared");
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
        toast.setView(redToastLayout);
        toast.show();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(STUD_PREF_KEY, null);
        Type type = new TypeToken<ArrayList<StudentModal>>() {}.getType();
        StudentModalArrayList = gson.fromJson(json, type);

        if (StudentModalArrayList == null) {
            StudentModalArrayList = new ArrayList<>();
        }
    }

    @SuppressLint("SetTextI18n")
    private void saveData()
    {
        for (StudentModal str : MainActivity.StudentModalArrayList) {
            if (!(Objects.equals(str.getID(), (edtID.getText().toString())))) {

                SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                gson = new Gson();
                String json = gson.toJson(StudentModalArrayList);
                editor.putString(STUD_PREF_KEY, json);
                editor.apply();

                greenToastMessage.setText("Saved Successfully");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 150);
                toast.setView(greenToastLayout);
                toast.show();

                edtID.setText("");
                edtFullName.setText("");
                break;
            } else {
                blueToastMessage.setText("ID Exists!");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 150);
                toast.setView(blueToastLayout);
                toast.show();
            }
        }
    }
}
