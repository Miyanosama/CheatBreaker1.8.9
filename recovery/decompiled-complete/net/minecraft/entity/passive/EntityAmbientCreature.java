package net.minecraft.entity.passive;

import com.cheatbreaker.client.util.title.Title$TitleType;
import net.minecraft.command.server.CommandOp;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.world.World;

public abstract class EntityAmbientCreature extends EntityLiving implements IAnimals {
   public TileEntityNote field_0001;
   public Title$TitleType field_0002;
   public CommandOp field_0000;

   public EntityAmbientCreature(World var1) {
      super(var1);
   }

   @Override
   public boolean interact(EntityPlayer var1) {
      return false;
   }

   @Override
   public boolean allowLeashing() {
      return false;
   }
}
