package com.example.mortgagecalculatorandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/* JavaFX CSS - Leave this comment until you have at least create one rule which uses -fx-Property */
/**
Assignment:

Create a JavaFX program that allows the user to input the following:

Total Gross Income

Total Monthly debt

Mortgage Interest Rate

Term (have this as a drop down, 10 years, 15 years, 30 years)

Down payment (numeric field, optional... default zero)

Calculate the following:

Housing payment only (18% of gross income):

Housing payment + other obligations (36% of gross income)

Maximum payment allowed (lower of the two payments)

Amount of mortgage that can be financed.

 

If the user changes any of the fields, re-calculate the amounts (change term, re-calculate amount, change down payment, re-calculate...)
**/

public class MortgageActivity extends Activity {
	
	TextView tv1;
	TextView tv2;
	TextView tv3;
	TextView tv4;
	
	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	EditText et5;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mortgage);
        
        tv1 = (TextView)findViewById(R.id.textView1);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        
        et1 = (EditText)findViewById(R.id.editText1);
        et2 = (EditText)findViewById(R.id.editText2);
        et3 = (EditText)findViewById(R.id.editText3);
        et4 = (EditText)findViewById(R.id.editText4);
        et5 = (EditText)findViewById(R.id.editText5);
    }
    
    public void calculate(View v){
    	String t1 = et1.getText().toString();
    	String t2 = et2.getText().toString();
    	String t3 = et3.getText().toString();
    	String t4 = et4.getText().toString();
    	String t5 = et5.getText().toString();
    	
    	double grossIncome = 0;
    	double monthlyDebt = 0;
    	double prefTerm = 0;
    	double mortgageInterest = 0;
    	double downPayment = 0;
    	try{
        	grossIncome = Double.parseDouble(t1);
        	monthlyDebt = Double.parseDouble(t2);
        	mortgageInterest = Double.parseDouble(t3);
        	prefTerm = Double.parseDouble(t4);
        	downPayment = Double.parseDouble(t5);
        	
    	}catch(Exception e){
    		
    	}
    	//calc
    	double housingPayment = 0;
    	double totalMonthlyObligation = 0;
    	double maxAllowed = 0;
    	//double mortgageFinanced = 0;
    	double monthlyRate = 0;
    	double numberOfPayments = 0;
    	double presentValue = 0;
    	double x = 0;
    	double b = 0;
    	
    	
    	housingPayment = (grossIncome/12.0*0.18);
    	totalMonthlyObligation = (grossIncome/12.0*0.36)- monthlyDebt;
    	
    	if(totalMonthlyObligation < housingPayment){
    		maxAllowed = totalMonthlyObligation;
    	}
    	else {
    		maxAllowed = housingPayment;
    	}
    	
    	monthlyRate = (mortgageInterest/12.0);
    	numberOfPayments = (prefTerm*12.0);
    	
    	x = (1.0 + monthlyRate);
    	b = Math.pow(x, numberOfPayments);
    	presentValue = maxAllowed*((b-1.0)/(monthlyRate*b));
    	
    	tv1.setText("Housing Payment (only) 18% = " + housingPayment);
    	tv2.setText("Housing Payment+ Monthly Obligation = " + totalMonthlyObligation);
    	tv3.setText("Maximum Payment Allowed = " + maxAllowed);
    	tv4.setText("Amount of Mortgage Financed = " +  presentValue);
    }
}
