package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;

import javax.swing.JPanel;

import Object.SuperObject;
import entity.CatPicture;
import entity.Entity;
import entity.Player;

import tile.TileManager;
public class GamePanel extends JPanel implements Runnable {// Runnable is Interface(implements) - Thread class is a super class (extends)
	//SCREEN SETTING
	final int originalTileSize = 16;//16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;//48x48
	public final int maxScreenCol = 15;
	public int maxScreenRow = 12;
	public final int screenWidth = maxScreenCol*tileSize;//768 pixels
	public final int screenHeight = maxScreenRow*tileSize;//576 pixelsm
	
	
	//WORLD-MAP SETTING
	public final int maxWorldCol = 60; 
	public final int maxWorldRow = 60;
	public final int maxMap = 10;
	public int currentMap = 0;

	//FPS
		int FPS = 60;
	
	
	//SYSTEM
	public KeyHandler keyH = new KeyHandler(this);
	TileManager tileM = new TileManager(this);
	Sound music = new Sound();
	Sound se = new Sound();
	CatPicture catP = new CatPicture(this);
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	
	public UI ui = new UI(this);
	
	Thread gameThread;//use to skip Program running
	

	
	//ENTITY AND OBJECT
	public Player player = new Player(this,keyH);
	public SuperObject obj[][] = new SuperObject[maxMap][24]; // suppose we will have 10 distinct objects in this game
	public Entity npc[][] = new Entity[maxMap][24];
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int answerState = 4;
	public final int endState = 5;
	
		
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
	public void restart()
	{
		player.setDefaultValues();
		aSetter.setObject();
		aSetter.setNPC();
		player.interactNPCCouter = 0;
		ui.answerNum = 0;
		ui.question = 0;
		ui.endNum = 0;
		ui.correct = false;
		ui.commandNum = 0;
		
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
		
		System.out.println("rowx:" + (player.worldX/48));
		System.out.println("coly :" + (player.worldY/48 + 1) );
		
		//PLAYER
	player.update();
		//NPC
	for(int i = 0;i<npc[1].length;++i)
	{
		if(npc[currentMap][i] != null)
		{
			npc[currentMap][i].update();
		}
	}
	
	} 
	else if(gameState == pauseState)
	{
	///	
	}
	
	else if(gameState == answerState)
	{
		//
		
	}
	else if(gameState == endState)
	{
		
	}
	
	}
	
	public void paintComponent(Graphics g) //Built-in method in java // call this method by - repaint()!!!!!!!!!!!
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		//TITLE SCREEN
		if(gameState == titleState )
		{
			
		}
		//ORTHERs

		else
		{
			tileM.draw(g2);
			
			
			//OBJECT
					for(int i=0;i<obj[1].length;++i)
					{
						if(obj[currentMap][i] !=null) // check the object in Array because there maybe a NUll slot in the array of Object that could cause ERROR	
						{
							obj[currentMap][i].draw(g2,this);
						}
					}
					
			//NPC
					for(int i=0;i<npc[1].length;++i)
					{
						if(npc[currentMap][i] !=null) 	
						{
							npc[currentMap][i].draw(g2);
						} 
					}
			//PLAYER
			player.draw(g2);		
					
		}
		
		
		//SYSTEM
		ui.draw(g2);
		
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
