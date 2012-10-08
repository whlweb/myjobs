package Game;


import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class diren implements Runnable{
	int x;
	int y;
	int go;
	int niao = 1;
	int xMove,yMove;
	int flowerup,flowerdown;
	BufferedImage showImage = null;
	int megubian = 1;
	int leixing;
	boolean zaime = true;
	static Thread direngo,change;
	int startx;
	boolean yun = false;
	boolean dead = false;
	int stax,stay;
	public void remove() {
		x = stax;
		y = stay;
		go = 1;
		zhuanshu = 1;
	}
	diren(int x,int y,int stax,int stay,int leixing) {
		this.x = x;
		this.y = y;
		this.leixing = leixing;
		this.stax = stax;
		this.stay = stay;
		go = 1;
		startx = x;
		flowerup = y - 70;
		flowerdown = y + 10;
		if(leixing==1) {
			try{
				showImage = ImageIO.read(new File("diren1.png"));
			}catch(Exception e){}
		}
		if(leixing==2) {
			try{
				showImage = ImageIO.read(new File("diren2.png"));
			}catch(Exception e){}
		}
		if(leixing==3) {
			try{
				showImage = ImageIO.read(new File("diren6.png"));
			}catch(Exception e){}
		}
		if(leixing==4) {
			try{
				showImage = ImageIO.read(new File("diren9.png"));
			}catch(Exception e){}
		}
		if(leixing==5) {
			try{
				showImage = ImageIO.read(new File("diren0.png"));
			}catch(Exception e){}
		}
		if(leixing==6) {
			try{
				showImage = ImageIO.read(new File("diren01.png"));
			}catch(Exception e){}
		}
		if(leixing==7) {
			try{
				showImage = ImageIO.read(new File("niao3.png"));
			}catch(Exception e){}
		}
		if(leixing==8) {
			try{
				showImage = ImageIO.read(new File("zhaoyun1.png"));
			}catch(Exception e){}
		}
		if(leixing==9) {
			try{
				showImage = ImageIO.read(new File("diren5.png"));
			}catch(Exception e){}
		}
		if(leixing==10) {
			try{
				showImage = ImageIO.read(new File("jiangshi1.png"));
			}catch(Exception e){}
		}
		if(leixing==11) {
			try{
				showImage = ImageIO.read(new File("bird1.png"));
			}catch(Exception e){}
		}
		direngo = new Thread(this);
		direngo.start();
		change = new Thread(new huantu());
		change.start();
	}
	public void leftMove(){
		if(x>0) {
			xMove = -5;
		}
		else{
			xMove = 0;
			go = 2;
		}
	}
	public void rightMove() {
		//System.out.println(go+"  "+x+"  "+y+"  ");
		if(x<910) {
			xMove = 5;
		}
		else {
			xMove = 0;
			go = 1;
		}
	}
	public void up() {
		if(y>flowerup) {
			yMove = -5;
		}
		else {
			yMove = 0;
			go = 2;
		}
	}
	public void down(){
		if(y<flowerdown){
			yMove = 5;
		}
		else {
			yMove = 0;
			go = 1;
		}
	}
	public void feidan() {
		if(x<0) {
			int san = (int)(Math.random()*5)+1;
			y = san*70;
			x = 910;
		}
		xMove = -10;
	}
	int zhuanshu = 1;
	public void zhaoyunmove() {
		if(zhuanshu==1&&x<0) {
			zhuanshu = 2;
			
		}
		if(zhuanshu == 2&&x>840) {
			zhuanshu = 1;
		}
		if(zhuanshu==1) {
			xMove = -5; 
		}
		else {
			xMove = 5;
		}
	}
	public void run(){
		while(zaime) {
			try{
				if(Mario.isdead ) {
					zhuanshu = 1;
				}
				if(leixing ==1) {
					if(go==1) {
						up();
					}
					else {
						down();
					}
					y += yMove;
				}
				else if(leixing ==6) {
						
					for(int i=0;i<MyFrame.getzhangainum();i++) {
						Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
						if(y>za.y-70&&y<za.y+70&&x>za.x-70&&x<za.x+70&&!(za.leixing==11&&!za.ding))
							x = startx;
					}
					
					if(x<=0) {
						x = startx;
					}
					xMove = -20;
					x+=xMove;
				}
				else if(leixing==5) {
					
				}
				else if(leixing ==8) {
					//System.out.println(zhuanshu+"  "+x+"  "+y+"  ");
					yun = false;
					for(int i=0;i<MyFrame.getzhangainum();i++) {
						Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
						if(za.y>=y&&za.y<=y+70&&x==za.x-140&&!(za.leixing==11&&!za.ding)) {
							if(zhuanshu==2) {
								yun = true;
								za.x = -90;
								za.y = -90;
							}
						}
						if(za.y>=y&&za.y<=y+70&&x==za.x+70&&!(za.leixing==11&&!za.ding)) {
							if(zhuanshu==1) {
								yun = true;
								za.x = -90;
								za.y = -90;
							}
						}
					}
					zhaoyunmove();
					x += xMove;
				}
				else if(leixing==9) {
					feidan();
					x+=xMove;
				}
				else {
					if(go ==1) {
						leftMove();
					}
					else {
						rightMove();
					}
					for(int i=0;i<MyFrame.getzhangainum();i++) {
						Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
						if(y>za.y-70&&y<za.y+70&&x==za.x-70&&!(za.leixing==11&&!za.ding)) {
							if(go==2) {
								go = 1;
								xMove = -5;
							}
						}
						if(y>za.y-70&&y<za.y+70&&x==za.x+70&&!(za.leixing==11&&!za.ding)) {
							if(go==1) {
								go = 2;
								xMove = 5;
							}
						}
					}
					
					x += xMove;
				
				if(leixing==3) {
					if(dead) {
						x = -88;
						y = -88;
					}
				}
					
				}
				Thread.sleep(100);
			}catch(Exception e){}
		}
	}
	class huantu implements Runnable {
		public void run(){
			try {
				while(zaime) {
					if(leixing==1){
						
					}
					else if(leixing == 2) {
						if(go ==1 ) {
							showImage = ImageIO.read(new File("diren2.png"));
						}
						else if(go == 2) {
							showImage = ImageIO.read(new File("diren3.png"));
						}
					}
					else if(leixing == 3) {
						if(megubian==1) {
							showImage = ImageIO.read(new File("diren9.png"));
							megubian = 2;
						}
						else if(megubian==2) {
							showImage = ImageIO.read(new File("diren8.png"));
							megubian = 1;
						}
					}
					else if(leixing==4) {
						if(go ==1 ) {
							showImage = ImageIO.read(new File("diren6.png"));
						}
						else if(go == 2) {
							showImage = ImageIO.read(new File("diren7.png"));
						}
					}
					else if(leixing==7) {
						if(go==1) {
							niao++;
							if(niao==3) 
								niao = 1;
							if(niao==1) {
								showImage = ImageIO.read(new File("niao3.png"));
							}
							else {
								showImage = ImageIO.read(new File("niao4.png"));
							}
						}
						if(go==2) {
							niao++;
							if(niao==3) 
								niao = 1;
							if(niao==1) {
								showImage = ImageIO.read(new File("niao1.png"));
							}
							else {
								showImage = ImageIO.read(new File("niao2.png"));
							}
						}
					}
					else if(leixing==8) {
						if(zhuanshu==1) {
							niao++;
							if(niao==3) 
								niao = 1;
							if(yun) {
								showImage = ImageIO.read(new File("zhaoyun5.png"));
							}
							else {
								if(niao==1) {
									showImage = ImageIO.read(new File("zhaoyun1.png"));
								}
								else if(niao==2){
									showImage = ImageIO.read(new File("zhaoyun3.png"));
								}
							}
						}
						if(zhuanshu==2) {
							niao++;
							if(niao==3) 
								niao = 1;
							if(yun) {
								showImage = ImageIO.read(new File("zhaoyun6.png"));
							}
							else {
								if(niao==1) {
									showImage = ImageIO.read(new File("zhaoyun2.png"));
								}
								else if(niao==2) {
									showImage = ImageIO.read(new File("zhaoyun4.png"));
								}
							}
						}
					}
					if(leixing==10) {
						if(go==1) {
							niao++;
							if(niao==26) 
								niao = 1;
							if(niao==1) {
								showImage = ImageIO.read(new File("jiangshi1.png"));
							}
							else if(niao ==6){
								showImage = ImageIO.read(new File("jiangshi2.png"));
							}
							else if(niao ==11){
								showImage = ImageIO.read(new File("jiangshi3.png"));
							}
							else if(niao ==16){
								showImage = ImageIO.read(new File("jiangshi4.png"));
							}
							else if(niao ==21){
								showImage = ImageIO.read(new File("jiangshi5.png"));
							}
						}
						if(go==2) {
							niao++;
							if(niao==26) 
								niao = 1;
							if(niao==1) {
								showImage = ImageIO.read(new File("jiangshi6.png"));
							}
							else if(niao==6){
								showImage = ImageIO.read(new File("jiangshi7.png"));
							}
							else if(niao==11){
								showImage = ImageIO.read(new File("jiangshi8.png"));
							}
							else if(niao==16){
								showImage = ImageIO.read(new File("jiangshi9.png"));
							}
							else if(niao==21){
								showImage = ImageIO.read(new File("jiangshi10.png"));
							}
						}
					}
					if(leixing==11) {
						if(go==1) {
							niao++;
							if(niao==3) 
								niao = 1;
							if(niao==1) {
								showImage = ImageIO.read(new File("bird1.png"));
							}
							else {
								showImage = ImageIO.read(new File("bird2.png"));
							}
						}
						if(go==2) {
							niao++;
							if(niao==3) 
								niao = 1;
							if(niao==1) {
								showImage = ImageIO.read(new File("bird3.png"));
							}
							else {
								showImage = ImageIO.read(new File("bird4.png"));
							}
						}
					}
					Thread.sleep(50);
				}
			}catch(Exception x){}
		}
	}
}
