package net.minecraft.client.gui.stream;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.IStream;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.ChatUserMode;
import tv.twitch.chat.ChatUserSubscription;

public class GuiTwitchUserMode extends GuiScreen {
   public static EnumChatFormatting field_152331_a = EnumChatFormatting.DARK_GREEN;
   public IStream stream;
   public static EnumChatFormatting field_152335_f = EnumChatFormatting.RED;
   public List<IChatComponent> field_152332_r = Lists.newArrayList();
   public static EnumChatFormatting field_152336_g = EnumChatFormatting.DARK_PURPLE;
   public int field_152334_t;
   public ChatUserInfo field_152337_h;
   public IChatComponent field_152338_i;

   @Override
   public void initGui() {
      int var1 = this.l / 3;
      int var2 = var1 - 130;
      this.n.add(new GuiButton(1, var1 * 0 + var2 / 2, this.m - 70, 130, 20, I18n.format("stream.userinfo.timeout")));
      this.n.add(new GuiButton(0, var1 * 1 + var2 / 2, this.m - 70, 130, 20, I18n.format("stream.userinfo.ban")));
      this.n.add(new GuiButton(2, var1 * 2 + var2 / 2, this.m - 70, 130, 20, I18n.format("stream.userinfo.mod")));
      this.n.add(new GuiButton(5, var1 * 0 + var2 / 2, this.m - 45, 130, 20, I18n.format("gui.cancel")));
      this.n.add(new GuiButton(3, var1 * 1 + var2 / 2, this.m - 45, 130, 20, I18n.format("stream.userinfo.unban")));
      this.n.add(new GuiButton(4, var1 * 2 + var2 / 2, this.m - 45, 130, 20, I18n.format("stream.userinfo.unmod")));
      int var3 = 0;

      for (IChatComponent var5 : this.field_152332_r) {
         var3 = Math.max(var3, this.q.getStringWidth(var5.getFormattedText()));
      }

      this.field_152334_t = this.l / 2 - var3 / 2;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.field_152338_i.getUnformattedText(), this.l / 2, 70, 16777215);
      int var4 = 80;

      for (IChatComponent var6 : this.field_152332_r) {
         this.drawString(this.q, var6.getFormattedText(), this.field_152334_t, var4, 16777215);
         var4 += this.q.FONT_HEIGHT;
      }

      super.drawScreen(var1, var2, var3);
   }

   public GuiTwitchUserMode(IStream var1, ChatUserInfo var2) {
      this.stream = var1;
      this.field_152337_h = var2;
      this.field_152338_i = new ChatComponentText(var2.displayName);
      this.field_152332_r.addAll(func_152328_a(var2.modes, var2.subscriptions, var1));
   }

   public static IChatComponent func_152330_a(ChatUserSubscription var0, String var1, boolean var2) {
      ChatComponentTranslation var3 = null;
      if (var0 == ChatUserSubscription.TTV_CHAT_USERSUB_SUBSCRIBER) {
         if (var1 == null) {
            var3 = new ChatComponentTranslation("stream.user.subscription.subscriber");
         } else if (var2) {
            var3 = new ChatComponentTranslation("stream.user.subscription.subscriber.self");
         } else {
            var3 = new ChatComponentTranslation("stream.user.subscription.subscriber.other", var1);
         }

         var3.getChatStyle().setColor(field_152331_a);
      } else if (var0 == ChatUserSubscription.TTV_CHAT_USERSUB_TURBO) {
         var3 = new ChatComponentTranslation("stream.user.subscription.turbo");
         var3.getChatStyle().setColor(field_152336_g);
      }

      return var3;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 0) {
            this.stream.func_152917_b("/ban " + this.field_152337_h.displayName);
         } else if (var1.k == 3) {
            this.stream.func_152917_b("/unban " + this.field_152337_h.displayName);
         } else if (var1.k == 2) {
            this.stream.func_152917_b("/mod " + this.field_152337_h.displayName);
         } else if (var1.k == 4) {
            this.stream.func_152917_b("/unmod " + this.field_152337_h.displayName);
         } else if (var1.k == 1) {
            this.stream.func_152917_b("/timeout " + this.field_152337_h.displayName);
         }

         this.j.displayGuiScreen((GuiScreen)null);
      }
   }

   public static IChatComponent func_152329_a(ChatUserMode var0, String var1, boolean var2) {
      ChatComponentTranslation var3 = null;
      if (var0 == ChatUserMode.TTV_CHAT_USERMODE_ADMINSTRATOR) {
         var3 = new ChatComponentTranslation("stream.user.mode.administrator");
         var3.getChatStyle().setColor(field_152336_g);
      } else if (var0 == ChatUserMode.TTV_CHAT_USERMODE_BANNED) {
         if (var1 == null) {
            var3 = new ChatComponentTranslation("stream.user.mode.banned");
         } else if (var2) {
            var3 = new ChatComponentTranslation("stream.user.mode.banned.self");
         } else {
            var3 = new ChatComponentTranslation("stream.user.mode.banned.other", var1);
         }

         var3.getChatStyle().setColor(field_152335_f);
      } else if (var0 == ChatUserMode.TTV_CHAT_USERMODE_BROADCASTER) {
         if (var1 == null) {
            var3 = new ChatComponentTranslation("stream.user.mode.broadcaster");
         } else if (var2) {
            var3 = new ChatComponentTranslation("stream.user.mode.broadcaster.self");
         } else {
            var3 = new ChatComponentTranslation("stream.user.mode.broadcaster.other");
         }

         var3.getChatStyle().setColor(field_152331_a);
      } else if (var0 == ChatUserMode.TTV_CHAT_USERMODE_MODERATOR) {
         if (var1 == null) {
            var3 = new ChatComponentTranslation("stream.user.mode.moderator");
         } else if (var2) {
            var3 = new ChatComponentTranslation("stream.user.mode.moderator.self");
         } else {
            var3 = new ChatComponentTranslation("stream.user.mode.moderator.other", var1);
         }

         var3.getChatStyle().setColor(field_152331_a);
      } else if (var0 == ChatUserMode.TTV_CHAT_USERMODE_STAFF) {
         var3 = new ChatComponentTranslation("stream.user.mode.staff");
         var3.getChatStyle().setColor(field_152336_g);
      }

      return var3;
   }

   public static List<IChatComponent> func_152328_a(Set<ChatUserMode> var0, Set<ChatUserSubscription> var1, IStream var2) {
      String var3 = var2 == null ? null : var2.func_152921_C();
      boolean var4 = var2 != null && var2.func_152927_B();
      ArrayList var5 = Lists.newArrayList();

      for (ChatUserMode var7 : var0) {
         IChatComponent var8 = func_152329_a(var7, var3, var4);
         if (var8 != null) {
            ChatComponentText var9 = new ChatComponentText("- ");
            var9.appendSibling(var8);
            var5.add(var9);
         }
      }

      for (ChatUserSubscription var11 : var1) {
         IChatComponent var12 = func_152330_a(var11, var3, var4);
         if (var12 != null) {
            ChatComponentText var13 = new ChatComponentText("- ");
            var13.appendSibling(var12);
            var5.add(var13);
         }
      }

      return var5;
   }
}
