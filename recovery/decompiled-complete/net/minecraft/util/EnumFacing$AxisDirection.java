package net.minecraft.util;

import io.netty.channel.AbstractChannelHandlerContext$8;
import net.minecraft.client.renderer.block.model.ModelBlock$1;
import net.minecraft.client.resources.SkinManager$1;
import net.minecraft.server.MinecraftServer$1;
import net.optifine.util.TextureUtils;

public enum EnumFacing$AxisDirection {
   NEGATIVE(-1, "Towards negative"),
   POSITIVE(1, "Towards positive");

   public AbstractChannelHandlerContext$8 field_0004;
   public TextureUtils field_0003;
   public int offset;
   public String description;
   public SkinManager$1 field_0005;
   public MinecraftServer$1 field_0002;
   public ModelBlock$1 field_0009;

   public int getOffset() {
      return this.offset;
   }

   @Override
   public String toString() {
      return this.description;
   }

   public EnumFacing$AxisDirection(int var3, String var4) {
      this.offset = var3;
      this.description = var4;
   }
}
