package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.management.LowerStringMap;

public class ServersideAttributeMap extends BaseAttributeMap {
   public Map<String, IAttributeInstance> descriptionToAttributeInstanceMap;
   public Set<IAttributeInstance> attributeInstanceSet = Sets.newHashSet();

   public Set<IAttributeInstance> getAttributeInstanceSet() {
      return this.attributeInstanceSet;
   }

   public ServersideAttributeMap() {
      this.descriptionToAttributeInstanceMap = new LowerStringMap<>();
   }

   public Collection<IAttributeInstance> getWatchedAttributes() {
      HashSet var1 = Sets.newHashSet();

      for (IAttributeInstance var3 : this.getAllAttributes()) {
         if (var3.getAttribute().getShouldWatch()) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public ModifiableAttributeInstance getAttributeInstance(IAttribute var1) {
      return (ModifiableAttributeInstance)super.getAttributeInstance(var1);
   }

   @Override
   public void func_180794_a(IAttributeInstance var1) {
      if (var1.getAttribute().getShouldWatch()) {
         this.attributeInstanceSet.add(var1);
      }

      for (IAttribute var3 : this.c.get(var1.getAttribute())) {
         ModifiableAttributeInstance var4 = this.getAttributeInstance(var3);
         if (var4 != null) {
            var4.flagForUpdate();
         }
      }
   }

   @Override
   public IAttributeInstance func_180376_c(IAttribute var1) {
      return new ModifiableAttributeInstance(this, var1);
   }

   public ModifiableAttributeInstance getAttributeInstanceByName(String var1) {
      IAttributeInstance var2 = super.getAttributeInstanceByName(var1);
      if (var2 == null) {
         var2 = this.descriptionToAttributeInstanceMap.get(var1);
      }

      return (ModifiableAttributeInstance)var2;
   }

   @Override
   public IAttributeInstance registerAttribute(IAttribute var1) {
      IAttributeInstance var2 = super.registerAttribute(var1);
      if (var1 instanceof RangedAttribute && ((RangedAttribute)var1).getDescription() != null) {
         this.descriptionToAttributeInstanceMap.put(((RangedAttribute)var1).getDescription(), var2);
      }

      return var2;
   }
}
