package net.minecraft.client.gui.achievement;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiAchievements extends GuiScreen implements IProgressMeter {
   public int recoveredField137;
   public static int field_146572_y = AchievementList.minDisplayColumn * 24 - 112;
   public int field_146555_f = 256;
   public StatFileWriter statFileWriter;
   public float field_146570_r;
   public double field_146569_s;
   public double field_146573_x;
   public double field_146565_w;
   public int recoveredField138;
   public static int field_146571_z = AchievementList.minDisplayRow * 24 - 112;
   public int recoveredField139;
   public static int field_146559_A = AchievementList.maxDisplayColumn * 24 - 77;
   public static int field_146560_B = AchievementList.maxDisplayRow * 24 - 77;
   public static ResourceLocation ACHIEVEMENT_BACKGROUND = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   public double field_146568_t;
   public GuiScreen parentScreen;
   public boolean loadingAchievements;
   public int field_146557_g = 202;
   public double field_146566_v;
   public double field_146567_u;

   public void drawTitle() {
      int var1 = (this.l - this.field_146555_f) / 2;
      int var2 = (this.m - this.field_146557_g) / 2;
      this.q.drawString(I18n.format("gui.achievements"), var1 + 15, var2 + 5, 4210752);
   }

   public GuiAchievements(GuiScreen var1, StatFileWriter var2) {
      this.field_146570_r = 1.0F;
      this.loadingAchievements = true;
      this.parentScreen = var1;
      this.statFileWriter = var2;
      short var3 = 141;
      short var4 = 141;
      this.field_146569_s = this.field_146567_u = this.field_146565_w = AchievementList.openInventory.displayColumn * 24 - var3 / 2 - 12;
      this.field_146568_t = this.field_146566_v = this.field_146573_x = AchievementList.openInventory.displayRow * 24 - var4 / 2;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (!this.loadingAchievements && var1.k == 1) {
         this.j.displayGuiScreen(this.parentScreen);
      }
   }

   public TextureAtlasSprite func_175371_a(Block var1) {
      return Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(var1.getDefaultState());
   }

   @Override
   public void updateScreen() {
      if (!this.loadingAchievements) {
         this.field_146569_s = this.field_146567_u;
         this.field_146568_t = this.field_146566_v;
         double var1 = this.field_146565_w - this.field_146567_u;
         double var3 = this.field_146573_x - this.field_146566_v;
         if (var1 * var1 + var3 * var3 < 4.0) {
            this.field_146567_u += var1;
            this.field_146566_v += var3;
         } else {
            this.field_146567_u += var1 * 0.85;
            this.field_146566_v += var3 * 0.85;
         }
      }
   }

   @Override
   public boolean b_() {
      return !this.loadingAchievements;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.loadingAchievements) {
         this.drawDefaultBackground();
         this.drawCenteredString(this.q, I18n.format("multiplayer.downloadingStats"), this.l / 2, this.m / 2, 16777215);
         this.drawCenteredString(
            this.q,
            lanSearchStates[(int)(Minecraft.getSystemTime() / 150L % lanSearchStates.length)],
            this.l / 2,
            this.m / 2 + this.q.FONT_HEIGHT * 2,
            16777215
         );
      } else {
         if (Mouse.isButtonDown(0)) {
            int var4 = (this.l - this.field_146555_f) / 2;
            int var5 = (this.m - this.field_146557_g) / 2;
            int var6 = var4 + 8;
            int var7 = var5 + 17;
            if ((this.recoveredField137 == 0 || this.recoveredField137 == 1) && var1 >= var6 && var1 < var6 + 224 && var2 >= var7 && var2 < var7 + 155) {
               if (this.recoveredField137 == 0) {
                  this.recoveredField137 = 1;
               } else {
                  this.field_146567_u = this.field_146567_u - (var1 - this.recoveredField138) * this.field_146570_r;
                  this.field_146566_v = this.field_146566_v - (var2 - this.recoveredField139) * this.field_146570_r;
                  this.field_146565_w = this.field_146569_s = this.field_146567_u;
                  this.field_146573_x = this.field_146568_t = this.field_146566_v;
               }

               this.recoveredField138 = var1;
               this.recoveredField139 = var2;
            }
         } else {
            this.recoveredField137 = 0;
         }

         int var11 = Mouse.getDWheel();
         float var12 = this.field_146570_r;
         if (var11 < 0) {
            this.field_146570_r += 0.25F;
         } else if (var11 > 0) {
            this.field_146570_r -= 0.25F;
         }

         this.field_146570_r = MathHelper.clamp_float(this.field_146570_r, 1.0F, 2.0F);
         if (this.field_146570_r != var12) {
            float var13 = var12 - this.field_146570_r;
            float var14 = var12 * this.field_146555_f;
            float var8 = var12 * this.field_146557_g;
            float var9 = this.field_146570_r * this.field_146555_f;
            float var10 = this.field_146570_r * this.field_146557_g;
            this.field_146567_u -= (var9 - var14) * 0.5F;
            this.field_146566_v -= (var10 - var8) * 0.5F;
            this.field_146565_w = this.field_146569_s = this.field_146567_u;
            this.field_146573_x = this.field_146568_t = this.field_146566_v;
         }

         if (this.field_146565_w < field_146572_y) {
            this.field_146565_w = field_146572_y;
         }

         if (this.field_146573_x < field_146571_z) {
            this.field_146573_x = field_146571_z;
         }

         if (this.field_146565_w >= field_146559_A) {
            this.field_146565_w = field_146559_A - 1;
         }

         if (this.field_146573_x >= field_146560_B) {
            this.field_146573_x = field_146560_B - 1;
         }

         this.drawDefaultBackground();
         this.method_25833(var1, var2, var3);
         GlStateManager.disableLighting();
         GlStateManager.disableDepth();
         this.drawTitle();
         GlStateManager.enableLighting();
         GlStateManager.enableDepth();
      }
   }

   @Override
   public void initGui() {
      this.j.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
      this.n.clear();
      this.n.add(new GuiOptionButton(1, this.l / 2 + 24, this.m / 2 + 74, 80, 20, I18n.format("gui.done")));
   }

   public void method_25833(int var1, int var2, float var3) {
      int var4 = MathHelper.floor_double(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * var3);
      int var5 = MathHelper.floor_double(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * var3);
      if (var4 < field_146572_y) {
         var4 = field_146572_y;
      }

      if (var5 < field_146571_z) {
         var5 = field_146571_z;
      }

      if (var4 >= field_146559_A) {
         var4 = field_146559_A - 1;
      }

      if (var5 >= field_146560_B) {
         var5 = field_146560_B - 1;
      }

      int var6 = (this.l - this.field_146555_f) / 2;
      int var7 = (this.m - this.field_146557_g) / 2;
      int var8 = var6 + 16;
      int var9 = var7 + 17;
      recoveredField2942 = 0.0F;
      GlStateManager.depthFunc(518);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var8, (float)var9, -200.0F);
      GlStateManager.scale(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
      GlStateManager.enableTexture2D();
      GlStateManager.disableLighting();
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableColorMaterial();
      int var10 = var4 + 288 >> 4;
      int var11 = var5 + 288 >> 4;
      int var12 = (var4 + 288) % 16;
      int var13 = (var5 + 288) % 16;
      byte var14 = 4;
      byte var15 = 8;
      byte var16 = 10;
      byte var17 = 22;
      byte var18 = 37;
      Random var19 = new Random();
      float var20 = 16.0F / this.field_146570_r;
      float var21 = 16.0F / this.field_146570_r;

      for (int var22 = 0; var22 * var20 - var13 < 155.0F; var22++) {
         float var23 = 0.6F - (var11 + var22) / 25.0F * 0.3F;
         GlStateManager.color(var23, var23, var23, 1.0F);

         for (int var24 = 0; var24 * var21 - var12 < 224.0F; var24++) {
            var19.setSeed(this.j.getSession().getPlayerID().hashCode() + var10 + var24 + (var11 + var22) * 16);
            int var25 = var19.nextInt(1 + var11 + var22) + (var11 + var22) / 2;
            TextureAtlasSprite var26 = this.func_175371_a(Blocks.sand);
            if (var25 > 37 || var11 + var22 == 35) {
               Block var27 = Blocks.bedrock;
               var26 = this.func_175371_a(var27);
            } else if (var25 == 22) {
               if (var19.nextInt(2) == 0) {
                  var26 = this.func_175371_a(Blocks.diamond_ore);
               } else {
                  var26 = this.func_175371_a(Blocks.redstone_ore);
               }
            } else if (var25 == 10) {
               var26 = this.func_175371_a(Blocks.iron_ore);
            } else if (var25 == 8) {
               var26 = this.func_175371_a(Blocks.coal_ore);
            } else if (var25 > 4) {
               var26 = this.func_175371_a(Blocks.stone);
            } else if (var25 > 0) {
               var26 = this.func_175371_a(Blocks.dirt);
            }

            this.j.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            this.drawTexturedModalRect(var24 * 16 - var12, var22 * 16 - var13, var26, 16, 16);
         }
      }

      GlStateManager.enableDepth();
      GlStateManager.depthFunc(515);
      this.j.getTextureManager().bindTexture(ACHIEVEMENT_BACKGROUND);

      for (int var33 = 0; var33 < AchievementList.achievementList.size(); var33++) {
         Achievement var35 = AchievementList.achievementList.get(var33);
         if (var35.parentAchievement != null) {
            int var37 = var35.displayColumn * 24 - var4 + 11;
            int var39 = var35.displayRow * 24 - var5 + 11;
            int var42 = var35.parentAchievement.displayColumn * 24 - var4 + 11;
            int var45 = var35.parentAchievement.displayRow * 24 - var5 + 11;
            boolean var28 = this.statFileWriter.hasAchievementUnlocked(var35);
            boolean var29 = this.statFileWriter.canUnlockAchievement(var35);
            int var30 = this.statFileWriter.func_150874_c(var35);
            if (var30 <= 4) {
               int var31 = -16777216;
               if (var28) {
                  var31 = -6250336;
               } else if (var29) {
                  var31 = -16711936;
               }

               this.drawHorizontalLine(var37, var42, var39, var31);
               this.drawVerticalLine(var42, var39, var45, var31);
               if (var37 > var42) {
                  this.drawTexturedModalRect(var37 - 11 - 7, var39 - 5, 114, 234, 7, 11);
               } else if (var37 < var42) {
                  this.drawTexturedModalRect(var37 + 11, var39 - 5, 107, 234, 7, 11);
               } else if (var39 > var45) {
                  this.drawTexturedModalRect(var37 - 5, var39 - 11 - 7, 96, 234, 11, 7);
               } else if (var39 < var45) {
                  this.drawTexturedModalRect(var37 - 5, var39 + 11, 96, 241, 11, 7);
               }
            }
         }
      }

      Achievement var34 = null;
      float var36 = (var1 - var8) * this.field_146570_r;
      float var38 = (var2 - var9) * this.field_146570_r;
      RenderHelper.enableGUIStandardItemLighting();
      GlStateManager.disableLighting();
      GlStateManager.enableRescaleNormal();
      GlStateManager.enableColorMaterial();

      for (int var40 = 0; var40 < AchievementList.achievementList.size(); var40++) {
         Achievement var43 = AchievementList.achievementList.get(var40);
         int var46 = var43.displayColumn * 24 - var4;
         int var48 = var43.displayRow * 24 - var5;
         if (var46 >= -24 && var48 >= -24 && var46 <= 224.0F * this.field_146570_r && var48 <= 155.0F * this.field_146570_r) {
            int var50 = this.statFileWriter.func_150874_c(var43);
            if (this.statFileWriter.hasAchievementUnlocked(var43)) {
               float var52 = 0.75F;
               GlStateManager.color(var52, var52, var52, 1.0F);
            } else if (this.statFileWriter.canUnlockAchievement(var43)) {
               float var53 = 1.0F;
               GlStateManager.color(var53, var53, var53, 1.0F);
            } else if (var50 < 3) {
               float var54 = 0.3F;
               GlStateManager.color(var54, var54, var54, 1.0F);
            } else if (var50 == 3) {
               float var55 = 0.2F;
               GlStateManager.color(var55, var55, var55, 1.0F);
            } else {
               if (var50 != 4) {
                  continue;
               }

               float var56 = 0.1F;
               GlStateManager.color(var56, var56, var56, 1.0F);
            }

            this.j.getTextureManager().bindTexture(ACHIEVEMENT_BACKGROUND);
            if (var43.getSpecial()) {
               this.drawTexturedModalRect(var46 - 2, var48 - 2, 26, 202, 26, 26);
            } else {
               this.drawTexturedModalRect(var46 - 2, var48 - 2, 0, 202, 26, 26);
            }

            if (!this.statFileWriter.canUnlockAchievement(var43)) {
               float var57 = 0.1F;
               GlStateManager.color(var57, var57, var57, 1.0F);
               this.k.isNotRenderingEffectsInGUI(false);
            }

            GlStateManager.enableLighting();
            GlStateManager.enableCull();
            this.k.renderItemAndEffectIntoGUI(var43.theItemStack, var46 + 3, var48 + 3);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.disableLighting();
            if (!this.statFileWriter.canUnlockAchievement(var43)) {
               this.k.isNotRenderingEffectsInGUI(true);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            if (var36 >= var46 && var36 <= var46 + 22 && var38 >= var48 && var38 <= var48 + 22) {
               var34 = var43;
            }
         }
      }

      GlStateManager.disableDepth();
      GlStateManager.enableBlend();
      GlStateManager.popMatrix();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(ACHIEVEMENT_BACKGROUND);
      this.drawTexturedModalRect(var6, var7, 0, 0, this.field_146555_f, this.field_146557_g);
      recoveredField2942 = 0.0F;
      GlStateManager.depthFunc(515);
      GlStateManager.disableDepth();
      GlStateManager.enableTexture2D();
      super.drawScreen(var1, var2, var3);
      if (var34 != null) {
         String var41 = var34.getStatName().getUnformattedText();
         String var44 = var34.getDescription();
         int var47 = var1 + 12;
         int var49 = var2 - 4;
         int var51 = this.statFileWriter.func_150874_c(var34);
         if (this.statFileWriter.canUnlockAchievement(var34)) {
            int var58 = Math.max(this.q.getStringWidth(var41), 120);
            int var61 = this.q.splitStringWidth(var44, var58);
            if (this.statFileWriter.hasAchievementUnlocked(var34)) {
               var61 += 12;
            }

            this.drawGradientRect(var47 - 3, var49 - 3, var47 + var58 + 3, var49 + var61 + 3 + 12, -1073741824, -1073741824);
            this.q.drawSplitString(var44, var47, var49 + 12, var58, -6250336);
            if (this.statFileWriter.hasAchievementUnlocked(var34)) {
               this.q.drawStringWithShadow(I18n.format("achievement.taken"), var47, var49 + var61 + 4, -7302913);
            }
         } else if (var51 == 3) {
            var41 = I18n.format("achievement.unknown");
            int var59 = Math.max(this.q.getStringWidth(var41), 120);
            String var62 = new ChatComponentTranslation("achievement.requires", var34.parentAchievement.getStatName()).getUnformattedText();
            int var32 = this.q.splitStringWidth(var62, var59);
            this.drawGradientRect(var47 - 3, var49 - 3, var47 + var59 + 3, var49 + var32 + 12 + 3, -1073741824, -1073741824);
            this.q.drawSplitString(var62, var47, var49 + 12, var59, -9416624);
         } else if (var51 < 3) {
            int var60 = Math.max(this.q.getStringWidth(var41), 120);
            String var63 = new ChatComponentTranslation("achievement.requires", var34.parentAchievement.getStatName()).getUnformattedText();
            int var64 = this.q.splitStringWidth(var63, var60);
            this.drawGradientRect(var47 - 3, var49 - 3, var47 + var60 + 3, var49 + var64 + 12 + 3, -1073741824, -1073741824);
            this.q.drawSplitString(var63, var47, var49 + 12, var60, -9416624);
         } else {
            var41 = null;
         }

         if (var41 != null) {
            this.q
               .drawStringWithShadow(
                  var41,
                  var47,
                  var49,
                  this.statFileWriter.canUnlockAchievement(var34) ? (var34.getSpecial() ? -128 : -1) : (var34.getSpecial() ? -8355776 : -8355712)
               );
         }
      }

      GlStateManager.enableDepth();
      GlStateManager.enableLighting();
      RenderHelper.disableStandardItemLighting();
   }

   @Override
   public void doneLoading() {
      if (this.loadingAchievements) {
         this.loadingAchievements = false;
      }
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (var2 == this.j.gameSettings.keyBindInventory.getKeyCode()) {
         this.j.displayGuiScreen((GuiScreen)null);
         this.j.method_20340();
      } else {
         super.keyTyped(var1, var2);
      }
   }
}
