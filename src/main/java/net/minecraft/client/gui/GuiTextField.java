package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;

public class GuiTextField extends Gui {
   public int lineScrollOffset;
   public int cursorCounter;
   public int yPosition;
   public boolean canLoseFocus;
   public int maxStringLength;
   public int selectionEnd;
   public int cursorPosition;
   public int height;
   public GuiPageButtonList.GuiResponder field_175210_x;
   public boolean enableBackgroundDrawing;
   public boolean isEnabled;
   public Predicate<String> validator;
   public int id;
   public int width;
   public int enabledColor;
   public boolean visible;
   public boolean isFocused;
   public FontRenderer fontRendererInstance;
   public int xPosition;
   public String text = "";
   public int disabledColor;

   public String getSelectedText() {
      int var1 = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
      int var2 = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
      return this.text.substring(var1, var2);
   }

   public void setCanLoseFocus(boolean var1) {
      this.canLoseFocus = var1;
   }

   public void setTextColor(int var1) {
      this.enabledColor = var1;
   }

   public void deleteFromCursor(int var1) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            boolean var2 = var1 < 0;
            int var3 = var2 ? this.cursorPosition + var1 : this.cursorPosition;
            int var4 = var2 ? this.cursorPosition : this.cursorPosition + var1;
            String var5 = "";
            if (var3 >= 0) {
               var5 = this.text.substring(0, var3);
            }

            if (var4 < this.text.length()) {
               var5 = var5 + this.text.substring(var4);
            }

