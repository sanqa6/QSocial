package com.qsocial.utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.application.utility.WebDriverUtility;
import com.qsocial.page.loginPage;


public class captureScreenshot extends loginPage

{
	public captureScreenshot(WebDriver driver) throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}

  private static int i=0;

	public static String captureSnapshot(String screenshotname) throws Exception
    {
    	try
    	{
    		
    	i++;
    	TakesScreenshot ts =(TakesScreenshot)WebDriverUtility.driver;
        File source=ts.getScreenshotAs(OutputType.FILE);
        
        String snapPath=envProperties.getString("TestReport")+screenshotname.concat(Integer.toString(i))+".png";
        System.out.println("snapshot path"+snapPath);
        File destination=new File(snapPath);
        FileUtils.copyFile(source, destination);
        return snapPath;
    	}
    	catch(Exception e)
    	{
    		System.out.println("error while taking screenshot"+e.getMessage());
    		return e.getMessage();
    	}
    }
    
	
	
}
