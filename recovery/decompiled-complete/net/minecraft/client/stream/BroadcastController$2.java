package net.minecraft.client.stream;

import com.cheatbreaker.client.util.Vec2d;
import javazoom.jl.decoder.LayerIIDecoder;
import tv.twitch.broadcast.IStatCallbacks;
import tv.twitch.broadcast.StatType;

public class BroadcastController$2 implements IStatCallbacks {
   public LayerIIDecoder field_0001;
   public Vec2d field_0000;

   public BroadcastController$2(BroadcastController var1) {
      this.field_0002 = var1;
      super();
   }

   public void statCallback(StatType var1, long var2) {
   }
}
