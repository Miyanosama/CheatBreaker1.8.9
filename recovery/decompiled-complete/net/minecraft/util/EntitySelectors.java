package net.minecraft.util;

import com.google.common.base.Predicate;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.server.management.PreYggdrasilConverter$1;
import recovered.unidentified.UnidentifiedClass3218;

public class EntitySelectors {
   public PreYggdrasilConverter$1 field_0003;
   public UnidentifiedClass3218 field_0005;
   public static Predicate<Entity> NOT_SPECTATING = new EntitySelectors$4();
   public EntityPlayerSP field_0004;
   public static Predicate<Entity> IS_STANDALONE = new EntitySelectors$2();
   public static Predicate<Entity> selectAnything = new EntitySelectors$1();
   public static Predicate<Entity> selectInventories = new EntitySelectors$3();
}
