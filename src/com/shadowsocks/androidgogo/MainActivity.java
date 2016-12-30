package com.shadowsocks.androidgogo;

import com.shadow.bean.ShadowTotalBean;
import com.shadow.util.HelloWorld;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity {

	private ShadowTotalBean mShadowTotalBean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	class MyAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			mShadowTotalBean = HelloWorld.getInfo();
			return null;
		}

	}

}
