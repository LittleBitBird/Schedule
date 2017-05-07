package com.example.Schedule;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.util.Log;

public class Client1 implements Runnable{
	static String url1 = "http://222.188.0.102/loginAction.do";
	static String url2 = "http://222.188.0.102/syglSyxkAction.do?&oper=xsxkKcbAll";
	List<project2> list;
	String login;
	String password;
	
	public Client1(String a,String b){
		login=a;
		password=b;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpClient client=new DefaultHttpClient();
		HttpPost post=new HttpPost(url1);
		post.setHeader("Content-Type", "application/x-www-form-urlencoded");
		post.setHeader("Host", "222.188.0.102");
		post.setHeader("Referer", "http://222.188.0.102/");
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("zjh", login));
		params.add(new BasicNameValuePair("mm", password));
		try {
			post.setEntity(new UrlEncodedFormEntity(params, "GBK"));
			HttpResponse response=client.execute(post);
			if(response.getStatusLine().getStatusCode()==200){
				List<Cookie> cookie=((AbstractHttpClient)client).getCookieStore().getCookies();
				Log.e("1", cookie.toString());
				Log.e("1", cookie.get(0).getName()+"="+cookie.get(0).getValue());
				String s=cookie.get(0).getName()+"="+cookie.get(0).getValue();
				getdata(client, s);
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getdata(HttpClient client,String cookie){
		HttpGet get=new HttpGet(url2);
		list=new ArrayList<project2>();
		get.addHeader("Cookie", cookie);
		try {
			HttpResponse response=client.execute(get);
			HttpEntity entity=response.getEntity();
			String s=EntityUtils.toString(entity);
			Log.e("2", s);
			Getdata get2=new Getdata(s);
			list.addAll(get2.list);
			for(int i1=0;i1<list.size();i1++){
				Log.e("2", list.get(i1).getProject());
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}










