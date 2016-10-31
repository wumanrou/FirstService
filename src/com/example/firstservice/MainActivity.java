package com.example.firstservice;

import com.example.firstservice.MyService.MyBinder;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {


	protected static final String TAG = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button start=(Button)findViewById(R.id.btnstart);
		Button stop=(Button)findViewById(R.id.btnstop);
		Button bind=(Button)findViewById(R.id.btnbind);
		Button unbind=(Button)findViewById(R.id.btnunbind);
		final Intent intent=new Intent();
		intent.setAction("com.example.firstservice.MyService");
		start.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0){
				startService(intent);
				
			}
		});
		bind.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				ServiceConnection conn = null;
				bindService(intent,conn,Service.BIND_AUTO_CREATE);
				
			}
		});
		unbind.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				ServiceConnection conn = null;
				unbindService(conn);
		}
		});
		
		stop.setOnClickListener(new OnClickListener(){
		
			public void onClick(View arg0){
				
				stopService(intent);
         }
		});

		
		ServiceConnection conn=new ServiceConnection(){
			public void onServiceDisconnected (ComponentName name){
				Log.i(TAG,"MainAcitivty onServiceDisconnected invoked!");
			}
		

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				Log.i(TAG,"MainAcitivty onServiceconnected invoked!");
				//将传递的参数强制类型转换成MyBinder对象
				final MyBinder myBinder = (MyBinder)service;
				View getData = null;
				getData.setOnClickListener(new OnClickListener(){
					public void onClick(View v){
						//以消息的形式，显示获取的数据
						Toast.makeText(MainActivity.this, "Count="+myBinder.getCount(),100).show();}
					});
			
				
			}

		};
		
		
	}

}
