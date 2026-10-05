package net.minecraft.world.gen.structure;

import com.cheatbreaker.client.ui.mainmenu.MainMenuBase;
import io.netty.buffer.AbstractByteBufAllocator;
import io.netty.handler.codec.base64.Base64Dialect;
import io.netty.handler.ssl.SslHandler$7;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockTorch;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureVillagePieces$Torch extends StructureVillagePieces$Village {
   public MainMenuBase field_0001;
   public SslHandler$7 field_0003;
   public AbstractByteBufAllocator field_0000;
   public Base64Dialect field_0002;

   public StructureVillagePieces$Torch(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
      super(var1, var2);
      this.m = var5;
      this.l = var4;
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.h < 0) {
         this.h = this.b(var1, var3);
         if (this.h < 0) {
            return true;
         }

         this.l.offset(0, this.h - this.l.maxY + 4 - 1, 0);
      }

      this.a(var1, var3, 0, 0, 0, 2, 3, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 0, 0, var3);
      this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 1, 0, var3);
      this.a(var1, Blocks.oak_fence.getDefaultState(), 1, 2, 0, var3);
      this.a(var1, Blocks.wool.getStateFromMeta(EnumDyeColor.WHITE.getDyeDamage()), 1, 3, 0, var3);
      boolean var4 = this.m == EnumFacing.EAST || this.m == EnumFacing.NORTH;
      this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.rotateY()), var4 ? 2 : 0, 3, 0, var3);
      this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m), 1, 3, 1, var3);
      this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.rotateYCCW()), var4 ? 0 : 2, 3, 0, var3);
      this.a(var1, Blocks.torch.getDefaultState().withProperty(BlockTorch.FACING, this.m.getOpposite()), 1, 3, -1, var3);
      return true;
   }

   public StructureVillagePieces$Torch() {
   }

   public static StructureBoundingBox func_175856_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 3, 4, 2, var6);
      return StructureComponent.findIntersecting(var1, var7) != null ? null : var7;
   }
}
