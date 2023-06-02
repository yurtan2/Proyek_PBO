/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacman_escape;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 *
 * @author W I N D O W S
 */
public abstract class Music {
    private static Clip backgroundMusicClip;
    public static void playBackgroundMusic(String musicFilePath) throws IOException {
    try {
        // Membaca file musik
        File musicFile = new File(musicFilePath);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);

        // Mendapatkan format audio
        AudioFormat format = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, format);

        // Membuat objek Clip untuk memainkan musik
        Clip clip = (Clip) AudioSystem.getLine(info);

        // Memuat musik dari AudioInputStream ke Clip
        clip.open(audioStream);

        // Memainkan musik secara terus menerus
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        
        // Menyimpan referensi objek Clip
        backgroundMusicClip = clip;
    } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (LineUnavailableException e) {
        e.printStackTrace();
    }
}

}
