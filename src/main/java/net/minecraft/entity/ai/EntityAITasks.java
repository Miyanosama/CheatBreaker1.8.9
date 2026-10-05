package net.minecraft.entity.ai;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityAITasks {
   public static Logger logger = LogManager.getLogger();
   public List<EntityAITasks.EntityAITaskEntry> taskEntries = Lists.newArrayList();
   public Profiler theProfiler;
   public List<EntityAITasks.EntityAITaskEntry> executingTaskEntries = Lists.newArrayList();
   public int tickCount;
   public int tickRate = 3;

   public void onUpdateTasks() {
      this.theProfiler.startSection("goalSetup");
      if (this.tickCount++ % this.tickRate == 0) {
         for (EntityAITasks.EntityAITaskEntry var2 : this.taskEntries) {
            boolean var3 = this.executingTaskEntries.contains(var2);
            if (var3) {
               if (this.canUse(var2) && this.canContinue(var2)) {
                  continue;
               }

               var2.action.resetTask();
               this.executingTaskEntries.remove(var2);
            }

            if (this.canUse(var2) && var2.action.shouldExecute()) {
               var2.action.startExecuting();
               this.executingTaskEntries.add(var2);
            }
         }
      } else {
         Iterator var4 = this.executingTaskEntries.iterator();

         while (var4.hasNext()) {
            EntityAITasks.EntityAITaskEntry var6 = (EntityAITasks.EntityAITaskEntry)var4.next();
            if (!this.canContinue(var6)) {
               var6.action.resetTask();
               var4.remove();
            }
         }
      }

      this.theProfiler.endSection();
      this.theProfiler.startSection("goalTick");

      for (EntityAITasks.EntityAITaskEntry var7 : this.executingTaskEntries) {
         var7.action.updateTask();
      }

      this.theProfiler.endSection();
   }

   public void addTask(int var1, EntityAIBase var2) {
      this.taskEntries.add(new EntityAITasks.EntityAITaskEntry(var1, var2));
   }

   public void removeTask(EntityAIBase var1) {
      Iterator var2 = this.taskEntries.iterator();

      while (var2.hasNext()) {
         EntityAITasks.EntityAITaskEntry var3 = (EntityAITasks.EntityAITaskEntry)var2.next();
         EntityAIBase var4 = var3.action;
         if (var4 == var1) {
            if (this.executingTaskEntries.contains(var3)) {
               var4.resetTask();
               this.executingTaskEntries.remove(var3);
            }

            var2.remove();
         }
      }
   }

   public boolean canContinue(EntityAITasks.EntityAITaskEntry var1) {
      return var1.action.continueExecuting();
   }

   public boolean areTasksCompatible(EntityAITasks.EntityAITaskEntry var1, EntityAITasks.EntityAITaskEntry var2) {
      return (var1.action.getMutexBits() & var2.action.getMutexBits()) == 0;
   }

   public EntityAITasks(Profiler var1) {
      this.theProfiler = var1;
   }

   public boolean canUse(EntityAITasks.EntityAITaskEntry var1) {
      for (EntityAITasks.EntityAITaskEntry var3 : this.taskEntries) {
         if (var3 != var1) {
            if (var1.priority >= var3.priority) {
               if (!this.areTasksCompatible(var1, var3) && this.executingTaskEntries.contains(var3)) {
                  return false;
               }
            } else if (!var3.action.isInterruptible() && this.executingTaskEntries.contains(var3)) {
               return false;
            }
         }
      }

      return true;
   }

   public class EntityAITaskEntry {
      public int priority;
      public EntityAIBase action;

      public EntityAITaskEntry(int var2, EntityAIBase var3) {
         this.priority = var2;
         this.action = var3;
      }
   }
}
