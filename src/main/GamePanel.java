package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

import javax.swing.JPanel;

import Object.SuperObject;
import entity.Entity;
import entity.Player;
import tile.TileManager;
public class GamePanel extends JPanel implements Runnable {// Runnable is Interface(implements) - Thread class is a super class (extends)
	//SCREEN SETTING
	final int originalTileSize = 16;//16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;//48x48
	public final int maxScreenCol = 16;
	public int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol*tileSize;//768 pixels
	public final int screenHeight = maxScreenRow*tileSize;//576 pixelsm
	
	//WORLD-MAP SETTING
	public final int maxWorldCol = 50; 
	public final int maxWorldRow = 50;
	
	
	//FPS
		int FPS = 60;
	
	
	//SYSTEM
	public KeyHandler keyH = new KeyHandler(this);
	TileManager tileM = new TileManager(this);
	Sound music = new Sound();
	Sound se = new Sound();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	
	public UI ui = new UI(this);
	
	Thread gameThread;//use to skip Program running
	
	
	
	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[] = new SuperObject[10]; // suppose we will have 10 distinct objects in this game
	public Entity npc[] = new Entity[10];
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	
	
		
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));//Create size for the panel
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);//Make the panel paint behind objects( does not overlap objects ) -> enhance Rendering performance 
		this.addKeyListener(keyH);
		this.setFocusable(true);//this GamePanel can be " focused " to receive key input
	}
	
	public void setupGame()
	{
		aSetter.setObject();
		aSetter.setNPC();
		//playMusic(0);
		gameState = titleState;
		
		//stopMusic();
			
	}
	
	
	public void startGameThread()
	{
		gameThread = new Thread(this); // "this" here use to call Constructor "GamePanel() "
		gameThread.start();
	}
	
// 2 Popular method for Creating FPS
	//METHOD 1:
	/*public void run()
	{
		double drawInterval = Math.pow(10,9)/FPS; // in- nanosecond 
		double nextDrawTime = System.nanoTime()+drawInterval; 
		
	
		while (gameThread != null)
		{
			long currentTime = System.nanoTime(); // return the current value of the Running java virtual Machine's  High-resolution time source - in nanoseconds 
													//10^9 nanoseconds = 1 second;
			//long currentTime2 = System.currentTimeMillis(); //10^3 milliseconds = 1 second;
			
			
			
			update();
			repaint();//the strange way to call paintComponent method !!!!!!!
			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/Math.pow(10,6);//have this line because the method Sleep below just accept Millisecond
				Thread.sleep((long) remainingTime); // will not do anything until sleeping time is over
				if(remainingTime <0)
				{
					remainingTime = 0;
				}
				nextDrawTime += drawInterval; 
				System.out.println(System.nanoTime());
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}*/
	//METHOD 2:
	public void run()
	{
		double drawInterval = Math.pow(10,9)/FPS;
		double delta =0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		
		
		while(gameThread !=null)
		{
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/ drawInterval;
			lastTime = currentTime;
			if(delta>=1) {
			update();
			repaint();
			delta--;
			}
		}
	}
		
		//1 - UPDATE : update information such as Character position
		//2- DRAW : draw the Screen with the updated information
		//FPS : for example fps(Frame Per Second) 60 means UPDATE and DRAW 60 times per second 
	
	public void update() {
	if(gameState == playState)
	{
		//PLAYER
	player.update();
		//NPC
	for(int i = 0;i<npc.length;++i)
	{
		if(npc[i] != null)
		{
			npc[i].update();
		}
	}
	
	}
	else if(gameState == pauseState)
	{
	///	
	}
	}
	
	public void paintComponent(Graphics g) //Built-in method in java // call this method by - repaint()!!!!!!!!!!!
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		//DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true)
		{
		drawStart = System.nanoTime();
		}
		
		//TITLE SCREEN
		if(gameState == titleState)
		{
			
		}
		//ORTHERs
		else {
		//player.draw(g2); // must not put line code of "player.draw()" here Because it likes a layer so the map will hide the player
		//TILE
		tileM.draw(g2);
		
		//OBJECT
				for(int i=0;i<obj.length;++i)
				{
					if(obj[i] !=null) // check the object in Array because there maybe a NUll slot in the array of Object that could cause ERROR	
					{
						obj[i].draw(g2,this);
					}
				}
				
		//NPC
				for(int i=0;i<npc.length;++i)
				{
					if(npc[i] !=null) 	
					{
						npc[i].draw(g2);
					}
				}
				
				
		//PLAYER
		player.draw(g2);
		
		
		
		
		}
		//SYSTEM
		ui.draw(g2);
//		//OBJECT ( place the codes here will display the key upon the player when they hit - should not that logic)
//		for(int i=0;i<obj.length;++i)
//		{
//			if(obj[i]!=null) // check slot the OBJECT gonna place in Empty to avoid ERROR
//			{
//				obj[i].draw(g2,this);
//			}
//		}
		
		if(keyH.checkDrawTime == true)
		{
		long drawEnd = 0;
		drawEnd = System.nanoTime();
		long intervalDraw_StartToEnd = drawEnd - drawStart;
		g2.setColor(Color.CYAN);
		g2.drawString("Draw Time: "+intervalDraw_StartToEnd, 20,300);
		}
		
		
		g2.dispose();//Program still works without this code but NEED to have to save memories of computer // Dispose of this Graphics context and release any system resources that it is using
		
	}
	public void playMusic(int i)
	{
			music.setFile(i); // the 3 lines are the sequence set - play - loop
			music.play(); //
			music.loop(); // 
	}
	
	public void stopMusic()
	{
		music.stop();
	}
	
	public void playSE(int i) //sound effect
	{
		se.setFile(i);
		se.play();
	} 

}
