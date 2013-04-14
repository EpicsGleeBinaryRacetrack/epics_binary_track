/*
 * buttonLED.c:
 *      Simple test program to change the blinking rate of an LED when a button is pressed
 */

#include <wiringPi.h>
#include <stdio.h>
#include <stdlib.h>

int main (int argc, char ** argv)
{
    int player1 = 4;    // GPIO7 / header pin 7
    int player2 = 5;    // GPIO7 / header pin 7
    int del = 750;

    FILE *logFile = fopen("log.txt", "w");
    fprintf(logFile, "Moving player %s\n", argv[1]);

    int pin = 0;
    if(atoi(argv[1]) == 1){
        pin = player1;
    }else if(atoi(argv[1]) == 2){
        pin = player2;
    }

    if (wiringPiSetup() == -1)
        exit (1);

    pinMode(pin, OUTPUT);

    digitalWrite(pin, 1);
    delay(del);
    digitalWrite(pin, 0);

    return 0 ;
}
