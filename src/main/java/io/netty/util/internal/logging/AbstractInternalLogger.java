package io.netty.util.internal.logging;

import io.netty.handler.ssl.OpenSsl;
import io.netty.util.internal.StringUtil;
import java.io.Serializable;

public abstract class AbstractInternalLogger implements InternalLogger, Serializable {
   public static final long serialVersionUID = -6382972526573193470L;
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
      switch (var1) {
         case TRACE:
            this.trace(var2, var3, var4);
            break;
         case DEBUG:
            this.debug(var2, var3, var4);
            break;
         case INFO:
            this.info(var2, var3, var4);
            break;
         case WARN:
            this.warn(var2, var3, var4);
            break;
         case ERROR:
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
      switch (var1) {
         case TRACE:
            return this.isTraceEnabled();
         case DEBUG:
            return this.isDebugEnabled();
         case INFO:
            return this.isInfoEnabled();
         case WARN:
            return this.isWarnEnabled();
         case ERROR:
            return this.isErrorEnabled();
         default:
            throw new Error();
      }
   }

   public Object readResolve() throws java.io.ObjectStreamException {
      return InternalLoggerFactory.getInstance(this.name());
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Object var3) {
      switch (var1) {
         case TRACE:
            this.trace(var2, var3);
            break;
         case DEBUG:
            this.debug(var2, var3);
            break;
         case INFO:
            this.info(var2, var3);
            break;
         case WARN:
            this.warn(var2, var3);
            break;
         case ERROR:
            this.error(var2, var3);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Throwable var3) {
      switch (var1) {
         case TRACE:
            this.trace(var2, var3);
            break;
         case DEBUG:
            this.debug(var2, var3);
            break;
         case INFO:
            this.info(var2, var3);
            break;
         case WARN:
            this.warn(var2, var3);
            break;
         case ERROR:
            this.error(var2, var3);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public void log(InternalLogLevel var1, String var2, Object... var3) {
      switch (var1) {
         case TRACE:
            this.trace(var2, var3);
            break;
         case DEBUG:
            this.debug(var2, var3);
            break;
         case INFO:
            this.info(var2, var3);
            break;
         case WARN:
            this.warn(var2, var3);
            break;
         case ERROR:
            this.error(var2, var3);
            break;
         default:
            throw new Error();
      }
   }

   @Override
   public void log(InternalLogLevel var1, String var2) {
      switch (var1) {
         case TRACE:
            this.trace(var2);
            break;
         case DEBUG:
            this.debug(var2);
            break;
         case INFO:
            this.info(var2);
            break;
         case WARN:
            this.warn(var2);
            break;
         case ERROR:
            this.error(var2);
            break;
         default:
            throw new Error();
      }
   }
}
