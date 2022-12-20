package com.example.assigntry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DisplayActivity extends AppCompatActivity {

    private RecyclerView studentRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Button btnBack = findViewById(R.id.btnBack);
        studentRV = findViewById(R.id.studentRV);

        buildRecyclerView();

        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }

    private void buildRecyclerView() {
        MainActivity.adapter = new StudentAdapter(MainActivity.StudentModalArrayList, this);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        studentRV.setHasFixedSize(true);
        studentRV.setLayoutManager(manager);
        studentRV.setAdapter(MainActivity.adapter);
    }
}