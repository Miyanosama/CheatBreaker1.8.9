package net.minecraft.entity.ai;

import io.netty.handler.codec.http.HttpObjectDecoder$HeaderParser;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.entity.EntityLiving;

public class EntityJumpHelper {
   public BlockRailBase field_0002;
   public boolean a;
   public ModelSkeletonHead field_0001;
   public HttpObjectDecoder$HeaderParser field_0003;
   public EntityLiving entity;

   public EntityJumpHelper(EntityLiving var1) {
      this.entity = var1;
   }

   public void doJump() {
      this.entity.i(this.a);
      this.a = false;
   }

   public void setJumping() {
      this.a = true;
   }
}
