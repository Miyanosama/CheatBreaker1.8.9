package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityAIOpenDoor extends EntityAIDoorInteract {
   public boolean closeDoor;
   public int closeDoorTemporisation;

   @Override
   public void updateTask() {
      this.closeDoorTemporisation--;
      super.updateTask();
   }

   @Override
   public void resetTask() {
      if (this.closeDoor) {
         this.c.toggleDoor(this.a.o, this.b, false);
      }
   }

   @Override
   public void startExecuting() {
      this.closeDoorTemporisation = 20;
      this.c.toggleDoor(this.a.o, this.b, true);
   }

   @Override
   public boolean continueExecuting() {
      return this.closeDoor && this.closeDoorTemporisation > 0 && super.continueExecuting();
   }

   public EntityAIOpenDoor(EntityLiving var1, boolean var2) {
      super(var1);
      this.a = var1;
      this.closeDoor = var2;
   }
}
