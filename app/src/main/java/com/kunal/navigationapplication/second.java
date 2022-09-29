package com.kunal.navigationapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class second extends Fragment {

    TextView textView;
    View view;
    ListView list;
    ArrayAdapter arrayAdapter;
    String[] arr = {"animal", "bike", "car", "food", "mobile"};

    myinterface m;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_second, container, false);

        list = view.findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter(getContext(), R.layout.myfile, R.id.city, arr);
        list.setAdapter(arrayAdapter);

        m = (myinterface) getContext();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                m.tranfer(arr[i]);
            }
        });

        textView = view.findViewById(R.id.text);
        if (getArguments() != null) {
            String str = getArguments().getString("item");
            textView.setText(str);
        }

        return view;
    }

}