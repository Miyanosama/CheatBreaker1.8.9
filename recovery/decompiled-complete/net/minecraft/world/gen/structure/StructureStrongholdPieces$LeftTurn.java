package net.minecraft.world.gen.structure;

import io.netty.channel.epoll.Epoll;
import io.netty.handler.codec.http.HttpClientCodec$Encoder;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockBanner;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureStrongholdPieces$LeftTurn extends StructureStrongholdPieces$Stronghold {
   public HttpClientCodec$Encoder field_0001;
   public BlockBanner field_0003;
   public ScreenChatOptions field_0000;
   public Epoll field_0002;

   public static StructureStrongholdPieces$LeftTurn func_175867_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -1, 0, 5, 5, 5, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureStrongholdPieces$LeftTurn(var6, var1, var7, var5)
         : null;
   }

   public StructureStrongholdPieces$LeftTurn() {
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      if (this.m != EnumFacing.NORTH && this.m != EnumFacing.EAST) {
         this.c((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      } else {
         this.b((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      }
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.a(var1, var3)) {
         return false;
      } else {
         this.a(var1, var3, 0, 0, 0, 4, 4, 4, true, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var2, var3, this.d, 1, 1, 0);
         if (this.m != EnumFacing.NORTH && this.m != EnumFacing.EAST) {
            this.a(var1, var3, 4, 1, 1, 4, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         } else {
            this.a(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         return true;
      }
   }

   public StructureStrongholdPieces$LeftTurn(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.d = this.a(var2);
      this.l = var3;
   }
}
