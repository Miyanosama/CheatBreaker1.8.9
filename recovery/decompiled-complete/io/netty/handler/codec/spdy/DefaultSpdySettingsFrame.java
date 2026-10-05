package io.netty.handler.codec.spdy;

import io.netty.channel.AbstractServerChannel$1;
import io.netty.util.internal.StringUtil;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import net.minecraft.util.RegistryDefaulted;
import recovered.unidentified.UnidentifiedClass1099;

public class DefaultSpdySettingsFrame implements SpdySettingsFrame {
   public boolean clear;
   public UnidentifiedClass1099 __junk9221772789891372166;
   public RegistryDefaulted __junk2304627584657372548;
   public AbstractServerChannel$1 __junk2829536373439457618;
   public Map<Integer, DefaultSpdySettingsFrame$Setting> settingsMap = new TreeMap<>();

   @Override
   public int getValue(int var1) {
      Integer var2 = var1;
      return this.settingsMap.containsKey(var2) ? this.settingsMap.get(var2).getValue() : -1;
   }

   @Override
   public SpdySettingsFrame setClearPreviouslyPersistedSettings(boolean var1) {
      this.clear = var1;
      return this;
   }

   public Set<Entry<Integer, DefaultSpdySettingsFrame$Setting>> getSettings() {
      return this.settingsMap.entrySet();
   }

   @Override
   public SpdySettingsFrame setValue(int var1, int var2, boolean var3, boolean var4) {
      if (var1 >= 0 && var1 <= 16777215) {
         Integer var5 = var1;
         if (this.settingsMap.containsKey(var5)) {
            DefaultSpdySettingsFrame$Setting var6 = this.settingsMap.get(var5);
            var6.setValue(var2);
            var6.setPersist(var3);
            var6.setPersisted(var4);
         } else {
            this.settingsMap.put(var5, new DefaultSpdySettingsFrame$Setting(var2, var3, var4));
         }

         return this;
      } else {
         throw new IllegalArgumentException("Setting ID is not valid: " + var1);
      }
   }

   @Override
   public Set<Integer> ids() {
      return this.settingsMap.keySet();
   }

   @Override
   public boolean isPersistValue(int var1) {
      Integer var2 = var1;
      return this.settingsMap.containsKey(var2) ? this.settingsMap.get(var2).isPersist() : false;
   }

   @Override
   public boolean isPersisted(int var1) {
      Integer var2 = var1;
      return this.settingsMap.containsKey(var2) ? this.settingsMap.get(var2).isPersisted() : false;
   }

   @Override
   public boolean clearPreviouslyPersistedSettings() {
      return this.clear;
   }

   @Override
   public SpdySettingsFrame setPersistValue(int var1, boolean var2) {
      Integer var3 = var1;
      if (this.settingsMap.containsKey(var3)) {
         this.settingsMap.get(var3).setPersist(var2);
      }

      return this;
   }

   @Override
   public SpdySettingsFrame removeValue(int var1) {
      Integer var2 = var1;
      if (this.settingsMap.containsKey(var2)) {
         this.settingsMap.remove(var2);
      }

      return this;
   }

   @Override
   public SpdySettingsFrame setValue(int var1, int var2) {
      return this.setValue(var1, var2, false, false);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append(StringUtil.NEWLINE);
      this.appendSettings(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }

   @Override
   public boolean isSet(int var1) {
      Integer var2 = var1;
      return this.settingsMap.containsKey(var2);
   }

   public void appendSettings(StringBuilder var1) {
      for (Entry var3 : this.getSettings()) {
         DefaultSpdySettingsFrame$Setting var4 = (DefaultSpdySettingsFrame$Setting)var3.getValue();
         var1.append("--> ");
         var1.append(var3.getKey());
         var1.append(':');
         var1.append(var4.getValue());
         var1.append(" (persist value: ");
         var1.append(var4.isPersist());
         var1.append("; persisted: ");
         var1.append(var4.isPersisted());
         var1.append(')');
         var1.append(StringUtil.NEWLINE);
      }
   }

   @Override
   public SpdySettingsFrame setPersisted(int var1, boolean var2) {
      Integer var3 = var1;
      if (this.settingsMap.containsKey(var3)) {
         this.settingsMap.get(var3).setPersisted(var2);
      }

      return this;
   }
}
