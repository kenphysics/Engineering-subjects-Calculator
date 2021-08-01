package com.myproject.engineeringcalculator;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DSPdftFragment extends Fragment implements View.OnClickListener {
    Button enter,okbutton;
    EditText xlength;
    private int N;
    private complex x[],y[];
    LinearLayout input_linearlayout,output_linearlayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_dsp_ft,container,false);
        enter=(Button)v.findViewById(R.id.ft_enter_button);
        okbutton=(Button)v.findViewById(R.id.ok_ft);
        xlength=(EditText)v.findViewById(R.id.ft_N);
        enter.setOnClickListener(this);
        okbutton.setOnClickListener(this);
        input_linearlayout=(LinearLayout)v.findViewById(R.id.input_display_ft);
        output_linearlayout=(LinearLayout)v.findViewById(R.id.result_display_ft);
        N=0;
        return(v);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.ft_enter_button:
                if (xlength.getText().toString().equals("")) {
                    Toast x = Toast.makeText(getActivity().getApplicationContext(), R.string.unentered_inputs, Toast.LENGTH_LONG);
                    x.show();
                } else {
                    String temp;
                    temp = xlength.getText().toString();
                    N = Integer.parseInt(temp);
                    x = new complex[N];
                    y = new complex[N];
                    complex.initiatearray(x);
                    complex.initiatearray(y);
                    enter.setVisibility(View.GONE);
                    xlength.setFocusable(false);
                    xlength.setClickable(false);
                    input_linearlayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.ok_ft:
                okbutton.setVisibility(View.GONE);
                getinput();
                compute();
                output_linearlayout.setVisibility(View.VISIBLE);
                commitresult();
                break;
        }
    }
    private void getinput()
    {
        EditText realx,imgx;
        realx=(EditText)getView().findViewById(R.id.real_1_ft);
        imgx=(EditText)getView().findViewById(R.id.img_1_ft);
        String xr[],xi[];
        xr=(realx.getText().toString()).split(" ");
        xi=(imgx.getText().toString()).split(" ");
        for(int i=0;i<xr.length && i<N;i++) {
            if(!xr[i].equals(""))
                x[i].setReal(Double.parseDouble(xr[i]));
        }
        for(int i=0;i<xi.length && i<N;i++) {
            if(!xi[i].equals(""))
                x[i].setImg(Double.parseDouble(xi[i]));
        }
    }
    private void compute()
    {
        for(int k=0;k<N;k++)
        {
            for(int n=0;n<N;n++)
            {
                y[k]=y[k].add(x[n].mul(complex.twiddle(k,n,N)));
            }
        }
    }
    private void commitresult()
    {
        ArrayAdapter<complex> temp = new ArrayAdapter<complex>(getActivity().getApplicationContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, y) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(getResources().getColor(android.R.color.black));
                return textView;
            }
        };
        LinearLayout templist=(LinearLayout)getView().findViewById(R.id.ft_result);
        for(int i=0;i<temp.getCount();i++)
        {
            View v=temp.getView(i,null,templist);
            templist.addView(v);
        }
    }
}
