package net.minecraft.world.gen.structure;

import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import java.util.Random;
import net.minecraft.tileentity.TileEntityBeacon$BeamSegment;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.WorldServerMulti$1;
import net.optifine.entity.model.ModelAdapterPig;
import net.optifine.reflect.ReflectorFields;
import recovered.unidentified.UnidentifiedClass1583;

public class StructureOceanMonumentPieces$WingRoom extends StructureOceanMonumentPieces$Piece {
   public WorldServerMulti$1 field_0003;
   public ModelAdapterPig field_0006;
   public TileEntityBeacon$BeamSegment field_0002;
   public ReflectorFields field_0005;
   public PongWebSocketFrame field_0000;
   public int field_175834_o;
   public UnidentifiedClass1583 field_0004;

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

         for (byte var8 = 18; var8 >= 7; var8 -= 3) {
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

   public StructureOceanMonumentPieces$WingRoom(EnumFacing var1, StructureBoundingBox var2, int var3) {
      super(var1, var2);
      this.field_175834_o = var3 & 1;
   }

   public StructureOceanMonumentPieces$WingRoom() {
   }
}
