package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import Object.OBJ_Key;

public class UI {
	
	GamePanel gp;
	Font Puri; 
	Graphics2D g2;
	Font purisaB;
	Font superGame;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1:...
	

	
//	double playTime;
//	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp)
	{
		this.gp=gp;
//		arial_40 = new Font("Arial",Font.PLAIN,40); // instantiate Font first therefore it has been done before the game starts -> not consume memory as the code below ( create 60 object per second )
//		arial_80B = new Font("Arial",Font.BOLD,80); // instantiate Font first therefore it has been done before the game starts -> not consume memory as the code below ( create 60 object per second )
////		OBJ_Key key = new OBJ_Key(gp);
////		keyImage = key.image;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/SuperGame-X3Rz9.ttf");
			superGame =  Font.createFont(Font.TRUETYPE_FONT, is);
			
			
		} catch (FontFormatException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	
	public void showMessage(String text)
	{
		message = text;
		messageOn = true;
		
	}
	
	
	public void draw(Graphics2D g2)
	{
		
		this.g2=g2;
		g2.setFont(purisaB);
		//g2.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); - ERROR somehow ???????
		g2.setColor(Color.white);
		//TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			g2.setFont(superGame);
			drawTitleScreen();
		}
		//PLAY STATE
		if(gp.gameState == gp.playState)
		{
			//do playstate
		}
		//PAUSE STATE
		if(gp.gameState == gp.pauseState)
		{
			drawPauseScreen();
		}
		//DIALOGUE STATE
		if(gp.gameState == gp.dialogueState)
		{
			drawDialogueScreen();
		}
	}
	public void drawDialogueScreen()
	{
		//WINDOW
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - gp.tileSize*4;
		int height = gp.tileSize*4;	
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,20F));
		x+=gp.tileSize;
		y+=gp.tileSize;
		
		for(String line : currentDialogue.split("\n"))
		{

			g2.drawString(line,x,y);
			y+=40;
		}		
	}
	
	public void drawTitleScreen()
	{
		if(titleScreenState == 0) {
		
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		String text = "ZOE FINDING SAMMY";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		//SHADOW
		g2.setColor(Color.GRAY);
		g2.drawString(text,x+5,y+5);
		
		//MAIN COLOR
		g2.setColor(Color.WHITE);
		g2.drawString(text,x,y);
		
		//CHACRACTER IMGAE
		x = gp.screenWidth/2 - gp.tileSize;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text,x,y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text,x,y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,60F));
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text,x,y);
		if(commandNum == 2)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		}
		else if(titleScreenState == 1)
		{
			
			//CLASS SELECTION SCREEN
			g2.setColor(Color.WHITE);
			g2.setFont(g2.getFont().deriveFont(30F));
			
			String text = "Select Color";
			
			int x = getXforCenteredText(text);
			int y =gp.tileSize*3;
			
			g2.drawString(text,x,y);
			
			text = "Black";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			text = "Gray";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			text = "Yellow";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			
			g2.setFont(g2.getFont().deriveFont(50F));
			text = "BACK";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
		}
		
		
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height)
	{
		Color c = new Color(0,0,0,200); // the last digit is the Opacity of window -> make the color lighter or heavier
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 40,40);
		
		c = Color.WHITE;
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5,y+5,width-10,height-10,40,40);
	}
	
	public void drawPauseScreen()
	{
		String text = "PAUSED";
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	} 

	
		
}
