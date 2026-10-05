package com.cheatbreaker.client.ui.overlay;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.element.ConsoleElement;
import com.cheatbreaker.client.ui.overlay.element.ElementListElement;
import com.cheatbreaker.client.ui.overlay.element.FlatButtonElement;
import com.cheatbreaker.client.ui.overlay.element.PrivateMessageElement;
import com.cheatbreaker.client.ui.overlay.element.RadioElement;
import com.cheatbreaker.client.ui.overlay.friend.FriendElement;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequestElement;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequestListElement;
import com.cheatbreaker.client.ui.overlay.friend.FriendsListElement;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.friend.Friend;
import com.cheatbreaker.client.util.friend.Status;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.optifine.player.PlayerConfigurationParser;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class OverlayGui extends AbstractGui {
   public FriendRequestListElement friendRequestsElement;
   public long initGuiMillis;
   public List<Alert> field_0003;
   public FriendsListElement friendsListElement;
   public PlayerConfigurationParser field_0009;
   public FlatButtonElement requestsButton;
   public RadioElement field_0010;
   public ElementListElement selectedElement;
   public PrivateMessageElement field_0002;
   public FlatButtonElement friendsButton;
   public static OverlayGui instance;
   public long revertToContextTime;
   public Queue<Alert> field_0001 = new LinkedList<>();
   public GuiScreen context;

   @Override
   public void drawMenu(float var1, float var2) {
      GL11.glClear(256);
      this.method_11295(this.getScaledWidth(), this.getScaledHeight());
      drawRect(0.0F, 0.0F, 140.0F, this.getScaledHeight(), -14671840);
      drawRect(140.0F, 0.0F, 141.0F, this.getScaledHeight(), -15395563);
      drawRect(0.0F, 0.0F, 140.0F, 28.0F, -15395563);
      a(6, 6, 22, 22, Friend.getStatusColor(CheatBreaker.getInstance().getStatus()));
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      ResourceLocation var3 = CheatBreaker.getInstance().method_19810(this.j.getSession().getUsername());
      RenderUtil.drawIcon(var3, 7.0F, 7.0F, 7.0F);
      CheatBreaker.getInstance().field_0036.drawString(this.j.getSession().getUsername(), 28.0F, 6.0F, -1);
      CheatBreaker.getInstance().field_0063.drawString(CheatBreaker.getInstance().method_19758(), 28.0F, 15.0F, -5460820);
      boolean var4 = var1 > 6.0F && var1 < 134.0F && var2 > 6.0F && var2 < 22.0F;
      if (this.isMouseHovered(this.friendsButton, var1, var2) && var4 && CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         drawRect(22.0F, 0.0F, 140.0F, 28.0F, -15395563);
         a(24, 6, 40, 22, Friend.getStatusColor(Status.ONLINE));
         a(42, 6, 58, 22, Friend.getStatusColor(Status.AWAY));
         a(60, 6, 76, 22, Friend.getStatusColor(Status.BUSY));
         a(78, 6, 94, 22, Friend.getStatusColor(Status.HIDDEN));
         GL11.glColor4f(0.15F, 0.15F, 0.15F, 1.0F);
         RenderUtil.drawIcon(var3, 7.0F, 25.0F, 7.0F);
         RenderUtil.drawIcon(var3, 7.0F, 43.0F, 7.0F);
         RenderUtil.drawIcon(var3, 7.0F, 61.0F, 7.0F);
         RenderUtil.drawIcon(var3, 7.0F, 79.0F, 7.0F);
      }

      this.selectedElement.drawElement(var1, var2, this.isMouseHovered(this.requestsButton, var1, var2));
      drawRect(69.5F, 28.0F, 70.5F, 28.0F + this.friendsButton.getHeight(), -14869219);
      drawRect(0.0F, 28.0F + this.friendsButton.getHeight(), 140.0F, 28.0F + this.friendsButton.getHeight() + 1.0F, -15395563);
      this.drawElements(var1, var2, this.friendsListElement, this.friendRequestsElement);
   }

   public void method_26658() {
      this.field_0003.removeIf(Alert::method_19900);
      if (!this.field_0001.isEmpty()) {
         boolean var1 = true;

         for (Alert var3 : this.field_0003) {
            if (!var3.method_19911()) {
               var1 = false;
            }
         }

         if (var1) {
            Alert var4 = this.field_0001.poll();
            var4.method_19910(this.getScaledHeight() - Alert.method_19909());
            this.field_0003.forEach(var0 -> var0.method_19910(var0.method_19899() - Alert.method_19909()));
            this.field_0003.add(var4);
         }
      }
   }

   public static void setInstance(OverlayGui var0) {
      instance = var0;
   }

   public static OverlayGui createInstance(GuiScreen var0) {
      if (var0 != instance) {
         getInstance().context = var0;
      }

      return getInstance();
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (var2 == 15 && Keyboard.isKeyDown(42) && System.currentTimeMillis() - this.initGuiMillis > (-1103131640420367652L & 343240L) || var2 == 1) {
         this.revertToContextTime = System.currentTimeMillis();
         this.j.displayGuiScreen(this.context);
      }

      this.handleKeyTyped(var1, var2);
      if (var2 == 59 && CheatBreaker.getInstance().method_19793()) {
         boolean var3 = true;

         for (AbstractElement var5 : this.field_0005) {
            if (var5 instanceof ConsoleElement) {
               var3 = false;
            }
         }

         if (var3) {
            AbstractElement[] var6 = new AbstractElement[1];
            ConsoleElement var7 = new ConsoleElement();
            var6[0] = var7;
            this.addElements(var6);
            var7.setElementSize(60.0F, 30.0F, 300.0F, 145.0F);
         }
      }
   }

   @Override
   public void setWorldAndResolution(Minecraft var1, int var2, int var3) {
      if (this.context != null) {
         this.context.setWorldAndResolution(var1, var2, var3);
      }

      float var4 = this.getScaledHeight();
      super.setWorldAndResolution(var1, var2, var3);
      this.field_0003.forEach(var2x -> this.renderAlert(var2x, this.getScaledHeight() - var4));
      this.field_0001.forEach(var2x -> this.renderAlert(var2x, this.getScaledHeight() - var4));
   }

   public FriendRequestListElement getFriendRequestsElement() {
      return this.friendRequestsElement;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.context != null) {
         this.context.drawScreen(-1, -1, var3);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void handleFriendRequest(FriendRequest var1, boolean var2) {
      if (var2) {
         this.friendRequestsElement.getElements().add(new FriendRequestElement(var1));
      } else {
         this.friendRequestsElement.getElements().removeIf(var1x -> var1x.getFriendRequest() == var1);
      }

      this.friendRequestsElement.resetSize();
   }

   public FriendsListElement getFriendsListElement() {
      return this.friendsListElement;
   }

   public void handleFriend(Friend var1, boolean var2) {
      if (var2) {
         this.friendsListElement.getElements().add(new FriendElement(var1));
      } else {
         this.friendsListElement.getElements().removeIf(var1x -> var1x.getFriend() == var1);
      }

      this.friendsListElement.updateSize();
   }

   public void queueAlert(String var1, String var2) {
      int var3 = Alert.method_19907();
      var2 = CheatBreaker.getInstance().playRegular14px.method_03181(var2, var3 - 10);
      Alert var4 = new Alert(var1, var2.split("\n"), this.getScaledHeight());
      var4.method_19906(var1.equals(""));
      this.field_0001.add(var4);
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
      this.context = null;
      this.method_02922();
      this.j.entityRenderer.stopUseShader();
   }

   public void method_26660() {
      this.field_0003.forEach(Alert::drawAlert);
      if (this.j != null
         && this.j.currentScreen == null
         && (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0034.getValue()
         && DashUtil.isPlayerNotNull()) {
         this.field_0010.drawElement(0.0F, 0.0F, false);
      }
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      this.handleMouse();
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.initGuiMillis = System.currentTimeMillis();
      if (CheatBreaker.getInstance().field_0020 != this) {
         this.method_11296();
      }

      this.friendsListElement.getElements().clear();

      for (Friend var2 : CheatBreaker.getInstance().getFriendsManager().getFriends().values()) {
         this.friendsListElement.getElements().add(new FriendElement(var2));
      }

      this.friendsButton.setElementSize(0.0F, 28.0F, 69.5F, 20.0F);
      this.requestsButton.setElementSize(70.5F, 28.0F, 69.5F, 20.0F);
      float var3 = 28.0F + this.friendsButton.getHeight() + 1.0F;
      this.friendsListElement.setElementSize(0.0F, var3, 140.0F, this.getScaledHeight() - var3);
      this.friendRequestsElement.setElementSize(0.0F, var3, 140.0F, this.getScaledHeight() - var3);
      float var4 = 190.0F;
      this.field_0010.setElementSize(this.getScaledWidth() - var4 - 20.0F, 20.0F, var4, 28.0F);
   }

   public void setSection(String var1) {
      this.queueAlert("", var1);
   }

   public long method_26661() {
      return this.revertToContextTime;
   }

   public void method_26645(Friend var1) {
      try {
         PrivateMessageElement var2 = null;

         for (AbstractElement var4 : this.field_0005) {
            if (var4 instanceof PrivateMessageElement) {
               var2 = (PrivateMessageElement)var4;
            }
         }

         if (var2 == null) {
            this.field_0002 = new PrivateMessageElement(var1);
            this.field_0005.add(this.field_0002);
            this.field_0002.setElementSize(170.0F, 30.0F, 245.0F, 150.0F);
         } else {
            this.field_0005.add(this.field_0005.remove(this.field_0005.indexOf(var2)));
            var2.method_01185(var1);
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   @Override
   public void updateScreen() {
      if (this.context != null) {
         this.context.updateScreen();
      }

      this.friendsButton.method_23820("FRIENDS (" + this.friendsListElement.getElements().size() + ")");
      this.requestsButton
         .method_23820("REQUESTS (" + this.friendRequestsElement.getElements().stream().filter(var0 -> !var0.getFriendRequest().isFriend()).count() + ")");
      this.method_02935();
   }

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
      this.handleMouseReleased(var1, var2, var3);
   }

   public void renderAlert(Alert var1, float var2) {
      var1.setX(this.getScaledWidth() - Alert.method_19907());
      var1.method_19908(var1.method_19902() + var2);
      var1.method_19901(var1.method_19899() + var2);
   }

   public OverlayGui() {
      this.field_0003 = new ArrayList<>();
      ArrayList var1 = new ArrayList();
      AbstractElement[] var2 = new AbstractElement[5];
      CheatBreaker.getInstance().getFriendsManager().getFriends().forEach((var1x, var2x) -> var1.add(new FriendElement(var2x)));
      this.friendsListElement = new FriendsListElement(var1);
      var2[0] = this.friendsListElement;
      this.friendRequestsElement = new FriendRequestListElement(new ArrayList());
      var2[1] = this.friendRequestsElement;
      this.requestsButton = new FlatButtonElement("REQUESTS");
      var2[2] = this.requestsButton;
      this.friendsButton = new FlatButtonElement("FRIENDS");
      var2[3] = this.friendsButton;
      this.field_0010 = new RadioElement();
      var2[4] = this.field_0010;
      this.setElementsAndUpdateSize(var2);
      this.setElements(this.friendsListElement, this.friendRequestsElement, this.requestsButton, this.friendsButton);
      this.selectedElement = this.friendsListElement;
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Loaded Overlay Gui");
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      this.selectedElement.handleElementMouseClicked(var1, var2, var3, this.isMouseHovered(this.requestsButton, var1, var2));
      this.onMouseClicked(var1, var2, var3, this.friendsListElement, this.friendRequestsElement);
      boolean var4 = this.isMouseHovered(this.friendsButton, var1, var2);
      if (var4 && this.friendsButton.a_(var1, var2) && this.selectedElement != this.friendsListElement) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.selectedElement = this.friendsListElement;
      } else if (var4 && this.requestsButton.a_(var1, var2) && this.selectedElement != this.friendRequestsElement) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.selectedElement = this.friendRequestsElement;
      }

      boolean var5 = var1 > 6.0F && var1 < 134.0F && var2 > 6.0F && var2 < 22.0F;
      if (var4 && var5 && CheatBreaker.getInstance().getAssetsWebSocket().isOpen()) {
         boolean var6 = var1 > 24.0F && var1 < 40.0F;
         boolean var7 = var1 > 42.0F && var1 < 58.0F;
         boolean var8 = var1 > 60.0F && var1 < 76.0F;
         boolean var9 = var1 > 78.0F && var1 < 94.0F;
         if (var6) {
            CheatBreaker.getInstance().setStatus(Status.ONLINE);
            this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         } else if (var7) {
            CheatBreaker.getInstance().setStatus(Status.AWAY);
            this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         } else if (var8) {
            CheatBreaker.getInstance().setStatus(Status.BUSY);
            this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         } else if (var9) {
            CheatBreaker.getInstance().setStatus(Status.HIDDEN);
            this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         }

         CheatBreaker.getInstance().getAssetsWebSocket().updateClientStatus();
      }
   }

   public static OverlayGui getInstance() {
      return instance;
   }
}
