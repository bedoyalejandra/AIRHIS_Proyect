package com.example.airhis_proyect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;

public class CreateCount extends AppCompatActivity {

    ImageView btnCreate;
    EditText name;
    EditText lastName;
    EditText email;
    EditText password;
    EditText confirmPassword;

    private FirebaseAuth mAuth;
    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_count);
        initComponents();
        mAuth = FirebaseAuth.getInstance();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(confirmPassword.getText().toString())) {
                    createAccount(name.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createAccount(final String name, final String lastName, final String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
                            DatabaseReference currentUserDB = mDatabase.child(mAuth.getCurrentUser().getUid());
                            currentUserDB.child("Correo").setValue(email);
                            currentUserDB.child("Nombre").setValue(name);
                            currentUserDB.child("Apellido").setValue(lastName);
                            Intent I= new Intent(getApplicationContext(), Menu.class);
                            I.putExtra("mAuth", mAuth.getCurrentUser().getUid());
                            startActivity(I);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CreateCount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void updateUI(FirebaseUser user) {
        BreakIterator textSignInStatus = null;
        if (user != null) {
            //textSignInStatus.setText("User ID: " + user.getUid());
        } else {
            //textSignInStatus.setText("Error: sign in failed.");
        }
    }

    private void initComponents(){
        btnCreate = findViewById(R.id.btnCreate);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
    }
}
