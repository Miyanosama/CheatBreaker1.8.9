package org.slf4j.helpers;

import com.cheatbreaker.client.ui.overlay.element.FlatButtonElement;
import io.netty.handler.codec.socks.SocksInitResponseDecoder$1;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import org.slf4j.Logger;
import org.slf4j.Marker;

public abstract class MarkerIgnoringBase extends NamedLoggerBase implements Logger {
   public ServersideAttributeMap field_0003;
   public SocksInitResponseDecoder$1 field_0001;
   public static long field_0002;
   public FlatButtonElement field_0000;

   @Override
   public String toString() {
      return this.getClass().getName() + "(" + this.getName() + ")";
   }

   @Override
   public void info(Marker var1, String var2) {
      this.info(var2);
   }

   @Override
   public void error(Marker var1, String var2, Object var3, Object var4) {
      this.error(var2, var3, var4);
   }

   @Override
   public void error(Marker var1, String var2, Object var3) {
      this.error(var2, var3);
   }

   @Override
   public void debug(Marker var1, String var2, Object... var3) {
      this.debug(var2, var3);
   }

   @Override
   public void info(Marker var1, String var2, Object... var3) {
      this.info(var2, var3);
   }

   @Override
   public boolean method_02600(Marker var1) {
      return this.method_02649();
   }

   @Override
   public void debug(Marker var1, String var2, Object var3, Object var4) {
      this.debug(var2, var3, var4);
   }

   @Override
   public void trace(Marker var1, String var2) {
      this.trace(var2);
   }

   @Override
   public void warn(Marker var1, String var2, Object... var3) {
      this.warn(var2, var3);
   }

   @Override
   public void error(Marker var1, String var2) {
      this.error(var2);
   }

   @Override
   public void warn(Marker var1, String var2, Object var3) {
      this.warn(var2, var3);
   }

   @Override
   public void info(Marker var1, String var2, Throwable var3) {
      this.info(var2, var3);
   }

   @Override
   public void trace(Marker var1, String var2, Object var3, Object var4) {
      this.trace(var2, var3, var4);
   }

   @Override
   public void trace(Marker var1, String var2, Throwable var3) {
      this.trace(var2, var3);
   }

   @Override
   public boolean method_02614(Marker var1) {
      return this.isTraceEnabled();
   }

   @Override
   public void debug(Marker var1, String var2, Object var3) {
      this.debug(var2, var3);
   }

   @Override
   public boolean method_02630(Marker var1) {
      return this.method_02608();
   }

   @Override
   public boolean method_02655(Marker var1) {
      return this.method_02637();
   }

   @Override
   public void trace(Marker var1, String var2, Object... var3) {
      this.trace(var2, var3);
   }

   @Override
   public void info(Marker var1, String var2, Object var3, Object var4) {
      this.info(var2, var3, var4);
   }

   @Override
   public void info(Marker var1, String var2, Object var3) {
      this.info(var2, var3);
   }

   @Override
   public void warn(Marker var1, String var2, Object var3, Object var4) {
      this.warn(var2, var3, var4);
   }

   @Override
   public void error(Marker var1, String var2, Throwable var3) {
      this.error(var2, var3);
   }

   @Override
   public boolean method_02643(Marker var1) {
      return this.method_02622();
   }

   @Override
   public void warn(Marker var1, String var2) {
      this.warn(var2);
   }

   @Override
   public void warn(Marker var1, String var2, Throwable var3) {
      this.warn(var2, var3);
   }

   @Override
   public void debug(Marker var1, String var2) {
      this.method_02650(var2);
   }

   @Override
   public void error(Marker var1, String var2, Object... var3) {
      this.error(var2, var3);
   }

   @Override
   public void trace(Marker var1, String var2, Object var3) {
      this.trace(var2, var3);
   }

   @Override
   public void debug(Marker var1, String var2, Throwable var3) {
      this.debug(var2, var3);
   }
}
