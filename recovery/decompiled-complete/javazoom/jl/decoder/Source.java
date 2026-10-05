package javazoom.jl.decoder;

public interface Source {
   long LENGTH_UNKNOWN = -1L;

   long length();

   boolean isSeekable();

   boolean willReadBlock();

   long tell();

   long seek(long var1);

   int read(byte[] var1, int var2, int var3);
}
