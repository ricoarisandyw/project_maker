package com.mrabid.pro_maker.Fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrabid.pro_maker.Activity.AddCorporationActivity;
import com.mrabid.pro_maker.Activity.SignInActivity;
import com.mrabid.pro_maker.Activity.myCorporationActivity;
import com.mrabid.pro_maker.Model.Corporation;
import com.mrabid.pro_maker.Project;
import com.mrabid.pro_maker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProfilFragment extends Fragment {

    Gson gson;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addCorporation = (Button)getActivity().findViewById(R.id.btn_profil_addCorporation);
        addCorporation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddCorporationActivity.class);
                startActivity(i);
            }
        });
        Button myCorporation = (Button)getActivity().findViewById(R.id.btn_profil_myCorporation);
        myCorporation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), myCorporationActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profil_fragment, container, false);

    }
}
