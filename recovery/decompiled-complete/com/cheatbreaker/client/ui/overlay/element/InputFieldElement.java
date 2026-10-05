package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import io.netty.channel.AbstractChannelHandlerContext$3;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import net.optifine.entity.model.CustomEntityRenderer;
import org.lwjgl.opengl.GL11;

public class InputFieldElement extends AbstractElement {
   public int field_0008;
   public int field_0017;
   public CBFontRenderer fontRenderer;
   public AbstractChannelHandlerContext$3 field_0014;
   public boolean field_0002;
   public BlockRailDetector field_0003;
   public int field_0018;
   public boolean field_0012;
   public String field_0004 = "";
   public boolean field_0019;
   public String field_0001;
   public int field_0009;
   public int field_0011 = 32;
   public int field_0006;
   public int field_0013;
   public boolean field_0016;
   public int field_0000;
   public boolean field_0005;
   public CustomEntityRenderer field_0010;
   public int field_0015;

   public int method_06026(int var1, int var2, boolean var3) {
      int var4 = var2;
      boolean var5 = var1 < 0;
      int var6 = Math.abs(var1);

      for (int var7 = 0; var7 < var6; var7++) {
         if (!var5) {
            int var8 = this.field_0004.length();
            if ((var4 = this.field_0004.indexOf(32, var4)) == -1) {
               var4 = var8;
            } else {
               while (var3 && var4 < var8 && this.field_0004.charAt(var4) == ' ') {
                  var4++;
               }
            }
         } else {
            while (var3 && var4 > 0 && this.field_0004.charAt(var4 - 1) == ' ') {
               var4--;
            }

            while (var4 > 0 && this.field_0004.charAt(var4 - 1) != ' ') {
               var4--;
            }
         }
      }

      return var4;
   }

   public void method_06028(boolean var1) {
      if (var1 && !this.field_0019) {
         this.field_0000 = 0;
      }

      this.field_0019 = var1;
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      this.method_06037(var1, var2);
   }

   public int method_06021() {
      return this.field_0011;
   }

   public int method_06024(int var1) {
      return this.method_06025(var1, this.method_06033());
   }

   public boolean method_06013() {
      return this.field_0019;
   }

   public void method_06016() {
      this.method_06014(0);
   }

