package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import tile.Tile;

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
			gp.playSE(8);
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
				switch(gp.ui.commandNum)
				{
				case 0: 
				{
					//gp.stopMusic();
					gp.currentMap = 0;
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				break;
				case 1:
				{
					//gp.stopMusic();
					gp.currentMap = 1;
					gp.gameState = gp.playState;
					gp.playMusic(1);
				}
				break;
				case 2:
				{
					//gp.stopMusic();
					gp.currentMap = 2;
					gp.gameState = gp.playState;
					gp.playMusic(2);
				}
				break;
				case 3:
				{
					gp.ui.titleScreenState = 0;
				}
				break;
				}	
			}
			}
		}
		
		//ANSWER STATE
		if(gp.gameState == gp.answerState)
		{
			gp.playSE(9);
			//lEFT
			if(code == KeyEvent.VK_A)
			{
				gp.ui.answerNum--;
				if(gp.ui.answerNum < 0)
				{
					gp.ui.answerNum = 2;
				}
			}
			//RIGHT
			if(code == KeyEvent.VK_D)
			{
				gp.ui.answerNum++;
				if(gp.ui.answerNum > 2)
				{
					gp.ui.answerNum = 0;
				}
			}
			switch (gp.currentMap)
			{
			case 0:
				keyAnswer0(code);
				break;
			case 1:
				keyAnswer1(code);
				break;
			case 2:
				keyAnswer2(code);
			break;
			
			
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
		
		//END STATE
		if(gp.gameState == gp.endState)
		{
			gp.playSE(9);
			//lEFT
			if(code == KeyEvent.VK_A)
			{
				gp.ui.endNum--;
				if(gp.ui.endNum < 0)
				{
					gp.ui.endNum = 1;
				}
			}
			//RIGHT
			if(code == KeyEvent.VK_D)
			{
				gp.ui.endNum++;
				if(gp.ui.endNum > 1)
				{
					gp.ui.endNum = 0;
				}
			}
			
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.endNum == 0)
				{
					gp.gameState = gp.titleState;
					gp.restart();
				}
				else if (gp.ui.endNum == 1)
				{
					System.exit(0);
				}
			}
				
			}
		
		
		//PAUSE STATE
		else if(gp.gameState == gp.pauseState)
		{
			if(code == KeyEvent.VK_P)
			{
					gp.gameState = gp.playState;
					gp.playMusic(0);
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
	public void keyAnswer2(int code)
	{
		if(gp.ui.question ==0) 
		{ 
		if(code == KeyEvent.VK_ENTER)
		{
			if(gp.ui.answerNum == 0) 
			{
				gp.gameState = gp.playState;
				gp.playSE(10);
				
			}
			if(gp.ui.answerNum == 1)
			{
				gp.gameState = gp.playState;
				gp.playSE(10);
				
			}
			if(gp.ui.answerNum == 2)
			{ 
				gp.ui.correct = true;
				gp.gameState = gp.playState;
				gp.ui.question++;
				gp.playSE(11); 
			}
			gp.ui.answerNum = 0;
		}
		}
		else if(gp.ui.question ==1) {
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.answerNum == 0)
				{
					gp.ui.correct = true;
					gp.gameState = gp.playState;
					gp.ui.question++;
					gp.playSE(11);
				}
				if(gp.ui.answerNum == 1)
				{
					gp.gameState = gp.playState;
					gp.playSE(10);
					
				}
				if(gp.ui.answerNum == 2)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);

				}
				gp.ui.answerNum = 0;
			}
		}
		else if(gp.ui.question ==2) {
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.answerNum == 0)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);
					
				}
				if(gp.ui.answerNum == 1)
				{
					gp.ui.correct = true;
					gp.gameState = gp.playState;
					gp.ui.question=0;
					gp.playSE(11);
				}
				if(gp.ui.answerNum == 2)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);

				}
				gp.ui.answerNum = 0;
			}
		}
		
	}

	public void keyAnswer1(int code)
	{
		if(gp.ui.question ==0) 
		{
		if(code == KeyEvent.VK_ENTER)
		{
			if(gp.ui.answerNum == 2)
			{
				gp.gameState = gp.playState;
				gp.playSE(10);
				
			}
			if(gp.ui.answerNum == 1)
			{
				gp.gameState = gp.playState;
				gp.playSE(10);
				 
			}
			if(gp.ui.answerNum == 0)
			{ 
				gp.ui.correct = true;
				gp.gameState = gp.playState;
				gp.ui.question++;
				gp.playSE(11);
			}
			gp.ui.answerNum = 0;
		}
		}
		else if(gp.ui.question ==1) {
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.answerNum == 0)
				{
					gp.ui.correct = true;
					gp.gameState = gp.playState;
					gp.ui.question++;
					gp.playSE(11);
				}
				if(gp.ui.answerNum == 2)
				{
					gp.gameState = gp.playState;
					gp.playSE(10);
					
				}
				if(gp.ui.answerNum == 1)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);

				}
				gp.ui.answerNum = 0;
			}
		}
		else if(gp.ui.question ==2) {
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.answerNum == 0)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);
					
				}
				if(gp.ui.answerNum == 2)
				{
					gp.ui.correct = true;
					gp.gameState = gp.playState;
					gp.ui.question=0;
					gp.playSE(11);
				}
				if(gp.ui.answerNum == 1)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);

				}
				gp.ui.answerNum = 0;
			}
		}
	}
	public void keyAnswer0(int code)
	{
		if(gp.ui.question ==0) 
		{
		if(code == KeyEvent.VK_ENTER)
		{
			if(gp.ui.answerNum == 0)
			{
				gp.gameState = gp.playState;
				gp.playSE(10);
				
			}
			if(gp.ui.answerNum == 2)
			{
				gp.gameState = gp.playState;
				gp.playSE(10);
				
			}
			if(gp.ui.answerNum == 1)
			{ 
				gp.ui.correct = true;
				gp.gameState = gp.playState;
				gp.ui.question++;
				gp.playSE(11);
			}
			gp.ui.answerNum = 0;
		}
		}
		else if(gp.ui.question ==1) {
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.answerNum == 2)
				{
					gp.ui.correct = true;
					gp.gameState = gp.playState;
					gp.ui.question++;
					gp.playSE(11);
				}
				if(gp.ui.answerNum == 1)
				{
					gp.gameState = gp.playState;
					gp.playSE(10);
					
				}
				if(gp.ui.answerNum == 0)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);

				}
				gp.ui.answerNum = 0;
			}
		}
		else if(gp.ui.question ==2) {
			if(code == KeyEvent.VK_ENTER)
			{
				if(gp.ui.answerNum == 1)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);
					
				}
				if(gp.ui.answerNum == 0)
				{
					gp.ui.correct = true;
					gp.gameState = gp.playState;
					gp.ui.question=0;
					gp.playSE(11);
				}
				if(gp.ui.answerNum == 2)
				{
					
					gp.gameState = gp.playState;
					gp.playSE(10);

				}
				gp.ui.answerNum = 0;
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
