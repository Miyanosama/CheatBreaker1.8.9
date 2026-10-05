package javazoom.jl.player;

import javazoom.jl.decoder.Decoder;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Corridor;
import net.optifine.ConnectedTextures$1;
import org.apache.log4j.helpers.DateTimeDateFormat;

public abstract class AudioDeviceBase implements AudioDevice {
   public BlockSapling __junk5604351797813863101;
   public ConnectedTextures$1 __junk2542332063162261653;
   public boolean open = false;
   public StructureStrongholdPieces$Corridor __junk3209060664380280619;
   public Decoder decoder = null;
   public DateTimeDateFormat __junk6686259112829529561;

   @Override
   public void write(short[] var1, int var2, int var3) {
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
   public synchronized void open(Decoder var1) {
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

   public void writeImpl(short[] var1, int var2, int var3) {
   }

   public void openImpl() {
   }

   public void flushImpl() {
   }
}
