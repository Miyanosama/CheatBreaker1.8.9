package net.minecraft.util;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import java.lang.reflect.Method;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import java.util.HashMap;
import java.util.Map;

/** Reads relative WM_INPUT mouse motion from the LWJGL window on Windows. */
final class WindowsRawMouseInput
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int WM_INPUT = 0x00FF;
    private static final int RID_INPUT = 0x10000003;
    private static final int GWLP_WNDPROC = -4;
    private static final int RIM_TYPEMOUSE = 0;
    private static final int MOUSE_MOVE_ABSOLUTE = 1;
    private static final int HEADER_SIZE = Native.POINTER_SIZE == 8 ? 24 : 16;
    private static final WindowProcedure PROCEDURE = new WindowProcedure();
    private static final Map<Long, Pointer> PREVIOUS_PROCEDURES = new HashMap<Long, Pointer>();

    private static Pointer window;
    private static int movementX;
    private static int movementY;
    private static boolean unavailable;

    private WindowsRawMouseInput()
    {
    }

    static boolean poll(MouseHelper helper)
    {
        if (unavailable || Util.getOSType() != Util.EnumOS.WINDOWS
            || !Display.isCreated())
        {
            return false;
        }

        try
        {
            if (!ensureRegistered())
            {
                return false;
            }

            helper.recoveredField1619 = movementX;
            helper.recoveredField1620 = movementY;
            reset();
            return true;
        }
        catch (Throwable error)
        {
            unavailable = true;
            LOGGER.warn("Windows raw mouse input is unavailable; using LWJGL mouse movement", error);
            return false;
        }
    }

    static void reset()
    {
        movementX = 0;
        movementY = 0;
    }

    private static boolean ensureRegistered() throws Exception
    {
        long handle = WindowHandle.get();
        if (handle == 0L)
        {
            return false;
        }

        if (window != null && Pointer.nativeValue(window) == handle)
        {
            return true;
        }

        reset();
        Pointer newWindow = new Pointer(handle);
        Pointer oldProcedure = Native.POINTER_SIZE == 8
            ? User32.INSTANCE.SetWindowLongPtrW(newWindow, GWLP_WNDPROC, PROCEDURE)
            : User32.INSTANCE.SetWindowLongW(newWindow, GWLP_WNDPROC, PROCEDURE);
        if (oldProcedure == null || Pointer.nativeValue(oldProcedure) == 0L)
        {
            throw new IllegalStateException("Could not subclass the LWJGL window");
        }

        PREVIOUS_PROCEDURES.put(handle, oldProcedure);
        window = newWindow;

        // RAWINPUTDEVICE: usage page, usage, flags, target HWND.
        Memory device = new Memory(Native.POINTER_SIZE == 8 ? 16 : 12);
        device.clear();
        device.setShort(0, (short)1); // Generic desktop controls
        device.setShort(2, (short)2); // Mouse
        device.setPointer(8, newWindow);
        if (!User32.INSTANCE.RegisterRawInputDevices(device, 1, (int)device.size()))
        {
            throw new IllegalStateException("Could not register the LWJGL window for raw mouse input");
        }
        return true;
    }

    private static final class WindowProcedure implements StdCallLibrary.StdCallCallback
    {
        public Pointer callback(Pointer hwnd, int message, Pointer wParam, Pointer lParam)
        {
            Pointer prior = PREVIOUS_PROCEDURES.get(Pointer.nativeValue(hwnd));
            if (message == WM_INPUT && window != null && window.equals(hwnd) && Display.isActive()
                && org.lwjgl.input.Mouse.isGrabbed())
            {
                try
                {
                    IntByReference length = new IntByReference();
                    int result = User32.INSTANCE.GetRawInputData(lParam, RID_INPUT, null, length, HEADER_SIZE);
                    if (result == 0 && length.getValue() >= HEADER_SIZE + 20)
                    {
                        Memory data = new Memory(length.getValue());
                        if (User32.INSTANCE.GetRawInputData(lParam, RID_INPUT, data, length, HEADER_SIZE)
                            == length.getValue() && data.getInt(0) == RIM_TYPEMOUSE
                            && (data.getShort(HEADER_SIZE) & MOUSE_MOVE_ABSOLUTE) == 0)
                        {
                            movementX += data.getInt(HEADER_SIZE + 12);
                            // Win32 Y grows downward; LWJGL Mouse.getDY() grows upward.
                            movementY -= data.getInt(HEADER_SIZE + 16);
                        }
                    }
                }
                catch (Throwable error)
                {
                    unavailable = true;
                    reset();
                }
            }

            return User32.INSTANCE.CallWindowProcW(prior, hwnd, message, wParam, lParam);
        }
    }

    // LWJGL 2 does not expose the HWND in its public API.
    private static final class WindowHandle
    {
        private static final Method GET_IMPLEMENTATION;
        private static final Method GET_HWND;
        static
        {
            try
            {
                GET_IMPLEMENTATION = Display.class.getDeclaredMethod("getImplementation");
                GET_HWND = Class.forName("org.lwjgl.opengl.WindowsDisplay", false,
                    Display.class.getClassLoader()).getDeclaredMethod("getHwnd");
                GET_IMPLEMENTATION.setAccessible(true);
                GET_HWND.setAccessible(true);
            }
            catch (Exception error)
            {
                throw new IllegalStateException("Unsupported LWJGL Windows display bridge", error);
            }
        }

        static long get() throws Exception
        {
            return ((Long)GET_HWND.invoke(GET_IMPLEMENTATION.invoke(null))).longValue();
        }
    }

    private interface User32 extends StdCallLibrary
    {
        User32 INSTANCE = (User32)Native.loadLibrary("user32", User32.class);

        Pointer SetWindowLongPtrW(Pointer hwnd, int index, StdCallCallback procedure);

        Pointer SetWindowLongW(Pointer hwnd, int index, StdCallCallback procedure);

        Pointer CallWindowProcW(Pointer previous, Pointer hwnd, int message, Pointer wParam, Pointer lParam);

        boolean RegisterRawInputDevices(Pointer devices, int count, int deviceSize);

        int GetRawInputData(Pointer rawInput, int command, Pointer data, IntByReference size, int headerSize);
    }
}
