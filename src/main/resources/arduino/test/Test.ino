#include <CapacitiveSensor.h>


long time = 0;
int state = HIGH;

boolean yes;

int debounce = 500;

CapacitiveSensor   cs_4_2 = CapacitiveSensor(3,4);        
void setup() {
}

void loop() {
	cs_4_2.set_CS_AutocaL_Millis(0xFFFFFFFF);
	long total1 = cs_4_2.capacitiveSensor(30);


	if (total1 > 70) { 
		yes = true; 
	}
	else { 
		yes = false; 
	}

	// to toggle the state
	if (yes == true && millis() - time>debounce) {

		if (state == LOW) {
			Serial.println("on");
			state = HIGH;
		}
		else {
			Serial.println("off");
			state = LOW;
		}

		time = millis();
	}

	delay(10);
}
