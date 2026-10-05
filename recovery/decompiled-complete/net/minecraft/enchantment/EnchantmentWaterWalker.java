package net.minecraft.enchantment;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import net.minecraft.network.play.client.C19PacketResourcePackStatus;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import recovered.unidentified.UnidentifiedClass0097;

public class EnchantmentWaterWalker extends Enchantment {
   public UnidentifiedClass0097 field_0000;
   public C19PacketResourcePackStatus field_0004;
   public GameRules field_0002;
   public ChannelHandlerAdapter field_0003;
   public TextWebSocketFrame field_0001;

   @Override
   public int getMinEnchantability(int var1) {
      return var1 * 10;
   }

   @Override
   public int getMaxEnchantability(int var1) {
      return this.getMinEnchantability(var1) + 15;
   }

   public EnchantmentWaterWalker(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.ARMOR_FEET);
      this.setName("waterWalker");
   }

   @Override
   public int getMaxLevel() {
      return 3;
   }
}
