package com.usa.retotiendavirtual.ui.sucursales;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.usa.retotiendavirtual.R;
import com.usa.retotiendavirtual.ui.sucursales.adapter.SucursalAdapter;
import com.usa.retotiendavirtual.ui.sucursales.model.SucursalModel;

import java.util.LinkedList;
import java.util.List;

public class SucursalesFragment extends Fragment {

    RecyclerView RecyclerViewSucursales;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sucursales, container, false);

        //Sucursales images
        List<SucursalModel> sucursales = new LinkedList<>();
        sucursales.add(new SucursalModel(R.drawable.banner_sucursal_01));
        sucursales.add(new SucursalModel(R.drawable.banner_sucursal_02));
        sucursales.add(new SucursalModel(R.drawable.banner_sucursal_03));
        sucursales.add(new SucursalModel(R.drawable.banner_sucursal_04));

        RecyclerViewSucursales = view.findViewById(R.id.RecyclerViewSucursales);
        RecyclerViewSucursales.setLayoutManager(new LinearLayoutManager(getContext()));
        SucursalAdapter adapter = new SucursalAdapter(sucursales);
        RecyclerViewSucursales.setAdapter(adapter);


        return view;
    }
}