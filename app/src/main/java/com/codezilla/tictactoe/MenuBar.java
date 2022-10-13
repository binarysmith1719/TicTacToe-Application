package com.codezilla.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuBar extends AppCompatActivity {
    Button btn , btn2 ,btn7;
    public static final String exName="inhuman";
    public static int st=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bar);
        btn=findViewById(R.id.button2);
        btn2=findViewById(R.id.button3);
        btn7=findViewById(R.id.button7);
        Log.d("bug", "onCreate: hahahahdsdsdsdsdsd uphaha");
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("bug", "onCreate: hahahah uphaha");
                Intent intent = new Intent(MenuBar.this,WithPc.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void onclick(View view)
    {
        Button clicKed = (Button) view;
        int id1 = R.id.button4;
        if(clicKed.getId()==id1){
            st=1;
        }
        else
            st=0;
        Intent intent = new Intent(MenuBar.this,MainActivity.class);
        intent.putExtra(exName,st);
        startActivity(intent);
    }
}