package net.minecraft.client.stream;

import com.google.common.collect.Lists;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import recovered.unidentified.UnidentifiedClass3177;
import tv.twitch.ErrorCode;
import tv.twitch.chat.ChatBadgeData;
import tv.twitch.chat.ChatChannelInfo;
import tv.twitch.chat.ChatEvent;
import tv.twitch.chat.ChatRawMessage;
import tv.twitch.chat.ChatTokenizedMessage;
import tv.twitch.chat.ChatUserInfo;
import tv.twitch.chat.IChatChannelListener;

public class ChatController$ChatChannelListener implements IChatChannelListener {
   public boolean field_176046_b;
   public List<ChatUserInfo> field_176044_d;
   public String field_176048_a;
   public ChatBadgeData field_176043_g;
   public LinkedList<ChatRawMessage> field_176045_e;
   public UnidentifiedClass3177 field_0008;
   public LinkedList<ChatTokenizedMessage> field_176042_f;
   public ChatController$EnumChannelState field_176047_c;

   public void chatBadgeDataDownloadCallback(String var1, ErrorCode var2) {
      if (ErrorCode.succeeded(var2)) {
         this.func_176039_i();
      }
   }

   public void func_176033_j() {
      if (this.field_176043_g != null) {
         ErrorCode var1 = this.field_176049_h.field_153008_f.clearBadgeData(this.field_176048_a);
         if (ErrorCode.succeeded(var1)) {
            this.field_176043_g = null;

            try {
               if (this.field_176049_h.field_153003_a != null) {
                  this.field_176049_h.field_153003_a.method_07710(this.field_176048_a);
               }
            } catch (Exception var3) {
               this.field_176049_h.func_152995_h(var3.toString());
            }
         } else {
            this.field_176049_h.func_152995_h("Error releasing badge data: " + ErrorCode.getString(var1));
         }
      }
   }

   public void chatChannelMembershipCallback(String var1, ChatEvent var2, ChatChannelInfo var3) {
      switch (ChatController$2.field_0002[var2.ordinal()]) {
         case 1:
            this.func_176035_a(ChatController$EnumChannelState.Connected);
            this.method_24597(var1);
            break;
         case 2:
            this.func_176030_k();
      }
   }

   public void func_176036_d(String var1) {
      try {
         if (this.field_176049_h.field_153003_a != null) {
            this.field_176049_h.field_153003_a.func_180607_b(var1);
         }
      } catch (Exception var3) {
         this.field_176049_h.func_152995_h(var3.toString());
      }
   }

   public void func_176030_k() {
      if (this.field_176047_c != ChatController$EnumChannelState.Disconnected) {
         this.func_176035_a(ChatController$EnumChannelState.Disconnected);
         this.func_176036_d(this.field_176048_a);
         this.func_176033_j();
      }
   }

   public void chatClearCallback(String var1, String var2) {
      this.func_176032_a(var2);
   }

   public boolean func_176037_b(String var1) {
      if (this.field_176047_c != ChatController$EnumChannelState.Connected) {
         return false;
      } else {
         ErrorCode var2 = this.field_176049_h.field_153008_f.sendMessage(this.field_176048_a, var1);
         if (ErrorCode.failed(var2)) {
            String var3 = ErrorCode.getString(var2);
            this.field_176049_h.func_152995_h(String.format("Error sending chat message: %s", var3));
            return false;
         } else {
            return true;
         }
      }
   }

   public void func_176041_h() {
      if (this.field_176049_h.field_175995_l != ChatController$EnumEmoticonMode.None && this.field_176043_g == null) {
         ErrorCode var1 = this.field_176049_h.field_153008_f.downloadBadgeData(this.field_176048_a);
         if (ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.field_176049_h.func_152995_h(String.format("Error trying to download badge data: %s", var2));
         }
      }
   }

   public void chatChannelRawMessageCallback(String var1, ChatRawMessage[] var2) {
      for (int var3 = 0; var3 < var2.length; var3++) {
         this.field_176045_e.addLast(var2[var3]);
      }

      try {
         if (this.field_176049_h.field_153003_a != null) {
            this.field_176049_h.field_153003_a.func_180605_a(this.field_176048_a, var2);
         }
      } catch (Exception var4) {
         this.field_176049_h.func_152995_h(var4.toString());
      }

      while (this.field_176045_e.size() > this.field_176049_h.field_153015_m) {
         this.field_176045_e.removeFirst();
      }
   }

   public boolean func_176034_g() {
      switch (ChatController$2.field_175976_a[this.field_176047_c.ordinal()]) {
         case 1:
         case 2:
            ErrorCode var1 = this.field_176049_h.field_153008_f.disconnect(this.field_176048_a);
            if (ErrorCode.failed(var1)) {
               String var2 = ErrorCode.getString(var1);
               this.field_176049_h.func_152995_h(String.format("Error disconnecting: %s", var2));
               return false;
            }

            this.func_176035_a(ChatController$EnumChannelState.Disconnecting);
            return true;
         case 3:
         case 4:
         case 5:
         default:
            return false;
      }
   }

   public void chatStatusCallback(String var1, ErrorCode var2) {
      if (!ErrorCode.succeeded(var2)) {
         this.field_176049_h.field_175998_i.remove(var1);
         this.func_176030_k();
      }
   }

