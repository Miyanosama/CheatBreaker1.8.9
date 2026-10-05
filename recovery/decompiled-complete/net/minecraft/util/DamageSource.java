package net.minecraft.util;

import net.minecraft.client.audio.SoundManager$1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.world.Explosion;

public class DamageSource {
   public static DamageSource magic = new DamageSource("magic").k().t();
   public boolean field_0023;
   public boolean field_0011;
   public static DamageSource lightningBolt = new DamageSource("lightningBolt");
   public boolean field_0006;
   public String damageType;
   public static DamageSource inFire = new DamageSource("inFire").setFireDamage();
   public boolean field_0017;
   public static DamageSource wither = new DamageSource("wither").k();
   public boolean field_0025;
   public static DamageSource generic = new DamageSource("generic").k();
   public static DamageSource outOfWorld = new DamageSource("outOfWorld").k().setDamageAllowedInCreativeMode();
   public boolean field_0016;
   public static DamageSource drown = new DamageSource("drown").k();
   public static DamageSource fall = new DamageSource("fall").k();
   public static DamageSource onFire = new DamageSource("onFire").k().setFireDamage();
   public static DamageSource fallingBlock = new DamageSource("fallingBlock");
   public static DamageSource lava = new DamageSource("lava").setFireDamage();
   public static DamageSource anvil = new DamageSource("anvil");
   public boolean field_0021;
   public SoundManager$1 field_0002;
   public static DamageSource cactus = new DamageSource("cactus");
   public float hungerDamage = 0.3F;
   public static DamageSource starve = new DamageSource("starve").k().setDamageIsAbsolute();
   public boolean field_0019;
   public static DamageSource inWall = new DamageSource("inWall").k();

   public boolean method_30210() {
      return this.field_0006;
   }

   public DamageSource setDamageAllowedInCreativeMode() {
      this.field_0021 = true;
      return this;
   }

   public static DamageSource causeArrowDamage(EntityArrow var0, Entity var1) {
      return new EntityDamageSourceIndirect("arrow", var0, var1).b();
   }

   public DamageSource setDamageIsAbsolute() {
      this.field_0025 = true;
      this.hungerDamage = 0.0F;
      return this;
   }

   public DamageSource t() {
      this.field_0023 = true;
      return this;
   }

   public DamageSource method_30205() {
      this.field_0016 = true;
      return this;
   }

   public DamageSource(String var1) {
      this.damageType = var1;
   }

   public IChatComponent getDeathMessage(EntityLivingBase var1) {
      EntityLivingBase var2 = var1.getAttackingEntity();
      String var3 = "death.attack." + this.damageType;
      String var4 = var3 + ".player";
      return var2 != null && StatCollector.canTranslate(var4)
         ? new ChatComponentTranslation(var4, var1.getDisplayName(), var2.getDisplayName())
         : new ChatComponentTranslation(var3, var1.getDisplayName());
   }

   public static DamageSource causeMobDamage(EntityLivingBase var0) {
      return new EntityDamageSource("mob", var0);
   }

   public boolean isCreativePlayer() {
      Entity var1 = this.getEntity();
      return var1 instanceof EntityPlayer && ((EntityPlayer)var1).bA.isCreativeMode;
   }

   public Entity getSourceOfDamage() {
      return this.getEntity();
   }

   public boolean isMagicDamage() {
      return this.field_0023;
   }

   public boolean canHarmInCreative() {
      return this.field_0021;
   }

   public Entity getEntity() {
      return null;
   }

   public DamageSource b() {
      this.field_0006 = true;
      return this;
   }

   public boolean isFireDamage() {
      return this.field_0011;
   }

   public static DamageSource causeThrownDamage(Entity var0, Entity var1) {
      return new EntityDamageSourceIndirect("thrown", var0, var1).b();
   }

   public DamageSource k() {
      this.field_0017 = true;
      this.hungerDamage = 0.0F;
      return this;
   }

   public static DamageSource causeFireballDamage(EntityFireball var0, Entity var1) {
      return var1 == null
         ? new EntityDamageSourceIndirect("onFire", var0, var0).setFireDamage().b()
         : new EntityDamageSourceIndirect("fireball", var0, var1).setFireDamage().b();
   }

   public DamageSource method_30208() {
      this.field_0019 = true;
      return this;
   }

   public String getDamageType() {
      return this.damageType;
   }

   public boolean isExplosion() {
      return this.field_0016;
   }

   public static DamageSource causePlayerDamage(EntityPlayer var0) {
      return new EntityDamageSource("player", var0);
   }

   public boolean isUnblockable() {
      return this.field_0017;
   }

   public boolean isDifficultyScaled() {
      return this.field_0019;
   }

   public float getHungerDamage() {
      return this.hungerDamage;
   }

   public static DamageSource setExplosionSource(Explosion var0) {
      return var0 != null && var0.getExplosivePlacedBy() != null
         ? new EntityDamageSource("explosion.player", var0.getExplosivePlacedBy()).method_30208().method_30205()
         : new DamageSource("explosion").method_30208().method_30205();
   }

   public boolean isDamageAbsolute() {
      return this.field_0025;
   }

   public DamageSource setFireDamage() {
      this.field_0011 = true;
      return this;
   }

   public static DamageSource causeThornsDamage(Entity var0) {
      return new EntityDamageSource("thorns", var0).setIsThornsDamage().t();
   }

   public static DamageSource causeIndirectMagicDamage(Entity var0, Entity var1) {
      return new EntityDamageSourceIndirect("indirectMagic", var0, var1).k().t();
   }
}
