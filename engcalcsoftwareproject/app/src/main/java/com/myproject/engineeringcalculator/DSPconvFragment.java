package com.myproject.engineeringcalculator;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DSPconvFragment extends Fragment implements View.OnClickListener{
    Button enter,okbutton;
    EditText xlength,hlength;
    private complex x[],h[],y[];
    private int l,m,n;
    LinearLayout input_linearlayout,output_linearlayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_d_s_pconv, container, false);
        enter=(Button)v.findViewById(R.id.conv_enter_button);
        okbutton=(Button)v.findViewById(R.id.ok_conv);
        xlength=(EditText)v.findViewById(R.id.length_1);
        hlength=(EditText)v.findViewById(R.id.length_2);
        input_linearlayout=(LinearLayout)v.findViewById(R.id.input_display_conv);
        output_linearlayout=(LinearLayout)v.findViewById(R.id.result_display_conv);
        enter.setOnClickListener(this);
        okbutton.setOnClickListener(this);
        l=m=n=0;
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.conv_enter_button:
            if (xlength.getText().toString().equals("") || hlength.getText().toString().equals("")) {
                Toast x = Toast.makeText(getActivity().getApplicationContext(), R.string.unentered_inputs, Toast.LENGTH_LONG);
                x.show();
            } else {
                String temp;
                temp = xlength.getText().toString();
                l = Integer.parseInt(temp);
                temp = hlength.getText().toString();
                m = Integer.parseInt(temp);
                n=l+m-1;
                x = new complex[n];
                h = new complex[n];
                y = new complex[n];
                complex.initiatearray(x);
                complex.initiatearray(h);
                complex.initiatearray(y);
                enter.setVisibility(View.GONE);
                xlength.setFocusable(false);
                hlength.setFocusable(false);
                xlength.setClickable(false);
                hlength.setClickable(false);
                input_linearlayout.setVisibility(View.VISIBLE);
            }
            break;
            case R.id.ok_conv:
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
        EditText realx,imgx,realh,imgh;
        realx=(EditText)getView().findViewById(R.id.real_1);
        imgx=(EditText)getView().findViewById(R.id.img_1);
        realh=(EditText)getView().findViewById(R.id.real_2);
        imgh=(EditText)getView().findViewById(R.id.img_2);
        String xr[],xi[],hr[],hi[];
        xr=(realx.getText().toString()).split(" ");
        xi=(imgx.getText().toString()).split(" ");
        hr=realh.getText().toString().split(" ");
        hi=imgh.getText().toString().split(" ");
        for(int i=0;i<xr.length && i<l;i++) {
            if(!xr[i].equals(""))
            x[i].setReal(Double.parseDouble(xr[i]));
        }
        for(int i=0;i<xi.length && i<l;i++) {
            if(!xi[i].equals(""))
            x[i].setImg(Double.parseDouble(xi[i]));
        }
        for(int i=0;i<hr.length && i<m;i++) {
            if(!hr[i].equals(""))
            h[i].setReal(Double.parseDouble(hr[i]));
        }
        for(int i=0;i<hi.length && i<m;i++) {
            if(!hi[i].equals(""))
            h[i].setImg(Double.parseDouble(hi[i]));
        }
    }
    private void compute()
    {
        for(int i=0;i<n;i++)
        {
            for(int k=0;k<=i;k++)
            {
                y[i]=y[i].add(x[k].mul(h[i-k]));
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
        LinearLayout templist=(LinearLayout)getView().findViewById(R.id.conv_result);
        for(int i=0;i<temp.getCount();i++)
        {
            View v=temp.getView(i,null,templist);
            templist.addView(v);
        }
    }
}