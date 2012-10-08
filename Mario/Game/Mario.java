package Game;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Mario implements Runnable {
	
	static int x;
	static int y;
	int left = 6;
	int right = 1;
	static int jumpTime =0;
	static boolean isdead = false;
	static String zhuangtai;
	static boolean leftan = false,rightan = false;
	static BufferedImage showImage = null;
	Thread t1,t2;
	boolean canup = true;
	static boolean canmove = true;
	static boolean upan = false;
	int xMove,yMove;
	Mario() {
		try {
			showImage = ImageIO.read(new File("1.png"));
		}catch(Exception x) {}
		x = 0;
		y = 490;
		zhuangtai = "right--standing";
		t1 = new Thread(this);
		t1.start();
		t2 = new Thread(new endtime());
	}
	public void leftMove() {
		if(zhuangtai.indexOf("jumping")!=-1) {
			xMove = -7;
			zhuangtai = "left--jumping";
		}
		else {
			xMove = -7;
			zhuangtai = "left--moving";
		}
	}
	public void rightMove() {
		if(zhuangtai.indexOf("jumping")!=-1) {
			xMove = 7;
			zhuangtai = "right--jumping";
		}
		else {
			xMove = 7;
			zhuangtai = "right--moving";
		}
	}
	public void leftstop() {
		if(zhuangtai.indexOf("jumping")!=-1) {
			xMove = 0;
			zhuangtai = "left--jumping";
		}
		else {
			xMove = 0;
			zhuangtai = "left--standing";
		}
	}
	public void rightstop() {
		if(zhuangtai.indexOf("jumping")!=-1) {
			xMove = 0;
			zhuangtai = "right--jumping";
		}
		else {
			xMove = 0;
			zhuangtai = "right--standing";
		}
	}
	public void jump() {
		if(zhuangtai.indexOf("jumping")==-1) {
			if(zhuangtai.indexOf("left")!=-1) {
				zhuangtai = "left--jumping";
			}
			else {
				zhuangtai = "right--jumping";
			}
			yMove = -20;
			jumpTime = 11;
		}
	}
	public void down() {
			//System.out.println("down");
			if(zhuangtai.indexOf("left")!=-1) {
				zhuangtai = "left--jumping";
			}
			else {
				zhuangtai = "right--jumping";
			}
			yMove = 10;
			upan = false;
	
	}
	public void run() {
		while(true&&canmove) {
			try {
				for(int i=0;i<MyFrame.getdirennum();i++) {
					diren dr = MyFrame.getdiren(i);
					if(x>dr.x-70&&x<dr.x+70&&y==dr.y-70) {
						MyFrame.getdiren(i).dead = true;
					}
					if(x>dr.x-60&&x<dr.x+60&&y<dr.y+60&&y>dr.y-60&&dr.leixing!=8||y>700) {
						isdead = true;
					}
					if(x>dr.x-60&&x<dr.x+130&&y<dr.y+130&&y>dr.y-60&&dr.leixing==8) {
						isdead = true;
					}
				}
				if(y>700) {
					isdead = true;
				}
				canup = true;
				boolean onland = false;
				for(int i=0;i<MyFrame.getzhangainum();i++) {
					Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
					if(x>za.x-70&&x<za.x+70&&y==za.y-70&&!(za.leixing==11&&!za.ding)&&za.leixing!=12&&za.leixing!=13) {
						onland = true;
						
					}
					if(x>za.x-70&&x<za.x+70&&y<za.y+70&&y>za.y-70&&yMove<0) {
						canup = false;
						
						if(!canup&&upan&&yMove<0&&zhuangtai.indexOf("jumping")!=-1&&y>za.y) {
							MyFrame.getzhangai(i).ding = true;
						}
					}
					if(za.leixing==12&&x==za.x&&y>=za.y-70) {
						za.x = -80;
						za.y = -80;
					}
					if(za.leixing==13&&x==za.x&&y>=za.y-70) {
						za.x = -80;
						za.y = -80;
					}
				}
				if(onland) {
					if(yMove>0) {
						yMove = 0;
						if(zhuangtai.indexOf("left")!=-1) {
							if(leftan) {
								zhuangtai = "left--moving";
							}
							else
							zhuangtai = "left--standing";
						}
						else {
							if(rightan){
								zhuangtai = "right--moving";
							}
							else
							zhuangtai = "right--standing";
						}
					}
				}
				else {
					//System.out.println(jumpTime +"not onland");
					if(jumpTime==0&&!(x==0&&y==490)) {
						//System.out.print("xia");
						down();
					}
					if(yMove>0) {//ÏÂ½µ
						down();
					}
					else if (yMove<0) {//ÉÏÉý
						jumpTime--;
						if(!canup) {
							down();
							jumpTime = 0;
						}
						
					}
					
				}
				y += yMove;
				
				
				boolean canLeft= true;
				boolean canRight = true;
				for(int i=0;i<MyFrame.getzhangainum();i++) {
					Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
					if(y>za.y-70&&y<za.y+70&&x==za.x-70&&!(za.leixing==11&&!za.ding)) {
						canRight = false;
					}
					if(y>za.y-70&&y<za.y+70&&x==za.x+70&&!(za.leixing==11&&!za.ding)) {
						canLeft = false;
					}
				}
				if((canLeft&&x>0&&zhuangtai.indexOf("left")!=-1)||(canRight&&x<950&&zhuangtai.indexOf("right")!=-1))
				x += xMove;
				if(zhuangtai.indexOf("standing")!=-1) {
					if(zhuangtai.indexOf("left")!=-1) {
						showImage = ImageIO.read(new File("6.png"));
						left = 6;
					}
					else {
						showImage = ImageIO.read(new File("1.png"));
						right = 1;
					}
				}
				else if(zhuangtai.indexOf("right")!=-1&&zhuangtai.indexOf("moving")!=-1) {
					right ++;
					if(right ==5) {
						right = 1;
					}
					if(right == 1) {
						showImage = ImageIO.read(new File("1.png"));
					}
					else if(right==2) {
						showImage = ImageIO.read(new File("2.png"));
					}
					else if(right==3) {
						showImage = ImageIO.read(new File("3.png"));
					}
					else if(right==4) {
						showImage = ImageIO.read(new File("4.png"));
					}
				}
				else if(zhuangtai.indexOf("left")!=-1&&zhuangtai.indexOf("moving")!=-1) {
					left++;
					if(left == 10) {
						left = 6;
					}
					if(left == 6) {
						showImage = ImageIO.read(new File("6.png"));
					}
					else if(left==7) {
						showImage = ImageIO.read(new File("7.png"));
					}
					else if(left==8) {
						showImage = ImageIO.read(new File("8.png"));
					}
					else if(left==9) {
						showImage = ImageIO.read(new File("9.png"));
					}
				}
				else if(zhuangtai.indexOf("jumping")!=-1) {
					if(zhuangtai.indexOf("left")!=-1) {
						showImage = ImageIO.read(new File("10.png"));
					}
					else {
						showImage = ImageIO.read(new File("5.png"));
					}
				}
				Thread.sleep(50);
			}catch(Exception x){}
		}
		t2.start();
		xMove = 0;
	}
	public class endtime implements Runnable {
		public void run(){
			for(int i=0;i<MyFrame.getzhangainum();i++) {
				Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
				if(za.leixing == 16) {
					((Zhangai)(MyFrame.getzhangai(i))).y = y;
				}
			}
			while(true) {
				if(y<420) {
					down();
					xMove = 0;
				}
					
				else if(y==420) {
					yMove = 0;
					x = 490;
					y = 490;
					zhuangtai = "right--moving";
				}
				for(int i=0;i<MyFrame.getzhangainum();i++) {
					Zhangai za = (Zhangai)(MyFrame.getzhangai(i));
					if(za.leixing == 16&&y<420) {
						((Zhangai)(MyFrame.getzhangai(i))).y = y;
					}
				}
				y += yMove;
				if(y==490) {
					xMove = 5;
				}
				if(x==840){
					xMove = 0;
					zhuangtai ="right--standing";
					MyFrame.bimu = true;
				}
				x += xMove;
				try {
					if(x==840) {
						showImage = ImageIO.read(new File("zhangai15.png"));
					}
					else {
						if(zhuangtai.indexOf("standing")!=-1) {
							if(zhuangtai.indexOf("left")!=-1) {
								showImage = ImageIO.read(new File("6.png"));
								left = 6;
							}
							else {
								showImage = ImageIO.read(new File("1.png"));
								right = 1;
							}
						}
						else if(zhuangtai.indexOf("right")!=-1&&zhuangtai.indexOf("moving")!=-1) {
							right ++;
							if(right ==5) {
								right = 1;
							}
							if(right == 1) {
								showImage = ImageIO.read(new File("j.png"));
							}
							else if(right==2) {
								showImage = ImageIO.read(new File("2.png"));
							}
							else if(right==3) {
								showImage = ImageIO.read(new File("3.png"));
							}
							else if(right==4) {
								showImage = ImageIO.read(new File("4.png"));
							}
						}
						else if(zhuangtai.indexOf("left")!=-1&&zhuangtai.indexOf("moving")!=-1) {
							left++;
							if(left == 10) {
								left = 6;
							}
							if(left == 6) {
								showImage = ImageIO.read(new File("6.png"));
							}
							else if(left==7) {
								showImage = ImageIO.read(new File("7.png"));
							}
							else if(left==8) {
								showImage = ImageIO.read(new File("8.png"));
							}
							else if(left==9) {
								showImage = ImageIO.read(new File("9.png"));
							}
						}
						else if(zhuangtai.indexOf("jumping")!=-1) {
							if(zhuangtai.indexOf("left")!=-1) {
								showImage = ImageIO.read(new File("10.png"));
							}
							else {
								showImage = ImageIO.read(new File("5.png"));
							}
						}
					}
					Thread.sleep(100);
				}catch(Exception s) {}
			}
		}
	}
	
}