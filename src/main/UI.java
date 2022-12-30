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
import Object.SuperObject;

public class UI {
	
	GamePanel gp;
	Font Puri; 
	Graphics2D g2;
	Font purisaB;
	Font superGame;
	Font lowBudget;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: the first screen, 1:...
	public int answerNum = 0;
	public int question = 0;
	public boolean correct = false;
	public int endNum = 0;
	
	BufferedImage keyImage;
	
	
//	double playTime;
//	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp)
	{
		this.gp=gp;
//		arial_40 = new Font("Arial",Font.PLAIN,40); // instantiate Font first therefore it has been done before the game starts -> not consume memory as the code below ( create 60 object per second )
//		arial_80B = new Font("Arial",Font.BOLD,80); // instantiate Font first therefore it has been done before the game starts -> not consume memory as the code below ( create 60 object per second )
		OBJ_Key key = new OBJ_Key(gp);
		keyImage = key.image;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/SuperGame-X3Rz9.ttf");
			superGame =  Font.createFont(Font.TRUETYPE_FONT, is);
			is = getClass().getResourceAsStream("/font/Low Budget.ttf");
			lowBudget =  Font.createFont(Font.TRUETYPE_FONT, is);
			
			
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
			g2.setFont(null);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
			g2.drawString("x"+gp.player.hasKey,74,65);
			g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
			
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
		
		//ANSWER STATE
		if(gp.gameState == gp.answerState)
		{
			g2.setFont(null);
			g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
			g2.drawString("x"+gp.player.hasKey,74,65);
			g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
			
			drawQuestionScreen();
		}
		
	
		//END STate
		
			if(gp.gameState == gp.endState)
			{
				drawEndScreen();
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
		g2.drawImage(gp.catP.catangry,9*gp.tileSize,gp.tileSize,4*gp.tileSize,3*gp.tileSize,null);
	}
	
	public void drawTitleScreen()
	{
		if(titleScreenState == 0) {
		g2.setFont(lowBudget);	
		g2.setColor(new Color(0,128,255));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		//TITLE NAME
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,70F));
		String text = "WHERE's  SAMMY?";
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
		g2.drawImage(gp.player.standDown[0],x,y,gp.tileSize*2,gp.tileSize*2,null);
		
		//MENU
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text,x,y);
		if(commandNum == 0)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text,x,y);
		if(commandNum == 1)
		{
			g2.drawString(">", x-gp.tileSize, y);
		}
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
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
			g2.setFont(lowBudget);
			g2.setColor(new Color(255,255,255)); 
			g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
			//CLASS SELECTION SCREEN
			g2.setColor(new Color(51,153,255));
			g2.setFont(g2.getFont().deriveFont(60F));
			
			String text = "Select Map";
			
			int x = getXforCenteredText(text);
			int y =gp.tileSize*3;
			
			g2.drawString(text,x,y);
			
			text = "JUNGLE";
			x = getXforCenteredText(text) ;
			y += gp.tileSize*3;
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.setColor(new Color(0,102,0));
			g2.drawString(text, x + gp.tileSize, y);
			if(commandNum == 0)
			{
				g2.drawString(">",x,y);
			} 
			
			text = "DESERT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.setColor(new Color(153,153,0));
			g2.drawString(text, x, y);
			if(commandNum == 1)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			text = "OCEAN";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(30F));
			g2.setColor(new Color(0,102,204));
			g2.drawString(text, x, y);
			if(commandNum == 2)
			{
				g2.drawString(">",x-gp.tileSize,y);
			}
			
			
			g2.setFont(g2.getFont().deriveFont(50F));
			text = "BACK";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.setColor(new Color(51,153,255));
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
		gp.stopMusic();
	}
	
	public void drawQuestionScreen()
	{
		switch (gp.currentMap)
		{
	case 0:
			if(question==0) {
			question("7 - 4 = ?","5","3","4");
			} 
			else if(question==1)
			{
			question("12 / 3 = ?","6","3","4");
			}
			else if(question==2)
			{
			question("21 - 17 = ?","4","7","8");
			}
			break;	
	case 1:
			if(question==0) {
			question("3 + 5 = ?","8","7","6");
			}
			else if(question==1)
			{
			question("10 / 2 = ?","5","4","8");
			}
			else if(question==2)
			{
			question("4 x 2 = ?","6","9","8");
			}
			break;
			
	case 2:
		if(question==0) {
		question("9 - 3 = ?","4","9","6");
		}
		else if(question==1)
		{
		question("4 x 2 = ?","8","5","4");
		}
		else if(question==2)
		{
		question("16 / 8 = ?","4","2","3");
		}
		break;
		}
	}
	public void drawEndScreen()
	{
		
		drawSubWindow(0,0,gp.screenWidth,gp.screenHeight);
		String text = "CONGRATULATION";
		String option_1 = "NEW GAME";
		String option_2  = "EXIT";
		g2.setColor(Color.yellow);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2 - 3*gp.tileSize;
		g2.drawString(text, x,y);
		g2.drawImage(gp.catP.catcry,5*gp.tileSize,4*gp.tileSize,4*gp.tileSize,3*gp.tileSize,null);
		
		g2.setColor(new Color(0,0,0,180));
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		//OPTION_1 
		int frameX = 2*gp.tileSize; 
		int frameY = 7*gp.tileSize ;
		int frameSizeX = 4*gp.tileSize;
		int frameSizeY = 4*gp.tileSize;
		g2.fillRoundRect(frameX,frameY,frameSizeX,frameSizeY, 20, 20);
		if(endNum==0)
		{
		drawSubWindow(frameX,frameY,frameSizeX,frameSizeY);
		}
		g2.setColor(Color.white);
		g2.drawString(option_1, frameX + gp.tileSize - 7,frameY +2*gp.tileSize);
		
		//OPTION_2
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,20F));
		g2.setColor(new Color(0,0,0,180));
		frameX = 8*gp.tileSize;
		g2.fillRoundRect(frameX,frameY,frameSizeX,frameSizeY, 20, 20);
		if(endNum==1)
		{
		drawSubWindow(frameX,frameY,frameSizeX,frameSizeY);
		}
		g2.setColor(Color.white);
		g2.drawString(option_2, frameX + gp.tileSize + 12,frameY + 2*gp.tileSize );
		
	}
	
	public int getXforCenteredText(String text)
	{
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	} 

	
	
	public void question(String question,String ans1,String ans2, String ans3) {
		g2.setColor(new Color(0,0,0,220));
		String line = "QUESTION";
		String quesText = question;
		String A = ans1;
		String B = ans2;
		String C = ans3;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		int x = getXforCenteredText(quesText);
		int y = gp.screenHeight/2;
		//BIG FRAME
		int frameSizeX = 12*gp.tileSize ;
		int frameSizeY = 4*gp.tileSize ;
		int frameX =gp.screenWidth/2 - frameSizeX/2 ;
		int frameY = gp.screenHeight/2 - frameSizeY/2 - 2*gp.tileSize ;
		g2.fillRoundRect(frameX, frameY,frameSizeX,frameSizeY, 40, 40);
		//SELECTING FRAME A 
		int selectFrameSizeX = 3*gp.tileSize ;
		int selectFrameSizeY = 3*gp.tileSize ;
		int selectFrameX =gp.tileSize ;
		int selectFrameY = frameY+ 5*gp.tileSize ;
		
		g2.setColor(new Color(0,0,0,180));
		g2.fillRoundRect(selectFrameX,selectFrameY,selectFrameSizeX,selectFrameSizeY, 20, 20);
		if(answerNum == 0)
		{
		drawSubWindow(selectFrameX,selectFrameY,selectFrameSizeX,selectFrameSizeY);
		}

		g2.setColor(Color.white);
		g2.drawString(A,selectFrameX + gp.tileSize , selectFrameY + 2*gp.tileSize);
		
		//SELECTING B
		selectFrameX += 5*gp.tileSize;
		g2.setColor(new Color(0,0,0,180));
		g2.fillRoundRect(selectFrameX,selectFrameY,selectFrameSizeX,selectFrameSizeY, 20, 20);
		if(answerNum == 1)
		{
		drawSubWindow(selectFrameX,selectFrameY,selectFrameSizeX,selectFrameSizeY);
		}
		
		g2.setColor(Color.white);
		g2.drawString(B,selectFrameX + gp.tileSize , selectFrameY + 2*gp.tileSize);
		
		//SELECTING C
		selectFrameX += 5*gp.tileSize;
		g2.setColor(new Color(0,0,0,180));
		g2.fillRoundRect(selectFrameX,selectFrameY,selectFrameSizeX,selectFrameSizeY, 20, 20);
		if(answerNum == 2)
		{
		drawSubWindow(selectFrameX,selectFrameY,selectFrameSizeX,selectFrameSizeY);
		}
		
		g2.setColor(Color.white);
		g2.drawString(C,selectFrameX + gp.tileSize , selectFrameY + 2*gp.tileSize);
		
		
		g2.drawString(quesText, x,y-gp.tileSize); 
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
		x = getXforCenteredText(line);
		g2.drawString(line, x,y-3*gp.tileSize); 	
	}
	
	

	
		
}
