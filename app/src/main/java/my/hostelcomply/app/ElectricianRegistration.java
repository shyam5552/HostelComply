package my.hostelcomply.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ElectricianRegistration extends AppCompatActivity{
    TextInputLayout Fname,Lname,Email,Pass,cpass,mobileno;
    Button signup, Emaill, Phone;
    FirebaseAuth FAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String fname,lname,emailid,password,confpassword,mobile;
    String role="Electrician";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrician_registration);

        Fname = (TextInputLayout)findViewById(R.id.Firstname);
        Lname = (TextInputLayout)findViewById(R.id.Lastname);
        Email = (TextInputLayout)findViewById(R.id.Email);
        Pass = (TextInputLayout)findViewById(R.id.Pwd);
        cpass = (TextInputLayout)findViewById(R.id.Cpass);
        mobileno = (TextInputLayout)findViewById(R.id.Mobileno);

        signup = (Button)findViewById(R.id.Signup);
        Emaill = (Button)findViewById(R.id.email);
        Phone = (Button)findViewById(R.id.phone);
        databaseReference = firebaseDatabase.getInstance().getReference("Electrician");
        FAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fname.getEditText().getText().toString().trim();
                lname = Lname.getEditText().getText().toString().trim();
                emailid = Email.getEditText().getText().toString().trim();
                mobile = mobileno.getEditText().getText().toString().trim();
                password = Pass.getEditText().getText().toString().trim();
                confpassword = cpass.getEditText().getText().toString().trim();

                if (isValid()){
                    final ProgressDialog mDialog = new ProgressDialog(ElectricianRegistration.this);
                    mDialog.setCancelable(false);
                    mDialog.setCanceledOnTouchOutside(false);
                    mDialog.setMessage("Registration in progress please wait......");
                    mDialog.show();

                    FAuth.createUserWithEmailAndPassword(emailid,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                String useridd = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                databaseReference = FirebaseDatabase.getInstance().getReference("User").child(useridd);
                                final HashMap<String , String> hashMap = new HashMap<>();
                                hashMap.put("Role",role);
                                databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        HashMap<String , String> hashMap1 = new HashMap<>();
                                        hashMap1.put("Mobile No",mobile);
                                        hashMap1.put("First Name",fname);
                                        hashMap1.put("Last Name",lname);
                                        hashMap1.put("EmailId",emailid);
                                        hashMap1.put("Password",password);
                                        hashMap1.put("Confirm Password",confpassword);

                                        firebaseDatabase.getInstance().getReference("Electrician")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(hashMap1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        mDialog.dismiss();

                                                        FAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful()){
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(ElectricianRegistration.this);
                                                                    builder.setMessage("You Have Registered! Make Sure To Verify Your Email");
                                                                    builder.setCancelable(false);
                                                                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(DialogInterface dialog, int which) {

                                                                            dialog.dismiss();
                                                                            String phonenumber = "+91"+mobile;
                                                                            Intent b = new Intent(ElectricianRegistration.this, ElectricianVerifyPhone.class);
                                                                            b.putExtra("phonenumber", phonenumber);
                                                                            startActivity(b);


                                                                        }
                                                                    });
                                                                    AlertDialog Alert = builder.create();
                                                                    Alert.show();
                                                                }else{
                                                                    mDialog.dismiss();
                                                                    ReusableCodeForAll.ShowAlert(ElectricianRegistration.this,"Error",task.getException().getMessage());
                                                                }
                                                            }
                                                        });

                                                    }
                                                });

                                    }
                                });
                            }
                        }
                    });
                }
//
            }
        });

        Emaill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ElectricianRegistration.this,Electricianlogin.class));
                finish();
            }
        });
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ElectricianRegistration.this,Electricianloginphone.class));
                finish();
            }
        });


    }

    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public boolean isValid(){
        Email.setErrorEnabled(false);
        Email.setError("");
        Fname.setErrorEnabled(false);
        Fname.setError("");
        Lname.setErrorEnabled(false);
        Lname.setError("");
        Pass.setErrorEnabled(false);
        Pass.setError("");
        mobileno.setErrorEnabled(false);
        mobileno.setError("");
        cpass.setErrorEnabled(false);
        cpass.setError("");


        boolean isValid=false,isValidlname=false,isValidname=false,isValidemail=false,isValidpassword=false,isValidconfpassword=false,isValidmobilenum=false;
        if(TextUtils.isEmpty(fname)){
            Fname.setErrorEnabled(true);
            Fname.setError("Enter First Name");
        }else{
            isValidname = true;
        }
        if(TextUtils.isEmpty(lname)){
            Lname.setErrorEnabled(true);
            Lname.setError("Enter Last Name");
        }else{
            isValidlname = true;
        }
        if(TextUtils.isEmpty(emailid)){
            Email.setErrorEnabled(true);
            Email.setError("Email Is Required");
        }else{
            if(emailid.matches(emailpattern)){
                isValidemail = true;
            }else{
                Email.setErrorEnabled(true);
                Email.setError("Enter a Valid Email Id");
            }
        }
        if(TextUtils.isEmpty(password)){
            Pass.setErrorEnabled(true);
            Pass.setError("Enter Password");
        }else{
            if(password.length()<8){
                Pass.setErrorEnabled(true);
                Pass.setError("Password is Weak");
            }else{
                isValidpassword = true;
            }
        }
        if(TextUtils.isEmpty(confpassword)){
            cpass.setErrorEnabled(true);
            cpass.setError("Enter Password Again");
        }else{
            if(!password.equals(confpassword)){
                cpass.setErrorEnabled(true);
                cpass.setError("Password Dosen't Match");
            }else{
                isValidconfpassword = true;
            }
        }
        if(TextUtils.isEmpty(mobile)){
            mobileno.setErrorEnabled(true);
            mobileno.setError("Mobile Number Is Required");
        }else{
            if(mobile.length()<10){
                mobileno.setErrorEnabled(true);
                mobileno.setError("Invalid Mobile Number");
            }else{
                isValidmobilenum = true;
            }
        }


        isValid = (isValidconfpassword && isValidpassword && isValidemail && isValidmobilenum && isValidname && isValidlname) ? true : false;
        return isValid;



    }
}