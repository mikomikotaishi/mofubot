package org.bot.audio;

import java.nio.ByteBuffer;
import javax.sound.sampled.*;

import net.dv8tion.jda.api.audio.AudioSendHandler;

public class SendHandler implements AudioSendHandler {
    private final AudioFormat format;
    private final TargetDataLine line;
    private final byte[] buffer;

    public SendHandler() {
        this.format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);

        try {
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
            line = (TargetDataLine)AudioSystem.getLine(info);
            line.open(format);
            line.start();
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Failed to initialise audio line", e);
        }

        this.buffer = new byte[1024];
    }

    @Override
    public boolean canProvide() {
        return line.available() >= buffer.length;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        int bytesRead = line.read(buffer, 0, buffer.length);
        if (bytesRead == -1)
            return null;
        return ByteBuffer.wrap(buffer);
    }

    public void close() {
        line.stop();
        line.close();
    }
}
