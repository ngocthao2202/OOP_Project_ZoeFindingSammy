package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public int worldX,worldY; // the Size screen of the whole map
	public int speed;
	public BufferedImage[] up,down,left,right,standRight,standLeft,standUp,standDown,cat_left_sleep,cat_left_sit,cat_left_run,cat_left_play,
	cat_right_sleep,cat_right_sit,cat_right_run,cat_right_play; // describes an image with an accessible buffer of image data (use this to store image files)
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,catcry,catangry;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 0;
	public int spriteNumStand = 0;
	public int spriteCounterStand = 0;
	public int spriteNumForNPC = 1;
	public int spriteNumCatSleep = 0,spriteNumCat = 0,spriteNumCatPlay = 0;
	public int spriteCounterCat = 0;
	
	
	public int actionLockCounter = 0;
	public String dialogues[] = new String[30];
	public int dialogueIndex = 0;
	public String preDirection = "down" ;
	
	public int status = 0;
	
	
	public Rectangle solidArea = new Rectangle(0,0,48,48); // use it to smooth the Characters on moving through Objects - Rectangle class can use to store x,y,width,height
	
	
	public int solidAreaDefaultX,solidAreaDefaultY;
	
	
	public boolean collisionOn =false;
	
	public Entity(GamePanel gp)
	{
		this.gp=gp;
		
		up = new BufferedImage[10];
		down = new BufferedImage[10];
		left = new BufferedImage[10];
		right = new BufferedImage[10];
		//
		standUp = new BufferedImage[3];
		standDown = new BufferedImage[3];
		standLeft = new BufferedImage[3];
		standRight = new BufferedImage[3];
		//
		cat_left_sleep = new BufferedImage[4];
		cat_left_sit = new BufferedImage[16];
		cat_left_run = new BufferedImage[16];
		cat_left_play = new BufferedImage[6];
		
		cat_right_sleep = new BufferedImage[4];
		cat_right_sit = new BufferedImage[16];
		cat_right_run = new BufferedImage[16];
		cat_right_play = new BufferedImage[6];
	}
	
	public void setAction()
	{	
	}
	
	public void speak()
	{
		if(dialogues[dialogueIndex] == null) {
			dialogueIndex=0;
		}
		gp.ui.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction)
		{ 
		case "up" :
			this.direction = "down";break;
		case "down" :
			this.direction = "up" ; break;
		case "left" :
			this.direction = "right"; break;
		case "right" :
			this.direction = "left"; break;
		}
	}
	
	public void update() {
		
	setAction();
	collisionOn = false;
	gp.cChecker.checkTile(this);
	gp.cChecker.checkObject(this, false);
	gp.cChecker.checkPlayer(this);
	
	//COLLISION FALSE -> ENTITY CAN MOVE
	if(this.collisionOn == false)
	{
		switch(direction) {
			case "up"   : worldY-=speed;break;
			case "down" : worldY+=speed;break;
			case "left" : worldX-=speed;break;
			case "right": worldX+=speed;break;
			
		}
	}
	
	spriteCounter++;
	if(spriteCounter>14) // 14 times of updating frame will change player image 1-2 and 2-1
	{
		if(spriteNumForNPC ==1)
		{
			spriteNumForNPC = 2;
		}
		else if(spriteNumForNPC==2)
		{
			spriteNumForNPC=1;
		}
		spriteCounter =0;
	}
	}
	
	
	public BufferedImage setUp (String imagePath)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaledImage(image, gp.tileSize,gp.tileSize);
			
		}catch(IOException e)
		{
			e.printStackTrace( );
		}
		
		return image;
	}
	
	public void draw(Graphics2D g2)
	{
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY; 
		//just draw what we need on the frame not whole the map at one time
		if( worldX +  gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - 2 * gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY +  gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - 2 * gp.tileSize < gp.player.worldY + gp.player.screenY)
		{
			switch(direction)
			{
				case"up":
					if(spriteNumForNPC == 1)
					{
						image = up1;
					}
					if(spriteNumForNPC ==2)
					{
						image = up2;
					}
					
					break;
				case"down":
					if(spriteNumForNPC ==1)
					{
						image = down1;
					}
					if(spriteNumForNPC ==2)
					{
						image=down2;
					}
					
					break;
				case"left":
					if(spriteNumForNPC ==1)
					{
						image = left1;
					}
					if(spriteNumForNPC ==2)
					{
						image = left2;
					}
					
					break;
				case"right":
					if(spriteNumForNPC ==1)
					{
						image = right1;
					}
					if(spriteNumForNPC ==2)
					{
						image = right2;
					}
					
					break;
			}
		g2.drawImage(image,screenX,screenY,null);
		}
	}

}
