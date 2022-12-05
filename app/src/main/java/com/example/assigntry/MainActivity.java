package com.example.assigntry;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtID, edtFullName;
    private Button addBtn, saveBtn;
    private RecyclerView studentRV;

    private StudentAdapter adapter;
    private ArrayList<StudentModal> StudentModalArrayList;
    public static final String STUD_PREF_NAME = "Stud_Prefs";
    public static final String STUD_PREF_KEY = "stud_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.edtID);
        edtFullName = findViewById(R.id.edtFullName);
        addBtn = findViewById(R.id.idBtnAdd);
        saveBtn = findViewById(R.id.idBtnSave);
        studentRV = findViewById(R.id.idRVStudent);

        loadData();

        buildRecyclerView();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(edtID.getText().toString().isEmpty()) && !(edtFullName.getText().toString()).isEmpty())
                {
                    StudentModalArrayList.add(new StudentModal(edtID.getText().toString(), edtFullName.getText().toString()));
                    adapter.notifyItemInserted(StudentModalArrayList.size());
                }
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void buildRecyclerView() {
        adapter = new StudentAdapter(StudentModalArrayList, MainActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        studentRV.setHasFixedSize(true);

        studentRV.setLayoutManager(manager);

        studentRV.setAdapter(adapter);
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

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(STUD_PREF_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(StudentModalArrayList);

        editor.putString(STUD_PREF_KEY, json);

        editor.apply();

        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }
}