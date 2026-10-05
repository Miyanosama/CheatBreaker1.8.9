package net.minecraft.item;

import net.minecraft.block.material.MapColor;
import net.minecraft.command.server.CommandListBans;
import net.minecraft.server.network.NetHandlerLoginServer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IStringSerializable;
import net.optifine.entity.model.ModelAdapterZombie;

public enum EnumDyeColor implements IStringSerializable {
   PURPLE(10, 5, "purple", "purple", MapColor.purpleColor, EnumChatFormatting.DARK_PURPLE),
   LIME(5, 10, "lime", "lime", MapColor.limeColor, EnumChatFormatting.GREEN),
   GREEN(13, 2, "green", "green", MapColor.greenColor, EnumChatFormatting.DARK_GREEN),
   RED(14, 1, "red", "red", MapColor.redColor, EnumChatFormatting.DARK_RED),
   BLUE(11, 4, "blue", "blue", MapColor.blueColor, EnumChatFormatting.DARK_BLUE),
   ORANGE(1, 14, "orange", "orange", MapColor.adobeColor, EnumChatFormatting.GOLD),
   YELLOW(4, 11, "yellow", "yellow", MapColor.yellowColor, EnumChatFormatting.YELLOW),
   LIGHT_BLUE(3, 12, "light_blue", "lightBlue", MapColor.lightBlueColor, EnumChatFormatting.BLUE),
   BROWN(12, 3, "brown", "brown", MapColor.brownColor, EnumChatFormatting.GOLD),
   SILVER(8, 7, "silver", "silver", MapColor.silverColor, EnumChatFormatting.GRAY),
   PINK(6, 9, "pink", "pink", MapColor.pinkColor, EnumChatFormatting.LIGHT_PURPLE),
   WHITE(0, 15, "white", "white", MapColor.snowColor, EnumChatFormatting.WHITE),
   CYAN(9, 6, "cyan", "cyan", MapColor.cyanColor, EnumChatFormatting.DARK_AQUA),
   BLACK(15, 0, "black", "black", MapColor.blackColor, EnumChatFormatting.BLACK),
   MAGENTA(2, 13, "magenta", "magenta", MapColor.magentaColor, EnumChatFormatting.AQUA),
   GRAY(7, 8, "gray", "gray", MapColor.grayColor, EnumChatFormatting.DARK_GRAY);

   public EnumChatFormatting chatColor;
   public String name;
   public static EnumDyeColor[] META_LOOKUP = new EnumDyeColor[values().length];
   public MapColor mapColor;
   public String unlocalizedName;
   public CommandListBans field_0009;
   public int meta;
   public int dyeDamage;
   // $VF: synthetic field
   public static EnumDyeColor[] $VALUES = new EnumDyeColor[]{
      WHITE,
      ORANGE,
      EnumDyeColor.MAGENTA,
      LIGHT_BLUE,
      YELLOW,
      LIME,
      PINK,
      EnumDyeColor.GRAY,
      SILVER,
      EnumDyeColor.CYAN,
      PURPLE,
      BLUE,
      BROWN,
      GREEN,
      RED,
      EnumDyeColor.BLACK
   };
   public NetHandlerLoginServer field_0000;
   public static EnumDyeColor[] DYE_DMG_LOOKUP = new EnumDyeColor[values().length];
   public ModelAdapterZombie field_0011;

   static {
      for (EnumDyeColor var3 : values()) {
         META_LOOKUP[var3.getMetadata()] = var3;
         DYE_DMG_LOOKUP[var3.getDyeDamage()] = var3;
      }
   }

   @Override
   public String getName() {
      return this.name;
   }

   public static EnumDyeColor byMetadata(int var0) {
      if (var0 < 0 || var0 >= META_LOOKUP.length) {
         var0 = 0;
      }

      return META_LOOKUP[var0];
   }

   public int getDyeDamage() {
      return this.dyeDamage;
   }

   public static EnumDyeColor byDyeDamage(int var0) {
      if (var0 < 0 || var0 >= DYE_DMG_LOOKUP.length) {
         var0 = 0;
      }

      return DYE_DMG_LOOKUP[var0];
   }

   public MapColor getMapColor() {
      return this.mapColor;
   }

   public EnumDyeColor(int var3, int var4, String var5, String var6, MapColor var7, EnumChatFormatting var8) {
      this.meta = var3;
      this.dyeDamage = var4;
      this.name = var5;
      this.unlocalizedName = var6;
      this.mapColor = var7;
      this.chatColor = var8;
   }

   public int getMetadata() {
      return this.meta;
   }

   @Override
   public String toString() {
      return this.unlocalizedName;
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }
}
