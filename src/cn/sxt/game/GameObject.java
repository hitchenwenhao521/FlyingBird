package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	
	Image img;
	// wd, no enemy state
	static boolean wd=false;
	double x,y,a,b;
	int width,height; 
	int speed;
	public void drawSelf(Graphics g){
		g.drawImage(img, (int)x,(int) y, Constant.PLANE_WIDTH,Constant.PLANE_HEIGHT , null);
	}
	
	public GameObject(Image img, double x, double y, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public GameObject(Image img, double x, double y, int width, int height,int speed) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
	
	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}

	
	 public GameObject() {
	     
	}
	 
	 public Rectangle getRect(){
		 return new Rectangle((int)x,(int)y,width,height);
	 }
}
