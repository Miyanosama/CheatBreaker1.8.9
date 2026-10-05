package org.apache.log4j;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;
import org.apache.log4j.helpers.LogLog;

public class NDC {
   public static final int recoveredField3001 = 5;
   public static Hashtable ht = new Hashtable();
   public static int pushCounter = 0;

   public static String peek() {
      Stack var0 = getCurrentStack();
      return var0 != null && !var0.isEmpty() ? ((NDC.DiagnosticContext)var0.peek()).message : "";
   }

   public static void inherit(Stack var0) {
      if (var0 != null) {
         ht.put(Thread.currentThread(), var0);
      }
   }

   public static String pop() {
      Stack var0 = getCurrentStack();
      return var0 != null && !var0.isEmpty() ? ((NDC.DiagnosticContext)var0.pop()).message : "";
   }

   public static void clear() {
      Stack var0 = getCurrentStack();
      if (var0 != null) {
         var0.setSize(0);
      }
   }

   public static Stack cloneStack() {
      Stack var0 = getCurrentStack();
      return var0 == null ? null : (Stack)var0.clone();
   }

   public static void remove() {
      if (ht != null) {
         ht.remove(Thread.currentThread());
         lazyRemove();
      }
   }

   public static String get() {
      Stack var0 = getCurrentStack();
      return var0 != null && !var0.isEmpty() ? ((NDC.DiagnosticContext)var0.peek()).fullMessage : null;
   }

   public static void push(String var0) {
      Stack var1 = getCurrentStack();
      if (var1 == null) {
         NDC.DiagnosticContext var2 = new NDC.DiagnosticContext(var0, null);
         var1 = new Stack();
         Thread var3 = Thread.currentThread();
         ht.put(var3, var1);
         var1.push(var2);
      } else if (var1.isEmpty()) {
         NDC.DiagnosticContext var5 = new NDC.DiagnosticContext(var0, null);
         var1.push(var5);
      } else {
         NDC.DiagnosticContext var6 = (NDC.DiagnosticContext)var1.peek();
         var1.push(new NDC.DiagnosticContext(var0, var6));
      }
   }

   public static void setMaxDepth(int var0) {
      Stack var1 = getCurrentStack();
      if (var1 != null && var0 < var1.size()) {
         var1.setSize(var0);
      }
   }

   public static int getDepth() {
      Stack var0 = getCurrentStack();
      return var0 == null ? 0 : var0.size();
   }

   public static void lazyRemove() {
      if (ht != null) {
         Vector var0;
         synchronized (ht) {
            if (++pushCounter <= 5) {
               return;
            }

            pushCounter = 0;
            int var2 = 0;
            var0 = new Vector();
            Enumeration var3 = ht.keys();

            while (var3.hasMoreElements() && var2 <= 4) {
               Thread var4 = (Thread)var3.nextElement();
               if (var4.isAlive()) {
                  var2++;
               } else {
                  var2 = 0;
                  var0.addElement(var4);
               }
            }
         }

         int var7 = var0.size();

         for (int var8 = 0; var8 < var7; var8++) {
            Thread var9 = (Thread)var0.elementAt(var8);
            LogLog.debug("Lazy NDC removal for thread [" + var9.getName() + "] (" + ht.size() + ").");
            ht.remove(var9);
         }
      }
   }

   public static Stack getCurrentStack() {
      return ht != null ? (Stack)ht.get(Thread.currentThread()) : null;
   }

   public static class DiagnosticContext {
      public String message;
      public String fullMessage;

      public DiagnosticContext(String var1, NDC.DiagnosticContext var2) {
         this.message = var1;
         if (var2 != null) {
            this.fullMessage = var2.fullMessage + ' ' + var1;
         } else {
            this.fullMessage = var1;
         }
      }
   }
}
