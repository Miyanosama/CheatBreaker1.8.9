package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.CompetitiveLeaveWarningGui;
import com.cheatbreaker.client.ui.DisconnectConfirmationGui;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.SessionServer;
import com.cheatbreaker.client.util.SessionServer$Status;
import java.awt.Color;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentArrowFire;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiIngameMenu extends GuiScreen {
   public long field_0006;
   public boolean field_0013;
   public ResourceLocation field_0005;
   public int field_146444_f;
   public boolean field_0001;
   public ResourceLocation field_0002;
   public int field_146445_a;
   public String field_0009;
   public CosineFade field_0003;
   public long field_0015;
   public long field_0000;
   public EnchantmentArrowFire field_0007;
   public boolean field_0008;
   public ResourceLocation field_0004 = new ResourceLocation("client/logo_white.png");
   public GuiButton field_0010;
   public CosineFade field_0012;

   public void method_22128(GuiButton var1) {
      if (CheatBreaker.getInstance().getNetHandler().method_11477()) {
         this.j.displayGuiScreen(new CompetitiveLeaveWarningGui(this));
      } else {
         if (CheatBreaker.getInstance().getGlobalSettings().field_0031.method_08908()) {
            boolean var2 = !CheatBreaker.getInstance().getGlobalSettings().field_0051.getValue().equals("Multiplayer") && this.j.isSingleplayer();
            boolean var3 = !CheatBreaker.getInstance().getGlobalSettings().field_0051.getValue().equals("Singleplayer") && !this.j.isSingleplayer();
            if (CheatBreaker.getInstance().getGlobalSettings().field_0072.method_08908()) {
               int var4 = this.field_0006 == (-6494232742540582220L & 566527241L)
                  ? (int)CheatBreaker.getInstance().getGlobalSettings().field_0012.method_08905()
                  : (int)(
                     (
                           CheatBreaker.getInstance().getGlobalSettings().field_0012.method_08905() * 1000.0F
                              + 999.0F
                              - (float)(System.currentTimeMillis() - this.field_0006)
                        )
                        / 1000.0F
                  );
               if (var4 > 0 && (var2 || var3)) {
                  this.j.displayGuiScreen(new DisconnectConfirmationGui(this));
                  return;
               }
            } else if (var2 || var3) {
               this.j.displayGuiScreen(new DisconnectConfirmationGui(this));
               return;
            }
         }

         var1.l = false;
         this.j.theWorld.method_05035();
         this.j.loadWorld(null);
         this.j.displayGuiScreen(new MainMenu());
      }
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.field_146444_f++;
   }

   @Override
   public void initGui() {
      this.field_146445_a = 0;
      this.n.clear();
      byte var1 = -16;
      boolean var2 = true;
      this.field_0006 = System.currentTimeMillis();
      this.field_0009 = I18n.format("menu.returnToMenu");
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 120 + var1, this.field_0009));
      if (!this.j.isIntegratedServerRunning()) {
         this.n.get(0).j = this.field_0009 = I18n.format("menu.disconnect");
      }

      this.n.add(new GuiButton(4, this.l / 2 - 100, this.m / 4 + 24 + var1, I18n.format("menu.returnToGame")));
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 96 + var1, 98, 20, I18n.format("menu.options")));
      GuiButton var3 = new GuiButton(7, this.l / 2 + 2, this.m / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan"));
      var3.l = this.j.isSingleplayer() && !this.j.getIntegratedServer().getPublic();
      this.n.add(new GuiButton(5, this.l / 2 - 100, this.m / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements")));
      this.n.add(new GuiButton(6, this.l / 2 + 2, this.m / 4 + 48 + var1, 98, 20, I18n.format("gui.stats")));
      if (!var3.l) {
         this.field_0010 = new GuiButton(10, this.l / 2 + 2, this.m / 4 + 96 + var1, 98, 20, "Mods");
         this.n.add(this.field_0010);
         this.n.add(new GuiButton(16, this.l / 2 - 100, this.m / 4 + 72 + var1, 200, 20, "Server List"));
      } else {
         this.n.add(var3);
         this.n.add(new GuiButton(16, this.l / 2 - 100, this.m / 4 + 72 + var1, 98, 20, "Server List"));
         this.field_0010 = new GuiButton(10, this.l / 2 + 2, this.m / 4 + 72 + var1, 98, 20, "Mods");
         this.n.add(this.field_0010);
      }

      boolean var4 = !CheatBreaker.getInstance().getGlobalSettings().field_0025.getValue().equals("Multiplayer") && this.j.isSingleplayer();
      boolean var5 = !CheatBreaker.getInstance().getGlobalSettings().field_0025.getValue().equals("Singleplayer") && !this.j.isSingleplayer();
      if (CheatBreaker.getInstance().getGlobalSettings().field_0056.method_08908() && (var4 || var5)) {
         this.n.get(0).l = false;
      }

      if (CheatBreaker.getInstance().getGlobalSettings().field_0112.method_08908() && CheatBreaker.getInstance().getGlobalSettings().field_0063.method_08908()) {
         this.field_0010.l = false;
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      short var4 = 600;
      short var5 = 356;
      double var6 = Math.min(this.l, this.m) / (var4 * 9.0);
      int var8 = (int)(var4 * var6);
      int var9 = (int)(var5 * var6);
      if (CheatBreaker.getInstance().getGlobalSettings().getCrosshairSettingsLabel().method_08912() == 1) {
         this.method_22127(this.l, this.m);
      } else {
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
         RenderUtil.method_22064(this.field_0004, this.l / 2 - var8 / 2 + 1.0F, var9 * 2 + 1.0F, var8, var9);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.method_22064(this.field_0004, this.l / 2 - var8 / 2, var9 * 2, var8, var9);
      }

      boolean var10 = false;

      for (SessionServer var12 : CheatBreaker.getInstance().field_0038) {
         if (var12.getStatus() == SessionServer$Status.field_0005) {
            var10 = true;
         }
      }

      if (CheatBreaker.getInstance().getGlobalSettings().field_0056.method_08908()) {
         int var13 = this.field_0006 == (1695322576L & 33701888L)
            ? (int)CheatBreaker.getInstance().getGlobalSettings().field_0002.method_08905()
            : (int)(
               (
                     CheatBreaker.getInstance().getGlobalSettings().field_0002.method_08905() * 1000.0F
                        + 999.0F
                        - (float)(System.currentTimeMillis() - this.field_0006)
                  )
                  / 1000.0F
            );
         if (var13 <= 0) {
            this.n.get(0).l = true;
         }
      }

      if (CheatBreaker.getInstance().getGlobalSettings().field_0063.method_08908()) {
         int var14 = this.field_0006 == (1745164882L & 4198171918966227329L)
            ? (int)CheatBreaker.getInstance().getGlobalSettings().field_0118.method_08905()
            : (int)(
               (
                     CheatBreaker.getInstance().getGlobalSettings().field_0118.method_08905() * 1000.0F
                        + 999.0F
                        - (float)(System.currentTimeMillis() - this.field_0006)
                  )
                  / 1000.0F
            );
         if (var14 <= 0) {
            this.field_0010.l = true;
         }
      }

      if (this.field_0013 && Mouse.isButtonDown(0) && CheatBreaker.getInstance().getGlobalSettings().field_0055.method_08908()) {
         float var15 = this.field_0015 == (413419520L & 538673587L)
            ? CheatBreaker.getInstance().getGlobalSettings().field_0105.method_08905()
            : (
                  CheatBreaker.getInstance().getGlobalSettings().field_0105.method_08905() * 1000.0F
                     + 999.0F
                     - (float)(System.currentTimeMillis() - this.field_0015)
               )
               / 1000.0F;
         this.n.get(0).j = this.field_0009 + " (" + (int)var15 + ")";
         if (var15 < 1.0F && var15 > 0.9F) {
            this.method_22128(this.n.get(0));
         }
      } else {
         this.n.get(0).j = this.field_0009;
         this.field_0015 = 3129344446844404289L & -3129344447395330810L;
         this.field_0013 = false;
      }

      if (this.field_0008
         && Mouse.isButtonDown(0)
         && CheatBreaker.getInstance().getGlobalSettings().field_0112.method_08908()
         && CheatBreaker.getInstance().getGlobalSettings().field_0008.method_08908()) {
         float var16 = this.field_0000 == (16845321L & 471916544L)
            ? CheatBreaker.getInstance().getGlobalSettings().field_0114.method_08905()
            : (
                  CheatBreaker.getInstance().getGlobalSettings().field_0114.method_08905() * 1000.0F
                     + 999.0F
                     - (float)(System.currentTimeMillis() - this.field_0000)
               )
               / 1000.0F;
         this.field_0010.j = "Mods (" + (int)var16 + ")";
         if ((int)var16 == 0) {
            this.j.displayGuiScreen(new CBModulesGui());
         }
      } else {
         this.field_0010.j = "Mods";
         this.field_0000 = 872958260L & 514L;
         this.field_0008 = false;
      }

      if (var10) {
         if (!this.field_0003.method_21217()) {
            this.field_0003.method_20200();
         }

         this.field_0003.method_21234();
         a(this.l / 2 - 100, this.m / 4 + 128, this.l / 2 + 100, this.m / 4 + 142, 1862270976);
         a(
            this.l / 2 - 100,
            this.m / 4 + 128,
            this.l / 2 + 100,
            this.m / 4 + 142,
            new Color(1.0F, 0.15F, 0.15F, 0.65F * this.field_0003.method_21227()).getRGB()
         );
         CheatBreaker.getInstance().field_0068.drawCenteredString("Some login services might be offline".toUpperCase(), this.l / 2, this.m / 4 + 130, -1);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void method_22127(double var1, double var3) {
      try {
         if (!this.field_0012.method_21217()) {
            this.field_0012.method_20200();
            this.field_0012.method_21234();
         }

         float var5 = 18.0F;
         double var6 = var1 / 2.0 - var5;
         double var8 = this.n.size() > 2 ? this.n.get(1).i - var5 - 32.0F : -100.0;
         GL11.glPushMatrix();
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glTranslatef((float)var6 + 1.0F, (float)var8 + 1.0F, 1.0F);
         GL11.glTranslatef(var5, var5, var5);
         GL11.glRotatef(180.0F * this.field_0012.method_21227(), 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-var5, -var5, -var5);
         RenderUtil.method_22063(this.field_0002, var5, 0.0F, 0.0F);
         GL11.glPopMatrix();
         RenderUtil.method_22063(this.field_0005, var5, (float)var6 + 1.0F, (float)var8 + 1.0F);
         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glTranslatef((float)var6, (float)var8, 1.0F);
         GL11.glTranslatef(var5, var5, var5);
         GL11.glRotatef(180.0F * this.field_0012.method_21227(), 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-var5, -var5, -var5);
         RenderUtil.method_22063(this.field_0002, var5, 0.0F, 0.0F);
         GL11.glPopMatrix();
         RenderUtil.method_22063(this.field_0005, var5, (float)var6, (float)var8);
      } catch (Exception var10) {
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      this.field_0008 = false;
      switch (var1.k) {
         case 0:
            this.j.displayGuiScreen(new GuiOptions(this, this.j.gameSettings));
            break;
         case 1:
            boolean var2 = !CheatBreaker.getInstance().getGlobalSettings().field_0064.getValue().equals("Multiplayer") && this.j.isSingleplayer();
            boolean var3 = !CheatBreaker.getInstance().getGlobalSettings().field_0064.getValue().equals("Singleplayer") && !this.j.isSingleplayer();
            if (!CheatBreaker.getInstance().getGlobalSettings().field_0055.method_08908() || !var2 && !var3) {
               this.method_22128(var1);
            } else {
               this.field_0013 = true;
               this.field_0015 = System.currentTimeMillis();
            }
         case 2:
         case 3:
         case 8:
         case 9:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         default:
            break;
         case 4:
            this.j.displayGuiScreen(null);
            this.j.method_20340();
            break;
         case 5:
            this.j.displayGuiScreen(new GuiAchievements(this, this.j.thePlayer.getStatFileWriter()));
            break;
         case 6:
            this.j.displayGuiScreen(new GuiStats(this, this.j.thePlayer.getStatFileWriter()));
            break;
         case 7:
            this.j.displayGuiScreen(new GuiShareToLan(this));
            break;
         case 10:
            if (CheatBreaker.getInstance().getGlobalSettings().field_0112.method_08908()
               && CheatBreaker.getInstance().getGlobalSettings().field_0008.method_08908()) {
               this.field_0008 = true;
               this.field_0000 = System.currentTimeMillis();
            } else {
               this.j.displayGuiScreen(new CBModulesGui());
            }
            break;
         case 16:
            this.j.displayGuiScreen(new GuiMultiplayer(this));
      }
   }

   public GuiIngameMenu() {
      this.field_0002 = new ResourceLocation("client/logo_255_outer.png");
      this.field_0005 = new ResourceLocation("client/logo_108_inner.png");
      this.field_0012 = new CosineFade(-3876765796738723865L & 1018974112L);
      this.field_0008 = false;
      this.field_0013 = false;
      this.field_0001 = false;
      this.field_0003 = new CosineFade(209766396L & 539231708L);
   }
}
