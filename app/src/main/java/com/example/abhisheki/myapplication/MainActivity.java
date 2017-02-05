package com.example.abhisheki.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> mobileArray = new ArrayList<String>();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, mobileArray);


        ListView listView = (ListView) findViewById(R.id.mainlist);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Add:
                CreateDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void CreateDialog()
    {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.mydialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setView(dialogView);

        // Setting Dialog Title
        alertDialog.setTitle("Enter The Details");
        final EditText nme = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText phn = (EditText) dialogView.findViewById(R.id.editPhone);
        final EditText brth = (EditText) dialogView.findViewById(R.id.editBirth);

        alertDialog.setPositiveButton("Save",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        // Write your code here to execute after dialog
                        String data="";
                        try {
                             data = nme.getText().toString() + "\n" +
                                    phn.getText().toString() + "\n" +
                                    brth.getText().toString();
                        }
                        catch(Exception exp)
                        {
                            Toast.makeText(MainActivity.this,exp.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        mobileArray.add(data);
                        adapter.notifyDataSetChanged();

                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,	int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getApplicationContext(), "You clicked on Cancel", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });



        // Showing Alert Message
        alertDialog.show();


    }
}
