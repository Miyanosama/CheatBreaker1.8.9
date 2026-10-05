package net.minecraft.init;

import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceEntriesToLongTask;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.optifine.util.Json;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$25;

public class Bootstrap$12$1 extends BehaviorProjectileDispense {
   public Json field_0005;
   public ArmorStandRenderer field_0004;
   public ConcurrentHashMapV8$MapReduceEntriesToLongTask field_0001;
   public LogBrokerMonitor$25 field_0000;
   public AbstractScrollableElement field_0002;

   @Override
   public float func_82498_a() {
      return super.func_82498_a() * 0.5F;
   }

   @Override
   public IProjectile getProjectileEntity(World var1, IPosition var2) {
      return new EntityPotion(var1, var2.getX(), var2.getY(), var2.getZ(), this.field_150836_b.copy());
   }

   public Bootstrap$12$1(Bootstrap$12 var1, ItemStack var2) {
      this.field_150837_c = var1;
      this.field_150836_b = var2;
      super();
   }

   @Override
   public float func_82500_b() {
      return super.func_82500_b() * 1.25F;
   }
}
