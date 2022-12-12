package com.example.assigntry;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private final ArrayList<StudentModal> StudentModalArrayList;

    public StudentAdapter(ArrayList<StudentModal> StudentModalArrayList, Context context) {
        this.StudentModalArrayList = StudentModalArrayList;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {
        StudentModal modal = StudentModalArrayList.get(position);
        holder.StudentID.setText(modal.getID());
        holder.StudentFullName.setText(modal.getFullName());
    }

    @Override
    public int getItemCount() {
        return StudentModalArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView StudentID;
        private final TextView StudentFullName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            StudentID = itemView.findViewById(R.id.textID);
            StudentFullName = itemView.findViewById(R.id.textName);
        }
    }
}

