package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.optifine.CustomGuiProperties$1;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryExplorerModel;
import recovered.unidentified.UnidentifiedClass4396;

public class ModifiableAttributeInstance implements IAttributeInstance {
   public double baseValue;
   public IAttribute genericAttribute;
   public CategoryExplorerModel field_0004;
   public boolean needsUpdate;
   public UnidentifiedClass4396 field_0001;
   public double cachedValue;
   public Map<String, Set<AttributeModifier>> mapByName;
   public Map<UUID, AttributeModifier> mapByUUID;
   public BaseAttributeMap attributeMap;
   public Map<Integer, Set<AttributeModifier>> mapByOperation = Maps.newHashMap();
   public CustomGuiProperties$1 field_0000;

   @Override
   public Collection<AttributeModifier> func_111122_c() {
      HashSet var1 = Sets.newHashSet();

      for (int var2 = 0; var2 < 3; var2++) {
         var1.addAll(this.getModifiersByOperation(var2));
      }

      return var1;
   }

   @Override
   public double getBaseValue() {
      return this.baseValue;
   }

   @Override
   public void setBaseValue(double var1) {
      if (var1 != this.getBaseValue()) {
         this.baseValue = var1;
         this.flagForUpdate();
      }
   }

   @Override
   public Collection<AttributeModifier> getModifiersByOperation(int var1) {
      return this.mapByOperation.get(var1);
   }

   @Override
   public void removeModifier(AttributeModifier var1) {
      for (int var2 = 0; var2 < 3; var2++) {
         Set var3 = this.mapByOperation.get(var2);
         var3.remove(var1);
      }

      Set var4 = this.mapByName.get(var1.getName());
      if (var4 != null) {
         var4.remove(var1);
         if (var4.isEmpty()) {
            this.mapByName.remove(var1.getName());
         }
      }

      this.mapByUUID.remove(var1.getID());
      this.flagForUpdate();
   }

   public double computeValue() {
      double var1 = this.getBaseValue();

      for (AttributeModifier var4 : this.func_180375_b(0)) {
         var1 += var4.getAmount();
      }

      double var7 = var1;

      for (AttributeModifier var6 : this.func_180375_b(1)) {
         var7 += var1 * var6.getAmount();
      }

      for (AttributeModifier var9 : this.func_180375_b(2)) {
         var7 *= 1.0 + var9.getAmount();
      }

      return this.genericAttribute.clampValue(var7);
   }

   @Override
   public double getAttributeValue() {
      if (this.needsUpdate) {
         this.cachedValue = this.computeValue();
         this.needsUpdate = false;
      }

      return this.cachedValue;
   }

   @Override
   public void applyModifier(AttributeModifier var1) {
      if (this.getModifier(var1.getID()) != null) {
         throw new IllegalArgumentException("Modifier is already applied on this attribute!");
      } else {
         Object var2 = this.mapByName.get(var1.getName());
         if (var2 == null) {
            var2 = Sets.newHashSet();
            this.mapByName.put(var1.getName(), (Set<AttributeModifier>)var2);
         }

         this.mapByOperation.get(var1.getOperation()).add(var1);
         var2.add(var1);
         this.mapByUUID.put(var1.getID(), var1);
         this.flagForUpdate();
      }
   }

   @Override
   public AttributeModifier getModifier(UUID var1) {
      return this.mapByUUID.get(var1);
   }

   @Override
   public IAttribute getAttribute() {
      return this.genericAttribute;
   }

   @Override
   public boolean hasModifier(AttributeModifier var1) {
      return this.mapByUUID.get(var1.getID()) != null;
   }

   @Override
   public void removeAllModifiers() {
      Collection var1 = this.func_111122_c();
      if (var1 != null) {
         for (AttributeModifier var3 : Lists.newArrayList(var1)) {
            this.removeModifier(var3);
         }
      }
   }

   public Collection<AttributeModifier> func_180375_b(int var1) {
      HashSet var2 = Sets.newHashSet(this.getModifiersByOperation(var1));

      for (IAttribute var3 = this.genericAttribute.func_180372_d(); var3 != null; var3 = var3.func_180372_d()) {
         IAttributeInstance var4 = this.attributeMap.getAttributeInstance(var3);
         if (var4 != null) {
            var2.addAll(var4.getModifiersByOperation(var1));
         }
      }

      return var2;
   }

   public void flagForUpdate() {
      this.needsUpdate = true;
      this.attributeMap.func_180794_a(this);
   }

   public ModifiableAttributeInstance(BaseAttributeMap var1, IAttribute var2) {
      this.mapByName = Maps.newHashMap();
      this.mapByUUID = Maps.newHashMap();
      this.needsUpdate = true;
      this.attributeMap = var1;
      this.genericAttribute = var2;
      this.baseValue = var2.getDefaultValue();

      for (int var3 = 0; var3 < 3; var3++) {
         this.mapByOperation.put(var3, Sets.newHashSet());
      }
   }
}
