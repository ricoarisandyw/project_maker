package com.mrabid.pro_maker.Fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mrabid.pro_maker.Project;
import com.mrabid.pro_maker.R;

public class ProfilFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profil_fragment, container, false);

    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        ((Project) getActivity()).setActionBarTitle("MyProfil");
//    }
}
