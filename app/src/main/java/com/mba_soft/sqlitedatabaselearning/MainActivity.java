package com.mba_soft.sqlitedatabaselearning;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "SQLDbL";
    final String DB_NAME = "MyDb";

    EditText mEditTextName, mEditTextEmail, mEditTextAddition;
    Button mButtonAdd, mButtonRead, mButtonClear;

    DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "App: MainActivity-onCreate");

        mEditTextName = findViewById(R.id.etName);
        mEditTextEmail = findViewById(R.id.etEmail);
        mEditTextAddition = findViewById(R.id.etAddition);

        mButtonAdd = findViewById(R.id.btnAdd);
        mButtonAdd.setOnClickListener(this);

        mButtonRead = findViewById(R.id.btnRead);
        mButtonRead.setOnClickListener(this);

        mButtonClear = findViewById(R.id.btnClear);
        mButtonClear.setOnClickListener(this);

        mDBHelper = new DBHelper(this, DB_NAME, null, 1);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "App: MainActivity-onClick. btnAdd clicked");
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG, "App: MainActivity-onClick. btnRead clicked");
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "App: MainActivity-onClick. btnClear clicked");
                break;

            default:
                Log.d(LOG_TAG, "App: MainActivity-onClick. default msg");

        }

    }
}
