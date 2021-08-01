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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.function.*;
import net.objecthunter.exp4j.*;
import java.math.BigDecimal;
import com.myproject.engineeringcalculator.R;

public class EquationSolverFragment extends Fragment{
    private String methods[]={"bisection","fixed point","newton method"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_equation_solver_na,container,false);
        ListView methodList=(ListView)v.findViewById(R.id.method_selector);
        ArrayAdapter<String> temp=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,methods);
        methodList.setAdapter(temp);
        methodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView x=(TextView)getView().findViewById(R.id.method_text);
                x.setText(methods[(int)id]);
                ListView y=(ListView)getView().findViewById(R.id.method_selector);
                y.setVisibility(View.GONE);
                switch((int)id)
                {
                    case 0: bisectionmethod(); break;
                    case 1: fixedpointmethod(); break;
                    case 2: newtonmethod(); break;
                }
            }
        });
        return(v);
    }
    private void bisectionmethod()
    {
        LinearLayout x=(LinearLayout)getView().findViewById(R.id.bisection_frame);
        x.setVisibility(View.VISIBLE);
        Button ok=(Button)getView().findViewById(R.id.bisection_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computebisection();
            }
        });
    }
    private void fixedpointmethod()
    {
        LinearLayout x=(LinearLayout)getView().findViewById(R.id.fixedpoint_frame);
        x.setVisibility(View.VISIBLE);
        Button ok=(Button)getView().findViewById(R.id.fixed_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computefixedpoint();
            }
        });
    }
    private void newtonmethod()
    {
        LinearLayout x=(LinearLayout)getView().findViewById(R.id.newton_frame);
        x.setVisibility(View.VISIBLE);
        Button ok=(Button)getView().findViewById(R.id.newton_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                computenewton();
            }
        });
    }
    private void computebisection()
    {
        String func,s_a,s_b,s_e,s_n;
        EditText edit_func,edit_a,edit_b,edit_e,edit_n;
        edit_func=(EditText)getView().findViewById(R.id.bisection_function);
        edit_a=(EditText)getView().findViewById(R.id.bisection_a);
        edit_b=(EditText)getView().findViewById(R.id.bisection_b);
        edit_e=(EditText)getView().findViewById(R.id.bisection_e);
        edit_n=(EditText)getView().findViewById(R.id.bisection_N);
        func=edit_func.getText().toString();
        s_a=edit_a.getText().toString();
        s_b=edit_b.getText().toString();
        s_e=edit_e.getText().toString();
        s_n=edit_n.getText().toString();
        if(func.equals("")||s_a.equals("")||s_b.equals("")||s_e.equals("")||s_n.equals(""))
            return;
        double a,b,e;
        long n;
        a=Double.parseDouble(s_a);
        b=Double.parseDouble(s_b);
        e=Double.parseDouble(s_e);
        n=Long.parseLong(s_n);
        double error=b-a;
        Expression ex;
        double x=a+(b-a)/2;
        Function logb = new Function("logb", 2) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]) / Math.log(args[1]);
            }
        };
        Function ln = new Function("ln", 1) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]);
            }
        };
        Function log10 = new Function("log10", 1) {
            @Override
            public double apply(double... args) {
                return Math.log10(args[0]);
            }
        };
        Function asinh = new Function("asinh", 1) {
            @Override
            public double apply(double... args) {
                return (Math.log(args[0]+Math.sqrt(Math.pow(args[0],2)+1)));
            }
        };
        Function acosh = new Function("acosh", 1) {
            @Override
            public double apply(double... args) {
                return (Math.log(args[0]+Math.sqrt(Math.pow(args[0],2)-1)));
            }
        };
        Function atanh = new Function("atanh", 1) {
            @Override
            public double apply(double... args) {
                return(0.5*Math.log((1+args[0])/(1-args[0])));
            }
        };
        for(int i=0;i<n && error>e;i++)
        {
            double fa, fb, fx;
            try {
                ex = new ExpressionBuilder(func).functions(logb,ln,log10,asinh,acosh,atanh).variable("x").build().setVariable("x", a);
                fa = ex.evaluate();
                ex = new ExpressionBuilder(func).functions(logb,ln,log10,asinh,acosh,atanh).variable("x").build().setVariable("x", b);
                fb = ex.evaluate();
                ex = new ExpressionBuilder(func).functions(logb,ln,log10,asinh,acosh,atanh).variable("x").build().setVariable("x", x);
                fx = ex.evaluate();
                if(fa==0.0)
                {
                    x=a; break;
                }
                else if(fb==0.0)
                {
                    x=b; break;
                }
                else if(fx==0.0)
                {
                    break;
                }
                if(fa*fx<0)
                {
                    b=x;
                    x=a+(b-a)/2;
                    error=Math.abs(b-x);
                }
                else if(fa*fb<0)
                {
                    a=x;
                    x=a+(b-a)/2;
                    error=Math.abs(a-x);
                }
            }
            catch (Exception exception)
            {
                Toast temp=Toast.makeText(getActivity().getApplicationContext(),"Some error occoured during execution",Toast.LENGTH_LONG);
                temp.show();
                return;
            }
        }
        String ans=""+x;
        TextView t1,t2;
        t1=(TextView)getView().findViewById(R.id.answer_equation_message);
        t1.setVisibility(View.VISIBLE);
        t2=(TextView)getView().findViewById(R.id.answer_equation_solved);
        t2.setVisibility(View.VISIBLE);
        t2.setText(ans);
    }
    private void computefixedpoint()
    {
        String func,s_x0,s_e,s_n;
        EditText edit_func,edit_x0,edit_e,edit_n;
        edit_func=(EditText)getView().findViewById(R.id.fixed_function);
        edit_x0=(EditText)getView().findViewById(R.id.fixed_x0);
        edit_e=(EditText)getView().findViewById(R.id.fixed_e);
        edit_n=(EditText)getView().findViewById(R.id.fixed_N);
        func=edit_func.getText().toString();
        s_x0=edit_x0.getText().toString();
        s_e=edit_e.getText().toString();
        s_n=edit_n.getText().toString();
        if(func.equals("")||s_x0.equals("")||s_e.equals("")||s_n.equals(""))
            return;
        double x0,e;
        long n;
        x0=Double.parseDouble(s_x0);
        e=Double.parseDouble(s_e);
        n=Long.parseLong(s_n);
        double error=10; //randomaly set to high value
        Expression ex;
        double x1;
        Function logb = new Function("logb", 2) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]) / Math.log(args[1]);
            }
        };
        Function ln = new Function("ln", 1) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]);
            }
        };
        Function log10 = new Function("log10", 1) {
            @Override
            public double apply(double... args) {
                return Math.log10(args[0]);
            }
        };
        Function asinh = new Function("asinh", 1) {
            @Override
            public double apply(double... args) {
                return (Math.log(args[0]+Math.sqrt(Math.pow(args[0],2)+1)));
            }
        };
        Function acosh = new Function("acosh", 1) {
            @Override
            public double apply(double... args) {
                return (Math.log(args[0]+Math.sqrt(Math.pow(args[0],2)-1)));
            }
        };
        Function atanh = new Function("atanh", 1) {
            @Override
            public double apply(double... args) {
                return(0.5*Math.log((1+args[0])/(1-args[0])));
            }
        };
        for(int i=0;i<n && error>e;i++)
        {
            try {
                ex = new ExpressionBuilder(func).functions(logb,ln,log10,asinh,acosh,atanh).variable("x").build().setVariable("x",x0);
                x1=ex.evaluate();
                error=Math.abs(x1-x0);
                x0=x1;
            }
            catch (Exception exception)
            {
                Toast temp=Toast.makeText(getActivity().getApplicationContext(),"Some error occoured during execution",Toast.LENGTH_LONG);
                temp.show();
                return;
            }
        }
        String ans=""+x0;
        TextView t1,t2;
        t1=(TextView)getView().findViewById(R.id.answer_equation_message);
        t1.setVisibility(View.VISIBLE);
        t2=(TextView)getView().findViewById(R.id.answer_equation_solved);
        t2.setVisibility(View.VISIBLE);
        t2.setText(ans);
    }
    private void computenewton()
    {
        String func,deriv,s_x0,s_e,s_n;
        EditText edit_func,edit_deriv,edit_x0,edit_e,edit_n;
        edit_func=(EditText)getView().findViewById(R.id.newton_function);
        edit_deriv=(EditText)getView().findViewById(R.id.newton_derivative);
        edit_x0=(EditText)getView().findViewById(R.id.newton_x0);
        edit_e=(EditText)getView().findViewById(R.id.newton_e);
        edit_n=(EditText)getView().findViewById(R.id.newton_N);
        func=edit_func.getText().toString();
        deriv=edit_deriv.getText().toString();
        s_x0=edit_x0.getText().toString();
        s_e=edit_e.getText().toString();
        s_n=edit_n.getText().toString();
        if(func.equals("")||deriv.equals("")||s_x0.equals("")||s_e.equals("")||s_n.equals(""))
            return;
        double x0,e;
        long n;
        func="x-"+"("+func+")/("+deriv+")";
        x0=Double.parseDouble(s_x0);
        e=Double.parseDouble(s_e);
        n=Long.parseLong(s_n);
        double error=10; //randomaly set to high value
        Expression ex;
        double x1;
        Function logb = new Function("logb", 2) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]) / Math.log(args[1]);
            }
        };
        Function ln = new Function("ln", 1) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]);
            }
        };
        Function log10 = new Function("log10", 1) {
            @Override
            public double apply(double... args) {
                return Math.log10(args[0]);
            }
        };
        Function asinh = new Function("asinh", 1) {
            @Override
            public double apply(double... args) {
                return (Math.log(args[0]+Math.sqrt(Math.pow(args[0],2)+1)));
            }
        };
        Function acosh = new Function("acosh", 1) {
            @Override
            public double apply(double... args) {
                return (Math.log(args[0]+Math.sqrt(Math.pow(args[0],2)-1)));
            }
        };
        Function atanh = new Function("atanh", 1) {
            @Override
            public double apply(double... args) {
                return(0.5*Math.log((1+args[0])/(1-args[0])));
            }
        };
        for(int i=0;i<n && error>e;i++)
        {
            try {
                ex = new ExpressionBuilder(func).functions(logb,ln,log10,asinh,acosh,atanh).variable("x").build().setVariable("x",x0);
                x1=ex.evaluate();
                error=Math.abs(x1-x0);
                x0=x1;
            }
            catch (Exception exception)
            {
                Toast temp=Toast.makeText(getActivity().getApplicationContext(),"Some error occoured during execution",Toast.LENGTH_LONG);
                temp.show();
                return;
            }
        }
        String ans=""+x0;
        TextView t1,t2;
        t1=(TextView)getView().findViewById(R.id.answer_equation_message);
        t1.setVisibility(View.VISIBLE);
        t2=(TextView)getView().findViewById(R.id.answer_equation_solved);
        t2.setVisibility(View.VISIBLE);
        t2.setText(ans);
    }
}