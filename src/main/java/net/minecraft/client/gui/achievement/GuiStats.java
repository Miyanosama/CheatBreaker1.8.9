package net.minecraft.client.gui.achievement;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatCrafting;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class GuiStats extends GuiScreen implements IProgressMeter {
   public StatFileWriter field_146546_t;
   public GuiStats.StatsBlock blockStats;
   public GuiScreen parentScreen;
   public GuiStats.StatsGeneral generalStats;
   public boolean doesGuiPauseGame;
   public GuiSlot displaySlot;
   public GuiStats.StatsItem itemStats;
   public GuiStats.StatsMobsList mobStats;
   public String screenTitle = "Select world";

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 0) {
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1.k == 1) {
            this.displaySlot = this.generalStats;
         } else if (var1.k == 3) {
            this.displaySlot = this.itemStats;
         } else if (var1.k == 2) {
            this.displaySlot = this.blockStats;
         } else if (var1.k == 4) {
            this.displaySlot = this.mobStats;
         } else {
            this.displaySlot.actionPerformed(var1);
         }
      }
   }

   public void drawStatsScreen(int var1, int var2, Item var3) {
      this.drawButtonBackground(var1 + 1, var2 + 1);
      GlStateManager.enableRescaleNormal();
      RenderHelper.enableGUIStandardItemLighting();
      this.k.renderItemIntoGUI(new ItemStack(var3, 1, 0), var1 + 2, var2 + 2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
   }

   public void drawButtonBackground(int var1, int var2) {
      this.drawSprite(var1, var2, 0, 0);
   }

   public void func_175366_f() {
      this.generalStats = new GuiStats.StatsGeneral(this.j);
      this.generalStats.registerScrollButtons(1, 1);
      this.itemStats = new GuiStats.StatsItem(this.j);
      this.itemStats.registerScrollButtons(1, 1);
      this.blockStats = new GuiStats.StatsBlock(this.j);
      this.blockStats.registerScrollButtons(1, 1);
      this.mobStats = new GuiStats.StatsMobsList(this.j);
      this.mobStats.registerScrollButtons(1, 1);
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("gui.stats");
      this.doesGuiPauseGame = true;
      this.j.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      if (this.displaySlot != null) {
         this.displaySlot.handleMouseInput();
      }
   }

   public GuiStats(GuiScreen var1, StatFileWriter var2) {
      this.doesGuiPauseGame = true;
      this.parentScreen = var1;
      this.field_146546_t = var2;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.doesGuiPauseGame) {
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
         this.displaySlot.a(var1, var2, var3);
         this.drawCenteredString(this.q, this.screenTitle, this.l / 2, 20, 16777215);
         super.drawScreen(var1, var2, var3);
      }
   }

   @Override
   public void doneLoading() {
      if (this.doesGuiPauseGame) {
         this.func_175366_f();
         this.createButtons();
         this.displaySlot = this.generalStats;
         this.doesGuiPauseGame = false;
      }
   }

   public void createButtons() {
      this.n.add(new GuiButton(0, this.l / 2 + 4, this.m - 28, 150, 20, I18n.format("gui.done")));
      this.n.add(new GuiButton(1, this.l / 2 - 160, this.m - 52, 80, 20, I18n.format("stat.generalButton")));
      GuiButton var1;
      this.n.add(var1 = new GuiButton(2, this.l / 2 - 80, this.m - 52, 80, 20, I18n.format("stat.blocksButton")));
      GuiButton var2;
      this.n.add(var2 = new GuiButton(3, this.l / 2, this.m - 52, 80, 20, I18n.format("stat.itemsButton")));
      GuiButton var3;
      this.n.add(var3 = new GuiButton(4, this.l / 2 + 80, this.m - 52, 80, 20, I18n.format("stat.mobsButton")));
      if (this.blockStats.getSize() == 0) {
         var1.l = false;
      }

      if (this.itemStats.getSize() == 0) {
         var2.l = false;
      }

      if (this.mobStats.getSize() == 0) {
         var3.l = false;
      }
   }

   @Override
   public boolean b_() {
      return !this.doesGuiPauseGame;
   }

   public void drawSprite(int var1, int var2, int var3, int var4) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      byte var7 = 18;
      byte var8 = 18;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var1 + 0, var2 + 18, recoveredField2942).tex((var3 + 0) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 18, recoveredField2942).tex((var3 + 18) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 0, recoveredField2942).tex((var3 + 18) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var10.pos(var1 + 0, var2 + 0, recoveredField2942).tex((var3 + 0) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var9.draw();
   }

   public abstract class Stats extends GuiSlot {
      public List<StatCrafting> w;
      public int y;
      public Comparator<StatCrafting> x;
      public int v = -1;
      public int z;

      public void func_148209_a(StatBase var1, int var2, int var3, boolean var4) {
         if (var1 != null) {
            String var5 = var1.format(GuiStats.this.field_146546_t.a(var1));
            GuiStats.this.drawString(GuiStats.this.q, var5, var2 - GuiStats.this.q.getStringWidth(var5), var3 + 5, var4 ? 16777215 : 9474192);
         } else {
            String var6 = "-";
            GuiStats.this.drawString(GuiStats.this.q, var6, var2 - GuiStats.this.q.getStringWidth(var6), var3 + 5, var4 ? 16777215 : 9474192);
         }
      }

      @Override
      public boolean isSelected(int var1) {
         return false;
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
      }

      @Override
      public void drawBackground() {
         GuiStats.this.drawDefaultBackground();
      }

      @Override
      public int getSize() {
         return this.w.size();
      }

      public Stats(Minecraft var2) {
         super(var2, GuiStats.this.l, GuiStats.this.m, 32, GuiStats.this.m - 64, 20);
         this.y = -1;
         this.setShowSelectionBox(false);
         this.setHasListHeader(true, 20);
      }

      public void func_148212_h(int var1) {
         if (var1 != this.y) {
            this.y = var1;
            this.z = -1;
         } else if (this.z == -1) {
            this.z = 1;
         } else {
            this.y = -1;
            this.z = 0;
         }

         Collections.sort(this.w, this.x);
      }

      @Override
      public void func_148132_a(int var1, int var2) {
         this.v = -1;
         if (var1 >= 79 && var1 < 115) {
            this.v = 0;
         } else if (var1 >= 129 && var1 < 165) {
            this.v = 1;
         } else if (var1 >= 179 && var1 < 215) {
            this.v = 2;
         }

         if (this.v >= 0) {
            this.func_148212_h(this.v);
            this.a.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }
      }

      public void func_148213_a(StatCrafting var1, int var2, int var3) {
         if (var1 != null) {
            Item var4 = var1.func_150959_a();
            ItemStack var5 = new ItemStack(var4);
            String var6 = var5.getUnlocalizedName();
            String var7 = ("" + I18n.format(var6 + ".name")).trim();
            if (var7.length() > 0) {
               int var8 = var2 + 12;
               int var9 = var3 - 12;
               int var10 = GuiStats.this.q.getStringWidth(var7);
               GuiStats.this.drawGradientRect(var8 - 3, var9 - 3, var8 + var10 + 3, var9 + 8 + 3, -1073741824, -1073741824);
               GuiStats.this.q.drawStringWithShadow(var7, var8, var9, -1);
            }
         }
      }

      public StatCrafting c(int var1) {
         return this.w.get(var1);
      }

      @Override
      public void func_148142_b(int var1, int var2) {
         if (var2 >= this.d && var2 <= this.bottom) {
            int var3 = this.c(var1, var2);
            int var4 = this.b / 2 - 92 - 16;
            if (var3 >= 0) {
               if (var1 < var4 + 40 || var1 > var4 + 40 + 20) {
                  return;
               }

               StatCrafting var11 = this.c(var3);
               this.func_148213_a(var11, var1, var2);
            } else {
               String var5 = "";
               if (var1 >= var4 + 115 - 18 && var1 <= var4 + 115) {
                  var5 = this.func_148210_b(0);
               } else if (var1 >= var4 + 165 - 18 && var1 <= var4 + 165) {
                  var5 = this.func_148210_b(1);
               } else {
                  if (var1 < var4 + 215 - 18 || var1 > var4 + 215) {
                     return;
                  }

                  var5 = this.func_148210_b(2);
               }

               var5 = ("" + I18n.format(var5)).trim();
               if (var5.length() > 0) {
                  int var6 = var1 + 12;
                  int var7 = var2 - 12;
                  int var8 = GuiStats.this.q.getStringWidth(var5);
                  GuiStats.this.drawGradientRect(var6 - 3, var7 - 3, var6 + var8 + 3, var7 + 8 + 3, -1073741824, -1073741824);
                  GuiStats.this.q.drawStringWithShadow(var5, var6, var7, -1);
               }
            }
         }
      }

      public abstract String func_148210_b(int var1);

      @Override
      public void drawListHeader(int var1, int var2, Tessellator var3) {
         if (!Mouse.isButtonDown(0)) {
            this.v = -1;
         }

         if (this.v == 0) {
            GuiStats.this.drawSprite(var1 + 115 - 18, var2 + 1, 0, 0);
         } else {
            GuiStats.this.drawSprite(var1 + 115 - 18, var2 + 1, 0, 18);
         }

         if (this.v == 1) {
            GuiStats.this.drawSprite(var1 + 165 - 18, var2 + 1, 0, 0);
         } else {
            GuiStats.this.drawSprite(var1 + 165 - 18, var2 + 1, 0, 18);
         }

         if (this.v == 2) {
            GuiStats.this.drawSprite(var1 + 215 - 18, var2 + 1, 0, 0);
         } else {
            GuiStats.this.drawSprite(var1 + 215 - 18, var2 + 1, 0, 18);
         }

         if (this.y != -1) {
            short var4 = 79;
            byte var5 = 18;
            if (this.y == 1) {
               var4 = 129;
            } else if (this.y == 2) {
               var4 = 179;
            }

            if (this.z == 1) {
               var5 = 36;
            }

            GuiStats.this.drawSprite(var1 + var4, var2 + 1, var5, 0);
         }
      }
   }

   public class StatsBlock extends GuiStats.Stats {
      @Override
      public String func_148210_b(int var1) {
         return var1 == 0 ? "stat.crafted" : (var1 == 1 ? "stat.used" : "stat.mined");
      }

      @Override
      public void drawListHeader(int var1, int var2, Tessellator var3) {
         super.drawListHeader(var1, var2, var3);
         if (this.v == 0) {
            GuiStats.this.drawSprite(var1 + 115 - 18 + 1, var2 + 1 + 1, 18, 18);
         } else {
            GuiStats.this.drawSprite(var1 + 115 - 18, var2 + 1, 18, 18);
         }

         if (this.v == 1) {
            GuiStats.this.drawSprite(var1 + 165 - 18 + 1, var2 + 1 + 1, 36, 18);
         } else {
            GuiStats.this.drawSprite(var1 + 165 - 18, var2 + 1, 36, 18);
         }

         if (this.v == 2) {
            GuiStats.this.drawSprite(var1 + 215 - 18 + 1, var2 + 1 + 1, 54, 18);
         } else {
            GuiStats.this.drawSprite(var1 + 215 - 18, var2 + 1, 54, 18);
         }
      }

      public StatsBlock(Minecraft var2) {
         super(var2);
         this.w = Lists.newArrayList();

         for (StatCrafting var4 : StatList.objectMineStats) {
            boolean var5 = false;
            int var6 = Item.getIdFromItem(var4.func_150959_a());
            if (GuiStats.this.field_146546_t.a(var4) > 0) {
               var5 = true;
            } else if (StatList.objectUseStats[var6] != null && GuiStats.this.field_146546_t.a(StatList.objectUseStats[var6]) > 0) {
               var5 = true;
            } else if (StatList.objectCraftStats[var6] != null && GuiStats.this.field_146546_t.a(StatList.objectCraftStats[var6]) > 0) {
               var5 = true;
            }

            if (var5) {
               this.w.add(var4);
            }
         }

         this.x = new Comparator<StatCrafting>() {
            public int compare(StatCrafting var1, StatCrafting var2x) {
               int var3 = Item.getIdFromItem(var1.func_150959_a());
               int var4 = Item.getIdFromItem(var2x.func_150959_a());
               StatBase var5x = null;
               StatBase var6x = null;
               if (StatsBlock.this.y == 2) {
                  var5x = StatList.mineBlockStatArray[var3];
                  var6x = StatList.mineBlockStatArray[var4];
               } else if (StatsBlock.this.y == 0) {
                  var5x = StatList.objectCraftStats[var3];
                  var6x = StatList.objectCraftStats[var4];
               } else if (StatsBlock.this.y == 1) {
                  var5x = StatList.objectUseStats[var3];
                  var6x = StatList.objectUseStats[var4];
               }

               if (var5x != null || var6x != null) {
                  if (var5x == null) {
                     return 1;
                  }

                  if (var6x == null) {
                     return -1;
                  }

                  int var7 = GuiStats.this.field_146546_t.a(var5x);
                  int var8 = GuiStats.this.field_146546_t.a(var6x);
                  if (var7 != var8) {
                     return (var7 - var8) * StatsBlock.this.z;
                  }
               }

               return var3 - var4;
            }
         };
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         StatCrafting var7 = this.c(var1);
         Item var8 = var7.func_150959_a();
         GuiStats.this.drawStatsScreen(var2 + 40, var3, var8);
         int var9 = Item.getIdFromItem(var8);
         this.func_148209_a(StatList.objectCraftStats[var9], var2 + 115, var3, var1 % 2 == 0);
         this.func_148209_a(StatList.objectUseStats[var9], var2 + 165, var3, var1 % 2 == 0);
         this.func_148209_a(var7, var2 + 215, var3, var1 % 2 == 0);
      }
   }

   public class StatsGeneral extends GuiSlot {
      @Override
      public int getSize() {
         return StatList.generalStats.size();
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
      }

      @Override
      public void drawBackground() {
         GuiStats.this.drawDefaultBackground();
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         StatBase var7 = StatList.generalStats.get(var1);
         GuiStats.this.drawString(GuiStats.this.q, var7.getStatName().getUnformattedText(), var2 + 2, var3 + 1, var1 % 2 == 0 ? 16777215 : 9474192);
         String var8 = var7.format(GuiStats.this.field_146546_t.a(var7));
         GuiStats.this.drawString(GuiStats.this.q, var8, var2 + 2 + 213 - GuiStats.this.q.getStringWidth(var8), var3 + 1, var1 % 2 == 0 ? 16777215 : 9474192);
      }

      @Override
      public boolean isSelected(int var1) {
         return false;
      }

      public StatsGeneral(Minecraft var2) {
         super(var2, GuiStats.this.l, GuiStats.this.m, 32, GuiStats.this.m - 64, 10);
         this.setShowSelectionBox(false);
      }

      @Override
      public int getContentHeight() {
         return this.getSize() * 10;
      }
   }

   public class StatsItem extends GuiStats.Stats {
      public StatsItem(Minecraft var2) {
         super(var2);
         this.w = Lists.newArrayList();

         for (StatCrafting var4 : StatList.itemStats) {
            boolean var5 = false;
            int var6 = Item.getIdFromItem(var4.func_150959_a());
            if (GuiStats.this.field_146546_t.a(var4) > 0) {
               var5 = true;
            } else if (StatList.objectBreakStats[var6] != null && GuiStats.this.field_146546_t.a(StatList.objectBreakStats[var6]) > 0) {
               var5 = true;
            } else if (StatList.objectCraftStats[var6] != null && GuiStats.this.field_146546_t.a(StatList.objectCraftStats[var6]) > 0) {
               var5 = true;
            }

            if (var5) {
               this.w.add(var4);
            }
         }

         this.x = new Comparator<StatCrafting>() {
            public int compare(StatCrafting var1, StatCrafting var2x) {
               int var3 = Item.getIdFromItem(var1.func_150959_a());
               int var4 = Item.getIdFromItem(var2x.func_150959_a());
               StatBase var5x = null;
               StatBase var6x = null;
               if (StatsItem.this.y == 0) {
                  var5x = StatList.objectBreakStats[var3];
                  var6x = StatList.objectBreakStats[var4];
               } else if (StatsItem.this.y == 1) {
                  var5x = StatList.objectCraftStats[var3];
                  var6x = StatList.objectCraftStats[var4];
               } else if (StatsItem.this.y == 2) {
                  var5x = StatList.objectUseStats[var3];
                  var6x = StatList.objectUseStats[var4];
               }

               if (var5x != null || var6x != null) {
                  if (var5x == null) {
                     return 1;
                  }

                  if (var6x == null) {
                     return -1;
                  }

                  int var7 = GuiStats.this.field_146546_t.a(var5x);
                  int var8 = GuiStats.this.field_146546_t.a(var6x);
                  if (var7 != var8) {
                     return (var7 - var8) * StatsItem.this.z;
                  }
               }

               return var3 - var4;
            }
         };
      }

      @Override
      public String func_148210_b(int var1) {
         return var1 == 1 ? "stat.crafted" : (var1 == 2 ? "stat.used" : "stat.depleted");
      }

      @Override
      public void drawListHeader(int var1, int var2, Tessellator var3) {
         super.drawListHeader(var1, var2, var3);
         if (this.v == 0) {
            GuiStats.this.drawSprite(var1 + 115 - 18 + 1, var2 + 1 + 1, 72, 18);
         } else {
            GuiStats.this.drawSprite(var1 + 115 - 18, var2 + 1, 72, 18);
         }

         if (this.v == 1) {
            GuiStats.this.drawSprite(var1 + 165 - 18 + 1, var2 + 1 + 1, 18, 18);
         } else {
            GuiStats.this.drawSprite(var1 + 165 - 18, var2 + 1, 18, 18);
         }

         if (this.v == 2) {
            GuiStats.this.drawSprite(var1 + 215 - 18 + 1, var2 + 1 + 1, 36, 18);
         } else {
            GuiStats.this.drawSprite(var1 + 215 - 18, var2 + 1, 36, 18);
         }
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         StatCrafting var7 = this.c(var1);
         Item var8 = var7.func_150959_a();
         GuiStats.this.drawStatsScreen(var2 + 40, var3, var8);
         int var9 = Item.getIdFromItem(var8);
         this.func_148209_a(StatList.objectBreakStats[var9], var2 + 115, var3, var1 % 2 == 0);
         this.func_148209_a(StatList.objectCraftStats[var9], var2 + 165, var3, var1 % 2 == 0);
         this.func_148209_a(var7, var2 + 215, var3, var1 % 2 == 0);
      }
   }

   public class StatsMobsList extends GuiSlot {
      public List<EntityList.EntityEggInfo> field_148222_l = Lists.newArrayList();

      @Override
      public int getContentHeight() {
         return this.getSize() * GuiStats.this.q.FONT_HEIGHT * 4;
      }

      @Override
      public void elementClicked(int var1, boolean var2, int var3, int var4) {
      }

      @Override
      public int getSize() {
         return this.field_148222_l.size();
      }

      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         EntityList.EntityEggInfo var7 = this.field_148222_l.get(var1);
         String var8 = I18n.format("entity." + EntityList.getStringFromID(var7.spawnedID) + ".name");
         int var9 = GuiStats.this.field_146546_t.a(var7.field_151512_d);
         int var10 = GuiStats.this.field_146546_t.a(var7.field_151513_e);
         String var11 = I18n.format("stat.entityKills", var9, var8);
         String var12 = I18n.format("stat.entityKilledBy", var8, var10);
         if (var9 == 0) {
            var11 = I18n.format("stat.entityKills.none", var8);
         }

         if (var10 == 0) {
            var12 = I18n.format("stat.entityKilledBy.none", var8);
         }

         GuiStats.this.drawString(GuiStats.this.q, var8, var2 + 2 - 10, var3 + 1, 16777215);
         GuiStats.this.drawString(GuiStats.this.q, var11, var2 + 2, var3 + 1 + GuiStats.this.q.FONT_HEIGHT, var9 == 0 ? 6316128 : 9474192);
         GuiStats.this.drawString(GuiStats.this.q, var12, var2 + 2, var3 + 1 + GuiStats.this.q.FONT_HEIGHT * 2, var10 == 0 ? 6316128 : 9474192);
      }

      @Override
      public boolean isSelected(int var1) {
         return false;
      }

      @Override
      public void drawBackground() {
         GuiStats.this.drawDefaultBackground();
      }

      public StatsMobsList(Minecraft var2) {
         super(var2, GuiStats.this.l, GuiStats.this.m, 32, GuiStats.this.m - 64, GuiStats.this.q.FONT_HEIGHT * 4);
         this.setShowSelectionBox(false);

         for (EntityList.EntityEggInfo var4 : EntityList.entityEggs.values()) {
            if (GuiStats.this.field_146546_t.a(var4.field_151512_d) > 0 || GuiStats.this.field_146546_t.a(var4.field_151513_e) > 0) {
               this.field_148222_l.add(var4);
            }
         }
      }
   }
}
