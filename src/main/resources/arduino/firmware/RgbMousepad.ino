#include <FastLED.h>
#include <SPI.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <CapacitiveSensor.h>
#include "pictures.h"
#define OLED_RESET 4
#define ID_LED 0
#define ID_SCREEN 1
#define NUM_LEDS 12
#define DATA_PIN 2

//FYI: my oled display is 128x32
Adafruit_SSD1306 display(OLED_RESET);
CRGB leds[NUM_LEDS];
int inbytes[8];
int led_config, led_brightness, led_fx;
int screen_config, screen_brightness, screen_fx;
int val1=0, val2=0, val3=0, val4=0;

// the setup function runs once when you press reset or power the board
void setup() {
	FastLED.addLeds<NEOPIXEL, DATA_PIN>(leds, NUM_LEDS);
	display.begin(SSD1306_SWITCHCAPVCC, 0x3C);
	display.setRotation(2);
	led_brightness = 45;
	splashScreen();

	Serial.begin(115200);
	display.setTextSize(1);
	display.setTextColor(WHITE);
	Serial.print("12abcdef42");
}

// the loop function runs over and over again until power down or reset
void loop() {
	FastLED.setBrightness(led_brightness);
	FastLED.show();
	printDisplay(0);
}

void serialEvent() {
	while (Serial.available()) {
		for (int n = 0; n < 8; n++) {
			inbytes[n] = Serial.read();
		}
		processInbytes();
	}
}

/*
led:		|ID|CONF|LUM|FX|IDLED| R  | G  | B  |
display:	|ID|CONF|LUM|FX|VAL1 |VAL2|VAL3|VAL4|
*/
void processInbytes() {
	if (inbytes[0] == ID_LED) {
		led_config = inbytes[1];
		led_brightness = inbytes[2];
		led_fx = inbytes[3];
		leds[inbytes[4]].r= inbytes[5];
		leds[inbytes[4]].g = inbytes[6];
		leds[inbytes[4]].b = inbytes[7];
	}
	else if (inbytes[0] == ID_SCREEN) {
		screen_config = inbytes[1];
		screen_brightness = inbytes[2];
		screen_fx = inbytes[3];
		val1 = inbytes[4];
		val2 = inbytes[5];
		val3 = inbytes[6];
		val4 = inbytes[7];
	}
}

void printDisplay(int mode) {
	if (mode == 0) {
		display.clearDisplay();
		display.setCursor(0, 0);
		//display.drawBitmap(0, 0, cpuIcon, 128, 32, 1);
		display.print("CPU:"); display.print(val1); 
		display.print("%");
		//display.setCursor(75, 0);
		//display.drawBitmap(75, 0, ramIcon, 128, 32, 1);
		//display.print("T(C):"); display.print(val2);
		display.setCursor(0, 15);
		display.print("RAM:"); display.print(val3); display.print("%");
		//display.setCursor(75, 15);
		//display.print("GPU:"); display.print(val4); display.print("%");
		display.display();
	}
}

void splashScreen() {
	display.clearDisplay();
	display.drawBitmap(0, 0, rog, 128, 64, 1);
	display.display();
	delay(2000);
	display.clearDisplay();
}
