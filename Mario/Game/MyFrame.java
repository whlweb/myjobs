  package Game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements KeyListener, Runnable {
	
	static ArrayList zhangai = new ArrayList();
	
	static ArrayList direnlist = new ArrayList();
	
	boolean zhangaiyidong = false;
	
	int guanqia;
	
	ArrayList bimulist = new ArrayList();
	
	ArrayList bimudiren = new ArrayList();
	
	Mario m = new Mario();;
	
	int xMove;
	
	int heix,heiy;
	
	static boolean bimu = false;  //游戏结束
	
	static Thread bimushi;
	
	static boolean end = false;
	
	BufferedImage beijing = null; 
	
	BufferedImage heise = null;
	
	public static void main(String[]args) {
		new MyFrame();
	}
	public static Zhangai getzhangai(int x) {
		return (Zhangai)(zhangai.get(x));
	}
	public static int getzhangainum() {
		return zhangai.size();
	}
	
	public static diren getdiren(int x) {
		return (diren)(direnlist.get(x));
	}
	public static int getdirennum() {
		return direnlist.size();
	}
	public void paint(Graphics g) {
		BufferedImage image = new BufferedImage(980,700,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2 = image.getGraphics();
		g2.drawImage(beijing,0,0,this);
		
		for(int i=0;i<direnlist.size();i++) {
			diren d = (diren)(direnlist.get(i));
			g2.drawImage(d.showImage,d.x,d.y,this);
		}
		for(int i=0;i<zhangai.size();i++) {
			Zhangai z = (Zhangai)(zhangai.get(i));
			g2.drawImage(z.showImage,z.x,z.y,this);
		}
		g2.drawImage(m.showImage,m.x,m.y,this);
		if(bimu) {
			g2.drawImage(heise,heix,heiy,this);
		}
		if(heiy == 0) {
			for(int i=0;i<bimulist.size();i++) {
				Zhangai bi = (Zhangai)(bimulist.get(i));
				g2.drawImage(bi.showImage,bi.x,bi.y,this);
			}
			for(int i=0;i<bimudiren.size();i++) {
				diren bi = (diren)(bimudiren.get(i));
				g2.drawImage(bi.showImage,bi.x,bi.y,this);
			}
		}
		g.drawImage(image,0,0,this);
}
	
	MyFrame () {																//构造方法
		heix = 0;
		heiy = 700;
		bimushi = new Thread(new heihei());
		Mario.jumpTime= 0;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			beijing = ImageIO.read(new File("background.png"));
		}catch(Exception e) {
			System.out.println(e);
		}
		try {
			heise = ImageIO.read(new File("heise.png"));
		}catch(Exception e) {
			System.out.println(e);
		}
		Mario.isdead = false;
		Zhangai bimu1 = new Zhangai(210,700,210,700,18);
		bimulist.add(bimu1);
		Zhangai bimu2 = new Zhangai(210,1110,210,1110,19);
		bimulist.add(bimu2);
		Zhangai bimu3 = new Zhangai(210,1270,210,1270,20);
		bimulist.add(bimu3);
		
		
		
		diren yunge = new diren(840,980,840,980,8);
		bimudiren.add(yunge);
		
		
		
		guanqia = 1;                                                          //设置关卡
		setBounds(200,0,980,700);
		setVisible(true);
		addKeyListener(this);
		guanqiashezhi();
		run();
	}
	
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成方法存根
		int fangxiang = e.getKeyCode();
		if(fangxiang==37) {
			if(!(end&&m.x>=380))
				m.leftMove();
				Mario.leftan  = true;
			
		}
		else if(fangxiang==39){
			if(!(end&&m.x>=380))
				m.rightMove();
				Mario.rightan  = true;
		
		}
		else if(fangxiang==38) {
			if(!(end&&m.x>=380))
			m.jump();
			Mario.upan = true;
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO 自动生成方法存根
		int fangxiang = e.getKeyCode();
		if(fangxiang==37) {
			if(!(end&&m.x>=380))
			m.leftstop();
			Mario.leftan  = false;
		}
		else if(fangxiang==39){
			if(!(end&&m.x>=380))
			m.rightstop();
			Mario.rightan  = false;
		}
		
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO 自动生成方法存根

	}


	public void run() {
		// TODO 自动生成方法存根
		while(true) {
			if(end && Mario.x >= 380&&Mario.x<=390) {
				
				Mario.canmove = false;
			}
			if(Mario.isdead) {
				Mario.jumpTime =0;
				for(int i=0;i<direnlist.size();i++) {
					((diren)(direnlist.get(i))).dead = false;
					((diren)(direnlist.get(i))).remove();
				}
				for(int i=0;i<zhangai.size();i++) {
					((Zhangai)(zhangai.get(i))).remove();
				}
				m.x = 0;
				m.y = 490;
				m.zhuangtai = "right--standing";
				Mario.isdead = false;
				
			}
			if(m.x>930) {
				Mario.jumpTime =0;
				guanqia ++;
				for(int i=0;i<direnlist.size();i++) {
					((diren)(direnlist.get(i))).zaime = false;
					/*if(((diren)(direnlist.get(i))).leixing==8) {
						((diren)(direnlist.get(i))).leixing = 1;
							try {
								((diren)(direnlist.get(i))).showImage = ImageIO.read(new File("zhangai15.png"));
							}catch(Exception e) {
								System.out.print(e);
							}
					}*/
				}
				for(int i=0;i<zhangai.size();i++) {
					((Zhangai)(zhangai.get(i))).zaime = false;
				}
				zhangai.clear();
				direnlist.clear();
				guanqiashezhi();
				m.x = 0;
				m.y = 490;
			}
			this.repaint();
			try {
				Thread.sleep(20);
			}catch(Exception ke) {}
			if(bimu&&heiy==700) {
				bimushi.start();
			}
		}
	}
	void guanqiashezhi() {																	//关卡
		if(guanqia == 1) {
			zhangai.clear();
			
			for(int i=0;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			Zhangai wen1 = new Zhangai(140,350,140,350,2);
			Zhangai wen2 = new Zhangai(350,350,350,350,2);
			Zhangai wen3 = new Zhangai(490,350,490,350,2);
			Zhangai wen4 = new Zhangai(420,140,420,140,2);
			zhangai.add(wen1);
			zhangai.add(wen2);
			zhangai.add(wen3);
			zhangai.add(wen4);
			Zhangai qiang1 = new Zhangai(280,350,280,350,1);
			Zhangai qiang2 = new Zhangai(420,350,420,350,1);
			Zhangai qiang3 = new Zhangai(560,350,560,350,1);
			zhangai.add(qiang1);
			zhangai.add(qiang2);
			zhangai.add(qiang3);
			Zhangai guan1 = new Zhangai(770,490,770,490,10);
			Zhangai guan2 = new Zhangai(840,490,840,490,9);
			Zhangai guan3 = new Zhangai(770,560,770,560,7);
			Zhangai guan4 = new Zhangai(840,560,840,560,8);
			Zhangai guan5 = new Zhangai(770,630,770,630,7);
			Zhangai guan6 = new Zhangai(840,630,840,630,8);
			zhangai.add(guan1);
			zhangai.add(guan2);
			zhangai.add(guan3);
			zhangai.add(guan4);
			zhangai.add(guan5);
			zhangai.add(guan6);
			diren wugui = new diren (560,490,560,490,4);
			diren chi = new diren (700,490,700,490,4);
			diren megu = new diren (805,490,805,490,1);
			direnlist.add(megu);
			direnlist.add(wugui);
			direnlist.add(chi);
		}
		else if(guanqia == 3) {
			zhangai.clear();
			
			for(int i=0;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			Zhangai wen1 = new Zhangai(140,350,140,350,6);
			Zhangai wen2 = new Zhangai(350,350,350,350,6);
			Zhangai wen3 = new Zhangai(560,350,560,350,6);
			Zhangai wen4 = new Zhangai(770,350,770,350,6);
			zhangai.add(wen1);
			zhangai.add(wen2);
			zhangai.add(wen3);
			zhangai.add(wen4);
			Zhangai kong1 = new Zhangai(210,140,210,140,11);
			zhangai.add(kong1);
			Zhangai kong2 = new Zhangai(420,140,420,140,11);
			zhangai.add(kong2);
			Zhangai kong3 = new Zhangai(630,140,630,140,11);
			zhangai.add(kong3);
			diren hua = new diren (805,490,805,490,5);
			diren dan = new diren (805,490,805,490,6);
			diren chi = new diren (700,490,700,490,3);
			diren niao = new diren(840,140,840,140,7);
			direnlist.add(niao);
			direnlist.add(chi);
			direnlist.add(dan);
			direnlist.add(hua);
			}
		else if(guanqia == 4) {
			zhangai.clear();
			
			for(int i=0;i<6;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=8;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=1;i<5;i++) {
				for(int j=1;j<=i;j++) {
					Zhangai tie = new Zhangai((6-j)*70,(i+3)*70,(6-j)*70,(i+3)*70,3);
					zhangai.add(tie);
				}
			}
			for(int i=1;i<5;i++) {
				for(int j=1;j<=i;j++) {
					Zhangai tie = new Zhangai((j+7)*70,(i+3)*70,(j+7)*70,(i+3)*70,3);
					zhangai.add(tie);
				}
			}
			Zhangai guan1 = new Zhangai(840,490,840,490,10);
			Zhangai guan2 = new Zhangai(910,490,910,490,9);
			Zhangai guan3 = new Zhangai(840,560,840,560,7);
			Zhangai guan4 = new Zhangai(910,560,910,560,8);
			Zhangai guan5 = new Zhangai(840,630,840,630,7);
			Zhangai guan6 = new Zhangai(910,630,910,630,8);
			zhangai.add(guan1);
			zhangai.add(guan2);
			zhangai.add(guan3);
			zhangai.add(guan4);
			zhangai.add(guan5);
			zhangai.add(guan6);
			Zhangai kong = new Zhangai(420,70,420,70,2);
			zhangai.add(kong);
			Zhangai kong1 = new Zhangai(210,210,210,210,11);
			zhangai.add(kong1);
			Zhangai kong2 = new Zhangai(70,350,70,350,11);
			zhangai.add(kong2);
			Zhangai kong3 = new Zhangai(840,280,840,280,11);
			zhangai.add(kong3);
			diren hua = new diren (560,210,560,210,5);
			diren dan = new diren (560,210,560,210,6);
			direnlist.add(dan);
			direnlist.add(hua);
			diren hua1 = new diren (875,490,875,490,1);
			direnlist.add(hua1);
			
		}
		else if(guanqia == 5) {
			zhangai.clear();
			
			for(int i=0;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			Zhangai qiang1 = new Zhangai(210,420,210,420,1);
			Zhangai qiang2 = new Zhangai(280,420,280,420,1);
			Zhangai qiang3 = new Zhangai(490,210,490,210,1);
			Zhangai qiang4 = new Zhangai(560,210,560,210,1);
			Zhangai qiang5 = new Zhangai(700,350,700,350,1);
			Zhangai qiang6 = new Zhangai(770,350,770,350,1);
			Zhangai yin = new Zhangai(350,210,350,210,11);
			zhangai.add(yin);
			zhangai.add(qiang1);
			zhangai.add(qiang2);
			zhangai.add(qiang3);
			zhangai.add(qiang4);
			zhangai.add(qiang5);
			zhangai.add(qiang6);
			diren zhaoyun = new diren (770,420,770,420,8);
			direnlist.add(zhaoyun);
			diren hua = new diren (770,280,770,280,5);
			diren dan = new diren (770,280,770,280,6);
			direnlist.add(dan);
			direnlist.add(hua);
		}
		else if(guanqia ==6) {
			zhangai.clear();
			
			for(int i=0;i<8;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=9;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			Zhangai jiadi1 = new Zhangai(560,560,560,560,12);
			Zhangai jiadi2 = new Zhangai(560,630,560,630,13);
			zhangai.add(jiadi1);
			zhangai.add(jiadi2);
			Zhangai guan1 = new Zhangai(210,420,210,420,10);
			Zhangai guan2 = new Zhangai(280,420,280,420,9);
			Zhangai guan8 = new Zhangai(210,490,210,490,7);
			Zhangai guan7 = new Zhangai(280,490,280,490,8);
			Zhangai guan3 = new Zhangai(210,560,210,560,7);
			Zhangai guan4 = new Zhangai(280,560,280,560,8);
			Zhangai guan5 = new Zhangai(210,630,210,630,7);
			Zhangai guan6 = new Zhangai(280,630,280,630,8);
			zhangai.add(guan1);
			zhangai.add(guan2);
			zhangai.add(guan3);
			zhangai.add(guan4);
			zhangai.add(guan5);
			zhangai.add(guan6);
			zhangai.add(guan7);
			zhangai.add(guan8);
			Zhangai guan11 = new Zhangai(630,420,630,420,10);
			Zhangai guan22 = new Zhangai(700,420,700,420,9);
			Zhangai guan33 = new Zhangai(630,490,630,490,7);
			Zhangai guan44 = new Zhangai(700,490,700,490,8);
			Zhangai guan55 = new Zhangai(630,560,630,560,7);
			Zhangai guan66 = new Zhangai(700,560,700,560,8);
			Zhangai guan77 = new Zhangai(630,630,630,630,7);
			Zhangai guan88 = new Zhangai(700,630,700,630,8);
			zhangai.add(guan11);
			zhangai.add(guan22);
			zhangai.add(guan33);
			zhangai.add(guan44);
			zhangai.add(guan55);
			zhangai.add(guan66);
			zhangai.add(guan77);
			zhangai.add(guan88);
			for(int i = 0;i<4;i++) {
				Zhangai asd = new Zhangai((i+5)*70,350,(i+5)*70,350,11);
				zhangai.add(asd);
			}
			Zhangai kong = new Zhangai(0,350,0,350,11);
			Zhangai kong1 = new Zhangai(140,210,140,210,11);
			Zhangai kong2 = new Zhangai(210,0,210,0,11);
			zhangai.add(kong1);
			zhangai.add(kong);
			zhangai.add(kong2);
			diren hua1 = new diren(245,420,245,420,1);
			diren hua2 = new diren(665,420,665,420,1);
			direnlist.add(hua1);
			direnlist.add(hua2);
			diren megu = new diren(490,490,490,490,3);
			direnlist.add(megu);
			diren dan = new diren(910,70,910,70,9);
			direnlist.add(dan);
		}
		else if(guanqia ==8) {
			zhangai.clear();
			for(int i=0;i<2;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=2;i<7;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,12);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,13);
				zhangai.add(dd);
			}
			for(int i=7;i<8;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=8;i<11;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,12);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,13);
				zhangai.add(dd);
			}
			for(int i=11;i<12;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=12;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,12);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,13);
				zhangai.add(dd);
			}
			for(int i=2;i<5;i++) {
				Zhangai qian = new Zhangai(i*70,350,i*70,350,1);
				zhangai.add(qian);
			}
			for(int i=9;i<12;i++) {
				Zhangai qian = new Zhangai(i*70,350,i*70,350,1);
				zhangai.add(qian);
			}
			for(int i=5;i<9;i++) {
				Zhangai qian = new Zhangai(i*70,420,i*70,420,1);
				zhangai.add(qian);
			}
			for(int i=8;i<12;i++) {
				Zhangai jia = new Zhangai(i*70,210,i*70,210,11);
				zhangai.add(jia);
			}
			for(int i=6;i<10;i++) {
				Zhangai jia = new Zhangai(14*70,i*70,14*70,i*70,3);
				zhangai.add(jia);
			}
			Zhangai guan1 = new Zhangai(420,140,420,140,10);
			Zhangai guan2 = new Zhangai(490,140,490,140,9);
			Zhangai guan3 = new Zhangai(420,210,420,210,7);
			Zhangai guan4 = new Zhangai(490,210,490,210,8);
			zhangai.add(guan1);
			zhangai.add(guan2);
			zhangai.add(guan3);
			zhangai.add(guan4);
			Zhangai tie1 = new Zhangai(420,280,420,280,3);
			Zhangai tie2 = new Zhangai(490,280,490,280,3);
			zhangai.add(tie1);
			zhangai.add(tie2);
			Zhangai yin1 = new Zhangai(420,0,420,0,11);
			Zhangai yin2 = new Zhangai(490,0,490,0,11);
			zhangai.add(yin1);
			zhangai.add(yin2);
			Zhangai yin3 = new Zhangai(350,-70,350,-70,11);
			Zhangai yin4 = new Zhangai(560,-70,560,-70,11);
			zhangai.add(yin3);
			zhangai.add(yin4);
			diren zhaoyun = new diren(770,420,770,420,8);
			direnlist.add(zhaoyun);
			diren dan = new diren(910,70,910,70,9);
			direnlist.add(dan);
			diren dan1 = new diren(630,140,630,140,9);
			direnlist.add(dan1);
			diren hua = new diren(455,140,455,140,1);
			direnlist.add(hua);
			
		}
		else if(guanqia ==7) {
			zhangai.clear();
			
			for(int i=0;i<5;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=5;i<9;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,12);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,13);
				zhangai.add(dd);
			}
			for(int i=9;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			Zhangai me = new Zhangai(420,210,420,210,14);
			zhangai.add(me);
			for(int i=2;i<6;i++) {
				Zhangai tie = new Zhangai(350,i*70,350,i*70,3);
				zhangai.add(tie);
			}
			for(int i=2;i<6;i++) {
				Zhangai tie = new Zhangai(560,i*70,560,i*70,3);
				zhangai.add(tie);
			}
			for(int i=6;i<8;i++) {
				Zhangai tie = new Zhangai(i*70,140,i*70,140,3);
				Zhangai tie1 = new Zhangai(i*70,350,i*70,350,3);
				zhangai.add(tie);zhangai.add(tie1);
			}
			Zhangai jia1 = new Zhangai(140,350,140,350,11);
			zhangai.add(jia1);
			Zhangai jia2 = new Zhangai(140,140,140,140,11);
			zhangai.add(jia2);
			Zhangai jia3 = new Zhangai(210,140,210,140,11);
			zhangai.add(jia3);
			Zhangai jia4 = new Zhangai(280,140,280,140,11);
			zhangai.add(jia4);
			for(int i=0;i<14;i++) {
				Zhangai jia = new Zhangai(i*70,-70,i*70,-70,11);
				zhangai.add(jia);
			}
			diren niao1 = new diren(280,280,280,280,7);
			diren niao2 = new diren(630,280,630,280,7);
			diren niao3 = new diren(490,70,490,70,7);
			direnlist.add(niao1);
			direnlist.add(niao2);
			direnlist.add(niao3);
			diren megu = new diren (805,490,805,490,1);
			direnlist.add(megu);
			Zhangai guan1 = new Zhangai(770,490,770,490,10);
			Zhangai guan2 = new Zhangai(840,490,840,490,9);
			Zhangai guan3 = new Zhangai(770,560,770,560,7);
			Zhangai guan4 = new Zhangai(840,560,840,560,8);
			Zhangai guan5 = new Zhangai(770,630,770,630,7);
			Zhangai guan6 = new Zhangai(840,630,840,630,8);
			zhangai.add(guan1);
			zhangai.add(guan2);
			zhangai.add(guan3);
			zhangai.add(guan4);
			zhangai.add(guan5);
			zhangai.add(guan6);
		}
		else if(guanqia == 2) {
			zhangai.clear();
			
			for(int i=0;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			for(int i=3;i<9;i+=2) {
				Zhangai za = new Zhangai(i*70,350,i*70,350,2);
				zhangai.add(za);
			}
			Zhangai dsa = new Zhangai(350,140,350,140,2);
			zhangai.add(dsa);
			diren niao1 = new diren(0,140,0,140,7);
			direnlist.add(niao1);
			diren niao = new diren(700,140,700,140,11);
			direnlist.add(niao);
			diren jiangshi = new diren(630,490,630,490,10);
			direnlist.add(jiangshi);
			diren jiangshi1 = new diren(770,490,770,490,10);
			direnlist.add(jiangshi1);
			for(int i=10;i<13;i+=2) {
				Zhangai yun = new Zhangai(i*70,280,i*70,280,6);
				zhangai.add(yun);
			}
		}
		else {
			zhangai.clear();
			
			for(int i=0;i<14;i++) {
				Zhangai d = new Zhangai(i*70,560,i*70,560,4);
				zhangai.add(d);
				Zhangai dd = new Zhangai(i*70,630,i*70,630,5);
				zhangai.add(dd);
			}
			Zhangai fang = new Zhangai(770,350,770,350,17);
			zhangai.add(fang);
			Zhangai yin = new Zhangai(210,350,210,350,11);
			zhangai.add(yin);
			Zhangai tie = new Zhangai(420,490,420,490,3);
			zhangai.add(tie);
			Zhangai qigan = new Zhangai(420,70,420,70,15);
			Zhangai qi = new Zhangai(464,90,460,90,16);
			zhangai.add(qigan);
			zhangai.add(qi);
			end = true;
		}
	}
	public class heihei implements Runnable {
		public void run(){
			while(true) {
				if(heiy>0) {
					heiy -= 2;
				}
				if(heiy==0) {
					for(int i=0;i<bimulist.size();i++) {
						((Zhangai)(bimulist.get(i))).y-=1;
					}
					for(int i=0;i<bimudiren.size();i++) {
						((diren)(bimudiren.get(i))).y-=1;
						if(((diren)(bimudiren.get(i))).y<-140) {
							((diren)(bimudiren.get(i))).zaime = false;
							bimudiren.remove(i);
						}
					}
				}
				try {
					Thread.sleep(50);
				}catch(Exception ke) {}
			}
			
		}
	}

}
