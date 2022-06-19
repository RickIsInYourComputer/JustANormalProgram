package xyz.rick.normalprogram;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.CountDownLatch;


public class Nothing {

    static {
        try {
            BufferedInputStream stream = new BufferedInputStream(Nothing.class.getClassLoader().getResourceAsStream("rick"));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(stream);
            Clip song = AudioSystem.getClip();
            song.open(audioIn);
            song.start();

            final CountDownLatch latch = new CountDownLatch(1);
            song.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType().equals(LineEvent.Type.STOP)) {
                        event.getLine().close();
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (Exception ignored) {ignored.printStackTrace();}


    }

    public static void main(String[] args){}

}
