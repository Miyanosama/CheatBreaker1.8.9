package net.minecraft.client.audio;

import com.cheatbreaker.client.ui.element.module.ModulePreviewContainer;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.Utf8Validator;
import paulscode.sound.SoundSystemConfig;

public class SoundManager$1 implements Runnable {
   public ModulePreviewContainer field_0002;
   public HttpHeaders field_0004;
   public HttpServerCodec field_0001;
   public Utf8Validator field_0000;

   public SoundManager$1(SoundManager var1) {
      this.field_148631_a = var1;
      super();
   }

   @Override
   public void run() {
      SoundSystemConfig.setLogger(new SoundManager$1$1(this));
      SoundManager.access$102(this.field_148631_a, new SoundManager$SoundSystemStarterThread(this.field_148631_a, null));
      SoundManager.access$302(this.field_148631_a, true);
      SoundManager.access$100(this.field_148631_a).setMasterVolume(SoundManager.access$400(this.field_148631_a).getSoundLevel(SoundCategory.MASTER));
      SoundManager.access$000().info(SoundManager.access$500(), "Sound engine started");
   }
}
