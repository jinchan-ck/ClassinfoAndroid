package tk.sweetvvck.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {

	private static String parseData(String data) throws JSONException{
		System.out.println(data);
		if(data == null || data.contains("<html"))
			return "error la ~~";
		return data;
	}
	
	public static String getData(String url, List<NameValuePair> nameValuePairs) throws JSONException {
		if (nameValuePairs == null) {
			nameValuePairs = new ArrayList<NameValuePair>();
		}
		String data = "";
		StringBuffer stringBuffer = new StringBuffer();
		try {
			HttpEntity httpEntity = new UrlEncodedFormEntity(nameValuePairs,
					"utf-8");
			System.out.println("访问的接口是： ----》" + url);
			HttpPost post = new HttpPost(url);
			post.setEntity(httpEntity);
			System.out.println("HttpEntity is : "
					+ new BufferedReader(new InputStreamReader(httpEntity
							.getContent())).readLine());
			HttpClient client = getHttpClient();
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				String line;
				while ((line = reader.readLine()) != null) {
					stringBuffer.append(line);
				}
			} else
				return null;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = stringBuffer.toString();
		return parseData(data);
	}

	private static HttpClient getHttpClient() {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				20000); // 超时设置
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 20000);// 连接超时
		return client;
	}
	
	public static boolean canConnect(Context context){
		ConnectivityManager cManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		  if (info != null && info.isAvailable()){
		       //能联网
		        return true;
		  }else{
		       //不能联网
		        return false;
		  } 
	}
}
