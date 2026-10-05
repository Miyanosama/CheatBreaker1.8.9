package net.minecraft.network.play.server;

import io.netty.handler.codec.compression.JZlibEncoder$2;
import java.util.Collection;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.world.biome.BiomeGenMesa;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.pattern.LoggerPatternConverter;
import recovered.unidentified.UnidentifiedClass5063;

public class S20PacketEntityProperties$Snapshot {
   public double field_151413_c;
   public Collection<AttributeModifier> field_151411_d;
   public BiomeGenMesa field_0003;
   public BasicConfigurator field_0006;
   public UnidentifiedClass5063 field_0000;
   public IChatComponent$Serializer field_0008;
   public String field_151412_b;
   public JZlibEncoder$2 field_0002;
   public LoggerPatternConverter field_0009;

   public Collection<AttributeModifier> func_151408_c() {
      return this.field_151411_d;
   }

   public double func_151410_b() {
      return this.field_151413_c;
   }

   public S20PacketEntityProperties$Snapshot(String var1, double var2, Collection<AttributeModifier> param4, Collection var5) {
      this.field_151414_a = var1;
      super();
      this.field_151412_b = var2;
      this.field_151413_c = var3;
      this.field_151411_d = var5;
   }

   public String func_151409_a() {
      return this.field_151412_b;
   }
}
