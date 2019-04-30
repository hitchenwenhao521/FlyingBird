package cn.sxt.game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Column  extends GameObject {
	boolean space,start=true;
	private Image imgs;
	int sjs,bl=3;
	public void drawSelf(Graphics g){
		if(Bird.startGame)
		{
			if(start)
			{
				if(Math.random()>=0.5)
				{
					sjs=(int) (Math.random()*300);
				}
				else
				{
					sjs=-(int) (Math.random()*300);
				}
				
			}
			
			g.drawImage(img, (int)x,(int) y, Constant.COLUMN_WIDTH,Constant.COLUMN_HEIGHT+sjs, null);
	
			g.drawImage(imgs, (int)x,(int) y+480+sjs, Constant.COLUMN_WIDTH,Constant.COLUMN_HEIGHT-sjs, null);
			
			if(wd)
			{
				x-=15;
				a-=15;
				b-=15;
			}else
			{
				x-=bl;
				a-=bl;
				b-=bl;
				bl+=0.1;
			}
			if(x<-40)
			{
				x=Constant.GAME_WIDTH+180;
				start=true;
			}
			else
			{
				start=false;
			}
		}
	}
	
	public Column(Image imgs,Image img,double x,double y){
		this.imgs=imgs;
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = Constant.COLUMN_WIDTH;//���Ӵ�С
		this.height = Constant.COLUMN_HEIGHT;//���Ӵ�С
	}
	
	public Rectangle getRectup(){
		 return new Rectangle((int)x+7,(int)y-17,width,height+sjs);
	 }
	
	public Rectangle getRectdown(){
		 return new Rectangle((int)x+7,(int)y+480+sjs+14,width,height-sjs);
	 }
	
	public boolean Pzjc (Bird plane)
	{
		boolean peng0 = this.getRectup().intersects(plane.getRect());
		boolean peng1 = this.getRectdown().intersects(plane.getRect());
		if(wd)
		{
			return false;
		}
		else
		{
			if(peng0==false&&peng1==false)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		
	}
	

}
