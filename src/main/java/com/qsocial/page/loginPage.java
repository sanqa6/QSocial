package com.qsocial.page;

import com.application.utility.ApplicationUtility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.LogStatus;

/**
 * Hello world!
 *
 */
public class loginPage extends ApplicationUtility
{
    
	private String Uname="UserName";
	private String Pwd="Password";
	private String submit=".login-btn";
	private String loginAsClient="DDLClient";
	private String login_error=".validation-summary-errors";
	private String dash_board_user_name=".user-name";
	
	public loginPage(WebDriver driver) throws Exception
	{
		super(driver);
	}
	
	
	public void loginQSocial(String userRole,String uName,String pwd) throws Exception
	{
			try
			{
		    enterValueInToTextField(By.id(Uname), uName);
			enterValueInToTextField(By.id(Pwd), pwd);
			clickElementByLocator(By.cssSelector(submit));
			if(userRole.equalsIgnoreCase("superadmin"))
			{
				if(checkIfElementIsPresent(By.cssSelector(loginAsClient),20))
				{
					selectFromDropDownByVisibleText(By.cssSelector(loginAsClient), "CA");
					enterValueInToTextField(By.id(Uname), uName);
					enterValueInToTextField(By.id(Pwd), pwd);
					clickElementByLocator(By.cssSelector(submit));
			
				}
			}
			
			if(!checkIfElementIsPresent(By.cssSelector(login_error),5))
			{
				if(checkIfElementIsPresent(By.cssSelector(dash_board_user_name),200))
				{
					System.out.println("user is logged in successfully");
					extlogger.log(LogStatus.PASS,"user login status"," user is logged in successfully");
				}
			}
			else
			{
				System.out.println("login is unsuccessfull");
				extlogger.log(LogStatus.FAIL,"user login status"," user is not logged ,check login error");
			}
			}
			catch(TimeoutException e)
			{
				e.printStackTrace();
			}
			catch(NoSuchElementException e)
			{
			  e.printStackTrace();	
			}
		
	}
}
