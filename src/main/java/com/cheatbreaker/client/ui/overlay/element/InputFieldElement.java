package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

public class InputFieldElement extends AbstractElement {
   public int recoveredField2623;
   public int recoveredField2624;
   public CBFontRenderer fontRenderer;
   public boolean recoveredField2625;
   public int recoveredField2626;
   public boolean recoveredField2627;
   public String recoveredField2628 = "";
   public boolean recoveredField2629;
   public String recoveredField2630;
   public int recoveredField2631;
   public int recoveredField2632 = 32;
   public int recoveredField2633;
   public int recoveredField2634;
   public boolean recoveredField2635;
   public int recoveredField2636;
   public boolean recoveredField2637;
   public int recoveredField2638;

   public int method_06026(int var1, int var2, boolean var3) {
      int var4 = var2;
      boolean var5 = var1 < 0;
      int var6 = Math.abs(var1);

      for (int var7 = 0; var7 < var6; var7++) {
         if (!var5) {
            int var8 = this.recoveredField2628.length();
            if ((var4 = this.recoveredField2628.indexOf(32, var4)) == -1) {
               var4 = var8;
            } else {
               while (var3 && var4 < var8 && this.recoveredField2628.charAt(var4) == ' ') {
                  var4++;
               }
            }
         } else {
            while (var3 && var4 > 0 && this.recoveredField2628.charAt(var4 - 1) == ' ') {
               var4--;
            }

            while (var4 > 0 && this.recoveredField2628.charAt(var4 - 1) != ' ') {
               var4--;
            }
         }
      }

      return var4;
   }

   public void method_06028(boolean var1) {
      if (var1 && !this.recoveredField2629) {
         this.recoveredField2636 = 0;
      }

      this.recoveredField2629 = var1;
      net.minecraft.client.WindowsImeSupport.focusChanged(this, this::method_06013);
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      this.method_06037(var1, var2);
   }

   public int method_06021() {
      return this.recoveredField2632;
   }

   public int method_06024(int var1) {
      return this.method_06025(var1, this.method_06033());
   }

   public boolean method_06013() {
      return this.recoveredField2629;
   }

   public void method_06016() {
      this.method_06014(0);
   }

   public boolean method_06037(char var1, int var2) {
      if (!this.recoveredField2629) {
         return false;
      } else {
         switch (var1) {
            case '\u0001':
               this.method_06012();
               this.method_06034(0);
               return true;
            case '\u0003':
               GuiScreen.setClipboardString(this.method_06029());
               return true;
            case '\u0016':
               if (this.recoveredField2627) {
                  this.method_06040(GuiScreen.getClipboardString());
               }

               return true;
            case '\u0018':
               GuiScreen.setClipboardString(this.method_06029());
               if (this.recoveredField2627) {
                  this.method_06040("");
               }

               return true;
            default:
               switch (var2) {
                  case 14:
                     if (GuiScreen.isCtrlKeyDown()) {
                        if (this.recoveredField2627) {
                           this.method_06039(-1);
                        }
                     } else if (this.recoveredField2627) {
                        this.method_06043(-1);
                     }

                     return true;
                  case 199:
                     if (GuiScreen.isShiftKeyDown()) {
                        this.method_06034(0);
                     } else {
                        this.method_06016();
                     }

                     return true;
                  case 203:
                     if (GuiScreen.isShiftKeyDown()) {
                        if (GuiScreen.isCtrlKeyDown()) {
                           this.method_06034(this.method_06025(-1, this.method_06036()));
                        } else {
                           this.method_06034(this.method_06036() - 1);
                        }
                     } else if (GuiScreen.isCtrlKeyDown()) {
                        this.method_06014(this.method_06024(-1));
                     } else {
                        this.method_06017(-1);
                     }

                     return true;
                  case 205:
                     if (GuiScreen.isShiftKeyDown()) {
                        if (GuiScreen.isCtrlKeyDown()) {
                           this.method_06034(this.method_06025(1, this.method_06036()));
                        } else {
                           this.method_06034(this.method_06036() + 1);
                        }
                     } else if (GuiScreen.isCtrlKeyDown()) {
                        this.method_06014(this.method_06024(1));
                     } else {
                        this.method_06017(1);
                     }

                     return true;
                  case 207:
                     if (GuiScreen.isShiftKeyDown()) {
                        this.method_06034(this.recoveredField2628.length());
                     } else {
                        this.method_06012();
                     }

                     return true;
                  case 211:
                     if (GuiScreen.isCtrlKeyDown()) {
                        if (this.recoveredField2627) {
                           this.method_06039(1);
                        }
                     } else if (this.recoveredField2627) {
                        this.method_06043(1);
                     }

                     return true;
                  default:
                     if (ChatAllowedCharacters.isAllowedCharacter(var1)) {
                        if (this.recoveredField2627) {
                           this.method_06040(Character.toString(var1));
                        }

                        return true;
                     } else {
                        return false;
                     }
               }
         }
      }
   }

