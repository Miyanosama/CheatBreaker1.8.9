package io.netty.util.internal;

import com.cheatbreaker.client.ui.fading.AbstractFade;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import java.security.PrivilegedAction;
import javazoom.jl.player.advanced.PlaybackListener;
import net.minecraft.client.particle.EntityHeartFX$AngryVillagerFactory;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.Util$EnumOS;
import net.optifine.gui.GuiOptionSliderOF;

public class PlatformDependent0$2 implements PrivilegedAction<ClassLoader> {
   public GuiOptionSliderOF __junk7377278902820849365;
   public Util$EnumOS __junk2366823073074620530;
   public EntityHeartFX$AngryVillagerFactory __junk5070408499532617736;
   public ItemSeeds __junk384184940336407421;
   public AbstractFade __junk8767625047825804197;
   public PlaybackListener __junk5267370509194806569;
   public MemoryAttribute __junk1360570845329245530;

   public ClassLoader run() {
      return Thread.currentThread().getContextClassLoader();
   }
}
