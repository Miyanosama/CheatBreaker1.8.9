package net.minecraft.entity;

import java.util.concurrent.Callable;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.optifine.shaders.SMCLog;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;

public class Entity$1 implements Callable<String> {
   public EntityAIFollowParent field_0004;
   public SMCLog field_0001;
   public OnlyOnceErrorHandler field_0003;
   public BlockStoneSlab field_0000;

   public String call() {
      return EntityList.getEntityString(this.field_85155_a) + " (" + this.field_85155_a.getClass().getCanonicalName() + ")";
   }

   public Entity$1(Entity var1) {
      this.field_85155_a = var1;
      super();
   }
}
