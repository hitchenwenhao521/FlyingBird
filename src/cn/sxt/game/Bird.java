package cn.sxt.game;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Bird  extends GameObject {
	boolean space;
	boolean live = true;
	static boolean startGame = false;
	double bl=0;
	Image jiemian  = GameUtil.getImage("img/6.png");
	public void drawSelf(Graphics g){
		if(!startGame){
			g.drawImage(jiemian, 0,0, Constant.GAME_WIDTH,Constant.GAME_HEIGHT, null);//������
		}
		if(live&&startGame){
			
				g.drawImage(img, (int)x,(int) y, Constant.PLANE_WIDTH,Constant.PLANE_HEIGHT , null);
				
			if(space){
				    bl=0;
					y = y-speed;
					 if(y<=20)
					{
						y=20;
					}
			}
			else
			{	
				if(wd)
				{
					y=y;
					bl=0;
					if(y>=Constant.GAME_HEIGHT-Constant.PLANE_HEIGHT)
					{
						y = Constant.GAME_HEIGHT-Constant.PLANE_HEIGHT;
					}
				}
				else
				{
					y+=4+bl;
					bl+=0.1;
					if(y>=Constant.GAME_HEIGHT-Constant.PLANE_HEIGHT)
					{
						y = Constant.GAME_HEIGHT-Constant.PLANE_HEIGHT;
					}
				}
			
				
			}
		}
	
		
	}
	
	public Bird(Image img,double x,double y){
		this.img= img;
		this.x = x;
		this.y = y;
		this.speed = 7;
		this.width = Constant.PLANE_WIDTH;
		this.height = Constant.PLANE_HEIGHT;
	}
	
	public void addDirection(KeyEvent e){
		switch(e.getKeyCode()){

		case KeyEvent.VK_SPACE:
			space = true;
			wd = false;
			break;
		case 10:
			startGame = true;
			break;
		case KeyEvent.VK_J:
			if(MyGameFrame.wdsj)
			{
				wd = true;
			}
			break;
			default:
				wd = false;
				break;
			
		}
	}
	
	public void minusDirection(KeyEvent e){
		switch(e.getKeyCode()){
	
		case KeyEvent.VK_SPACE:
			space = false;
			break;
		}
	}
	
	public Rectangle getRect(){
		 return new Rectangle((int)x,(int)y,width,height);
	 }
	
}
