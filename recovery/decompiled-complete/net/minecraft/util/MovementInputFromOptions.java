package net.minecraft.util;

import com.cheatbreaker.client.module.type.BossBarModule;
import com.cheatbreaker.client.util.friend.Status;
import io.netty.handler.codec.http.HttpContentDecoder;
import net.minecraft.client.settings.GameSettings;

public class MovementInputFromOptions extends MovementInput {
   public Status field_0002;
   public GameSettings gameSettings;
   public HttpContentDecoder field_0003;
   public BossBarModule field_0001;

   public MovementInputFromOptions(GameSettings var1) {
      this.gameSettings = var1;
   }

   @Override
   public void updatePlayerMoveState() {
      this.field_0003 = 0.0F;
      this.field_0002 = 0.0F;
      if (this.gameSettings.keyBindForward.isKeyDown()) {
         this.field_0002++;
      }

      if (this.gameSettings.keyBindBack.isKeyDown()) {
         this.field_0002--;
      }

      if (this.gameSettings.keyBindLeft.isKeyDown()) {
         this.field_0003++;
      }

      if (this.gameSettings.keyBindRight.isKeyDown()) {
         this.field_0003--;
      }

      this.jump = this.gameSettings.keyBindJump.isKeyDown();
      this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
      if (this.sneak) {
         this.field_0003 = (float)(this.field_0003 * 0.3);
         this.field_0002 = (float)(this.field_0002 * 0.3);
      }
   }
}
