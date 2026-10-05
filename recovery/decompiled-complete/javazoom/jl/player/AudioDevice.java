package javazoom.jl.player;

import javazoom.jl.decoder.Decoder;

public interface AudioDevice {
   void close();

   void flush();

   void write(short[] var1, int var2, int var3);

   boolean isOpen();

   void open(Decoder var1);

   int getPosition();
}
