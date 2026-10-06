package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.ResourceLocation;

public class HitboxesModule extends AbstractModule {
   public HitboxSettings recoveredField327;
   public HitboxSettings recoveredField328;
   public HitboxSettings recoveredField329;
   public HitboxSettings recoveredField330;
   public HitboxSettings recoveredField331 = new HitboxSettings(this, "Player");

   public HitboxSettings method_09734(Entity var1) {
      if (var1 instanceof AbstractClientPlayer) {
         return this.recoveredField331;
      } else if (var1 instanceof EntityItem) {
         return this.recoveredField327;
      } else if (var1 instanceof EntityXPOrb) {
         return this.recoveredField330;
      } else {
         return var1 instanceof IProjectile || var1 instanceof EntityFireball ? this.recoveredField329 : this.recoveredField328;
      }
   }

   public HitboxesModule() {
      super("Hitboxes");
      this.recoveredField328 = new HitboxSettings(this, "Mob");
      this.recoveredField327 = new HitboxSettings(this, "Item");
      this.recoveredField329 = new HitboxSettings(this, "Projectile");
      this.recoveredField330 = new HitboxSettings(this, "Exp Orb");
      this.method_28821("Shows an outline around an entity's hitbox.");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/hitboxes.png"), 32, 32);
   }
}