   public boolean method_06037(char var1, int var2) {
      if (!this.field_0019) {
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
               if (this.field_0012) {
                  this.method_06040(GuiScreen.getClipboardString());
               }

               return true;
            case '\u0018':
               GuiScreen.setClipboardString(this.method_06029());
               if (this.field_0012) {
                  this.method_06040("");
               }

               return true;
            default:
               switch (var2) {
                  case 14:
                     if (GuiScreen.isCtrlKeyDown()) {
                        if (this.field_0012) {
                           this.method_06039(-1);
                        }
                     } else if (this.field_0012) {
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
                        this.method_06034(this.field_0004.length());
                     } else {
                        this.method_06012();
                     }

                     return true;
                  case 211:
                     if (GuiScreen.isCtrlKeyDown()) {
                        if (this.field_0012) {
                           this.method_06039(1);
                        }
                     } else if (this.field_0012) {
                        this.method_06043(1);
                     }

                     return true;
                  default:
                     if (ChatAllowedCharacters.isAllowedCharacter(var1)) {
                        if (this.field_0012) {
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
      if (this.field_0004.length() != 0) {
         if (this.field_0015 != this.field_0018) {
            this.method_06040("");
         } else {
            this.method_06043(this.method_06024(var1) - this.field_0018);
         }
      }
   }

   public String getText() {
      return this.field_0004;
   }

   public boolean method_06031() {
      return this.field_0002;
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
         if (this.field_0016) {
            this.method_06028(var5);
         }

         if (this.field_0019 && var3 == 0) {
            float var7 = var1 - this.x;
            if (this.field_0002) {
               var7 -= 4.0F;
            }

            String var8 = this.fontRenderer.method_03177(this.field_0004.substring(this.field_0013), this.method_06044());
            this.method_06014(this.fontRenderer.method_03177(var8, var7).length() + this.field_0013);
         }

         return false;
      }
   }

   public void trimToLength(int var1) {
      this.field_0011 = var1;
      if (this.field_0004.length() > var1) {
         this.field_0004 = this.field_0004.substring(0, var1);
      }
   }

   public InputFieldElement(CBFontRenderer var1, String var2, int var3, int var4) {
      this.field_0002 = true;
      this.field_0016 = true;
      this.field_0012 = true;
      this.field_0009 = 14737632;
      this.field_0017 = 7368816;
      this.field_0005 = true;
      this.fontRenderer = var1;
      this.field_0001 = var2;
      this.field_0006 = var3;
      this.field_0008 = var4;
   }

   public boolean method_06018() {
      return this.field_0005;
   }

   @Override
   public void handleElementUpdate() {
      this.method_06030();
   }

   public void method_06041(boolean var1) {
      this.field_0005 = var1;
   }

   public void method_06034(int var1) {
      int var2 = this.field_0004.length();
      if (var1 > var2) {
         var1 = var2;
      }

      if (var1 < 0) {
         var1 = 0;
      }

      this.field_0015 = var1;
      if (this.fontRenderer != null) {
         if (this.field_0013 > var2) {
            this.field_0013 = var2;
         }

         float var3 = this.method_06044();
         String var4 = this.fontRenderer.method_03177(this.field_0004.substring(this.field_0013), var3);
         int var5 = var4.length() + this.field_0013;
         if (var1 == this.field_0013) {
            this.field_0013 = this.field_0013 - this.fontRenderer.method_03185(this.field_0004, var3, true).length();
         }

         if (var1 > var5) {
            this.field_0013 += var1 - var5;
         } else if (var1 <= this.field_0013) {
            this.field_0013 = this.field_0013 - (this.field_0013 - var1);
         }

         if (this.field_0013 < 0) {
            this.field_0013 = 0;
         }

         if (this.field_0013 > var2) {
            this.field_0013 = var2;
         }
      }
   }

   public float method_06044() {
      return this.method_06031() ? this.width - 8.0F : this.width;
   }

   public void method_06014(int var1) {
      this.field_0018 = var1;
      int var2 = this.field_0004.length();
      if (this.field_0018 < 0) {
         this.field_0018 = 0;
      }

      if (this.field_0018 > var2) {
         this.field_0018 = var2;
      }

      this.method_06034(this.field_0018);
   }

   public void method_06012() {
      this.method_06014(this.field_0004.length());
   }

   public void setText(String var1) {
      this.field_0004 = var1.length() > this.field_0011 ? var1.substring(0, this.field_0011) : var1;
      this.method_06012();
   }

   public String method_06029() {
      int var1 = Math.min(this.field_0018, this.field_0015);
      int var2 = Math.max(this.field_0018, this.field_0015);
      return this.field_0004.substring(var1, var2);
   }

   public void method_06023(boolean var1) {
      this.field_0012 = var1;
   }

   public void method_06017(int var1) {
      this.method_06014(this.field_0015 + var1);
   }

   public void method_06043(int var1) {
      if (this.field_0004.length() != 0) {
         if (this.field_0015 != this.field_0018) {
            this.method_06040("");
         } else {
            boolean var2 = var1 < 0;
            int var3 = var2 ? this.field_0018 + var1 : this.field_0018;
            int var4 = var2 ? this.field_0018 : this.field_0018 + var1;
            String var5 = "";
            if (var3 >= 0) {
               var5 = this.field_0004.substring(0, var3);
            }

            if (var4 < this.field_0004.length()) {
               var5 = var5 + this.field_0004.substring(var4);
            }

            this.field_0004 = var5;
            if (var2) {
               this.method_06017(var1);
            }
         }
      }
   }

   public void method_06030() {
      this.field_0000++;
   }

   public void method_06035(boolean var1) {
      this.field_0002 = var1;
   }

   public void method_06032(int var1) {
      this.field_0009 = var1;
   }

   public void method_06040(String var1) {
      String var3 = "";
      String var4 = ChatAllowedCharacters.filterAllowedCharacters(var1);
      int var5 = Math.min(this.field_0018, this.field_0015);
      int var6 = Math.max(this.field_0018, this.field_0015);
      int var7 = this.field_0011 - this.field_0004.length() - (var5 - this.field_0015);
      boolean var8 = false;
      if (this.field_0004.length() > 0) {
         var3 = var3 + this.field_0004.substring(0, var5);
      }

      int var2;
      if (var7 < var4.length()) {
         var3 = var3 + var4.substring(0, var7);
         var2 = var7;
      } else {
         var3 = var3 + var4;
         var2 = var4.length();
      }

      if (this.field_0004.length() > 0 && var6 < this.field_0004.length()) {
         var3 = var3 + this.field_0004.substring(var6);
      }

      this.field_0004 = var3;
      this.method_06017(var5 - this.field_0015 + var2);
   }

   public void method_06020() {
      if (this.method_06018()) {
         if (this.method_06031()) {
            Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, this.field_0006);
         }

         int var1 = this.field_0012 ? this.field_0009 : this.field_0017;
         int var2 = this.field_0018 - this.field_0013;
         int var3 = this.field_0015 - this.field_0013;
         String var4 = this.fontRenderer.method_03177(this.field_0004.substring(this.field_0013), this.method_06044());
         boolean var5 = var2 >= 0 && var2 <= var4.length();
         boolean var6 = this.field_0019 && this.field_0000 / 6 % 2 == 0 && var5;
         float var7 = this.field_0002 ? this.x + 4.0F : this.x;
         float var8 = this.field_0002 ? this.y + (this.height - 8.0F) / 2.0F : this.y;
         float var9 = var7;
         if (var3 > var4.length()) {
            var3 = var4.length();
         }

         if (var4.length() > 0) {
            String var10 = var5 ? var4.substring(0, var2) : var4;
            var9 = this.fontRenderer.drawString(var10, var7, var8, var1);
         } else if (!this.method_06013()) {
            this.fontRenderer.drawString(this.field_0001, var7, var8, var1);
         }

         boolean var13 = this.field_0018 < this.field_0004.length() || this.field_0004.length() >= this.method_06021();
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
      return this.field_0018;
   }

   public void method_06015(boolean var1) {
      this.field_0016 = var1;
   }

   public int method_06036() {
      return this.field_0015;
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
      this.field_0017 = var1;
   }
}
