package org.example.project.timing;

public class Timer implements ITimer {

	private long elapsed = 0;
	private long stored = 0;
	private TimerState state;

	public Timer() {
		state = TimerState.Stopped;
	}


	public void start() {
		stored = 0;
		resume();
	}


	public long stop() {
		if (state.equals(TimerState.Running)) {
			elapsed = System.nanoTime() - elapsed;
			state = TimerState.Stopped;
			stored += elapsed;

			return stored;
		} else
			return stored;
	}


	public long pause() {
		elapsed = System.nanoTime() - elapsed;
		state = TimerState.Paused;
		stored += elapsed;

		return stored;
	}


	public void resume() {
		state = TimerState.Running;
		elapsed = System.nanoTime();
	}
}
