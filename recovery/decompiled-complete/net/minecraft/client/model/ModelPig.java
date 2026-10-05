package net.minecraft.client.model;

import net.minecraft.client.gui.spectator.PlayerMenuObject;
import net.minecraft.world.Teleporter$PortalPosition;
import net.minecraft.world.WorldManager;
import net.optifine.CustomSky;
import net.optifine.gui.TooltipProviderShaderOptions;

public class ModelPig extends ModelQuadruped {
   public CustomSky field_0003;
   public ModelRenderer field_0005;
   public PlayerMenuObject field_0002;
   public TooltipProviderShaderOptions field_0004;
   public Teleporter$PortalPosition field_0000;
   public WorldManager field_0001;

   public ModelPig() {
      this(0.0F);
   }

   public ModelPig(float var1) {
      super(6, var1);
      this.a.setTextureOffset(16, 16).addBox(-2.0F, 0.0F, -9.0F, 4, 3, 1, var1);
      this.g = 4.0F;
   }
}
