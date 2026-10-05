package io.netty.util.internal.logging;

import io.netty.handler.ssl.OpenSsl;
import io.netty.util.internal.StringUtil;
import java.io.Serializable;

public abstract class AbstractInternalLogger implements InternalLogger, Serializable {
   public OpenSsl __junk5017345051270424707;
   public static long serialVersionUID;
   public String name;

   public AbstractInternalLogger(String var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         this.name = var1;
      }
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + '(' + this.name() + ')';
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Object var3, Object var4) {
      switch (AbstractInternalLogger$1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[var1.ordinal()]) {
         case 1:
            this.trace(var2, var3, var4);
            break;
         case 2:
            this.debug(var2, var3, var4);
            break;
         case 3:
            this.info(var2, var3, var4);
            break;
         case 4:
            this.warn(var2, var3, var4);
            break;
         case 5:
            this.error(var2, var3, var4);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public String name() {
      return this.name;
   }

   @Override
   public boolean isEnabled(InternalLogLevel var1) {
      switch (AbstractInternalLogger$1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[var1.ordinal()]) {
         case 1:
            return this.isTraceEnabled();
         case 2:
            return this.isDebugEnabled();
         case 3:
            return this.isInfoEnabled();
         case 4:
            return this.isWarnEnabled();
         case 5:
            return this.isErrorEnabled();
         default:
            throw new Error();
      }
   }

   public Object readResolve() {
      return InternalLoggerFactory.getInstance(this.name());
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Object var3) {
      switch (AbstractInternalLogger$1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[var1.ordinal()]) {
         case 1:
            this.trace(var2, var3);
            break;
         case 2:
            this.debug(var2, var3);
            break;
         case 3:
            this.info(var2, var3);
            break;
         case 4:
            this.warn(var2, var3);
            break;
         case 5:
            this.error(var2, var3);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Throwable var3) {
      switch (AbstractInternalLogger$1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[var1.ordinal()]) {
         case 1:
            this.trace(var2, var3);
            break;
         case 2:
            this.debug(var2, var3);
            break;
         case 3:
            this.info(var2, var3);
            break;
         case 4:
            this.warn(var2, var3);
            break;
         case 5:
            this.error(var2, var3);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Object... var3) {
      switch (AbstractInternalLogger$1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[var1.ordinal()]) {
         case 1:
            this.trace(var2, var3);
            break;
         case 2:
            this.debug(var2, var3);
            break;
         case 3:
            this.info(var2, var3);
            break;
         case 4:
            this.warn(var2, var3);
            break;
         case 5:
            this.error(var2, var3);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public void log(InternalLogLevel var1, String var2) {
      switch (AbstractInternalLogger$1.$SwitchMap$io$netty$util$internal$logging$InternalLogLevel[var1.ordinal()]) {
         case 1:
            this.trace(var2);
            break;
         case 2:
            this.debug(var2);
            break;
         case 3:
            this.info(var2);
            break;
         case 4:
            this.warn(var2);
            break;
         case 5:
            this.error(var2);
            break;
         default:
            throw new Error();
      }
   }
}
