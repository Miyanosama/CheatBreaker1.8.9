package org.slf4j.helpers;

import com.cheatbreaker.client.util.SessionServer;
import io.netty.util.concurrent.DefaultPromise$LateListeners;
import io.netty.util.concurrent.GlobalEventExecutor$PurgeTask;
import java.util.Map;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.creativetab.CreativeTabs$5;
import net.minecraft.world.gen.structure.MapGenNetherBridge$Start;
import org.slf4j.spi.MDCAdapter;

public class NOPMDCAdapter implements MDCAdapter {
   public MapGenNetherBridge$Start field_0003;
   public GlobalEventExecutor$PurgeTask field_0005;
   public LayeredTexture field_0002;
   public CreativeTabs$5 field_0004;
   public SessionServer field_0000;
   public DefaultPromise$LateListeners field_0001;

   @Override
   public void setContextMap(Map<String, String> var1) {
   }

   @Override
   public void clear() {
   }

   @Override
   public void put(String var1, String var2) {
   }

   @Override
   public Map<String, String> getCopyOfContextMap() {
      return null;
   }

   @Override
   public void remove(String var1) {
   }

   @Override
   public String get(String var1) {
      return null;
   }
}
