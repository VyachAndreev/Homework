package com.andreev.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.andreev.homework.fragments.DetailsFragment;
import com.andreev.homework.fragments.ListFragment;


public class MainActivity extends AppCompatActivity implements ListFragment.IListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            goList();
        }
    }

    @Override
    public void onItemClicked(int item){
        goDetails(item);
    }

    private void goList() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new ListFragment())
                .commitAllowingStateLoss();
    }

    private void goDetails(int item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment.newInstance(item))
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}