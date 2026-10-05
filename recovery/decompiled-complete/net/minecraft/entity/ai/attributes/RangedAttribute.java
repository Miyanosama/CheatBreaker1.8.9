package net.minecraft.entity.ai.attributes;

import io.netty.handler.codec.spdy.SpdyFrameDecoder$State;
import net.minecraft.client.Minecraft$8;
import net.minecraft.util.MathHelper;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryPath;
import recovered.unidentified.UnidentifiedClass1534;

public class RangedAttribute extends BaseAttribute {
   public UnidentifiedClass1534 field_0001;
   public String description;
   public CategoryPath field_0005;
   public double maximumValue;
   public double minimumValue;
   public SpdyFrameDecoder$State field_0006;
   public Minecraft$8 field_0000;

   public String getDescription() {
      return this.description;
   }

   public RangedAttribute(IAttribute var1, String var2, double var3, double var5, double var7) {
      super(var1, var2, var3);
      this.minimumValue = var5;
      this.maximumValue = var7;
      if (var5 > var7) {
         throw new IllegalArgumentException("Minimum value cannot be bigger than maximum value!");
      } else if (var3 < var5) {
         throw new IllegalArgumentException("Default value cannot be lower than minimum value!");
      } else if (var3 > var7) {
         throw new IllegalArgumentException("Default value cannot be bigger than maximum value!");
      }
   }

   @Override
   public double clampValue(double var1) {
      return MathHelper.clamp_double(var1, this.minimumValue, this.maximumValue);
   }

   public RangedAttribute setDescription(String var1) {
      this.description = var1;
      return this;
   }
}
