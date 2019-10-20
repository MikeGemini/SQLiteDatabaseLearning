package com.mba_soft.sqlitedatabaselearning;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "SQLDbL";
    final String DB_NAME = "MyDb";

    EditText mEditTextName, mEditTextEmail, mEditTextAddition, mEditTextId;
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
        mEditTextId = findViewById(R.id.etId);

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

        // object for the data
        ContentValues contentValues = new ContentValues();
        long mRowId;
        String tableName = "main_table";
        String cNameName = "name";
        String cNameEmail = "email";
        String cNameAddition = "addition";
        String cNameId = "id";


        String mName = mEditTextName.getText().toString();
        String mEmail = mEditTextEmail.getText().toString();
        String mAddition = mEditTextAddition.getText().toString();

        // connecting to the database
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        switch (v.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "App: MainActivity-onClick. btnAdd clicked");

                // putting values in data object
                contentValues.put(cNameName, mName);
                contentValues.put(cNameEmail, mEmail);
                contentValues.put(cNameAddition, mAddition);

                mRowId = db.insert(tableName, null, contentValues);

                Log.d(LOG_TAG, " *** Row inserted, ID = " + mRowId);

                break;
            case R.id.btnRead:
                Log.d(LOG_TAG, "App: MainActivity-onClick. btnRead clicked");

                // query for database
                Cursor cursor = db.query(tableName,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);

                int idColIndex = cursor.getColumnIndex(cNameId);
                int nameCollIndex = cursor.getColumnIndex(cNameName);
                int emailCollIndex = cursor.getColumnIndex(cNameEmail);
                int additionColIndex = cursor.getColumnIndex(cNameAddition);

                if (cursor.moveToNext()) {
                    do {

                        mEditTextId.setText("Raw id is " + cursor.getString(idColIndex));
                        mEditTextName.setText(cursor.getString(nameCollIndex));
                        mEditTextEmail.setText(cursor.getString(emailCollIndex));
                        mEditTextAddition.setText(cursor.getString(additionColIndex));

                        Log.d(LOG_TAG, " *** " +
                                "id = " + cursor.getInt(idColIndex) +
                                "; name = " + cursor.getString(nameCollIndex) +
                                "; email = " + cursor.getString(emailCollIndex) +
                                "; addition = " + cursor.getString(additionColIndex));

                    } while (cursor.moveToNext()); // пока есть следующая запись

                    // do not forget closing the cursor object
                    cursor.close();
                }

                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "App: MainActivity-onClick. btnClear clicked");

                int deletedRows = db.delete(tableName, null, null);

                Log.d(LOG_TAG, " *** Deleted " + deletedRows + " rows");

                break;
            default:
                Log.d(LOG_TAG, "App: MainActivity-onClick. default msg");
        }

        mDBHelper.close();

    }
}
