![JDK version](https://img.shields.io/badge/JDK-1.8-blue.svg)
![Progress](https://img.shields.io/badge/progress-70%25-yellow.svg)


# Project Aurora
> _"Taking RGB gaming mousepads a step further!"_

This is an hardware-software project: if you are interested in the hardware part, check out [my post on instructables](https://www.instructables.com/id/Project-Aurora-a-Smart-Gaming-Mousepad-for-20/).
This application is supposed to handle basically everything about the mousepad: lights, oled display and macro keys.
_I really suggest you to visit the instructables post I wrote, for 2 main reasons: you can understand more, as this readme is still an early writing, and *voting the project for the color of the rainbow contest :))))*_

## Current progress
Still a work in progress: the program compiles but there's a lot more to do, unfortunately I don't have much time to work on this because I'm a student and, you know... I have to study. Whenever I have some free time, I try my best to carry on as much as possible this software.
Up to now, you can set a static color to each led, including set colors with some static layouts (such as, you can set the same color to all LEDs etc.)

## To-Do
These are some of the most important things to do but are not in priority order:

- Implementing a decent serial protocol communication (including handshakes, checksums and headers)
- Adding OLED settings handlers (as they are on stock builder values)
- Adding macro configurator (still thinking of a nice UI design)
- Implementing presets; I'm planning to make them sharable, so I need an import/export function or something similar
- Fixing all the JFrames (since they should all have the same icon and screen position)
- adding translations (up to now if you choose either IT or EN, nothing changes at all; this is because the translation files are still empty)