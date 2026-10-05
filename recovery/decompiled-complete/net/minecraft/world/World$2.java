package net.minecraft.world;

import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.client.gui.stream.GuiStreamOptions;
import net.optifine.config.ConnectedParser$2;
import org.slf4j.helpers.BasicMarkerFactory;

public class World$2 implements Callable<String> {
   public GuiStreamOptions field_0002;
   public ConnectedParser$2 field_0004;
   public BasicMarkerFactory field_0000;

   public World$2(World var1, Block var2) {
      this.field_77405_a = var1;
      this.field_151300_a = var2;
      super();
   }

   public String call() {
      try {
         return String.format(
            "ID #%d (%s // %s)",
            Block.getIdFromBlock(this.field_151300_a),
            this.field_151300_a.getUnlocalizedName(),
            this.field_151300_a.getClass().getCanonicalName()
         );
      } catch (Throwable var2) {
         return "ID #" + Block.getIdFromBlock(this.field_151300_a);
      }
   }
}
