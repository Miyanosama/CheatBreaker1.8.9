package com.cheatbreaker.client.ui.overlay.friend;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.element.ScrollableElement;
import com.cheatbreaker.client.ui.overlay.Alert;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import com.cheatbreaker.client.ui.overlay.element.FlatButtonElement;
import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.websocket.client.WSPacketClientRequestsStatus;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class FriendRequestListElement extends ElementListElement<FriendRequestElement> {
   public ScaledResolution field_0006;
   public FlatButtonElement toggleRequests;
   public LayerCustomHead field_0002;
   public ScrollableElement scrollableElement;
   public InputFieldElement filter;
   public List<FriendRequestElement> friendRequestElements = new ArrayList<>();
   public FlatButtonElement addButton;
   public InputFieldElement username;
   public SlotCrafting field_0004;

   public void resetSize() {
      this.setElementSize(this.x, this.y, this.width, this.height);
   }

   @Override
   public boolean handleElementMouseRelease(float var1, float var2, int var3, boolean var4) {
      if (!var4) {
         return false;
      } else {
         this.filter.handleElementMouseRelease(var1, var2 - this.scrollableElement.getHeight(), var3, var4);
         this.username.handleElementMouseRelease(var1, var2 - this.scrollableElement.getHeight(), var3, var4);
         this.addButton.handleElementMouseRelease(var1, var2 - this.scrollableElement.getHeight(), var3, var4);
         this.scrollableElement.handleElementMouseRelease(var1, var2 - this.scrollableElement.getHeight(), var3, var4);
         boolean var5 = false;

         for (FriendRequestElement var7 : this.elements) {
            if (this.isFilterMatch(var7)) {
               if (var5) {
                  break;
               }

               var5 = var7.handleElementMouseRelease(var1, var2 - this.scrollableElement.getHeight(), var3, var4);
            }
         }

         return var5;
      }
   }

   public FriendRequestListElement(List var1) {
      super(var1);
      this.filter = new InputFieldElement(CheatBreaker.getInstance().playRegular14px, "Filter", 805306367, 1879048191);
      this.username = new InputFieldElement(CheatBreaker.getInstance().playRegular14px, "Username", 805306367, 1879048191);
      this.addButton = new FlatButtonElement("ADD");
      this.toggleRequests = new FlatButtonElement("");
      this.scrollableElement = new ScrollableElement(this);
   }

   public FlatButtonElement method_01199() {
      return this.addButton;
   }

   @Override
   public boolean handleElementMouseClicked(float var1, float var2, int var3, boolean var4) {
      this.filter.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
      this.username.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
      if (this.filter.method_06013() && var3 == 1 && this.filter.getText().equals("")) {
         this.resetSize();
      }

      if (!var4) {
         return false;
      } else {
         this.addButton.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
         this.toggleRequests.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
         this.scrollableElement.handleElementMouseClicked(var1, var2, var3, var4);
         if (this.addButton.a_(var1, var2 - this.scrollableElement.method_12074())) {
            this.method_01202();
         }

         if (this.toggleRequests.a_(var1, var2 - this.scrollableElement.method_12074())) {
            CheatBreaker.getInstance()
               .getAssetsWebSocket()
               .sentToServer(new WSPacketClientRequestsStatus(!CheatBreaker.getInstance().isAcceptingFriendRequests()));
            CheatBreaker.getInstance().setAcceptingFriendRequests(!CheatBreaker.getInstance().isAcceptingFriendRequests());
            return false;
         } else {
            boolean var5 = false;

            for (FriendRequestElement var7 : this.elements) {
               if (this.isFilterMatch(var7)) {
                  if (var5) {
                     break;
                  }

                  var5 = var7.handleElementMouseClicked(var1, var2 - this.scrollableElement.method_12074(), var3, var4);
               }
            }

            return var5;
         }
      }
   }

   @Override
   public void handleElementUpdate() {
      this.filter.handleElementUpdate();
      this.username.handleElementUpdate();
      this.toggleRequests.handleElementUpdate();
      this.addButton.handleElementUpdate();
      this.scrollableElement.handleElementUpdate();
   }

   @Override
   public void handleElementKeyTyped(char var1, int var2) {
      super.handleElementKeyTyped(var1, var2);
      this.filter.handleElementKeyTyped(var1, var2);
      this.username.handleElementKeyTyped(var1, var2);
      this.toggleRequests.handleElementKeyTyped(var1, var2);
      this.addButton.handleElementKeyTyped(var1, var2);
      this.scrollableElement.handleElementKeyTyped(var1, var2);
      if (this.username.method_06013() && var2 == 28) {
         this.method_01202();
      }

      this.setElementSize(this.x, this.y, this.width, this.height);
   }

   @Override
   public void handleElementMouse() {
      this.scrollableElement.handleElementMouse();
   }

   @Override
   public void handleElementDraw(float var1, float var2, boolean var3) {
      if (!this.friendRequestElements.isEmpty()) {
         this.elements.removeAll(this.friendRequestElements);
         OverlayGui.getInstance().getFriendRequestsElement().resetSize();
         this.friendRequestElements.clear();
      }

      if (!CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         float var10002 = this.x + this.width / 2.0F;
         float var10003 = this.y + 10.0F;
         CheatBreaker.getInstance().field_0039.drawCenteredString("Connection lost", var10002, var10003, -1);
         var10002 = this.x + this.width / 2.0F;
         var10003 = this.y + 22.0F;
         CheatBreaker.getInstance().playRegular14px.drawCenteredString("Please try again later.", var10002, var10003, -1);
      } else {
         GL11.glPushMatrix();
         GL11.glEnable(3089);
         OverlayGui var4 = OverlayGui.getInstance();
         this.scrollableElement.drawScrollable(var1, var2, var3);
         RenderUtil.method_22061(
            (int)this.x,
            (int)this.y,
            (int)(this.x + this.width),
            (int)(this.y + this.height),
            (int)(var4.getResolution().getScaleFactor() * var4.getScaleFactor()),
            (int)var4.getScaledHeight()
         );
         GL11.glDisable(3089);
         GL11.glPopMatrix();
         ImmutableList var5 = ImmutableList.copyOf(this.elements);
         UnmodifiableIterator var6 = var5.iterator();

         while (var6.hasNext()) {
            FriendRequestElement var7 = (FriendRequestElement)var6.next();
            if (this.isFilterMatch(var7)) {
               var7.drawElement(var1, var2 - this.scrollableElement.getHeight(), var3);
            }
         }

         if (var5.isEmpty()) {
            float var9 = this.x + this.width / 2.0F;
            float var11 = this.y + 30.0F;
            CheatBreaker.getInstance().field_0039.drawCenteredString("No friend requests", var9, var11, -1);
         }

         this.filter.drawElement(var1, var2 - this.scrollableElement.getHeight(), true);
         this.username.drawElement(var1, var2, true);
         this.addButton.drawElement(var1, var2, true);
         this.toggleRequests
            .method_23821((CheatBreaker.getInstance().isAcceptingFriendRequests() ? "Disable" : "Enable") + " incoming friend requests", var1, var2, true);
         this.scrollableElement.handleElementDraw(var1, var2, var3);
      }
   }

   @Override
   public List<FriendRequestElement> getElements() {
      return this.friendRequestElements;
   }

   @Override
   public void handleElementClose() {
      this.filter.handleElementClose();
      this.scrollableElement.handleElementClose();
   }

   @Override
   public void setElementSize(float var1, float var2, float var3, float var4) {
      super.setElementSize(var1, var2, var3, var4);
      this.scrollableElement.setElementSize(var1 + var3 - 4.0F, var2, 4.0F, var4);
      byte var5 = 22;
      int var6 = 0;

      for (FriendRequestElement var8 : this.elements) {
         if (this.isFilterMatch(var8)) {
            var8.setElementSize(var1, var2 + 14.0F + var6 * 22, var3, 22.0F);
            var6++;
         }
      }

      float var9 = 14 + this.elements.size() * 22 + 30;
      if (var9 < var4) {
         var9 = var4;
      }

      this.filter.setElementSize(0.0F, var2, var3, 13.0F);
      this.username.setElementSize(0.0F, var2 + var9 - 13.0F, var3 - 35.0F, 13.0F);
      this.addButton.setElementSize(var3 - 35.0F, var2 + var9 - 13.0F, 35.0F, 13.0F);
      this.toggleRequests.setElementSize(0.0F, var2 + var9 - 26.0F, var3, 13.0F);
      this.scrollableElement.setScrollAmount(var9);
   }

   public void method_01202() {
      if (!this.username.getText().isEmpty()) {
         this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         String var1 = this.username.getText();
         if (var1.matches("([a-zA-Z0-9_]+)") && var1.length() <= 16) {
            CheatBreaker.getInstance().getAssetsWebSocket().sentToServer(new WSPacketFriendRequest("", this.username.getText()));
            this.username.setText("");
         } else {
            Alert.displayMessage(EnumChatFormatting.RED + "Error!", "Incorrect username.");
         }
      }
   }

   public boolean isFilterMatch(FriendRequestElement var1) {
      return this.filter.getText().equals("")
         || EnumChatFormatting.getTextWithoutFormattingCodes(var1.getFriendRequest().getUsername())
            .toLowerCase()
            .startsWith(this.filter.getText().toLowerCase());
   }

   public FlatButtonElement method_01201() {
      return this.toggleRequests;
   }
}
