package Game;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Zhangai implements Runnable{
	int x;
	int y;
	boolean zaime ;
	
	BufferedImage showImage = null;
	//马里奥处于移动状态需要场景向后移动时为TRUE
	static boolean marioMove = false;
	//当马里奥顶到障碍物时为true
	Thread zhangaigo;
	static boolean zhangaiMove = false;
	boolean ding = false;
	int leixing ;
	int stax,stay;
	public void remove() {
		y = stay;
		x = stax;
		ding = false;
		zhangaiMove = false;
		marioMove = false;
		if(leixing==2) {
			try {
				showImage = ImageIO.read(new File("zhangai2.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		if(leixing==11) {
			try {
				showImage = ImageIO.read(new File("zhangai15.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
	}
	Zhangai(int x,int y,int stax,int stay,int leixing) {
		zaime = true;
		this.x = x;
		this.y = y;
		this.stax = stax;
		this.stay = stay;
		this.leixing = leixing;
		if(leixing == 1) {
			if(ding)  {    //砖块顶过后消失
				try {
					
					showImage = ImageIO.read(new File("zhangai15.png"));
				}catch(Exception e) {
					System.out.print(e);
				}
			}
			else {
				try {
					
					showImage = ImageIO.read(new File("zhangai1.png"));
				}catch(Exception e) {
					System.out.print(e);
				}
			}
		}
		else if(leixing == 2) {
			if(ding) {
				try {
					showImage = ImageIO.read(new File("zhangai3.png")); //问号顶过后变成铁块
				}catch(Exception e) {
					System.out.print(e);
				}
			}
			else {
				try {
					showImage = ImageIO.read(new File("zhangai2.png"));
				}catch(Exception e) {
					System.out.print(e);
				}
			}
		}
		else if(leixing == 3) {
			try {
				showImage = ImageIO.read(new File("zhangai4.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 4) {
			try {
				showImage = ImageIO.read(new File("zhangai5.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 5) {
			try {
				showImage = ImageIO.read(new File("zhangai6.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 6) {
			try {
				showImage = ImageIO.read(new File("zhangai7.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 7) {
			try {
				showImage = ImageIO.read(new File("zhangai11.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 8) {
			try {
				showImage = ImageIO.read(new File("zhangai12.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 9) {
			try {
				showImage = ImageIO.read(new File("zhangai13.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing == 10) {
			try {
				showImage = ImageIO.read(new File("zhangai14.png"));
			}catch(Exception e) {
				System.out.print(e);
			}
		}
		else if(leixing ==11){
				try {
					showImage = ImageIO.read(new File("zhangai15.png"));
				}catch(Exception e) {}
		}
		else if(leixing ==12) {
			try {
				showImage = ImageIO.read(new File("zhangai5.png"));
			}catch(Exception e) {}
		}
		else if(leixing ==13) {
			try {
				showImage = ImageIO.read(new File("zhangai6.png"));
			}catch(Exception e) {}
		}
		else if(leixing ==14) {
			try {
				showImage = ImageIO.read(new File("me.png"));
			}catch(Exception e) {}
		}
		else if(leixing==15) {
			try {
				showImage = ImageIO.read(new File("qigan.png"));
			}catch(Exception e) {}
		}
		else if(leixing==16) {
			try {
				showImage = ImageIO.read(new File("qi.png"));
			}catch(Exception e) {}
		}
		else if(leixing==17) {
			try {
				showImage = ImageIO.read(new File("xiaofang.png"));
			}catch(Exception e) {}
		}
		else if(leixing==18) {
			try {
				showImage = ImageIO.read(new File("bimu1.png"));
			}catch(Exception e) {}
		}
		else if(leixing==19) {
			try {
				showImage = ImageIO.read(new File("bimu2.png"));
			}catch(Exception e) {}
		}
		else if(leixing==20) {
			try {
				showImage = ImageIO.read(new File("bimu3.png"));
			}catch(Exception e) {}
		}
		zhangaigo = new Thread(this);
			zhangaigo.start();
	}

	public void run() {
		while(zaime) {
			try {
				if(leixing==1) {
					if(ding) {
						x = -79;
						y = -81;
					}
				}
				if(leixing==2) {
					if(ding) {
						showImage = ImageIO.read(new File("zhangai3.png"));
					}
				}
				if(leixing==11) {
					if(ding) {
						showImage = ImageIO.read(new File("zhangai3.png"));
					}
				}
				Thread.sleep(50);
			}catch(Exception e) {
				System.out.print(e);
			}
		}
	}
}
