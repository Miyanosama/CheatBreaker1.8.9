package io.netty.util.internal.logging;

import io.netty.buffer.PoolThreadCache;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import net.minecraft.item.EnumAction;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;

public class Slf4JLoggerFactory extends InternalLoggerFactory {
   public static final boolean $assertionsDisabled = !Slf4JLoggerFactory.class.desiredAssertionStatus();

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
         final StringBuffer var2 = new StringBuffer();
         PrintStream var3 = System.err;

         try {
            System.setErr(new PrintStream(new OutputStream() {

               @Override
               public void write(int var1) {
                  var2.append((char)var1);
               }
            }, true, "US-ASCII"));
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
