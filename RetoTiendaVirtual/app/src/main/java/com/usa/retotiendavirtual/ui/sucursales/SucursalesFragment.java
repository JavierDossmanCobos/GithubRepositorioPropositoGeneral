package com.usa.retotiendavirtual.ui.sucursales;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usa.retotiendavirtual.R;

public class SucursalesFragment extends Fragment {

    RecyclerView RecyclerViewSucursales;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sucursales, container, false);

        RecyclerViewSucursales = view.findViewById(R.id.RecyclerViewSucursales);
        RecyclerViewSucursales.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;
    }
}