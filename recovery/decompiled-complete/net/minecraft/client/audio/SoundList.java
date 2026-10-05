package net.minecraft.client.audio;

import com.google.common.collect.Lists;
import io.netty.handler.codec.http.HttpRequestEncoder;
import java.util.List;
import net.minecraft.nbt.NBTTagLong;
import org.newsclub.net.unix.AFUNIXSocket;

public class SoundList {
   public HttpRequestEncoder field_0003;
   public boolean replaceExisting;
   public SoundCategory category;
   public NBTTagLong field_0004;
   public List<SoundList$SoundEntry> soundList = Lists.newArrayList();
   public AFUNIXSocket field_0001;

   public boolean canReplaceExisting() {
      return this.replaceExisting;
   }

   public void setReplaceExisting(boolean var1) {
      this.replaceExisting = var1;
   }

   public List<SoundList$SoundEntry> getSoundList() {
      return this.soundList;
   }

   public SoundCategory getSoundCategory() {
      return this.category;
   }

   public void setSoundCategory(SoundCategory var1) {
      this.category = var1;
   }
}
