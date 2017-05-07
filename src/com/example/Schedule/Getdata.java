package com.example.Schedule;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class Getdata {
	List<project2> list;

	public Getdata(String s) {
		list = new ArrayList<project2>();
		Document html = Jsoup.parse(s);
		Elements th = html.getElementById("user").select("tr");
		int i = 1, j = 0, k = 1;
		switch (i) {
		case 1:
			getdata1(th.get(1));
			i = i + 2;
			for (j = 0; j < list.size(); j++) {
				list.get(j).setStart(1);
			}
			k = j;
			System.out.println();
		case 3:
			getdata2(th.get(3));
			i = i + 3;
			for (j = k; j < list.size(); j++) {
				list.get(j).setStart(2);
			}
			k = j;
			System.out.println();
		case 6:
			getdata1(th.get(6));
			i += 2;
			for (j = k; j < list.size(); j++) {
				list.get(j).setStart(3);
			}
			k = j;
			System.out.println();
		case 8:
			getdata2(th.get(8));
			i += 3;
			for (j = k; j < list.size(); j++) {
				list.get(j).setStart(4);
			}
			k = j;
			System.out.println();
		case 11:
			getdata1(th.get(11));
			for (j = k; j < list.size(); j++) {
				list.get(j).setStart(5);
			}
			k = j ;
		}           
		for(int i1=0;i1<list.size();i1++){
			Log.e("1", list.get(i1).getProject());
		}
	}

	public void getdata1(Element tr) {
		int week = -1;
		Elements td = tr.select("td");
		for (Element t : td) {
			String s = t.text();
			if (s.length() > 8) {
				project2 p = new project2();
				p.setProject(s.substring(0, s.indexOf(":")));
				p.setWeek(week);
				list.add(p);				
			}
			week++;
		}
	}

	public void getdata2(Element tr) {
		int week = 0;
		Elements td = tr.select("td");
		for (Element t : td) {
			String s = t.text();
			if (s.length() > 8) {
				project2 p = new project2();
				p.setProject(s.substring(0, s.indexOf(":")));
				p.setWeek(week);
				list.add(p);				
			}
			week++;
		}
	}
}
