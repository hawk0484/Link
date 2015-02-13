package com.SG.link;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WelcomePage extends Activity {
	View startPage = new View(this);
	Bitmap logo = null;
	Display disp = null;
	int phoneWidth=0,phoneHeight=0;
	ImageView iv=null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		disp = getWindowManager().getDefaultDisplay();
		Point p = new Point();
		disp.getSize(p);
		phoneWidth=p.x;
		phoneHeight=p.y;
		clickListener click = new clickListener();
		iv = new ImageView(this);
		logo=BitmapFactory.decodeFile("linklogo.png");
		iv.setImageBitmap(logo);
		LinearLayout ll = new LinearLayout(this);
		int calculatedWidth = (int) (phoneWidth-(phoneWidth*0.1));
		int calculatedHeight = (int) (phoneHeight*(calculatedWidth/logo.getHeight()));
		placePerc(iv,ll,0.5f,0.5f,0f,0f,calculatedWidth,calculatedHeight);
		iv.setClickable(true);
		iv.setOnClickListener(click);
		setContentView(startPage);
		
	}
	private WelcomePage getThis(){
		return this;
	}
	private void placePerc(View v,LinearLayout parent,float xp,float yp,float xo,float yo,int w,int h){
		int xx=(int) (xp*phoneWidth+xo-w/2);
		int yy=(int) (yp*phoneWidth+yo-h/2);
		v.setX(xx);
		v.setY(yy);
		v.getLayoutParams().width=(int) w;
		v.getLayoutParams().height=(int) h;
		parent.addView(v);
	}
	private class clickListener implements OnClickListener{
		public void onClick(View v) {
			if(v==iv){
				int newx=(int) iv.getX(),oldx=(int) iv.getX();
				int newy=(int) (phoneHeight*0.1);
				int oldy=(int) iv.getY();
				
				TranslateAnimation ta = new TranslateAnimation(oldx,newx,oldy,newy);
				iv.setClickable(false);
				ta.setStartTime(1000);
				ta.setDuration(2000);
				iv.setAnimation(ta);
				ta.start();
			}
		}
		
	}
	
}
