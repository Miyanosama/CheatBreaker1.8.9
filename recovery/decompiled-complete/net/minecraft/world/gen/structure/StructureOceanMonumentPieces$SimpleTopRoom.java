package net.minecraft.world.gen.structure;

import io.netty.channel.group.ChannelMatchers$InstanceMatcher;
import java.util.Random;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureOceanMonumentPieces$SimpleTopRoom extends StructureOceanMonumentPieces$Piece {
   public ModelWitch field_0001;
   public ChannelMatchers$InstanceMatcher field_0002;
   public StructureVillagePieces$House3 field_0000;

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

   public StructureOceanMonumentPieces$SimpleTopRoom() {
   }

   public StructureOceanMonumentPieces$SimpleTopRoom(EnumFacing var1, StructureOceanMonumentPieces$RoomDefinition var2, Random var3) {
      super(1, var1, var2, 1, 1, 1);
   }
}
