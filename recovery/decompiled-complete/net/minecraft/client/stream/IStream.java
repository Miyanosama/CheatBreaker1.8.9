package net.minecraft.client.stream;

import tv.twitch.ErrorCode;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.chat.ChatUserInfo;

public interface IStream {
   void muteMicrophone(boolean var1);

   boolean method_02383();

   void func_152911_a(Metadata var1, long var2);

   boolean func_152908_z();

   boolean func_152913_F();

   boolean func_152927_B();

   boolean isBroadcasting();

   void method_02381();

   ChatUserInfo func_152926_a(String var1);

   IngestServerTester func_152932_y();

   void method_02395();

   boolean func_152929_G();

   void shutdownStream();

   void func_152917_b(String var1);

   void func_152935_j();

   void updateStreamVolume();

   void requestCommercial();

   ErrorCode func_152912_E();

   void stopBroadcasting();

   boolean isPaused();

   void unpause();

   boolean func_152928_D();

   void pause();

   String func_152921_C();

   boolean method_02394();

   void func_152909_x();

   void func_176026_a(Metadata var1, long var2, long var4);

   IngestServer[] func_152925_v();

   int func_152920_A();

   IStream$AuthFailureReason func_152918_H();
}
