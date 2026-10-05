package org.slf4j.event;

import net.minecraft.nbt.JsonToNBT;
import net.optifine.entity.model.ModelAdapterMinecartMobSpawner;

public enum Level {
   DEBUG(10, "DEBUG"),
   TRACE(0, "TRACE"),
   ERROR(40, "ERROR"),
   INFO(20, "INFO"),
   WARN(30, "WARN");

   public JsonToNBT field_0003;
   public String levelStr;
   public ModelAdapterMinecartMobSpawner field_0000;
   public int levelInt;

   @Override
   public String toString() {
      return this.levelStr;
   }

   public Level(int var3, String var4) {
      this.levelInt = var3;
      this.levelStr = var4;
   }

   public int toInt() {
      return this.levelInt;
   }
}
