package net.minecraft.client.network;

import com.google.common.collect.Lists;
import java.net.InetAddress;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.command.WrongUsageException;
import net.minecraft.network.NettyCompressionDecoder;
import org.java_websocket.WebSocketAdapter;

public class LanServerDetector$LanServerList {
   public List<LanServerDetector$LanServer> listOfLanServers = Lists.newArrayList();
   public NettyCompressionDecoder field_0005;
   public DefaultPlayerSkin field_0002;
   public WrongUsageException field_0004;
   public WebSocketAdapter field_0000;
   public boolean wasUpdated;

   public synchronized void setWasNotUpdated() {
      this.wasUpdated = false;
   }

   public synchronized List<LanServerDetector$LanServer> getLanServers() {
      return Collections.unmodifiableList(this.listOfLanServers);
   }

   public synchronized boolean getWasUpdated() {
      return this.wasUpdated;
   }

   public synchronized void func_77551_a(String var1, InetAddress var2) {
      String var3 = ThreadLanServerPing.method_24020(var1);
      String var4 = ThreadLanServerPing.method_24018(var1);
      if (var4 != null) {
         var4 = var2.getHostAddress() + ":" + var4;
         boolean var5 = false;

         for (LanServerDetector$LanServer var7 : this.listOfLanServers) {
            if (var7.getServerIpPort().equals(var4)) {
               var7.updateLastSeen();
               var5 = true;
               break;
            }
         }

         if (!var5) {
            this.listOfLanServers.add(new LanServerDetector$LanServer(var3, var4));
            this.wasUpdated = true;
         }
      }
   }
}