   public void method_24597(String var1) {
      try {
         if (this.field_176049_h.field_153003_a != null) {
            this.field_176049_h.field_153003_a.func_180606_a(var1);
         }
      } catch (Exception var3) {
         this.field_176049_h.func_152995_h(var3.toString());
      }
   }

   public void func_176032_a(String var1) {
      if (this.field_176049_h.field_175995_l == ChatController$EnumEmoticonMode.None) {
         this.field_176045_e.clear();
         this.field_176042_f.clear();
      } else {
         if (this.field_176045_e.size() > 0) {
            ListIterator var2 = this.field_176045_e.listIterator();

            while (var2.hasNext()) {
               ChatRawMessage var3 = (ChatRawMessage)var2.next();
               if (var3.userName.equals(var1)) {
                  var2.remove();
               }
            }
         }

         if (this.field_176042_f.size() > 0) {
            ListIterator var5 = this.field_176042_f.listIterator();

            while (var5.hasNext()) {
               ChatTokenizedMessage var6 = (ChatTokenizedMessage)var5.next();
               if (var6.displayName.equals(var1)) {
                  var5.remove();
               }
            }
         }
      }

      try {
         if (this.field_176049_h.field_153003_a != null) {
            this.field_176049_h.field_153003_a.func_176019_a(this.field_176048_a, var1);
         }
      } catch (Exception var4) {
         this.field_176049_h.func_152995_h(var4.toString());
      }
   }

   public ChatController$ChatChannelListener(ChatController var1, String var2) {
      this.field_176049_h = var1;
      super();
      this.field_176048_a = null;
      this.field_176046_b = false;
      this.field_176047_c = ChatController$EnumChannelState.Created;
      this.field_176044_d = Lists.newArrayList();
      this.field_176045_e = new LinkedList<>();
      this.field_176042_f = new LinkedList<>();
      this.field_176043_g = null;
      this.field_176048_a = var2;
   }

   public void func_176039_i() {
      if (this.field_176043_g == null) {
         this.field_176043_g = new ChatBadgeData();
         ErrorCode var1 = this.field_176049_h.field_153008_f.getBadgeData(this.field_176048_a, this.field_176043_g);
         if (ErrorCode.succeeded(var1)) {
            try {
               if (this.field_176049_h.field_153003_a != null) {
                  this.field_176049_h.field_153003_a.method_07705(this.field_176048_a);
               }
            } catch (Exception var3) {
               this.field_176049_h.func_152995_h(var3.toString());
            }
         } else {
            this.field_176049_h.func_152995_h("Error preparing badge data: " + ErrorCode.getString(var1));
         }
      }
   }

   public void func_176035_a(ChatController$EnumChannelState var1) {
      if (var1 != this.field_176047_c) {
         this.field_176047_c = var1;
      }
   }

   public boolean func_176038_a(boolean var1) {
      this.field_176046_b = var1;
      ErrorCode var2 = ErrorCode.TTV_EC_SUCCESS;
      if (var1) {
         var2 = this.field_176049_h.field_153008_f.connectAnonymous(this.field_176048_a, this);
      } else {
         var2 = this.field_176049_h
            .field_153008_f
            .connect(this.field_176048_a, this.field_176049_h.field_153004_b, this.field_176049_h.field_153012_j.data, this);
      }

      if (ErrorCode.failed(var2)) {
         String var3 = ErrorCode.getString(var2);
         this.field_176049_h.func_152995_h(String.format("Error connecting: %s", var3));
         this.func_176036_d(this.field_176048_a);
         return false;
      } else {
         this.func_176035_a(ChatController$EnumChannelState.Connecting);
         this.func_176041_h();
         return true;
      }
   }

   public void chatChannelUserChangeCallback(String var1, ChatUserInfo[] var2, ChatUserInfo[] var3, ChatUserInfo[] var4) {
      for (int var5 = 0; var5 < var3.length; var5++) {
         int var6 = this.field_176044_d.indexOf(var3[var5]);
         if (var6 >= 0) {
            this.field_176044_d.remove(var6);
         }
      }

      for (int var8 = 0; var8 < var4.length; var8++) {
         int var10 = this.field_176044_d.indexOf(var4[var8]);
         if (var10 >= 0) {
            this.field_176044_d.remove(var10);
         }

         this.field_176044_d.add(var4[var8]);
      }

      for (int var9 = 0; var9 < var2.length; var9++) {
         this.field_176044_d.add(var2[var9]);
      }

      try {
         if (this.field_176049_h.field_153003_a != null) {
            this.field_176049_h.field_153003_a.func_176018_a(this.field_176048_a, var2, var3, var4);
         }
      } catch (Exception var7) {
         this.field_176049_h.func_152995_h(var7.toString());
      }
   }

   public ChatController$EnumChannelState func_176040_a() {
      return this.field_176047_c;
   }

   public void chatChannelTokenizedMessageCallback(String var1, ChatTokenizedMessage[] var2) {
      for (int var3 = 0; var3 < var2.length; var3++) {
         this.field_176042_f.addLast(var2[var3]);
      }

      try {
         if (this.field_176049_h.field_153003_a != null) {
            this.field_176049_h.field_153003_a.func_176025_a(this.field_176048_a, var2);
         }
      } catch (Exception var4) {
         this.field_176049_h.func_152995_h(var4.toString());
      }

      while (this.field_176042_f.size() > this.field_176049_h.field_153015_m) {
         this.field_176042_f.removeFirst();
      }
   }
}
