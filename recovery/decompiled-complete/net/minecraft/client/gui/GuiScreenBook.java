package net.minecraft.client.gui;

import com.cheatbreaker.client.network.CustomPayloadSender;
import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.rtsp.RtspMethods;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs$2;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class GuiScreenBook extends GuiScreen {
   public static Logger logger = LogManager.getLogger();
   public boolean bookIsModified;
   public GuiButton buttonDone;
   public int bookTotalPages;
   public int updateCount;
   public static ResourceLocation bookGuiTextures = new ResourceLocation("textures/gui/book.png");
   public GuiButton buttonCancel;
   public EntityPlayer editingPlayer;
   public int currPage;
   public RtspMethods field_0023;
   public String bookTitle;
   public CreativeTabs$2 field_0013;
   public GuiButton buttonFinalize;
   public int field_175387_B;
   public ItemStack bookObj;
   public GuiButton buttonSign;
   public NBTTagList bookPages;
   public List<IChatComponent> field_175386_A;
   public GuiScreenBook$NextPageButton buttonNextPage;
   public int bookImageHeight;
   public int bookImageWidth = 192;
   public boolean bookGettingSigned;
   public boolean bookIsUnsigned;
   public GuiScreenBook$NextPageButton buttonPreviousPage;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(bookGuiTextures);
      int var4 = (this.l - this.bookImageWidth) / 2;
      byte var5 = 2;
      this.drawTexturedModalRect(var4, var5, 0, 0, this.bookImageWidth, this.bookImageHeight);
      if (this.bookGettingSigned) {
         String var6 = this.bookTitle;
         if (this.bookIsUnsigned) {
            if (this.updateCount / 6 % 2 == 0) {
               var6 = var6 + "" + EnumChatFormatting.BLACK + "_";
            } else {
               var6 = var6 + "" + EnumChatFormatting.GRAY + "_";
            }
         }

         String var7 = I18n.format("book.editTitle");
         int var8 = this.q.getStringWidth(var7);
         this.q.drawString(var7, var4 + 36 + (116 - var8) / 2, var5 + 16 + 16, 0);
         int var9 = this.q.getStringWidth(var6);
         this.q.drawString(var6, var4 + 36 + (116 - var9) / 2, var5 + 48, 0);
         Object var10 = I18n.format("book.byAuthor", this.editingPlayer.z_());
         int var11 = this.q.getStringWidth((String)var10);
         this.q.drawString(EnumChatFormatting.DARK_GRAY + var10, var4 + 36 + (116 - var11) / 2, var5 + 48 + 10, 0);
         String var12 = I18n.format("book.finalizeWarning");
         this.q.drawSplitString(var12, var4 + 36, var5 + 80, 116, 0);
      } else {
         String var14 = I18n.format("book.pageIndicator", this.currPage + 1, this.bookTotalPages);
         String var15 = "";
         if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
            var15 = this.bookPages.getStringTagAt(this.currPage);
         }

         if (this.bookIsUnsigned) {
            if (this.q.getBidiFlag()) {
               var15 = var15 + "_";
            } else if (this.updateCount / 6 % 2 == 0) {
               var15 = var15 + "" + EnumChatFormatting.BLACK + "_";
            } else {
               var15 = var15 + "" + EnumChatFormatting.GRAY + "_";
            }
         } else if (this.field_175387_B != this.currPage) {
            if (ItemEditableBook.validBookTagContents(this.bookObj.getTagCompound())) {
               try {
                  IChatComponent var16 = IChatComponent$Serializer.jsonToComponent(var15);
                  this.field_175386_A = var16 != null ? GuiUtilRenderComponents.splitText(var16, 116, this.q, true, true) : null;
               } catch (JsonParseException var13) {
                  this.field_175386_A = null;
               }
            } else {
               ChatComponentText var17 = new ChatComponentText(EnumChatFormatting.DARK_RED.toString() + "* Invalid book tag *");
               this.field_175386_A = Lists.newArrayList(var17);
            }

            this.field_175387_B = this.currPage;
         }

         int var18 = this.q.getStringWidth(var14);
         this.q.drawString(var14, var4 - var18 + this.bookImageWidth - 44, var5 + 16, 0);
         if (this.field_175386_A == null) {
            this.q.drawSplitString(var15, var4 + 36, var5 + 16 + 16, 116, 0);
         } else {
            int var19 = Math.min(128 / this.q.FONT_HEIGHT, this.field_175386_A.size());

            for (int var20 = 0; var20 < var19; var20++) {
               IChatComponent var22 = this.field_175386_A.get(var20);
               this.q.drawString(var22.getUnformattedText(), var4 + 36, var5 + 16 + 16 + var20 * this.q.FONT_HEIGHT, 0);
            }

            IChatComponent var21 = this.func_175385_b(var1, var2);
            if (var21 != null) {
               this.a(var21, var1, var2);
            }
         }
      }

      super.drawScreen(var1, var2, var3);
   }

   public GuiScreenBook(EntityPlayer var1, ItemStack var2, boolean var3) {
      this.bookImageHeight = 192;
      this.bookTotalPages = 1;
      this.bookTitle = "";
      this.field_175387_B = -1;
      this.editingPlayer = var1;
      this.bookObj = var2;
      this.bookIsUnsigned = var3;
      if (var2.hasTagCompound()) {
         NBTTagCompound var4 = var2.getTagCompound();
         this.bookPages = var4.getTagList("pages", 8);
         if (this.bookPages != null) {
            this.bookPages = (NBTTagList)this.bookPages.copy();
            this.bookTotalPages = this.bookPages.tagCount();
            if (this.bookTotalPages < 1) {
               this.bookTotalPages = 1;
            }
         }
      }

      if (this.bookPages == null && var3) {
         this.bookPages = new NBTTagList();
         this.bookPages.appendTag(new NBTTagString(""));
         this.bookTotalPages = 1;
      }
   }

   @Override
   public void updateScreen() {
      super.updateScreen();
      this.updateCount++;
   }

   public void pageSetCurrent(String var1) {
      if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
         this.bookPages.set(this.currPage, new NBTTagString(var1));
         this.bookIsModified = true;
      }
   }

   public IChatComponent func_175385_b(int var1, int var2) {
      if (this.field_175386_A == null) {
         return null;
      } else {
         int var3 = var1 - (this.l - this.bookImageWidth) / 2 - 36;
         int var4 = var2 - 2 - 16 - 16;
         if (var3 >= 0 && var4 >= 0) {
            int var5 = Math.min(128 / this.q.FONT_HEIGHT, this.field_175386_A.size());
            if (var3 <= 116 && var4 < this.j.fontRendererObj.FONT_HEIGHT * var5 + var5) {
               int var6 = var4 / this.j.fontRendererObj.FONT_HEIGHT;
               if (var6 >= 0 && var6 < this.field_175386_A.size()) {
                  IChatComponent var7 = this.field_175386_A.get(var6);
                  int var8 = 0;

                  for (IChatComponent var10 : var7) {
                     if (var10 instanceof ChatComponentText) {
                        var8 += this.j.fontRendererObj.getStringWidth(((ChatComponentText)var10).method_07470());
                        if (var8 > var3) {
                           return var10;
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public void method_12685(char var1, int var2) {
      switch (var2) {
         case 14:
            if (!this.bookTitle.isEmpty()) {
               this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
               this.updateButtons();
            }

            return;
         case 28:
         case 156:
            if (!this.bookTitle.isEmpty()) {
               this.sendBookToServer(true);
               this.j.displayGuiScreen((GuiScreen)null);
            }

            return;
         default:
            if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(var1)) {
               this.bookTitle = this.bookTitle + Character.toString(var1);
               this.updateButtons();
               this.bookIsModified = true;
            }
      }
   }

   @Override
   public void keyTyped(char var1, int var2) {
      super.keyTyped(var1, var2);
      if (this.bookIsUnsigned) {
         if (this.bookGettingSigned) {
            this.method_12685(var1, var2);
         } else {
            this.method_12680(var1, var2);
         }
      }
   }

   public void updateButtons() {
      this.buttonNextPage.m = !this.bookGettingSigned && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned);
      this.buttonPreviousPage.m = !this.bookGettingSigned && this.currPage > 0;
      this.buttonDone.m = !this.bookIsUnsigned || !this.bookGettingSigned;
      if (this.bookIsUnsigned) {
         this.buttonSign.m = !this.bookGettingSigned;
         this.buttonCancel.m = this.bookGettingSigned;
         this.buttonFinalize.m = this.bookGettingSigned;
         this.buttonFinalize.l = this.bookTitle.trim().length() > 0;
      }
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      if (var3 == 0) {
         IChatComponent var4 = this.func_175385_b(var1, var2);
         if (this.handleComponentClick(var4)) {
            return;
         }
      }

      super.mouseClicked(var1, var2, var3);
   }

   @Override
   public boolean handleComponentClick(IChatComponent var1) {
      ClickEvent var2 = var1 == null ? null : var1.getChatStyle().getChatClickEvent();
      if (var2 == null) {
         return false;
      } else if (var2.getAction() == ClickEvent$Action.field_0007) {
         String var6 = var2.getValue();

         try {
            int var4 = Integer.parseInt(var6) - 1;
            if (var4 >= 0 && var4 < this.bookTotalPages && var4 != this.currPage) {
               this.currPage = var4;
               this.updateButtons();
               return true;
            }
         } catch (Throwable var5) {
         }

         return false;
      } else {
         boolean var3 = super.handleComponentClick(var1);
         if (var3 && var2.getAction() == ClickEvent$Action.field_0005) {
            this.j.displayGuiScreen((GuiScreen)null);
         }

         return var3;
      }
   }

   @Override
   public void initGui() {
      this.n.clear();
      Keyboard.enableRepeatEvents(true);
      if (this.bookIsUnsigned) {
         this.n.add(this.buttonSign = new GuiButton(3, this.l / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.signButton")));
         this.n.add(this.buttonDone = new GuiButton(0, this.l / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.done")));
         this.n.add(this.buttonFinalize = new GuiButton(5, this.l / 2 - 100, 4 + this.bookImageHeight, 98, 20, I18n.format("book.finalizeButton")));
         this.n.add(this.buttonCancel = new GuiButton(4, this.l / 2 + 2, 4 + this.bookImageHeight, 98, 20, I18n.format("gui.cancel")));
      } else {
         this.n.add(this.buttonDone = new GuiButton(0, this.l / 2 - 100, 4 + this.bookImageHeight, 200, 20, I18n.format("gui.done")));
      }

      int var1 = (this.l - this.bookImageWidth) / 2;
      byte var2 = 2;
      this.n.add(this.buttonNextPage = new GuiScreenBook$NextPageButton(1, var1 + 120, var2 + 154, true));
      this.n.add(this.buttonPreviousPage = new GuiScreenBook$NextPageButton(2, var1 + 38, var2 + 154, false));
      this.updateButtons();
   }

   public void method_12680(char var1, int var2) {
      if (GuiScreen.isKeyComboCtrlV(var2)) {
         this.pageInsertIntoCurrent(GuiScreen.getClipboardString());
      } else {
         switch (var2) {
            case 14:
               String var3 = this.pageGetCurrent();
               if (var3.length() > 0) {
                  this.pageSetCurrent(var3.substring(0, var3.length() - 1));
               }

               return;
            case 28:
            case 156:
               this.pageInsertIntoCurrent("\n");
               return;
            default:
               if (ChatAllowedCharacters.isAllowedCharacter(var1)) {
                  this.pageInsertIntoCurrent(Character.toString(var1));
               }
         }
      }
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 0) {
            this.j.displayGuiScreen((GuiScreen)null);
            this.sendBookToServer(false);
         } else if (var1.k == 3 && this.bookIsUnsigned) {
            this.bookGettingSigned = true;
         } else if (var1.k == 1) {
            if (this.currPage < this.bookTotalPages - 1) {
               this.currPage++;
            } else if (this.bookIsUnsigned) {
               this.addNewPage();
               if (this.currPage < this.bookTotalPages - 1) {
                  this.currPage++;
               }
            }
         } else if (var1.k == 2) {
            if (this.currPage > 0) {
               this.currPage--;
            }
         } else if (var1.k == 5 && this.bookGettingSigned) {
            this.sendBookToServer(true);
            this.j.displayGuiScreen((GuiScreen)null);
         } else if (var1.k == 4 && this.bookGettingSigned) {
            this.bookGettingSigned = false;
         }

         this.updateButtons();
      }
   }

   public void pageInsertIntoCurrent(String var1) {
      String var2 = this.pageGetCurrent();
      String var3 = var2 + var1;
      int var4 = this.q.splitStringWidth(var3 + "" + EnumChatFormatting.BLACK + "_", 118);
      if (var4 <= 128 && var3.length() < 256) {
         this.pageSetCurrent(var3);
      }
   }

   public void sendBookToServer(boolean var1) {
      if (this.bookIsUnsigned && this.bookIsModified && this.bookPages != null) {
         while (this.bookPages.tagCount() > 1) {
            String var2 = this.bookPages.getStringTagAt(this.bookPages.tagCount() - 1);
            if (var2.length() != 0) {
               break;
            }

            this.bookPages.removeTag(this.bookPages.tagCount() - 1);
         }

         if (this.bookObj.hasTagCompound()) {
            NBTTagCompound var6 = this.bookObj.getTagCompound();
            var6.setTag("pages", this.bookPages);
         } else {
            this.bookObj.setTagInfo("pages", this.bookPages);
         }

         String var7 = "MC|BEdit";
         if (var1) {
            var7 = "MC|BSign";
            this.bookObj.setTagInfo("author", new NBTTagString(this.editingPlayer.z_()));
            this.bookObj.setTagInfo("title", new NBTTagString(this.bookTitle.trim()));

            for (int var3 = 0; var3 < this.bookPages.tagCount(); var3++) {
               String var4 = this.bookPages.getStringTagAt(var3);
               ChatComponentText var5 = new ChatComponentText(var4);
               var4 = IChatComponent$Serializer.componentToJson(var5);
               this.bookPages.set(var3, new NBTTagString(var4));
            }

            this.bookObj.setItem(Items.written_book);
         }

         PacketBuffer var8 = new PacketBuffer(Unpooled.buffer());
         var8.writeItemStackToBuffer(this.bookObj);
         this.j.getNetHandler().addToSendQueue(new CustomPayloadSender(var7, var8));
      }
   }

   public void addNewPage() {
      if (this.bookPages != null && this.bookPages.tagCount() < 50) {
         this.bookPages.appendTag(new NBTTagString(""));
         this.bookTotalPages++;
         this.bookIsModified = true;
      }
   }

   public String pageGetCurrent() {
      return this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount() ? this.bookPages.getStringTagAt(this.currPage) : "";
   }
}
