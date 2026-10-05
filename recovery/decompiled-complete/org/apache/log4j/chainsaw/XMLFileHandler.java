package org.apache.log4j.chainsaw;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import java.util.StringTokenizer;
import net.minecraft.client.Minecraft$2;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$XYDoubleRoomFitHelper;
import org.apache.log4j.Level;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLFileHandler extends DefaultHandler {
   public Minecraft$2 field_0010;
   public String[] mThrowableStrRep;
   public SyntaxErrorException field_0009;
   public static String field_0016;
   public String mCategoryName;
   public int mNumEvents;
   public static String field_0020;
   public ChannelDuplexHandler field_0014;
   public long mTimeStamp;
   public MyTableModel mModel;
   public StringBuffer mBuf = new StringBuffer();
   public static String field_0011;
   public String mThreadName;
   public StructureOceanMonumentPieces$XYDoubleRoomFitHelper field_0008;
   public ProtobufEncoder field_0015;
   public static String field_0018;
   public PathPoint field_0002;
   public String mLocationDetails;
   public String mNDC;
   public Level mLevel;
   public String mMessage;
   public static String field_0000;

   public int getNumEvents() {
      return this.mNumEvents;
   }

   public void endElement(String var1, String var2, String var3) {
      if ("log4j:event".equals(var3)) {
         this.addEvent();
         this.resetData();
      } else if ("log4j:NDC".equals(var3)) {
         this.mNDC = this.mBuf.toString();
      } else if ("log4j:message".equals(var3)) {
         this.mMessage = this.mBuf.toString();
      } else if ("log4j:throwable".equals(var3)) {
         StringTokenizer var4 = new StringTokenizer(this.mBuf.toString(), "\n\t");
         this.mThrowableStrRep = new String[var4.countTokens()];
         if (this.mThrowableStrRep.length > 0) {
            this.mThrowableStrRep[0] = var4.nextToken();

            for (int var5 = 1; var5 < this.mThrowableStrRep.length; var5++) {
               this.mThrowableStrRep[var5] = "\t" + var4.nextToken();
            }
         }
      }
   }

   public void addEvent() {
      this.mModel
         .addEvent(
            new EventDetails(
               this.mTimeStamp, this.mLevel, this.mCategoryName, this.mNDC, this.mThreadName, this.mMessage, this.mThrowableStrRep, this.mLocationDetails
            )
         );
      this.mNumEvents++;
   }

   public void startDocument() {
      this.mNumEvents = 0;
   }

   public void startElement(String var1, String var2, String var3, Attributes var4) {
      this.mBuf.setLength(0);
      if ("log4j:event".equals(var3)) {
         this.mThreadName = var4.getValue("thread");
         this.mTimeStamp = Long.parseLong(var4.getValue("timestamp"));
         this.mCategoryName = var4.getValue("logger");
         this.mLevel = Level.toLevel(var4.getValue("level"));
      } else if ("log4j:locationInfo".equals(var3)) {
         this.mLocationDetails = var4.getValue("class") + "." + var4.getValue("method") + "(" + var4.getValue("file") + ":" + var4.getValue("line") + ")";
      }
   }

   public void characters(char[] var1, int var2, int var3) {
      this.mBuf.append(String.valueOf(var1, var2, var3));
   }

   public XMLFileHandler(MyTableModel var1) {
      this.mModel = var1;
   }

   public void resetData() {
      this.mTimeStamp = 1069057L & -5892251985871852888L;
      this.mLevel = null;
      this.mCategoryName = null;
      this.mNDC = null;
      this.mThreadName = null;
      this.mMessage = null;
      this.mThrowableStrRep = null;
      this.mLocationDetails = null;
   }
}
