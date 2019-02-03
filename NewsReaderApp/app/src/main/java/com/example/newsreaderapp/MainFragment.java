package com.example.newsreaderapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    Button goToAccountButton;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button goToAccountButton = (Button) view.findViewById(R.id.goToAccountButton);
        Button goToSettingsButton = (Button) view.findViewById(R.id.goToSettingsButton);

        goToAccountButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.toAccount));
        goToSettingsButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.toSettings));
    }
}
