package io.netty.util.concurrent;

import com.cheatbreaker.client.module.staff.XRayModule;
import com.cheatbreaker.client.ui.mainmenu.element.TextButtonElement;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.entity.monster.IMob$1;
import net.minecraft.server.network.NetHandlerLoginServer$1;
import net.minecraft.world.biome.BiomeGenHills;
import org.apache.log4j.Level;
import org.java_websocket.enums.CloseHandshakeType;

public class DefaultPromise$LateListenerNotifier implements Runnable {
   public NetHandlerLoginServer$1 __junk6858049108321327315;
   public TextButtonElement __junk2063543679422103065;
   public Level __junk7338326689617405083;
   public CloseHandshakeType __junk5680820104485476673;
   public BiomeGenHills __junk427371168417752106;
   public GenericFutureListener<?> l;
   public BlockRedstoneComparator __junk7479252546465739022;
   public IMob$1 __junk6348381018050380635;
   public XRayModule __junk8310461680805820820;

   @Override
   public void run() {
      DefaultPromise$LateListeners var1 = DefaultPromise.access$500(this.this$0);
      if (this.l != null) {
         if (var1 == null) {
            DefaultPromise.access$502(this.this$0, var1 = new DefaultPromise$LateListeners(this.this$0));
         }

         var1.add(this.l);
         this.l = null;
      }

      var1.run();
   }

   public DefaultPromise$LateListenerNotifier(GenericFutureListener<?> var1, GenericFutureListener var2) {
      this.this$0 = var1;
      super();
      this.l = var2;
   }
}
