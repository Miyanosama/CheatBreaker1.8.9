package javazoom.jl.decoder;

import org.apache.log4j.FileAppender;
import org.java_websocket.client.WebSocketClient$1;

public class LayerIIIDecoder$III_side_info_t {
   public int private_bits;
   public LayerIIIDecoder$temporaire[] ch;
   public FileAppender __junk5262306295187709133;
   public WebSocketClient$1 __junk2849794656954291932;
   public int main_data_begin = 0;

   public LayerIIIDecoder$III_side_info_t() {
      this.private_bits = 0;
      this.ch = new LayerIIIDecoder$temporaire[2];
      this.ch[0] = new LayerIIIDecoder$temporaire();
      this.ch[1] = new LayerIIIDecoder$temporaire();
   }
}
