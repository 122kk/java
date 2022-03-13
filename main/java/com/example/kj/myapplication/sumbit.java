package com.example.kj.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
//
//

public class sumbit extends AppCompatActivity implements View.OnClickListener {

    private EditText E_pwd,E_phone;
    private Button  btn_zhu,btn_deng;
    private String pwd,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sumbit);
        init();
    }
    public  void init(){
        E_phone=findViewById(R.id.E_phone);
        E_pwd=findViewById(R.id.E_pwd);

        btn_zhu=findViewById(R.id.btn_zhu);
        btn_deng=findViewById(R.id.btn_deng);

        btn_zhu.setOnClickListener(this);
        btn_deng.setOnClickListener(this);
    }

    public void getdate(){
        phone=E_phone.getText().toString().trim();
        pwd=E_pwd.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_zhu:
                Intent it=new Intent();
                it.setClass(sumbit.this,MainActivity.class);
                sumbit.this.startActivity(it);
                Toast.makeText(sumbit.this,"正在进入注册界面",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.btn_deng:
                getdate();
                if (TextUtils.isEmpty(phone)){
                    Toast.makeText(sumbit.this,"请输入电话",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pwd)) {
                    Toast.makeText(sumbit.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(sumbit.this, "电话："+phone+"\n\r"+"密码："+pwd+"\n\r"+"欢迎登录", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
