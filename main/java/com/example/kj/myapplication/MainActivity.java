package com.example.kj.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements  View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private EditText Etext_name,Etext_email,Etext_paw;
    private  TextView  text_email,text_name,text_login,text_wei,text_q,
            text_email2;
    private RadioGroup r_sex,r_boy,r_girl;
    private Button btn_submit;
    private CheckBox sing,dance,run;
    private String name,email,paw,sex,hobbys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private  void init(){
        Etext_name=findViewById(R.id.Etext_name);
        Etext_email=findViewById(R.id.Etext_email);
        Etext_paw=findViewById(R.id.Etext_paw);

        text_login=findViewById(R.id.text_login);
        text_wei=findViewById(R.id.text_wei);
        text_q=findViewById(R.id.text_q);
        text_email=findViewById(R.id.text_email);
        text_name=findViewById(R.id.text_name);
        text_email2=findViewById(R.id.text_paw);
        btn_submit=findViewById(R.id.btn_submit);
        r_sex=findViewById(R.id.r_sex);
        run=findViewById(R.id.run);
        sing=findViewById(R.id.sing);
        dance=findViewById(R.id.dance);


        run.setOnCheckedChangeListener(this);
        sing.setOnCheckedChangeListener(this);
        dance.setOnCheckedChangeListener(this);
        text_email.setOnClickListener(this);
        text_q.setOnClickListener(this);
        text_wei.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        hobbys=new String();


        r_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.r_boy:
                        sex="男";
                        break;
                    case R.id.r_girl:
                        sex="女";
                        break;
                }
            }
        });
    }
    private void getDate(){
        name=Etext_name.getText().toString().trim();
        email=Etext_email.getText().toString().trim();
        paw=Etext_paw.getText().toString().trim();
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.text_q:
                Toast.makeText(MainActivity.this,"正在跳转QQ登录。。。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_wei:
                Toast.makeText(MainActivity.this,"正在跳转微信登录。。。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.text_email:
                Toast.makeText(MainActivity.this,"正在跳转邮箱登录。。。",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_submit:
                getDate();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(MainActivity.this,"请输入名字",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this,"请输入邮箱",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(paw)){
                    Toast.makeText(MainActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(sex)){
                    Toast.makeText(MainActivity.this,"请选择性别",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"姓名："+name+"\n\r"+"邮箱："+email+"\n\r"+"密码："+paw+"\n\r"+"性别:"+sex+"\n\r"+"兴趣："+hobbys+
                            "\n\r"+"已提交",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String motion=buttonView.getText().toString();
        if (isChecked){
            if (!hobbys.contains(motion)){
                hobbys=hobbys+motion;
            }
        }else {
            if (hobbys.contains(motion)){
                hobbys=hobbys.replace(motion,"");
            }
        }
    }
}
