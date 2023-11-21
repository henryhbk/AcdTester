package com.ibm.fhir.threading;

import java.io.File;

public class TestThread implements Runnable {

	private final String threadName;

	public TestThread(String threadName) {
		super();
		this.threadName = threadName;
	}

	@Override
	public void run() {
		System.out.println("Started run of " + threadName);
		doTestStuff();
		System.out.println("exiting " + threadName);

	}

	private void doTestStuff() {
		File folder = new File("/");
		File[] listOfFiles = folder.listFiles();

		System.out.println(listOfFiles.length + " files were read by thread " + threadName);
	}
}
