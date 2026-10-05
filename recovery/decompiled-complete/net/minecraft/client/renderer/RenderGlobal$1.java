package net.minecraft.client.renderer;

import io.netty.handler.codec.marshalling.MarshallingDecoder;
import java.util.concurrent.Callable;
import javazoom.jl.decoder.JavaLayerError;
import javazoom.jl.player.JavaSoundAudioDeviceFactory;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Bootstrap$6;
import recovered.unidentified.UnidentifiedClass0156;

public class RenderGlobal$1 implements Callable<String> {
   public JavaSoundAudioDeviceFactory field_0005;
   public RenderEnderman field_0004;
   public IMetadataSerializer field_0002;
   public JavaLayerError field_0009;
   public Bootstrap$6 field_0003;
   public MarshallingDecoder field_0010;
   public UnidentifiedClass0156 field_0000;

   public RenderGlobal$1(RenderGlobal var1, double var2, double var4, double var6) {
      this.this$0 = var1;
      this.val$xCoord = var2;
      this.val$yCoord = var4;
      this.val$zCoord = var6;
      super();
   }

   public String call() {
      return CrashReportCategory.getCoordinateInfo(this.val$xCoord, this.val$yCoord, this.val$zCoord);
   }
}
