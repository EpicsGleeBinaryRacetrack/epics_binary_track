/*
 * buttonLED.c:
 *      Simple test program to change the blinking rate of an LED when a button is pressed
 */

#include <wiringPi.h>
#include <stdio.h>

int main (void)
{
    int pin_LED = 7;    // GPIO7 / header pin 7
    int pin_switch = 8; // SDA0 / header pin 3
    int del = 250;

    printf ("Raspberry Pi wiringPi button LED test\n") ;

    if (wiringPiSetup() == -1)
        exit (1);

    pinMode(pin_LED, OUTPUT);
    pinMode(pin_switch, INPUT);

    while(1){
        if (digitalRead (8) == 0){ // button pressed
            del = 100;
        } else {
            del = 250;
        }

        digitalWrite(pin_LED, 1);
        delay(del);
        digitalWrite(pin_LED, 0);
        delay(del);
    }

    return 0 ;
}
