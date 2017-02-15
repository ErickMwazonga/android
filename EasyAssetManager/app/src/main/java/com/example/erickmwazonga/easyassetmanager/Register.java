package com.example.erickmwazonga.easyassetmanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etComfirmPassword;
    private TextInputLayout input_layout_name, input_layout_email, input_layout_password, input_layout_comfirmPassword;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_password = (TextInputLayout) findViewById(R.id.input_layout_password);
        input_layout_comfirmPassword = (TextInputLayout) findViewById(R.id.input_layout_comfirmPassword);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etComfirmPassword = (EditText) findViewById(R.id.etComfirmPassword);

        btnSignUp = (Button) findViewById(R.id.btn_signup);

        etName.addTextChangedListener((TextWatcher) new MyTextWatcher(etName));
        etEmail.addTextChangedListener(new MyTextWatcher(etEmail));
        etPassword.addTextChangedListener(new MyTextWatcher(etPassword));
        etComfirmPassword.addTextChangedListener(new MyTextWatcher(etComfirmPassword));
    }

    public void registerUser() {

        final String name = etName.getText().toString();
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString();
        final String comfirmPassword = etComfirmPassword.getText().toString();
        //final int age=Integer.parseInt(etage.getText().toString());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        Intent intent = new Intent(Register.this, Login.class);
                        Register.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                        builder.setMessage("Register Failed \n The user already exists")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RegisterRequest registerRequest = new RegisterRequest(name, email, password, comfirmPassword, responseListener);
        RequestQueue queue = Volley.newRequestQueue(Register.this);
        queue.add(registerRequest);

    }

    public void userReg(View view) {
        if(submitForm()){
            registerUser();
        }else{
            Toast.makeText(this,"Please Ensure Everything is oukay",Toast.LENGTH_LONG).show();
        }

    }

    //Validating form
    private Boolean submitForm() {

        if (!validateName()) {
            return false;
        }

        if (!validateEmail()) {
            return false;
        }

        if (!validatePassword()) {
            return false;
        }

        if (!validatecPassword()) {
            return false;
        }
        return true;
    }

    private boolean validateName() {
        if (etName.getText().toString().trim().isEmpty()) {
            input_layout_name.setError(getString(R.string.err_msg_name));
            requestFocus(etName);
            return false;
        } else {
            input_layout_name.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validateEmail() {
        String email = etEmail.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            input_layout_email.setError(getString(R.string.err_msg_email));
            requestFocus(etEmail);
            return false;
        } else {
            input_layout_email.setErrorEnabled(false);
            return true;
        }

    }

    private boolean validatePassword() {
        if (etPassword.getText().toString().trim().isEmpty()) {
            input_layout_password.setError(getString(R.string.err_msg_password));
            requestFocus(etPassword);
            return false;
        } else {
            input_layout_password.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatecPassword() {

        if (!etPassword.getText().toString().equals(etComfirmPassword.getText().toString())) {
            input_layout_comfirmPassword.setError(getString(R.string.err_msg_cpassword));
            requestFocus(etComfirmPassword);
            return false;
        } else{
            input_layout_comfirmPassword.setErrorEnabled(false);
            return true;
        }
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

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.etName:
                    validateName();
                    break;
                case R.id.etEmail:
                    validateEmail();
                    break;
                case R.id.etPassword:
                    validatePassword();
                    break;
                case R.id.etComfirmPassword:
                    //validatecPassword();
                    break;
            }
        }
    }

    class RegisterRequest extends StringRequest {

        private Map<String, String> params;


        public RegisterRequest(String name, String email, String password, String comfirmPassword, Response.Listener<String> listener) {
            super(Method.POST, Config.REGISTER_REQUEST_URL, listener, null);

            params = new HashMap<>();
            params.put("name", name);
            params.put("email", email);
            params.put("password", password);
            params.put("comfirmPassword", comfirmPassword);
            //params.put("age",age + "");
        }

        public Map<String, String> getParams() {
            return params;
        }
    }
}
