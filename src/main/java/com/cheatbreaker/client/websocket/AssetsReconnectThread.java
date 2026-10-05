package com.cheatbreaker.client.websocket;

import com.cheatbreaker.client.CheatBreaker;
import java.net.URISyntaxException;

public class AssetsReconnectThread extends Thread {
   @Override
   public void run() {
      try {
         Thread.sleep(25000L);
         CheatBreaker.getInstance().method_19761();
      } catch (URISyntaxException | InterruptedException var2) {
         var2.printStackTrace();
      }
   }
}
