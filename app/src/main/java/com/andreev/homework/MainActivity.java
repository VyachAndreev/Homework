package com.andreev.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.andreev.homework.fragments.DetailsFragment;
import com.andreev.homework.fragments.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListFragment.IListener {

    public static int detail;
    public static List<String> data = new ArrayList<>();
    private static final String DATA = "DATA";
    private static final String DETAIL = "DETAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int max;

        if(savedInstanceState != null){
            max = savedInstanceState.getInt(DATA);
            detail = savedInstanceState.getInt(DETAIL);
            Log.i("notSaved", "false");
        } else {
            max = 100;
            detail = 0;
            Log.i("notSaved", "true");
        }

        if (detail != 0) {
            goDetails();
        } else{
            goList();
        }

        data.clear();

        for (int i = 0; i < max; i++) {
            data.add(Integer.toString(i + 1));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClicked(int item){
        detail = item;
        goDetails();
    }

    public void goList() {
        detail = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ListFragment())
                .commitAllowingStateLoss();
    }

    public void goDetails() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new DetailsFragment())
                .commitAllowingStateLoss();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DATA, data.size());
        outState.putInt(DETAIL, detail);
        Log.i("notSaved", "saved");
    }


}