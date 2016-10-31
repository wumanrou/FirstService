package com.example.firstservice;



import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private static final String TAG="MyService";
	//�߳���ѭ���Ƿ�ֹͣ�ı�־
	private boolean quit=false;
	private int count=0;
	//�����Զ����MyBinder����
	private MyBinder myBinder=new MyBinder();

	//��дOnBind����
	
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
    	//MyBinder�Ĺ��췽�����۲�ʲôʱ�򴴽�
    	public MyBinder(){
    		Log.i(TAG,"MyBinder Constructure invoked!");
    	}
    	//MyBinder���ṩ�Ļ�ȡ���ݵķ���
    	public int getCount(){
    		return count;
    	}
    }
    //��дonBind()���������ش����Ķ���
    public IBinder onBind(Intent arg0){
    	Log.i(TAG,"MyService onBind invoked!");
    	return myBinder;
    }
    public void onCreate(){
    	Log.i(TAG,"MyService onCreate invoked!");
    	super.onCreate();
    	new Thread(){
    		public void run(){
    			//�ж��Ƿ����ִ��ѭ��
    			while(!quit){
    				try{
    					//����0.5S
    					Thread.sleep(500);
    					//���ݵ���
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
    	//�ı�ѭ���Ƿ��˳��ı�־���������߳�һֱ��ѭ��
    	quit=true;
    }
}


