package com.legionhamz.bsbank;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity
{
    EditText moneyTxt;
    TextView screenMny;
    Button arttirBtn, azaltBtn;
    private FirebaseAuth fbAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fbAuth = FirebaseAuth.getInstance();

        arttirBtn = findViewById(R.id.arttir_bt);
        azaltBtn = findViewById(R.id.azalt_bt);
        moneyTxt = findViewById(R.id.moneyTxt);
        screenMny = findViewById(R.id.screenmny);

        Random rnd = new Random();

        int gosterMny = rnd.nextInt(10001);

        screenMny.setText(String.valueOf(gosterMny));

    }

    public void arttir(View view)
    {
        if(moneyTxt.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Miktari giriniz",Toast.LENGTH_SHORT).show();
        }
        else
        {
            int deger = Integer.parseInt(screenMny.getText().toString()) + Integer.parseInt(moneyTxt.getText().toString());
            screenMny.setText( String.valueOf(deger) ) ;
        }
    }
    public void azalt(View view)
    {
        if(moneyTxt.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Miktari giriniz",Toast.LENGTH_SHORT).show();
        }
        else
        {
            int deger = Integer.parseInt(screenMny.getText().toString()) - Integer.parseInt(moneyTxt.getText().toString());
            screenMny.setText( String.valueOf(deger) ) ;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.cikis_yap)
        {
            fbAuth.signOut();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}

