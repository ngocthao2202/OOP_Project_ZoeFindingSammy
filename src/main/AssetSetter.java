package main;



import Object.OBJ_Door;
import Object.OBJ_Key;
import entity.Cat;
import entity.NPC_OldMan;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp)
	{
		this.gp=gp;
	}
	public void setObject()
	{
		//MAP OCEAN
		int mapNum = 1;
		int i=0;
//
		///MAP SAND
		mapNum = 1;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*12;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*22;
		gp.obj[mapNum][i].worldY = gp.tileSize*42;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*36;
		gp.obj[mapNum][i].worldY = gp.tileSize*23;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*12;
		gp.obj[mapNum][i].worldY = gp.tileSize*28;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*29;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++; 
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*41;
		gp.obj[mapNum][i].worldY = gp.tileSize*51; 
		i++;
		////////////////////
		mapNum = 2;
		gp.obj[mapNum][0] = new OBJ_Key(gp);
		gp.obj[mapNum][0].worldX = gp.tileSize*13;
		gp.obj[mapNum][0].worldY = gp.tileSize*16;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*21;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*38;
		gp.obj[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*16;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*28;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize*43;
		gp.obj[mapNum][i].worldY = gp.tileSize*29;
		i++;

		///////////////
		
		
		
		
		
	}
	public void setNPC()
	{
		int i =0;
		int mapNum = 1;

		///
		mapNum = 1;
		gp.npc[mapNum][i] = new Cat(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*47;
		gp.npc[mapNum][i].worldY = gp.tileSize*8;
		i++;
		mapNum = 2;
		gp.npc[mapNum][i] = new Cat(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*14;
		gp.npc[mapNum][i].worldY = gp.tileSize*24;
		i++;
		
			
	}

}
