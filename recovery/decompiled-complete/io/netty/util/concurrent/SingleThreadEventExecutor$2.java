package io.netty.util.concurrent;

import com.cheatbreaker.client.ui.element.module.ModulePreviewContainer;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ReduceKeysTask;
import net.minecraft.util.DamageSource;
import net.minecraft.world.biome.BiomeColorHelper$3;
import org.newsclub.net.unix.NarSystem;

public class SingleThreadEventExecutor$2 implements Runnable {
   public BiomeColorHelper$3 __junk4128868690732647227;
   public NarSystem __junk5884554672494360826;
   public DamageSource __junk6002047122605433595;
   public ConcurrentHashMapV8$ReduceKeysTask __junk6569975842745330084;
   public ModulePreviewContainer __junk4405120642503981782;

   public SingleThreadEventExecutor$2(SingleThreadEventExecutor var1) {
      this.this$0 = var1;
      super();
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void run() {
      boolean var1 = false;
      this.this$0.updateLastExecutionTime();
      boolean var112 = false /* VF: Semaphore variable */;

      label1146: {
         try {
            var112 = true;
            this.this$0.run();
            var1 = true;
            var112 = false;
            break label1146;
         } catch (Throwable var119) {
            SingleThreadEventExecutor.access$000().warn("Unexpected exception from an event executor: ", var119);
            var112 = false;
         } finally {
            if (var112) {
               int var10;
               do {
                  var10 = SingleThreadEventExecutor.access$100().get(this.this$0);
               } while (var10 < 3 && !SingleThreadEventExecutor.access$100().compareAndSet(this.this$0, var10, 3));

               if (var1 && SingleThreadEventExecutor.access$200(this.this$0) == (145795432L & 1157890689L)) {
                  SingleThreadEventExecutor.access$000()
                     .error(
                        "Buggy "
                           + EventExecutor.class.getSimpleName()
                           + " implementation; "
                           + SingleThreadEventExecutor.class.getSimpleName()
                           + ".confirmShutdown() must be called "
                           + "before run() implementation terminates."
                     );
               }

               try {
                  while (!this.this$0.confirmShutdown()) {
                  }
               } finally {
                  try {
                     this.this$0.cleanup();
                  } finally {
                     SingleThreadEventExecutor.access$100().set(this.this$0, 5);
                     SingleThreadEventExecutor.access$300(this.this$0).release();
                     if (!SingleThreadEventExecutor.access$400(this.this$0).isEmpty()) {
                        SingleThreadEventExecutor.access$000()
                           .warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.access$400(this.this$0).size() + ')');
                     }

                     SingleThreadEventExecutor.access$500(this.this$0).setSuccess(null);
                  }
               }
            }
         }

         int var2;
         do {
            var2 = SingleThreadEventExecutor.access$100().get(this.this$0);
         } while (var2 < 3 && !SingleThreadEventExecutor.access$100().compareAndSet(this.this$0, var2, 3));

         if (var1 && SingleThreadEventExecutor.access$200(this.this$0) == (-4907703740677943036L & 140805664L)) {
            SingleThreadEventExecutor.access$000()
               .error(
                  "Buggy "
                     + EventExecutor.class.getSimpleName()
                     + " implementation; "
                     + SingleThreadEventExecutor.class.getSimpleName()
                     + ".confirmShutdown() must be called "
                     + "before run() implementation terminates."
               );
         }

         try {
            while (!this.this$0.confirmShutdown()) {
            }

            return;
         } finally {
            try {
               this.this$0.cleanup();
            } finally {
               SingleThreadEventExecutor.access$100().set(this.this$0, 5);
               SingleThreadEventExecutor.access$300(this.this$0).release();
               if (!SingleThreadEventExecutor.access$400(this.this$0).isEmpty()) {
                  SingleThreadEventExecutor.access$000()
                     .warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.access$400(this.this$0).size() + ')');
               }

               SingleThreadEventExecutor.access$500(this.this$0).setSuccess(null);
            }
         }
      }

      int var121;
      do {
         var121 = SingleThreadEventExecutor.access$100().get(this.this$0);
      } while (var121 < 3 && !SingleThreadEventExecutor.access$100().compareAndSet(this.this$0, var121, 3));

      if (var1 && SingleThreadEventExecutor.access$200(this.this$0) == (294158593L & -4397706344258315642L)) {
         SingleThreadEventExecutor.access$000()
            .error(
               "Buggy "
                  + EventExecutor.class.getSimpleName()
                  + " implementation; "
                  + SingleThreadEventExecutor.class.getSimpleName()
                  + ".confirmShutdown() must be called "
                  + "before run() implementation terminates."
            );
      }

      try {
         while (!this.this$0.confirmShutdown()) {
         }
      } finally {
         try {
            this.this$0.cleanup();
         } finally {
            SingleThreadEventExecutor.access$100().set(this.this$0, 5);
            SingleThreadEventExecutor.access$300(this.this$0).release();
            if (!SingleThreadEventExecutor.access$400(this.this$0).isEmpty()) {
               SingleThreadEventExecutor.access$000()
                  .warn("An event executor terminated with non-empty task queue (" + SingleThreadEventExecutor.access$400(this.this$0).size() + ')');
            }

            SingleThreadEventExecutor.access$500(this.this$0).setSuccess(null);
         }
      }
   }
}
