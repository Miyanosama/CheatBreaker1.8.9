package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.network.play.client.C14PacketTabComplete;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiChat extends GuiScreen {
   public String defaultInputFieldText;
   public List<String> foundPlayerNames;
   public boolean playerNamesFound;
   public int autocompleteIndex;
   public boolean waitingOnAutocomplete;
   public GuiTextField inputField;
   public String historyBuffer = "";
   public int sentHistoryCursor = -1;
   public static Logger logger = LogManager.getLogger();

   @Override
   public boolean b_() {
      return false;
   }

   public void autocompletePlayerNames() {
      if (this.playerNamesFound) {
         this.inputField.deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
         if (this.autocompleteIndex >= this.foundPlayerNames.size()) {
            this.autocompleteIndex = 0;
         }
      } else {
         int var1 = this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false);
         this.foundPlayerNames.clear();
         this.autocompleteIndex = 0;
         String var2 = this.inputField.getText().substring(var1).toLowerCase();
         String var3 = this.inputField.getText().substring(0, this.inputField.getCursorPosition());
         this.sendAutocompleteRequest(var3, var2);
         if (this.foundPlayerNames.isEmpty()) {
            return;
         }

         this.playerNamesFound = true;
         this.inputField.deleteFromCursor(var1 - this.inputField.getCursorPosition());
      }

      if (this.foundPlayerNames.size() > 1) {
         StringBuilder var4 = new StringBuilder();

         for (String var6 : this.foundPlayerNames) {
            if (var4.length() > 0) {
               var4.append(", ");
            }

            var4.append(var6);
         }

         this.j.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new ChatComponentText(var4.toString()), 1);
      }

      this.inputField.writeText(this.foundPlayerNames.get(this.autocompleteIndex++));
   }

   @Override
   public void setText(String var1, boolean var2) {
      if (var2) {
         this.inputField.setText(var1);
      } else {
         this.inputField.writeText(var1);
      }
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      this.waitingOnAutocomplete = false;
      if (var2 == 15) {
         this.autocompletePlayerNames();
      } else {
         this.playerNamesFound = false;
      }

      if (var2 == 1) {
         this.j.displayGuiScreen((GuiScreen)null);
      } else if (var2 == 28 || var2 == 156) {
         String var3 = this.inputField.getText().trim();
         if (var3.length() > 0) {
            this.f(var3);
         }

         this.j.displayGuiScreen((GuiScreen)null);
      } else if (var2 == 200) {
         this.getSentHistory(-1);
      } else if (var2 == 208) {
         this.getSentHistory(1);
      } else if (var2 == 201) {
         this.j.ingameGUI.getChatGUI().scroll(this.j.ingameGUI.getChatGUI().getLineCount() - 1);
      } else if (var2 == 209) {
         this.j.ingameGUI.getChatGUI().scroll(-this.j.ingameGUI.getChatGUI().getLineCount() + 1);
      } else {
         this.inputField.textboxKeyTyped(var1, var2);
      }
   }

   public void onAutocompleteResponse(String[] var1) {
      if (this.waitingOnAutocomplete) {
         this.playerNamesFound = false;
         this.foundPlayerNames.clear();

         for (String var5 : var1) {
            if (var5.length() > 0) {
               this.foundPlayerNames.add(var5);
            }
         }

         String var6 = this.inputField.getText().substring(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false));
         String var7 = StringUtils.getCommonPrefix(var1);
         if (var7.length() > 0 && !var6.equalsIgnoreCase(var7)) {
            this.inputField
               .deleteFromCursor(this.inputField.func_146197_a(-1, this.inputField.getCursorPosition(), false) - this.inputField.getCursorPosition());
            this.inputField.writeText(var7);
         } else if (this.foundPlayerNames.size() > 0) {
            this.playerNamesFound = true;
            this.autocompletePlayerNames();
         }
      }
   }

   public GuiChat() {
      this.foundPlayerNames = Lists.newArrayList();
      this.defaultInputFieldText = "";
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.sentHistoryCursor = this.j.ingameGUI.getChatGUI().getSentMessages().size();
      this.inputField = new GuiTextField(0, this.q, 4, this.m - 12, this.l - 4, 12);
      this.inputField.setMaxStringLength(100);
      this.inputField.setEnableBackgroundDrawing(false);
      this.inputField.setFocused(true);
      this.inputField.setText(this.defaultInputFieldText);
      this.inputField.setCanLoseFocus(false);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      a(2, this.m - 14, this.l - 2, this.m - 2, Integer.MIN_VALUE);
      this.inputField.drawTextBox();
      IChatComponent var4 = this.j.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
      if (var4 != null && var4.getChatStyle().getChatHoverEvent() != null) {
         this.a(var4, var1, var2);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void sendAutocompleteRequest(String var1, String var2) {
      if (var1.length() >= 1) {
         BlockPos var3 = null;
         if (this.j.objectMouseOver != null && this.j.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            var3 = this.j.objectMouseOver.getBlockPos();
         }

         this.j.thePlayer.sendQueue.addToSendQueue(new C14PacketTabComplete(var1, var3));
         this.waitingOnAutocomplete = true;
      }
   }

   public GuiChat(String var1) {
      this.foundPlayerNames = Lists.newArrayList();
      this.defaultInputFieldText = "";
      this.defaultInputFieldText = var1;
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      int var1 = Mouse.getEventDWheel();
      if (var1 != 0) {
         if (var1 > 1) {
            var1 = 1;
         }

         if (var1 < -1) {
            var1 = -1;
         }

         if (!isShiftKeyDown()) {
            var1 *= 7;
         }

         this.j.ingameGUI.getChatGUI().scroll(var1);
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      if (var3 == 0) {
         IChatComponent var4 = this.j.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
         if (this.handleComponentClick(var4)) {
            return;
         }
      }

      this.inputField.mouseClicked(var1, var2, var3);
      super.mouseClicked(var1, var2, var3);
   }

   @Override
   public void updateScreen() {
      this.inputField.updateCursorCounter();
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
      this.j.ingameGUI.getChatGUI().resetScroll();
   }

   public void getSentHistory(int var1) {
      int var2 = this.sentHistoryCursor + var1;
      int var3 = this.j.ingameGUI.getChatGUI().getSentMessages().size();
      var2 = MathHelper.clamp_int(var2, 0, var3);
      if (var2 != this.sentHistoryCursor) {
         if (var2 == var3) {
            this.sentHistoryCursor = var3;
            this.inputField.setText(this.historyBuffer);
         } else {
            if (this.sentHistoryCursor == var3) {
               this.historyBuffer = this.inputField.getText();
            }

            this.inputField.setText(this.j.ingameGUI.getChatGUI().getSentMessages().get(var2));
            this.sentHistoryCursor = var2;
         }
      }
   }
}
