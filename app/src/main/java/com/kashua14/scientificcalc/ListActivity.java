package com.kashua14.scientificcalc;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kashua14.scientificcalc.Adaptors.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ListActivity extends AppCompatActivity {

    private ArrayList<String> mRegNo = new ArrayList<>();
    private ArrayList<String> mName = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Add a back button in the top bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // Change the title in the Action bar of the Activity
        getSupportActionBar().setTitle("List Activity");

        // Create array lists of students with their RegNos.
        initStudents();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        return true;
    }

    private void initStudents(){
        mRegNo.add("S17B23/314");
        mName.add("Kasasira Joshua");

        mRegNo.add("S17B23/315");
        mName.add("Opio Alvin");

        mRegNo.add("S17B23/303");
        mName.add("Ologe Micheal");

        mRegNo.add("S17B23/312");
        mName.add("Kimanuka Derrick");

        mRegNo.add("S17B23/304");
        mName.add("Mawanda Peter");

        mRegNo.add("S17B23/606");
        mName.add("Ssemazzi Justus");

        mRegNo.add("S17B23/502");
        mName.add("Emejje C. Arthur");

        mRegNo.add("S17B23/434");
        mName.add("Nabafu Erinor");

        mRegNo.add("S17B23/389");
        mName.add("Owach Emmanuel");

        mRegNo.add("S17B23/478");
        mName.add("Oboch Samuel");

        mRegNo.add("S17B23/142");
        mName.add("Mudola Calvin");

        mRegNo.add("S17B23/534");
        mName.add("Muchwa Christopher");

        initRecyclerView();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, mRegNo, mName);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
