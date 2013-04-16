/usr/bin/sudo mount -o uid=pi,gid=pi /dev/sda1 /mnt/USB

/usr/bin/sudo ./adb -s c089052a0120707f push /mnt/USB/build/exe.win-amd64-3.3/questions.txt /mnt/sdcard/questions.txt
/usr/bin/sudo ./adb -s c0890b1707e4c65f push /mnt/USB/build/exe.win-amd64-3.3/questions.txt /mnt/sdcard/questions.txt

sudo ./startup.sh

/usr/bin/sudo umount /mnt/USB
