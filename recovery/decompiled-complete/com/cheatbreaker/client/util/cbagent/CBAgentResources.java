package com.cheatbreaker.client.util.cbagent;

import io.netty.handler.codec.spdy.SpdyStreamStatus;
import net.minecraft.block.BlockSilverfish$EnumType$6;
import net.optifine.entity.model.ModelAdapterHeadHumanoid;
import recovered.unidentified.UnidentifiedClass3581;

public class CBAgentResources {
   public BlockSilverfish$EnumType$6 field_0001;
   public UnidentifiedClass3581 field_0003;
   public SpdyStreamStatus field_0000;
   public ModelAdapterHeadHumanoid field_0002;

   public static native boolean existsBytesNative(String var0);

   public static native byte[] getBytesNative(String var0);

   public static boolean existsBytes(String var0) {
      boolean var1 = false;

      try {
         var1 = existsBytesNative(var0);
      } catch (UnsatisfiedLinkError var3) {
      }

      return var1;
   }
}
