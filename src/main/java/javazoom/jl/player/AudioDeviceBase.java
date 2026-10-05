package javazoom.jl.player;

import javazoom.jl.decoder.Decoder;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.gen.structure.StructureStrongholdPieces;
import org.apache.log4j.helpers.DateTimeDateFormat;

public abstract class AudioDeviceBase implements AudioDevice {
   public boolean open = false;
   public Decoder decoder = null;

   @Override
   public void write(short[] var1, int var2, int var3) throws javazoom.jl.decoder.JavaLayerException {
      if (this.isOpen()) {
         this.writeImpl(var1, var2, var3);
      }
   }

   public Decoder getDecoder() {
      return this.decoder;
   }

   @Override
   public synchronized void close() {
      if (this.isOpen()) {
         this.closeImpl();
         this.setOpen(false);
         this.decoder = null;
      }
   }

   @Override
   public void flush() {
      if (this.isOpen()) {
         this.flushImpl();
      }
   }

   @Override
   public synchronized void open(Decoder var1) throws javazoom.jl.decoder.JavaLayerException {
      if (!this.isOpen()) {
         this.decoder = var1;
         this.openImpl();
         this.setOpen(true);
      }
   }

   public void closeImpl() {
   }

   @Override
   public synchronized boolean isOpen() {
      return this.open;
   }

   public void setOpen(boolean var1) {
      this.open = var1;
   }

   public void writeImpl(short[] var1, int var2, int var3) throws javazoom.jl.decoder.JavaLayerException {
   }

   public void openImpl() throws javazoom.jl.decoder.JavaLayerException {
   }

   public void flushImpl() {
   }
}
