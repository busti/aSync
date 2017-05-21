# Audio Sync

Stage Lighting is typically controlled using pre-generated effects that have been created
to exactly match the setup they are being applied to.
This often limits the creation of an effect to a single setup
and makes setting up shows a long and tideous process,
that often requires to know the exact layout of the show days if not weeks
before the actual event in order to plan and prepare for it correctly.

Sudden changes in the layout or fixtures used can often not be adapted without efford and time.

Furthermore are effects often limited to the environment they have been created in and cannot be shared with other users.

Traditionally such effects have been keyframe based, meaning that the operator would have to set
all fixtures to the required states on the first keyframe, then on the second and so on, until the effect is completed.
The used software would then interpolate between the keyframes to render a smooth animation.  
In the recent years LED Pixels have become more and more important, which most software soultions attempt to control
using video playback to predefined LED setups or matrix effects.

While all these solutions are good for their respective field of application, they lack the flexibility to be more portable
and adapt to situation changes quickly and effortless.
But when trying to overcome those issue the complexity of the software often increases to a point where users are presented with scripting.


## Proof of concept

This project is an attempt to provide maximum flexibility when creating effects and applying those effects to a specific setup,
while still trying to keep the software easy to understand and work with.
Furthermore does it attempt to enable operators to make changes to the show, while it is running, without the audience noticing,
while still providing maximum real time feedback on the changes made.

The main aspect of this project is its visual programming language, which provides the main context of interaction with connected
controllers and fixtures.  

To get started lets take a look at controlling the color of an RGB - Par Fixture using a linear slider or rotary knob.  
Lets assume that the software is able to recieve data from a Midi device which contains said slider.

