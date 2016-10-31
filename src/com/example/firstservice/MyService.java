package com.example.firstservice;



import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private static final String TAG="MyService";
	//线程中循环是否停止的标志
	private boolean quit=false;
	private int count=0;
	//创建自定义的MyBinder对象
	private MyBinder myBinder=new MyBinder();

	//重写OnBind方法
	
	public void onDestory(){
		Log.i(TAG,"MyService onDestory invoked!");
		super.onDestroy();
		this.quit=true;
	}
	public int onStartCommand(Intent intent,int flags,int startId){
		Log.i(TAG,"MyService onStartCommand invoked!");
		return super.onStartCommand(intent,flags,startId);
	}
	public boolean onUnbind(Intent intent){
		Log.i(TAG,"MyService onUnbind invoked!");
		return super.onUnbind(intent);
	}
	public void onRebind(Intent intent){
		Log.i(TAG,"MyService onRebind invoked!");
		super.onRebind(intent);
	}
    public class MyBinder extends Binder{
    	//MyBinder的构造方法，观察什么时候创建
    	public MyBinder(){
    		Log.i(TAG,"MyBinder Constructure invoked!");
    	}
    	//MyBinder中提供的获取数据的方法
    	public int getCount(){
    		return count;
    	}
    }
    //重写onBind()方法，返回创建的对象
    public IBinder onBind(Intent arg0){
    	Log.i(TAG,"MyService onBind invoked!");
    	return myBinder;
    }
    public void onCreate(){
    	Log.i(TAG,"MyService onCreate invoked!");
    	super.onCreate();
    	new Thread(){
    		public void run(){
    			//判断是否继续执行循环
    			while(!quit){
    				try{
    					//休眠0.5S
    					Thread.sleep(500);
    					//数据递增
    					count++;
    				}catch(Exception e){
    					e.printStackTrace();
    				}
    			}
    		}
    	}.start();
    }
    public void onDestroy(){
    	Log.i(TAG,"MyService onDestory invoked!");
    	super.onDestroy();
    	//改变循环是否退出的标志，否则子线程一直在循环
    	quit=true;
    }
}


