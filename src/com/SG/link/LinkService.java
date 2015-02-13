package com.SG.link;

import java.util.Stack;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LinkService extends IntentService{
	static LinkService instance;
	public LinkService(String name) {
		super(name);
		instance=this;
	}
	protected void onHandleIntent(Intent intent) {
		
	}
	public Stack<LinkHandler> links = new Stack<LinkHandler>();
	public static LinkService getInstance(){
		return instance;
	}
	public boolean canConnect(String deviceName) {
		if(!deviceName.startsWith("Link:")){
			return false;
		}
		for(LinkHandler link : (LinkHandler[])links.toArray()){
			if(link.name==deviceName){
				return false;
			}
		}
		return true;
	}
}
