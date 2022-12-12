package com.example.assigntry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Objects;

public class UpdateActivity extends AppCompatActivity {

    private Button btnUpdate;
    private EditText edtUpdate, edtUpdateID, edtUpdateFullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Button btnFind = findViewById(R.id.btnFind);
        Button btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
        edtUpdate = findViewById(R.id.edtUpdate);
        edtUpdateID = findViewById(R.id.edtUpdateID);
        edtUpdateFullName = findViewById(R.id.edtUpdateFullName);

        edtUpdateID.setVisibility(View.INVISIBLE);
        edtUpdateFullName.setVisibility(View.INVISIBLE);
        btnUpdate.setVisibility(View.INVISIBLE);

        btnFind.setOnClickListener(v -> {
            find();
        });

        btnUpdate.setOnClickListener(v -> {
            update();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    private void find() {

        String searchID = edtUpdate.getText().toString(), newID = edtUpdateID.getText().toString(), newFullName = edtUpdateFullName.getText().toString();
        for (StudentModal str : MainActivity.StudentModalArrayList)
        {
            if(Objects.equals(str.getID(), searchID))
            {
                edtUpdateID.setVisibility(View.VISIBLE);
                edtUpdateFullName.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.VISIBLE);

                MainActivity.greenToastMessage.setText("ID Found!");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(MainActivity.greenToastLayout);
                toast.show();
                break;
            }
            else{
                edtUpdateID.setVisibility(View.INVISIBLE);
                edtUpdateFullName.setVisibility(View.INVISIBLE);
                btnUpdate.setVisibility(View.INVISIBLE);

                MainActivity.blueToastMessage.setText("Id Not Found!");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(MainActivity.blueToastLayout);
                toast.show();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void update() {
        String findID = edtUpdate.getText().toString(), newID = edtUpdateID.getText().toString(), newFullName = edtUpdateFullName.getText().toString();

        for(int i = 0; i < MainActivity.StudentModalArrayList.size(); i++)
        {
            if(Objects.equals(MainActivity.StudentModalArrayList.get(i).getID(), findID))
            {
                MainActivity.StudentModalArrayList.get(i).setID(newID);
                MainActivity.StudentModalArrayList.get(i).setFullName(newFullName);

                save();

                MainActivity.greenToastMessage.setText("Updated Successfully");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(MainActivity.greenToastLayout);
                toast.show();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void save()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.STUD_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        MainActivity.gson = new Gson();
        String json = MainActivity.gson.toJson(MainActivity.StudentModalArrayList);
        editor.putString(MainActivity.STUD_PREF_KEY, json);
        editor.apply();
    }
}