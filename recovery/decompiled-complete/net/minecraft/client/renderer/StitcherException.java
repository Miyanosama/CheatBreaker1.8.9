package net.minecraft.client.renderer;

import io.netty.channel.local.LocalEventLoop;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachValueTask;
import net.minecraft.client.renderer.texture.Stitcher$Holder;
import net.minecraft.tileentity.TileEntity;

public class StitcherException extends RuntimeException {
   public ConcurrentHashMapV8$ForEachValueTask field_0001;
   public LocalEventLoop field_0003;
   public TileEntity field_0000;
   public Stitcher$Holder field_0002;

   public StitcherException(Stitcher$Holder var1, String var2) {
      super(var2);
      this.field_0002 = var1;
   }
}
