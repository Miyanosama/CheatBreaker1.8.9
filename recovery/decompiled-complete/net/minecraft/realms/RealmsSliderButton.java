package net.minecraft.realms;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.DefaultFutureListeners;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.MathHelper;

public class RealmsSliderButton extends RealmsButton {
   public int steps;
   public float value = 1.0F;
   public boolean sliding;
   public float minValue;
   public DefaultFutureListeners field_0007;
   public ReadTimeoutHandler field_0000;
   public float maxValue;
   public S23PacketBlockChange field_0004;

   public RealmsSliderButton(int var1, int var2, int var3, int var4, int var5, int var6, float var7, float var8) {
      super(var1, var2, var3, var4, 20, "");
      this.minValue = var7;
      this.maxValue = var8;
      this.value = this.toPct(var6);
      this.getProxy().j = this.getMessage();
   }

   public void clicked(float var1) {
   }

   @Override
   public int getYImage(boolean var1) {
      return 0;
   }

   @Override
   public void clicked(int var1, int var2) {
      this.value = (float)(var1 - (this.getProxy().h + 4)) / (this.getProxy().getButtonWidth() - 8);
      this.value = MathHelper.clamp_float(this.value, 0.0F, 1.0F);
      this.clicked(this.toValue(this.value));
      this.getProxy().j = this.getMessage();
      this.sliding = true;
   }

   public float toPct(float var1) {
      return MathHelper.clamp_float((this.clamp(var1) - this.minValue) / (this.maxValue - this.minValue), 0.0F, 1.0F);
   }

   @Override
   public void released(int var1, int var2) {
      this.sliding = false;
   }

   public RealmsSliderButton(int var1, int var2, int var3, int var4, int var5, int var6) {
      this(var1, var2, var3, var4, var6, 0, 1.0F, var5);
   }

   @Override
   public void renderBg(int var1, int var2) {
      if (this.getProxy().m) {
         if (this.sliding) {
            this.value = (float)(var1 - (this.getProxy().h + 4)) / (this.getProxy().getButtonWidth() - 8);
            this.value = MathHelper.clamp_float(this.value, 0.0F, 1.0F);
            float var3 = this.toValue(this.value);
            this.clicked(var3);
            this.value = this.toPct(var3);
            this.getProxy().j = this.getMessage();
         }

         Minecraft.getMinecraft().getTextureManager().bindTexture(WIDGETS_LOCATION);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.blit(this.getProxy().h + (int)(this.value * (this.getProxy().getButtonWidth() - 8)), this.getProxy().i, 0, 66, 4, 20);
         this.blit(this.getProxy().h + (int)(this.value * (this.getProxy().getButtonWidth() - 8)) + 4, this.getProxy().i, 196, 66, 4, 20);
      }
   }

   public float toValue(float var1) {
      return this.clamp(this.minValue + (this.maxValue - this.minValue) * MathHelper.clamp_float(var1, 0.0F, 1.0F));
   }

   public float clampSteps(float var1) {
      if (this.steps > 0) {
         var1 = this.steps * Math.round(var1 / this.steps);
      }

      return var1;
   }

   public String getMessage() {
      return "";
   }

   public float clamp(float var1) {
      var1 = this.clampSteps(var1);
      return MathHelper.clamp_float(var1, this.minValue, this.maxValue);
   }
}
