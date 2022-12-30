package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Cat extends Entity {
	
	public Cat (GamePanel gp)
	{
		super(gp);
		direction = "right";
		speed = 1;
		getCatImage();
		setDialogue();
		//getCatPicture();
	}
	public void getCatImage()

	{
		cat_left_sleep[0] = setUp("/npc/cat_spirite_left_sleep_00");
		cat_left_sleep[1] = setUp("/npc/cat_spirite_left_sleep_01");
		cat_left_sleep[2] = setUp("/npc/cat_spirite_left_sleep_02");
		cat_left_sleep[3] = setUp("/npc/cat_spirite_left_sleep_03");
		
		//
		cat_left_sit[0] = setUp("/npc/cat_spirite_left_sit_00");
		cat_left_sit[1] = setUp("/npc/cat_spirite_left_sit_01");
		cat_left_sit[2] = setUp("/npc/cat_spirite_left_sit_02");
		cat_left_sit[3] = setUp("/npc/cat_spirite_left_sit_03");
		cat_left_sit[4] = setUp("/npc/cat_spirite_left_sit_04");
		cat_left_sit[5] = setUp("/npc/cat_spirite_left_sit_05");
		cat_left_sit[6] = setUp("/npc/cat_spirite_left_sit_06");
		cat_left_sit[7] = setUp("/npc/cat_spirite_left_sit_07");
		cat_left_sit[8] = setUp("/npc/cat_spirite_left_sit_08");
		cat_left_sit[9] = setUp("/npc/cat_spirite_left_sit_09");
		cat_left_sit[10] = setUp("/npc/cat_spirite_left_sit_10");
		cat_left_sit[11] = setUp("/npc/cat_spirite_left_sit_11");
		cat_left_sit[12] = setUp("/npc/cat_spirite_left_sit_12");
		cat_left_sit[13] = setUp("/npc/cat_spirite_left_sit_13");
		cat_left_sit[14] = setUp("/npc/cat_spirite_left_sit_14");
		cat_left_sit[15] = setUp("/npc/cat_spirite_left_sit_15");
		//
		cat_left_run[0] = setUp("/npc/cat_spirite_left_run_00");
		cat_left_run[1] = setUp("/npc/cat_spirite_left_run_01");
		cat_left_run[2] = setUp("/npc/cat_spirite_left_run_02");
		cat_left_run[3] = setUp("/npc/cat_spirite_left_run_03");
		cat_left_run[4] = setUp("/npc/cat_spirite_left_run_04");
		cat_left_run[5] = setUp("/npc/cat_spirite_left_run_05");
		cat_left_run[6] = setUp("/npc/cat_spirite_left_run_06");
		cat_left_run[7] = setUp("/npc/cat_spirite_left_run_07");
		cat_left_run[8] = setUp("/npc/cat_spirite_left_run_08");
		cat_left_run[9] = setUp("/npc/cat_spirite_left_run_09");
		cat_left_run[10] = setUp("/npc/cat_spirite_left_run_10");
		cat_left_run[11] = setUp("/npc/cat_spirite_left_run_11");
		cat_left_run[12] = setUp("/npc/cat_spirite_left_run_12");
		cat_left_run[13] = setUp("/npc/cat_spirite_left_run_13");
		cat_left_run[14] = setUp("/npc/cat_spirite_left_run_14");
		cat_left_run[15] = setUp("/npc/cat_spirite_left_run_15");
		//
		cat_left_play[0] = setUp("/npc/cat_spirite_left_play_00");
		cat_left_play[1] = setUp("/npc/cat_spirite_left_play_01");
		cat_left_play[2] = setUp("/npc/cat_spirite_left_play_02");
		cat_left_play[3] = setUp("/npc/cat_spirite_left_play_03");
		cat_left_play[4] = setUp("/npc/cat_spirite_left_play_04");
		cat_left_play[5] = setUp("/npc/cat_spirite_left_play_05");
		
		///////////////////
		
		cat_right_sleep[0] = setUp("/npc/cat_spirite_right_sleep_00");
		cat_right_sleep[1] = setUp("/npc/cat_spirite_right_sleep_01");
		cat_right_sleep[2] = setUp("/npc/cat_spirite_right_sleep_02");
		cat_right_sleep[3] = setUp("/npc/cat_spirite_right_sleep_03");
		//
		cat_right_sit[0] = setUp("/npc/cat_spirite_right_sit_00");
		cat_right_sit[1] = setUp("/npc/cat_spirite_right_sit_01");
		cat_right_sit[2] = setUp("/npc/cat_spirite_right_sit_02");
		cat_right_sit[3] = setUp("/npc/cat_spirite_right_sit_03");
		cat_right_sit[4] = setUp("/npc/cat_spirite_right_sit_04");
		cat_right_sit[5] = setUp("/npc/cat_spirite_right_sit_05");
		cat_right_sit[6] = setUp("/npc/cat_spirite_right_sit_06");
		cat_right_sit[7] = setUp("/npc/cat_spirite_right_sit_07");
		cat_right_sit[8] = setUp("/npc/cat_spirite_right_sit_08");
		cat_right_sit[9] = setUp("/npc/cat_spirite_right_sit_09");
		cat_right_sit[10] = setUp("/npc/cat_spirite_right_sit_10");
		cat_right_sit[11] = setUp("/npc/cat_spirite_right_sit_11");
		cat_right_sit[12] = setUp("/npc/cat_spirite_right_sit_12");
		cat_right_sit[13] = setUp("/npc/cat_spirite_right_sit_13");
		cat_right_sit[14] = setUp("/npc/cat_spirite_right_sit_14");
		cat_right_sit[15] = setUp("/npc/cat_spirite_right_sit_15");
		//
		cat_right_run[0] = setUp("/npc/cat_spirite_right_run_00");
		cat_right_run[1] = setUp("/npc/cat_spirite_right_run_01");
		cat_right_run[2] = setUp("/npc/cat_spirite_right_run_02");
		cat_right_run[3] = setUp("/npc/cat_spirite_right_run_03");
		cat_right_run[4] = setUp("/npc/cat_spirite_right_run_04");
		cat_right_run[5] = setUp("/npc/cat_spirite_right_run_05");
		cat_right_run[6] = setUp("/npc/cat_spirite_right_run_06");
		cat_right_run[7] = setUp("/npc/cat_spirite_right_run_07");
		cat_right_run[8] = setUp("/npc/cat_spirite_right_run_08");
		cat_right_run[9] = setUp("/npc/cat_spirite_right_run_09");
		cat_right_run[10] = setUp("/npc/cat_spirite_right_run_10");
		cat_right_run[11] = setUp("/npc/cat_spirite_right_run_11");
		cat_right_run[12] = setUp("/npc/cat_spirite_right_run_12");
		cat_right_run[13] = setUp("/npc/cat_spirite_right_run_13");
		cat_right_run[14] = setUp("/npc/cat_spirite_right_run_14");
		cat_right_run[15] = setUp("/npc/cat_spirite_right_run_15");
		//
		cat_right_play[0] = setUp("/npc/cat_spirite_right_play_00");
		cat_right_play[1] = setUp("/npc/cat_spirite_right_play_01");
		cat_right_play[2] = setUp("/npc/cat_spirite_right_play_02");
		cat_right_play[3] = setUp("/npc/cat_spirite_right_play_03");
		cat_right_play[4] = setUp("/npc/cat_spirite_right_play_04");
		cat_right_play[5] = setUp("/npc/cat_spirite_right_play_05");

	}
//	public void getCatPicture()
//	{
//		try
//		{
//			catcry = ImageIO.read(getClass().getResourceAsStream("/picture/catcry.png"));
//			catangry = ImageIO.read(getClass().getResourceAsStream("/picture/catangry.png"));
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
	public void setDialogue()
	{
		
		dialogues[0] = "NOOOOOOOOOOOOOO \n LET's ME GOOOOOOOO";
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
				
				case"left":
					//sleep
					if(status == 0)
					{
						if(cat_left_sleep[spriteNumCatSleep] != null)
						{
							image = cat_left_sleep[spriteNumCatSleep];
						}
					}
					//play
					else if(status == 2)
					{
						if(cat_left_play[spriteNumCatPlay] != null)
						{
							image = cat_left_play[spriteNumCatPlay];
						}
					}
					//sit
					else if(status == 1)
					{
						if(cat_left_sit[spriteNumCat] != null)
						{
							image = cat_left_sit[spriteNumCat];
						}
					}
				
					//run
					else if(status == 3)
					{
						if(cat_left_run[spriteNumCat] != null)
						{
							image = cat_left_run[spriteNumCat];
						}
					}
					
					
					break;
				case"right":
					//sleep
					if(status == 0)
					{
						if(cat_right_sleep[spriteNumCatSleep] != null)
						{
							image = cat_right_sleep[spriteNumCatSleep];
						}
					}
					//play
					else if(status == 2)
					{
						if(cat_right_play[spriteNumCatPlay] != null)
						{
							image = cat_right_play[spriteNumCatPlay];
						}
					}
					//sit
					else if(status == 1)
					{
						if(cat_right_sit[spriteNumCat] != null)
						{
							image = cat_right_sit[spriteNumCat];
						}
					}
					
					//run
					else if(status == 3)
					{
						if(cat_right_run[spriteNumCat] != null)
						{
							image = cat_right_run[spriteNumCat];
						}
					}
					break;
			}
		g2.drawImage(image,screenX,screenY,null);
		}
	}
	public void setAction()
	{
		actionLockCounter ++;
		if(actionLockCounter == 180) {
			Random random = new Random();
			int i = random.nextInt(4);
			if(i==0)
			{
				status = 0;//sleep
			}
			else if(i==1)
			{
				status = 1;//sit
			}
			else if(i==2)
			{
				status = 2;//play
			}
			else if(i==3)
			{
				status = 3;//run
			}
			if(i>=2)
			{
				direction = "left";
			}
			if(i<2)
			{
				direction = "right";
			}
			actionLockCounter = 0;
			}
	}
	public void update() {
		
		setAction();
		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
		
		//COLLISION FALSE -> ENTITY CAN MOVE
		if(this.collisionOn == false )
		{ 
			if(status == 3) {
			switch(direction) {	
				case "left" : worldX-=speed;break;
				case "right": worldX+=speed;break;	
			}
		}
		}
		
		spriteCounterCat++;
		if(spriteCounterCat>15) // 14 times of updating frame will change player image 1-2 and 2-1
		{
			spriteNumCat++;
			spriteNumCatSleep++;
			spriteNumCatPlay++;
			
			if(spriteNumCat > 15) {
				spriteNumCat = 0;
			}
			if(spriteNumCatSleep >3)
			{
				spriteNumCatSleep = 0;
			}
			if(spriteNumCatPlay >5)
			{
				spriteNumCatPlay = 0;
			}
			spriteCounterCat = 0;
		}
	}
}
