package com.myproject.engineeringcalculator;
public class complex {
    private double real,img;
    public static double pi=3.14159265358979323846264338327950288419716939937510;
    public complex(){
        real=img=0.0;
    }
    public complex(double real)
    {
        this.real=real;
        this.img=0.0;
    }
    public complex(double real,double img)
    {
        this.img=img;
        this.real=real;
    }
    public complex add(complex a)
    {
        return(new complex(this.real+a.real,this.img+a.img));
    }
    public complex sub(complex a)
    {
        return(new complex(this.real-a.real,this.img-a.img));
    }
    public complex mul(complex a)
    {
        return(new complex(this.real*a.real-this.img*a.img,this.real*a.img+a.real*this.img));
    }
    public double arg()
    {
        return(Math.atan2(this.img,this.real));
    }
    public double mod()
    {
        return (Math.sqrt(real*real+img*img));
    }
    public double modsquare()
    {
        return(real*real+img*img);
    }
    public complex conjugate()
    {
        return(new complex(this.real,-this.img));
    }
    public complex div(double x)
    {
        return(new complex(this.real/x,this.img/x));
    }
    public complex div(complex a)
    {
        complex temp=a.conjugate();
        temp=this.mul(temp);
        temp=temp.div(a.modsquare());
        return(temp);
    }
    public static void initiatearray(complex x[])
    {
        for(int i=0;i<x.length;i++)
            x[i]=new complex();
    }
    @Override
    public String toString() {
        return real+" + "+img+"i";
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImg(double img) {
        this.img = img;
    }
    public static complex twiddle(int k,int n,int N)
    {
        return(new complex(Math.cos(2*pi*k*n/N),-Math.sin(2*pi*k*n/N)));
    }
}
