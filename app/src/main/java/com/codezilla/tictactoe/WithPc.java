package com.codezilla.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class WithPc extends AppCompatActivity {

    public int mat[][]={{0,0,0},
            {0,0,0},
            {0,0,0}};//FOR O ITS "1" && FOR X ITS "2"
    public int row[]={1,1,1};
    public int col[]={1,1,1};
    public int cross[]={1,1,1}; //the third 1 is useless just for using the reset
    public int count=0,flip=0,p;
    public TextView txt;

    public int visited[][]={{0,0,0},
            {0,0,0},
            {0,0,0}};
    public int difficulty=2; //Default is hard , 2 means easy
    public int initialrc , unlock=1; //           (Hav to Reset)
    public ImageView imgmtrx[][]; //IMAGE ID MATRIX
    public ImageView imgView;
    public int twowinpos[]={-1,-1,-1,-1,-1,-1}; // Indexes(of row and col) from where there is 2  member in the row or coloumn.
    public int onewinpos[]={-1,-1,-1,-1,-1,-1}; // Indexes(of row and col) from where there is 1 member in the row or coloumn.
    public int emptywinpos[]={-1,-1,-1,-1,-1,-1}; // Indexes(of row and col) from where there is no member in the row or coloumn.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_pc);
        txt=findViewById(R.id.textView41);
        Log.d("bug", "onCreate: hahahah up");
        imgmtrx = new ImageView[3][3];
        //FILLING IMAGE ID MATRIX ****
        imgmtrx[0][0]=findViewById(R.id.imageView51);
        imgmtrx[0][1]=findViewById(R.id.imageView41);
        imgmtrx[0][2]=findViewById(R.id.imageView31);
        imgmtrx[1][0]=findViewById(R.id.imageView81);
        imgmtrx[1][1]=findViewById(R.id.imageView71);
        imgmtrx[1][2]=findViewById(R.id.imageView61);
        imgmtrx[2][0]=findViewById(R.id.imageView111);
        imgmtrx[2][1]=findViewById(R.id.imageView101);
        imgmtrx[2][2]=findViewById(R.id.imageView91);
        //*****************************
        String s=String.valueOf(imgmtrx[0][1]);
        Log.d("bug", " img id = "+s);
        Intent intent = getIntent();

        flip=0;
    }

    public void LetsGo(View view) //
    {

        ImageView img = (ImageView) view;
        String tagline=img.getTag().toString();
        int tagNo = Integer.parseInt(tagline);
        int r = (tagNo%100)/10;
        int c = tagNo%10;

        if(unlock==1)
        {
          if(r==1 && c==1){ initialrc=1;}
          else {initialrc=0;}
          unlock=0;
        }
        //UPDATING THE LAYOUT
        int reslt = UpLayout(r,c,img);
        if(reslt==1)PcTurn();
    }
    public int UpLayout(int r, int c,ImageView img)
    {
        if(visited[r][c]!=0)
        {
            Toast.makeText(WithPc.this, "CAN'T BE CHANGED", Toast.LENGTH_SHORT).show();
            return 0;
        }
        visited[r][c]=1;
        count++;
        String str=img.getTag().toString();
        Log.d("bug", "UpLayout + "+str);
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
                Log.d("bug", "Ulayout +1st"+str);
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
                Log.d("bug", "Ulayout +2nd"+str);
                img.setImageResource(R.drawable.crossed);
                mat[r][c] = 2;
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
        return 1;
    }

    public void PcTurn()
    {
        Log.d("bug", "here1");
        if(initialrc==1) { Rule1();}
        else {Rule2();}
    }

    public void Rule1() // WHEN OPPONENT HAS ENTERED IN THE CENTRE
    {
        int x;
        Log.d("bug", "here11");
       //CHECKING THE POSSIBILITY OF OUR WIN*********
        if(count>=5) {
            int ourpos = winPossibilityCnt(2);
            if (ourpos >= 100) {
                int r = (ourpos % 100) / 10;
                int c = ourpos % 10;

                UpLayout(r, c, imgmtrx[r][c]);
                return ;
            }
            for(int i=0;i<6;i++)
            {twowinpos[i]=onewinpos[i]=emptywinpos[i]=-1;}
        }
        //*******************************************
        if(count==1)
        {   Random rand = new Random();
             x = rand.nextInt(10);
            Log.d("bug", "here12");
            int rw=0,cl=2;
            if(x>0 && x<=2){rw=0;cl=2;}
            if(x>2 && x<=5){rw=0;cl=0;}
            if(x>5 && x<=7){rw=2;cl=0;}
            if(x>7 && x<=10){rw=2;cl=2;}
            UpLayout(rw, cl, imgmtrx[rw][cl]);
            for(int i=0;i<6;i++)
            {twowinpos[i]=onewinpos[i]=emptywinpos[i]=-1;} return ;
        }
        else if(count==3 || count==5 || count==7)
        {
            int winpos= winPossibilityCnt(1); Log.d("bug", "count==3");

            int indx = 100 ;
            int cnt =0, cnt1 =0,cnt0=0;
            for(int i=0;i<6;i++)
            {if(twowinpos[i]!=-1)cnt++;if(onewinpos[i]==-1)cnt1++;if(emptywinpos[i]!=-1)cnt0++;}
            Random rand1 = new Random();
             x = rand1.nextInt(10);

            String s1=String.valueOf(winpos);
               if(winpos>=100)
               {
                   int r = (winpos%100)/10; Log.d("bug", "winpos>=100");
                   int c = winpos%10;
                   indx=100+(r*10)+c;
               }
               else if(winpos==1)
               {
                   Log.d("bug", "winpos==1");
                   if(cnt1>1) {
                       if (x < 5) indx = onewinpos[0];
                       else indx = onewinpos[1];
                   } else{indx=onewinpos[0];}
               }
               else if(winpos==2)
               {
                   Log.d("bug", "winpos==2");
                   int coners[]={-1,-1},ct=0;

                   if(difficulty==3)
                   {
                   for(int i=0;i<6;i++){if(twowinpos[i]!=-1){int a=((twowinpos[i]%100)/10),b=twowinpos[i]%10;
                       if(a==0&&b==0 || a==0&&b==2 || a==2&&b==0 || a==2&&b==2){coners[ct]=twowinpos[i];ct++;}}}
                   if(cnt>1) {if (x < 5) indx = coners[0];
                   else indx = coners[1];}
                   else{indx=coners[0];}
                   }
                   else if(cnt==4)
                   {
                       if(x>0 && x<=2){indx=twowinpos[0];}
                       if(x>2 && x<=5){indx=twowinpos[1];}
                       if(x>5 && x<=7){indx=twowinpos[2];}
                       if(x>7 && x<=10){indx=twowinpos[3];}
                   }
                   else if(cnt>1 && x<5) indx=twowinpos[1];
                   else indx=twowinpos[0];

               }
               else if(winpos==0)
               {
                   Log.d("bug", "winpos==0");
                   if(cnt0>1) { Log.d("bug", "winpos==0");if (x < 5) indx = emptywinpos[0];
                       else indx = emptywinpos[1];} else{indx=emptywinpos[0];}

                   indx=emptywinpos[0];
               }
            int r = (indx%100)/10;
            int c = indx%10;
            UpLayout(r, c, imgmtrx[r][c]);
        }
        for(int i=0;i<6;i++)
        {twowinpos[i]=onewinpos[i]=emptywinpos[i]=-1;}
    }
    int twowinposcnt;
    public void Rule2() //WHEN OPPONENT HAS NOT ENTERED IN THE CENTRE
    {
        //CHECKING THE POSSIBILITY OF OUR WIN*********
             if(count>=5) {
             int ourpos = winPossibilityCnt(2);
             if (ourpos >= 100) {
                int r = (ourpos % 100) / 10;
                int c = ourpos % 10;
                UpLayout(r, c, imgmtrx[r][c]);
                return ;
             }
                 for(int i=0;i<6;i++)
                 {twowinpos[i]=onewinpos[i]=emptywinpos[i]=-1;}
             }
       //***********************************************
             if(count==1)
             {UpLayout(1, 1, imgmtrx[1][1]);return;}


             Random rand1 = new Random();
             Log.d("bug", "Random rule2");
             int x = rand1.nextInt(10);
             twowinposcnt=0;
             int winpos= winPossibilityCnt(1);
             if(count==3 || count==5 || count==7 )//COUNT ==> 3 || 5 || 7
             {
                 int indx=100;
                 if(winpos>=100)
                 {
                     Log.d("bug", " winpos>=100 r2");
                     int r = (winpos % 100) / 10;
                     int c = winpos % 10;
                     indx=100+(r*10)+c;
                 }
                 else if(winpos==2)
                 {
                     Log.d("bug", "winpos ==2 r2");
                     if(twowinposcnt==2 && count==3)
                     {
                             int re=2,ce=0;
                             if(difficulty!=3)
                             {
                                 Random rand2 = new Random();
                                 int y = rand1.nextInt(10);
                                 for(int i=0;i<6;i++){if(twowinpos[i]!=-1){int a=((twowinpos[i]%100)/10),b=twowinpos[i]%10;
                                     if(a==0&&b==0 || a==0&&b==2 || a==2&&b==0 || a==2&&b==2){re=a;ce=b;}}}

                                 if(y<5 && re==ce)re=ce=0;
                             }
                         int rw=0,cl=1;
                         if(x>0 && x<=2){if(difficulty==3){rw=0;cl=1;}else{rw=re;cl=ce;}}
                         if(x>2 && x<=5){rw=1;cl=0;}
                         if(x>5 && x<=7){if(difficulty==3){rw=1;cl=2;}else{rw=ce;cl=re;}}
                         if(x>7 && x<=10){rw=2;cl=1;}
                         indx=100+(rw*10)+cl;
//                         UpLayout(rw, cl, imgmtrx[rw][cl]);
                     }
                     else if (twowinposcnt==2)
                     {
                         if(x<5) indx= twowinpos[0];
                         else indx=twowinpos[1];
                     }
                     else indx= twowinpos[0];

                 }
                 else if(winpos==1)
                 {
                     Log.d("bug", "winpos == 1 r2");
                     int cnt=0;
                     for(int i=0;i<6;i++){if(onewinpos[i]!=-1)cnt++;}
                     if(cnt>1){
                         if(x<5)indx=onewinpos[0];
                         else indx=onewinpos[1];
                     }else indx=onewinpos[0];
                 }
                 else if(winpos==0)
                 {
                     Log.d("bug", "winpos == 0 r2");
                     int cnt=0;
                     for(int i=0;i<6;i++){if(emptywinpos[i]!=-1)cnt++;}
                     if(cnt>1){
                         if(x<5)indx=emptywinpos[0];
                         else indx=emptywinpos[1];
                     }else indx=emptywinpos[0];
                 }
                 int r = (indx%100)/10;
                 int c = indx%10;
                 UpLayout(r, c, imgmtrx[r][c]);
             }
        for(int i=0;i<6;i++)
        {twowinpos[i]=onewinpos[i]=emptywinpos[i]=-1;}
    }

    public int winPossibilityCnt(int px)// WIN POSSIBLITY COUNT
    {
        int n=3;
        if(px==1)n=2;

        int n2=n*n;

        int CHKmate=0;// IF THERE IS A DIRECT WIN BY Px IT WILL RETURN THE POSITION TO HANLDLE IT
        int psblty=0; //possibility count
        int max=-1;
        int cnt1=0,cnt2=0,cnt3=0;
        for(int rowx=0;rowx<=2;rowx++)
        {
            for(int colx=0;colx<=2;colx++)
            {
                psblty=0;
              if(mat[rowx][colx]!=0)
              {continue;}

                String s1=String.valueOf(rowx);
                String s2=String.valueOf(colx);
                Log.d("bug", "here12->>>" +s1+s2);
              if(row[rowx]==n || row[rowx]==n2)
              {
                  psblty++;
                  if(row[rowx]==n2) {
                      return  CHKmate=100+(rowx*10)+(colx);
                  }
              }
              if(col[colx]==n || col[colx]==n2)
              {
                  psblty++;
                  if(col[colx]==n2)
                      return  CHKmate=100+(rowx*10)+(colx);
              }
              if(rowx==0&&colx==2 || rowx==2&&colx==0)
              {
                  if(cross[1]==n){psblty++;}
                  else if(cross[1]==n2)
                          return CHKmate=100+(rowx*10)+(colx);
              }
              if(rowx==colx)
              {
                  if(cross[0]==n) {psblty++;}
                  else if(cross[0]==n2)
                          return CHKmate=100+(rowx*10)+(colx);
              }
              if(psblty>=2)
              {twowinpos[cnt1]=(100+(rowx*10)+colx);cnt1++;
                  Log.d("bug", "easywinpos **");twowinposcnt++;
              }
              else if(psblty==0)
              {emptywinpos[cnt2]=(100+(rowx*10)+colx);cnt2++;
                  Log.d("bug", "emptywinpos o");}
              else if(psblty==1)
              {onewinpos[cnt3]=(100+(rowx*10)+colx);cnt3++;
                  Log.d("bug", "onewinpos o");}

              max=psblty>max?psblty:max;
            }
        }
        psblty=max;
        return psblty;
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
        unlock=1;
        count=0;
        flip=p;
        txt.setText("");
        ((ImageView)findViewById(R.id.imageView31)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView41)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView51)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView61)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView71)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView81)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView91)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView101)).setImageResource(R.drawable.nully);
        ((ImageView)findViewById(R.id.imageView111)).setImageResource(R.drawable.nully);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==(R.id.itm_1))
           difficulty=3;
       else
           difficulty=2;
       rePlay(imgView);
       return super.onOptionsItemSelected(item);
    }

}