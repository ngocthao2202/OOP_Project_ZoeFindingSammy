package tile;
import java.awt.Graphics2D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
	GamePanel gp ;
	public Tile[] tile;
	public int mapTileNum[][][]; //or ' int[][] mapTileNum ' is the same
	public TileManager(GamePanel gp)
	{
		this.gp=gp;
		tile = new Tile[50];// 10 here means 10 kinds of Tile (we could change it )
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		loadMap("/maps/worldV2.txt",0);
		loadMap("/maps/mapsand.txt",1);
		loadMap("/maps/mapocean.txt",2); 
		
	}
	public void getTileImage() {
	
			setUp(0,"grass00",false);
			setUp(1,"grass00",false);
			setUp(2,"grass00",false);
			setUp(3,"grass00",false);
			setUp(4,"grass00",false);
			setUp(5,"grass00",false);
			setUp(6,"grass00",false);
			setUp(7,"grass00",false);
			setUp(8,"grass00",false);
			setUp(9,"grass00",false);
			setUp(10,"grass00",false);
			setUp(11,"grass01",false);
			
			setUp(12,"water00",true);
			setUp(13,"water01",true);
			setUp(14,"water02",true);
			setUp(15,"water03",true);
			setUp(16,"water04",true);
			setUp(17,"water05",true);
			setUp(18,"water06",true);
			setUp(19,"water07",true);
			setUp(20,"water08",true);
			setUp(21,"water09",true);
			setUp(22,"water10",true);
			setUp(23,"water11",true);
			setUp(24,"water12",true);
			setUp(25,"water13",true);
			
			setUp(26,"road00",false);
			setUp(27,"road01",false);
			setUp(28,"road02",false);
			setUp(29,"road03",false);
			setUp(30,"road04",false);
			setUp(31,"road05",false);
			setUp(32,"road06",false);
			setUp(33,"road07",false);
			setUp(34,"road08",false);
			setUp(35,"road09",false);
			setUp(36,"road10",false);
			setUp(37,"road11",false);
			setUp(38,"road12",false);
			
			setUp(39,"earth",false);
			setUp(40,"wall",true);
			setUp(41,"tree",true);
			
			setUp(42, "sand", false);
			setUp(43, "hut", true);
			setUp(44, "table01", true);
			
	}		

	public void setUp(int index, String imageName, boolean collision)
	{
		UtilityTool uTool = new UtilityTool();
		
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName+".png"));
			tile[index].image = uTool.scaledImage(tile[index].image,gp.tileSize,gp.tileSize);
			tile[index].collision = collision;
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	public void loadMap(String filePath,int map)// + String filePath should do it this way for reusing the code easily for many maps in the future
	{
		try {
			InputStream is = getClass().getResourceAsStream(filePath);//use to read the TExt of map
			BufferedReader br = new BufferedReader ( new InputStreamReader(is));//
			int col =0;
			int row = 0;
			while(row<gp.maxWorldRow && col <gp.maxWorldCol)
			{
				String line = br.readLine();// .readLine read 'A SINGLE line' of text ' At a TIME until the end' and then we got 'A STRING' as 1 1 1 1 1 1 1 1 1- 
				while (col<gp.maxWorldCol)
				{
					String numbers[]  = line.split(" ");//use to erase all space" " of a line and got 'ARRAY of STRING' as numbes0] =1, numbers[1]=1,...
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[map][col][row]=num;
					++col;
				}
				if(col==gp.maxWorldCol)
				{
					col=0;
					++row;
				}
			}
			br.close();//close and release
			
		}catch(Exception e)
		{
			
		}
	}
	public void draw(Graphics2D g2)
	{
		int worldCol =0;
		int worldRow =0;
		
		while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow)
		{
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY; 
			//improve the game , just draw what we need on the frame not whole the map at one time
			if( worldX +  gp.tileSize > gp.player.worldX - gp.player.screenX &&
				worldX - 2 * gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY +  gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - 2 * gp.tileSize < gp.player.worldY + gp.player.screenY)
			{
			g2.drawImage(tile[tileNum].image,screenX,screenY,null);
			}
			++worldCol;
			
			
			if(worldCol==gp.maxWorldCol)
			{
				worldCol=0;
				++worldRow;
				
			}
			
		}
		
	}

}
