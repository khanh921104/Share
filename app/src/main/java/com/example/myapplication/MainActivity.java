package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_dangnhap;
    EditText text_nganhang;
    EditText text_taikhoan;
    CheckBox checkbox;

    SharedPreferences share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        anhxa();

        // Use getSharedPreferences to retrieve shared preferences
        share = getSharedPreferences("data", MODE_PRIVATE);

        // Correctly retrieve data using the appropriate keys
        text_nganhang.setText(share.getString("nganhang", ""));
        text_taikhoan.setText(share.getString("taikhoan", ""));
        checkbox.setChecked(share.getBoolean("check", false));

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nganhang = text_nganhang.getText().toString().trim();
                String taikhoan = text_taikhoan.getText().toString().trim();

                SharedPreferences.Editor editor = share.edit();
                if (checkbox.isChecked()) {
                    // Save data if the checkbox is checked
                    editor.putString("nganhang", nganhang);
                    editor.putString("taikhoan", taikhoan);
                    editor.putBoolean("check", true);
                } else {
                    // Remove saved data if checkbox is unchecked
                    editor.remove("nganhang");
                    editor.remove("taikhoan");
                    editor.remove("check");
                }
                editor.commit(); // Commit changes
            }
        });
    }

    private void anhxa() {
        btn_dangnhap = findViewById(R.id.button);
        text_nganhang = findViewById(R.id.editTextText2);
        text_taikhoan = findViewById(R.id.editTextText3);
        checkbox = findViewById(R.id.checkbox);
    }
}
