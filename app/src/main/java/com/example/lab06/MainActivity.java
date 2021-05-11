package com.example.lab06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private EditText editValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            TextView tvName = view.findViewById(R.id.tvName);
            editValue.setText(tvName.getText().toString());
        });

        adapter = new CustomAdapter(this, R.layout.list_item);
        listView.setAdapter(adapter);

        editValue = findViewById(R.id.editValue);

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            String value = editValue.getText().toString();

            if(!value.trim().isEmpty()) {
                adapter.add(value);
            }
        });

        findViewById(R.id.btnRemove).setOnClickListener(v -> {
            String value = editValue.getText().toString();

            if(!value.trim().isEmpty())
                adapter.remove(value);
        });
    }
}