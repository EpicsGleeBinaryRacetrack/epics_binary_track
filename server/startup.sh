#!/bin/bash
/home/pi/epics_binary_track/server/adb -s c0890b1707e4c65f forward tcp:5001 tcp:8081
/home/pi/epics_binary_track/server/adb -s c089052a0120707f forward tcp:5002 tcp:8081


/home/pi/epics_binary_track/server/adb -s c089052a0120707f uninstall epics.binarytrack
/home/pi/epics_binary_track/server/adb -s c089052a0120707f install /home/pi/epics_binary_track/android/bin/BinaryTrack.apk
/home/pi/epics_binary_track/server/adb -s c089052a0120707f shell am start -n epics.binarytrack/epics.binarytrack.MainActivity

/home/pi/epics_binary_track/server/adb -s c0890b1707e4c65f uninstall epics.binarytrack
/home/pi/epics_binary_track/server/adb -s c0890b1707e4c65f install /home/pi/epics_binary_track/android/bin/BinaryTrack.apk
/home/pi/epics_binary_track/server/adb -s c0890b1707e4c65f shell am start -n epics.binarytrack/epics.binarytrack.MainActivity

cd /home/pi/epics_binary_track/server/

java Main
