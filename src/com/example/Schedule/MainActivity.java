package com.example.Schedule;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.Schedule.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23,
			t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, top2;
	List<TextView> list;
	List<project2> list1;
	ArrayList<String> weeklist;
	int a1[] = { R.drawable.background, R.drawable.background1, R.drawable.background2, R.drawable.background3,
			R.drawable.background4 };
	PopupWindow pw;
	int pop1 = 12;
	String login;
	String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seton();
		getid();
		settext();
		setweek();
	}

	public void seton() {
		Intent i = getIntent();
		String a = i.getExtras().getString("login");
		Log.e("e", a);
		String []b=a.split(" ");
		login = b[0];
		password = b[1];
		String txt = a;
		String filename = "Mydata.txt";
		try {
			FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(txt.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			Log.e("e", e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setweek() {
		top2 = (TextView) findViewById(R.id.top2);
		int j = pop1 + 1;
		top2.setText("第" + j + "周");
		top2.setOnClickListener(new OnClickListener() {

			@SuppressLint("InflateParams")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				View myview = getLayoutInflater().inflate(R.layout.pop, null);
				pw = new PopupWindow(myview, 240, 360, true);
				pw.setFocusable(true);
				pw.setBackgroundDrawable(new ColorDrawable());
				int loaction[] = new int[2];
				v.getLocationOnScreen(loaction);
				pw.showAtLocation(v, Gravity.NO_GRAVITY, (loaction[0] + v.getWidth() / 2 - 120),
						(loaction[1] + v.getHeight() + 5));
				final ListView lv = (ListView) myview.findViewById(R.id.lv_pop);
				lv.post(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int h = pop1 - 2;
						if (h <= 0)
							lv.setSelection(0);
						else
							lv.setSelection(h);
					}
				});
				lv.setAdapter(new ListViewAdapter(MainActivity.this, weeklist, pop1));

				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						pop1 = position;
						position++;
						settextback(position);
						top2.setText("第" + position + "周");
						pw.dismiss();
					}
				});
			}
		});
	}

	public void settext() {
		Client1 t = new Client1(login, password);
		Thread thread = new Thread(t);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list1 = new ArrayList<project2>();
		list1.addAll(t.list);
		getweek();
		int j = 0;

		for (int i = 0; i < list.size(); i++) {
			int i1 = i % 7 + 1;
			int i2 = i / 7 + 1;
			int i3 = list1.get(j).getWeek();
			int i4 = list1.get(j).getStart();
			if (i1 == i3 && i2 == i4) {
				list.get(i).setText(list1.get(j).getProject());
				list.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, 13);
				list.get(i).setPadding(5, 0, 5, 0);
				Random r = new Random();
				list.get(i).setBackgroundResource(a1[r.nextInt(5)]);
				list.get(i).setTextColor(this.getResources().getColor(R.color.ccc));
				int i5 = pop1 + 1;
				if (i5 < list1.get(j).getWeekstart() || i5 > list1.get(j).getWeekend())
					list.get(i).setTextColor(Color.parseColor("#000000"));
				j++;

			}
			if (j == list1.size())
				break;
		}
	}

	public void settextback(int i5) {
		int j = 0;
		for (int i = 0; i < list.size(); i++) {
			int i1 = i % 7 + 1;
			int i2 = i / 7 + 1;
			int i3 = list1.get(j).getWeek();
			int i4 = list1.get(j).getStart();
			if (i1 == i3 && i2 == i4) {
				list.get(i).setTextColor(this.getResources().getColor(R.color.ccc));
				if (i5 < list1.get(j).getWeekstart() || i5 > list1.get(j).getWeekend())
					list.get(i).setTextColor(Color.parseColor("#000000"));
				j++;
			}
			if (j == list1.size())
				break;
		}
	}

	public void getweek() {
		for (int i1 = 0; i1 < list1.size(); i1++) {
			switch (i1) {
			case 0:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(14);
				break;
			case 1:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(15);
				break;
			case 2:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(14);
				break;
			case 3:
				list1.get(i1).setWeekstart(9);
				list1.get(i1).setWeekend(16);
				break;
			case 4:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(15);
				break;
			case 5:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(12);
				break;
			case 6:
				list1.get(i1).setWeekstart(9);
				list1.get(i1).setWeekend(16);
				break;
			case 7:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(15);
				break;
			case 8:
				list1.get(i1).setWeekstart(3);
				list1.get(i1).setWeekend(3);
				break;
			case 9:
				list1.get(i1).setWeekstart(2);
				list1.get(i1).setWeekend(13);
				break;
			case 10:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(15);
				break;
			case 11:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(12);
				break;
			case 12:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(15);
				break;
			case 13:
				list1.get(i1).setWeekstart(1);
				list1.get(i1).setWeekend(16);
				break;
			case 14:
				list1.get(i1).setWeekstart(3);
				list1.get(i1).setWeekend(6);
				break;
			case 15:
				list1.get(i1).setWeekstart(2);
				list1.get(i1).setWeekend(13);
				break;
			case 16:
				list1.get(i1).setWeekstart(3);
				list1.get(i1).setWeekend(6);
				break;
			}
		}
	}

	public void getid() {
		list = new ArrayList<TextView>();
		t1 = (TextView) findViewById(R.id.t1);
		t2 = (TextView) findViewById(R.id.t2);
		t3 = (TextView) findViewById(R.id.t3);
		t4 = (TextView) findViewById(R.id.t4);
		t5 = (TextView) findViewById(R.id.t5);
		t6 = (TextView) findViewById(R.id.t6);
		t7 = (TextView) findViewById(R.id.t7);
		t8 = (TextView) findViewById(R.id.t8);
		t9 = (TextView) findViewById(R.id.t9);
		t10 = (TextView) findViewById(R.id.t10);
		t11 = (TextView) findViewById(R.id.t11);
		t12 = (TextView) findViewById(R.id.t12);
		t13 = (TextView) findViewById(R.id.t13);
		t14 = (TextView) findViewById(R.id.t14);
		t15 = (TextView) findViewById(R.id.t15);
		t16 = (TextView) findViewById(R.id.t16);
		t17 = (TextView) findViewById(R.id.t17);
		t18 = (TextView) findViewById(R.id.t18);
		t19 = (TextView) findViewById(R.id.t19);
		t20 = (TextView) findViewById(R.id.t20);
		t21 = (TextView) findViewById(R.id.t21);
		t22 = (TextView) findViewById(R.id.t22);
		t23 = (TextView) findViewById(R.id.t23);
		t24 = (TextView) findViewById(R.id.t24);
		t25 = (TextView) findViewById(R.id.t25);
		t26 = (TextView) findViewById(R.id.t26);
		t27 = (TextView) findViewById(R.id.t27);
		t28 = (TextView) findViewById(R.id.t28);
		t29 = (TextView) findViewById(R.id.t29);
		t30 = (TextView) findViewById(R.id.t30);
		t31 = (TextView) findViewById(R.id.t31);
		t32 = (TextView) findViewById(R.id.t32);
		t33 = (TextView) findViewById(R.id.t33);
		t34 = (TextView) findViewById(R.id.t34);
		t35 = (TextView) findViewById(R.id.t35);
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		list.add(t7);
		list.add(t8);
		list.add(t9);
		list.add(t10);
		list.add(t11);
		list.add(t12);
		list.add(t13);
		list.add(t14);
		list.add(t15);
		list.add(t16);
		list.add(t17);
		list.add(t18);
		list.add(t19);
		list.add(t20);
		list.add(t21);
		list.add(t22);
		list.add(t23);
		list.add(t24);
		list.add(t25);
		list.add(t26);
		list.add(t27);
		list.add(t28);
		list.add(t29);
		list.add(t30);
		list.add(t31);
		list.add(t32);
		list.add(t33);
		list.add(t34);
		list.add(t35);
		weeklist = new ArrayList<String>();
		for (int h = 0; h < 24; h++) {
			int p = h + 1;
			weeklist.add("第" + p + "周");
		}
		RadioButton rb=(RadioButton)findViewById(R.id.radioButton1);
		rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,User.class);
				startActivity(i);
			}
		});
	}

}
