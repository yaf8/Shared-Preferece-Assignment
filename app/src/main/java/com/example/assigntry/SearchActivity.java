package com.example.assigntry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    private EditText edtSearch;
    private TextView textSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button btnBack = findViewById(R.id.btnBack);
        Button btnSearch = findViewById(R.id.btnSearch);
        edtSearch = findViewById(R.id.edtSearch);
        textSearch = findViewById(R.id.textSearch);

        btnSearch.setOnClickListener(v -> {
            searchText();
        });

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    @SuppressLint("SetTextI18n")
    private void searchText() {

        String searchTxt = edtSearch.getText().toString();
        for (StudentModal str : MainActivity.StudentModalArrayList) {
            if(Objects.equals(str.getID(), searchTxt))
            {
                textSearch.setText("ID :      " + str.getID() + "\n" + "Name :    " + str.getFullName() + "\n\n");

                MainActivity.greenToastMessage.setText("Id Found!");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(MainActivity.greenToastLayout);
                toast.show();
                break;
            }
            else
            {
                MainActivity.blueToastMessage.setText("Id Not Found!");
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.TOP, 0, 150);
                toast.setView(MainActivity.blueToastLayout);
                toast.show();
            }
        }
    }


}