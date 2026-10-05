package net.minecraft.entity.passive;

import com.cheatbreaker.client.module.type.PackDisplayModule;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.optifine.reflect.ReflectorRaw;
import net.optifine.shaders.FlipTextures;
import net.optifine.shaders.config.ShaderOptionSwitch;
import recovered.unidentified.UnidentifiedClass1688;

public class EntityRabbit$RabbitJumpHelper extends EntityJumpHelper {
   public ReflectorRaw field_0001;
   public FlipTextures field_0007;
   public BlockBrewingStand field_0006;
   public boolean field_180068_d;
   public PackDisplayModule field_0008;
   public UnidentifiedClass1688 field_0000;
   public EntityRabbit theEntity;
   public ShaderOptionSwitch field_0003;

   public EntityRabbit$RabbitJumpHelper(EntityRabbit var1, EntityRabbit var2) {
      this.field_180069_b = var1;
      super(var2);
      this.field_180068_d = false;
      this.theEntity = var2;
   }

   public void func_180066_a(boolean var1) {
      this.field_180068_d = var1;
   }

   public boolean func_180065_d() {
      return this.field_180068_d;
   }

   @Override
   public void doJump() {
      if (this.a) {
         this.theEntity.doMovementAction(EntityRabbit$EnumMoveType.STEP);
         this.a = false;
      }
   }

   public boolean getIsJumping() {
      return this.a;
   }
}
