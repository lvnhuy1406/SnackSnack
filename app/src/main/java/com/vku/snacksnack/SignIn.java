package com.vku.snacksnack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.vku.snacksnack.Common.Common;
import com.vku.snacksnack.Model.Users;

import info.hoang8f.widget.FButton;

public class SignIn extends AppCompatActivity {

    EditText edtPhone, edtPassword;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);

        btnSignIn = (FButton)findViewById(R.id.btnSignIn);

        // Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_users = database.getReference("Users");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                mDialog.setMessage("Xin vui lòng chờ...");
                mDialog.show();

                table_users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Check if user not exist in database
                        if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            // Get user information
                            mDialog.dismiss();

                            Users users = dataSnapshot.child(edtPhone.getText().toString()).getValue(Users.class);
                            users.setPhone(edtPhone.getText().toString()); // Set Phone

                            if (users.getPassword().equals(edtPassword.getText().toString())) {
                                Intent intentHome = new Intent(SignIn.this, Home.class);
                                Common.currentUser = users;
                                startActivity(intentHome);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Đăng nhập không thành công!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            mDialog.dismiss();

                            Toast.makeText(SignIn.this, "Người dùng không tồn tại trong CSDL", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}