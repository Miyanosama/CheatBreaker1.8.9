package net.minecraft.stats;

import io.netty.handler.codec.spdy.SpdyHeaderBlockZlibEncoder;
import net.minecraft.client.resources.SkinManager$1;
import net.minecraft.client.resources.SkinManager$3;
import net.minecraft.network.play.client.C0BPacketEntityAction;
import net.minecraft.util.IChatComponent;
import net.optifine.shaders.config.ExpressionShaderOptionSwitch;
import org.apache.log4j.EnhancedPatternLayout;

public class StatBasic extends StatBase {
   public EnhancedPatternLayout field_0003;
   public SpdyHeaderBlockZlibEncoder field_0005;
   public SkinManager$1 field_0002;
   public SkinManager$3 field_0004;
   public C0BPacketEntityAction field_0000;
   public ExpressionShaderOptionSwitch field_0001;

   @Override
   public StatBase registerStat() {
      super.registerStat();
      StatList.generalStats.add(this);
      return this;
   }

   public StatBasic(String var1, IChatComponent var2, IStatType var3) {
      super(var1, var2, var3);
   }

   public StatBasic(String var1, IChatComponent var2) {
      super(var1, var2);
   }
}
