package net.minecraft.client.multiplayer;

import java.util.concurrent.Callable;
import net.minecraft.block.BlockLever$EnumOrientation;
import net.minecraft.client.particle.EntitySuspendFX;

public class WorldClient$4 implements Callable<String> {
   public EntitySuspendFX field_0002;
   public BlockLever$EnumOrientation field_0000;

   public WorldClient$4(WorldClient var1) {
      this.this$0 = var1;
      super();
   }

   public String call() {
      return WorldClient.access$200(this.this$0).getIntegratedServer() == null ? "Non-integrated multiplayer server" : "Integrated singleplayer server";
   }
}
