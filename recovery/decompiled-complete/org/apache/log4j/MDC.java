package org.apache.log4j;

import io.netty.handler.ssl.SslHandler$8;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import net.minecraft.block.BlockButton$1;
import net.minecraft.client.Minecraft$5;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.world.gen.structure.StructureVillagePieces$Hall;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.ThreadLocalMap;

public class MDC {
   public Minecraft$5 field_0005;
   public Object tlm;
   public boolean java1 = Loader.isJava1();
   public static MDC mdc = new MDC();
   public Method removeMethod;
   public static int field_0002;
   public BlockButton$1 field_0009;
   public ModelLargeChest field_0006;
   public SslHandler$8 field_0003;
   public static Class class$java$lang$ThreadLocal;
   public StructureVillagePieces$Hall field_0000;

   public static void clear() {
      if (mdc != null) {
         mdc.clear0();
      }
   }

   public Hashtable getContext0() {
      return !this.java1 && this.tlm != null ? (Hashtable)((ThreadLocalMap)this.tlm).get() : null;
   }

   public void put0(String var1, Object var2) {
      if (!this.java1 && this.tlm != null) {
         Hashtable var3 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         if (var3 == null) {
            var3 = new Hashtable(7);
            ((ThreadLocalMap)this.tlm).set(var3);
         }

         var3.put(var1, var2);
      }
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public static void put(String var0, Object var1) {
      if (mdc != null) {
         mdc.put0(var0, var1);
      }
   }

   public MDC() {
      if (!this.java1) {
         this.tlm = new ThreadLocalMap();
      }

      try {
         this.removeMethod = (class$java$lang$ThreadLocal == null
               ? (class$java$lang$ThreadLocal = class$("java.lang.ThreadLocal"))
               : class$java$lang$ThreadLocal)
            .getMethod("remove", null);
      } catch (NoSuchMethodException var2) {
      }
   }

   public Object get0(String var1) {
      if (!this.java1 && this.tlm != null) {
         Hashtable var2 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         return var2 != null && var1 != null ? var2.get(var1) : null;
      } else {
         return null;
      }
   }

   public static Object get(String var0) {
      return mdc != null ? mdc.get0(var0) : null;
   }

   public static void remove(String var0) {
      if (mdc != null) {
         mdc.remove0(var0);
      }
   }

   public void clear0() {
      if (!this.java1 && this.tlm != null) {
         Hashtable var1 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         if (var1 != null) {
            var1.clear();
         }

         if (this.removeMethod != null) {
            try {
               this.removeMethod.invoke(this.tlm, null);
            } catch (IllegalAccessException var3) {
            } catch (InvocationTargetException var4) {
            }
         }
      }
   }

   public static Hashtable getContext() {
      return mdc != null ? mdc.getContext0() : null;
   }

   public void remove0(String var1) {
      if (!this.java1 && this.tlm != null) {
         Hashtable var2 = (Hashtable)((ThreadLocalMap)this.tlm).get();
         if (var2 != null) {
            var2.remove(var1);
            if (var2.isEmpty()) {
               this.clear0();
            }
         }
      }
   }
}
