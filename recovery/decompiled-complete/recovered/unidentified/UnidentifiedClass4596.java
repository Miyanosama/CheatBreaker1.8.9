package recovered.unidentified;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.ModuleManager;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.channel.MultithreadEventLoopGroup;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.profiler.Profiler$Result;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.gen.feature.WorldGenFire;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass4596 extends GuiScreen {
   public GuiTextField field_0003 = null;
   public MultithreadEventLoopGroup field_0006;
   public GuiScreen field_0002;
   public Profiler$Result field_0005;
   public boolean field_0000;
   public WorldGenFire field_0001;
   public Setting field_0007;
   public float field_0004;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.field_0002.drawScreen(var1, var2, var3);
      this.drawDefaultBackground();
      a(this.l / 2 - 73, this.m / 2 - 19, this.l / 2 + 73, this.m / 2 + 8, -11250604);
      a(this.l / 2 - 72, this.m / 2 - 18, this.l / 2 + 72, this.m / 2 + 7, -3881788);
      GL11.glPushMatrix();
      GL11.glScalef(this.field_0004, this.field_0004, this.field_0004);
      int var4 = (int)(this.l / this.field_0004);
      int var5 = (int)(this.m / this.field_0004);
      CheatBreaker.getInstance()
         .field_0068
         .drawString(this.field_0007.method_08911(), var4 / 2 - 70.0F / this.field_0004, var5 / 2 - 17.0F / this.field_0004, 1862270976);
      GL11.glPopMatrix();
      this.field_0003.setMaxStringLength(64);
      this.field_0003.drawTextBox();
   }

   public UnidentifiedClass4596(GuiScreen var1, float var2) {
      this.field_0002 = var1;
      this.field_0004 = var2;
      this.field_0000 = true;
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      if (!this.field_0000) {
         this.j.displayGuiScreen(this.field_0002);
      } else {
         this.field_0000 = false;
         this.field_0003 = new GuiTextField(299, this.j.fontRendererObj, this.l / 2 - 70, this.m / 2 - 6, 140, 10);
         if (this.field_0007 != null) {
            this.field_0003.setText(this.field_0007.method_08874());
         }

         this.field_0003.setFocused(true);
      }
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_0003.mouseClicked(var1, var2, var3);
   }

   public UnidentifiedClass4596(Setting var1, GuiScreen var2, float var3) {
      this(var2, var3);
      this.field_0007 = var1;
   }

   @Override
   public void keyTyped(char var1, int var2) {
      ModuleManager var3 = CheatBreaker.getInstance().getModuleManager();
      switch (var2) {
         case 1:
            this.j.displayGuiScreen(this.field_0002);
            ((CBModulesGui)this.field_0002).currentScrollableElement = ((CBModulesGui)this.field_0002).field_0017;
            CheatBreaker.getInstance().method_19818().method_04443(var3.field_0028);
            break;
         case 28:
            this.field_0007.setValue(this.field_0003.getText());
            this.j.displayGuiScreen(this.field_0002);
            CheatBreaker.getInstance()
               .getModuleManager()
               .notifications
               .queueNotification("info", EnumChatFormatting.GREEN + "Updated custom string value successfully.", 654447496L & -855667866957407352L);
            ((CBModulesGui)this.field_0002).currentScrollableElement = ((CBModulesGui)this.field_0002).field_0017;
            CheatBreaker.getInstance().method_19818().method_04443(var3.field_0028);
            break;
         default:
            this.field_0003.textboxKeyTyped(var1, var2);
      }
   }

   @Override
   public void updateScreen() {
      this.field_0003.updateCursorCounter();
   }
}
