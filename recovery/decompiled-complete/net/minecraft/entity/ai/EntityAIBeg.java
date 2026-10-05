package net.minecraft.entity.ai;

import io.netty.channel.ChannelFlushPromiseNotifier;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.optifine.CustomGuiProperties$1;
import recovered.unidentified.UnidentifiedClass3676;
import recovered.unidentified.UnidentifiedClass5128;

public class EntityAIBeg extends EntityAIBase {
   public float minPlayerDistance;
   public EntityWolf theWolf;
   public ChannelFlushPromiseNotifier field_0003;
   public UnidentifiedClass3676 field_0006;
   public int timeoutCounter;
   public CustomGuiProperties$1 field_0001;
   public UnidentifiedClass5128 field_0008;
   public World worldObject;
   public EntityPlayer thePlayer;

   @Override
   public boolean shouldExecute() {
      this.thePlayer = this.worldObject.getClosestPlayerToEntity(this.theWolf, this.minPlayerDistance);
      return this.thePlayer == null ? false : this.hasPlayerGotBoneInHand(this.thePlayer);
   }

   @Override
   public void updateTask() {
      this.theWolf
         .getLookHelper()
         .setLookPosition(this.thePlayer.s, this.thePlayer.t + this.thePlayer.getEyeHeight(), this.thePlayer.u, 10.0F, this.theWolf.getVerticalFaceSpeed());
      this.timeoutCounter--;
   }

   @Override
   public boolean continueExecuting() {
      return !this.thePlayer.isEntityAlive()
         ? false
         : (
            this.theWolf.h(this.thePlayer) > this.minPlayerDistance * this.minPlayerDistance
               ? false
               : this.timeoutCounter > 0 && this.hasPlayerGotBoneInHand(this.thePlayer)
         );
   }

   @Override
   public void startExecuting() {
      this.theWolf.setBegging(true);
      this.timeoutCounter = 40 + this.theWolf.getRNG().nextInt(40);
   }

   public boolean hasPlayerGotBoneInHand(EntityPlayer var1) {
      ItemStack var2 = var1.bi.getCurrentItem();
      return var2 == null ? false : (!this.theWolf.isTamed() && var2.getItem() == Items.bone ? true : this.theWolf.isBreedingItem(var2));
   }

   public EntityAIBeg(EntityWolf var1, float var2) {
      this.theWolf = var1;
      this.worldObject = var1.o;
      this.minPlayerDistance = var2;
      this.setMutexBits(2);
   }

   @Override
   public void resetTask() {
      this.theWolf.setBegging(false);
      this.thePlayer = null;
   }
}
