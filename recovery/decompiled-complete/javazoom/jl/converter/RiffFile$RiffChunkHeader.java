package javazoom.jl.converter;

import io.netty.util.Recycler;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceKeysToIntTask;
import net.minecraft.network.play.client.C03PacketPlayer$C04PacketPlayerPosition;
import net.minecraft.world.storage.WorldInfo$7;

public class RiffFile$RiffChunkHeader {
   public Recycler __junk4600477677068487122;
   public int ckID;
   public ConcurrentHashMapV8$MapReduceKeysToIntTask __junk9171067242034738180;
   public C03PacketPlayer$C04PacketPlayerPosition __junk7908591867193710350;
   public WorldInfo$7 __junk6841009392196270721;
   public int ckSize;

   public RiffFile$RiffChunkHeader(RiffFile var1) {
      this.this$0 = var1;
      super();
      this.ckID = 0;
      this.ckSize = 0;
   }
}
