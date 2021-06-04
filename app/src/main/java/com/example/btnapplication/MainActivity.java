package com.example.btnapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (quantity<=99) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else {
            Toast.makeText(getApplicationContext(), "The quantity cannot exceed 100! Quantity must be less than 100.", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void decrement(View view){
        if(quantity>=2) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else {
            Toast.makeText(getApplicationContext(), "The quantity cannot be less than 1! Quantity must be 1 or more.", Toast.LENGTH_LONG).show();
            return;
        }
    }

    public String whippedCream(View view){
        String verdict1;
        CheckBox cb = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        if(cb.isChecked()== true) {
            verdict1 = "true";
        }
        else{
            verdict1="false";
        }
        return verdict1;
    }

    public String chocolateTop(View view){
        String verdict2;
        CheckBox cb1 = (CheckBox) findViewById(R.id.chocolate_check_box);
        if(cb1.isChecked()== true) {
            verdict2 = "true";
        }
        else{
            verdict2="false";
        }
        return verdict2;
    }

    public void submitOrder(View view) {
        int price=calculatePrice();
        String hasWhippedCream=whippedCream(findViewById(R.id.whipped_cream_check_box));
        String hasChocolate=chocolateTop(findViewById(R.id.chocolate_check_box));
        EditText enterName= (EditText) findViewById(R.id.name_edit_text);
        //EditText enterEmail = (EditText) findViewById(R.id.email_edit_text);
        String name = enterName.getText().toString();
        //String email = enterEmail.getText().toString();
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        //displayMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("text/html");
        //intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your coffee order!");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        startActivity(intent);
    }

    private int calculatePrice(){
        int base= 5;
        String toppingWhipped= whippedCream(findViewById(R.id.whipped_cream_check_box));
        String toppingChocolate = chocolateTop(findViewById(R.id.chocolate_check_box));

        if (toppingWhipped == "true"){
            base+=1;
        }

        if (toppingChocolate == "true"){
            base+=2;
        }

        return quantity*base;
    }



    private String createOrderSummary(int price, String hasWhippedCream, String hasChocolate, String name){
        String priceMessage = "Name: "+ name;
        priceMessage+="\nAdd whipped cream? "+ hasWhippedCream ;
        priceMessage+="\nAdd chocolate? "+ hasChocolate;
        priceMessage+="\nQuantity: "+quantity;
        priceMessage+="\nTotal = $"+ price;
        priceMessage+="\nThank you!";
        return priceMessage;

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    /*private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/
}