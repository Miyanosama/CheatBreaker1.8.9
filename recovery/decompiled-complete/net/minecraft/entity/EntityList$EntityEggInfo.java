package net.minecraft.entity;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity$1;

public class EntityList$EntityEggInfo {
   public TileEntity$1 field_0003;
   public StatBase field_151512_d;
   public GuiTextField field_0002;
   public int primaryColor;
   public int secondaryColor;
   public int spawnedID;
   public StatBase field_151513_e;

   public EntityList$EntityEggInfo(int var1, int var2, int var3) {
      this.spawnedID = var1;
      this.primaryColor = var2;
      this.secondaryColor = var3;
      this.field_151512_d = StatList.getStatKillEntity(this);
      this.field_151513_e = StatList.getStatEntityKilledBy(this);
   }
}
