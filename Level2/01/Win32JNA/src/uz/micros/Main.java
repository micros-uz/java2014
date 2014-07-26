package uz.micros;

import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX;
import com.sun.jna.platform.win32.WinUser.WindowProc;

public class Main implements WindowProc {

    public Main() {
        WString windowClass = new WString("MyWindowClass");
        HMODULE hInst = Kernel32.INSTANCE.GetModuleHandle("");

        WNDCLASSEX wClass = new WNDCLASSEX();
        wClass.hInstance = hInst;
        wClass.lpfnWndProc = this;
        wClass.lpszClassName = windowClass;

        User32.INSTANCE.RegisterClassEx(wClass);
        getLastError();

        HWND hWnd = User32.INSTANCE
                .CreateWindowEx(
                        0x00040000,
                        windowClass,
                        "JAVA win32",
                        //User32.WS_OVERLAPPED,
                        0xcf0000,
                        100, 100, 600, 400,
                        null, null, hInst, null);

        System.out.println("window sucessfully created! window hwnd: "
                + hWnd.getPointer().toString());

        HWND btn = User32.INSTANCE.CreateWindowEx(
            0, new WString("BUTTON"), "Close", User32.WS_CHILD | User32.WS_VISIBLE,
                160, 40, 80, 23, hWnd, new HMENU(new Pointer(101)), hInst, null);

        HWND edit = User32.INSTANCE.CreateWindowEx(
                0, new WString("EDIT"), "", User32.WS_CHILD | User32.WS_VISIBLE | 0x800000,
                160, 90, 80, 23, hWnd, null, hInst, null);

        User32.INSTANCE.ShowWindow(hWnd, 1);
        getLastError();

        MSG msg = new MSG();
        while (User32.INSTANCE.GetMessage(msg, hWnd, 0, 0) != 0) {
            User32.INSTANCE.TranslateMessage(msg);
            User32.INSTANCE.DispatchMessage(msg);
        }

        System.out.println("program exit!");
    }

    public LRESULT callback(HWND hwnd, int uMsg, WPARAM wParam, LPARAM lParam) {
        System.out.println(uMsg);
        switch (uMsg) {
            case WinUser.WM_CLOSE:
                User32.INSTANCE.PostQuitMessage(0);
                break;
            case 273:
                if (wParam.intValue() == 101)
                    User32.INSTANCE.PostQuitMessage(0);
                break;
            default:
                return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
        }

        return new LRESULT(0);
    }

    public int getLastError() {
        int rc = Kernel32.INSTANCE.GetLastError();

        if (rc != 0)
            System.out.println("error: " + rc);

        return rc;
    }

    public static void main(String[] args) {
        new Main();
    }
}

