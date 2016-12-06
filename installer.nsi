; Installer Title
Name "Stage Plan Installer"
; Generated install file
OutFile "stageplan_setup.exe"
; Default install location
InstallDir "$PROGRAMFILES\Stage Plan\"
; The text to prompt the user to enter a directory
DirText "Choose a directory to install Stage Plan"

; Install Section
Section "Install"
; Set the output path to the install location.
SetOutPath $INSTDIR
; Install all files
File "Stage Plan.exe"
File "docs\Stage Plan User Manual.pdf"
; Copy res folder + contents
SetOutPath "$INSTDIR\res"
File /nonfatal /a /r "res\"
; Create Desktop Shortcut
CreateShortcut "$DESKTOP\Stage Plan.lnk" "$INSTDIR\Stage Plan.exe"
CreateShortcut "$DESKTOP\Stage Plan User Manual.lnk" "$INSTDIR\Stage Plan User Manual.pdf"
; Create Start Menu shortcuts
CreateDirectory "$SMPROGRAMS\Stage Plan"
CreateShortCut "$SMPROGRAMS\Stage Plan\Stage Plan.lnk" "$INSTDIR\Stage Plan.exe"
CreateShortCut "$SMPROGRAMS\Stage Plan\Stage Plan User Manual.lnk" "$INSTDIR\Stage Plan User Manual.pdf"
CreateShortCut "$SMPROGRAMS\Stage Plan\Uninstall Stage Plan.lnk" "$INSTDIR\Uninstall.exe" 
; Needed for uninstall
WriteUninstaller $INSTDIR\Uninstall.exe
SectionEnd

; The uninstall section
Section "Uninstall"
Delete "$INSTDIR\Uninstall.exe"
Delete "$INSTDIR\Stage Plan.exe"
Delete "$INSTDIR\Stage Plan User Manual.pdf"
RMDir /r "$INSTDIR\res\"
RMDir "$INSTDIR\res\"
RMDir $INSTDIR
Delete "$SMPROGRAMS\Stage Plan\Uninstall Stage Plan.lnk"
Delete "$SMPROGRAMS\Stage Plan\Stage Plan User Manual.lnk"
Delete "$SMPROGRAMS\Stage Plan\Stage Plan.lnk"
RMDir /r "$SMPROGRAMS\Stage Plan"
Delete "$DESKTOP\Stage Plan.lnk"
Delete "$DESKTOP\Stage Plan User Manual.lnk"
SectionEnd