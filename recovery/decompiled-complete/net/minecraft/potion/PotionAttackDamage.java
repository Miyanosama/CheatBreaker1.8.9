package net.minecraft.potion;

import io.netty.handler.codec.marshalling.CompatibleMarshallingDecoder;
import io.netty.handler.codec.spdy.SpdySession$StreamState;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Bootstrap$3;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.Layout;

public class PotionAttackDamage extends Potion {
   public Layout field_0000;
   public SpdySession$StreamState field_0001;
   public CompatibleMarshallingDecoder field_0002;
   public EntitySheep field_0003;
   public Bootstrap$3 field_0004;

   public PotionAttackDamage(int var1, ResourceLocation var2, boolean var3, int var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   public double getAttributeModifierAmount(int var1, AttributeModifier var2) {
      return this.id == Potion.weakness.id ? -0.5F * (var1 + 1) : 1.3 * (var1 + 1);
   }
}
