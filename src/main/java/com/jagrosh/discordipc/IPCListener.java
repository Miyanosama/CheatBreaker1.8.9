package com.jagrosh.discordipc;

import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.User;
import org.json.JSONObject;

public interface IPCListener {
   default void method_13336(IPCClient var1, String var2) {
   }

   default void method_13335(IPCClient var1, Packet var2) {
   }

   default void method_13334(IPCClient var1, JSONObject var2) {
   }

   default void method_13340(IPCClient var1, String var2) {
   }

   default void method_13338(IPCClient var1, Throwable var2) {
   }

   default void method_13339(IPCClient var1, Packet var2) {
   }

   default void method_13337(IPCClient var1, String var2, User var3) {
   }

   default void method_13333(IPCClient var1) {
   }
}
