;-------------------------------------------------------------------;
; Settings

#NoEnv
#UseHook
#SingleInstance force
#MaxHotkeysPerInterval 200
global a_launch_dir := A_WorkingDir
SetWorkingDir %A_ScriptDir%
SetBatchLines -1
SetMouseDelay -1
SendMode Input
CoordMode Mouse, Screen
CoordMode Pixel, Screen
SetTitleMatchMode RegEx

;-------------------------------------------------------------------;


Hotkey("^r", "exit")
Hotkey("Esc", "exit")
Hotkey("WheelUp", "sendWheel",, "Minesweeper ahk_exe java.exe")
Hotkey("WheelDown", "sendWheel",, "Minesweeper ahk_exe java.exe")

exit() {
	ExitApp
}

sendWheel() {
	Send {MButton}
}

Hotkey(keyname, label:="", opt:="", context:="", params*) {
	static hotkey_list := hotkey_list ? hotkey_list : {}
	
	if (Format("{:L}", keyname) == "list")
		return hotkey_list
	if (label == "") {
		MsgBox % "Error, no label specified"
		Return
	}
	
	if (IsObject(context) || context == "") {
		Sleep 0
	} else if (IsFunc(context) && !RegExMatch(context, "(^active )|(^exist )")) {
		context := Func(context).Bind(params*)
	} else if (RegExMatch(context, "^exist ")) {
		context := FixWintitle(RegExReplace(context, "^exist "))
		context := Func("WinExist").Bind(context, "", "", "")
	} else {
		context := FixWintitle(RegExReplace(context, "^active "))
		context := Func("WinActive").Bind(context, "", "", "")
	}
	
	Hotkey if, % context
	Hotkey % keyname, % label, % opt
	Hotkey if
	
	hotkey_list.push([keyname, label, opt, context, params*])
}

FixWintitle(ByRef wintitle:="A") {
	if (wintitle == "")
		Return ""
	
	if (SubStr(wintitle, 1, 2) == "0x")
		wintitle := "ahk_id " wintitle
	else if (wintitle == "M") {
		MouseGetPos,,, hwnd
		wintitle := "ahk_id " hwnd
	}
	Return wintitle
}