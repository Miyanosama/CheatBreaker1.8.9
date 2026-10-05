package io.netty.util;

import java.util.Map;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEndermite;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.optifine.reflect.ReflectorField;
import recovered.unidentified.UnidentifiedClass1222;

public class Recycler$DefaultHandle implements Recycler$Handle {
   public Object value;
   public GlStateManager __junk2577061913562083712;
   public Recycler$Stack<?> stack;
   public int recycleId;
   public RenderEndermite __junk4483916204091922576;
   public MapGenVillage __junk5225789962203437698;
   public ReflectorField __junk6707064488746056723;
   public UnidentifiedClass1222 __junk7329766704892415112;
   public int lastRecycledId;

   public void recycle() {
      Thread var1 = Thread.currentThread();
      if (var1 == this.stack.thread) {
         this.stack.push(this);
      } else {
         Map var2 = (Map)Recycler.access$300().get();
         Recycler$WeakOrderQueue var3 = (Recycler$WeakOrderQueue)var2.get(this.stack);
         if (var3 == null) {
            var2.put(this.stack, var3 = new Recycler$WeakOrderQueue(this.stack, var1));
         }

         var3.add(this);
      }
   }

   public Recycler$DefaultHandle(Recycler$Stack<?> var1) {
      this.stack = var1;
   }
}
