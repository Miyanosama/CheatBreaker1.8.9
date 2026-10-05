package net.minecraft.util;

import com.google.common.base.Function;
import com.jagrosh.discordipc.entities.User$DefaultAvatar;
import java.util.Iterator;

public class ChatComponentStyle$1 implements Function<IChatComponent, Iterator<IChatComponent>> {
   public User$DefaultAvatar field_0000;

   public Iterator<IChatComponent> apply(IChatComponent var1) {
      return var1.iterator();
   }
}
