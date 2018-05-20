#include <CapacitiveSensor.h>

CapacitiveSensor   key1 = CapacitiveSensor(3, 4);        // 1.5M resistor between pins 3 & 4, pin 4 is sensor pin, add a wire and or foil if desired
CapacitiveSensor   key2 = CapacitiveSensor(5, 6);        // 1.5M resistor between pins 5 & 6, pin 6 is sensor pin, add a wire and or foil
CapacitiveSensor   key3 = CapacitiveSensor(7, 8);        // 1.5M resistor between pins 7 & 8, pin 8 is sensor pin, add a wire and or foil

void setup()
{
		Serial.begin(9600);
}

void loop()
{
	long total1 = key1.capacitiveSensor(30);
	long total2 = key2.capacitiveSensor(30);
	long total3 = key3.capacitiveSensor(30);

	Serial.print(total1);                  // print sensor output 1
	Serial.print("\t");
	Serial.print(total2);                  // print sensor output 2
	Serial.print("\t");
	Serial.println(total3);                // print sensor output 3

	delay(10);
}
