package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {
	GamePanel gp;
	public OBJ_Chest(GamePanel gp)
	{
		name="Chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
			image = uTool.scaledImage(image, gp.tileSize,gp.tileSize);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		this.collision = true;

}
}
