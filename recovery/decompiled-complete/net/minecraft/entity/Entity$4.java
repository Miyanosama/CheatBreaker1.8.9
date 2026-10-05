package net.minecraft.entity;

import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import io.netty.handler.codec.spdy.DefaultSpdySettingsFrame;
import java.util.concurrent.Callable;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.item.EntityExpBottle;
import recovered.unidentified.UnidentifiedClass3876;

public class Entity$4 implements Callable<String> {
   public DefaultSpdySettingsFrame field_0003;
   public RenderSlime field_0006;
   public GlobalSettings field_0002;
   public EntityExpBottle field_0005;
   public Setting field_0001;
   public UnidentifiedClass3876 field_0007;
   public RenderPlayer field_0004;

   public Entity$4(Entity var1) {
      this.field_180117_a = var1;
      super();
   }

   public String call() {
      return this.field_180117_a.m.toString();
   }
}
