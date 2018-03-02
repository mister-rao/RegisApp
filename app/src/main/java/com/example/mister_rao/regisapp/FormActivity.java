package com.example.mister_rao.regisapp;

import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.view.WindowManager;
import android.text.TextWatcher;


public class FormActivity extends AppCompatActivity {

    private EditText Name, Email, Username, pass1, pass2;
    private Button SubmitButton;
    private TextInputLayout inputLayoutName, inputLayoutEmail, inputLayoutPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Name = findViewById(R.id.PName);
        Email = findViewById(R.id.Mail);
        Username = findViewById(R.id.UName);
        pass1 = findViewById(R.id.Pass);
        pass2 = findViewById(R.id.PassConf);
        SubmitButton = findViewById(R.id.btn_submit);

        inputLayoutName = findViewById(R.id.input_layout_Uname);
        inputLayoutEmail = findViewById(R.id.input_layout_Mail);
        inputLayoutPassword = findViewById(R.id.input_layout_Pass);

        Username.addTextChangedListener(new MyTextWatcher(Username));
        Email.addTextChangedListener(new MyTextWatcher(Email));
        pass2.addTextChangedListener(new MyTextWatcher(pass1));


        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name1 = Name.getText().toString();
                String Email1 = Email.getText().toString();
                String Username1 = Username.getText().toString();
                String pass11 = pass1.getText().toString();
                String pass21 = pass2.getText().toString();


                if (Name1.equals("") || Email1.equals("") || Username1.equals("") || pass11.equals("") || pass21.equals("")) {
                    Toast.makeText(FormActivity.this, "All Credentials are Mandatory", Toast.LENGTH_SHORT).show();
                }


                if (pass11.length() > 8 && pass11.equals(pass21)) {
                    Intent intent = new Intent(FormActivity.this, MainActivity.class);
                    intent.putExtra("intent_Name", Name1);
                    intent.putExtra("intent_Email", Email1);
                    intent.putExtra("intent_Username", Username1);
                    Toast.makeText(FormActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                } else {
                    if (pass11.length() < 8) {
                        Toast.makeText(FormActivity.this, "Password Too Short!!", Toast.LENGTH_SHORT).show();
                    }
                    if (pass11.length() > 8 && pass11 != pass21) {
                        Toast.makeText(FormActivity.this, "Passwords Don't Match!", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= 26) {
                            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(400, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(500);
                        }
                    }
                }
                submitForm();
            }});
        }


            /**
             * Validating form
             */
            private void submitForm() {
                if (!validateName()) {
                    return;
                }

                if (!validateEmail()) {
                    return;
                }

                if (!validatePassword()) {
                    return;
                }

            }

            private boolean validateName() {
                if (Username.getText().toString().trim().isEmpty()) {
                    inputLayoutName.setError(getString(R.string.err_msg_name));
                    requestFocus(Username);
                    return false;
                } else {
                    inputLayoutName.setErrorEnabled(false);
                }

                return true;
            }




    private boolean validateEmail() {
        String email = Email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(Email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (pass1.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(pass1);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.UName:
                    validateName();
                    break;
                case R.id.Mail:
                    validateEmail();
                    break;
                case R.id.Pass:
                    validatePassword();
                    break;
            }
        }







    }
}
