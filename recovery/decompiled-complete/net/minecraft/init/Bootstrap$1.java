package net.minecraft.init;

import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.client.renderer.texture.TextureClock;
import net.minecraft.dispenser.BehaviorProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.world.World;
import org.java_websocket.server.DefaultSSLWebSocketServerFactory;

public class Bootstrap$1 extends BehaviorProjectileDispense {
   public DefaultSSLWebSocketServerFactory field_0002;
   public S30PacketWindowItems field_0001;
   public TextureClock field_0000;
   public LayerVillagerArmor field_0003;

   @Override
   public IProjectile getProjectileEntity(World var1, IPosition var2) {
      EntityArrow var3 = new EntityArrow(var1, var2.getX(), var2.getY(), var2.getZ());
      var3.canBePickedUp = 1;
      return var3;
   }
}
