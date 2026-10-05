package net.minecraft.client.resources;

import io.netty.util.internal.logging.AbstractInternalLogger$1;
import net.minecraft.client.resources.data.AnimationMetadataSectionSerializer;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.inventory.InventoryMerchant;
import net.optifine.reflect.ReflectorFields;

public class Language implements Comparable<Language> {
   public boolean bidirectional;
   public EntityDragon field_0007;
   public String region;
   public AbstractInternalLogger$1 field_0006;
   public String name;
   public AnimationMetadataSectionSerializer field_0001;
   public InventoryMerchant field_0008;
   public ReflectorFields field_0005;
   public EntityAIControlledByPlayer field_0002;
   public String languageCode;

   @Override
   public boolean equals(Object var1) {
      return this == var1 ? true : (!(var1 instanceof Language) ? false : this.languageCode.equals(((Language)var1).languageCode));
   }

   public Language(String var1, String var2, String var3, boolean var4) {
      this.languageCode = var1;
      this.region = var2;
      this.name = var3;
      this.bidirectional = var4;
   }

   @Override
   public int hashCode() {
      return this.languageCode.hashCode();
   }

   public String getLanguageCode() {
      return this.languageCode;
   }

   @Override
   public String toString() {
      return String.format("%s (%s)", this.name, this.region);
   }

   public int compareTo(Language var1) {
      return this.languageCode.compareTo(var1.languageCode);
   }

   public boolean isBidirectional() {
      return this.bidirectional;
   }
}
