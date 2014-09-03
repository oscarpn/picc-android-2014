package com.example.treinamento.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public abstract class LifeCycleActivity extends ActionBarActivity {
	
	public abstract String getActivityName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onCreate");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onStart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onPause");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onRestart");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(getString(R.string.lifecycle),getActivityName() + ": onDestroy");
	}
	
}
