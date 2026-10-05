package net.minecraft.client.network;

import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.monster.EntityGuardian$GuardianMoveHelper;
import net.minecraft.world.biome.BiomeGenTaiga;
import org.apache.log4j.pattern.ClassNamePatternConverter;

public class LanServerDetector$LanServer {
   public Enchantment field_0004;
   public EntityGuardian$GuardianMoveHelper field_0007;
   public BiomeGenTaiga field_0003;
   public ClientBrandRetriever field_0006;
   public String lanServerMotd;
   public RenderList field_0001;
   public String lanServerIpPort;
   public ClassNamePatternConverter field_0005;
   public long timeLastSeen;

   public String getServerMotd() {
      return this.lanServerMotd;
   }

   public void updateLastSeen() {
      this.timeLastSeen = Minecraft.getSystemTime();
   }

   public String getServerIpPort() {
      return this.lanServerIpPort;
   }

   public LanServerDetector$LanServer(String var1, String var2) {
      this.lanServerMotd = var1;
      this.lanServerIpPort = var2;
      this.timeLastSeen = Minecraft.getSystemTime();
   }
}
