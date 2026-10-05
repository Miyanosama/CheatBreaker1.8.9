package io.netty.util.internal;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandshakeHandler;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesBanners;
import net.minecraft.world.EnumDifficulty;

public class TypeParameterMatcher$ReflectiveMatcher extends TypeParameterMatcher {
   public ItemStack __junk42956108174173959;
   public ItemBanner __junk578589458511760099;
   public Class<?> type;
   public RecipesBanners __junk7512223424349117798;
   public EnumDifficulty __junk5836710112993756254;
   public WebSocketServerProtocolHandshakeHandler __junk4435345553726035088;

   @Override
   public boolean match(Object var1) {
      return this.type.isInstance(var1);
   }

   public TypeParameterMatcher$ReflectiveMatcher(Class<?> var1) {
      this.type = var1;
   }
}
