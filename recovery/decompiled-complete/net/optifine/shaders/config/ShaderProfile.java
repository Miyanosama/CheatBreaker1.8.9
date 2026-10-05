package net.optifine.shaders.config;

import com.cheatbreaker.client.module.type.armourstatus.ArmourStatusDamageComparable;
import io.netty.handler.codec.socks.SocksAuthRequestDecoder$State;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.WorldServerMulti;
import org.apache.log4j.EnhancedThrowableRenderer;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$31;

public class ShaderProfile {
   public SocksAuthRequestDecoder$State field_0003;
   public LogBrokerMonitor$31 field_0006;
   public EnhancedThrowableRenderer field_0002;
   public String name = null;
   public ArmourStatusDamageComparable field_0000;
   public WorldServerMulti field_0001;
   public Set<String> disabledPrograms;
   public Map<String, String> mapOptionValues = new LinkedHashMap<>();

   public void addDisabledPrograms(Collection<String> var1) {
      this.disabledPrograms.addAll(var1);
   }

   public String[] getOptions() {
      Set var1 = this.mapOptionValues.keySet();
      return var1.toArray(new String[var1.size()]);
   }

   public String getName() {
      return this.name;
   }

   public void addOptionValue(String var1, String var2) {
      this.mapOptionValues.put(var1, var2);
   }

   public void addDisabledProgram(String var1) {
      this.disabledPrograms.add(var1);
   }

   public void addOptionValues(ShaderProfile var1) {
      if (var1 != null) {
         this.mapOptionValues.putAll(var1.mapOptionValues);
      }
   }

   public ShaderProfile(String var1) {
      this.disabledPrograms = new LinkedHashSet<>();
      this.name = var1;
   }

   public Collection<String> getDisabledPrograms() {
      return new LinkedHashSet<>(this.disabledPrograms);
   }

   public void removeDisabledProgram(String var1) {
      this.disabledPrograms.remove(var1);
   }

   public void applyOptionValues(ShaderOption[] var1) {
      for (int var2 = 0; var2 < var1.length; var2++) {
         ShaderOption var3 = var1[var2];
         String var4 = var3.getName();
         String var5 = this.mapOptionValues.get(var4);
         if (var5 != null) {
            var3.setValue(var5);
         }
      }
   }

   public String getValue(String var1) {
      return this.mapOptionValues.get(var1);
   }

   public boolean isProgramDisabled(String var1) {
      return this.disabledPrograms.contains(var1);
   }
}