   public void method_06039(int var1) {
      if (this.recoveredField2628.length() != 0) {
         if (this.recoveredField2638 != this.recoveredField2626) {
            this.method_06040("");
         } else {
            this.method_06043(this.method_06024(var1) - this.recoveredField2626);
         }
      }
   }

   public String getText() {
      return this.recoveredField2628;
   }

   public boolean method_06031() {
      return this.recoveredField2625;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         this.method_06028(false);
         return true;
      } else {
         if (var3 == 1 && this.a_(var1, var2)) {
            this.setText("");
         }

         boolean var5 = var1 >= this.x && var1 < this.x + this.width && var2 >= this.y && var2 < this.y + this.height;
         if (this.recoveredField2635) {
            this.method_06028(var5);
         }

         if (this.recoveredField2629 && var3 == 0) {
            float var7 = var1 - this.x;
            if (this.recoveredField2625) {
               var7 -= 4.0F;
            }

            String var8 = this.fontRenderer.method_03177(this.recoveredField2628.substring(this.recoveredField2634), this.method_06044());
            this.method_06014(this.fontRenderer.method_03177(var8, var7).length() + this.recoveredField2634);
         }

         return false;
      }
   }

   public void trimToLength(int var1) {
      this.recoveredField2632 = var1;
      if (this.recoveredField2628.length() > var1) {
         this.recoveredField2628 = this.recoveredField2628.substring(0, var1);
      }
   }

   public InputFieldElement(CBFontRenderer var1, String var2, int var3, int var4) {
      this.recoveredField2625 = true;
      this.recoveredField2635 = true;
      this.recoveredField2627 = true;
      this.recoveredField2631 = 14737632;
      this.recoveredField2624 = 7368816;
      this.recoveredField2637 = true;
      this.fontRenderer = var1;
      this.recoveredField2630 = var2;
      this.recoveredField2633 = var3;
      this.recoveredField2623 = var4;
   }

   public boolean method_06018() {
      return this.recoveredField2637;
   }

   @Override
   public void handleElementUpdate() {
      this.method_06030();
   }

   public void method_06041(boolean var1) {
      this.recoveredField2637 = var1;
   }

   public void method_06034(int var1) {
      int var2 = this.recoveredField2628.length();
      if (var1 > var2) {
         var1 = var2;
      }

      if (var1 < 0) {
         var1 = 0;
      }

      this.recoveredField2638 = var1;
      if (this.fontRenderer != null) {
         if (this.recoveredField2634 > var2) {
            this.recoveredField2634 = var2;
         }

         float var3 = this.method_06044();
         String var4 = this.fontRenderer.method_03177(this.recoveredField2628.substring(this.recoveredField2634), var3);
         int var5 = var4.length() + this.recoveredField2634;
         if (var1 == this.recoveredField2634) {
            this.recoveredField2634 = this.recoveredField2634 - this.fontRenderer.method_03185(this.recoveredField2628, var3, true).length();
         }

         if (var1 > var5) {
            this.recoveredField2634 += var1 - var5;
         } else if (var1 <= this.recoveredField2634) {
            this.recoveredField2634 = this.recoveredField2634 - (this.recoveredField2634 - var1);
         }

         if (this.recoveredField2634 < 0) {
            this.recoveredField2634 = 0;
         }

         if (this.recoveredField2634 > var2) {
            this.recoveredField2634 = var2;
         }
      }
   }

   public float method_06044() {
      return this.method_06031() ? this.width - 8.0F : this.width;
   }

   public void method_06014(int var1) {
      this.recoveredField2626 = var1;
      int var2 = this.recoveredField2628.length();
      if (this.recoveredField2626 < 0) {
         this.recoveredField2626 = 0;
      }

      if (this.recoveredField2626 > var2) {
         this.recoveredField2626 = var2;
      }

      this.method_06034(this.recoveredField2626);
   }

   public void method_06012() {
      this.method_06014(this.recoveredField2628.length());
   }

   public void setText(String var1) {
      this.recoveredField2628 = var1.length() > this.recoveredField2632 ? var1.substring(0, this.recoveredField2632) : var1;
      this.method_06012();
   }

   public String method_06029() {
      int var1 = Math.min(this.recoveredField2626, this.recoveredField2638);
      int var2 = Math.max(this.recoveredField2626, this.recoveredField2638);
      return this.recoveredField2628.substring(var1, var2);
   }

   public void method_06023(boolean var1) {
      this.recoveredField2627 = var1;
   }

   public void method_06017(int var1) {
      this.method_06014(this.recoveredField2638 + var1);
   }

   public void method_06043(int var1) {
      if (this.recoveredField2628.length() != 0) {
         if (this.recoveredField2638 != this.recoveredField2626) {
            this.method_06040("");
         } else {
            boolean var2 = var1 < 0;
            int var3 = var2 ? this.recoveredField2626 + var1 : this.recoveredField2626;
            int var4 = var2 ? this.recoveredField2626 : this.recoveredField2626 + var1;
            String var5 = "";
            if (var3 >= 0) {
               var5 = this.recoveredField2628.substring(0, var3);
            }

            if (var4 < this.recoveredField2628.length()) {
               var5 = var5 + this.recoveredField2628.substring(var4);
            }

            this.recoveredField2628 = var5;
            if (var2) {
               this.method_06017(var1);
            }
         }
      }
   }

   public void method_06030() {
      this.recoveredField2636++;
   }

   public void method_06035(boolean var1) {
      this.recoveredField2625 = var1;
   }

   public void method_06032(int var1) {
      this.recoveredField2631 = var1;
   }

   public void method_06040(String var1) {
      String var3 = "";
      String var4 = ChatAllowedCharacters.filterAllowedCharacters(var1);
      int var5 = Math.min(this.recoveredField2626, this.recoveredField2638);
      int var6 = Math.max(this.recoveredField2626, this.recoveredField2638);
      int var7 = this.recoveredField2632 - this.recoveredField2628.length() - (var5 - this.recoveredField2638);
      boolean var8 = false;
      if (this.recoveredField2628.length() > 0) {
         var3 = var3 + this.recoveredField2628.substring(0, var5);
      }

      int var2;
      if (var7 < var4.length()) {
         var3 = var3 + var4.substring(0, var7);
         var2 = var7;
      } else {
         var3 = var3 + var4;
         var2 = var4.length();
      }

      if (this.recoveredField2628.length() > 0 && var6 < this.recoveredField2628.length()) {
         var3 = var3 + this.recoveredField2628.substring(var6);
      }

      this.recoveredField2628 = var3;
      this.method_06017(var5 - this.recoveredField2638 + var2);
   }

   public void method_06020() {
      if (this.method_06018()) {
         if (this.method_06031()) {
            Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.recoveredField2633);
         }

         int var1 = this.recoveredField2627 ? this.recoveredField2631 : this.recoveredField2624;
         int var2 = this.recoveredField2626 - this.recoveredField2634;
         int var3 = this.recoveredField2638 - this.recoveredField2634;
         String var4 = this.fontRenderer.method_03177(this.recoveredField2628.substring(this.recoveredField2634), this.method_06044());
         boolean var5 = var2 >= 0 && var2 <= var4.length();
         boolean var6 = this.recoveredField2629 && this.recoveredField2636 / 6 % 2 == 0 && var5;
         float var7 = this.recoveredField2625 ? this.x + 4.0F : this.x;
         float var8 = this.recoveredField2625 ? this.y + (this.height - 8.0F) / 2.0F : this.y;
         float var9 = var7;
         if (var3 > var4.length()) {
            var3 = var4.length();
         }

         if (var4.length() > 0) {
            String var10 = var5 ? var4.substring(0, var2) : var4;
            var9 = this.fontRenderer.drawString(var10, var7, var8, var1);
         } else if (!this.method_06013()) {
            this.fontRenderer.drawString(this.recoveredField2630, var7, var8, var1);
         }

         boolean var13 = this.recoveredField2626 < this.recoveredField2628.length() || this.recoveredField2628.length() >= this.method_06021();
         float var11 = var9;
         if (!var5) {
            var11 = var2 > 0 ? var7 + this.width : var7;
         } else if (var13) {
            var11 = var9 - 1.0F;
            var9--;
         }

         if (var4.length() > 0 && var5 && var2 < var4.length()) {
            this.fontRenderer.drawString(var4.substring(var2), var9, var8, var1);
         }

         if (var6) {
            if (var13) {
               Gui.drawRect(var11, var8 - 1.0F, var11 + 1.0F, var8 + 1.0F + this.fontRenderer.getHeight(), -3092272);
            } else {
               this.fontRenderer.drawString("_", var11, var8, var1);
            }
         }

         if (var3 != var2) {
            float var12 = var7 + this.fontRenderer.getStringWidth(var4.substring(0, var3));
            this.method_06038(var11, var8 - 1.0F + 2.0F, var12 - 1.0F, var8 + 1.0F + this.fontRenderer.getHeight() + 2.0F);
         }
      }
   }

   public int method_06025(int var1, int var2) {
      return this.method_06026(var1, this.method_06033(), true);
   }

   public int method_06033() {
      return this.recoveredField2626;
   }

   public void method_06015(boolean var1) {
      this.recoveredField2635 = var1;
   }

   public int method_06036() {
      return this.recoveredField2638;
   }

   public void method_06038(float var1, float var2, float var3, float var4) {
      if (var1 < var3) {
         float var5 = var1;
         var1 = var3;
         var3 = var5;
      }

      if (var2 < var4) {
         ;
      }

      if (var3 > this.x + this.width) {
         var3 = this.x + this.width;
      }

      if (var1 > this.x + this.width) {
         var1 = this.x + this.width;
      }

      GL11.glColor4f(0.0F, 0.0F, 255.0F, 255.0F);
      GL11.glDisable(3553);
      GL11.glEnable(3058);
      GL11.glLogicOp(5387);
      GL11.glDisable(3058);
      GL11.glEnable(3553);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.method_06020();
   }

   public void method_06019(int var1) {
      this.recoveredField2624 = var1;
   }
}
