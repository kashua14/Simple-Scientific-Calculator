package com.kashua14.scientificcalc.Adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kashua14.scientificcalc.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mRegNo;
    private ArrayList<String> mName;

    public RecyclerViewAdapter(Context context,ArrayList<String> regNos, ArrayList<String> names){
        mRegNo = regNos;
        mName = names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.regNo.setText(mRegNo.get(position));
        holder.name.setText(mName.get(position));
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, mName.get(position), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mRegNo.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView regNo, name;
        LinearLayout parentLayout;

        ViewHolder(View itemView){
            super(itemView);
            regNo = itemView.findViewById(R.id.regNo);
            name = itemView.findViewById(R.id.name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
