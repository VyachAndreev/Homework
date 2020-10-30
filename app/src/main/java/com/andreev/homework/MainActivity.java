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

    public static final int MAX_DEFAULT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            goList(MAX_DEFAULT);
        }
    }

    @Override
    public void onItemClicked(int item){
        goDetails(item);
    }

    public void goList(int max) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, ListFragment.newInstance(max))
                .commitAllowingStateLoss();
    }

    private void goDetails(int item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment.newInstance(item))
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}