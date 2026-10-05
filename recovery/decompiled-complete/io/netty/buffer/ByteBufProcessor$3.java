package io.netty.buffer;

import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.http.DefaultHttpContent;
import io.netty.handler.codec.socks.SocksInitRequestDecoder;
import net.minecraft.command.PlayerSelector$1;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.optifine.entity.model.ModelAdapterHeadSkeleton;

public class ByteBufProcessor$3 implements ByteBufProcessor {
   public ModelAdapterHeadSkeleton __junk7247412134620747534;
   public DefaultHttpContent __junk8633835542551745552;
   public PlayerSelector$1 __junk6960011531085743312;
   public FixedLengthFrameDecoder __junk7753543155682359427;
   public EntityMinecartFurnace __junk4375144422346010880;
   public SocksInitRequestDecoder __junk5309448128169093581;

   @Override
   public boolean process(byte var1) {
      return var1 != 13;
   }
}
