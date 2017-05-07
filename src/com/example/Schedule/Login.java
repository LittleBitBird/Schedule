package com.example.Schedule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.example.Schedule.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity {
	Button bt1;
	EditText et1;
	EditText et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		bt1 = (Button) findViewById(R.id.denglu1);
		et1 = (EditText) findViewById(R.id.editText1);
		et2 = (EditText) findViewById(R.id.editText2);		
		try {
			check();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					String a = et1.getText().toString();
					String b = et2.getText().toString();
					a = a + " " + b;
					Intent i = new Intent();
					Bundle bd = new Bundle();
					bd.putString("login", a);
					i.putExtras(bd);
					i.setClass(Login.this, MainActivity.class);
					startActivity(i);
				}catch(Exception e){
					
				}
				
			}
		});
	}
	public void check() throws IOException {
		String filename = "Mydata.txt";
		String mydata = null;
		FileInputStream fis=null;
		try {
			fis = openFileInput(filename);
			byte[] reader = new byte[fis.available()];
			if (fis.read(reader) != -1) {
				mydata = new String(reader);				
			}
			if (mydata.equals("1")) {
								
			}
			else {
				String a=mydata;				
				Intent i=new Intent();
				Bundle bd=new Bundle();
				bd.putString("login", a);
				i.putExtras(bd);
				i.setClass(Login.this, MainActivity.class);
				startActivity(i);
			}	
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("t", e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.e("t",e.toString());
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
