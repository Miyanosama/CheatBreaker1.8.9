package net.minecraft.tileentity;

import io.netty.handler.codec.http.CookieDecoder;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import org.java_websocket.client.WebSocketClient;

public class TileEntity$2 implements Callable<String> {
   public WebSocketClient field_0002;
   public CookieDecoder field_0000;

   public TileEntity$2(TileEntity var1) {
      this.field_150832_a = var1;
      super();
   }

   public String call() {
      int var1 = Block.getIdFromBlock(this.field_150832_a.b.getBlockState(this.field_150832_a.c).getBlock());

      try {
         return String.format("ID #%d (%s // %s)", var1, Block.getBlockById(var1).getUnlocalizedName(), Block.getBlockById(var1).getClass().getCanonicalName());
      } catch (Throwable var3) {
         return "ID #" + var1;
      }
   }
}
