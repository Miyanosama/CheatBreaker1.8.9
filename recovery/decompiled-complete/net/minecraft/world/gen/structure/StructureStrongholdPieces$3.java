package net.minecraft.world.gen.structure;

import net.minecraft.client.renderer.block.model.BlockPartFace$Deserializer;
import net.minecraft.client.renderer.block.model.ItemModelGenerator;
import net.minecraft.client.stream.BroadcastController;
import net.minecraft.util.EnumFacing;

// $VF: synthetic class
public class StructureStrongholdPieces$3 {
   public BroadcastController field_0002;
   public ItemModelGenerator field_0001;
   public BlockPartFace$Deserializer field_0000;

   static {
      try {
         field_175951_b[StructureStrongholdPieces$Stronghold$Door.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var8) {
      }

      try {
         field_175951_b[StructureStrongholdPieces$Stronghold$Door.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var7) {
      }

      try {
         field_175951_b[StructureStrongholdPieces$Stronghold$Door.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var6) {
      }

      try {
         field_175951_b[StructureStrongholdPieces$Stronghold$Door.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var5) {
      }

      field_75245_a = new int[EnumFacing.values().length];

      try {
         field_75245_a[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_75245_a[EnumFacing.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_75245_a[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_75245_a[EnumFacing.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
