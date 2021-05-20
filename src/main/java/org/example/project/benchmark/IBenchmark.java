package org.example.project.benchmark;

public interface IBenchmark {

	/**
	 * Called to explicitly initialize org.example.project.benchmark data. <br>
	 * This call should not be benchmarked.
	 * 
	 * @param params variable list of arguments (of any type) needed to setup the org.example.project.benchmark
	 */
	void initialize(Object... params);

	/**
	 * Called right before running the algorithm itself to "warm-up" the task at
	 * hand. <br>
	 * The warm up should do the exact task as the run method, however it should not
	 * be timed. <br>
	 * The amount of warm-up data/time should be between 10-100% of the total time.
	 * <br>
	 * This call should not be benchmarked.
	 */
	void warmUp();

	/**
	 * Calls the actual benchmarking algorithm, optionally after <b>initialize</b>
	 * was called. <br>
	 * This call should be benchmarked.
	 */
	void run();

	/**
	 * Calls the actual benchmarking algorithm, optionally after <b>initialize</b>
	 * was called. <br>
	 * This call should be benchmarked.
	 * 
	 * @param options May pass a org.example.project.benchmark option defined by the org.example.project.benchmark class
	 *               itself
	 */
	void run(Object... options);

	/**
	 * Stops a org.example.project.benchmark during execution. <br>
	 * Should be checked in the main for-loop, or main thread of the org.example.project.benchmark.
	 */
	void cancel();

	/**
	 * Called to explicitly release allocated data. <br>
	 * This call should not be benchmarked.
	 */
	void clean();

	/**
	 * Return the result of the org.example.project.benchmark. <br>
	 * This call should not be benchmarked.
	 * 
	 * @return
	 */
	String getResult();
}
