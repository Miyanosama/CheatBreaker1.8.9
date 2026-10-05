package net.minecraft.client.renderer.block.model;

import io.netty.handler.stream.ChunkedWriteHandler;
import net.minecraft.item.ItemLeaves;
import net.minecraft.util.EnumFacing;

public enum ItemModelGenerator$SpanFacing {
   UP(EnumFacing.UP, 0, -1),
   DOWN(EnumFacing.DOWN, 0, 1),
   RIGHT(EnumFacing.WEST, 1, 0),
   LEFT(EnumFacing.EAST, -1, 0);
   public ItemLeaves field_0007;
   public int field_178374_g;
   public ChunkedWriteHandler field_0006;
   public EnumFacing facing;
   public int field_178373_f;

   public EnumFacing getFacing() {
      return this.facing;
   }

   public boolean func_178369_d() {
      return this == DOWN || this == UP;
   }

   public int func_178371_c() {
      return this.field_178374_g;
   }

   public int func_178372_b() {
      return this.field_178373_f;
   }

   public ItemModelGenerator$SpanFacing(EnumFacing var3, int var4, int var5) {
      this.facing = var3;
      this.field_178373_f = var4;
      this.field_178374_g = var5;
   }
}
