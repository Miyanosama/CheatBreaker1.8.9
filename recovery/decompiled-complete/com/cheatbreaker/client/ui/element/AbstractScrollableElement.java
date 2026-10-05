package com.cheatbreaker.client.ui.element;

import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.jagrosh.discordipc.IPCClient;
import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import net.minecraft.entity.passive.EntityRabbit$RabbitJumpHelper;
import net.minecraft.world.gen.structure.StructureVillagePieces$Field1;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass1774;

public abstract class AbstractScrollableElement extends AbstractModulesGuiElement {
   public boolean field_0007;
   public int field_0009;
   public double field_0006 = 0.0;
   public int field_0010;
   public int field_0013 = 0;
   public UnidentifiedClass1774 field_0003;
   public DefaultMarshallerProvider field_0005;
   public EntityRabbit$RabbitJumpHelper field_0008;
   public float field_0012;
   public StructureVillagePieces$Field1 field_0002;
   public boolean field_0001;
   public IPCClient field_0004;
   public boolean field_0000;
   public int field_0011;

   @Override
   public void onScroll(int var1) {
      if (var1 != 0 && this.field_0009 >= this.height) {
         this.field_0006 += var1 / 2;
      }
   }

   public abstract boolean method_03198(AbstractModule var1);

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
   }

   public void postDraw(int var1, int var2) {
      this.field_0000 = true;
      GL11.glPopMatrix();
      boolean var3 = this.field_0009 > this.height;
      if (this.field_0001 && !Mouse.isButtonDown(0)) {
         this.field_0001 = false;
      }

      double var4 = this.height - 10;
      double var6 = this.field_0009;
      double var8 = var4 / var6 * 100.0;
      double var10 = var4 / 100.0 * var8;
      double var12 = this.field_0013 / 100.0 * var8;
      if (var3) {
         int var14 = this.height;
         boolean var15 = var1 > (this.x + this.width - 9) * this.scale
            && var1 < (this.x + this.width - 3) * this.scale
            && var2 > (this.y + 11 - var12) * this.scale
            && var2 < (this.y + 8 + var10 - var12) * this.scale;
         boolean var16 = var1 > (this.x + this.width - 9) * this.scale
            && var1 < (this.x + this.width - 3) * this.scale
            && var2 > (this.y + 11) * this.scale
            && var2 < (this.y + 6 + var4 - 3.0) * this.scale;
         if (this.field_0001) {
            if (this.field_0013 != this.field_0012 && this.field_0012 != var10 / 2.0 && this.field_0012 != var10 / 2.0 + -this.field_0009 + var14) {
               if (var2 > (this.y + 11 + var10 - var10 / 4.0 - var12) * this.scale) {
                  this.field_0013 = (int)(this.field_0013 - var6 / 70.0);
               } else if (var2 < (this.y + 11 + var10 / 4.0 - var12) * this.scale) {
                  this.field_0013 = (int)(this.field_0013 + var6 / 70.0);
               }

               this.field_0012 = this.field_0013;
            } else if (var2 > (this.y + 11 + var10 - var10 / 4.0 - var12) * this.scale || var2 < (this.y + 11 + var10 / 4.0 - var12) * this.scale) {
               this.field_0012 = 1.0F;
            }
         }

         if (this.field_0013 < -this.field_0009 + var14) {
            this.field_0013 = -this.field_0009 + var14;
            this.field_0006 = 0.0;
         }

         if (this.field_0013 > 0) {
            this.field_0013 = 0;
            this.field_0006 = 0.0;
         }

         RenderUtil.method_22054(
            this.x + this.width - 6, this.y + 11, this.x + this.width - 4, this.y + 6 + var4 - 3.0, 2.0, var16 && !var15 ? 1862270976 : 1056964608
         );
         RenderUtil.method_22054(
            this.x + this.width - 7,
            this.y + 11 - var12,
            this.x + this.width - 3,
            this.y + 8 + var10 - var12,
            4.0,
            !var15 && !this.field_0001 ? -12418828 : -16629505
         );
      }

      if (!var3 && this.field_0013 != 0) {
         this.field_0013 = 0;
      }
   }

   public abstract void method_03200(AbstractModule var1);

   public void preDraw(int var1, int var2) {
      if (this.isMouseInside(var1, var2)) {
         double var3 = Math.round(this.field_0006 / 25.0);
         this.field_0006 -= var3;
         if (this.field_0006 != 0.0) {
            this.field_0013 = (int)(this.field_0013 + var3);
         }
      } else {
         this.field_0006 = 0.0;
      }

      if (this.field_0000) {
         if (this.field_0013 < -this.field_0009 + this.height) {
            this.field_0013 = -this.field_0009 + this.height;
            this.field_0006 = 0.0;
         }

         if (this.field_0013 > 0) {
            this.field_0013 = 0;
            this.field_0006 = 0.0;
         }
      }

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, this.field_0013, 0.0F);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      double var4 = this.height - 10;
      double var6 = this.field_0009;
      double var8 = var4 / var6 * 100.0;
      double var10 = var4 / 100.0 * var8;
      double var12 = this.field_0013 / 100.0 * var8;
      boolean var14 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11 - var12) * this.scale
         && var2 < (this.y + 8 + var10 - var12) * this.scale;
      boolean var15 = var1 > (this.x + this.width - 9) * this.scale
         && var1 < (this.x + this.width - 3) * this.scale
         && var2 > (this.y + 11) * this.scale
         && var2 < (this.y + 6 + var4 - 3.0) * this.scale;
      if (var3 == 0 && var15 || var14) {
         this.field_0001 = true;
      }
   }

   public AbstractScrollableElement(float var1, int var2, int var3, int var4, int var5) {
      super(var1);
      this.field_0009 = 0;
      this.field_0000 = false;
      this.field_0001 = false;
      this.field_0007 = false;
      this.field_0010 = var2;
      this.field_0011 = var3;
      this.x = var2;
      this.y = var3;
      this.width = var4;
      this.height = var5;
   }
}
