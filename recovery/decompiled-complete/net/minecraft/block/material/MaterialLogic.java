package net.minecraft.block.material;

import net.minecraft.client.resources.model.ModelBakery;
import org.java_websocket.server.CustomSSLWebSocketServerFactory;

public class MaterialLogic extends Material {
   public ModelBakery field_0000;
   public CustomSSLWebSocketServerFactory field_0001;

   @Override
   public boolean isSolid() {
      return false;
   }

   public MaterialLogic(MapColor var1) {
      super(var1);
      this.setAdventureModeExempt();
   }

   @Override
   public boolean blocksLight() {
      return false;
   }

   @Override
   public boolean blocksMovement() {
      return false;
   }
}
