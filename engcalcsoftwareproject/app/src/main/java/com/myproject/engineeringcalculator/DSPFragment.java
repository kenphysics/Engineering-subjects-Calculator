package com.myproject.engineeringcalculator;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DSPFragment extends ListFragment{
    private String[] functons={"linear convolution","circular convolution","DFT","IDFT","FFT","IFFT"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> x=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,functons);
        setListAdapter(x);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Fragment frag=null;
        switch(position)
        {
            case 1:
                frag=new DSPcconvFragment();
                break;
            case 2:
                frag=new DSPdftFragment();
                break;
            case 3:
                frag=new DSPidftFragment();
                break;
            case 4:
                frag=new DSPfftFragment();
                break;
            case 5:
                frag=new DSPifftFragment();
                break;
            default:
                frag=new DSPconvFragment();
                break;
        }
        FragmentTransaction ft=getFragmentManager().beginTransaction();
        ft.replace(R.id.temp_frame,frag,"current_displayed_fragment");
        ft.addToBackStack(null);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }
}