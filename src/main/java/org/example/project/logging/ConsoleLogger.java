package org.example.project.logging;


public class ConsoleLogger implements ILogger {


	public void write(String string) {
		System.out.println(string);
	}


	public void write(long value) {
		System.out.println(String.valueOf(value));
	}


	public void write(Object... values) {
		for (Object o : values)
			System.out.print(o.toString() + " ");
		System.out.println();
	}


	public void writeTime(long value, TimeUnit unit) {
		System.out.println(String.valueOf(TimeUnit.toTimeUnit(value, unit)));
	}


	public void writeTime(String string, long value, TimeUnit unit) {
		System.out.println(string + " " + TimeUnit.toTimeUnit(value, unit) + " " +unit);
	}


	public void close() {
	}
}
