
package entity;

//
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


import main.GamePanel;
import main.KeyHandler;



public class Player extends Entity{
	
	KeyHandler keyH;
	public final int screenX;// screen X and Y identify the screen surround the centered Player
	public final int screenY;//
	public int hasKey = 0;
	public int interactNPCCouter = 0;
	
	
	public Player(GamePanel gp, KeyHandler keyH)
	{
		super(gp);
		this.keyH=keyH;
		
		
		this.screenX=gp.screenWidth/2-gp.tileSize;
		this.screenY=gp.screenHeight/2-gp.tileSize;
		
		solidArea = new Rectangle();
		
		solidArea.x = 17;
		solidArea.y = 34;
		solidArea.width = 14;
		solidArea.height = 10;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		
		this.setDefaultValues();
		this.getPlayerImage();
		
		
	}
	public void setDefaultValues()
	{
		
		worldX = gp.tileSize*12;
		worldY = gp.tileSize*24;
		speed = 3;
		direction = "down";
		
	
	}
	public void  getPlayerImage()
	{
		
			down[0] = setUp("/player/girl_down_000");
			down[1] = setUp("/player/girl_down_001");
			down[2] = setUp("/player/girl_down_002");
			down[3] = setUp("/player/girl_down_003");
			down[4] = setUp("/player/girl_down_004");
			down[5] = setUp("/player/girl_down_005");
			down[6] = setUp("/player/girl_down_006");
			down[7] = setUp("/player/girl_down_007");
			down[8] = setUp("/player/girl_down_008");
			down[9] = setUp("/player/girl_down_009");
			
			up[0] = setUp("/player/girl_up_000");
			up[1] = setUp("/player/girl_up_001");
			up[2] = setUp("/player/girl_up_002");
			up[3] = setUp("/player/girl_up_003");
			up[4] = setUp("/player/girl_up_004");
			up[5] = setUp("/player/girl_up_005");
			up[6] = setUp("/player/girl_up_006");
			up[7] = setUp("/player/girl_up_007");
			up[8] = setUp("/player/girl_up_008");
			up[9] = setUp("/player/girl_up_009");
			
			left[0] = setUp("/player/girl_left_000");
			left[1] = setUp("/player/girl_left_001");
			left[2] = setUp("/player/girl_left_002");
			left[3] = setUp("/player/girl_left_003");
			left[4] = setUp("/player/girl_left_004");
			left[5] = setUp("/player/girl_left_005");
			left[6] = setUp("/player/girl_left_006");
			left[7] = setUp("/player/girl_left_007");
			left[8] = setUp("/player/girl_left_008");
			left[9] = setUp("/player/girl_left_009");
			
		
			right[0] = setUp("/player/girl_right_000");
			right[1] = setUp("/player/girl_right_001");
			right[2] = setUp("/player/girl_right_002");
			right[3] = setUp("/player/girl_right_003");
			right[4] = setUp("/player/girl_right_004");
			right[5] = setUp("/player/girl_right_005");
			right[6] = setUp("/player/girl_right_006");
			right[7] = setUp("/player/girl_right_007");
			right[8] = setUp("/player/girl_right_008");
			right[9] = setUp("/player/girl_right_009");
			
			standUp[0] = setUp("/player/girl_up_stand_000");
			standUp[1] = setUp("/player/girl_up_stand_001");
			standUp[2] = setUp("/player/girl_up_stand_002");
			
			standDown[0] = setUp("/player/girl_down_stand_000");
			standDown[1] = setUp("/player/girl_down_stand_001");
			standDown[2] = setUp("/player/girl_down_stand_002");
			
			standRight[0] = setUp("/player/girl_right_stand_000");
			standRight[1] = setUp("/player/girl_right_stand_001");
			standRight[2] = setUp("/player/girl_right_stand_002");
			
			standLeft[0] =  setUp("/player/girl_left_stand_000");
			standLeft[1] =  setUp("/player/girl_left_stand_001");
			standLeft[2] =  setUp("/player/girl_left_stand_002");
			
		
	}

	
	public void update()//update frame 60 times per second
	{
		//FIRST CHECK DIRECTION
		if(keyH.upPressed==false && keyH.downPressed==false && keyH.leftPressed==false && keyH.rightPressed==false)
		{
			direction = "stand";
			spriteCounterStand++;
			if(spriteCounterStand>30) // 14 times of updating frame will change player image 1-2 and 2-1
			{
				spriteNumStand++;
				if(spriteNumStand > 2) {
					spriteNumStand = 0;
				}
				spriteCounterStand = 0;
			}
		
		}
		else if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true)
		{
		if(keyH.upPressed)
		{
			direction = "up";
			
		}
		else if(keyH.downPressed)
		{
			direction = "down";
			
		}
		else if(keyH.leftPressed)
		{
			direction = "left";
			
		}
		else if(keyH.rightPressed)
		{
			direction = "right";
			
		}
		preDirection = direction;
		//SECOND CHECK WHETHER THE PLAYER CAN MOVE OR NOT
		
		//CHECK TILE COLLISION
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
//CHECK OBJECT COLLISION
	int objIndex = gp.cChecker.checkObject(this,true);
	pickUpObject(objIndex);
	
	//CHECK NPC COLLISION
	int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
	interactNPC(npcIndex);
		
		
		//IF COLLISSON " false " THE PLAYER CAN MOVE,ORTHERWISE STOP
		if(this.collisionOn == false)
		{
			switch(direction) {
				case "up"   : worldY-=speed;break;
				case "down" : worldY+=speed;break;
				case "left" : worldX-=speed;break;
				case "right": worldX+=speed;break;
				
		}}
		spriteCounter++;
		if(spriteCounter>10) // 14 times of updating frame will change player image 1-2 and 2-1
		{
			spriteNum++;
			if(spriteNum > 9) {
				spriteNum = 0;
			}
			spriteCounter = 0;
		}
		}
}
	
	
	public void pickUpObject(int index) 

	{
		if(index != 999)//Every number is fine as long as not used by the object'array index ex: 888 or 567
		{
			String objectName = gp.obj[gp.currentMap][index].name;
			switch(objectName)
			{
			case "Key":
				
				if(gp.ui.correct == true) {
				gp.obj[gp.currentMap][index]=null;
				gp.ui.correct = false;
				hasKey++;
				gp.playSE(6);
				}
				else if(gp.ui.correct == false)
				{
				gp.gameState = gp.answerState;
				}
				
			 
			break;
			case "Door":
				if(hasKey>0)
				{
					gp.obj[gp.currentMap][index]=null; 
					hasKey--;
					gp.playSE(7);
				}
				
			break;
			}
			
	}
}
	public void interactNPC(int index)
	{
		if(index != 999)
		{
			if(gp.keyH.enterPressed == true)
			{
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][index].speak();
				interactNPCCouter++;
			}
			gp.keyH.enterPressed = false;
			if(interactNPCCouter > 1)
			{
				gp.gameState = gp.endState;
				gp.stopMusic();
				gp.playSE(12);
				gp.playSE(13);
				interactNPCCouter = 0;
			}
			
		}
	}


	
	public void draw(Graphics2D g2)
	{
//		g2.setColor(Color.WHITE);
//		
//		g2.fillOval(x,y,gp.tileSize,gp.tileSize);
		BufferedImage image = null;
		switch(direction)
		{
			case"up":
				if(up[spriteNum]!=null) {
				image = up[spriteNum];}break;
			case"down":
				if(down[spriteNum]!=null) {
				image = down[spriteNum];}break;
			case"left":
				if(left[spriteNum]!=null) {
				image = left[spriteNum];}break;
			case"right":
				if(right[spriteNum]!=null) {
				image = right[spriteNum];}break;
			case"stand":
				if(preDirection == "up")
				{
					if(up[spriteNumStand]!=null) {
				image = standUp[spriteNumStand];}
				}
				if (preDirection == "down")
				{
					if(left[spriteNumStand]!=null) {
				image = standDown[spriteNumStand];}
				}
				if (preDirection == "right")
				{
					if(right[spriteNumStand]!=null) {
				image = standRight[spriteNumStand];}
				}
				if( preDirection == "left")
				{
					if(left[spriteNumStand]!=null) {
				image = standLeft[spriteNumStand];}
				}
		}
			g2.drawImage(image,screenX,screenY,null);
				
			
		
		}
}

