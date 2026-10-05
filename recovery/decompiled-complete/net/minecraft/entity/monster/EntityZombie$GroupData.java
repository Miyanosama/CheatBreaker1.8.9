package net.minecraft.entity.monster;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.realms.RealmsVertexFormat;

public class EntityZombie$GroupData implements IEntityLivingData {
   public RealmsVertexFormat field_0003;
   public boolean isChild;
   public boolean isVillager;

   public EntityZombie$GroupData(EntityZombie var1, boolean var2, boolean var3) {
      this.field_142047_c = var1;
      super();
      this.isChild = false;
      this.isVillager = false;
      this.isChild = var2;
      this.isVillager = var3;
   }
}
