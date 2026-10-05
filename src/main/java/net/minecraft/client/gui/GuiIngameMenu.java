package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.CompetitiveLeaveWarningGui;
import com.cheatbreaker.client.ui.DisconnectConfirmationGui;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.SessionServer;
import java.awt.Color;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiIngameMenu extends GuiScreen {
   public long recoveredField2442;
   public boolean recoveredField2443;
   public ResourceLocation recoveredField2444;
   public int field_146444_f;
   public boolean recoveredField2445;
   public ResourceLocation recoveredField2446;
   public int field_146445_a;
   public String recoveredField2447;
   public CosineFade recoveredField2448;
   public long recoveredField2449;
   public long recoveredField2450;
   public boolean recoveredField2451;
   public ResourceLocation recoveredField2452 = new ResourceLocation("client/logo_white.png");
   public GuiButton recoveredField2453;
   public CosineFade recoveredField2454;

   public void method_22128(GuiButton var1) {
      if (CheatBreaker.getInstance().getNetHandler().method_11477()) {
         this.j.displayGuiScreen(new CompetitiveLeaveWarningGui(this));
      } else {
         if (CheatBreaker.getInstance().getGlobalSettings().recoveredField511.method_08908()) {
            boolean var2 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField508.getValue().equals("Multiplayer") && this.j.isSingleplayer();
            boolean var3 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField508.getValue().equals("Singleplayer") && !this.j.isSingleplayer();
            if (CheatBreaker.getInstance().getGlobalSettings().recoveredField573.method_08908()) {
               int var4 = this.recoveredField2442 == 0L
                  ? (int)CheatBreaker.getInstance().getGlobalSettings().recoveredField496.method_08905()
                  : (int)(
                     (
                           CheatBreaker.getInstance().getGlobalSettings().recoveredField496.method_08905() * 1000.0F
                              + 999.0F
                              - (float)(System.currentTimeMillis() - this.recoveredField2442)
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
      this.recoveredField2442 = System.currentTimeMillis();
      this.recoveredField2447 = I18n.format("menu.returnToMenu");
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 120 + var1, this.recoveredField2447));
      if (!this.j.isIntegratedServerRunning()) {
         this.n.get(0).j = this.recoveredField2447 = I18n.format("menu.disconnect");
      }

      this.n.add(new GuiButton(4, this.l / 2 - 100, this.m / 4 + 24 + var1, I18n.format("menu.returnToGame")));
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 96 + var1, 98, 20, I18n.format("menu.options")));
      GuiButton var3 = new GuiButton(7, this.l / 2 + 2, this.m / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan"));
      var3.l = this.j.isSingleplayer() && !this.j.getIntegratedServer().getPublic();
      this.n.add(new GuiButton(5, this.l / 2 - 100, this.m / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements")));
      this.n.add(new GuiButton(6, this.l / 2 + 2, this.m / 4 + 48 + var1, 98, 20, I18n.format("gui.stats")));
      if (!var3.l) {
         this.recoveredField2453 = new GuiButton(10, this.l / 2 + 2, this.m / 4 + 96 + var1, 98, 20, "Mods");
         this.n.add(this.recoveredField2453);
         this.n.add(new GuiButton(16, this.l / 2 - 100, this.m / 4 + 72 + var1, 200, 20, "Server List"));
      } else {
         this.n.add(var3);
         this.n.add(new GuiButton(16, this.l / 2 - 100, this.m / 4 + 72 + var1, 98, 20, "Server List"));
         this.recoveredField2453 = new GuiButton(10, this.l / 2 + 2, this.m / 4 + 72 + var1, 98, 20, "Mods");
         this.n.add(this.recoveredField2453);
      }

      boolean var4 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField520.getValue().equals("Multiplayer") && this.j.isSingleplayer();
      boolean var5 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField520.getValue().equals("Singleplayer") && !this.j.isSingleplayer();
      if (CheatBreaker.getInstance().getGlobalSettings().recoveredField481.method_08908() && (var4 || var5)) {
         this.n.get(0).l = false;
      }

      if (CheatBreaker.getInstance().getGlobalSettings().recoveredField480.method_08908()
         && CheatBreaker.getInstance().getGlobalSettings().recoveredField577.method_08908()) {
         this.recoveredField2453.l = false;
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
         RenderUtil.method_22064(this.recoveredField2452, this.l / 2 - var8 / 2 + 1.0F, var9 * 2 + 1.0F, var8, var9);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         RenderUtil.method_22064(this.recoveredField2452, this.l / 2 - var8 / 2, var9 * 2, var8, var9);
      }

      boolean var10 = false;

      for (SessionServer var12 : CheatBreaker.getInstance().recoveredField1578) {
         if (var12.getStatus() == SessionServer.Status.DOWN) {
            var10 = true;
         }
      }

      if (CheatBreaker.getInstance().getGlobalSettings().recoveredField481.method_08908()) {
         int var13 = this.recoveredField2442 == 0L
            ? (int)CheatBreaker.getInstance().getGlobalSettings().recoveredField530.method_08905()
            : (int)(
               (
                     CheatBreaker.getInstance().getGlobalSettings().recoveredField530.method_08905() * 1000.0F
                        + 999.0F
                        - (float)(System.currentTimeMillis() - this.recoveredField2442)
                  )
                  / 1000.0F
            );
         if (var13 <= 0) {
            this.n.get(0).l = true;
         }
      }

      if (CheatBreaker.getInstance().getGlobalSettings().recoveredField577.method_08908()) {
         int var14 = this.recoveredField2442 == 0L
            ? (int)CheatBreaker.getInstance().getGlobalSettings().recoveredField552.method_08905()
            : (int)(
               (
                     CheatBreaker.getInstance().getGlobalSettings().recoveredField552.method_08905() * 1000.0F
                        + 999.0F
                        - (float)(System.currentTimeMillis() - this.recoveredField2442)
                  )
                  / 1000.0F
            );
         if (var14 <= 0) {
            this.recoveredField2453.l = true;
         }
      }

      if (this.recoveredField2443 && Mouse.isButtonDown(0) && CheatBreaker.getInstance().getGlobalSettings().recoveredField531.method_08908()) {
         float var15 = this.recoveredField2449 == 0L
            ? CheatBreaker.getInstance().getGlobalSettings().recoveredField581.method_08905()
            : (
                  CheatBreaker.getInstance().getGlobalSettings().recoveredField581.method_08905() * 1000.0F
                     + 999.0F
                     - (float)(System.currentTimeMillis() - this.recoveredField2449)
               )
               / 1000.0F;
         this.n.get(0).j = this.recoveredField2447 + " (" + (int)var15 + ")";
         if (var15 < 1.0F && var15 > 0.9F) {
            this.method_22128(this.n.get(0));
         }
      } else {
         this.n.get(0).j = this.recoveredField2447;
         this.recoveredField2449 = 0L;
         this.recoveredField2443 = false;
      }

      if (this.recoveredField2451
         && Mouse.isButtonDown(0)
         && CheatBreaker.getInstance().getGlobalSettings().recoveredField480.method_08908()
         && CheatBreaker.getInstance().getGlobalSettings().recoveredField497.method_08908()) {
         float var16 = this.recoveredField2450 == 0L
            ? CheatBreaker.getInstance().getGlobalSettings().recoveredField551.method_08905()
            : (
                  CheatBreaker.getInstance().getGlobalSettings().recoveredField551.method_08905() * 1000.0F
                     + 999.0F
                     - (float)(System.currentTimeMillis() - this.recoveredField2450)
               )
               / 1000.0F;
         this.recoveredField2453.j = "Mods (" + (int)var16 + ")";
         if ((int)var16 == 0) {
            this.j.displayGuiScreen(new CBModulesGui());
         }
      } else {
         this.recoveredField2453.j = "Mods";
         this.recoveredField2450 = 0L;
         this.recoveredField2451 = false;
      }

      if (var10) {
         if (!this.recoveredField2448.method_21217()) {
            this.recoveredField2448.method_20200();
         }

         this.recoveredField2448.method_21234();
         a(this.l / 2 - 100, this.m / 4 + 128, this.l / 2 + 100, this.m / 4 + 142, 1862270976);
         a(
            this.l / 2 - 100,
            this.m / 4 + 128,
            this.l / 2 + 100,
            this.m / 4 + 142,
            new Color(1.0F, 0.15F, 0.15F, 0.65F * this.recoveredField2448.method_21227()).getRGB()
         );
         CheatBreaker.getInstance()
            .recoveredField1589
            .drawCenteredString("Some login services might be offline".toUpperCase(), this.l / 2, this.m / 4 + 130, -1);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void method_22127(double var1, double var3) {
      try {
         if (!this.recoveredField2454.method_21217()) {
            this.recoveredField2454.method_20200();
            this.recoveredField2454.method_21234();
         }

         float var5 = 18.0F;
         double var6 = var1 / 2.0 - var5;
         double var8 = this.n.size() > 2 ? this.n.get(1).i - var5 - 32.0F : -100.0;
         GL11.glPushMatrix();
         GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
         GL11.glTranslatef((float)var6 + 1.0F, (float)var8 + 1.0F, 1.0F);
         GL11.glTranslatef(var5, var5, var5);
         GL11.glRotatef(180.0F * this.recoveredField2454.method_21227(), 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-var5, -var5, -var5);
         RenderUtil.method_22063(this.recoveredField2446, var5, 0.0F, 0.0F);
         GL11.glPopMatrix();
         RenderUtil.method_22063(this.recoveredField2444, var5, (float)var6 + 1.0F, (float)var8 + 1.0F);
         GL11.glPushMatrix();
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glTranslatef((float)var6, (float)var8, 1.0F);
         GL11.glTranslatef(var5, var5, var5);
         GL11.glRotatef(180.0F * this.recoveredField2454.method_21227(), 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-var5, -var5, -var5);
         RenderUtil.method_22063(this.recoveredField2446, var5, 0.0F, 0.0F);
         GL11.glPopMatrix();
         RenderUtil.method_22063(this.recoveredField2444, var5, (float)var6, (float)var8);
      } catch (Exception var10) {
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      this.recoveredField2451 = false;
      switch (var1.k) {
         case 0:
            this.j.displayGuiScreen(new GuiOptions(this, this.j.gameSettings));
            break;
         case 1:
            boolean var2 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField555.getValue().equals("Multiplayer") && this.j.isSingleplayer();
            boolean var3 = !CheatBreaker.getInstance().getGlobalSettings().recoveredField555.getValue().equals("Singleplayer") && !this.j.isSingleplayer();
            if (!CheatBreaker.getInstance().getGlobalSettings().recoveredField531.method_08908() || !var2 && !var3) {
               this.method_22128(var1);
            } else {
               this.recoveredField2443 = true;
               this.recoveredField2449 = System.currentTimeMillis();
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
            if (CheatBreaker.getInstance().getGlobalSettings().recoveredField480.method_08908()
               && CheatBreaker.getInstance().getGlobalSettings().recoveredField497.method_08908()) {
               this.recoveredField2451 = true;
               this.recoveredField2450 = System.currentTimeMillis();
            } else {
               this.j.displayGuiScreen(new CBModulesGui());
            }
            break;
         case 16:
            this.j.displayGuiScreen(new GuiMultiplayer(this));
      }
   }

   public GuiIngameMenu() {
      this.recoveredField2446 = new ResourceLocation("client/logo_255_outer.png");
      this.recoveredField2444 = new ResourceLocation("client/logo_108_inner.png");
      this.recoveredField2454 = new CosineFade(4000L);
      this.recoveredField2451 = false;
      this.recoveredField2443 = false;
      this.recoveredField2445 = false;
      this.recoveredField2448 = new CosineFade(1500L);
   }
}
