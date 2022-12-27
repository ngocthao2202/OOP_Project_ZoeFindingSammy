package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {//KeyListener: the listener interface for receiving the keyboard events ( keystrokes)
	
	GamePanel gp ;

	public boolean upPressed,downPressed,leftPressed,rightPressed,enterPressed;
	//DEBUG
	boolean checkDrawTime = false;
	//ON-OFF THEME SONG
	boolean switchThemeSong = true;
	public KeyHandler(GamePanel gp)
	{
		this.gp=gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode(); //return the integer of keyCode associated with the key in this event
		
		//TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			if(gp.ui.titleScreenState == 0)
			{

			//UP
			if(code == KeyEvent.VK_W)
			{
				gp.ui.commandNum --;
				if(gp.ui.commandNum <0)
				{
					gp.ui.commandNum = 2;
				}
			}
			
			//DOWN
			if(code == KeyEvent.VK_S)
			{
				gp.ui.commandNum ++;
				if(gp.ui.commandNum >2)
				{
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.commandNum == 0)
				{
					gp.ui.titleScreenState = 1;
				}
				if(gp.ui.commandNum == 1)
				{
					//LOADSTATE
				}
				if(gp.ui.commandNum == 2)
				{
					System.exit(0);
				}
			}
			}
			
		
		
		//SELECTING GAME
		
			else if(gp.ui.titleScreenState == 1)
			{
			if(code == KeyEvent.VK_W)
			{
				gp.ui.commandNum --;
				if(gp.ui.commandNum <0)
				{
					gp.ui.commandNum = 3;
				}
			}
			
			if(code == KeyEvent.VK_S)
			{
				gp.ui.commandNum ++;
				if(gp.ui.commandNum >3)
				{
					gp.ui.commandNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.commandNum == 0)
				{
					System.out.print("Black cat");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1)
				{
					System.out.print("Gray cat");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 2)
				{
					System.out.print("Yellow cat");
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 3)
				{
					gp.ui.titleScreenState = 0;
				}
			}
			}
		}
		
		
		//PLAY STATE
		if(gp.gameState == gp.playState)
		{
			//UP
			if(code == KeyEvent.VK_W)
			{
				upPressed = true;
			}
			//lEFT
			if(code == KeyEvent.VK_A)
			{
				leftPressed = true;
			}
			//RIGHT
			if(code == KeyEvent.VK_D)
			{
				rightPressed = true;
			}
			//DOWN
			if(code == KeyEvent.VK_S)
			{
				downPressed = true;
			}
			if(code == KeyEvent.VK_P)
			{
				gp.gameState = gp.pauseState;	
			}
			if(code == KeyEvent.VK_ENTER)
			{
				enterPressed =true;
			}
		}
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState)
		{
			if(code == KeyEvent.VK_P)
			{
					gp.gameState = gp.playState;	
			}
		}
		
		//DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState)
		{
			if(code == KeyEvent.VK_SPACE)
			{
				gp.gameState = gp.playState;
			}
		}
		
		
		//DEBUG
		if(code == KeyEvent.VK_T)
		{
			if(checkDrawTime == false)
			{
				checkDrawTime = true;
			}
			else if (checkDrawTime == true)
			{
				checkDrawTime = false;
			}
		}
		
	
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode(); 
		//UP
		if(code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		//lEFT
		if(code == KeyEvent.VK_A)
		{
			leftPressed = false;
		}
		//RIGHT
		if(code == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
		//DOWN
		if(code == KeyEvent.VK_S)
		{
			downPressed = false;
		}
		

}}
