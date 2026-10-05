package net.minecraft.item;

import com.google.common.base.Function;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.util.BlockPos$2$1;
import net.minecraft.world.WorldManager;
import org.apache.log4j.lf5.viewer.LF5SwingUtils$1;
import org.apache.log4j.rewrite.MapRewritePolicy;
import recovered.unidentified.UnidentifiedClass0275;

public class Item$16 implements Function<ItemStack, String> {
   public S03PacketTimeUpdate field_0003;
   public MapRewritePolicy field_0005;
   public UnidentifiedClass0275 field_0002;
   public BlockPos$2$1 field_0004;
   public WorldManager field_0000;
   public LF5SwingUtils$1 field_0001;

   public String apply(ItemStack var1) {
      return (var1.getMetadata() & 1) == 1 ? "wet" : "dry";
   }
}
