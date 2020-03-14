package com.phong.bttrl_datientrinh_asynctasklayketquatrave;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnOK;
    EditText edtNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStart();
            }
        });
    }

    private void doStart() {
        //tạo 1 tiến trình
        MyAsyncTask myAsyncTask = new MyAsyncTask(MainActivity.this);
        //lấy giá trị nhập từ EditText:
        int number = Integer.parseInt(edtNumber.getText().toString());
        //thực thi tiến trình với đối số truyền vào là number
        //nó được dùng trong đối số của doInBackground
        myAsyncTask.execute(number);
    }

    private void addControls() {
        btnOK = findViewById(R.id.btnOK);
        edtNumber = findViewById(R.id.edtNumber);
    }
}
