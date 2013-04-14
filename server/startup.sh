./adb -s c0890b1707e4c65f forward tcp:5001 tcp:8081
./adb -s c089052a0120707f forward tcp:5002 tcp:8081


./adb -s c089052a0120707f uninstall epics.binarytrack
./adb -s c089052a0120707f install ../android/bin/BinaryTrack.apk
./adb -s c089052a0120707f shell am start -n epics.binarytrack/epics.binarytrack.MainActivity

./adb -s c0890b1707e4c65f uninstall epics.binarytrack
./adb -s c0890b1707e4c65f install ../android/bin/BinaryTrack.apk
./adb -s c0890b1707e4c65f shell am start -n epics.binarytrack/epics.binarytrack.MainActivity

while true; do
java Main
done
