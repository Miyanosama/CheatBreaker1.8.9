package net.minecraft.block;

import io.netty.channel.DefaultFileRegion;
import net.minecraft.client.gui.spectator.categories.TeleportToPlayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.storage.WorldInfo$5;
import org.apache.log4j.spi.ThrowableInformation;
import recovered.unidentified.UnidentifiedClass4786;

public enum BlockStairs$EnumHalf implements IStringSerializable {
   TOP("top"),
   BOTTOM("bottom");

   public String name;
   public TeleportToPlayer field_0003;
   // $VF: synthetic field
   public static BlockStairs$EnumHalf[] $VALUES = new BlockStairs$EnumHalf[]{TOP, BlockStairs$EnumHalf.BOTTOM};
   public BlockRedstoneWire field_0000;
   public WorldInfo$5 field_0001;
   public ThrowableInformation field_0005;
   public UnidentifiedClass4786 field_0002;
   public DefaultFileRegion field_0009;

   @Override
   public String toString() {
      return this.name;
   }

   public BlockStairs$EnumHalf(String var3) {
      this.name = var3;
   }

   @Override
   public String getName() {
      return this.name;
   }
}
