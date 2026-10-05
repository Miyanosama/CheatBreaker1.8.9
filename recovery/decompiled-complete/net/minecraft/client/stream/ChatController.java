package net.minecraft.client.stream;

import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.EntityLivingBase;
import org.apache.log4j.spi.Filter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tv.twitch.AuthToken;
import tv.twitch.Core;
import tv.twitch.ErrorCode;
import tv.twitch.StandardCoreAPI;
import tv.twitch.chat.Chat;
import tv.twitch.chat.ChatEmoticonData;
import tv.twitch.chat.ChatTokenizationOption;
import tv.twitch.chat.IChatAPIListener;
import tv.twitch.chat.StandardChatAPI;

public class ChatController {
   public Chat field_153008_f;
   public int field_175994_o;
   public AuthToken field_153012_j;
   public ChatController$ChatState field_153011_i;
   public BlockFarmland field_0002;
   public ChatController$EnumEmoticonMode field_175995_l;
   public Core field_175992_e;
   public String field_153006_d;
   public HashMap<String, ChatController$ChatChannelListener> field_175998_i;
   public int field_175993_n;
   public String field_153004_b;
   public String field_153007_e;
   public static Logger LOGGER = LogManager.getLogger();
   public ChatEmoticonData field_175996_m;
   public int field_153015_m;
   public Filter field_0016;
   public IngestServerTester$IngestTestListener field_153003_a = null;
   public IChatAPIListener field_175999_p;
   public EntityLivingBase field_0010;
   public ChatController$EnumEmoticonMode field_175997_k;

   public void func_152998_c(String var1) {
      this.field_153004_b = var1;
   }

   public boolean func_152986_d(String var1) {
      return this.func_175987_a(var1, false);
   }

   public void func_152997_n() {
      if (this.field_153011_i != ChatController$ChatState.Uninitialized) {
         ErrorCode var1 = this.field_153008_f.flushEvents();
         if (ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error flushing chat events: %s", var2));
         }
      }
   }

   public ChatController$ChatState func_153000_j() {
      return this.field_153011_i;
   }

   public ChatController() {
      this.field_153004_b = "";
      this.field_153006_d = "";
      this.field_153007_e = "";
      this.field_175992_e = null;
      this.field_153008_f = null;
      this.field_153011_i = ChatController$ChatState.Uninitialized;
      this.field_153012_j = new AuthToken();
      this.field_175998_i = new HashMap<>();
      this.field_153015_m = 128;
      this.field_175997_k = ChatController$EnumEmoticonMode.None;
      this.field_175995_l = ChatController$EnumEmoticonMode.None;
      this.field_175996_m = null;
      this.field_175993_n = 500;
      this.field_175994_o = 2000;
      this.field_175999_p = new ChatController$1(this);
      this.field_175992_e = Core.getInstance();
      if (this.field_175992_e == null) {
         this.field_175992_e = new Core(new StandardCoreAPI());
      }

      this.field_153008_f = new Chat(new StandardChatAPI());
   }

   public void func_152996_t() {
      if (this.field_175996_m != null) {
         ErrorCode var1 = this.field_153008_f.clearEmoticonData();
         if (ErrorCode.succeeded(var1)) {
            this.field_175996_m = null;

            try {
               if (this.field_153003_a != null) {
                  this.field_153003_a.method_07678();
               }
            } catch (Exception var3) {
               this.func_152995_h(var3.toString());
            }
         } else {
            this.func_152995_h("Error clearing emoticon data: " + ErrorCode.getString(var1));
         }
      }
   }

   public void func_152984_a(String var1) {
      this.field_153006_d = var1;
   }

   public void func_152995_h(String var1) {
      LOGGER.error(TwitchStream.STREAM_MARKER, "[Chat controller] {}", new Object[]{var1});
   }

   public void func_152990_a(IngestServerTester$IngestTestListener var1) {
      this.field_153003_a = var1;
   }

   public void func_175985_a(ChatController$ChatState var1) {
      if (var1 != this.field_153011_i) {
         this.field_153011_i = var1;

         try {
            if (this.field_153003_a != null) {
               this.field_153003_a.func_176017_a(var1);
            }
         } catch (Exception var3) {
            this.func_152995_h(var3.toString());
         }
      }
   }

   public void func_175988_p() {
      if (this.func_153000_j() != ChatController$ChatState.Uninitialized) {
         this.func_152993_m();
         if (this.func_153000_j() == ChatController$ChatState.ShuttingDown) {
            while (this.func_153000_j() != ChatController$ChatState.Uninitialized) {
               try {
                  Thread.sleep(60948696L & -5479655340727856408L);
                  this.func_152997_n();
               } catch (InterruptedException var2) {
               }
            }
         }
      }
   }

