package com.andreev.homework.fragments;
import com.andreev.homework.MainActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andreev.homework.MainActivity;
import com.andreev.homework.R;

public class DetailsFragment extends Fragment {
    private Button backButton;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_details,
                container,
                false
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backButton = view.findViewById(R.id.button_back);
        textView = view.findViewById(R.id.name);
        textView.setText(Integer.toString(MainActivity.detail));
        String s;
        if (MainActivity.detail % 2 == 0) {
            s = "#FF0000";
        } else {
            s = "#0000FF";
        }
        textView.setTextColor(Color.parseColor(s));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity() != null){
                MainActivity activity = (MainActivity)getActivity();
                activity.goList();
                }
            }
        });
    }
}
