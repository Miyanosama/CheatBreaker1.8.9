package net.optifine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicLightsMap {
   public Map<Integer, DynamicLight> map = new HashMap<>();
   public boolean dirty;
   public List<DynamicLight> list = new ArrayList<>();

   public DynamicLight put(int var1, DynamicLight var2) {
      DynamicLight var3 = this.map.put(var1, var2);
      this.setDirty();
      return var3;
   }

   public void setDirty() {
      this.dirty = true;
   }

   public void clear() {
      this.map.clear();
      this.list.clear();
      this.setDirty();
   }

   public List<DynamicLight> valueList() {
      if (this.dirty) {
         this.list.clear();
         this.list.addAll(this.map.values());
         this.dirty = false;
      }

      return this.list;
   }

   public DynamicLightsMap() {
      this.dirty = false;
   }

   public DynamicLight remove(int var1) {
      DynamicLight var2 = this.map.remove(var1);
      if (var2 != null) {
         this.setDirty();
      }

      return var2;
   }

   public DynamicLight get(int var1) {
      return this.map.get(var1);
   }

   public int size() {
      return this.map.size();
   }
}
