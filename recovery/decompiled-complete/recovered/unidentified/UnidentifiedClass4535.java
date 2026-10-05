package recovered.unidentified;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.common.base.Preconditions;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.play.server.S21PacketChunkData;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public abstract class UnidentifiedClass4535 extends GuiScreen {
   public Consumer<UnidentifiedClass0127> field_0003;
   public int field_0005;
   public int field_0000;
   public UnidentifiedClass0127[] field_0001 = new UnidentifiedClass0127[8];
   public static int field_0006;
   public UnidentifiedClass4818[] field_0004 = new UnidentifiedClass4818[8];
   public S21PacketChunkData field_0002;

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.field_0000++;
      if (!Keyboard.isKeyDown(this.field_0005)) {
         if (this.field_0003 != null) {
            for (int var1 = 0; var1 < this.field_0004.length; var1++) {
               UnidentifiedClass4818 var2 = this.field_0004[var1];
               UnidentifiedClass0127 var3 = this.field_0001[var1];
               ScaledResolution var4 = new ScaledResolution(this.j);
               int var5 = var4.getScaledWidth();
               int var6 = var4.getScaledHeight();
               if (var3 != null
                  && var3.method_00994().toString().contains("minecraft:")
                  && var2.method_28761((float)Mouse.getX() * var5 / this.j.displayHeight, (float)Mouse.getY() * var6 / this.j.displayWidth - 1.0F)) {
                  this.field_0003.accept(var3);
                  break;
               }
            }
         }

         this.j.displayGuiScreen(null);
      }
   }

   public UnidentifiedClass4535(int var1, List<UnidentifiedClass0127> var2) {
      this.field_0000 = 0;
      Preconditions.checkNotNull(var2, "options");
      Preconditions.checkArgument(var2.size() <= 8, "cannot have more than 8 options");

      for (int var3 = 0; var3 < var2.size(); var3++) {
         this.field_0001[var3] = (UnidentifiedClass0127)var2.get(var3);
      }

      for (int var5 = 0; var5 < this.field_0004.length; var5++) {
         UnidentifiedClass0127 var4 = null;
         if (var5 < var2.size()) {
            var4 = (UnidentifiedClass0127)var2.get(var5);
         }

         this.field_0004[var5] = new UnidentifiedClass4818(this, var5, var4);
      }

      this.field_0005 = var1;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      ScaledResolution var4 = new ScaledResolution(this.j);
      int var5 = var4.getScaledWidth();
      int var6 = var4.getScaledHeight();

      for (UnidentifiedClass4818 var10 : this.field_0004) {
         if (var10 != null) {
            var10.handleElementDraw(var1, var2, true);
         }
      }

      float var11 = 10.0F;
      float var12 = this.field_0000 >= var11 ? 1.0F : this.field_0000 / var11;
      GL11.glPushMatrix();
      GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.5F * var12);
      RenderUtil.method_22055(var5 / 2.0F, var6 / 2.0F, 90.0, 88.0, 100.0, 100, 100.0);
      RenderUtil.method_22055(var5 / 2.0F, var6 / 2.0F, 20.0, 18.0, 100.0, 100, 100.0);
      GL11.glPopMatrix();
   }
}
