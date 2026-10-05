package net.optifine.reflect;

import io.netty.channel.group.ChannelMatchers$InstanceMatcher;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.network.play.server.S34PacketMaps;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$2;

public class ReflectorResolver {
   public static boolean resolved = false;
   public CategoryNodeEditor$2 field_0004;
   public static List<IResolvable> RESOLVABLES = Collections.synchronizedList(new ArrayList<>());
   public ChannelMatchers$InstanceMatcher field_0003;
   public S34PacketMaps field_0000;

   public static void register(IResolvable var0) {
      if (!resolved) {
         RESOLVABLES.add(var0);
      } else {
         var0.resolve();
      }
   }

   public static void resolve() {
      if (!resolved) {
         for (IResolvable var1 : RESOLVABLES) {
            var1.resolve();
         }

         resolved = true;
      }
   }
}
