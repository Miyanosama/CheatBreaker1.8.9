package io.netty.util.internal;

import io.netty.channel.SingleThreadEventLoop;
import net.minecraft.block.BlockSeaLantern;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import org.scijava.nativelib.WebappJniExtractor;

public class NoOpTypeParameterMatcher extends TypeParameterMatcher {

   @Override
   public boolean match(Object var1) {
      return true;
   }
}
