package net.minecraft.client.particle;

import io.netty.bootstrap.ServerBootstrap$1;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.command.server.CommandSaveOff;
import net.minecraft.realms.RealmsSliderButton;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.world.World;

public class EntityExplodeFX extends EntityFX {
   public WebSocketServerHandshaker field_0003;
   public TileEntityEndPortal field_0005;
   public ServerBootstrap$1 field_0002;
   public CommandSaveOff field_0004;
   public GuiYesNo field_0000;
   public RealmsSliderButton field_0001;

   public EntityExplodeFX(World var1, double var2, double var4, double var6, double var8, double var10, double var12) {
      super(var1, var2, var4, var6, var8, var10, var12);
      this.v = var8 + (Math.random() * 2.0 - 1.0) * 0.05F;
      this.w = var10 + (Math.random() * 2.0 - 1.0) * 0.05F;
      this.x = var12 + (Math.random() * 2.0 - 1.0) * 0.05F;
      this.ar = this.as = this.at = this.V.nextFloat() * 0.3F + 0.7F;
      this.h = this.V.nextFloat() * this.V.nextFloat() * 6.0F + 1.0F;
      this.g = (int)(16.0 / (this.V.nextFloat() * 0.8 + 0.2)) + 2;
   }

   @Override
   public void onUpdate() {
      this.p = this.s;
      this.q = this.t;
      this.r = this.u;
      if (this.f++ >= this.g) {
         this.setDead();
      }

      this.k(7 - this.f * 8 / this.g);
      this.w += 0.004;
      this.d(this.v, this.w, this.x);
      this.v *= 0.9F;
      this.w *= 0.9F;
      this.x *= 0.9F;
      if (this.C) {
         this.v *= 0.7F;
         this.x *= 0.7F;
      }
   }
}
