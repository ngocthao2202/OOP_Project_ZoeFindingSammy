package entity;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.GamePanel;

public class CatPicture {
	GamePanel gp;
	public BufferedImage catcry;
	public BufferedImage catangry;
	public CatPicture(GamePanel gp)
	{
		this.gp=gp;
		getCatPicture();
		
	}

	public void getCatPicture()
	{
		try
		{
			catcry = ImageIO.read(getClass().getResourceAsStream("/picture/catcry.png"));
			catangry = ImageIO.read(getClass().getResourceAsStream("/picture/catangry.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
