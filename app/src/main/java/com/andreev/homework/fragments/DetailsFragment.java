package com.andreev.homework.fragments;

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

import com.andreev.homework.R;

public class DetailsFragment extends Fragment {

    private static final String EXTRAS_DATA = "DATA";

    public static DetailsFragment newInstance(int data) {
        final Bundle extras = new Bundle();
        extras.putInt(EXTRAS_DATA, data);

        final DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(extras);

        return fragment;
    }

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

        if (getArguments() != null) {
            int detail = getArguments().getInt(EXTRAS_DATA);
            TextView textView = view.findViewById(R.id.name);
            textView.setText(Integer.toString(detail));
            if (detail % 2 == 0) {
                textView.setTextColor(Color.RED);
            } else {
                textView.setTextColor(Color.BLUE);
            }

            Button backButton = view.findViewById(R.id.button_back);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().onBackPressed();
                }
            });
        }
    }
}
