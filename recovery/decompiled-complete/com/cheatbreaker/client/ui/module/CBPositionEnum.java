package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.ui.CompetitiveLeaveWarningGui;
import net.minecraft.block.BlockBreakable;
import net.minecraft.client.particle.EntityBreakingFX$Factory;
import net.minecraft.item.EnumRarity;
import org.apache.log4j.helpers.ThreadLocalMap;

public enum CBPositionEnum {
   LEFT("LEFT"),
   field_0008("TOP"),
   RIGHT("RIGHT"),
   CENTER("CENTER"),
   field_0006("BOTTOM");
   // $VF: synthetic field
   public static CBPositionEnum[] field_0005 = new CBPositionEnum[]{
      CBPositionEnum.field_0006, CBPositionEnum.field_0008, CBPositionEnum.CENTER, CBPositionEnum.LEFT, CBPositionEnum.RIGHT
   };
   public CompetitiveLeaveWarningGui field_0004;
   public ThreadLocalMap field_0001;
   public BlockBreakable field_0002;
   public String identifier;
   public EntityBreakingFX$Factory field_0011;
   public EnumRarity field_0000;

   public CBPositionEnum(String var3) {
      this.identifier = var3;
   }

   public String getIdentifier() {
      return this.identifier;
   }
}
