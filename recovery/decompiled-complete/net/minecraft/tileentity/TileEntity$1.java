package net.minecraft.tileentity;

import io.netty.handler.codec.http.cors.CorsConfig$Builder;
import java.util.concurrent.Callable;
import net.optifine.util.MemoryMonitor;

public class TileEntity$1 implements Callable<String> {
   public CorsConfig$Builder field_0002;
   public MemoryMonitor field_0000;

   public TileEntity$1(TileEntity var1) {
      this.field_150830_a = var1;
      super();
   }

   public String call() {
      return (String)TileEntity.access$000().get(this.field_150830_a.getClass()) + " // " + this.field_150830_a.getClass().getCanonicalName();
   }
}
