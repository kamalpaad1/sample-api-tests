package com;

import java.util.ArrayList;

import org.testng.TestNG;

public class App
{
	
	public static void main(String[] args)
	{
		String dir = System.getProperty("user.dir");
		ArrayList<String> file = new ArrayList<String>();
		file.add(dir+"/APITestNg.xml");
		
	    TestNG testNG = new TestNG();
	    testNG.setTestSuites(file);
	    testNG.run();
	}

}