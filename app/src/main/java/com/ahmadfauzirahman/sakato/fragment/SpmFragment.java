package com.ahmadfauzirahman.sakato.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.ahmadfauzirahman.sakato.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpmFragment extends Fragment {

    public SpmFragment() {
        // Required empty public constructor
    }
    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;
    //    private MapView mapView;
//    private GoogleMap gMap;
    private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyCYCQmU7s8YUEOb7v2-yh2ywAcRMxs6OUI";
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_spm, container, false);
        recyclerView = view.findViewById(R.id.reyspm);
        swipeRefreshLayout = view.findViewById(R.id.swpspm);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // loading = ProgressDialog.show(context,null,"Sedang mendapatkan berita",true,false);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
}
