package logisticspipes.utils;

import net.minecraft.world.World;

public class SafeTimeTracker {

    private long lastMark = Long.MIN_VALUE;
    private long duration = -1;
    private long randomRange = 0;
    private long lastRandomDelay = 0;
    private final long internalDelay;

    public SafeTimeTracker(long delay) {
        internalDelay = delay;
    }

    /**
     * In many situations, it is a bad idea to have all objects of the same kind to be waiting for the exact same amount
     * of time, as that can lead to some situation where they're all synchronized and got to work all at the same time.
     * When created with a random range, the mark that is set when reaching the expect delay will be added with a random
     * number between [0, range[, meaning that the event will take between 0 and range more tick to run.
     */
    public SafeTimeTracker(long delay, long random) {
        internalDelay = delay;
        randomRange = random;
    }

    public boolean markTimeIfDelay(World world) {
        return markTimeIfDelay(world, internalDelay);
    }

    private boolean markTimeIfDelay(World world, long delay) {
        if (world == null) {
            return false;
        }

        long currentTime = world.getTotalWorldTime();

        if (currentTime < lastMark) {
            lastMark = currentTime;
            return false;
        } else if (lastMark + delay + lastRandomDelay <= currentTime) {
            duration = currentTime - lastMark;
            lastMark = currentTime;
            lastRandomDelay = (int) (Math.random() * randomRange);

            return true;
        } else {
            return false;
        }
    }

    public long durationOfLastDelay() {
        return duration > 0 ? duration : 0;
    }

    public void markTime(World world) {
        lastMark = world.getTotalWorldTime();
    }
}
