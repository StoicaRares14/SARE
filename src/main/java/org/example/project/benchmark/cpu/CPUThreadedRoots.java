package org.example.project.benchmark.cpu;

import org.example.project.benchmark.IBenchmark;

public class CPUThreadedRoots implements IBenchmark {

    private double result;
    private int size;
    private boolean running;


    public void initialize(Object... params) {
        this.size = (Integer) params[0];
        // save size from params array
    }


    public void warmUp() {
        run(Runtime.getRuntime().availableProcessors());
        // call run method: call run() once
        // detect number of cores: Runtime.....availableProcessors();

    }


    public void run() {
        throw new UnsupportedOperationException(
                "Method not implemented. Use run(Objects...) instead");
    }


    public void run(Object... options) {
        // options[0] -> number of threads
        // ...
        int nThreads = (Integer) options[0];

        Thread[] threads = new Thread[nThreads];

        // e.g. 1 to 10,000 on 4 threads = 2500 jobs per thread
        final int jobPerThread = (int) Math.ceil(size * 1.0 / nThreads); /**/

        running = true; // flag used to stop all started threads
        // create a thread for each runnable (SquareRootTask) and start it
        for (int i = 0; (i < nThreads) && running; ++i) {
            // ...
           threads[i] = new Thread(new SquareRootTask(jobPerThread*i,jobPerThread*(i+1)));
           threads[i].start();
        }

        // join threads
        for (int i = 0; (i < nThreads) && running; ++i) {
            // ...
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void cancel() {
        running = false;
    }


    public void clean() {
        // only implement if needed
    }


    public String getResult() {
        return String.valueOf(result);
    }

    public synchronized void updateValue(double k){
        this.result += k;
    }

    class SquareRootTask implements Runnable {

        private int from, to;
        private final double precision = 1e-4; // fixed
        private double result = 0.0;

        public SquareRootTask(int from, int to) {
            // save params to class members
            this.from = from;
            this.to = to;
        }

        public void run() {

            for (int i = from; i < to && running == true; i++){
              result += getNewtonian(i);
            }
            // compute Newtonian square root on each number from i = 'from' to 'to', and also check 'running'
            // save (+=) each computed square root in the local 'result' variable
            // extra: send 'result' back to main thread and sum up with all results
            updateValue(result);
        }

        private double getNewtonian(double x) {
            // ... implement the algorithm for Newton's square root(x) here
            double s = x;
            while(Math.abs(s*s - x) > precision){
                s = (x/s + s)/2;
            }
            return s;
        }

        // extra: compute sum, pass it back to wrapper class. Use synchronized


    }

}