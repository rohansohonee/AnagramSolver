package com.example.paul.anagramsolver;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> strArr;
    private ListView l;
    private ArrayAdapter<String> myarrayadapter;
    private long c;
    EditText e1;
    NumberPicker n1;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText);
        t1 = (TextView) findViewById(R.id.textView);
        n1 = (NumberPicker) findViewById(R.id.numberPicker);
        l = (ListView) findViewById(R.id.listView);
        n1.setEnabled(false);
        n1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                // do something here
                try {
                    valuechanged();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void gen1(View v) throws IOException {
        c = 0;
        String s = e1.getText().toString();
        int n = s.length();
        strArr = new ArrayList<String>();
        myarrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strArr);
        l.setAdapter(myarrayadapter);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        if (s.contentEquals("")) {
            Toast.makeText(MainActivity.this, "Enter a String", Toast.LENGTH_SHORT).show();
        } else {
            n1.setMaxValue(n);
            n1.setMinValue(2);
            n1.setEnabled(true);
            n1.setValue(n);
            n = n - 1;
            show(s, n);
        }

    }

    public void clear(View v) {
        strArr.add("");
        myarrayadapter.notifyDataSetChanged();
        myarrayadapter.clear();
        myarrayadapter.notifyDataSetChanged();
        t1.setText("");
        n1.setEnabled(false);
    }
    public void clear2() {
        strArr.add("");
        myarrayadapter.notifyDataSetChanged();
        myarrayadapter.clear();
        myarrayadapter.notifyDataSetChanged();
        t1.setText("");
        n1.setEnabled(false);
    }

    public void valuechanged() throws IOException {
        clear2();
        String s = e1.getText().toString();
        int n2=n1.getValue();
        int n = s.length();
        n=n-1;
        show1(s,n,n2);
    }

    public void show(String source, int n) throws IOException
    {
        int i,n1,j=0,c;
        String str = "";
        char []s1=new char[30];
        char []s2=new char[30];
       s1=source.toCharArray();
        InputStream is = this.getResources().openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            while ((str = reader.readLine()) != null)
            {
                s1=source.toCharArray();
                c=0;
                s2=str.toCharArray();
                n1=str.length();
                if(n1==n+1)
                {
                    for (j = 0; j <= n1 - 1; j++)
                    {
                        for (i = 0; i <= n; i++)
                        {
                            if (s2[j] == s1[i])
                            {
                                s1[i]=0;
                                c = c + 1;
                                break;
                            }
                        }
                    }
                    if (c == n + 1)
                    {
                        strArr.add(str);
                        myarrayadapter.notifyDataSetChanged();
                    }
                }
            }
        }
        is.close();
    }
    public void show1(String source, int n,int n2) throws IOException
    {
        n1.setEnabled(true);
        int i,n1,j=0,c;
        String str = "";
        char []s1=new char[30];
        char []s2=new char[30];
        s1=source.toCharArray();
        InputStream is = this.getResources().openRawResource(R.raw.words);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            while ((str = reader.readLine()) != null)
            {
                s1=source.toCharArray();
                c=0;
                s2=str.toCharArray();
                n1=str.length();
                if(n1==n2)
                {
                    for (j = 0; j <= n1 - 1; j++)
                    {
                        for (i = 0; i <= n; i++)
                        {
                            if (s2[j] == s1[i])
                            {
                                s1[i]=0;
                                c = c + 1;
                                break;
                            }
                        }
                    }
                    if (c >= n2)
                    {
                        strArr.add(str);
                        myarrayadapter.notifyDataSetChanged();
                    }
                }
            }
        }
        is.close();
    }
}
