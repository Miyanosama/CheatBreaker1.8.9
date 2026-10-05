package net.minecraft.client.stream;

import tv.twitch.ErrorCode;
import tv.twitch.chat.IChatAPIListener;

public class ChatController$1 implements IChatAPIListener {
   public void chatEmoticonDataDownloadCallback(ErrorCode var1) {
      if (ErrorCode.succeeded(var1)) {
         this.field_176000_a.func_152988_s();
      }
   }

   public void chatInitializationCallback(ErrorCode var1) {
      if (ErrorCode.succeeded(var1)) {
         this.field_176000_a.field_153008_f.setMessageFlushInterval(this.field_176000_a.field_175993_n);
         this.field_176000_a.field_153008_f.setUserChangeEventInterval(this.field_176000_a.field_175994_o);
         this.field_176000_a.func_153001_r();
         this.field_176000_a.func_175985_a(ChatController$ChatState.Initialized);
      } else {
         this.field_176000_a.func_175985_a(ChatController$ChatState.Uninitialized);
      }

      try {
         if (this.field_176000_a.field_153003_a != null) {
            this.field_176000_a.field_153003_a.func_176023_d(var1);
         }
      } catch (Exception var3) {
         this.field_176000_a.func_152995_h(var3.toString());
      }
   }

   public ChatController$1(ChatController var1) {
      this.field_176000_a = var1;
      super();
   }

   public void chatShutdownCallback(ErrorCode var1) {
      if (ErrorCode.succeeded(var1)) {
         ErrorCode var2 = this.field_176000_a.field_175992_e.shutdown();
         if (ErrorCode.failed(var2)) {
            String var3 = ErrorCode.getString(var2);
            this.field_176000_a.func_152995_h(String.format("Error shutting down the Twitch sdk: %s", var3));
         }

         this.field_176000_a.func_175985_a(ChatController$ChatState.Uninitialized);
      } else {
         this.field_176000_a.func_175985_a(ChatController$ChatState.Initialized);
         this.field_176000_a.func_152995_h(String.format("Error shutting down Twith chat: %s", var1));
      }

      try {
         if (this.field_176000_a.field_153003_a != null) {
            this.field_176000_a.field_153003_a.func_176022_e(var1);
         }
      } catch (Exception var4) {
         this.field_176000_a.func_152995_h(var4.toString());
      }
   }
}
