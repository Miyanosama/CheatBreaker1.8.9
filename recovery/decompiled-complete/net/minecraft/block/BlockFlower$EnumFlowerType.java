package net.minecraft.block;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import io.netty.channel.AbstractChannelHandlerContext$11;
import java.util.Collection;
import net.minecraft.client.gui.inventory.GuiBeacon$PowerButton;
import net.minecraft.client.gui.spectator.categories.TeleportToTeam;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.util.IStringSerializable;
import net.optifine.util.TimedEvent;

public enum BlockFlower$EnumFlowerType implements IStringSerializable {
   BLUE_ORCHID(BlockFlower$EnumFlowerColor.RED, 1, "blue_orchid", "blueOrchid"),
   ORANGE_TULIP(BlockFlower$EnumFlowerColor.RED, 5, "orange_tulip", "tulipOrange"),
   PINK_TULIP(BlockFlower$EnumFlowerColor.RED, 7, "pink_tulip", "tulipPink"),
   DANDELION(BlockFlower$EnumFlowerColor.YELLOW, 0, "dandelion"),
   OXEYE_DAISY(BlockFlower$EnumFlowerColor.RED, 8, "oxeye_daisy", "oxeyeDaisy"),
   POPPY(BlockFlower$EnumFlowerColor.RED, 0, "poppy"),
   RED_TULIP(BlockFlower$EnumFlowerColor.RED, 4, "red_tulip", "tulipRed"),
   ALLIUM(BlockFlower$EnumFlowerColor.RED, 2, "allium"),
   HOUSTONIA(BlockFlower$EnumFlowerColor.RED, 3, "houstonia"),
   WHITE_TULIP(BlockFlower$EnumFlowerColor.RED, 6, "white_tulip", "tulipWhite");

   public String name;
   public String unlocalizedName;
   public AbstractChannelHandlerContext$11 field_0014;
   public GuiBeacon$PowerButton field_0021;
   public BlockSlab field_0003;
   public BlockFlower$EnumFlowerColor blockType;
   public IChatComponent$Serializer field_0018;
   public static BlockFlower$EnumFlowerType[][] TYPES_FOR_BLOCK = new BlockFlower$EnumFlowerType[BlockFlower$EnumFlowerColor.values().length][];
   public TeleportToTeam field_0007;
   public TimedEvent field_0012;
   public int meta;

   public int getMeta() {
      return this.meta;
   }

   @Override
   public String getName() {
      return this.name;
   }

   public static BlockFlower$EnumFlowerType[] getTypes(BlockFlower$EnumFlowerColor var0) {
      return TYPES_FOR_BLOCK[var0.ordinal()];
   }

   public BlockFlower$EnumFlowerType(BlockFlower$EnumFlowerColor var3, int var4, String var5) {
      this(var3, var4, var5, var5);
   }

   static {
      for (BlockFlower$EnumFlowerColor var3 : BlockFlower$EnumFlowerColor.values()) {
         Collection var4 = Collections2.filter(Lists.newArrayList(values()), new BlockFlower$EnumFlowerType$1(var3));
         TYPES_FOR_BLOCK[var3.ordinal()] = var4.toArray(new BlockFlower$EnumFlowerType[var4.size()]);
      }
   }

   public String getUnlocalizedName() {
      return this.unlocalizedName;
   }

   @Override
   public String toString() {
      return this.name;
   }

   public BlockFlower$EnumFlowerColor getBlockType() {
      return this.blockType;
   }

   public static BlockFlower$EnumFlowerType getType(BlockFlower$EnumFlowerColor var0, int var1) {
      BlockFlower$EnumFlowerType[] var2 = TYPES_FOR_BLOCK[var0.ordinal()];
      if (var1 < 0 || var1 >= var2.length) {
         var1 = 0;
      }

      return var2[var1];
   }

   public BlockFlower$EnumFlowerType(BlockFlower$EnumFlowerColor var3, int var4, String var5, String var6) {
      this.blockType = var3;
      this.meta = var4;
      this.name = var5;
      this.unlocalizedName = var6;
   }
}
