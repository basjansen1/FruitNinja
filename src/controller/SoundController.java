package controller;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;

public class SoundController {
	public void startGameSoundTrack() {
		AudioInputStream as1;
		AudioFormat af;
		Clip clip1;
		DataLine.Info info;
		Line line1;
		
		try {
			as1 = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/game_music.wav"));
			af = as1.getFormat();
			clip1 = AudioSystem.getClip();
			info = new DataLine.Info(Clip.class, af);
			line1 = AudioSystem.getLine(info);

			if (!line1.isOpen()) {
				clip1.open(as1);
				clip1.loop(Clip.LOOP_CONTINUOUSLY);
				clip1.start();
			}
		} catch (Exception e) {
		}
	}
	
	public void startSlashSound() {
		AudioInputStream as1;
		AudioFormat af;
		Clip clip1;
		DataLine.Info info;
		Line line1;
		
		try {
			as1 = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/resources/slash.wav"));
			af = as1.getFormat();
			clip1 = AudioSystem.getClip();
			info = new DataLine.Info(Clip.class, af);
			line1 = AudioSystem.getLine(info);

			if (!line1.isOpen()) {
				clip1.open(as1);
				clip1.start();
			}
		} catch (Exception e) {
		}
	}
}
