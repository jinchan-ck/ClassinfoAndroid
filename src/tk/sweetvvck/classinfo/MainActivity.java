package tk.sweetvvck.classinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;

import tk.sweetvvck.constant.Constant;
import tk.sweetvvck.domain.Banji;
import tk.sweetvvck.utils.HttpUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity {

	private static final int LOGIN_WHAT = 0;
	private static final int VISIT_WHAT = 1;

	private static final String TAG = "MainActivity";
	private EditText etUsername;
	private EditText etPwd;

	private Button loginBtn;
	private Button visitBtn;
	private boolean flag;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOGIN_WHAT:
				Bundle bundle = msg.getData();
				String result = bundle.getString("result");
				String url = bundle.getString("url");
				Log.i(TAG, result);
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG)
						.show();
				loginBtn.setText(getResources().getString(R.string.login));
				loginBtn.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.login_button));
				if ("\"验证成功\"".equals(result)) {
					Intent intent = new Intent(MainActivity.this,
							WebMainActivity.class);
					intent.putExtra("url", url);
					startActivity(intent);
				}
				break;
			case VISIT_WHAT:
				if (visitBtn != null)
					visitBtn.setText(MainActivity.this.getResources()
							.getString(R.string.select_banji));
				@SuppressWarnings("unchecked")
				List<Banji> list = (List<Banji>) msg.obj;
				final Spinner spinner = (Spinner) MainActivity.this
						.findViewById(R.id.banji_spinner);
				List<String> items = new ArrayList<String>();
				final Map<Integer, Integer> map = new HashMap<Integer, Integer>();
				if (list != null && !list.isEmpty()) {
					for (int i = 0; i < list.size(); i++) {
						items.add(list.get(i).getBanjiName());
						map.put(i, list.get(i).getBanjiId());
					}
					ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(
							MainActivity.this,
							android.R.layout.simple_spinner_item, items);
					spinner.setAdapter(mAdapter);
					spinner.setVisibility(View.VISIBLE);

					spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							if (flag) {
								Intent intent = new Intent(MainActivity.this,
										WebMainActivity.class);
								int banjiId = map.get(position);
								String url = "http://classinfo.duapp.com/LoginAction.action?userType=visit&ua=android&banjiId=";
								System.out.println("banjiId------>" + banjiId);
								intent.putExtra("url", url + banjiId);
								startActivity(intent);
							}
							flag = true;
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {

						}
					});
				}

				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPwd = (EditText) findViewById(R.id.et_password);
		loginBtn = (Button) findViewById(R.id.button_login);

	}

	public void login(View v) {
		if (!HttpUtil.canConnect(this)) {
			Toast.makeText(this, "未连接到网络，请检查后重试", Toast.LENGTH_LONG).show();
			return;
		}
		loginBtn.setText(getResources().getString(R.string.logining));
		loginBtn.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.login_button_pressed));
		new Thread(new Runnable() {
			@Override
			public void run() {
				String username = etUsername.getText().toString().trim();
				String pwd = etPwd.getText().toString().trim();
				try {
					String result = login(username, pwd);
					Message msg = new Message();
					msg.what = LOGIN_WHAT;
					Bundle bundle = new Bundle();
					bundle.putString("result", result);
					bundle.putString("url",
							"http://classinfo.duapp.com/LoginAction?userType=host&ua=android"
									+ "&studentNum=" + username + "&password="
									+ pwd);
					msg.setData(bundle);
					mHandler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	private String login(String username, String pwd) {
		String url = Constant.LOGIN_VALIDATE_URL;
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		NameValuePair nameValuePair1 = new BasicNameValuePair("studentNum",
				username);
		NameValuePair nameValuePair2 = new BasicNameValuePair("password", pwd);
		nameValuePairs.add(nameValuePair1);
		nameValuePairs.add(nameValuePair2);
		String result = null;
		try {
			result = HttpUtil.getData(url, nameValuePairs);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void visit(View v) {
		if (!HttpUtil.canConnect(this)) {
			Toast.makeText(this, "未连接到网络，请检查后重试", Toast.LENGTH_LONG).show();
			return;
		}
		visitBtn = (Button) v;
		visitBtn.setText(getResources().getString(R.string.loading_banji));
		v.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.login_button_pressed));
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String url = Constant.VISITOR_URL;
					String data = HttpUtil.getData(url, null);
					Gson gson = new GsonBuilder().setDateFormat(
							"yyyy-MM-dd'T'HH:mm:ss").create();
					List<Banji> list = gson.fromJson(data,
							new TypeToken<List<Banji>>() {
							}.getType());
					Message msg = new Message();
					msg.what = VISIT_WHAT;
					msg.obj = list;
					mHandler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}).start();
	}

	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
