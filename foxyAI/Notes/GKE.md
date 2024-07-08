
# GPU
GPU time-sharing lets multiple jobs use the GPU at once, like kids sharing a Lego box. They take turns grabbing "pieces" (memory) to work on their projects. This is faster than waiting for turns with the whole box, but risky. If one kid grabs too many Legos (uses too much memory), others might have nothing left (causing job failures). It's best for jobs that use memory responsibly!

https://cloud.google.com/kubernetes-engine/docs/concepts/timesharing-gpus

`However, time-sharing provides no memory limit enforcement between shared Jobs and the rapid context switching for shared access may introduce overhead.`

#### Therefore what we need to do is, either switch to NVIDIA MPS or enforce VRAM limits at container level.
But before we can do that, we need to know how much memory does each model need so that we can allocate that to them. 
To do that, we need to monitor them -- using tools like prometheus, 