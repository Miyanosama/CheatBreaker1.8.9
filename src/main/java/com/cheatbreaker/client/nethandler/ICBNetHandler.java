package com.cheatbreaker.client.nethandler;

import com.cheatbreaker.client.nethandler.shared.PacketAddWaypoint;
import com.cheatbreaker.client.nethandler.shared.PacketRemoveWaypoint;

public interface ICBNetHandler {
   void handleRemoveWaypoint(PacketRemoveWaypoint var1);

   void handleAddWaypoint(PacketAddWaypoint var1);
}
