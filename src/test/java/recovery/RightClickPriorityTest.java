package recovery;

import java.lang.reflect.Field;
import junit.framework.TestCase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import sun.misc.Unsafe;

public class RightClickPriorityTest extends TestCase {
    private static <T> T allocate(Class<T> type) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        return type.cast(((Unsafe)field.get(null)).allocateInstance(type));
    }

    public void testRightClickCancelsMiningBeforeUsingHeldItem() throws Exception {
        checkRightClick(true);
    }

    public void testRightClickStillUsesItemWhenNotMining() throws Exception {
        checkRightClick(false);
    }

    private void checkRightClick(boolean mining) throws Exception {
        Minecraft minecraft = allocate(Minecraft.class);
        RecordingController controller = allocate(RecordingController.class);
        controller.isHittingBlock = mining;
        minecraft.playerController = controller;
        minecraft.thePlayer = allocate(EntityPlayerSP.class);
        minecraft.thePlayer.bi = new InventoryPlayer(minecraft.thePlayer);
        ItemStack held = allocate(ItemStack.class);
        minecraft.thePlayer.bi.mainInventory[0] = held;
        minecraft.objectMouseOver = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS,
                new Vec3(0, 0, 0), null, null);

        minecraft.method_20407();

        assertFalse(controller.isHittingBlock);
        assertSame(held, controller.usedItem);
        assertEquals(4, minecraft.recoveredField3825);
    }

    private static class RecordingController extends PlayerControllerMP {
        ItemStack usedItem;
        RecordingController() { super(null, null); }
        @Override public void resetBlockRemoving() { isHittingBlock = false; }
        @Override public boolean sendUseItem(EntityPlayer player, World world, ItemStack stack) {
            assertFalse("Mining must be cancelled before the use-item request", isHittingBlock);
            usedItem = stack;
            return false;
        }
    }
}