   public boolean func_175984_n() {
      if (this.field_153011_i != ChatController$ChatState.Uninitialized) {
         return false;
      } else {
         this.func_175985_a(ChatController$ChatState.Initializing);
         ErrorCode var1 = this.field_175992_e.initialize(this.field_153006_d, (String)null);
         if (ErrorCode.failed(var1)) {
            this.func_175985_a(ChatController$ChatState.Uninitialized);
            String var5 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error initializing Twitch sdk: %s", var5));
            return false;
         } else {
            this.field_175995_l = this.field_175997_k;
            HashSet var2 = new HashSet();
            switch (ChatController$2.field_0004[this.field_175997_k.ordinal()]) {
               case 1:
                  var2.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_NONE);
                  break;
               case 2:
                  var2.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_EMOTICON_URLS);
                  break;
               case 3:
                  var2.add(ChatTokenizationOption.TTV_CHAT_TOKENIZATION_OPTION_EMOTICON_TEXTURES);
            }

            var1 = this.field_153008_f.initialize(var2, this.field_175999_p);
            if (ErrorCode.failed(var1)) {
               this.field_175992_e.shutdown();
               this.func_175985_a(ChatController$ChatState.Uninitialized);
               String var3 = ErrorCode.getString(var1);
               this.func_152995_h(String.format("Error initializing Twitch chat: %s", var3));
               return false;
            } else {
               this.func_175985_a(ChatController$ChatState.Initialized);
               return true;
            }
         }
      }
   }

   public boolean func_175986_a(String var1, String var2) {
      if (this.field_153011_i != ChatController$ChatState.Initialized) {
         return false;
      } else if (!this.field_175998_i.containsKey(var1)) {
         this.func_152995_h("Not in channel: " + var1);
         return false;
      } else {
         ChatController$ChatChannelListener var3 = this.field_175998_i.get(var1);
         return var3.func_176037_b(var2);
      }
   }

   public boolean func_175991_l(String var1) {
      if (this.field_153011_i != ChatController$ChatState.Initialized) {
         return false;
      } else if (!this.field_175998_i.containsKey(var1)) {
         this.func_152995_h("Not in channel: " + var1);
         return false;
      } else {
         ChatController$ChatChannelListener var2 = this.field_175998_i.get(var1);
         return var2.func_176034_g();
      }
   }

   public ChatController$EnumChannelState func_175989_e(String var1) {
      if (!this.field_175998_i.containsKey(var1)) {
         return ChatController$EnumChannelState.Disconnected;
      } else {
         ChatController$ChatChannelListener var2 = this.field_175998_i.get(var1);
         return var2.func_176040_a();
      }
   }

   public void func_152988_s() {
      if (this.field_175996_m == null) {
         this.field_175996_m = new ChatEmoticonData();
         ErrorCode var1 = this.field_153008_f.getEmoticonData(this.field_175996_m);
         if (ErrorCode.succeeded(var1)) {
            try {
               if (this.field_153003_a != null) {
                  this.field_153003_a.method_07704();
               }
            } catch (Exception var3) {
               this.func_152995_h(var3.toString());
            }
         } else {
            this.func_152995_h("Error preparing emoticon data: " + ErrorCode.getString(var1));
         }
      }
   }

   public void func_152994_a(AuthToken var1) {
      this.field_153012_j = var1;
   }

   public boolean func_152993_m() {
      if (this.field_153011_i != ChatController$ChatState.Initialized) {
         return false;
      } else {
         ErrorCode var1 = this.field_153008_f.shutdown();
         if (ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error shutting down chat: %s", var2));
            return false;
         } else {
            this.func_152996_t();
            this.func_175985_a(ChatController$ChatState.ShuttingDown);
            return true;
         }
      }
   }

   public boolean func_175990_d(String var1) {
      if (!this.field_175998_i.containsKey(var1)) {
         return false;
      } else {
         ChatController$ChatChannelListener var2 = this.field_175998_i.get(var1);
         return var2.func_176040_a() == ChatController$EnumChannelState.Connected;
      }
   }

   public void func_153001_r() {
      if (this.field_175995_l != ChatController$EnumEmoticonMode.None && this.field_175996_m == null) {
         ErrorCode var1 = this.field_153008_f.downloadEmoticonData();
         if (ErrorCode.failed(var1)) {
            String var2 = ErrorCode.getString(var1);
            this.func_152995_h(String.format("Error trying to download emoticon data: %s", var2));
         }
      }
   }

   public boolean func_175987_a(String var1, boolean var2) {
      if (this.field_153011_i != ChatController$ChatState.Initialized) {
         return false;
      } else if (this.field_175998_i.containsKey(var1)) {
         this.func_152995_h("Already in channel: " + var1);
         return false;
      } else if (var1 != null && !var1.equals("")) {
         ChatController$ChatChannelListener var3 = new ChatController$ChatChannelListener(this, var1);
         this.field_175998_i.put(var1, var3);
         boolean var4 = var3.func_176038_a(var2);
         if (!var4) {
            this.field_175998_i.remove(var1);
         }

         return var4;
      } else {
         return false;
      }
   }
}
