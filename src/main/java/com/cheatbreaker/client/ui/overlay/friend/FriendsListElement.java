package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;

public class FriendsListElement extends ElementListElement<FriendElement> {
   public ScrollableElement scrollableElement;
   public InputFieldElement filterElement;
   public List<FriendElement> friendElements = new ArrayList<>();

   public void updateSize() {
      this.setElementSize(this.x, this.y, this.width, this.height);
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (!this.friendElements.isEmpty()) {
         this.elements.removeAll(this.friendElements);
         OverlayGui.getInstance().getFriendsListElement().updateSize();
      }

      if (!CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         float var10 = this.x + this.width / 2.0F;
         float var12 = this.y + 10.0F;
         CheatBreaker.getInstance().recoveredField1595.drawCenteredString("Connection lost", var10, var12, -1);
         var10 = this.x + this.width / 2.0F;
         var12 = this.y + 22.0F;
         CheatBreaker.getInstance().playRegular14px.drawCenteredString("Please try again later.", var10, var12, -1);
      } else {
         GL11.glPushMatrix();
         GL11.glEnable(3089);
         OverlayGui var6 = OverlayGui.getInstance();
         this.scrollableElement.drawScrollable(var1, var2, var3);
         RenderUtil.method_22061(
            (int)this.x,
            (int)this.y,
            (int)(this.x + this.width),
            (int)(this.y + this.height),
            (int)(var6.getResolution().getScaleFactor() * var6.getScaleFactor()),
            (int)var6.getScaledHeight()
         );
         ImmutableList var7 = ImmutableList.copyOf(this.elements);

         for (FriendElement var9 : (Iterable<FriendElement>)(Iterable<?>)(var7)) {
            if (this.isFilterMatch(var9)) {
               var9.drawElement(var1, var2 - this.scrollableElement.method_12074(), var3 && !this.scrollableElement.a_(var1, var2));
            }
         }

         if (var7.isEmpty()) {
            float var4 = this.x + this.width / 2.0F;
            float var5 = this.y + 30.0F;
            CheatBreaker.getInstance().recoveredField1595.drawCenteredString("No friends", var4, var5, -1);
         }

         this.filterElement.drawElement(var1, var2 - this.scrollableElement.method_12074(), var3);
         GL11.glDisable(3089);
         GL11.glPopMatrix();
         this.scrollableElement.handleElementDraw(var1, var2, var3);
      }
   }

   @Override
   public boolean handleElementMouseRelease(float var1, float var2, int var3, boolean var4) {
      this.filterElement.handleElementMouseRelease(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
      this.scrollableElement.handleElementMouseRelease(var1, var2, var3, var4);
      if (!var4) {
         return false;
      } else {
         boolean var5 = false;

         for (FriendElement var7 : this.elements) {
            if (this.isFilterMatch(var7)) {
               if (var5) {
                  break;
               }

               var5 = var7.handleElementMouseRelease(var1, var2, var3, var4);
            }
         }

         return var5;
      }
   }

   @Override
   public void handleElementMouse() {
      this.scrollableElement.handleElementMouse();
   }

   @Override
   public List<FriendElement> getElements() {
      return this.friendElements;
   }

   public boolean isFilterMatch(FriendElement var1) {
      return this.filterElement.getText().equals("")
         || EnumChatFormatting.getTextWithoutFormattingCodes(var1.getFriend().getName()).toLowerCase().startsWith(this.filterElement.getText().toLowerCase());
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      this.filterElement.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
      if (this.filterElement.method_06013() && var3 == 1 && this.filterElement.getText().equals("")) {
         this.updateSize();
      }

      if (!var4) {
         return false;
      } else {
         this.scrollableElement.handleElementMouseClicked(var1, var2, var3, var4);
         boolean var5 = false;

         for (FriendElement var7 : this.elements) {
            if (this.isFilterMatch(var7)) {
               if (var5) {
                  break;
               }

               var5 = var7.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4 && !this.scrollableElement.a_(var1, var2));
            }
         }

         return var5;
      }
   }

   public FriendsListElement(List<FriendElement> var1) {
      super(var1);
      this.filterElement = new InputFieldElement(CheatBreaker.getInstance().playRegular14px, "Filter", 805306367, 1879048191);
      this.scrollableElement = new ScrollableElement(this);
   }

   @Override
   public void handleElementUpdate() {
      this.filterElement.handleElementUpdate();
      this.scrollableElement.handleElementUpdate();
   }

   @Override
   public void handleElementClose() {
      this.filterElement.handleElementClose();
      this.scrollableElement.handleElementClose();
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      super.handleElementKeyTyped(var1, var2);
      this.filterElement.handleElementKeyTyped(var1, var2);
      this.scrollableElement.handleElementKeyTyped(var1, var2);
      this.setElementSize(this.x, this.y, this.width, this.height);
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      this.filterElement.setElementSize(0.0F, var2, var3, 13.0F);
      this.scrollableElement.setElementSize(var1 + var3 - 4.0F, var2, 4.0F, var4);
      this.elements.sort((var0, var1x) -> {
         String var2x = EnumChatFormatting.getTextWithoutFormattingCodes(var0.getFriend().getName());
         String var3x = EnumChatFormatting.getTextWithoutFormattingCodes(var1x.getFriend().getName());
         if (var0.getFriend().isOnline() == var1x.getFriend().isOnline()) {
            return var2x.compareTo(var3x);
         } else {
            return var0.getFriend().isOnline() ? -1 : 1;
         }
      });
      boolean var5 = true;
      int var6 = 0;

      for (FriendElement var8 : this.elements) {
         if (this.isFilterMatch(var8)) {
            var8.setElementSize(var1, var2 + 14.0F + var6 * 22, var3, 22.0F);
            var6++;
         }
      }

      this.scrollableElement.setScrollAmount(14 + this.elements.size() * 22);
   }
}
