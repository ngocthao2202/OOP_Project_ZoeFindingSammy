package entity;



import java.util.Random;

import main.GamePanel;


public class NPC_OldMan extends Entity {
	
	
	public NPC_OldMan(GamePanel gp)
	{
		super(gp);
		direction = "left";
		speed = 1;
		getNPCImage();
		setDialogue();
		
	}
	public void  getNPCImage()
	{
		
			up1 = setUp("/npc/oldman_up_1");
			up2 = setUp("/npc/oldman_up_2");
			down1 = setUp("/npc/oldman_down_1");
			down2 = setUp("/npc/oldman_down_2");
			left1 = setUp("/npc/oldman_left_1");
			left2 = setUp("/npc/oldman_left_2");
			right1 = setUp("/npc/oldman_right_1");
			right2 = setUp("/npc/oldman_right_2");
		
	}

	public void setAction()
	{
		actionLockCounter ++;
	if(actionLockCounter == 120) {
		Random random = new Random();
		int i = random.nextInt(100);
		if(i<=25)
		{
			direction = "up";
		}
		if(i>25 && i<= 50)
		{
			direction = "down";
		}
		if(i>50 && i <=75)
		{
			direction = "left";
		}
		if(i>75 && i<=100)
		{
			direction = "right";
		}
		actionLockCounter = 0;
		}

	}
	
	public void setDialogue()
	{
		dialogues[0] = "Hello, boy ! ";
		dialogues[1] = "Welcome to the holy Place.\n Now ! you have to find ... and ...";
		dialogues[2] = "Hello, boy2! ";
		dialogues[3] = "Hello, boy3 ! ";
	}
	public void speak()
	{
		super.speak();
	}
	


}
