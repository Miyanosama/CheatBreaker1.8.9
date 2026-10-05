package javazoom.jl.decoder;

public interface Control {
   void pause();

   void setPosition(double var1);

   void stop();

   boolean isRandomAccess();

   double getPosition();

   void start();

   boolean isPlaying();
}
