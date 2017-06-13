package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

import static java.lang.Math.E;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.StrictMath.sqrt;

/**
 * Created by barto on 5/26/2017.
 */
public class Generator {
    public Generator(){

    }

    public Generator (double x, double y, List<Double> list){

        a = x;
        b = y;
        this.list = list;
    }
    protected double a;
    protected double b;
    protected double c;
    protected double d;
    protected double x;
    private List<Double> list = new ArrayList<Double>();
    Random random = new Random();


    public List<Double> trojkatny(int n){

        for(int i=0; i<=n; i++){

            c = random.nextDouble();
            d=random.nextDouble();
            c=c*(b/2+a/2);
            d=d*(b/2+a/2);
            c=c-a/2;
            d=d-a/2;
            x = c+d;
            System.out.println(c);
            System.out.println(d);
            System.out.println(x);
            list.add(x);

        }

        return this.list;
    }
    public List<Double> gamma(int n){
        double R;
        double Z;


        //generating pseudorandom variable from gamma distribution


        for(int i=0; i<=n; i++){

            if(a>1){
                do{
                    c = random.nextDouble();
                    d=random.nextDouble();
                    double U = d*log((c/(1-c)));
                    x=a*pow(E, U);
                    Z = pow(c,2)*b;
                   R=b+c*U-x;


                }
                while(!(R>=log(Z)));

            }
            else{
                double t = 0.07+0.75 *(1-a);
                double p = t/(t+a*pow(E, (-1)*t));
                boolean e;

                do{
                    c = random.nextDouble();
                    d=random.nextDouble();
                    if(c*(1/p)<=1){
                        x = t*pow(c*(1/p), 1/a);
                        double h = pow((1/E),x);
                        e = (d<=h);
                    }
                    else
                    { x = -log((t/a)*(1/p-c));
                        e=(d<=pow((x/t),(a-1)));
                    }    }
                while(!e);
            }
            list.add(x*b);
        }




        return this.list;
    }
    //generating pseudorandom variable from exp distribution
    public List<Double> exp(int n){
        for(int i = 0; i<=n; i++){

            c = random.nextDouble();
       x = log(c);
       list.add(x*a+b);


        }
        return this.list;
    }
    public List<Double> Rou(int n){
        for(int i =0; i<=n; i++){
        do{

            c = random.nextDouble();
            d=random.nextDouble();
            d=d*2*sqrt(2/E);
            d=d-sqrt(2/E);
            if(c!=0)
                x = d/c;
        }
        while(c*c > pow(1/E, (x*x)/2));
        list.add(x*b + a);}
        return this.list;
    }
    private void couchy(){

        c = random.nextDouble();
        d=random.nextDouble();
        c=c*2-1;
        d=d*2-1;

    }
    public List<Double> couchy(int n){
        for(int i = 0; i<=n; i++){

         this.couchy();
         if((c + 0.27324)*(1 + pow(d, 2)) > 1.27324 ){
            this.couchy();
        }
        else {
             list.add(d*a+b);
         }
        }
        return this.list;
    }
    public List<Double> lognorm(int n){
        for (int i=0; i<=n; i++){
            list.add(pow(E, Rou(1).get(0)));
        }
        return this.list;
    }

}
