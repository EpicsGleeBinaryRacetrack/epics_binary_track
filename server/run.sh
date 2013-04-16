#!/bin/bash

/usr/bin/sudo /home/pi/epics_binary_track/server/adb -s c089052a0120707f push /media/usb0/build/exe.win-amd64-3.3/questions.txt /mnt/sdcard/questions.txt
/usr/bin/sudo /home/pi/epics_binary_track/server/adb -s c0890b1707e4c65f push /media/usb0/build/exe.win-amd64-3.3/questions.txt /mnt/sdcard/questions.txt

/usr/bin/sudo /home/pi/epics_binary_track/server/startup.sh
