package org.example.project.testbench;

import org.example.project.benchmark.IBenchmark;
import org.example.project.benchmark.cpu.CPUThreadedRoots;
import org.example.project.logging.ConsoleLogger;
import org.example.project.logging.ILogger;
import org.example.project.logging.TimeUnit;
import org.example.project.timing.ITimer;
import org.example.project.timing.Timer;


public class TestThreadedRoots {

    public static void main(String[] args) {
        ITimer timer = new Timer();
        ILogger log = new ConsoleLogger();
        TimeUnit timeUnit = TimeUnit.Sec;

        IBenchmark bench = new CPUThreadedRoots();
        bench.initialize(10000000);
        bench.warmUp();

        for (int i = 1; i <= 32; i *= 2) {
            timer.start();
            bench.run(i);
            long time = timer.stop();
            log.writeTime("[t=" + i + "] Finished in", time, timeUnit);
        }

        bench.clean();
        log.close();
    }
}