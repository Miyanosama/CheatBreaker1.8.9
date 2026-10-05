package net.minecraft.enchantment;

import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.init.Bootstrap;
import net.minecraft.network.NettyEncryptingDecoder;
import net.minecraft.util.ResourceLocation;
import org.json.JSONObject;
import recovered.unidentified.UnidentifiedClass3499;

public class EnchantmentArrowFire extends Enchantment {
   public JSONObject field_0000;
   public PlayerControllerMP field_0005;
   public GuiShareToLan field_0003;
   public NettyEncryptingDecoder field_0004;
   public Bootstrap field_0002;
   public UnidentifiedClass3499 field_0001;

   public EnchantmentArrowFire(int var1, ResourceLocation var2, int var3) {
      super(var1, var2, var3, EnumEnchantmentType.BOW);
      this.setName("arrowFire");
   }

   @Override
   public int getMinEnchantability(int var1) {
      return 20;
   }

   @Override
   public int getMaxEnchantability(int var1) {
      return 50;
   }

   @Override
   public int getMaxLevel() {
      return 1;
   }
}
