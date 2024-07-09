
# GPU
GPU time-sharing lets multiple jobs use the GPU at once, like kids sharing a Lego box. They take turns grabbing "pieces" (memory) to work on their projects. This is faster than waiting for turns with the whole box, but risky. If one kid grabs too many Legos (uses too much memory), others might have nothing left (causing job failures). It's best for jobs that use memory responsibly!

https://cloud.google.com/kubernetes-engine/docs/concepts/timesharing-gpus

`However, time-sharing provides no memory limit enforcement between shared Jobs and the rapid context switching for shared access may introduce overhead.`

#### Therefore what we need to do is, either switch to NVIDIA MPS or enforce VRAM limits at container level.
But before we can do that, we need to know how much memory does each model need so that we can allocate that to them. 
To do that, we need to monitor them -- using tools like prometheus, 


So I took some time out during the weekend to read more about our issue:
and figured out that time-sharing provides no memory limit enforcement, so eventually we might have to move towards NVIDIA MPS if we would like to be safe and avoid 137s or enforce memory limits at container level

but to get to that place we might need to know what memory each workload needs, and therefore to monitor I we can use something like prometheus with NVIDIA DGCM exporter (for gpu metrics)

once we have a good idea of limits/requirements on each workload, we could move towards enforcing limits






# scaling

- **Queue Depth:** High depth means models are overloaded, consider scaling up.
- **Response Time:** Slow response times suggest model inefficiency or lack of resources, scale up or optimize models.
- **Resource Usage:** High usage indicates potential for scaling up, low usage suggests overprovisioning, consider scaling down.

![image](https://www.kubecost.com/images/hpa-overview.png)

![s](https://keda.sh/img/keda-arch.png)



how do you determine number of instances / message?







/** 
 * What we’d have to do is create an expression that would calculate how long the backlog would take to run, 
 * and scale on that target metric
 *
 * 1. processing time = time a model is taking atm to resolve one query
 * 2. backlog number = messages in the backlog
 * 3. backlog time = backlog number / processing time
 *
 * if backlog time goes above 5s, spin more up
 * if backlog time is under 5s, reduce the number of replicas
 *
 * sub 5s for each model except sub 3s for cond + quality + room scene
 */

