package com.myproject.engineeringcalculator;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.objecthunter.exp4j.function.*;
import net.objecthunter.exp4j.*;
import java.math.BigDecimal;
public class ScientificCalculatorFragment extends Fragment implements View.OnClickListener{
    private boolean DEGmode,SHFTmode,HYPEmode;
    private Button deg,shift,back,clc,pi,hyp,e,logab,sin,cos,tan,log,ln,ainverse,asquare,acube,apower,aroot,one,two,three,four,five,six,seven,eight,nine,zero,openbracket,closebracket,multiply,divide,plus,minus,decimal,answer,equalsign,comma;
    private EditText answer_text,expression_text;
    private BigDecimal answer_to_display;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_scientific_calculator, container, false);
        //getting references to all buttons and the expression and answer field
        deg=(Button)v.findViewById(R.id.mode_button);
        shift=(Button)v.findViewById(R.id.shift_button);
        back=(Button)v.findViewById(R.id.back_button);
        clc=(Button)v.findViewById(R.id.clear_button);
        pi=(Button)v.findViewById(R.id.pi_button);
        hyp=(Button)v.findViewById(R.id.hyp_button);
        comma=(Button)v.findViewById(R.id.comma_button);
      //  i=(Button)v.findViewById(R.id.iota_button);
        e=(Button)v.findViewById(R.id.exp_button);
        logab=(Button)v.findViewById(R.id.logab_button);
        sin=(Button)v.findViewById(R.id.sin_button);
        cos=(Button)v.findViewById(R.id.cos_button);
        tan=(Button)v.findViewById(R.id.tan_button);
        log=(Button)v.findViewById(R.id.log_button);
        ln=(Button)v.findViewById(R.id.ln_button);
        ainverse=(Button)v.findViewById(R.id.inverse_button);
        asquare=(Button)v.findViewById(R.id.square_button);
        acube=(Button)v.findViewById(R.id.cube_button);
        apower=(Button)v.findViewById(R.id.power_button);
        aroot=(Button)v.findViewById(R.id.sqrt_button);
        one=(Button)v.findViewById(R.id.one_button);
        two=(Button)v.findViewById(R.id.two_button);
        three=(Button)v.findViewById(R.id.three_button);
        four=(Button)v.findViewById(R.id.four_button);
        five=(Button)v.findViewById(R.id.five_button);
        six=(Button)v.findViewById(R.id.six_button);
        seven=(Button)v.findViewById(R.id.seven_button);
        eight=(Button)v.findViewById(R.id.eight_button);
        nine=(Button)v.findViewById(R.id.nine_button);
        zero=(Button)v.findViewById(R.id.zero_button);
        openbracket=(Button)v.findViewById(R.id.open_button);
        closebracket=(Button)v.findViewById(R.id.close_button);
        multiply=(Button)v.findViewById(R.id.mul_button);
        divide=(Button)v.findViewById(R.id.div_button);
        plus=(Button)v.findViewById(R.id.plus_button);
        minus=(Button)v.findViewById(R.id.minus_button);
        decimal=(Button)v.findViewById(R.id.dot_button);
        answer=(Button)v.findViewById(R.id.answer_button);
        equalsign=(Button)v.findViewById(R.id.equal_button);
        answer_text=(EditText)v.findViewById(R.id.answer_text);
        expression_text=(EditText)v.findViewById(R.id.expression_text);
        //setting thier onclicklistener
        deg.setOnClickListener(this);
        shift.setOnClickListener(this);
        back.setOnClickListener(this);
        clc.setOnClickListener(this);
        pi.setOnClickListener(this);
        hyp.setOnClickListener(this);
      //  i.setOnClickListener(this);
        e.setOnClickListener(this);
        logab.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        log.setOnClickListener(this);
        ln.setOnClickListener(this);
        ainverse.setOnClickListener(this);
        asquare.setOnClickListener(this);
        acube.setOnClickListener(this);
        apower.setOnClickListener(this);
        aroot.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        openbracket.setOnClickListener(this);
        comma.setOnClickListener(this);
        closebracket.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        decimal.setOnClickListener(this);
        answer.setOnClickListener(this);
        equalsign.setOnClickListener(this);
        answer_to_display=new BigDecimal(0);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DEGmode=true; SHFTmode=false; HYPEmode=false;
    }
    @Override
    public void onClick(View v) {
        StringBuffer expressionstring=new StringBuffer(expression_text.getText().toString());
        boolean temp=true;
        switch(v.getId())
        {
            case R.id.one_button:
                expressionstring.append(1);
                expression_text.setText(expressionstring);
                break;
            case R.id.two_button:
                expressionstring.append(2);
                expression_text.setText(expressionstring);
                break;
            case R.id.three_button:
                expressionstring.append(3);
                expression_text.setText(expressionstring);
                break;
            case R.id.four_button:
                expressionstring.append(4);
                expression_text.setText(expressionstring);
                break;
            case R.id.five_button:
                expressionstring.append(5);
                expression_text.setText(expressionstring);
                break;
            case R.id.six_button:
                expressionstring.append(6);
                expression_text.setText(expressionstring);
                break;
            case R.id.seven_button:
                expressionstring.append(7);
                expression_text.setText(expressionstring);
                break;
            case R.id.eight_button:
                expressionstring.append(8);
                expression_text.setText(expressionstring);
                break;
            case R.id.nine_button:
                expressionstring.append(9);
                expression_text.setText(expressionstring);
                break;
            case R.id.zero_button:
                expressionstring.append(0);
                expression_text.setText(expressionstring);
                break;
            case R.id.clear_button:
                expression_text.setText("");
                answer_text.setText("");
                break;
            case R.id.plus_button:
                expressionstring.append('+');
                expression_text.setText(expressionstring);
                break;
            case R.id.minus_button:
                expressionstring.append('-');
                expression_text.setText(expressionstring);
                break;
            case R.id.mul_button:
                expressionstring.append('*');
                expression_text.setText(expressionstring);
                break;
            case R.id.div_button:
                expressionstring.append('/');
                expression_text.setText(expressionstring);
                break;
            case R.id.open_button:
                expressionstring.append('(');
                expression_text.setText(expressionstring);
                break;
            case R.id.close_button:
                expressionstring.append(')');
                expression_text.setText(expressionstring);
                break;
            case R.id.dot_button:
                expressionstring.append('.');
                expression_text.setText(expressionstring);
                break;
            case R.id.log_button:
                if(SHFTmode)
                    expressionstring.append("10^");
                else
                    expressionstring.append("log10(");
                expression_text.setText(expressionstring);
                break;
            case R.id.ln_button:
                if(SHFTmode)
                    expressionstring.append("exp(");
                else
                    expressionstring.append("ln(");
                expression_text.setText(expressionstring);
                break;
            case R.id.mode_button:
                if(DEGmode) {
                    DEGmode = false;
                    deg.setText("RAD");
                }
                else
                {
                    DEGmode=true;
                    deg.setText("DEG");
                }
                break;
            case R.id.shift_button:
                if(SHFTmode)
                {
                    SHFTmode=false;
                    shift.setTextColor(getResources().getColor(android.R.color.black));
                }
                else
                {
                    SHFTmode=true;
                    shift.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                }
                temp=false;
                break;
            case R.id.hyp_button:
                if(HYPEmode)
                {
                    HYPEmode=false;
                    hyp.setTextColor(getResources().getColor(android.R.color.black));
                }
                else
                {
                    HYPEmode=true;
                    hyp.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                }
                temp=false;
                break;
            case R.id.sin_button:
                if(HYPEmode && SHFTmode)
                {
                    expressionstring.append("asinh(");
                }
                else if(HYPEmode && !SHFTmode)
                {
                    expressionstring.append("sinh(");
                }
                else if(!HYPEmode && SHFTmode)
                {
                    expressionstring.append("asin(");
                }
                else
                {
                    expressionstring.append("sin(");
                }
                expression_text.setText(expressionstring);
                break;
            case R.id.cos_button:
                if(HYPEmode && SHFTmode)
                {
                    expressionstring.append("acosh(");
                }
                else if(HYPEmode && !SHFTmode)
                {
                    expressionstring.append("cosh(");
                }
                else if(!HYPEmode && SHFTmode)
                {
                    expressionstring.append("acos(");
                }
                else
                {
                    expressionstring.append("cos(");
                }
                expression_text.setText(expressionstring);
                break;
            case R.id.tan_button:
                if(HYPEmode && SHFTmode)
                {
                    expressionstring.append("atanh(");
                }
                else if(HYPEmode && !SHFTmode)
                {
                    expressionstring.append("tanh(");
                }
                else if(!HYPEmode && SHFTmode)
                {
                    expressionstring.append("atan(");
                }
                else
                {
                    expressionstring.append("tan(");
                }
                expression_text.setText(expressionstring);
                break;
           /* case R.id.iota_button:
                expressionstring.append("*i");
                expression_text.setText(expressionstring);
                break;*/
            case R.id.exp_button:
                expressionstring.append("exp(1)");
                expression_text.setText(expressionstring);
                break;
            case R.id.back_button:
                if(expressionstring.length()!=0)
                expressionstring.deleteCharAt(expressionstring.length()-1);
                expression_text.setText(expressionstring);
                break;
            case R.id.answer_button:
                expressionstring.append(answer_to_display);
                expression_text.setText(expressionstring);
                break;
            case R.id.equal_button:
                if(!expressionstring.equals(""))
                    evaluate(expressionstring.toString());
                break;
            case R.id.pi_button:
                expressionstring.append("P");
                expression_text.setText(expressionstring);
                break;
            case R.id.inverse_button:
                expressionstring.append("^-1");
                expression_text.setText(expressionstring);
                break;
            case R.id.logab_button:
                expressionstring.append("logb(");
                expression_text.setText(expressionstring);
                break;
            case R.id.comma_button:
                expressionstring.append(",");
                expression_text.setText(expressionstring);
                break;
            case R.id.square_button:
                expressionstring.append("^2");
                expression_text.setText(expressionstring);
                break;
            case R.id.cube_button:
                if(SHFTmode)
                    expressionstring.append("^(1/3)");
                else
                    expressionstring.append("^3");
                expression_text.setText(expressionstring);
                break;
            case R.id.power_button:
                if(SHFTmode)
                    expressionstring.append("^(1/");
                else
                    expressionstring.append("^");
                expression_text.setText(expressionstring);
                break;
            case R.id.sqrt_button:
                expressionstring.append("sqrt(");
                expression_text.setText(expressionstring);
                break;
        }
        if (temp) {
            HYPEmode = false;
            SHFTmode = false;
            hyp.setTextColor(getResources().getColor(android.R.color.black));
            shift.setTextColor(getResources().getColor(android.R.color.black));
        }
    }
    private void evaluate(String x)
    {
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
        Expression expression;
        try {
            Functions.mode=DEGmode;
            expression = new ExpressionBuilder(x).functions(logb,ln,log10,asinh,acosh,atanh).variable("P").build().setVariable("P",3.14159265358979323846264338);
            answer_to_display = new BigDecimal(expression.evaluate());
            answer_text.setText(answer_to_display.toString());
        }
        catch (Exception e)
        {
            Toast temp=Toast.makeText(getActivity().getApplicationContext(),"Invalid input",Toast.LENGTH_LONG);
            temp.show();
        }
    }
}