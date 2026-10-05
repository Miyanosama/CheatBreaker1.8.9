package io.netty.util.internal.logging;

import io.netty.buffer.PoolThreadCache$MemoryRegionCache;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import net.minecraft.entity.monster.IMob$2;
import net.minecraft.item.EnumAction;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;

public class Slf4JLoggerFactory extends InternalLoggerFactory {
   public EnumAction __junk5656289629395904284;
   public PoolThreadCache$MemoryRegionCache __junk4557034424721636451;
   public IMob$2 __junk6783261431510647255;

   @Override
   public InternalLogger newInstance(String var1) {
      return new Slf4JLogger(LoggerFactory.getLogger(var1));
   }

   public Slf4JLoggerFactory() {
   }

   public Slf4JLoggerFactory(boolean var1) {
      if (!$assertionsDisabled && !var1) {
         throw new AssertionError();
      } else {
         StringBuffer var2 = new StringBuffer();
         PrintStream var3 = System.err;

         try {
            System.setErr(new PrintStream(new Slf4JLoggerFactory$1(this, var2), true, "US-ASCII"));
         } catch (UnsupportedEncodingException var8) {
            throw new Error(var8);
         }

         try {
            if (LoggerFactory.getILoggerFactory() instanceof NOPLoggerFactory) {
               throw new NoClassDefFoundError(var2.toString());
            }

            var3.print(var2);
            var3.flush();
         } finally {
            System.setErr(var3);
         }
      }
   }
}
