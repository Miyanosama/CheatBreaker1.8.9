package net.optifine.util;

import com.cheatbreaker.client.util.title.Title;
import java.util.List;
import net.minecraft.command.server.CommandTestForBlock;
import net.optifine.shaders.HFNoiseTexture;

public class IteratorCache$IteratorReadOnly implements IteratorCache$IteratorReusable<Object> {
   public List<Object> list;
   public CommandTestForBlock field_0005;
   public Title field_0002;
   public boolean hasNext;
   public HFNoiseTexture field_0000;
   public int index;

   @Override
   public void remove() {
      throw new UnsupportedOperationException("remove");
   }

   @Override
   public void setList(List<Object> var1) {
      if (this.hasNext) {
         throw new RuntimeException("Iterator still used, oldList: " + this.list + ", newList: " + var1);
      } else {
         this.list = var1;
         this.index = 0;
         this.hasNext = var1 != null && this.index < var1.size();
      }
   }

   @Override
   public boolean hasNext() {
      if (!this.hasNext) {
         IteratorCache.access$000(this);
         return false;
      } else {
         return this.hasNext;
      }
   }

   @Override
   public Object next() {
      if (!this.hasNext) {
         return null;
      } else {
         Object var1 = this.list.get(this.index);
         this.index++;
         this.hasNext = this.index < this.list.size();
         return var1;
      }
   }
}
