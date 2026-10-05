package net.minecraft.client.stream;

import tv.twitch.ErrorCode;
import tv.twitch.broadcast.IngestServer;
import tv.twitch.chat.ChatUserInfo;

public class NullStream implements IStream {
   public Throwable field_152938_a;

   @Override
   public void func_152917_b(String var1) {
   }

   @Override
   public boolean func_152928_D() {
      return false;
   }

   @Override
   public void method_02395() {
   }

   @Override
   public void stopBroadcasting() {
   }

   public NullStream(Throwable var1) {
      this.field_152938_a = var1;
   }

   @Override
   public int func_152920_A() {
      return 0;
   }

   @Override
   public boolean func_152927_B() {
      return false;
   }

   @Override
   public boolean method_02383() {
      return false;
   }

   @Override
   public boolean func_152929_G() {
      return false;
   }

   @Override
   public void method_02381() {
   }

   @Override
   public ErrorCode func_152912_E() {
      return null;
   }

   @Override
   public void unpause() {
   }

   @Override
   public IngestServer[] func_152925_v() {
      return new IngestServer[0];
   }

   @Override
   public boolean method_02394() {
      return false;
   }

   @Override
   public void shutdownStream() {
   }

   @Override
   public void func_176026_a(Metadata var1, long var2, long var4) {
   }

   @Override
   public boolean isPaused() {
      return false;
   }

   @Override
   public void func_152911_a(Metadata var1, long var2) {
   }

   public Throwable func_152937_a() {
      return this.field_152938_a;
   }

   @Override
   public boolean func_152913_F() {
      return false;
   }

   @Override
   public void func_152935_j() {
   }

   @Override
   public void muteMicrophone(boolean var1) {
   }

   @Override
   public void func_152909_x() {
   }

   @Override
   public boolean func_152908_z() {
      return false;
   }

   @Override
   public String func_152921_C() {
      return null;
   }

   @Override
   public void updateStreamVolume() {
   }

   @Override
   public ChatUserInfo func_152926_a(String var1) {
      return null;
   }

   @Override
   public IngestServerTester func_152932_y() {
      return null;
   }

   @Override
   public IStream.AuthFailureReason func_152918_H() {
      return IStream.AuthFailureReason.ERROR;
   }

   @Override
   public void requestCommercial() {
   }

   @Override
   public boolean isBroadcasting() {
      return false;
   }

   @Override
   public void pause() {
   }
}
