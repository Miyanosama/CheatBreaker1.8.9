package net.minecraft.client.renderer.block.model;

import io.netty.channel.ChannelFutureListener$2;
import io.netty.handler.codec.compression.Snappy;
import net.minecraft.block.BlockLeaves;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerModel;

public class BlockFaceUV {
   public float[] uvs;
   public BlockLeaves field_0005;
   public CategoryExplorerModel field_0002;
   public int rotation;
   public Snappy field_0000;
   public ChannelFutureListener$2 field_0001;

   public void setUvs(float[] var1) {
      if (this.uvs == null) {
         this.uvs = var1;
      }
   }

   public BlockFaceUV(float[] var1, int var2) {
      this.uvs = var1;
      this.rotation = var2;
   }

   public int func_178345_c(int var1) {
      return (var1 + (4 - this.rotation / 90)) % 4;
   }

   public float func_178346_b(int var1) {
      if (this.uvs == null) {
         throw new NullPointerException("uvs");
      } else {
         int var2 = this.func_178347_d(var1);
         return var2 != 0 && var2 != 3 ? this.uvs[3] : this.uvs[1];
      }
   }

   public float func_178348_a(int var1) {
      if (this.uvs == null) {
         throw new NullPointerException("uvs");
      } else {
         int var2 = this.func_178347_d(var1);
         return var2 != 0 && var2 != 1 ? this.uvs[2] : this.uvs[0];
      }
   }

   public int func_178347_d(int var1) {
      return (var1 + this.rotation / 90) % 4;
   }
}
