package com.cheatbreaker.client.ui.overlay.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.CosineFade;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.util.friend.Friend;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreenAddServer;
import net.minecraft.client.gui.spectator.BaseSpectatorGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenForest;
import org.apache.log4j.helpers.OnlyOnceErrorHandler;
import org.apache.log4j.spi.Filter;

public class AliasesElement extends DraggableElement {
   public GuiScreenAddServer field_0004;
   public Friend friend;
   public OnlyOnceErrorHandler field_0003;
   public List<String> aliases = new ArrayList<>();
   public ScrollableElement scrollContainer = new ScrollableElement(this);
   public CosineFade cosineFade;
   public Filter field_0008;
   public FlatButtonElement closeButton;
   public BaseSpectatorGroup field_0002;
   public BiomeGenForest field_0009;

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      this.scrollContainer.setElementSize(var1 + var3 - 4.0F, var2, 4.0F, var4);
      this.scrollContainer.setScrollAmount(var4);
      this.closeButton.setElementSize(var1 + var3 - 12.0F, var2 + 2.0F, 10.0F, 10.0F);
   }

   public AliasesElement(Friend var1) {
      this.friend = var1;
      this.closeButton = new FlatButtonElement("X");
      this.cosineFade = new CosineFade(-8914128080021207042L & 26740188L);
      this.cosineFade.method_20200();
      this.cosineFade.method_21234();
   }

   public List<String> getAliases() {
      return this.aliases;
   }

   public float method_06340() {
      return this.cosineFade.method_21227() * 2.0F - 1.0F;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         this.scrollContainer.handleElementMouseClicked(var1, var2, var3, var4);
         this.closeButton.handleElementMouseClicked(var1, var2, var3, var4);
         if (this.closeButton.a_(var1, var2)) {
            this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            OverlayGui.getInstance().removeElements(this);
            return true;
         } else {
            if (this.a_(var1, var2)) {
               this.updateDraggingPosition(var1, var2);
            }

            return false;
         }
      }
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      this.drag(var1, var2);
      this.scrollContainer.drawScrollable(var1, var2, var3);
      Gui.drawBoxWithOutLine(this.x, this.y, this.x + this.width, this.y + this.height, 0.5F, -16777216, -14869219);
      CheatBreaker.getInstance().playRegular14px.drawString(this.friend.getName(), this.x + 4.0F, this.y + 4.0F, -1);
      Gui.drawRect(this.x + 3.0F, this.y + 15.0F, this.x + this.width - 3.0F, this.y + 15.5F, 805306367);
      if (this.aliases.isEmpty()) {
         Gui.drawRect(this.x + 4.0F, this.y + this.height - 9.0F, this.x + this.width - 4.0F, this.y + this.height - 5.0F, -13158601);
         float var4 = this.x + this.width / 2.0F - 10.0F + (this.width - 28.0F) * this.method_06340() / 2.0F;
         Gui.drawRect(var4, this.y + this.height - 9.0F, var4 + 20.0F, this.y + this.height - 5.0F, -4180940);
      }

      int var7 = 0;

      for (String var6 : this.aliases) {
         float var10002 = this.x + 4.0F;
         CheatBreaker.getInstance().playRegular14px.drawString(var6, var10002, this.y + 18.0F + var7 * 10, -1);
         var7++;
      }

      this.scrollContainer.handleElementDraw(var1, var2, var3);
      this.closeButton.handleElementDraw(var1, var2, var3);
   }

   public Friend getFriend() {
      return this.friend;
   }
}
