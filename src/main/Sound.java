package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30]; // store file paths of sound files
	public Sound()
	{
		soundURL[0] = getClass().getResource("/sounds/themeGame.wav"); // theme game music
		soundURL[1] = getClass().getResource("/sounds/windegypt.wav");
		soundURL[2] = getClass().getResource("/sounds/oceantheme.wav");
		soundURL[3] = getClass().getResource("/sounds/unlock.wav");
		soundURL[4] = getClass().getResource("/sounds/fanfare.wav");
		soundURL[5] = getClass().getResource("/sounds/questionTime.wav");
		soundURL[6] = getClass().getResource("/sounds/item-pick-up.wav");
		soundURL[7] = getClass().getResource("/sounds/open-doors.wav");
		soundURL[8] = getClass().getResource("/sounds/click.wav");
		soundURL[9] = getClass().getResource("/sounds/button.wav");
		soundURL[10] = getClass().getResource("/sounds/wrong-answer.wav");
		soundURL[11] = getClass().getResource("/sounds/answer_sucess.wav");
		soundURL[12] = getClass().getResource("/sounds/finishGame.wav");
		soundURL[13] = getClass().getResource("/sounds/angry-cat-meow.wav");
	}
	public void setFile(int i)
	{
		try
		{
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		}
		catch(Exception e)
		{
			
		}
	}
	public void play()
	{
		clip.start();
	}
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop()
	{
		clip.stop();
	}

}
