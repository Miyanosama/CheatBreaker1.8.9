package net.minecraft.server.network;

import net.minecraft.client.resources.FileResourcePack;
import recovered.unidentified.UnidentifiedClass3833;

public enum NetHandlerLoginServer$LoginState {
   KEY,
   ACCEPTED,
   AUTHENTICATING,
   READY_TO_ACCEPT,
   HELLO,
   DELAY_ACCEPT;

   public FileResourcePack field_0008;
   public UnidentifiedClass3833 field_0002;
}
