package dz.esi.commerce.mshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEduitText;
    private CheckBox rememberMeCheckBox;
    private TextView forgotPasswordTextView;
    private Button loginButton;
    private Button signUpButton;
    private ProgressDialog progressDialog;
    protected String enteredUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        progressDialog = new ProgressDialog(loginActivity.this);
        progressDialog.setIndeterminate(true);

        usernameEditText = (EditText) findViewById(R.id.edittext_username);
        passwordEduitText = (EditText) findViewById(R.id.edittext_password);
        rememberMeCheckBox = (CheckBox) findViewById(R.id.checkbox_remember_me);
        forgotPasswordTextView = (TextView) findViewById(R.id.textview_forgot_password);
        loginButton = (Button) findViewById(R.id.button_login);
        signUpButton = (Button) findViewById(R.id.button_sign_up);

        loginButton.setOnClickListener(onClickLoginButtonListener);
        signUpButton.setOnClickListener(onClickSignUpButtonListener);
        forgotPasswordTextView.setOnClickListener(onClickForgotPasswordTextViewListener);


        }


    private void goToSignupActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void goToWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    View.OnClickListener onClickLoginButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String username = usernameEditText.getText().toString();
            String password = passwordEduitText.getText().toString();

            if(username.equals("") || password.equals("")){
                Toast.makeText(loginActivity.this, "Username or password must be filled", Toast.LENGTH_LONG).show();
                return;
            }
            if(password.length() <= 5){
                Toast.makeText(loginActivity.this, "Password length must be greater than 5 caracters", Toast.LENGTH_LONG).show();
                return;
            }
            if(username.length() <= 3 ){
                Toast.makeText(loginActivity.this, "Username length must be greater than 3 caracters", Toast.LENGTH_LONG).show();
                return;
            }
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
         /*   usernameEditText.setText("");
            passwordEduitText.setText("");*/


        }
    };

    View.OnClickListener onClickSignUpButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goToSignupActivity();
        }
    };

    View.OnClickListener onClickForgotPasswordTextViewListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
            builder.setTitle("Warning");
            builder.setIcon(R.drawable.ic_error);
            builder.setMessage("Not implement");
            builder.setPositiveButton("OK", null);
            builder.show();
        }
    };











}