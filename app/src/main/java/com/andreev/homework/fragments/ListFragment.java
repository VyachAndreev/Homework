package com.andreev.homework.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andreev.homework.R;
import com.andreev.homework.adapter.ItemAdapter;
import com.andreev.homework.adapter.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private static final int MAX = 100;
    private int max;
    private static final String EXTRAS_DATA = "DATA";
    private List<String> data = new ArrayList<>();

    public interface IListener {
        void onItemClicked(int item);
    }

    protected IListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (requireActivity() instanceof IListener) {
            listener = (IListener) requireActivity();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            max = savedInstanceState.getInt(EXTRAS_DATA);
        } else {
            max = MAX;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(
                R.layout.fragment_list,
                container,
                false
        );

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addButton = view.findViewById(R.id.add_button);

        data.clear();

        for (int i = 1; i <= max; i++) {
            data.add(Integer.toString(i));
        }
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setAdapter(new ItemAdapter(data, new ItemClickHandler()));
        Configuration configuration = getContext().getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 4));
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.add(Integer.toString(data.size() + 1));
                max++;
                recyclerView.setAdapter(new ItemAdapter(data, new ItemClickHandler()));
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRAS_DATA, max);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        listener = null;
    }

    class ItemClickHandler implements ItemViewHolder.IListener {
        @Override
        public void onItemClicked(int position) {
            final int item = position + 1;

            if (listener != null) {
                listener.onItemClicked(item);
            }
        }
    }
}
