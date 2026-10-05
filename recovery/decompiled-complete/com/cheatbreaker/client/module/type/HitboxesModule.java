package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.module.AbstractModule;
import io.netty.channel.group.DefaultChannelGroupFuture$DefaultEntry;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityThrowable;

public class HitboxesModule extends AbstractModule {
   public DefaultChannelGroupFuture$DefaultEntry field_0002;
   public HitboxSettings field_0003;
   public HitboxSettings field_0000;
   public HitboxSettings field_0001;
   public HitboxSettings field_0005;
   public HitboxSettings field_0004 = new HitboxSettings(this, "Player");

   public HitboxSettings method_09734(Entity var1) {
      if (var1 instanceof AbstractClientPlayer) {
         return this.field_0004;
      } else if (var1 instanceof EntityItem) {
         return this.field_0003;
      } else if (var1 instanceof EntityXPOrb) {
         return this.field_0005;
      } else {
         return var1 instanceof EntityThrowable ? this.field_0001 : this.field_0000;
      }
   }

   public HitboxesModule() {
      super("Hitboxes");
      this.field_0000 = new HitboxSettings(this, "Mob");
      this.field_0003 = new HitboxSettings(this, "Item");
      this.field_0001 = new HitboxSettings(this, "Projectile");
      this.field_0005 = new HitboxSettings(this, "Exp Orb");
      this.method_28821("Shows an outline around an entity's hitbox.");
      this.setPreviewLabel("Hitboxes", 1.0F);
   }
}
