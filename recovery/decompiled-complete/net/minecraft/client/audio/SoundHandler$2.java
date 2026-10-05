package net.minecraft.client.audio;

import com.cheatbreaker.client.util.server.ServerRestrictionManager;
import io.netty.util.internal.MpscLinkedQueuePad1;
import net.minecraft.util.ResourceLocation;
import org.apache.log4j.Hierarchy;

public class SoundHandler$2 implements ISoundEventAccessor<SoundPoolEntry> {
   public MpscLinkedQueuePad1 field_0003;
   public ServerRestrictionManager field_0004;
   public Hierarchy field_0000;
   public ResourceLocation field_148726_a;

   @Override
   public int getWeight() {
      SoundEventAccessorComposite var1 = SoundHandler.access$000(this.field_148723_d).getObject(this.field_148726_a);
      return var1 == null ? 0 : var1.getWeight();
   }

   public SoundPoolEntry cloneEntry() {
      SoundEventAccessorComposite var1 = SoundHandler.access$000(this.field_148723_d).getObject(this.field_148726_a);
      return var1 == null ? SoundHandler.missing_sound : var1.cloneEntry();
   }

   public SoundHandler$2(SoundHandler var1, String var2, SoundList$SoundEntry var3) {
      this.field_148723_d = var1;
      this.field_148724_b = var2;
      this.field_148725_c = var3;
      super();
      this.field_148726_a = new ResourceLocation(this.field_148724_b, this.field_148725_c.getSoundEntryName());
   }
}
