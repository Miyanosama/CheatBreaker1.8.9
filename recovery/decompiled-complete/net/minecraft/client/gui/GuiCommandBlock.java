package net.minecraft.client.gui;

import com.cheatbreaker.client.network.CustomPayloadSender;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpContentCompressor;
import javax.vecmath.Matrix3d;
import net.minecraft.client.particle.MobAppearance$Factory;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiCommandBlock extends GuiScreen {
   public GuiTextField previousOutputTextField;
   public HttpContentCompressor field_0008;
   public static Logger field_146488_a = LogManager.getLogger();
   public MobAppearance$Factory field_0007;
   public GuiTextField commandTextField;
   public boolean field_175389_t;
   public GuiButton cancelBtn;
   public GuiButton field_175390_s;
   public GuiButton doneBtn;
   public Matrix3d field_0010;
   public CommandBlockLogic localCommandBlock;

   public GuiCommandBlock(CommandBlockLogic var1) {
      this.localCommandBlock = var1;
   }

   public void func_175388_a() {
      if (this.localCommandBlock.shouldTrackOutput()) {
         this.field_175390_s.j = "O";
         if (this.localCommandBlock.getLastOutput() != null) {
            this.previousOutputTextField.setText(this.localCommandBlock.getLastOutput().getUnformattedText());
         }
      } else {
         this.field_175390_s.j = "X";
         this.previousOutputTextField.setText("-");
      }
   }

   @Override
   public void updateScreen() {
      this.commandTextField.updateCursorCounter();
   }

   @Override
   public void keyTyped(char var1, int var2) {
      this.commandTextField.textboxKeyTyped(var1, var2);
      this.previousOutputTextField.textboxKeyTyped(var1, var2);
      this.doneBtn.l = this.commandTextField.getText().trim().length() > 0;
      if (var2 == 28 || var2 == 156) {
         this.actionPerformed(this.doneBtn);
      } else if (var2 == 1) {
         this.actionPerformed(this.cancelBtn);
      }
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.n.clear();
      this.n.add(this.doneBtn = new GuiButton(0, this.l / 2 - 4 - 150, this.m / 4 + 120 + 12, 150, 20, I18n.format("gui.done")));
      this.n.add(this.cancelBtn = new GuiButton(1, this.l / 2 + 4, this.m / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel")));
      this.n.add(this.field_175390_s = new GuiButton(4, this.l / 2 + 150 - 20, 150, 20, 20, "O"));
      this.commandTextField = new GuiTextField(2, this.q, this.l / 2 - 150, 50, 300, 20);
      this.commandTextField.setMaxStringLength(32767);
      this.commandTextField.setFocused(true);
      this.commandTextField.setText(this.localCommandBlock.getCommand());
      this.previousOutputTextField = new GuiTextField(3, this.q, this.l / 2 - 150, 150, 276, 20);
      this.previousOutputTextField.setMaxStringLength(32767);
      this.previousOutputTextField.setEnabled(false);
      this.previousOutputTextField.setText("-");
      this.field_175389_t = this.localCommandBlock.shouldTrackOutput();
      this.func_175388_a();
      this.doneBtn.l = this.commandTextField.getText().trim().length() > 0;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.commandTextField.mouseClicked(var1, var2, var3);
      this.previousOutputTextField.mouseClicked(var1, var2, var3);
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 1) {
            this.localCommandBlock.setTrackOutput(this.field_175389_t);
            this.j.displayGuiScreen((GuiScreen)null);
         } else if (var1.k == 0) {
            PacketBuffer var2 = new PacketBuffer(Unpooled.buffer());
            var2.writeByte(this.localCommandBlock.func_145751_f());
            this.localCommandBlock.func_145757_a(var2);
            var2.writeString(this.commandTextField.getText());
            var2.writeBoolean(this.localCommandBlock.shouldTrackOutput());
            this.j.getNetHandler().addToSendQueue(new CustomPayloadSender("MC|AdvCdm", var2));
            if (!this.localCommandBlock.shouldTrackOutput()) {
               this.localCommandBlock.setLastOutput((IChatComponent)null);
            }

            this.j.displayGuiScreen((GuiScreen)null);
         } else if (var1.k == 4) {
            this.localCommandBlock.setTrackOutput(!this.localCommandBlock.shouldTrackOutput());
            this.func_175388_a();
         }
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, I18n.format("advMode.setCommand"), this.l / 2, 20, 16777215);
      this.drawString(this.q, I18n.format("advMode.command"), this.l / 2 - 150, 37, 10526880);
      this.commandTextField.drawTextBox();
      int var4 = 75;
      int var5 = 0;
      this.drawString(this.q, I18n.format("advMode.nearestPlayer"), this.l / 2 - 150, var4 + var5++ * this.q.FONT_HEIGHT, 10526880);
      this.drawString(this.q, I18n.format("advMode.randomPlayer"), this.l / 2 - 150, var4 + var5++ * this.q.FONT_HEIGHT, 10526880);
      this.drawString(this.q, I18n.format("advMode.allPlayers"), this.l / 2 - 150, var4 + var5++ * this.q.FONT_HEIGHT, 10526880);
      this.drawString(this.q, I18n.format("advMode.allEntities"), this.l / 2 - 150, var4 + var5++ * this.q.FONT_HEIGHT, 10526880);
      this.drawString(this.q, "", this.l / 2 - 150, var4 + var5++ * this.q.FONT_HEIGHT, 10526880);
      if (this.previousOutputTextField.getText().length() > 0) {
         var4 = var4 + var5 * this.q.FONT_HEIGHT + 16;
         this.drawString(this.q, I18n.format("advMode.previousOutput"), this.l / 2 - 150, var4, 10526880);
         this.previousOutputTextField.drawTextBox();
      }

      super.drawScreen(var1, var2, var3);
   }
}
