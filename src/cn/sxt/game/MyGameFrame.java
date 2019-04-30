package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;


public class MyGameFrame extends Frame {
	static boolean wdsj=false;
	
	Image bird1  = GameUtil.getImage("img/1.png");
	
	Image bg  = GameUtil.getImage("img/5.png");
	Image column0  = GameUtil.getImage("img/3.png");
	Image column1 = GameUtil.getImage("img/4.png");
	
	Bird plane = new Bird(bird1,Constant.GAME_WIDTH/3,Constant.GAME_HEIGHT/2);
	
	
	Column columndown1 = new Column(column1,column0,920,0);
	Column columndown2 = new Column(column1,column0,440,0);
	Column columndown3 = new Column(column1,column0,680,0);
	Date  startTime = new Date();
	Date  endTime;
	int period,period0;
	boolean first=true;
	@Override
	public void paint(Graphics g) {
		Color c =  g.getColor();
		
		
		g.drawImage(bg, 0, 0, null);
		g.drawImage(bg, 0,0, Constant.GAME_WIDTH,Constant.GAME_HEIGHT, null);
		columndown1.drawSelf(g);
		columndown2.drawSelf(g);
		columndown3.drawSelf(g);
		plane.drawSelf(g);
		
		if(columndown1.Pzjc(plane)||columndown2.Pzjc(plane)||columndown3.Pzjc(plane)){
				plane.live = false;
				if(first)
				{
					first=false;
					endTime = new Date();			
					period = (int)((endTime.getTime() - startTime.getTime())/1000);
				}
					
			}
		
		if(period0>=10&&period0%10>=1&&period0%10<=9)
		{
			wdsj=true;
			g.setColor(Color.red);
			Font f00 = new Font("����",Font.BOLD,20);
			g.setFont(f00);
			g.drawString("press J,invincible state",(int)Constant.GAME_WIDTH*2/5,(int)Constant.GAME_HEIGHT*1/16);
			g.setColor(c);
		}else
		{
			wdsj=false;
			Bird.wd=false;
		}
		
		if(!plane.live){
			g.setColor(Color.RED);
			Font f = new Font("����",Font.BOLD,50);
			g.setFont(f);
			g.drawString("score:"+period+"seconds",(int)Constant.GAME_WIDTH*2/7,(int)Constant.GAME_HEIGHT*6/10);
			g.drawString("GameOver",(int)Constant.GAME_WIDTH*2/7,(int)Constant.GAME_HEIGHT*4/10);
		}else if(Bird.startGame)
			{
				endTime = new Date();			
				period0 = (int)((endTime.getTime() - startTime.getTime())/1000);
				g.setColor(Color.white);
				Font f0 = new Font("Times New Roman",Font.BOLD,20);
				g.setFont(f0);
				g.drawString("score:"+period0+"seconds",(int)Constant.GAME_WIDTH*4/9,(int)Constant.GAME_HEIGHT*1/16);
			}
			
		g.setColor(c);
	}

	
	class PaintThread extends Thread{
		@Override
		public void run() {
			while(true){
				repaint();
				try {
					Thread.sleep(40);//1s = 1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
	}
	
	public void  launchFrame(){
		this.setTitle("FlyingBird");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		this.setLocation(700,150);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
					System.exit(0);
			}
		});
		
		new PaintThread().start();
		addKeyListener(new KeyMonitor());
		
		
	}
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
	//shanshuo
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	} 
	

	
	
}
