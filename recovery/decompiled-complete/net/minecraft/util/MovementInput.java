package net.minecraft.util;

import io.netty.handler.codec.serialization.CompatibleObjectEncoder;
import io.netty.util.HashedWheelTimer$HashedWheelBucket;
import net.minecraft.block.BlockFarmland$1;
import net.minecraft.creativetab.CreativeTabs$3;
import net.minecraft.entity.monster.EntitySlime$SlimeMoveHelper;
import org.apache.log4j.pattern.NamePatternConverter;

public class MovementInput {
   public float field_0003;
   public BlockFarmland$1 field_0005;
   public CreativeTabs$3 field_0006;
   public CompatibleObjectEncoder field_0009;
   public float field_0002;
   public boolean jump;
   public NamePatternConverter field_0004;
   public boolean sneak;
   public EntitySlime$SlimeMoveHelper field_0008;
   public HashedWheelTimer$HashedWheelBucket field_0007;

   public void updatePlayerMoveState() {
   }
}
