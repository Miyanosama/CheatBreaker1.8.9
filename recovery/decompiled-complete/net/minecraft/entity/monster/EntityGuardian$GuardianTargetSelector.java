package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$1;
import io.netty.util.internal.TypeParameterMatcher;
import junit.framework.AssertionFailedError;
import net.minecraft.block.BlockSourceImpl;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityGuardian$GuardianTargetSelector implements Predicate<EntityLivingBase> {
   public EntityGuardian parentEntity;
   public PathNavigateGround field_0005;
   public SpdyHeaderBlockRawDecoder$1 field_0002;
   public AssertionFailedError field_0004;
   public TypeParameterMatcher field_0000;
   public BlockSourceImpl field_0001;

   public EntityGuardian$GuardianTargetSelector(EntityGuardian var1) {
      this.parentEntity = var1;
   }

   public boolean apply(EntityLivingBase var1) {
      return (var1 instanceof EntityPlayer || var1 instanceof EntitySquid) && var1.h(this.parentEntity) > 9.0;
   }
}
