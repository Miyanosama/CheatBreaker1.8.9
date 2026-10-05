package io.netty.handler.codec.base64;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;
import javax.vecmath.Vector3d;
import net.minecraft.util.LoggingPrintStream;
import net.optifine.entity.model.ModelAdapterMinecartMobSpawner;
import org.apache.log4j.helpers.PatternParser$ClassNamePatternConverter;

public class Base64Encoder extends MessageToMessageEncoder<ByteBuf> {
   public Vector3d __junk740660491726598583;
   public PatternParser$ClassNamePatternConverter __junk5442068354343189825;
   public boolean breakLines;
   public Base64Dialect dialect;
   public LoggingPrintStream __junk4488367505511541484;
   public ModelAdapterMinecartMobSpawner __junk3985497843794907458;

   public Base64Encoder() {
      this(true);
   }

   public Base64Encoder(boolean var1) {
      this(var1, Base64Dialect.STANDARD);
   }

   public void encode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) {
      var3.add(Base64.encode(var2, var2.readerIndex(), var2.readableBytes(), this.breakLines, this.dialect));
   }

   public Base64Encoder(boolean var1, Base64Dialect var2) {
      if (var2 == null) {
         throw new NullPointerException("dialect");
      } else {
         this.breakLines = var1;
         this.dialect = var2;
      }
   }
}
