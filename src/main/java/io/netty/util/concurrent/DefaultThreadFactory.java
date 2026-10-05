package io.netty.util.concurrent;

import io.netty.util.internal.StringUtil;
import java.util.Locale;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.network.status.client.C00PacketServerQuery;
import org.json.JSONStringer;
import com.cheatbreaker.client.emote.type.ShrugEmote;

public class DefaultThreadFactory implements ThreadFactory {
   public static AtomicInteger poolId = new AtomicInteger();
   public int priority;
   public String prefix;
   public boolean daemon;
   public AtomicInteger nextId = new AtomicInteger();

   public DefaultThreadFactory(Class<?> var1) {
      this(var1, false, 5);
   }

   public DefaultThreadFactory(String var1, boolean var2, int var3) {
      if (var1 == null) {
         throw new NullPointerException("poolName");
      } else if (var3 >= 1 && var3 <= 10) {
         this.prefix = var1 + '-' + poolId.incrementAndGet() + '-';
         this.daemon = var2;
         this.priority = var3;
      } else {
         throw new IllegalArgumentException("priority: " + var3 + " (expected: Thread.MIN_PRIORITY <= priority <= Thread.MAX_PRIORITY)");
      }
   }

   public Thread newThread(Runnable var1, String var2) {
      return new FastThreadLocalThread(var1, var2);
   }

   public static String toPoolName(Class<?> var0) {
      if (var0 == null) {
         throw new NullPointerException("poolType");
      } else {
         String var1 = StringUtil.simpleClassName(var0);
         switch (var1.length()) {
            case 0:
               return "unknown";
            case 1:
               return var1.toLowerCase(Locale.US);
            default:
               return Character.isUpperCase(var1.charAt(0)) && Character.isLowerCase(var1.charAt(1))
                  ? Character.toLowerCase(var1.charAt(0)) + var1.substring(1)
                  : var1;
         }
      }
   }

   public DefaultThreadFactory(Class<?> var1, boolean var2) {
      this(var1, var2, 5);
   }

   public DefaultThreadFactory(Class<?> var1, boolean var2, int var3) {
      this(toPoolName(var1), var2, var3);
   }

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = this.newThread(new DefaultThreadFactory.DefaultRunnableDecorator(var1), this.prefix + this.nextId.incrementAndGet());

      try {
         if (var2.isDaemon()) {
            if (!this.daemon) {
               var2.setDaemon(false);
            }
         } else if (this.daemon) {
            var2.setDaemon(true);
         }

         if (var2.getPriority() != this.priority) {
            var2.setPriority(this.priority);
         }
      } catch (Exception var4) {
      }

      return var2;
   }

   public DefaultThreadFactory(String var1, boolean var2) {
      this(var1, var2, 5);
   }

   public DefaultThreadFactory(String var1) {
      this(var1, false, 5);
   }

   public DefaultThreadFactory(Class<?> var1, int var2) {
      this(var1, false, var2);
   }

   public DefaultThreadFactory(String var1, int var2) {
      this(var1, false, var2);
   }

   public static final class DefaultRunnableDecorator implements Runnable {
      public Runnable r;

      @Override
      public void run() {
         try {
            this.r.run();
         } finally {
            FastThreadLocal.removeAll();
         }
      }

      public DefaultRunnableDecorator(Runnable var1) {
         this.r = var1;
      }
   }
}
