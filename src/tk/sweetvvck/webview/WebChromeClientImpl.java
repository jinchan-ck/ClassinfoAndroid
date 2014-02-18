package tk.sweetvvck.webview;

import tk.sweetvvck.classinfo.WebMainActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

public class WebChromeClientImpl extends WebChromeClient {
	private ValueCallback<Uri> mUploadMessage;

	public ValueCallback<Uri> getmUploadMessage() {
		return mUploadMessage;
	}

	private Activity context;

	/**
	 * @param mUploadMessage
	 */
	public WebChromeClientImpl(ValueCallback<Uri> mUploadMessage,
			Activity context) {
		this.mUploadMessage = mUploadMessage;
		this.context = context;
	}

	@Override
	public void onExceededDatabaseQuota(String url, String databaseIdentifier,
			long currentQuota, long estimatedSize, long totalUsedQuota,
			WebStorage.QuotaUpdater quotaUpdater) {
		quotaUpdater.updateQuota(estimatedSize * 2);
	}

	@Override
	public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota,
			WebStorage.QuotaUpdater quotaUpdater) {
		quotaUpdater.updateQuota(spaceNeeded * 2);
	}

	public final void onCloseWindow(WebView paramWebView) {
		super.onCloseWindow(paramWebView);
	}

	public final void onConsoleMessage(String paramString1, int paramInt,
			String paramString2) {
		System.out.println("WebPageClient---->" + "--onConsoleMessage message:"
				+ paramString1 + ",lineNumber:" + paramInt + ",sourceId:"
				+ paramString2);
	}

	public final boolean onJsAlert(WebView paramWebView, String paramString1,
			String paramString2, JsResult paramJsResult) {
		System.out.println(paramJsResult);
		return super.onJsAlert(paramWebView, paramString1, paramString2,
				paramJsResult);
	}

	public final boolean onJsConfirm(WebView paramWebView, String paramString1,
			String paramString2, JsResult paramJsResult) {
		return super.onJsConfirm(paramWebView, paramString1, paramString2,
				paramJsResult);
	}

	public final boolean onJsPrompt(WebView paramWebView, String paramString1,
			String paramString2, String paramString3,
			JsPromptResult paramJsPromptResult) {
		return super.onJsPrompt(paramWebView, paramString1, paramString2,
				paramString3, paramJsPromptResult);
	}

	public final void onProgressChanged(WebView paramWebView, int paramInt) {
		super.onProgressChanged(paramWebView, paramInt);
		if (paramInt == 100) {
			((WebMainActivity) context).getProgressDialog().hide();
		}
	}

	/***************** android中使用WebView来打开本机的文件选择器 *************************/
	// js上传文件的<input type="file" name="fileField" id="fileField" />事件捕获
	// Android > 4.1.1 调用这个方法
	public void openFileChooser(ValueCallback<Uri> uploadMsg,
			String acceptType, String capture) {
		mUploadMessage = uploadMsg;
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		context.startActivityForResult(
				Intent.createChooser(intent, "完成操作需要使用"),
				WebMainActivity.FILECHOOSER_RESULTCODE);

	}

	// 3.0 + 调用这个方法
	public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
		mUploadMessage = uploadMsg;
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		context.startActivityForResult(
				Intent.createChooser(intent, "完成操作需要使用"),
				WebMainActivity.FILECHOOSER_RESULTCODE);
	}

	// Android < 3.0 调用这个方法
	public void openFileChooser(ValueCallback<Uri> uploadMsg) {
		mUploadMessage = uploadMsg;
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		intent.setType("image/*");
		context.startActivityForResult(
				Intent.createChooser(intent, "完成操作需要使用"),
				WebMainActivity.FILECHOOSER_RESULTCODE);

	}
	/************** end ***************/
}