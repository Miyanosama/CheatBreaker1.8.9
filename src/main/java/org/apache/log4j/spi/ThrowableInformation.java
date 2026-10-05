package org.apache.log4j.spi;

import java.io.Serializable;
import org.apache.log4j.Category;
import org.apache.log4j.DefaultThrowableRenderer;

public class ThrowableInformation implements Serializable {
   public static final long recoveredField3862 = -4748765566864322735L;
   public transient Throwable throwable;
   public String[] rep;
   public transient Category category;

   public ThrowableInformation(Throwable var1, Category var2) {
      this.throwable = var1;
      this.category = var2;
   }

   public ThrowableInformation(String[] var1) {
      if (var1 != null) {
         this.rep = (String[])var1.clone();
      }
   }

   public synchronized String[] getThrowableStrRep() {
      if (this.rep == null) {
         ThrowableRenderer var1 = null;
         if (this.category != null) {
            LoggerRepository var2 = this.category.getLoggerRepository();
            if (var2 instanceof ThrowableRendererSupport) {
               var1 = ((ThrowableRendererSupport)var2).getThrowableRenderer();
            }
         }

         if (var1 == null) {
            this.rep = DefaultThrowableRenderer.render(this.throwable);
         } else {
            this.rep = var1.doRender(this.throwable);
         }
      }

      return (String[])this.rep.clone();
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public ThrowableInformation(Throwable var1) {
      this.throwable = var1;
   }
}
