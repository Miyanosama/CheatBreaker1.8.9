package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;

public class Signal extends Error {
   public UniqueName uname;
   public static ConcurrentMap<String, Boolean> map = PlatformDependent.newConcurrentHashMap();
   public static long serialVersionUID;

   @Override
   public Throwable initCause(Throwable var1) {
      return this;
   }

   @Override
   public String toString() {
      return this.uname.name();
   }

   public void expect(Signal var1) {
      if (this != var1) {
         throw new IllegalStateException("unexpected signal: " + var1);
      }
   }

   @Override
   public Throwable fillInStackTrace() {
      return this;
   }

   public Signal(String var1) {
      super(var1);
      this.uname = new UniqueName(map, var1);
   }

   public static Signal valueOf(String var0) {
      return new Signal(var0);
   }
}
