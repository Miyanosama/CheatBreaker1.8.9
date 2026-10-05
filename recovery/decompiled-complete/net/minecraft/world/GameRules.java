package net.minecraft.world;

import io.netty.channel.udt.UdtChannelOption;
import java.util.Set;
import java.util.TreeMap;
import net.minecraft.client.particle.EntityFootStepFX;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import net.optifine.CustomGuiProperties;
import recovered.unidentified.UnidentifiedClass1899;
import recovered.unidentified.UnidentifiedClass4506;

public class GameRules {
   public CustomGuiProperties field_0003;
   public TreeMap<String, GameRules$Value> theGameRules = new TreeMap<>();
   public S03PacketEnableCompression field_0002;
   public UdtChannelOption field_0004;
   public EntityFootStepFX field_0000;
   public UnidentifiedClass4506 field_0001;
   public UnidentifiedClass1899 field_0006;

   public String[] getRules() {
      Set var1 = this.theGameRules.keySet();
      return var1.toArray(new String[var1.size()]);
   }

   public boolean areSameType(String var1, GameRules$ValueType var2) {
      GameRules$Value var3 = this.theGameRules.get(var1);
      return var3 != null && (var3.getType() == var2 || var2 == GameRules$ValueType.ANY_VALUE);
   }

   public NBTTagCompound writeToNBT() {
      NBTTagCompound var1 = new NBTTagCompound();

      for (String var3 : this.theGameRules.keySet()) {
         GameRules$Value var4 = this.theGameRules.get(var3);
         var1.setString(var3, var4.getString());
      }

      return var1;
   }

   public void setOrCreateGameRule(String var1, String var2) {
      GameRules$Value var3 = this.theGameRules.get(var1);
      if (var3 != null) {
         var3.setValue(var2);
      } else {
         this.addGameRule(var1, var2, GameRules$ValueType.ANY_VALUE);
      }
   }

   public boolean hasRule(String var1) {
      return this.theGameRules.containsKey(var1);
   }

   public void addGameRule(String var1, String var2, GameRules$ValueType var3) {
      this.theGameRules.put(var1, new GameRules$Value(var2, var3));
   }

   public String getString(String var1) {
      GameRules$Value var2 = this.theGameRules.get(var1);
      return var2 != null ? var2.getString() : "";
   }

   public void readFromNBT(NBTTagCompound var1) {
      for (String var3 : var1.getKeySet()) {
         String var4 = var1.getString(var3);
         this.setOrCreateGameRule(var3, var4);
      }
   }

   public boolean getBoolean(String var1) {
      GameRules$Value var2 = this.theGameRules.get(var1);
      return var2 != null ? var2.getBoolean() : false;
   }

   public GameRules() {
      this.addGameRule("doFireTick", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("mobGriefing", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("keepInventory", "false", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("doMobSpawning", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("doMobLoot", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("doTileDrops", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("doEntityDrops", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("commandBlockOutput", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("naturalRegeneration", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("doDaylightCycle", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("logAdminCommands", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("showDeathMessages", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("randomTickSpeed", "3", GameRules$ValueType.NUMERICAL_VALUE);
      this.addGameRule("sendCommandFeedback", "true", GameRules$ValueType.BOOLEAN_VALUE);
      this.addGameRule("reducedDebugInfo", "false", GameRules$ValueType.BOOLEAN_VALUE);
   }

   public int getInt(String var1) {
      GameRules$Value var2 = this.theGameRules.get(var1);
      return var2 != null ? var2.getInt() : 0;
   }
}