            if (this.validator.apply(var5)) {
               this.text = var5;
               if (var2) {
                  this.moveCursorBy(var1);
               }

               if (this.field_175210_x != null) {
                  this.field_175210_x.func_175319_a(this.id, this.text);
               }
            }
         }
      }
   }

   public int getSelectionEnd() {
      return this.selectionEnd;
   }

   public void setText(String var1) {
      if (this.validator.apply(var1)) {
         if (var1.length() > this.maxStringLength) {
            this.text = var1.substring(0, this.maxStringLength);
         } else {
            this.text = var1;
         }

         this.setCursorPositionEnd();
      }
   }

   public void setDisabledTextColour(int var1) {
      this.disabledColor = var1;
   }

   public boolean isFocused() {
      return this.isFocused;
   }

   public void setCursorPositionZero() {
      this.setCursorPosition(0);
   }

   public void setFocused(boolean var1) {
      if (var1 && !this.isFocused) {
         this.cursorCounter = 0;
      }

      this.isFocused = var1;
   }

   public void method_10662(int var1) {
      if (this.text.length() != 0) {
         if (this.selectionEnd != this.cursorPosition) {
            this.writeText("");
         } else {
            this.deleteFromCursor(this.getNthWordFromCursor(var1) - this.cursorPosition);
         }
      }
   }

   public void drawCursorVertical(int var1, int var2, int var3, int var4) {
      if (var1 < var3) {
         int var5 = var1;
         var1 = var3;
         var3 = var5;
      }

      if (var2 < var4) {
         int var7 = var2;
         var2 = var4;
         var4 = var7;
      }

      if (var3 > this.xPosition + this.width) {
         var3 = this.xPosition + this.width;
      }

      if (var1 > this.xPosition + this.width) {
         var1 = this.xPosition + this.width;
      }

      Tessellator var8 = Tessellator.getInstance();
      WorldRenderer var6 = var8.getWorldRenderer();
      GlStateManager.color(0.0F, 0.0F, 255.0F, 255.0F);
      GlStateManager.disableTexture2D();
      GlStateManager.enableColorLogic();
      GlStateManager.colorLogicOp(5387);
      var6.begin(7, DefaultVertexFormats.POSITION);
      var6.pos(var1, var4, 0.0).endVertex();
      var6.pos(var3, var4, 0.0).endVertex();
      var6.pos(var3, var2, 0.0).endVertex();
      var6.pos(var1, var2, 0.0).endVertex();
      var8.draw();
      GlStateManager.disableColorLogic();
      GlStateManager.enableTexture2D();
   }

   public String getText() {
      return this.text;
   }

   public int func_146197_a(int var1, int var2, boolean var3) {
      int var4 = var2;
      boolean var5 = var1 < 0;
      int var6 = Math.abs(var1);

      for (int var7 = 0; var7 < var6; var7++) {
         if (!var5) {
            int var8 = this.text.length();
            var4 = this.text.indexOf(32, var4);
            if (var4 == -1) {
               var4 = var8;
            } else {
               while (var3 && var4 < var8 && this.text.charAt(var4) == ' ') {
                  var4++;
               }
            }
         } else {
            while (var3 && var4 > 0 && this.text.charAt(var4 - 1) == ' ') {
               var4--;
            }

            while (var4 > 0 && this.text.charAt(var4 - 1) != ' ') {
               var4--;
            }
         }
      }

      return var4;
   }

   public void drawTextBox() {
      if (this.getVisible()) {
         if (this.getEnableBackgroundDrawing()) {
            a(this.xPosition - 1, this.yPosition - 1, this.xPosition + this.width + 1, this.yPosition + this.height + 1, -6250336);
            a(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, -16777216);
         }

         int var1 = this.isEnabled ? this.enabledColor : this.disabledColor;
         int var2 = this.cursorPosition - this.lineScrollOffset;
         int var3 = this.selectionEnd - this.lineScrollOffset;
         String var4 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
         boolean var5 = var2 >= 0 && var2 <= var4.length();
         boolean var6 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && var5;
         int var7 = this.enableBackgroundDrawing ? this.xPosition + 4 : this.xPosition;
         int var8 = this.enableBackgroundDrawing ? this.yPosition + (this.height - 8) / 2 : this.yPosition;
         int var9 = var7;
         if (var3 > var4.length()) {
            var3 = var4.length();
         }

         if (var4.length() > 0) {
            String var10 = var5 ? var4.substring(0, var2) : var4;
            var9 = this.fontRendererInstance.drawStringWithShadow(var10, var7, var8, var1);
         }

         boolean var14 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
         int var11 = var9;
         if (!var5) {
            var11 = var2 > 0 ? var7 + this.width : var7;
         } else if (var14) {
            var11 = var9 - 1;
            var9--;
         }

         if (var4.length() > 0 && var5 && var2 < var4.length()) {
            var9 = this.fontRendererInstance.drawStringWithShadow(var4.substring(var2), var9, var8, var1);
         }

         if (var6) {
            if (var14) {
               Gui.a(var11, var8 - 1, var11 + 1, var8 + 1 + this.fontRendererInstance.FONT_HEIGHT, -3092272);
            } else {
               this.fontRendererInstance.drawStringWithShadow("_", var11, var8, var1);
            }
         }

         if (var3 != var2) {
            int var12 = var7 + this.fontRendererInstance.getStringWidth(var4.substring(0, var3));
            this.drawCursorVertical(var11, var8 - 1, var12 - 1, var8 + 1 + this.fontRendererInstance.FONT_HEIGHT);
         }
      }
   }

   public int getNthWordFromPos(int var1, int var2) {
      return this.func_146197_a(var1, var2, true);
   }

   public void setCursorPositionEnd() {
      this.setCursorPosition(this.text.length());
   }

   public void setEnableBackgroundDrawing(boolean var1) {
      this.enableBackgroundDrawing = var1;
   }

   public void setSelectionPos(int var1) {
      int var2 = this.text.length();
      if (var1 > var2) {
         var1 = var2;
      }

      if (var1 < 0) {
         var1 = 0;
      }

      this.selectionEnd = var1;
      if (this.fontRendererInstance != null) {
         if (this.lineScrollOffset > var2) {
            this.lineScrollOffset = var2;
         }

         int var3 = this.getWidth();
         String var4 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), var3);
         int var5 = var4.length() + this.lineScrollOffset;
         if (var1 == this.lineScrollOffset) {
            this.lineScrollOffset = this.lineScrollOffset - this.fontRendererInstance.trimStringToWidth(this.text, var3, true).length();
         }

         if (var1 > var5) {
            this.lineScrollOffset += var1 - var5;
         } else if (var1 <= this.lineScrollOffset) {
            this.lineScrollOffset = this.lineScrollOffset - (this.lineScrollOffset - var1);
         }

         this.lineScrollOffset = MathHelper.clamp_int(this.lineScrollOffset, 0, var2);
      }
   }

   public void setMaxStringLength(int var1) {
      this.maxStringLength = var1;
      if (this.text.length() > var1) {
         this.text = this.text.substring(0, var1);
      }
   }

   public boolean textboxKeyTyped(char var1, int var2) {
      if (!this.isFocused) {
         return false;
      } else if (GuiScreen.isKeyComboCtrlA(var2)) {
         this.setCursorPositionEnd();
         this.setSelectionPos(0);
         return true;
      } else if (GuiScreen.isKeyComboCtrlC(var2)) {
         GuiScreen.setClipboardString(this.getSelectedText());
         return true;
      } else if (GuiScreen.isKeyComboCtrlV(var2)) {
         if (this.isEnabled) {
            this.writeText(GuiScreen.getClipboardString());
         }

         return true;
      } else if (GuiScreen.isKeyComboCtrlX(var2)) {
         GuiScreen.setClipboardString(this.getSelectedText());
         if (this.isEnabled) {
            this.writeText("");
         }

         return true;
      } else {
         switch (var2) {
            case 14:
               if (GuiScreen.isCtrlKeyDown()) {
                  if (this.isEnabled) {
                     this.method_10662(-1);
                  }
               } else if (this.isEnabled) {
                  this.deleteFromCursor(-1);
               }

               return true;
            case 199:
               if (GuiScreen.isShiftKeyDown()) {
                  this.setSelectionPos(0);
               } else {
                  this.setCursorPositionZero();
               }

               return true;
            case 203:
               if (GuiScreen.isShiftKeyDown()) {
                  if (GuiScreen.isCtrlKeyDown()) {
                     this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                  } else {
                     this.setSelectionPos(this.getSelectionEnd() - 1);
                  }
               } else if (GuiScreen.isCtrlKeyDown()) {
                  this.setCursorPosition(this.getNthWordFromCursor(-1));
               } else {
                  this.moveCursorBy(-1);
               }

               return true;
            case 205:
               if (GuiScreen.isShiftKeyDown()) {
                  if (GuiScreen.isCtrlKeyDown()) {
                     this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                  } else {
                     this.setSelectionPos(this.getSelectionEnd() + 1);
                  }
               } else if (GuiScreen.isCtrlKeyDown()) {
                  this.setCursorPosition(this.getNthWordFromCursor(1));
               } else {
                  this.moveCursorBy(1);
               }

               return true;
            case 207:
               if (GuiScreen.isShiftKeyDown()) {
                  this.setSelectionPos(this.text.length());
               } else {
                  this.setCursorPositionEnd();
               }

               return true;
            case 211:
               if (GuiScreen.isCtrlKeyDown()) {
                  if (this.isEnabled) {
                     this.method_10662(1);
                  }
               } else if (this.isEnabled) {
                  this.deleteFromCursor(1);
               }

               return true;
            default:
               if (ChatAllowedCharacters.isAllowedCharacter(var1)) {
                  if (this.isEnabled) {
                     this.writeText(Character.toString(var1));
                  }

                  return true;
               } else {
                  return false;
               }
         }
      }
   }

   public void setCursorPosition(int var1) {
      this.cursorPosition = var1;
      int var2 = this.text.length();
      this.cursorPosition = MathHelper.clamp_int(this.cursorPosition, 0, var2);
      this.setSelectionPos(this.cursorPosition);
   }

   public void setValidator(Predicate<String> var1) {
      this.validator = var1;
   }

   public int getCursorPosition() {
      return this.cursorPosition;
   }

   public int getMaxStringLength() {
      return this.maxStringLength;
   }

   public int getId() {
      return this.id;
   }

   public void moveCursorBy(int var1) {
      this.setCursorPosition(this.selectionEnd + var1);
   }

   public int getWidth() {
      return this.getEnableBackgroundDrawing() ? this.width - 8 : this.width;
   }

   public boolean getVisible() {
      return this.visible;
   }

   public void setVisible(boolean var1) {
      this.visible = var1;
   }

   public void updateCursorCounter() {
      this.cursorCounter++;
   }

   public void mouseClicked(int var1, int var2, int var3) {
      boolean var4 = var1 >= this.xPosition && var1 < this.xPosition + this.width && var2 >= this.yPosition && var2 < this.yPosition + this.height;
      if (this.canLoseFocus) {
         this.setFocused(var4);
      }

      if (this.isFocused && var4 && var3 == 0) {
         int var5 = var1 - this.xPosition;
         if (this.enableBackgroundDrawing) {
            var5 -= 4;
         }

         String var6 = this.fontRendererInstance.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
         this.setCursorPosition(this.fontRendererInstance.trimStringToWidth(var6, var5).length() + this.lineScrollOffset);
      }
   }

   public int getNthWordFromCursor(int var1) {
      return this.getNthWordFromPos(var1, this.getCursorPosition());
   }

   public GuiTextField(int var1, FontRenderer var2, int var3, int var4, int var5, int var6) {
      this.maxStringLength = 32;
      this.enableBackgroundDrawing = true;
      this.canLoseFocus = true;
      this.isEnabled = true;
      this.enabledColor = 14737632;
      this.disabledColor = 7368816;
      this.visible = true;
      this.validator = Predicates.alwaysTrue();
      this.id = var1;
      this.fontRendererInstance = var2;
      this.xPosition = var3;
      this.yPosition = var4;
      this.width = var5;
      this.height = var6;
   }

   public void writeText(String var1) {
      String var2 = "";
      String var3 = ChatAllowedCharacters.filterAllowedCharacters(var1);
      int var4 = this.cursorPosition < this.selectionEnd ? this.cursorPosition : this.selectionEnd;
      int var5 = this.cursorPosition < this.selectionEnd ? this.selectionEnd : this.cursorPosition;
      int var6 = this.maxStringLength - this.text.length() - (var4 - var5);
      int var7 = 0;
      if (this.text.length() > 0) {
         var2 = var2 + this.text.substring(0, var4);
      }

      if (var6 < var3.length()) {
         var2 = var2 + var3.substring(0, var6);
         var7 = var6;
      } else {
         var2 = var2 + var3;
         var7 = var3.length();
      }

      if (this.text.length() > 0 && var5 < this.text.length()) {
         var2 = var2 + this.text.substring(var5);
      }

      if (this.validator.apply(var2)) {
         this.text = var2;
         this.moveCursorBy(var4 - this.selectionEnd + var7);
         if (this.field_175210_x != null) {
            this.field_175210_x.func_175319_a(this.id, this.text);
         }
      }
   }

   public void func_175207_a(GuiPageButtonList.GuiResponder var1) {
      this.field_175210_x = var1;
   }

   public boolean getEnableBackgroundDrawing() {
      return this.enableBackgroundDrawing;
   }

   public void setEnabled(boolean var1) {
      this.isEnabled = var1;
   }
}
