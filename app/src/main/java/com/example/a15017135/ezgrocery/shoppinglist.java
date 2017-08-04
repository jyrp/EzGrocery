package com.example.a15017135.ezgrocery;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class shoppinglist extends AppCompatActivity{
    ArrayAdapter<String> aaItems;
    Button btnAdd;
    Button btnDelete;
    Button btnReset;
    EditText etDetail;
    EditText etName;
    ListView lvItem;
    String itemPosition;
    String folderLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppinglist);

        folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GroceryList";
        File folder = new File(folderLocation);
        if (folder.exists() == false) {
            boolean result = folder.mkdir();
            if (result == true) {
                Log.d("File Read/Write", "Folder created");
            }
        }

        etName = ((EditText)findViewById(R.id.etName));
        etDetail = ((EditText)findViewById(R.id.etDetail));
        lvItem = ((ListView)findViewById(R.id.lv));
        aaItems = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList());
        lvItem.setAdapter(aaItems);
        btnAdd = ((Button)findViewById(R.id.btnAdd));
        btnDelete = ((Button)findViewById(R.id.btnDelete));
        btnReset = ((Button)findViewById(R.id.btnReset));
        lvItem.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
                itemPosition= position + "";
                Toast.makeText(getApplicationContext(),"You have selected" + itemPosition,Toast.LENGTH_LONG).show();
            }

        });
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                String name = shoppinglist.this.etName.getText().toString();
                String detail = shoppinglist.this.etDetail.getText().toString();
                String str = "Item Name: " + name + " \n Detail: " + detail;
              aaItems.add(str);
             aaItems.notifyDataSetChanged();


                File targetFile = new File(folderLocation, "list.txt");

                try {
                    FileWriter writer = new FileWriter(targetFile, true);
                    writer.write(str + "\n");
                    writer.flush();
                    writer.close();
                } catch (Exception e) {
                    Toast.makeText(shoppinglist.this, "Failed to add!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
        this.btnDelete.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View onClick) {
                Integer intPos = Integer.parseInt(itemPosition);
                String selectedItem = aaItems.getItem(intPos);
                aaItems.remove(selectedItem);
               aaItems.notifyDataSetChanged();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View onClick) {
            aaItems.clear();
            aaItems.notifyDataSetChanged();

                File targetFile = new File(folderLocation, "list.txt");

                if (targetFile.exists() == true) {
                    targetFile.delete();
                }
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();

        File targetFile = new File(folderLocation, "list.txt");

        if (targetFile.exists() == true) {
            String data = "";
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null) {
                    data += line + "\n";
                    line = br.readLine();
                }
                br.close();
                reader.close();
            } catch (Exception e) {
                Toast.makeText(shoppinglist.this, "Failed to read!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            aaItems.add(data);
            aaItems.notifyDataSetChanged();
        }

    }
}
