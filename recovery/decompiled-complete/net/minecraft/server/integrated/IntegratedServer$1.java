package net.minecraft.server.integrated;

import java.util.concurrent.Callable;
import net.minecraft.block.BlockPistonExtension$EnumPistonType;
import net.minecraft.client.renderer.chunk.ListChunkFactory;

public class IntegratedServer$1 implements Callable<String> {
   public ListChunkFactory field_0001;
   public BlockPistonExtension$EnumPistonType field_0000;

   public IntegratedServer$1(IntegratedServer var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return "Integrated Server (map_client.txt)";
   }
}
