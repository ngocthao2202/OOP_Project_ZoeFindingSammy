package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObject {
	public BufferedImage image ; 
	public String name;
	public boolean collision = false;
	public int worldX,worldY;
	
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public UtilityTool uTool = new UtilityTool();
	
	public void draw(Graphics2D g2,GamePanel gp)
	{
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY; 
		//just draw what we need on the frame not whole the map at one time
		if( worldX +  gp.tileSize > gp.player.worldX - gp.player.screenX &&
			worldX - 2 * gp.tileSize < gp.player.worldX + gp.player.screenX &&
			worldY +  gp.tileSize > gp.player.worldY - gp.player.screenY &&
			worldY - 2 * gp.tileSize < gp.player.worldY + gp.player.screenY)
		{
		g2.drawImage(image,screenX,screenY,null);
		}
	}
	

}
