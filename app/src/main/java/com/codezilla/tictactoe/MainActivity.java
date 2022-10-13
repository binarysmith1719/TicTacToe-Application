package com.codezilla.tictactoe;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
//    public static final String exName="inhuman";
    public int mat[][]={{0,0,0},
                        {0,0,0},
                        {0,0,0}};
    public int row[]={1,1,1};
    public int col[]={1,1,1};
    public int cross[]={1,1,1}; //the third 1 is useless just for using the reset
    public int count=0,flip=0,p;
    public TextView txt;

    public int visited[][]={{0,0,0},
                            {0,0,0},
                            {0,0,0}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.textView4);
        Intent intent = getIntent();
//        int ft = intent.getIntExtra(exName,0);
        int ft=MenuBar.st;
        flip=p=ft;
    }
    public void LetsGo(View view) //
    {
        ImageView img = (ImageView) view;
        String tagline=img.getTag().toString();
        int tagNo = Integer.parseInt(tagline);
        int r = (tagNo%100)/10;
        int c = tagNo%10;
        if(visited[r][c]==1)
        {
            Toast.makeText(MainActivity.this, "CAN'T BE CHANGED", Toast.LENGTH_SHORT).show();
            return;
        }
        visited[r][c]=1;
        count++;
       // txt.setText(""+count+"="+flip);
        if(count<=9) {
            if (mat[r][c] == 0 && flip==0) {
                row[r] *= 2;
                col[c] *= 2;
                if (r == c) {
                    cross[0] *= 2;
                }
                if (r==2&&c==0 || r==0&&c==2 || r==1&&c==1) {
                    cross[1] *= 2;
                }
                img.setImageResource(R.drawable.xrs);
                mat[r][c] = 1;
                flip=1;
            } else if ( mat[r][c] == 0 && flip==1) {
                row[r] *= 3;
                col[c] *= 3;
                if (r == c) {
                    cross[0] *= 3;
                }
                if (r==2&&c==0 || r==0&&c==2 || r==1&&c==1) {
                    cross[1] *= 3;
                }
                img.setImageResource(R.drawable.crossed);
                mat[r][c] = 1;
                flip=0;
            }
        }
        if(count<=9 && count>=5)
        {
            if(row[r]==8 || col[c]==8|| cross[0]==8 || cross[1]==8)
            {
//                finish();
                txt.setText("ZERO WON");count=9;
            }
            else if(row[r]==27 || col[c]==27|| cross[0]==27 || cross[1]==27)
            {
                txt.setText("CROSS WON");count=9;
            }
            else if(count==9)
            {
                txt.setText("DRAW");
            }
        }
    }

    public void rePlay(View view)
    {
       for(int i=0;i<=2;i++)
       {
           row[i]=col[i]=cross[i]=1;
           for(int j=0;j<=2;j++) {
               mat[i][j] = 0;
               visited[i][j]=0;
           }
       }
       count=0;
       flip=p;
       txt.setText("");
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView10)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView11)).setImageResource(R.drawable.nully);
    }
}