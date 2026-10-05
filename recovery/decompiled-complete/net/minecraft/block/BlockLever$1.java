package net.minecraft.block;

import io.netty.buffer.PoolArena$HeapArena;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.nbt.NBTTagCompound$2;
import net.minecraft.network.play.server.S38PacketPlayerListItem$1;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Axis;
import net.optifine.reflect.ReflectorFields;
import org.apache.log4j.lf5.viewer.LogTableColumnFormatException;

// $VF: synthetic class
public class BlockLever$1 {
   public S38PacketPlayerListItem$1 field_0004;
   public ReflectorFields field_0003;
   public PoolArena$HeapArena field_0006;
   public GuiScreenResourcePacks field_0001;
   public LogTableColumnFormatException field_0005;
   public NBTTagCompound$2 field_0002;

   static {
      try {
         field_180164_c[EnumFacing$Axis.X.ordinal()] = 1;
      } catch (NoSuchFieldError var16) {
      }

      try {
         field_180164_c[EnumFacing$Axis.Z.ordinal()] = 2;
      } catch (NoSuchFieldError var15) {
      }

      field_180163_b = new int[BlockLever$EnumOrientation.values().length];

      try {
         field_180163_b[BlockLever$EnumOrientation.EAST.ordinal()] = 1;
      } catch (NoSuchFieldError var14) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var13) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError var12) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError var11) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.UP_Z.ordinal()] = 5;
      } catch (NoSuchFieldError var10) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.UP_X.ordinal()] = 6;
      } catch (NoSuchFieldError var9) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.DOWN_X.ordinal()] = 7;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_180163_b[BlockLever$EnumOrientation.DOWN_Z.ordinal()] = 8;
      } catch (NoSuchFieldError var7) {
      }

      field_180165_a = new int[EnumFacing.values().length];

      try {
         field_180165_a[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_180165_a[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError var5) {
      }

      try {
         field_180165_a[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_180165_a[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_180165_a[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_180165_a[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError var1) {
      }
   }
}
