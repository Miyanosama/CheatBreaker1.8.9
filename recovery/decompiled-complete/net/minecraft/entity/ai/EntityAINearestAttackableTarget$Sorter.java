package net.minecraft.entity.ai;

import io.netty.handler.codec.http.HttpConstants;
import java.util.Comparator;
import net.minecraft.client.renderer.chunk.ChunkRenderDispatcher$1;
import net.minecraft.entity.Entity;
import net.optifine.util.FrameEvent;
import net.optifine.util.LinkedList$Node;

public class EntityAINearestAttackableTarget$Sorter implements Comparator<Entity> {
   public FrameEvent field_0002;
   public HttpConstants field_0004;
   public ChunkRenderDispatcher$1 field_0001;
   public Entity theEntity;
   public LinkedList$Node field_0000;

   public EntityAINearestAttackableTarget$Sorter(Entity var1) {
      this.theEntity = var1;
   }

   public int compare(Entity var1, Entity var2) {
      double var3 = this.theEntity.h(var1);
      double var5 = this.theEntity.h(var2);
      return var3 < var5 ? -1 : (var3 > var5 ? 1 : 0);
   }
}
