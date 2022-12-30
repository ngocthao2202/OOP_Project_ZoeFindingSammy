package main;

import entity.Entity;

public class CollisionChecker {
	public GamePanel gp;
	public CollisionChecker(GamePanel gp)
	{
		this.gp=gp;
	}
	public void checkTile(Entity entity)
	{
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1,tileNum2;
		
		switch(entity.direction) 
		{
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize; // minus entity.speed to make the display more likely " right "
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision==true)
			{
				entity.collisionOn = true;
			}
			break;
		case "down":
			 entityBottomRow = (entityBottomWorldY - entity.speed)/gp.tileSize;
			 tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			 tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			 if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision==true)
			 {
					entity.collisionOn = true;
			 }
			 
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			 tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			 tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			 if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision==true)
			 {
					entity.collisionOn = true;
			 }
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			 tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			 tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			 if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision==true)
			 {
					entity.collisionOn = true;
			 }
			break;
		}
		
	}
	
	
	public int checkObject(Entity entity, boolean player) // this boolean use for many case in the future for object collides another object - in this case boolean is true for player and false for other objects
	{													// Ex: player can pick up Objects and NPC cannot pick up Objects
		
		int index = 999; //Every number is fine as long as not used by the object'array index ex: 888 or 567
		
		for(int i = 0; i<gp.obj[1].length;++i)
		{
			if(gp.obj[gp.currentMap][i]!=null)  // this line to check the Object in array is not null to prevent ERROR nullPoint  
			{ 
				
			//get entity's solid area position
			entity.solidArea.x = entity.worldX + entity.solidArea.x; 
			entity.solidArea.y = entity.worldY + entity.solidArea.y; 
			
			//get the object's solid area position
			gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;//in those cases the solidArea x and y of object is 0 
			gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;//-> we can manipulate those parameter of Object as we want
			
			switch(entity.direction)
			{
			case "up" :
				entity.solidArea.y -= entity.speed;
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
				{
					if(gp.obj[gp.currentMap][i].collision==true)
					{
						entity.collisionOn = true;
					}
					if(player == true)
					{
						index = i;
					}
				}
				break;
			case "down" :
				entity.solidArea.y += entity.speed;
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
				{
					if(gp.obj[gp.currentMap][i].collision==true)
					{
						entity.collisionOn = true;
					}
					if(player == true)
					{
						index = i;
					}
				}
				break;
			case "left" :
				entity.solidArea.x -= entity.speed;
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
				{
					if(gp.obj[gp.currentMap][i].collision==true)
					{
						entity.collisionOn = true;
					}
					if(player == true)
					{
						index = i;
					}
				}
				break;
			case "right" :
				entity.solidArea.x += entity.speed;
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea))
				{
					if(gp.obj[gp.currentMap][i].collision==true)
					{
						entity.collisionOn = true;
					}
					if(player == true)
					{
						index = i;
					}
				}
				break;
				
			}
			
			
			
			entity.solidArea.x = entity.solidAreaDefaultX; //because the entity.solidaArea x and y above will be increased by entity.world x and y
			entity.solidArea.y = entity.solidAreaDefaultY; //Continually therefore we have to set them back
			
			gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;// and the same case for Object
			gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
			
			}	
		}
		
		return index;
	}
	
	//NPC OR MONSTER
	public int checkEntity(Entity entity,Entity[][] target)
	{
		int index = 999; //Every number is fine as long as not used by the object'array index ex: 888 or 567
		
		for(int i = 0; i<target[1].length;++i) 
		{
			if(target[gp.currentMap][i]!=null)  // this line to check the Object in array is not null to prevent ERROR nullPoint  
			{ 
				
			//get entity's solid area position
			entity.solidArea.x = entity.worldX + entity.solidArea.x; 
			entity.solidArea.y = entity.worldY + entity.solidArea.y;
			
			//get the object's solid area position
			target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;//in those cases the solidArea x and y of object is 0 
			target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;//-> we can manipulate those parameter of Object as we want
			
			switch(entity.direction)
			{
			case "up" :
				entity.solidArea.y -= entity.speed;
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea))
				{
						entity.collisionOn = true;
						index = i;
				}
				break;
			case "down" :
				entity.solidArea.y += entity.speed;
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea))
				{
						entity.collisionOn = true;
						index = i;
					
				}
				break;
			case "left" :
				entity.solidArea.x -= entity.speed;
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea))
				{
						entity.collisionOn = true;
						index = i;		
				}
				break;
			case "right" :
				entity.solidArea.x += entity.speed;
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea))
				{
						entity.collisionOn = true;
						index = i;
				}
				break;
				
			}
			
			
			
			entity.solidArea.x = entity.solidAreaDefaultX; //because the entity.solidaArea x and y above will be increased by entity.world x and y
			entity.solidArea.y = entity.solidAreaDefaultY; //Continually therefore we have to set them back
			
			target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;// and the same case for Object
			target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			
			}	
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		//get entity's solid area position
		entity.solidArea.x = entity.worldX + entity.solidArea.x; 
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		//get the object's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;//in those cases the solidArea x and y of object is 0 
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;//-> we can manipulate those parameter of Object as we want
		
		switch(entity.direction)
		{
		case "up" :
			entity.solidArea.y -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea))
			{
					entity.collisionOn = true;
			}
			break;
		case "down" :
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea))
			{
					entity.collisionOn = true;
					
				
			}
			break;
		case "left" :
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea))
			{
					entity.collisionOn = true;
						
			}
			break;
		case "right" :
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea))
			{
					entity.collisionOn = true;
				
			}
			break;
			
		}
		
		
		
		entity.solidArea.x = entity.solidAreaDefaultX; //because the entity.solidaArea x and y above will be increased by entity.world x and y
		entity.solidArea.y = entity.solidAreaDefaultY; //Continually therefore we have to set them back
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;// and the same case for Object
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
	}
	
	}
