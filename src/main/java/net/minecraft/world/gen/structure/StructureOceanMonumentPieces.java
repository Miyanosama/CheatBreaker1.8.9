package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$MonumentCoreRoom;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$XDoubleRoomFitHelper;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$YDoubleRoomFitHelper;

public class StructureOceanMonumentPieces {
   public static void registerOceanMonumentPieces() {
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.MonumentBuilding.class, "OMB");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces$MonumentCoreRoom.class, "OMCR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleXRoom.class, "OMDXR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleXYRoom.class, "OMDXYR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleYRoom.class, "OMDYR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleYZRoom.class, "OMDYZR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.DoubleZRoom.class, "OMDZR");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.EntryRoom.class, "OMEntry");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.Penthouse.class, "OMPenthouse");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.SimpleRoom.class, "OMSimple");
      MapGenStructureIO.registerStructureComponent(StructureOceanMonumentPieces.SimpleTopRoom.class, "OMSimpleT");
   }

   public static class DoubleXRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleXRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 2, 1, 1);
      }

      public DoubleXRoom() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         StructureOceanMonumentPieces.RoomDefinition var4 = this.k.field_175965_b[EnumFacing.EAST.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition var5 = this.k;
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 8, 0, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
            this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         if (var5.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 4, 1, 7, 4, 6, a);
         }

         if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 8, 4, 1, 14, 4, 6, a);
         }

         this.a(var1, var3, 0, 3, 0, 0, 3, 7, b, b, false);
         this.a(var1, var3, 15, 3, 0, 15, 3, 7, b, b, false);
         this.a(var1, var3, 1, 3, 0, 15, 3, 0, b, b, false);
         this.a(var1, var3, 1, 3, 7, 14, 3, 7, b, b, false);
         this.a(var1, var3, 0, 2, 0, 0, 2, 7, a, a, false);
         this.a(var1, var3, 15, 2, 0, 15, 2, 7, a, a, false);
         this.a(var1, var3, 1, 2, 0, 15, 2, 0, a, a, false);
         this.a(var1, var3, 1, 2, 7, 14, 2, 7, a, a, false);
         this.a(var1, var3, 0, 1, 0, 0, 1, 7, b, b, false);
         this.a(var1, var3, 15, 1, 0, 15, 1, 7, b, b, false);
         this.a(var1, var3, 1, 1, 0, 15, 1, 0, b, b, false);
         this.a(var1, var3, 1, 1, 7, 14, 1, 7, b, b, false);
         this.a(var1, var3, 5, 1, 0, 10, 1, 4, b, b, false);
         this.a(var1, var3, 6, 2, 0, 9, 2, 3, a, a, false);
         this.a(var1, var3, 5, 3, 0, 10, 3, 4, b, b, false);
         this.a(var1, e, 6, 2, 3, var3);
         this.a(var1, e, 9, 2, 3, var3);
         if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
         }

         if (var5.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 7, 4, 2, 7, false);
         }

         if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
         }

         if (var4.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 11, 1, 0, 12, 2, 0, false);
         }

         if (var4.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 11, 1, 7, 12, 2, 7, false);
         }

         if (var4.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 15, 1, 3, 15, 2, 4, false);
         }

         return true;
      }
   }

   public static class DoubleXYRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleXYRoom() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         StructureOceanMonumentPieces.RoomDefinition var4 = this.k.field_175965_b[EnumFacing.EAST.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition var5 = this.k;
         StructureOceanMonumentPieces.RoomDefinition var6 = var5.field_175965_b[EnumFacing.UP.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition var7 = var4.field_175965_b[EnumFacing.UP.getIndex()];
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 8, 0, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
            this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         if (var6.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 8, 1, 7, 8, 6, a);
         }

         if (var7.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 8, 8, 1, 14, 8, 6, a);
         }

         for (int var8 = 1; var8 <= 7; var8++) {
            IBlockState var9 = b;
            if (var8 == 2 || var8 == 6) {
               var9 = a;
            }

            this.a(var1, var3, 0, var8, 0, 0, var8, 7, var9, var9, false);
            this.a(var1, var3, 15, var8, 0, 15, var8, 7, var9, var9, false);
            this.a(var1, var3, 1, var8, 0, 15, var8, 0, var9, var9, false);
            this.a(var1, var3, 1, var8, 7, 14, var8, 7, var9, var9, false);
         }

         this.a(var1, var3, 2, 1, 3, 2, 7, 4, b, b, false);
         this.a(var1, var3, 3, 1, 2, 4, 7, 2, b, b, false);
         this.a(var1, var3, 3, 1, 5, 4, 7, 5, b, b, false);
         this.a(var1, var3, 13, 1, 3, 13, 7, 4, b, b, false);
         this.a(var1, var3, 11, 1, 2, 12, 7, 2, b, b, false);
         this.a(var1, var3, 11, 1, 5, 12, 7, 5, b, b, false);
         this.a(var1, var3, 5, 1, 3, 5, 3, 4, b, b, false);
         this.a(var1, var3, 10, 1, 3, 10, 3, 4, b, b, false);
         this.a(var1, var3, 5, 7, 2, 10, 7, 5, b, b, false);
         this.a(var1, var3, 5, 5, 2, 5, 7, 2, b, b, false);
         this.a(var1, var3, 10, 5, 2, 10, 7, 2, b, b, false);
         this.a(var1, var3, 5, 5, 5, 5, 7, 5, b, b, false);
         this.a(var1, var3, 10, 5, 5, 10, 7, 5, b, b, false);
         this.a(var1, b, 6, 6, 2, var3);
         this.a(var1, b, 9, 6, 2, var3);
         this.a(var1, b, 6, 6, 5, var3);
         this.a(var1, b, 9, 6, 5, var3);
         this.a(var1, var3, 5, 4, 3, 6, 4, 4, b, b, false);
         this.a(var1, var3, 9, 4, 3, 10, 4, 4, b, b, false);
         this.a(var1, e, 5, 4, 2, var3);
         this.a(var1, e, 5, 4, 5, var3);
         this.a(var1, e, 10, 4, 2, var3);
         this.a(var1, e, 10, 4, 5, var3);
         if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
         }

         if (var5.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 7, 4, 2, 7, false);
         }

         if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
         }

         if (var4.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 11, 1, 0, 12, 2, 0, false);
         }

         if (var4.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 11, 1, 7, 12, 2, 7, false);
         }

         if (var4.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 15, 1, 3, 15, 2, 4, false);
         }

         if (var6.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 5, 0, 4, 6, 0, false);
         }

         if (var6.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 5, 7, 4, 6, 7, false);
         }

         if (var6.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 5, 3, 0, 6, 4, false);
         }

         if (var7.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 11, 5, 0, 12, 6, 0, false);
         }

         if (var7.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 11, 5, 7, 12, 6, 7, false);
         }

         if (var7.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 15, 5, 3, 15, 6, 4, false);
         }

         return true;
      }

      public DoubleXYRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 2, 2, 1);
      }
   }

   public static class DoubleYRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleYRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 1, 2, 1);
      }

      public DoubleYRoom() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 0, 0, this.k.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         StructureOceanMonumentPieces.RoomDefinition var4 = this.k.field_175965_b[EnumFacing.UP.getIndex()];
         if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 8, 1, 6, 8, 6, a);
         }

         this.a(var1, var3, 0, 4, 0, 0, 4, 7, b, b, false);
         this.a(var1, var3, 7, 4, 0, 7, 4, 7, b, b, false);
         this.a(var1, var3, 1, 4, 0, 6, 4, 0, b, b, false);
         this.a(var1, var3, 1, 4, 7, 6, 4, 7, b, b, false);
         this.a(var1, var3, 2, 4, 1, 2, 4, 2, b, b, false);
         this.a(var1, var3, 1, 4, 2, 1, 4, 2, b, b, false);
         this.a(var1, var3, 5, 4, 1, 5, 4, 2, b, b, false);
         this.a(var1, var3, 6, 4, 2, 6, 4, 2, b, b, false);
         this.a(var1, var3, 2, 4, 5, 2, 4, 6, b, b, false);
         this.a(var1, var3, 1, 4, 5, 1, 4, 5, b, b, false);
         this.a(var1, var3, 5, 4, 5, 5, 4, 6, b, b, false);
         this.a(var1, var3, 6, 4, 5, 6, 4, 5, b, b, false);
         StructureOceanMonumentPieces.RoomDefinition var5 = this.k;

         for (int var6 = 1; var6 <= 5; var6 += 4) {
            byte var7 = 0;
            if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
               this.a(var1, var3, 2, var6, var7, 2, var6 + 2, var7, b, b, false);
               this.a(var1, var3, 5, var6, var7, 5, var6 + 2, var7, b, b, false);
               this.a(var1, var3, 3, var6 + 2, var7, 4, var6 + 2, var7, b, b, false);
            } else {
               this.a(var1, var3, 0, var6, var7, 7, var6 + 2, var7, b, b, false);
               this.a(var1, var3, 0, var6 + 1, var7, 7, var6 + 1, var7, a, a, false);
            }

            var7 = 7;
            if (var5.field_175966_c[EnumFacing.NORTH.getIndex()]) {
               this.a(var1, var3, 2, var6, var7, 2, var6 + 2, var7, b, b, false);
               this.a(var1, var3, 5, var6, var7, 5, var6 + 2, var7, b, b, false);
               this.a(var1, var3, 3, var6 + 2, var7, 4, var6 + 2, var7, b, b, false);
            } else {
               this.a(var1, var3, 0, var6, var7, 7, var6 + 2, var7, b, b, false);
               this.a(var1, var3, 0, var6 + 1, var7, 7, var6 + 1, var7, a, a, false);
            }

            byte var8 = 0;
            if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
               this.a(var1, var3, var8, var6, 2, var8, var6 + 2, 2, b, b, false);
               this.a(var1, var3, var8, var6, 5, var8, var6 + 2, 5, b, b, false);
               this.a(var1, var3, var8, var6 + 2, 3, var8, var6 + 2, 4, b, b, false);
            } else {
               this.a(var1, var3, var8, var6, 0, var8, var6 + 2, 7, b, b, false);
               this.a(var1, var3, var8, var6 + 1, 0, var8, var6 + 1, 7, a, a, false);
            }

            var8 = 7;
            if (var5.field_175966_c[EnumFacing.EAST.getIndex()]) {
               this.a(var1, var3, var8, var6, 2, var8, var6 + 2, 2, b, b, false);
               this.a(var1, var3, var8, var6, 5, var8, var6 + 2, 5, b, b, false);
               this.a(var1, var3, var8, var6 + 2, 3, var8, var6 + 2, 4, b, b, false);
            } else {
               this.a(var1, var3, var8, var6, 0, var8, var6 + 2, 7, b, b, false);
               this.a(var1, var3, var8, var6 + 1, 0, var8, var6 + 1, 7, a, a, false);
            }

            var5 = var4;
         }

         return true;
      }
   }

   public static class DoubleYZRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleYZRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 1, 2, 2);
      }

      public DoubleYZRoom() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         StructureOceanMonumentPieces.RoomDefinition var4 = this.k.field_175965_b[EnumFacing.NORTH.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition var5 = this.k;
         StructureOceanMonumentPieces.RoomDefinition var6 = var4.field_175965_b[EnumFacing.UP.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition var7 = var5.field_175965_b[EnumFacing.UP.getIndex()];
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 0, 8, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
            this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         if (var7.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 8, 1, 6, 8, 7, a);
         }

         if (var6.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 8, 8, 6, 8, 14, a);
         }

         for (int var8 = 1; var8 <= 7; var8++) {
            IBlockState var9 = b;
            if (var8 == 2 || var8 == 6) {
               var9 = a;
            }

            this.a(var1, var3, 0, var8, 0, 0, var8, 15, var9, var9, false);
            this.a(var1, var3, 7, var8, 0, 7, var8, 15, var9, var9, false);
            this.a(var1, var3, 1, var8, 0, 6, var8, 0, var9, var9, false);
            this.a(var1, var3, 1, var8, 15, 6, var8, 15, var9, var9, false);
         }

         for (int var10 = 1; var10 <= 7; var10++) {
            IBlockState var11 = c;
            if (var10 == 2 || var10 == 6) {
               var11 = e;
            }

            this.a(var1, var3, 3, var10, 7, 4, var10, 8, var11, var11, false);
         }

         if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
         }

         if (var5.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 7, 1, 3, 7, 2, 4, false);
         }

         if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
         }

         if (var4.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 15, 4, 2, 15, false);
         }

         if (var4.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 11, 0, 2, 12, false);
         }

         if (var4.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 7, 1, 11, 7, 2, 12, false);
         }

         if (var7.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 5, 0, 4, 6, 0, false);
         }

         if (var7.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 7, 5, 3, 7, 6, 4, false);
            this.a(var1, var3, 5, 4, 2, 6, 4, 5, b, b, false);
            this.a(var1, var3, 6, 1, 2, 6, 3, 2, b, b, false);
            this.a(var1, var3, 6, 1, 5, 6, 3, 5, b, b, false);
         }

         if (var7.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 5, 3, 0, 6, 4, false);
            this.a(var1, var3, 1, 4, 2, 2, 4, 5, b, b, false);
            this.a(var1, var3, 1, 1, 2, 1, 3, 2, b, b, false);
            this.a(var1, var3, 1, 1, 5, 1, 3, 5, b, b, false);
         }

         if (var6.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 5, 15, 4, 6, 15, false);
         }

         if (var6.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 5, 11, 0, 6, 12, false);
            this.a(var1, var3, 1, 4, 10, 2, 4, 13, b, b, false);
            this.a(var1, var3, 1, 1, 10, 1, 3, 10, b, b, false);
            this.a(var1, var3, 1, 1, 13, 1, 3, 13, b, b, false);
         }

         if (var6.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 7, 5, 11, 7, 6, 12, false);
            this.a(var1, var3, 5, 4, 10, 6, 4, 13, b, b, false);
            this.a(var1, var3, 6, 1, 10, 6, 3, 10, b, b, false);
            this.a(var1, var3, 6, 1, 13, 6, 3, 13, b, b, false);
         }

         return true;
      }
   }

   public static class DoubleZRoom extends StructureOceanMonumentPieces.Piece {
      public DoubleZRoom() {
      }

      public DoubleZRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 1, 1, 2);
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         StructureOceanMonumentPieces.RoomDefinition var4 = this.k.field_175965_b[EnumFacing.NORTH.getIndex()];
         StructureOceanMonumentPieces.RoomDefinition var5 = this.k;
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 0, 8, var4.field_175966_c[EnumFacing.DOWN.getIndex()]);
            this.a(var1, var3, 0, 0, var5.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         if (var5.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 4, 1, 6, 4, 7, a);
         }

         if (var4.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 4, 8, 6, 4, 14, a);
         }

         this.a(var1, var3, 0, 3, 0, 0, 3, 15, b, b, false);
         this.a(var1, var3, 7, 3, 0, 7, 3, 15, b, b, false);
         this.a(var1, var3, 1, 3, 0, 7, 3, 0, b, b, false);
         this.a(var1, var3, 1, 3, 15, 6, 3, 15, b, b, false);
         this.a(var1, var3, 0, 2, 0, 0, 2, 15, a, a, false);
         this.a(var1, var3, 7, 2, 0, 7, 2, 15, a, a, false);
         this.a(var1, var3, 1, 2, 0, 7, 2, 0, a, a, false);
         this.a(var1, var3, 1, 2, 15, 6, 2, 15, a, a, false);
         this.a(var1, var3, 0, 1, 0, 0, 1, 15, b, b, false);
         this.a(var1, var3, 7, 1, 0, 7, 1, 15, b, b, false);
         this.a(var1, var3, 1, 1, 0, 7, 1, 0, b, b, false);
         this.a(var1, var3, 1, 1, 15, 6, 1, 15, b, b, false);
         this.a(var1, var3, 1, 1, 1, 1, 1, 2, b, b, false);
         this.a(var1, var3, 6, 1, 1, 6, 1, 2, b, b, false);
         this.a(var1, var3, 1, 3, 1, 1, 3, 2, b, b, false);
         this.a(var1, var3, 6, 3, 1, 6, 3, 2, b, b, false);
         this.a(var1, var3, 1, 1, 13, 1, 1, 14, b, b, false);
         this.a(var1, var3, 6, 1, 13, 6, 1, 14, b, b, false);
         this.a(var1, var3, 1, 3, 13, 1, 3, 14, b, b, false);
         this.a(var1, var3, 6, 3, 13, 6, 3, 14, b, b, false);
         this.a(var1, var3, 2, 1, 6, 2, 3, 6, b, b, false);
         this.a(var1, var3, 5, 1, 6, 5, 3, 6, b, b, false);
         this.a(var1, var3, 2, 1, 9, 2, 3, 9, b, b, false);
         this.a(var1, var3, 5, 1, 9, 5, 3, 9, b, b, false);
         this.a(var1, var3, 3, 2, 6, 4, 2, 6, b, b, false);
         this.a(var1, var3, 3, 2, 9, 4, 2, 9, b, b, false);
         this.a(var1, var3, 2, 2, 7, 2, 2, 8, b, b, false);
         this.a(var1, var3, 5, 2, 7, 5, 2, 8, b, b, false);
         this.a(var1, e, 2, 2, 5, var3);
         this.a(var1, e, 5, 2, 5, var3);
         this.a(var1, e, 2, 2, 10, var3);
         this.a(var1, e, 5, 2, 10, var3);
         this.a(var1, b, 2, 3, 5, var3);
         this.a(var1, b, 5, 3, 5, var3);
         this.a(var1, b, 2, 3, 10, var3);
         this.a(var1, b, 5, 3, 10, var3);
         if (var5.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
         }

         if (var5.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 7, 1, 3, 7, 2, 4, false);
         }

         if (var5.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
         }

         if (var4.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 15, 4, 2, 15, false);
         }

         if (var4.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 11, 0, 2, 12, false);
         }

         if (var4.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 7, 1, 11, 7, 2, 12, false);
         }

         return true;
      }
   }

   public static class EntryRoom extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 0, 3, 0, 2, 3, 7, b, b, false);
         this.a(var1, var3, 5, 3, 0, 7, 3, 7, b, b, false);
         this.a(var1, var3, 0, 2, 0, 1, 2, 7, b, b, false);
         this.a(var1, var3, 6, 2, 0, 7, 2, 7, b, b, false);
         this.a(var1, var3, 0, 1, 0, 0, 1, 7, b, b, false);
         this.a(var1, var3, 7, 1, 0, 7, 1, 7, b, b, false);
         this.a(var1, var3, 0, 1, 7, 7, 3, 7, b, b, false);
         this.a(var1, var3, 1, 1, 0, 2, 3, 0, b, b, false);
         this.a(var1, var3, 5, 1, 0, 6, 3, 0, b, b, false);
         if (this.k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 7, 4, 2, 7, false);
         }

         if (this.k.field_175966_c[EnumFacing.WEST.getIndex()]) {
            this.a(var1, var3, 0, 1, 3, 1, 2, 4, false);
         }

         if (this.k.field_175966_c[EnumFacing.EAST.getIndex()]) {
            this.a(var1, var3, 6, 1, 3, 7, 2, 4, false);
         }

         return true;
      }

      public EntryRoom() {
      }

      public EntryRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2) {
         super(1, var1, var2, 1, 1, 1);
      }
   }

   public static class FitSimpleRoomHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1) {
         return true;
      }

      public FitSimpleRoomHelper() {
      }

      @Override
      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         var2.field_175963_d = true;
         return new StructureOceanMonumentPieces.SimpleRoom(var1, var2, var3);
      }
   }

   public static class FitSimpleRoomTopHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         var2.field_175963_d = true;
         return new StructureOceanMonumentPieces.SimpleTopRoom(var1, var2, var3);
      }

      public FitSimpleRoomTopHelper() {
      }

      @Override
      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1) {
         return !var1.field_175966_c[EnumFacing.WEST.getIndex()]
            && !var1.field_175966_c[EnumFacing.EAST.getIndex()]
            && !var1.field_175966_c[EnumFacing.NORTH.getIndex()]
            && !var1.field_175966_c[EnumFacing.SOUTH.getIndex()]
            && !var1.field_175966_c[EnumFacing.UP.getIndex()];
      }
   }

   public static class MonumentBuilding extends StructureOceanMonumentPieces.Piece {
      public List<StructureOceanMonumentPieces.Piece> field_175843_q = Lists.newArrayList();
      public StructureOceanMonumentPieces.RoomDefinition recoveredField1839;
      public StructureOceanMonumentPieces.RoomDefinition recoveredField1840;

      public void func_175835_e(World var1, Random var2, StructureBoundingBox var3) {
         if (this.func_175818_a(var3, 0, 21, 6, 58)) {
            this.a(var1, var3, 0, 0, 21, 6, 0, 57, a, a, false);
            this.a(var1, var3, 0, 1, 21, 6, 7, 57, false);
            this.a(var1, var3, 4, 4, 21, 6, 4, 53, a, a, false);

            for (int var4 = 0; var4 < 4; var4++) {
               this.a(var1, var3, var4, var4 + 1, 21, var4, var4 + 1, 57 - var4, b, b, false);
            }

            for (int var5 = 23; var5 < 53; var5 += 3) {
               this.a(var1, field_175824_d, 5, 5, var5, var3);
            }

            this.a(var1, field_175824_d, 5, 5, 52, var3);

            for (int var6 = 0; var6 < 4; var6++) {
               this.a(var1, var3, var6, var6 + 1, 21, var6, var6 + 1, 57 - var6, b, b, false);
            }

            this.a(var1, var3, 4, 1, 52, 6, 3, 52, a, a, false);
            this.a(var1, var3, 5, 1, 51, 5, 3, 53, a, a, false);
         }

         if (this.func_175818_a(var3, 51, 21, 58, 58)) {
            this.a(var1, var3, 51, 0, 21, 57, 0, 57, a, a, false);
            this.a(var1, var3, 51, 1, 21, 57, 7, 57, false);
            this.a(var1, var3, 51, 4, 21, 53, 4, 53, a, a, false);

            for (int var7 = 0; var7 < 4; var7++) {
               this.a(var1, var3, 57 - var7, var7 + 1, 21, 57 - var7, var7 + 1, 57 - var7, b, b, false);
            }

            for (int var8 = 23; var8 < 53; var8 += 3) {
               this.a(var1, field_175824_d, 52, 5, var8, var3);
            }

            this.a(var1, field_175824_d, 52, 5, 52, var3);
            this.a(var1, var3, 51, 1, 52, 53, 3, 52, a, a, false);
            this.a(var1, var3, 52, 1, 51, 52, 3, 53, a, a, false);
         }

         if (this.func_175818_a(var3, 0, 51, 57, 57)) {
            this.a(var1, var3, 7, 0, 51, 50, 0, 57, a, a, false);
            this.a(var1, var3, 7, 1, 51, 50, 10, 57, false);

            for (int var9 = 0; var9 < 4; var9++) {
               this.a(var1, var3, var9 + 1, var9 + 1, 57 - var9, 56 - var9, var9 + 1, 57 - var9, b, b, false);
            }
         }
      }

      public MonumentBuilding(Random var1, int var2, int var3, EnumFacing var4) {
         super(0);
         this.m = var4;
         switch (this.m) {
            case NORTH:
            case SOUTH:
               this.l = new StructureBoundingBox(var2, 39, var3, var2 + 58 - 1, 61, var3 + 58 - 1);
               break;
            default:
               this.l = new StructureBoundingBox(var2, 39, var3, var2 + 58 - 1, 61, var3 + 58 - 1);
         }

         List var5 = this.func_175836_a(var1);
         this.recoveredField1839.field_175963_d = true;
         this.field_175843_q.add(new StructureOceanMonumentPieces.EntryRoom(this.m, this.recoveredField1839));
         this.field_175843_q.add(new StructureOceanMonumentPieces$MonumentCoreRoom(this.m, this.recoveredField1840, var1));
         ArrayList var6 = Lists.newArrayList();
         var6.add(new StructureOceanMonumentPieces.XYDoubleRoomFitHelper());
         var6.add(new StructureOceanMonumentPieces.YZDoubleRoomFitHelper());
         var6.add(new StructureOceanMonumentPieces.ZDoubleRoomFitHelper());
         var6.add(new StructureOceanMonumentPieces$XDoubleRoomFitHelper());
         var6.add(new StructureOceanMonumentPieces$YDoubleRoomFitHelper());
         var6.add(new StructureOceanMonumentPieces.FitSimpleRoomTopHelper());
         var6.add(new StructureOceanMonumentPieces.FitSimpleRoomHelper());

         for (StructureOceanMonumentPieces.RoomDefinition var8 : (Iterable<StructureOceanMonumentPieces.RoomDefinition>)(Iterable<?>)(var5)) {
            if (!var8.field_175963_d && !var8.func_175961_b()) {
               for (StructureOceanMonumentPieces.MonumentRoomFitHelper var10 : (Iterable<StructureOceanMonumentPieces.MonumentRoomFitHelper>)(Iterable<?>)(var6)) {
                  if (var10.func_175969_a(var8)) {
                     this.field_175843_q.add(var10.func_175968_a(this.m, var8, var1));
                     break;
                  }
               }
            }
         }

         int var14 = this.l.minY;
         int var15 = this.a(9, 22);
         int var16 = this.b(9, 22);

         for (StructureOceanMonumentPieces.Piece var11 : this.field_175843_q) {
            var11.getBoundingBox().offset(var15, var14, var16);
         }

         StructureBoundingBox var18 = StructureBoundingBox.func_175899_a(this.a(1, 1), this.d(1), this.b(1, 1), this.a(23, 21), this.d(8), this.b(23, 21));
         StructureBoundingBox var19 = StructureBoundingBox.func_175899_a(this.a(34, 1), this.d(1), this.b(34, 1), this.a(56, 21), this.d(8), this.b(56, 21));
         StructureBoundingBox var12 = StructureBoundingBox.func_175899_a(this.a(22, 22), this.d(13), this.b(22, 22), this.a(35, 35), this.d(17), this.b(35, 35));
         int var13 = var1.nextInt();
         this.field_175843_q.add(new StructureOceanMonumentPieces.WingRoom(this.m, var18, var13++));
         this.field_175843_q.add(new StructureOceanMonumentPieces.WingRoom(this.m, var19, var13++));
         this.field_175843_q.add(new StructureOceanMonumentPieces.Penthouse(this.m, var12));
      }

      public void func_175842_f(World var1, Random var2, StructureBoundingBox var3) {
         if (this.func_175818_a(var3, 7, 21, 13, 50)) {
            this.a(var1, var3, 7, 0, 21, 13, 0, 50, a, a, false);
            this.a(var1, var3, 7, 1, 21, 13, 10, 50, false);
            this.a(var1, var3, 11, 8, 21, 13, 8, 53, a, a, false);

            for (int var4 = 0; var4 < 4; var4++) {
               this.a(var1, var3, var4 + 7, var4 + 5, 21, var4 + 7, var4 + 5, 54, b, b, false);
            }

            for (int var5 = 21; var5 <= 45; var5 += 3) {
               this.a(var1, field_175824_d, 12, 9, var5, var3);
            }
         }

         if (this.func_175818_a(var3, 44, 21, 50, 54)) {
            this.a(var1, var3, 44, 0, 21, 50, 0, 50, a, a, false);
            this.a(var1, var3, 44, 1, 21, 50, 10, 50, false);
            this.a(var1, var3, 44, 8, 21, 46, 8, 53, a, a, false);

            for (int var6 = 0; var6 < 4; var6++) {
               this.a(var1, var3, 50 - var6, var6 + 5, 21, 50 - var6, var6 + 5, 54, b, b, false);
            }

            for (int var7 = 21; var7 <= 45; var7 += 3) {
               this.a(var1, field_175824_d, 45, 9, var7, var3);
            }
         }

         if (this.func_175818_a(var3, 8, 44, 49, 54)) {
            this.a(var1, var3, 14, 0, 44, 43, 0, 50, a, a, false);
            this.a(var1, var3, 14, 1, 44, 43, 10, 50, false);

            for (int var8 = 12; var8 <= 45; var8 += 3) {
               this.a(var1, field_175824_d, var8, 9, 45, var3);
               this.a(var1, field_175824_d, var8, 9, 52, var3);
               if (var8 == 12 || var8 == 18 || var8 == 24 || var8 == 33 || var8 == 39 || var8 == 45) {
                  this.a(var1, field_175824_d, var8, 9, 47, var3);
                  this.a(var1, field_175824_d, var8, 9, 50, var3);
                  this.a(var1, field_175824_d, var8, 10, 45, var3);
                  this.a(var1, field_175824_d, var8, 10, 46, var3);
                  this.a(var1, field_175824_d, var8, 10, 51, var3);
                  this.a(var1, field_175824_d, var8, 10, 52, var3);
                  this.a(var1, field_175824_d, var8, 11, 47, var3);
                  this.a(var1, field_175824_d, var8, 11, 50, var3);
                  this.a(var1, field_175824_d, var8, 12, 48, var3);
                  this.a(var1, field_175824_d, var8, 12, 49, var3);
               }
            }

            for (int var9 = 0; var9 < 3; var9++) {
               this.a(var1, var3, 8 + var9, 5 + var9, 54, 49 - var9, 5 + var9, 54, a, a, false);
            }

            this.a(var1, var3, 11, 8, 54, 46, 8, 54, b, b, false);
            this.a(var1, var3, 14, 8, 44, 43, 8, 53, a, a, false);
         }
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         int var4 = Math.max(var1.F(), 64) - this.l.minY;
         this.a(var1, var3, 0, 0, 0, 58, var4, 58, false);
         this.func_175840_a(false, 0, var1, var2, var3);
         this.func_175840_a(true, 33, var1, var2, var3);
         this.func_175839_b(var1, var2, var3);
         this.func_175837_c(var1, var2, var3);
         this.func_175841_d(var1, var2, var3);
         this.func_175835_e(var1, var2, var3);
         this.func_175842_f(var1, var2, var3);
         this.func_175838_g(var1, var2, var3);

         for (int var5 = 0; var5 < 7; var5++) {
            int var6 = 0;

            while (var6 < 7) {
               if (var6 == 0 && var5 == 3) {
                  var6 = 6;
               }

               int var7 = var5 * 9;
               int var8 = var6 * 9;

               for (int var9 = 0; var9 < 4; var9++) {
                  for (int var10 = 0; var10 < 4; var10++) {
                     this.a(var1, b, var7 + var9, 0, var8 + var10, var3);
                     this.b(var1, b, var7 + var9, -1, var8 + var10, var3);
                  }
               }

               if (var5 != 0 && var5 != 6) {
                  var6 += 6;
               } else {
                  var6++;
               }
            }
         }

         for (int var11 = 0; var11 < 5; var11++) {
            this.a(var1, var3, -1 - var11, 0 + var11 * 2, -1 - var11, -1 - var11, 23, 58 + var11, false);
            this.a(var1, var3, 58 + var11, 0 + var11 * 2, -1 - var11, 58 + var11, 23, 58 + var11, false);
            this.a(var1, var3, 0 - var11, 0 + var11 * 2, -1 - var11, 57 + var11, 23, -1 - var11, false);
            this.a(var1, var3, 0 - var11, 0 + var11 * 2, 58 + var11, 57 + var11, 23, 58 + var11, false);
         }

         for (StructureOceanMonumentPieces.Piece var13 : this.field_175843_q) {
            if (var13.getBoundingBox().intersectsWith(var3)) {
               var13.addComponentParts(var1, var2, var3);
            }
         }

         return true;
      }

      public void func_175840_a(boolean var1, int var2, World var3, Random var4, StructureBoundingBox var5) {
         byte var6 = 24;
         if (this.func_175818_a(var5, var2, 0, var2 + 23, 20)) {
            this.a(var3, var5, var2 + 0, 0, 0, var2 + 24, 0, 20, a, a, false);
            this.a(var3, var5, var2 + 0, 1, 0, var2 + 24, 10, 20, false);

            for (int var7 = 0; var7 < 4; var7++) {
               this.a(var3, var5, var2 + var7, var7 + 1, var7, var2 + var7, var7 + 1, 20, b, b, false);
               this.a(var3, var5, var2 + var7 + 7, var7 + 5, var7 + 7, var2 + var7 + 7, var7 + 5, 20, b, b, false);
               this.a(var3, var5, var2 + 17 - var7, var7 + 5, var7 + 7, var2 + 17 - var7, var7 + 5, 20, b, b, false);
               this.a(var3, var5, var2 + 24 - var7, var7 + 1, var7, var2 + 24 - var7, var7 + 1, 20, b, b, false);
               this.a(var3, var5, var2 + var7 + 1, var7 + 1, var7, var2 + 23 - var7, var7 + 1, var7, b, b, false);
               this.a(var3, var5, var2 + var7 + 8, var7 + 5, var7 + 7, var2 + 16 - var7, var7 + 5, var7 + 7, b, b, false);
            }

            this.a(var3, var5, var2 + 4, 4, 4, var2 + 6, 4, 20, a, a, false);
            this.a(var3, var5, var2 + 7, 4, 4, var2 + 17, 4, 6, a, a, false);
            this.a(var3, var5, var2 + 18, 4, 4, var2 + 20, 4, 20, a, a, false);
            this.a(var3, var5, var2 + 11, 8, 11, var2 + 13, 8, 20, a, a, false);
            this.a(var3, field_175824_d, var2 + 12, 9, 12, var5);
            this.a(var3, field_175824_d, var2 + 12, 9, 15, var5);
            this.a(var3, field_175824_d, var2 + 12, 9, 18, var5);
            int var11 = var1 ? var2 + 19 : var2 + 5;
            int var8 = var1 ? var2 + 5 : var2 + 19;

            for (int var9 = 20; var9 >= 5; var9 -= 3) {
               this.a(var3, field_175824_d, var11, 5, var9, var5);
            }

            for (int var12 = 19; var12 >= 7; var12 -= 3) {
               this.a(var3, field_175824_d, var8, 5, var12, var5);
            }

            for (int var13 = 0; var13 < 4; var13++) {
               int var10 = var1 ? var2 + (24 - (17 - var13 * 3)) : var2 + 17 - var13 * 3;
               this.a(var3, field_175824_d, var10, 5, 5, var5);
            }

            this.a(var3, field_175824_d, var8, 5, 5, var5);
            this.a(var3, var5, var2 + 11, 1, 12, var2 + 13, 7, 12, a, a, false);
            this.a(var3, var5, var2 + 12, 1, 11, var2 + 12, 7, 13, a, a, false);
         }
      }

      public void func_175838_g(World var1, Random var2, StructureBoundingBox var3) {
         if (this.func_175818_a(var3, 14, 21, 20, 43)) {
            this.a(var1, var3, 14, 0, 21, 20, 0, 43, a, a, false);
            this.a(var1, var3, 14, 1, 22, 20, 14, 43, false);
            this.a(var1, var3, 18, 12, 22, 20, 12, 39, a, a, false);
            this.a(var1, var3, 18, 12, 21, 20, 12, 21, b, b, false);

            for (int var4 = 0; var4 < 4; var4++) {
               this.a(var1, var3, var4 + 14, var4 + 9, 21, var4 + 14, var4 + 9, 43 - var4, b, b, false);
            }

            for (int var5 = 23; var5 <= 39; var5 += 3) {
               this.a(var1, field_175824_d, 19, 13, var5, var3);
            }
         }

         if (this.func_175818_a(var3, 37, 21, 43, 43)) {
            this.a(var1, var3, 37, 0, 21, 43, 0, 43, a, a, false);
            this.a(var1, var3, 37, 1, 22, 43, 14, 43, false);
            this.a(var1, var3, 37, 12, 22, 39, 12, 39, a, a, false);
            this.a(var1, var3, 37, 12, 21, 39, 12, 21, b, b, false);

            for (int var6 = 0; var6 < 4; var6++) {
               this.a(var1, var3, 43 - var6, var6 + 9, 21, 43 - var6, var6 + 9, 43 - var6, b, b, false);
            }

            for (int var7 = 23; var7 <= 39; var7 += 3) {
               this.a(var1, field_175824_d, 38, 13, var7, var3);
            }
         }

         if (this.func_175818_a(var3, 15, 37, 42, 43)) {
            this.a(var1, var3, 21, 0, 37, 36, 0, 43, a, a, false);
            this.a(var1, var3, 21, 1, 37, 36, 14, 43, false);
            this.a(var1, var3, 21, 12, 37, 36, 12, 39, a, a, false);

            for (int var8 = 0; var8 < 4; var8++) {
               this.a(var1, var3, 15 + var8, var8 + 9, 43 - var8, 42 - var8, var8 + 9, 43 - var8, b, b, false);
            }

            for (int var9 = 21; var9 <= 36; var9 += 3) {
               this.a(var1, field_175824_d, var9, 13, 38, var3);
            }
         }
      }

      public void func_175839_b(World var1, Random var2, StructureBoundingBox var3) {
         if (this.func_175818_a(var3, 22, 5, 35, 17)) {
            this.a(var1, var3, 25, 0, 0, 32, 8, 20, false);

            for (int var4 = 0; var4 < 4; var4++) {
               this.a(var1, var3, 24, 2, 5 + var4 * 4, 24, 4, 5 + var4 * 4, b, b, false);
               this.a(var1, var3, 22, 4, 5 + var4 * 4, 23, 4, 5 + var4 * 4, b, b, false);
               this.a(var1, b, 25, 5, 5 + var4 * 4, var3);
               this.a(var1, b, 26, 6, 5 + var4 * 4, var3);
               this.a(var1, e, 26, 5, 5 + var4 * 4, var3);
               this.a(var1, var3, 33, 2, 5 + var4 * 4, 33, 4, 5 + var4 * 4, b, b, false);
               this.a(var1, var3, 34, 4, 5 + var4 * 4, 35, 4, 5 + var4 * 4, b, b, false);
               this.a(var1, b, 32, 5, 5 + var4 * 4, var3);
               this.a(var1, b, 31, 6, 5 + var4 * 4, var3);
               this.a(var1, e, 31, 5, 5 + var4 * 4, var3);
               this.a(var1, var3, 27, 6, 5 + var4 * 4, 30, 6, 5 + var4 * 4, a, a, false);
            }
         }
      }

      public void func_175837_c(World var1, Random var2, StructureBoundingBox var3) {
         if (this.func_175818_a(var3, 15, 20, 42, 21)) {
            this.a(var1, var3, 15, 0, 21, 42, 0, 21, a, a, false);
            this.a(var1, var3, 26, 1, 21, 31, 3, 21, false);
            this.a(var1, var3, 21, 12, 21, 36, 12, 21, a, a, false);
            this.a(var1, var3, 17, 11, 21, 40, 11, 21, a, a, false);
            this.a(var1, var3, 16, 10, 21, 41, 10, 21, a, a, false);
            this.a(var1, var3, 15, 7, 21, 42, 9, 21, a, a, false);
            this.a(var1, var3, 16, 6, 21, 41, 6, 21, a, a, false);
            this.a(var1, var3, 17, 5, 21, 40, 5, 21, a, a, false);
            this.a(var1, var3, 21, 4, 21, 36, 4, 21, a, a, false);
            this.a(var1, var3, 22, 3, 21, 26, 3, 21, a, a, false);
            this.a(var1, var3, 31, 3, 21, 35, 3, 21, a, a, false);
            this.a(var1, var3, 23, 2, 21, 25, 2, 21, a, a, false);
            this.a(var1, var3, 32, 2, 21, 34, 2, 21, a, a, false);
            this.a(var1, var3, 28, 4, 20, 29, 4, 21, b, b, false);
            this.a(var1, b, 27, 3, 21, var3);
            this.a(var1, b, 30, 3, 21, var3);
            this.a(var1, b, 26, 2, 21, var3);
            this.a(var1, b, 31, 2, 21, var3);
            this.a(var1, b, 25, 1, 21, var3);
            this.a(var1, b, 32, 1, 21, var3);

            for (int var4 = 0; var4 < 7; var4++) {
               this.a(var1, c, 28 - var4, 6 + var4, 21, var3);
               this.a(var1, c, 29 + var4, 6 + var4, 21, var3);
            }

            for (int var5 = 0; var5 < 4; var5++) {
               this.a(var1, c, 28 - var5, 9 + var5, 21, var3);
               this.a(var1, c, 29 + var5, 9 + var5, 21, var3);
            }

            this.a(var1, c, 28, 12, 21, var3);
            this.a(var1, c, 29, 12, 21, var3);

            for (int var6 = 0; var6 < 3; var6++) {
               this.a(var1, c, 22 - var6 * 2, 8, 21, var3);
               this.a(var1, c, 22 - var6 * 2, 9, 21, var3);
               this.a(var1, c, 35 + var6 * 2, 8, 21, var3);
               this.a(var1, c, 35 + var6 * 2, 9, 21, var3);
            }

            this.a(var1, var3, 15, 13, 21, 42, 15, 21, false);
            this.a(var1, var3, 15, 1, 21, 15, 6, 21, false);
            this.a(var1, var3, 16, 1, 21, 16, 5, 21, false);
            this.a(var1, var3, 17, 1, 21, 20, 4, 21, false);
            this.a(var1, var3, 21, 1, 21, 21, 3, 21, false);
            this.a(var1, var3, 22, 1, 21, 22, 2, 21, false);
            this.a(var1, var3, 23, 1, 21, 24, 1, 21, false);
            this.a(var1, var3, 42, 1, 21, 42, 6, 21, false);
            this.a(var1, var3, 41, 1, 21, 41, 5, 21, false);
            this.a(var1, var3, 37, 1, 21, 40, 4, 21, false);
            this.a(var1, var3, 36, 1, 21, 36, 3, 21, false);
            this.a(var1, var3, 33, 1, 21, 34, 1, 21, false);
            this.a(var1, var3, 35, 1, 21, 35, 2, 21, false);
         }
      }

      public MonumentBuilding() {
      }

      public void func_175841_d(World var1, Random var2, StructureBoundingBox var3) {
         if (this.func_175818_a(var3, 21, 21, 36, 36)) {
            this.a(var1, var3, 21, 0, 22, 36, 0, 36, a, a, false);
            this.a(var1, var3, 21, 1, 22, 36, 23, 36, false);

            for (int var4 = 0; var4 < 4; var4++) {
               this.a(var1, var3, 21 + var4, 13 + var4, 21 + var4, 36 - var4, 13 + var4, 21 + var4, b, b, false);
               this.a(var1, var3, 21 + var4, 13 + var4, 36 - var4, 36 - var4, 13 + var4, 36 - var4, b, b, false);
               this.a(var1, var3, 21 + var4, 13 + var4, 22 + var4, 21 + var4, 13 + var4, 35 - var4, b, b, false);
               this.a(var1, var3, 36 - var4, 13 + var4, 22 + var4, 36 - var4, 13 + var4, 35 - var4, b, b, false);
            }

            this.a(var1, var3, 25, 16, 25, 32, 16, 32, a, a, false);
            this.a(var1, var3, 25, 17, 25, 25, 19, 25, b, b, false);
            this.a(var1, var3, 32, 17, 25, 32, 19, 25, b, b, false);
            this.a(var1, var3, 25, 17, 32, 25, 19, 32, b, b, false);
            this.a(var1, var3, 32, 17, 32, 32, 19, 32, b, b, false);
            this.a(var1, b, 26, 20, 26, var3);
            this.a(var1, b, 27, 21, 27, var3);
            this.a(var1, e, 27, 20, 27, var3);
            this.a(var1, b, 26, 20, 31, var3);
            this.a(var1, b, 27, 21, 30, var3);
            this.a(var1, e, 27, 20, 30, var3);
            this.a(var1, b, 31, 20, 31, var3);
            this.a(var1, b, 30, 21, 30, var3);
            this.a(var1, e, 30, 20, 30, var3);
            this.a(var1, b, 31, 20, 26, var3);
            this.a(var1, b, 30, 21, 27, var3);
            this.a(var1, e, 30, 20, 27, var3);
            this.a(var1, var3, 28, 21, 27, 29, 21, 27, a, a, false);
            this.a(var1, var3, 27, 21, 28, 27, 21, 29, a, a, false);
            this.a(var1, var3, 28, 21, 30, 29, 21, 30, a, a, false);
            this.a(var1, var3, 30, 21, 28, 30, 21, 29, a, a, false);
         }
      }

      public List<StructureOceanMonumentPieces.RoomDefinition> func_175836_a(Random var1) {
         StructureOceanMonumentPieces.RoomDefinition[] var2 = new StructureOceanMonumentPieces.RoomDefinition[75];

         for (int var3 = 0; var3 < 5; var3++) {
            for (int var4 = 0; var4 < 4; var4++) {
               byte var5 = 0;
               int var6 = func_175820_a(var3, var5, var4);
               var2[var6] = new StructureOceanMonumentPieces.RoomDefinition(var6);
            }
         }

         for (int var15 = 0; var15 < 5; var15++) {
            for (int var19 = 0; var19 < 4; var19++) {
               byte var23 = 1;
               int var27 = func_175820_a(var15, var23, var19);
               var2[var27] = new StructureOceanMonumentPieces.RoomDefinition(var27);
            }
         }

         for (int var16 = 1; var16 < 4; var16++) {
            for (int var20 = 0; var20 < 2; var20++) {
               byte var24 = 2;
               int var28 = func_175820_a(var16, var24, var20);
               var2[var28] = new StructureOceanMonumentPieces.RoomDefinition(var28);
            }
         }

         this.recoveredField1839 = var2[field_175823_g];

         for (int var17 = 0; var17 < 5; var17++) {
            for (int var21 = 0; var21 < 5; var21++) {
               for (int var25 = 0; var25 < 3; var25++) {
                  int var29 = func_175820_a(var17, var25, var21);
                  if (var2[var29] != null) {
                     for (EnumFacing var10 : EnumFacing.values()) {
                        int var11 = var17 + var10.getFrontOffsetX();
                        int var12 = var25 + var10.getFrontOffsetY();
                        int var13 = var21 + var10.getFrontOffsetZ();
                        if (var11 >= 0 && var11 < 5 && var13 >= 0 && var13 < 5 && var12 >= 0 && var12 < 3) {
                           int var14 = func_175820_a(var11, var12, var13);
                           if (var2[var14] != null) {
                              if (var13 != var21) {
                                 var2[var29].func_175957_a(var10.getOpposite(), var2[var14]);
                              } else {
                                 var2[var29].func_175957_a(var10, var2[var14]);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         StructureOceanMonumentPieces.RoomDefinition var18;
         var2[field_175831_h].func_175957_a(EnumFacing.UP, var18 = new StructureOceanMonumentPieces.RoomDefinition(1003));
         StructureOceanMonumentPieces.RoomDefinition var22;
         var2[field_175832_i].func_175957_a(EnumFacing.SOUTH, var22 = new StructureOceanMonumentPieces.RoomDefinition(1001));
         StructureOceanMonumentPieces.RoomDefinition var26;
         var2[field_175829_j].func_175957_a(EnumFacing.SOUTH, var26 = new StructureOceanMonumentPieces.RoomDefinition(1002));
         var18.field_175963_d = true;
         var22.field_175963_d = true;
         var26.field_175963_d = true;
         this.recoveredField1839.field_175964_e = true;
         this.recoveredField1840 = var2[func_175820_a(var1.nextInt(4), 0, 2)];
         this.recoveredField1840.field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         this.recoveredField1840.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()]
            .field_175963_d = true;
         ArrayList var30 = Lists.newArrayList();

         for (StructureOceanMonumentPieces.RoomDefinition var37 : var2) {
            if (var37 != null) {
               var37.func_175958_a();
               var30.add(var37);
            }
         }

         var18.func_175958_a();
         Collections.shuffle(var30, var1);
         int var32 = 1;

         for (StructureOceanMonumentPieces.RoomDefinition var36 : (Iterable<StructureOceanMonumentPieces.RoomDefinition>)(Iterable<?>)(var30)) {
            int var38 = 0;
            int var39 = 0;

            while (var38 < 2 && var39 < 5) {
               var39++;
               int var40 = var1.nextInt(6);
               if (var36.field_175966_c[var40]) {
                  int var41 = EnumFacing.getFront(var40).getOpposite().getIndex();
                  var36.field_175966_c[var40] = false;
                  var36.field_175965_b[var40].field_175966_c[var41] = false;
                  if (var36.func_175959_a(var32++) && var36.field_175965_b[var40].func_175959_a(var32++)) {
                     var38++;
                  } else {
                     var36.field_175966_c[var40] = true;
                     var36.field_175965_b[var40].field_175966_c[var41] = true;
                  }
               }
            }
         }

         var30.add(var18);
         var30.add(var22);
         var30.add(var26);
         return var30;
      }
   }

   public interface MonumentRoomFitHelper {
      boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1);

      StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3);
   }

   public static class Penthouse extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         this.a(var1, var3, 2, -1, 2, 11, -1, 11, b, b, false);
         this.a(var1, var3, 0, -1, 0, 1, -1, 11, a, a, false);
         this.a(var1, var3, 12, -1, 0, 13, -1, 11, a, a, false);
         this.a(var1, var3, 2, -1, 0, 11, -1, 1, a, a, false);
         this.a(var1, var3, 2, -1, 12, 11, -1, 13, a, a, false);
         this.a(var1, var3, 0, 0, 0, 0, 0, 13, b, b, false);
         this.a(var1, var3, 13, 0, 0, 13, 0, 13, b, b, false);
         this.a(var1, var3, 1, 0, 0, 12, 0, 0, b, b, false);
         this.a(var1, var3, 1, 0, 13, 12, 0, 13, b, b, false);

         for (int var4 = 2; var4 <= 11; var4 += 3) {
            this.a(var1, e, 0, 0, var4, var3);
            this.a(var1, e, 13, 0, var4, var3);
            this.a(var1, e, var4, 0, 0, var3);
         }

         this.a(var1, var3, 2, 0, 3, 4, 0, 9, b, b, false);
         this.a(var1, var3, 9, 0, 3, 11, 0, 9, b, b, false);
         this.a(var1, var3, 4, 0, 9, 9, 0, 11, b, b, false);
         this.a(var1, b, 5, 0, 8, var3);
         this.a(var1, b, 8, 0, 8, var3);
         this.a(var1, b, 10, 0, 10, var3);
         this.a(var1, b, 3, 0, 10, var3);
         this.a(var1, var3, 3, 0, 3, 3, 0, 7, c, c, false);
         this.a(var1, var3, 10, 0, 3, 10, 0, 7, c, c, false);
         this.a(var1, var3, 6, 0, 10, 7, 0, 10, c, c, false);
         byte var7 = 3;

         for (int var5 = 0; var5 < 2; var5++) {
            for (int var6 = 2; var6 <= 8; var6 += 3) {
               this.a(var1, var3, var7, 0, var6, var7, 2, var6, b, b, false);
            }

            var7 = 10;
         }

         this.a(var1, var3, 5, 0, 10, 5, 2, 10, b, b, false);
         this.a(var1, var3, 8, 0, 10, 8, 2, 10, b, b, false);
         this.a(var1, var3, 6, -1, 7, 7, -1, 8, c, c, false);
         this.a(var1, var3, 6, -1, 3, 7, -1, 4, false);
         this.a(var1, var3, 6, 1, 6);
         return true;
      }

      public Penthouse(EnumFacing var1, StructureBoundingBox var2) {
         super(var1, var2);
      }

      public Penthouse() {
      }
   }

   public abstract static class Piece extends StructureComponent {
      public static IBlockState a = Blocks.prismarine.getStateFromMeta(BlockPrismarine.ROUGH_META);
      public static IBlockState b = Blocks.prismarine.getStateFromMeta(BlockPrismarine.BRICKS_META);
      public static IBlockState c = Blocks.prismarine.getStateFromMeta(BlockPrismarine.DARK_META);
      public static IBlockState field_175824_d = StructureOceanMonumentPieces.Piece.b;
      public StructureOceanMonumentPieces.RoomDefinition k;
      public static IBlockState e = Blocks.sea_lantern.getDefaultState();
      public static IBlockState field_175822_f = Blocks.water.getDefaultState();
      public static int field_175823_g = func_175820_a(2, 0, 0);
      public static int field_175831_h = func_175820_a(2, 2, 0);
      public static int field_175832_i = func_175820_a(0, 1, 0);
      public static int field_175829_j = func_175820_a(4, 1, 0);

      public boolean a(World var1, StructureBoundingBox var2, int var3, int var4, int var5) {
         int var6 = this.a(var3, var5);
         int var7 = this.d(var4);
         int var8 = this.b(var3, var5);
         if (var2.isVecInside(new BlockPos(var6, var7, var8))) {
            EntityGuardian var9 = new EntityGuardian(var1);
            var9.setElder(true);
            var9.heal(var9.getMaxHealth());
            var9.a_(var6 + 0.5, var7, var8 + 0.5, 0.0F, 0.0F);
            var9.onInitialSpawn(var1.E(new BlockPos(var9)), (IEntityLivingData)null);
            var1.spawnEntityInWorld(var9);
            return true;
         } else {
            return false;
         }
      }

      public Piece() {
         super(0);
      }

      public Piece(int var1) {
         super(var1);
      }

      public void a(World var1, StructureBoundingBox var2, int var3, int var4, boolean var5) {
         if (var5) {
            this.a(var1, var2, var3 + 0, 0, var4 + 0, var3 + 2, 0, var4 + 8 - 1, a, a, false);
            this.a(var1, var2, var3 + 5, 0, var4 + 0, var3 + 8 - 1, 0, var4 + 8 - 1, a, a, false);
            this.a(var1, var2, var3 + 3, 0, var4 + 0, var3 + 4, 0, var4 + 2, a, a, false);
            this.a(var1, var2, var3 + 3, 0, var4 + 5, var3 + 4, 0, var4 + 8 - 1, a, a, false);
            this.a(var1, var2, var3 + 3, 0, var4 + 2, var3 + 4, 0, var4 + 2, b, b, false);
            this.a(var1, var2, var3 + 3, 0, var4 + 5, var3 + 4, 0, var4 + 5, b, b, false);
            this.a(var1, var2, var3 + 2, 0, var4 + 3, var3 + 2, 0, var4 + 4, b, b, false);
            this.a(var1, var2, var3 + 5, 0, var4 + 3, var3 + 5, 0, var4 + 4, b, b, false);
         } else {
            this.a(var1, var2, var3 + 0, 0, var4 + 0, var3 + 8 - 1, 0, var4 + 8 - 1, a, a, false);
         }
      }

      public Piece(int var1, EnumFacing var2, StructureOceanMonumentPieces.RoomDefinition var3, int var4, int var5, int var6) {
         super(var1);
         this.m = var2;
         this.k = var3;
         int var7 = var3.field_175967_a;
         int var8 = var7 % 5;
         int var9 = var7 / 5 % 5;
         int var10 = var7 / 25;
         if (var2 != EnumFacing.NORTH && var2 != EnumFacing.SOUTH) {
            this.l = new StructureBoundingBox(0, 0, 0, var6 * 8 - 1, var5 * 4 - 1, var4 * 8 - 1);
         } else {
            this.l = new StructureBoundingBox(0, 0, 0, var4 * 8 - 1, var5 * 4 - 1, var6 * 8 - 1);
         }

         switch (var2) {
            case NORTH:
               this.l.offset(var8 * 8, var10 * 4, -(var9 + var6) * 8 + 1);
               break;
            case SOUTH:
               this.l.offset(var8 * 8, var10 * 4, var9 * 8);
               break;
            case WEST:
               this.l.offset(-(var9 + var6) * 8 + 1, var10 * 4, var8 * 8);
               break;
            default:
               this.l.offset(var9 * 8, var10 * 4, var8 * 8);
         }
      }

      public void a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
         for (int var10 = var4; var10 <= var7; var10++) {
            for (int var11 = var3; var11 <= var6; var11++) {
               for (int var12 = var5; var12 <= var8; var12++) {
                  if (!var9 || this.a(var1, var11, var10, var12, var2).getBlock().getMaterial() != Material.air) {
                     if (this.d(var10) >= var1.F()) {
                        this.a(var1, Blocks.air.getDefaultState(), var11, var10, var12, var2);
                     } else {
                        this.a(var1, field_175822_f, var11, var10, var12, var2);
                     }
                  }
               }
            }
         }
      }

      public static int func_175820_a(int var0, int var1, int var2) {
         return var1 * 25 + var2 * 5 + var0;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
      }

      public void a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, IBlockState var9) {
         for (int var10 = var4; var10 <= var7; var10++) {
            for (int var11 = var3; var11 <= var6; var11++) {
               for (int var12 = var5; var12 <= var8; var12++) {
                  if (this.a(var1, var11, var10, var12, var2) == field_175822_f) {
                     this.a(var1, var9, var11, var10, var12, var2);
                  }
               }
            }
         }
      }

      public boolean func_175818_a(StructureBoundingBox var1, int var2, int var3, int var4, int var5) {
         int var6 = this.a(var2, var3);
         int var7 = this.b(var2, var3);
         int var8 = this.a(var4, var5);
         int var9 = this.b(var4, var5);
         return var1.intersectsWith(Math.min(var6, var8), Math.min(var7, var9), Math.max(var6, var8), Math.max(var7, var9));
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
      }

      public Piece(EnumFacing var1, StructureBoundingBox var2) {
         super(1);
         this.m = var1;
         this.l = var2;
      }
   }

   public static class RoomDefinition {
      public boolean field_175964_e;
      public boolean[] field_175966_c;
      public int field_175967_a;
      public int field_175962_f;
      public StructureOceanMonumentPieces.RoomDefinition[] field_175965_b = new StructureOceanMonumentPieces.RoomDefinition[6];
      public boolean field_175963_d;

      public boolean func_175961_b() {
         return this.field_175967_a >= 75;
      }

      public int func_175960_c() {
         int var1 = 0;

         for (int var2 = 0; var2 < 6; var2++) {
            if (this.field_175966_c[var2]) {
               var1++;
            }
         }

         return var1;
      }

      public boolean func_175959_a(int var1) {
         if (this.field_175964_e) {
            return true;
         } else {
            this.field_175962_f = var1;

            for (int var2 = 0; var2 < 6; var2++) {
               if (this.field_175965_b[var2] != null
                  && this.field_175966_c[var2]
                  && this.field_175965_b[var2].field_175962_f != var1
                  && this.field_175965_b[var2].func_175959_a(var1)) {
                  return true;
               }
            }

            return false;
         }
      }

      public void func_175958_a() {
         for (int var1 = 0; var1 < 6; var1++) {
            this.field_175966_c[var1] = this.field_175965_b[var1] != null;
         }
      }

      public void func_175957_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2) {
         this.field_175965_b[var1.getIndex()] = var2;
         var2.field_175965_b[var1.getOpposite().getIndex()] = this;
      }

      public RoomDefinition(int var1) {
         this.field_175966_c = new boolean[6];
         this.field_175967_a = var1;
      }
   }

   public static class SimpleRoom extends StructureOceanMonumentPieces.Piece {
      public int field_175833_o;

      public SimpleRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 1, 1, 1);
         this.field_175833_o = var3.nextInt(3);
      }

      public SimpleRoom() {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 0, 0, this.k.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         if (this.k.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 4, 1, 6, 4, 6, a);
         }

         boolean var4 = this.field_175833_o != 0
            && var2.nextBoolean()
            && !this.k.field_175966_c[EnumFacing.DOWN.getIndex()]
            && !this.k.field_175966_c[EnumFacing.UP.getIndex()]
            && this.k.func_175960_c() > 1;
         if (this.field_175833_o == 0) {
            this.a(var1, var3, 0, 1, 0, 2, 1, 2, b, b, false);
            this.a(var1, var3, 0, 3, 0, 2, 3, 2, b, b, false);
            this.a(var1, var3, 0, 2, 0, 0, 2, 2, a, a, false);
            this.a(var1, var3, 1, 2, 0, 2, 2, 0, a, a, false);
            this.a(var1, e, 1, 2, 1, var3);
            this.a(var1, var3, 5, 1, 0, 7, 1, 2, b, b, false);
            this.a(var1, var3, 5, 3, 0, 7, 3, 2, b, b, false);
            this.a(var1, var3, 7, 2, 0, 7, 2, 2, a, a, false);
            this.a(var1, var3, 5, 2, 0, 6, 2, 0, a, a, false);
            this.a(var1, e, 6, 2, 1, var3);
            this.a(var1, var3, 0, 1, 5, 2, 1, 7, b, b, false);
            this.a(var1, var3, 0, 3, 5, 2, 3, 7, b, b, false);
            this.a(var1, var3, 0, 2, 5, 0, 2, 7, a, a, false);
            this.a(var1, var3, 1, 2, 7, 2, 2, 7, a, a, false);
            this.a(var1, e, 1, 2, 6, var3);
            this.a(var1, var3, 5, 1, 5, 7, 1, 7, b, b, false);
            this.a(var1, var3, 5, 3, 5, 7, 3, 7, b, b, false);
            this.a(var1, var3, 7, 2, 5, 7, 2, 7, a, a, false);
            this.a(var1, var3, 5, 2, 7, 6, 2, 7, a, a, false);
            this.a(var1, e, 6, 2, 6, var3);
            if (this.k.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
               this.a(var1, var3, 3, 3, 0, 4, 3, 0, b, b, false);
            } else {
               this.a(var1, var3, 3, 3, 0, 4, 3, 1, b, b, false);
               this.a(var1, var3, 3, 2, 0, 4, 2, 0, a, a, false);
               this.a(var1, var3, 3, 1, 0, 4, 1, 1, b, b, false);
            }

            if (this.k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
               this.a(var1, var3, 3, 3, 7, 4, 3, 7, b, b, false);
            } else {
               this.a(var1, var3, 3, 3, 6, 4, 3, 7, b, b, false);
               this.a(var1, var3, 3, 2, 7, 4, 2, 7, a, a, false);
               this.a(var1, var3, 3, 1, 6, 4, 1, 7, b, b, false);
            }

            if (this.k.field_175966_c[EnumFacing.WEST.getIndex()]) {
               this.a(var1, var3, 0, 3, 3, 0, 3, 4, b, b, false);
            } else {
               this.a(var1, var3, 0, 3, 3, 1, 3, 4, b, b, false);
               this.a(var1, var3, 0, 2, 3, 0, 2, 4, a, a, false);
               this.a(var1, var3, 0, 1, 3, 1, 1, 4, b, b, false);
            }

            if (this.k.field_175966_c[EnumFacing.EAST.getIndex()]) {
               this.a(var1, var3, 7, 3, 3, 7, 3, 4, b, b, false);
            } else {
               this.a(var1, var3, 6, 3, 3, 7, 3, 4, b, b, false);
               this.a(var1, var3, 7, 2, 3, 7, 2, 4, a, a, false);
               this.a(var1, var3, 6, 1, 3, 7, 1, 4, b, b, false);
            }
         } else if (this.field_175833_o == 1) {
            this.a(var1, var3, 2, 1, 2, 2, 3, 2, b, b, false);
            this.a(var1, var3, 2, 1, 5, 2, 3, 5, b, b, false);
            this.a(var1, var3, 5, 1, 5, 5, 3, 5, b, b, false);
            this.a(var1, var3, 5, 1, 2, 5, 3, 2, b, b, false);
            this.a(var1, e, 2, 2, 2, var3);
            this.a(var1, e, 2, 2, 5, var3);
            this.a(var1, e, 5, 2, 5, var3);
            this.a(var1, e, 5, 2, 2, var3);
            this.a(var1, var3, 0, 1, 0, 1, 3, 0, b, b, false);
            this.a(var1, var3, 0, 1, 1, 0, 3, 1, b, b, false);
            this.a(var1, var3, 0, 1, 7, 1, 3, 7, b, b, false);
            this.a(var1, var3, 0, 1, 6, 0, 3, 6, b, b, false);
            this.a(var1, var3, 6, 1, 7, 7, 3, 7, b, b, false);
            this.a(var1, var3, 7, 1, 6, 7, 3, 6, b, b, false);
            this.a(var1, var3, 6, 1, 0, 7, 3, 0, b, b, false);
            this.a(var1, var3, 7, 1, 1, 7, 3, 1, b, b, false);
            this.a(var1, a, 1, 2, 0, var3);
            this.a(var1, a, 0, 2, 1, var3);
            this.a(var1, a, 1, 2, 7, var3);
            this.a(var1, a, 0, 2, 6, var3);
            this.a(var1, a, 6, 2, 7, var3);
            this.a(var1, a, 7, 2, 6, var3);
            this.a(var1, a, 6, 2, 0, var3);
            this.a(var1, a, 7, 2, 1, var3);
            if (!this.k.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
               this.a(var1, var3, 1, 3, 0, 6, 3, 0, b, b, false);
               this.a(var1, var3, 1, 2, 0, 6, 2, 0, a, a, false);
               this.a(var1, var3, 1, 1, 0, 6, 1, 0, b, b, false);
            }

            if (!this.k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
               this.a(var1, var3, 1, 3, 7, 6, 3, 7, b, b, false);
               this.a(var1, var3, 1, 2, 7, 6, 2, 7, a, a, false);
               this.a(var1, var3, 1, 1, 7, 6, 1, 7, b, b, false);
            }

            if (!this.k.field_175966_c[EnumFacing.WEST.getIndex()]) {
               this.a(var1, var3, 0, 3, 1, 0, 3, 6, b, b, false);
               this.a(var1, var3, 0, 2, 1, 0, 2, 6, a, a, false);
               this.a(var1, var3, 0, 1, 1, 0, 1, 6, b, b, false);
            }

            if (!this.k.field_175966_c[EnumFacing.EAST.getIndex()]) {
               this.a(var1, var3, 7, 3, 1, 7, 3, 6, b, b, false);
               this.a(var1, var3, 7, 2, 1, 7, 2, 6, a, a, false);
               this.a(var1, var3, 7, 1, 1, 7, 1, 6, b, b, false);
            }
         } else if (this.field_175833_o == 2) {
            this.a(var1, var3, 0, 1, 0, 0, 1, 7, b, b, false);
            this.a(var1, var3, 7, 1, 0, 7, 1, 7, b, b, false);
            this.a(var1, var3, 1, 1, 0, 6, 1, 0, b, b, false);
            this.a(var1, var3, 1, 1, 7, 6, 1, 7, b, b, false);
            this.a(var1, var3, 0, 2, 0, 0, 2, 7, c, c, false);
            this.a(var1, var3, 7, 2, 0, 7, 2, 7, c, c, false);
            this.a(var1, var3, 1, 2, 0, 6, 2, 0, c, c, false);
            this.a(var1, var3, 1, 2, 7, 6, 2, 7, c, c, false);
            this.a(var1, var3, 0, 3, 0, 0, 3, 7, b, b, false);
            this.a(var1, var3, 7, 3, 0, 7, 3, 7, b, b, false);
            this.a(var1, var3, 1, 3, 0, 6, 3, 0, b, b, false);
            this.a(var1, var3, 1, 3, 7, 6, 3, 7, b, b, false);
            this.a(var1, var3, 0, 1, 3, 0, 2, 4, c, c, false);
            this.a(var1, var3, 7, 1, 3, 7, 2, 4, c, c, false);
            this.a(var1, var3, 3, 1, 0, 4, 2, 0, c, c, false);
            this.a(var1, var3, 3, 1, 7, 4, 2, 7, c, c, false);
            if (this.k.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
               this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
            }

            if (this.k.field_175966_c[EnumFacing.NORTH.getIndex()]) {
               this.a(var1, var3, 3, 1, 7, 4, 2, 7, false);
            }

            if (this.k.field_175966_c[EnumFacing.WEST.getIndex()]) {
               this.a(var1, var3, 0, 1, 3, 0, 2, 4, false);
            }

            if (this.k.field_175966_c[EnumFacing.EAST.getIndex()]) {
               this.a(var1, var3, 7, 1, 3, 7, 2, 4, false);
            }
         }

         if (var4) {
            this.a(var1, var3, 3, 1, 3, 4, 1, 4, b, b, false);
            this.a(var1, var3, 3, 2, 3, 4, 2, 4, a, a, false);
            this.a(var1, var3, 3, 3, 3, 4, 3, 4, b, b, false);
         }

         return true;
      }
   }

   public static class SimpleTopRoom extends StructureOceanMonumentPieces.Piece {
      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.k.field_175967_a / 25 > 0) {
            this.a(var1, var3, 0, 0, this.k.field_175966_c[EnumFacing.DOWN.getIndex()]);
         }

         if (this.k.field_175965_b[EnumFacing.UP.getIndex()] == null) {
            this.a(var1, var3, 1, 4, 1, 6, 4, 6, a);
         }

         for (int var4 = 1; var4 <= 6; var4++) {
            for (int var5 = 1; var5 <= 6; var5++) {
               if (var2.nextInt(3) != 0) {
                  int var6 = 2 + (var2.nextInt(4) == 0 ? 0 : 1);
                  this.a(var1, var3, var4, var6, var5, var4, 3, var5, Blocks.sponge.getStateFromMeta(1), Blocks.sponge.getStateFromMeta(1), false);
               }
            }
         }

         this.a(var1, var3, 0, 1, 0, 0, 1, 7, b, b, false);
         this.a(var1, var3, 7, 1, 0, 7, 1, 7, b, b, false);
         this.a(var1, var3, 1, 1, 0, 6, 1, 0, b, b, false);
         this.a(var1, var3, 1, 1, 7, 6, 1, 7, b, b, false);
         this.a(var1, var3, 0, 2, 0, 0, 2, 7, c, c, false);
         this.a(var1, var3, 7, 2, 0, 7, 2, 7, c, c, false);
         this.a(var1, var3, 1, 2, 0, 6, 2, 0, c, c, false);
         this.a(var1, var3, 1, 2, 7, 6, 2, 7, c, c, false);
         this.a(var1, var3, 0, 3, 0, 0, 3, 7, b, b, false);
         this.a(var1, var3, 7, 3, 0, 7, 3, 7, b, b, false);
         this.a(var1, var3, 1, 3, 0, 6, 3, 0, b, b, false);
         this.a(var1, var3, 1, 3, 7, 6, 3, 7, b, b, false);
         this.a(var1, var3, 0, 1, 3, 0, 2, 4, c, c, false);
         this.a(var1, var3, 7, 1, 3, 7, 2, 4, c, c, false);
         this.a(var1, var3, 3, 1, 0, 4, 2, 0, c, c, false);
         this.a(var1, var3, 3, 1, 7, 4, 2, 7, c, c, false);
         if (this.k.field_175966_c[EnumFacing.SOUTH.getIndex()]) {
            this.a(var1, var3, 3, 1, 0, 4, 2, 0, false);
         }

         return true;
      }

      public SimpleTopRoom() {
      }

      public SimpleTopRoom(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         super(1, var1, var2, 1, 1, 1);
      }
   }

   public static class WingRoom extends StructureOceanMonumentPieces.Piece {
      public int field_175834_o;

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.field_175834_o == 0) {
            for (int var4 = 0; var4 < 4; var4++) {
               this.a(var1, var3, 10 - var4, 3 - var4, 20 - var4, 12 + var4, 3 - var4, 20, b, b, false);
            }

            this.a(var1, var3, 7, 0, 6, 15, 0, 16, b, b, false);
            this.a(var1, var3, 6, 0, 6, 6, 3, 20, b, b, false);
            this.a(var1, var3, 16, 0, 6, 16, 3, 20, b, b, false);
            this.a(var1, var3, 7, 1, 7, 7, 1, 20, b, b, false);
            this.a(var1, var3, 15, 1, 7, 15, 1, 20, b, b, false);
            this.a(var1, var3, 7, 1, 6, 9, 3, 6, b, b, false);
            this.a(var1, var3, 13, 1, 6, 15, 3, 6, b, b, false);
            this.a(var1, var3, 8, 1, 7, 9, 1, 7, b, b, false);
            this.a(var1, var3, 13, 1, 7, 14, 1, 7, b, b, false);
            this.a(var1, var3, 9, 0, 5, 13, 0, 5, b, b, false);
            this.a(var1, var3, 10, 0, 7, 12, 0, 7, c, c, false);
            this.a(var1, var3, 8, 0, 10, 8, 0, 12, c, c, false);
            this.a(var1, var3, 14, 0, 10, 14, 0, 12, c, c, false);

            for (int var8 = 18; var8 >= 7; var8 -= 3) {
               this.a(var1, e, 6, 3, var8, var3);
               this.a(var1, e, 16, 3, var8, var3);
            }

            this.a(var1, e, 10, 0, 10, var3);
            this.a(var1, e, 12, 0, 10, var3);
            this.a(var1, e, 10, 0, 12, var3);
            this.a(var1, e, 12, 0, 12, var3);
            this.a(var1, e, 8, 3, 6, var3);
            this.a(var1, e, 14, 3, 6, var3);
            this.a(var1, b, 4, 2, 4, var3);
            this.a(var1, e, 4, 1, 4, var3);
            this.a(var1, b, 4, 0, 4, var3);
            this.a(var1, b, 18, 2, 4, var3);
            this.a(var1, e, 18, 1, 4, var3);
            this.a(var1, b, 18, 0, 4, var3);
            this.a(var1, b, 4, 2, 18, var3);
            this.a(var1, e, 4, 1, 18, var3);
            this.a(var1, b, 4, 0, 18, var3);
            this.a(var1, b, 18, 2, 18, var3);
            this.a(var1, e, 18, 1, 18, var3);
            this.a(var1, b, 18, 0, 18, var3);
            this.a(var1, b, 9, 7, 20, var3);
            this.a(var1, b, 13, 7, 20, var3);
            this.a(var1, var3, 6, 0, 21, 7, 4, 21, b, b, false);
            this.a(var1, var3, 15, 0, 21, 16, 4, 21, b, b, false);
            this.a(var1, var3, 11, 2, 16);
         } else if (this.field_175834_o == 1) {
            this.a(var1, var3, 9, 3, 18, 13, 3, 20, b, b, false);
            this.a(var1, var3, 9, 0, 18, 9, 2, 18, b, b, false);
            this.a(var1, var3, 13, 0, 18, 13, 2, 18, b, b, false);
            byte var9 = 9;
            byte var5 = 20;
            byte var6 = 5;

            for (int var7 = 0; var7 < 2; var7++) {
               this.a(var1, b, var9, var6 + 1, var5, var3);
               this.a(var1, e, var9, var6, var5, var3);
               this.a(var1, b, var9, var6 - 1, var5, var3);
               var9 = 13;
            }

            this.a(var1, var3, 7, 3, 7, 15, 3, 14, b, b, false);
            var9 = 10;

            for (int var12 = 0; var12 < 2; var12++) {
               this.a(var1, var3, var9, 0, 10, var9, 6, 10, b, b, false);
               this.a(var1, var3, var9, 0, 12, var9, 6, 12, b, b, false);
               this.a(var1, e, var9, 0, 10, var3);
               this.a(var1, e, var9, 0, 12, var3);
               this.a(var1, e, var9, 4, 10, var3);
               this.a(var1, e, var9, 4, 12, var3);
               var9 = 12;
            }

            var9 = 8;

            for (int var13 = 0; var13 < 2; var13++) {
               this.a(var1, var3, var9, 0, 7, var9, 2, 7, b, b, false);
               this.a(var1, var3, var9, 0, 14, var9, 2, 14, b, b, false);
               var9 = 14;
            }

            this.a(var1, var3, 8, 3, 8, 8, 3, 13, c, c, false);
            this.a(var1, var3, 14, 3, 8, 14, 3, 13, c, c, false);
            this.a(var1, var3, 11, 5, 13);
         }

         return true;
      }

      public WingRoom(EnumFacing var1, StructureBoundingBox var2, int var3) {
         super(var1, var2);
         this.field_175834_o = var3 & 1;
      }

      public WingRoom() {
      }
   }

   public static class XYDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      public XYDoubleRoomFitHelper() {
      }

      @Override
      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1) {
         if (var1.field_175966_c[EnumFacing.EAST.getIndex()]
            && !var1.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d
            && var1.field_175966_c[EnumFacing.UP.getIndex()]
            && !var1.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d) {
            StructureOceanMonumentPieces.RoomDefinition var2 = var1.field_175965_b[EnumFacing.EAST.getIndex()];
            return var2.field_175966_c[EnumFacing.UP.getIndex()] && !var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
         } else {
            return false;
         }
      }

      @Override
      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         var2.field_175963_d = true;
         var2.field_175965_b[EnumFacing.EAST.getIndex()].field_175963_d = true;
         var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         var2.field_175965_b[EnumFacing.EAST.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleXYRoom(var1, var2, var3);
      }
   }

   public static class YZDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         var2.field_175963_d = true;
         var2.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
         var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         var2.field_175965_b[EnumFacing.NORTH.getIndex()].field_175965_b[EnumFacing.UP.getIndex()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleYZRoom(var1, var2, var3);
      }

      public YZDoubleRoomFitHelper() {
      }

      @Override
      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1) {
         if (var1.field_175966_c[EnumFacing.NORTH.getIndex()]
            && !var1.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d
            && var1.field_175966_c[EnumFacing.UP.getIndex()]
            && !var1.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d) {
            StructureOceanMonumentPieces.RoomDefinition var2 = var1.field_175965_b[EnumFacing.NORTH.getIndex()];
            return var2.field_175966_c[EnumFacing.UP.getIndex()] && !var2.field_175965_b[EnumFacing.UP.getIndex()].field_175963_d;
         } else {
            return false;
         }
      }
   }

   public static class ZDoubleRoomFitHelper implements StructureOceanMonumentPieces.MonumentRoomFitHelper {
      @Override
      public StructureOceanMonumentPieces.Piece func_175968_a(EnumFacing var1, StructureOceanMonumentPieces.RoomDefinition var2, Random var3) {
         StructureOceanMonumentPieces.RoomDefinition var4 = var2;
         if (!var2.field_175966_c[EnumFacing.NORTH.getIndex()] || var2.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d) {
            var4 = var2.field_175965_b[EnumFacing.SOUTH.getIndex()];
         }

         var4.field_175963_d = true;
         var4.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d = true;
         return new StructureOceanMonumentPieces.DoubleZRoom(var1, var4, var3);
      }

      public ZDoubleRoomFitHelper() {
      }

      @Override
      public boolean func_175969_a(StructureOceanMonumentPieces.RoomDefinition var1) {
         return var1.field_175966_c[EnumFacing.NORTH.getIndex()] && !var1.field_175965_b[EnumFacing.NORTH.getIndex()].field_175963_d;
      }
   }
}
