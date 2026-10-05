package org.apache.log4j.spi;

import com.cheatbreaker.client.network.messages.Message;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Vector;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemShears;
import org.apache.log4j.Appender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class NOPLogger extends Logger {
   public Message field_0002;
   public EntityAnimal field_0001;
   public EntityWolf field_0003;
   public ItemEgg field_0000;
   public ItemShears field_0004;

   public boolean isTraceEnabled() {
      return false;
   }

   public void trace(Object var1) {
   }

   public void warn(Object var1, Throwable var2) {
   }

   public void info(Object var1) {
   }

   public void closeNestedAppenders() {
   }

   public void callAppenders(LoggingEvent var1) {
   }

   public void removeAppender(Appender var1) {
   }

   public void l7dlog(Priority var1, String var2, Throwable var3) {
   }

   public void warn(Object var1) {
   }

   public void setLevel(Level var1) {
   }

   public Enumeration getAllAppenders() {
      return new Vector().elements();
   }

   public void log(Priority var1, Object var2) {
   }

   public void setResourceBundle(ResourceBundle var1) {
   }

   public NOPLogger(NOPLoggerRepository var1, String var2) {
      super(var2);
      this.repository = var1;
      this.level = Level.OFF;
      this.parent = this;
   }

   public void error(Object var1, Throwable var2) {
   }

   public void log(String var1, Priority var2, Object var3, Throwable var4) {
   }

   public void debug(Object var1, Throwable var2) {
   }

   public boolean isEnabledFor(Priority var1) {
      return false;
   }

   public void addAppender(Appender var1) {
   }

   public Appender getAppender(String var1) {
      return null;
   }

   public void l7dlog(Priority var1, String var2, Object[] var3, Throwable var4) {
   }

   public void removeAppender(String var1) {
   }

   public void log(Priority var1, Object var2, Throwable var3) {
   }

   public void removeAllAppenders() {
   }

   public void assertLog(boolean var1, String var2) {
   }

   public void fatal(Object var1) {
   }

   public Level getEffectiveLevel() {
      return Level.OFF;
   }

   public void error(Object var1) {
   }

   public void debug(Object var1) {
   }

   public void trace(Object var1, Throwable var2) {
   }

   public boolean isAttached(Appender var1) {
      return false;
   }

   public boolean isInfoEnabled() {
      return false;
   }

   public boolean isDebugEnabled() {
      return false;
   }

   public Priority getChainedPriority() {
      return this.getEffectiveLevel();
   }

   public void fatal(Object var1, Throwable var2) {
   }

   public void setPriority(Priority var1) {
   }

   public ResourceBundle getResourceBundle() {
      return null;
   }

   public void info(Object var1, Throwable var2) {
   }
}
