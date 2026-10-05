package net.minecraft.entity.ai.attributes;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.management.LowerStringMap;

public abstract class BaseAttributeMap {
   public Map<IAttribute, IAttributeInstance> attributes = Maps.newHashMap();
   public Multimap<IAttribute, IAttribute> c;
   public Map<String, IAttributeInstance> attributesByName = new LowerStringMap<>();

   public void removeAttributeModifiers(Multimap<String, AttributeModifier> var1) {
      for (Entry var3 : var1.entries()) {
         IAttributeInstance var4 = this.getAttributeInstanceByName((String)var3.getKey());
         if (var4 != null) {
            var4.removeModifier((AttributeModifier)var3.getValue());
         }
      }
   }

   public IAttributeInstance getAttributeInstanceByName(String var1) {
      return this.attributesByName.get(var1);
   }

   public abstract IAttributeInstance func_180376_c(IAttribute var1);

   public void func_180794_a(IAttributeInstance var1) {
   }

   public IAttributeInstance registerAttribute(IAttribute var1) {
      if (this.attributesByName.containsKey(var1.getAttributeUnlocalizedName())) {
         throw new IllegalArgumentException("Attribute is already registered!");
      } else {
         IAttributeInstance var2 = this.func_180376_c(var1);
         this.attributesByName.put(var1.getAttributeUnlocalizedName(), var2);
         this.attributes.put(var1, var2);

         for (IAttribute var3 = var1.func_180372_d(); var3 != null; var3 = var3.func_180372_d()) {
            this.c.put(var3, var1);
         }

         return var2;
      }
   }

   public BaseAttributeMap() {
      this.c = HashMultimap.create();
   }

   public Collection<IAttributeInstance> getAllAttributes() {
      return this.attributesByName.values();
   }

   public void applyAttributeModifiers(Multimap<String, AttributeModifier> var1) {
      for (Entry var3 : var1.entries()) {
         IAttributeInstance var4 = this.getAttributeInstanceByName((String)var3.getKey());
         if (var4 != null) {
            var4.removeModifier((AttributeModifier)var3.getValue());
            var4.applyModifier((AttributeModifier)var3.getValue());
         }
      }
   }

   public IAttributeInstance getAttributeInstance(IAttribute var1) {
      return this.attributes.get(var1);
   }
}
