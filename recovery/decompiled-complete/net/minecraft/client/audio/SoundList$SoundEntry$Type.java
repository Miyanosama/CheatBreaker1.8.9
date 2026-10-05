package net.minecraft.client.audio;

import io.netty.util.concurrent.MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser;
import net.minecraft.world.pathfinder.NodeProcessor;
import org.json.CDL;

public enum SoundList$SoundEntry$Type {
   FILE("file"),
   SOUND_EVENT("event");

   public NodeProcessor field_0003;
   public MultithreadEventExecutorGroup$PowerOfTwoEventExecutorChooser field_0005;
   public CDL field_0002;
   // $VF: synthetic field
   public static SoundList$SoundEntry$Type[] $VALUES = new SoundList$SoundEntry$Type[]{SoundList$SoundEntry$Type.FILE, SoundList$SoundEntry$Type.SOUND_EVENT};
   public String field_148583_c;

   public SoundList$SoundEntry$Type(String var3) {
      this.field_148583_c = var3;
   }

   public static SoundList$SoundEntry$Type getType(String var0) {
      for (SoundList$SoundEntry$Type var4 : values()) {
         if (var4.field_148583_c.equals(var0)) {
            return var4;
         }
      }

      return null;
   }
}
