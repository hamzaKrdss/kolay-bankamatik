package com.legionhamz.bsbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    EditText emailTxt, passTxt;
    Button girisbtn;
    private FirebaseAuth fbAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailTxt = findViewById(R.id.emailTxt);
        passTxt = findViewById(R.id.passwordTxt);
        girisbtn = findViewById(R.id.girisbtn);

        fbAuth = FirebaseAuth.getInstance();
        FirebaseUser person = fbAuth.getCurrentUser();



        if(person != null)
        {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    public void girisYap(View view)
    {
        fbAuth.signInWithEmailAndPassword(emailTxt.getText().toString(), passTxt.getText().toString()).addOnCompleteListener(task -> {

            if(task.isSuccessful())
           {
                String person = fbAuth.getCurrentUser().getEmail().toString();
                String personToName = "";

                for(int i = 0; i < person.length(); i++)
                {
                   if(person.charAt(i) != '@')
                   {
                       personToName += person.charAt(i);

                   }
                   else
                   {
                       break;
                   }
                }

                Toast.makeText(this, "Hoşgeldin: " + personToName, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
           }

        }).addOnFailureListener(exception ->{
            Toast.makeText(this, exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        });

    }

    public void kayitOl(View view)
    {
        int baslangıcMaas = 10;
        String getEmail = emailTxt.getText().toString();
        String getPass = passTxt.getText().toString();
        String getEmailToName = "";

        for(int i = 0; i < getEmail.length(); i++)
        {
            if(getEmail.charAt(i) != '@')
            {
                getEmailToName += getEmail.charAt(i);

            }
            else
            {
                break;
            }
        }
        String getName = getEmailToName;

        fbAuth.createUserWithEmailAndPassword(getEmail,getPass).addOnCompleteListener(task -> {
           if(task.isSuccessful())
           {
               Intent intent = new Intent(this,HomeActivity.class);
               startActivity(intent);
               finish();
           }

        }).addOnFailureListener(exception ->{
            Toast.makeText(MainActivity.this, "Ters giden bir şeyler var", Toast.LENGTH_SHORT).show();
        });

    }

}





