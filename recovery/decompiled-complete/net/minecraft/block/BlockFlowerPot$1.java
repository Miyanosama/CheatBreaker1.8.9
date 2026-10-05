package net.minecraft.block;

import io.netty.handler.codec.http.DefaultHttpResponse;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.tileentity.TileEntitySign;

// $VF: synthetic class
public class BlockFlowerPot$1 {
   public TileEntitySign field_0005;
   public EnchantmentThorns field_0004;
   public DefaultHttpResponse field_0000;
   public S1FPacketSetExperience field_0001;

   static {
      try {
         field_180352_b[BlockFlower$EnumFlowerType.POPPY.ordinal()] = 1;
      } catch (NoSuchFieldError var15) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.BLUE_ORCHID.ordinal()] = 2;
      } catch (NoSuchFieldError var14) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.ALLIUM.ordinal()] = 3;
      } catch (NoSuchFieldError var13) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.HOUSTONIA.ordinal()] = 4;
      } catch (NoSuchFieldError var12) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.RED_TULIP.ordinal()] = 5;
      } catch (NoSuchFieldError var11) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.ORANGE_TULIP.ordinal()] = 6;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.WHITE_TULIP.ordinal()] = 7;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.PINK_TULIP.ordinal()] = 8;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_180352_b[BlockFlower$EnumFlowerType.OXEYE_DAISY.ordinal()] = 9;
      } catch (NoSuchFieldError var7) {
      }

      field_180353_a = new int[BlockPlanks$EnumType.values().length];

      try {
         field_180353_a[BlockPlanks$EnumType.OAK.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_180353_a[BlockPlanks$EnumType.SPRUCE.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_180353_a[BlockPlanks$EnumType.BIRCH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180353_a[BlockPlanks$EnumType.JUNGLE.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180353_a[BlockPlanks$EnumType.ACACIA.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180353_a[BlockPlanks$EnumType.DARK_OAK.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
