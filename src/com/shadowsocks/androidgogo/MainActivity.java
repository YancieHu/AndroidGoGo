package com.shadowsocks.androidgogo;

import com.shadow.bean.ShadowTotalBean;
import com.shadow.util.HelloWorld;
import com.shadowsocks.util.ConvertUtil;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity implements OnClickListener {

	private ShadowTotalBean mShadowTotalBean;
	LinearLayout linlaHeaderProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linlaHeaderProgress = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
		findViewById(R.id.button).setOnClickListener(this);
	}

	class MyAsyncTask extends AsyncTask<Void, Void, String> {
		@Override
		protected void onPreExecute() {
			// SHOW THE SPINNER WHILE LOADING FEEDS
			linlaHeaderProgress.setVisibility(View.VISIBLE);
		}

		@Override
		protected String doInBackground(Void... params) {
			mShadowTotalBean = HelloWorld.getInfo();
			String info = ConvertUtil.convertBean(mShadowTotalBean);
			return info;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			ConvertUtil.copy(result, MainActivity.this);
			linlaHeaderProgress.setVisibility(View.GONE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			new MyAsyncTask().execute();
			break;

		default:
			break;
		}

	}

}
