package net.minecraft.util;

import net.minecraft.client.settings.GameSettings;

public class MovementInputFromOptions extends MovementInput {
   public GameSettings gameSettings;

   public MovementInputFromOptions(GameSettings var1) {
      this.gameSettings = var1;
   }

   @Override
   public void updatePlayerMoveState() {
      this.recoveredField3365 = 0.0F;
      this.recoveredField3366 = 0.0F;
      if (this.gameSettings.keyBindForward.isKeyDown()) {
         this.recoveredField3366++;
      }

      if (this.gameSettings.keyBindBack.isKeyDown()) {
         this.recoveredField3366--;
      }

      if (this.gameSettings.keyBindLeft.isKeyDown()) {
         this.recoveredField3365++;
      }

      if (this.gameSettings.keyBindRight.isKeyDown()) {
         this.recoveredField3365--;
      }

      this.jump = this.gameSettings.keyBindJump.isKeyDown();
      this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
      if (this.sneak) {
         this.recoveredField3365 = (float)(this.recoveredField3365 * 0.3);
         this.recoveredField3366 = (float)(this.recoveredField3366 * 0.3);
      }
   }
}
