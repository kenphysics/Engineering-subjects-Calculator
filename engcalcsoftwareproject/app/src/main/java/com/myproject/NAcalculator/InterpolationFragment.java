package com.myproject.NAcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.myproject.engineeringcalculator.R;

public class InterpolationFragment extends Fragment {
    private String methods[]={"lagrange","newtons divided difference"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_interpolation_na,container,false);
        ArrayAdapter<String> x=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,methods);
        ListView listView=(ListView)v.findViewById(R.id.method_selector_inter);
        listView.setAdapter(x);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x=(TextView)getView().findViewById(R.id.method_text_inter);
                x.setText(methods[(int)id]);
                ListView y=(ListView)getView().findViewById(R.id.method_selector_inter);
                y.setVisibility(View.GONE);
                switch((int)id)
                {
                    case 0: lagrangemethod(); break;
                    case 1: newtonmethod(); break;
                }
            }
        });
        return(v);
    }
    private void lagrangemethod()
    {
        LinearLayout x=(LinearLayout)getView().findViewById(R.id.lagrange_frame);
        x.setVisibility(View.VISIBLE);
        Button ok1=(Button)getView().findViewById(R.id.lagrange_ok1);
        ok1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generatelagrange();
            }
        });
    }
    private void newtonmethod()
    {

    }
    private void generatelagrange()
    {

    }
    private void generatenewton()
    {

    }
    private void computelagrange()
    {

    }
    private void computenewton()
    {

    }
}
