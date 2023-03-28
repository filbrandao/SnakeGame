package academy.mindswap.field;

import academy.mindswap.SoundFiles;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Sound {
    Clip soundLoop;
    Clip sound;
    public void getSoundClip(String soundPath) {
        final Clip soundClip;
        try {
            File soundFile = new File(soundPath);
            AudioInputStream soundToPlay = AudioSystem.getAudioInputStream(soundFile);
            soundClip = AudioSystem.getClip();
            soundClip.open(soundToPlay);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (soundClip.isRunning()) soundClip.stop();   // Stop the player if it is still running
        soundClip.setFramePosition(0); // rewind to the beginning
        soundClip.start();     // Start playing
    }
    public void getSoundLoop() {
        String soundPath = "resources/soundEffects/8bit-music-for-game-68698.wav";
        try {
            File soundFile = new File(soundPath);
            AudioInputStream soundToPlay = AudioSystem.getAudioInputStream(soundFile);
            soundLoop = AudioSystem.getClip();
            soundLoop.open(soundToPlay);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
            soundLoop.setFramePosition(0);
            soundLoop.start();

    }

    public Clip getSoundLoopVar() {
        return soundLoop;
    }

    public void init() {
        try {
            this.sound = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized void playSound(SoundFiles option) {
        File soundFile;
        Semaphore sem = new Semaphore(0);

        if (this.sound != null || this.sound.isRunning()) {
            this.sound.stop();
            this.sound.close();
        }

        try {
            soundFile = new File(option.getPath());

            AudioInputStream soundToPlay = AudioSystem.getAudioInputStream(soundFile);
            this.sound.open(soundToPlay);

            if (option == SoundFiles.GAME_LOOP) {
                this.sound.loop(Clip.LOOP_CONTINUOUSLY);  // repeat forever
            } else {
                this.sound.addLineListener(e -> {
                    if (e.getType() == LineEvent.Type.STOP) {
                        sem.release();
                    }
                });

                this.sound.start();     // Start playing

                sem.acquire();
                if (option == SoundFiles.FRUIT) {
                    playSound(SoundFiles.GAME_LOOP);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

