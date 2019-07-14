package com.ahmadfauzirahman.sakato.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import com.ahmadfauzirahman.sakato.R;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class KontrakFragment extends Fragment {


    public KontrakFragment() {
        // Required empty public constructor
    }

    View view;
    JSONArray jsonArray;
    JSONObject data;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kontrak, container, false);




        return view;
    }

}
