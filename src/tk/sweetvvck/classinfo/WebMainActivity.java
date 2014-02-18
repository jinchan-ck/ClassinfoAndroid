package tk.sweetvvck.classinfo;

import tk.sweetvvck.webview.MyWebViewClient;
import tk.sweetvvck.webview.WebChromeClientImpl;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebMainActivity extends Activity {

	public ValueCallback<Uri> mUploadMessage;
	public final static int FILECHOOSER_RESULTCODE = 1;
	private WebView webview;
	private WebChromeClientImpl wcci;
	private ProgressDialog progressDialog;

	public ProgressDialog getProgressDialog() {
		return progressDialog;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web_main);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		progressInit();
		init(url);
	}

	/**
	 * 
	 */
	private void progressInit() {
		progressDialog = new ProgressDialog(this);
	}

	/**
	 * @param url
	 */
	private void init(String url) {
		webview = (WebView) findViewById(R.id.my_web);
		WebSettings webseting = webview.getSettings();
		webseting.setDomStorageEnabled(true);
		webseting.setAppCacheMaxSize(1024 * 1024 * 8);// 设置缓冲大小，我设的是8M
		String appCacheDir = this.getApplicationContext()
				.getDir("cache", Context.MODE_PRIVATE).getPath();
		webseting.setAppCachePath(appCacheDir);
		webseting.setAllowFileAccess(true);
		webseting.setAppCacheEnabled(true);
		webseting.setCacheMode(WebSettings.LOAD_DEFAULT);
		webseting.setJavaScriptEnabled(true);

		wcci = new WebChromeClientImpl(mUploadMessage, this);
		webview.setWebChromeClient(wcci);
		webview.setWebViewClient(new MyWebViewClient());
		webview.setHorizontalScrollBarEnabled(false);// 水平不显示
		webview.setVerticalScrollBarEnabled(false); // 垂直不显示
		progressDialog.setMessage("loading...");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.show();
		progressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				progressDialog.dismiss();
			}
		});
		webview.loadUrl(url);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webview.canGoBack()) {
				webview.goBack();
			} else {
				this.finish();
			}
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	/**
	 * 返回文件选择
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (requestCode == FILECHOOSER_RESULTCODE) {
			mUploadMessage = wcci.getmUploadMessage();
			if (null == mUploadMessage)
				return;
			Uri result = intent == null || resultCode != RESULT_OK ? null
					: intent.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;

		}
	}
}
