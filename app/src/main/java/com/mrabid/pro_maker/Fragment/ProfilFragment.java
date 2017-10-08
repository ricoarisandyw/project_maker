package com.mrabid.pro_maker.Fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.mrabid.pro_maker.Activity.AddCorporationActivity;
import com.mrabid.pro_maker.Activity.myCorporationActivity;
import com.mrabid.pro_maker.R;

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
        return inflater.inflate(R.layout.fragment_profil, container, false);

    }
}
