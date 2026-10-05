package net.minecraft.block;

import com.google.common.base.Predicate;
import io.netty.handler.codec.spdy.SpdyFrameDecoder$1;
import io.netty.util.internal.logging.CommonsLogger;
import net.optifine.CustomLoadingScreen;

public class BlockOldLog$1 implements Predicate<BlockPlanks$EnumType> {
   public CommonsLogger field_0001;
   public SpdyFrameDecoder$1 field_0002;
   public CustomLoadingScreen field_0000;

   public boolean apply(BlockPlanks$EnumType var1) {
      return var1.getMetadata() < 4;
   }
}
