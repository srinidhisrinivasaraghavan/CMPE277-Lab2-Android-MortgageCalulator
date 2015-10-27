package com.assignment.cmpe277.mortgagecalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    //Declare Varialbles
    EditText home_value;
    EditText down_payment;
    EditText apr;
    EditText terms;
    EditText tax_rate;

    EditText total_tax_paid;
    EditText total_interest_paid;
    EditText monthly_payment;
    EditText pay_off_date;

    Button calculate_button;
    Button reset_button;

    double home_value_value,down_payment_value,apr_value,terms_value,tax_rate_value;
    double total_tax_paid_value,total_interest_paid_value,monthly_payment_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Map the UI controls to variables
        home_value = (EditText)findViewById(R.id.editTextHomeValue);
        down_payment=(EditText)findViewById(R.id.editTextDownPayment);
        apr=(EditText)findViewById(R.id.editTextApr);
        terms=(EditText)findViewById(R.id.editTextTerms);
        tax_rate=(EditText)findViewById(R.id.editTextTaxRate);

        total_tax_paid=(EditText)findViewById(R.id.editTextToTalTaxPaid);
        total_interest_paid=(EditText)findViewById(R.id.editTextTotalInterestPaid);
        monthly_payment=(EditText)findViewById(R.id.editTextMonthlyPayment);
        pay_off_date=(EditText)findViewById(R.id.editTextPayOffDate);

        calculate_button = (Button)findViewById(R.id.calculate_button);
        reset_button=(Button)findViewById(R.id.reset_button);

        //Define what should happen on button press
        calculate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // total tax paid calculation
                home_value_value = Double.parseDouble(home_value.getText().toString());
                terms_value = Double.parseDouble(terms.getText().toString());
                tax_rate_value = Double.parseDouble(tax_rate.getText().toString());

                total_tax_paid_value = Math.round(home_value_value * (tax_rate_value/100) * terms_value);
                total_tax_paid.setText(Double.toString(total_tax_paid_value) );

                // total interest paid calculation
                apr_value = Double.parseDouble(apr.getText().toString());
                down_payment_value =Double.parseDouble(down_payment.getText().toString());
                double apr_yearly = apr_value/100;
                double apr_monthly = apr_yearly/12;
                int total_months= (int)terms_value*12;

                double monthly_tax_paid = total_tax_paid_value/total_months;




                double actual_loan = home_value_value-down_payment_value;
                double monthly_mortgage_payment = (actual_loan*apr_monthly*Math.pow((1+apr_monthly),total_months))/(Math.pow((1+apr_monthly),total_months)-1);



                monthly_payment_value= Math.round(monthly_mortgage_payment+monthly_tax_paid);




                total_interest_paid_value =Math.round(monthly_payment_value * total_months - actual_loan - total_tax_paid_value);
                total_interest_paid.setText(Double.toString(total_interest_paid_value));

                //monthly payment
                monthly_payment.setText((Double.toString((monthly_payment_value))));


            }
        });







        //Define what should happen on button press
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               home_value.setText("");
                down_payment.setText("");
                tax_rate.setText("");
                apr.setText("");

                total_interest_paid.setText("");
                pay_off_date.setText("");
                total_interest_paid.setText("");
                monthly_payment.setText("");
            }
        });




























    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
