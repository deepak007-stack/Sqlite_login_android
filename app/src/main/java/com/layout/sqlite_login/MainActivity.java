package com.layout.sqlite_login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Database_helper db;
    private EditText name;
    private EditText email;
    private EditText age;
    private EditText password;
    private Button insertData;
    private Button updateData;
    private Button viewData;
    private Button deleteData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database_helper(this);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        password = (EditText) findViewById(R.id.password);
        insertData = (Button) findViewById(R.id.insert_data);
        updateData = (Button) findViewById(R.id.update_data);
        viewData = (Button) findViewById(R.id.view_data);
        deleteData = (Button) findViewById(R.id.delete_data);


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = name.getText().toString();
                String user_email = email.getText().toString();
                String user_age = age.getText().toString();
                String user_password = password.getText().toString();

                boolean result = db.insert_data(user_name, user_email, user_age, user_password);

                if (result) {
                    Toast.makeText(MainActivity.this, "data insert successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "data can not insert", Toast.LENGTH_SHORT).show();
                }

                name.setText("");
                email.setText("");
                age.setText("");
                password.setText("");

            }
        });

        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = name.getText().toString();
                String user_email = email.getText().toString();
                String user_age = age.getText().toString();
                String user_password = password.getText().toString();

                boolean result = db.update_data(user_name, user_email, user_age, user_password);

                if (result == true) {
                    Toast.makeText(MainActivity.this, "update data successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "data cant update", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_email = email.getText().toString();

                boolean result = db.delete_data(user_email);

                if (result) {
                    Toast.makeText(MainActivity.this, "delete data successfull", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "data cant delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = db.get_data();

                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "no entery exists", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();

                while (cursor.moveToNext()) {

                    stringBuffer.append("Name : " + cursor.getString(0) + "\n");
                    stringBuffer.append("Email : " + cursor.getString(1) + "\n");
                    stringBuffer.append("Age : " + cursor.getString(2) + "\n");
                    stringBuffer.append("Password : " + cursor.getString(3) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setCancelable(true);
                builder.setTitle("User Details");
                builder.setMessage(stringBuffer.toString());
                builder.show();


            }
        });

    }


}