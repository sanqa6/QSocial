package com.qsocial.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.application.utility.ApplicationUtility;
import com.relevantcodes.extentreports.LogStatus;

public class dashboardPage extends ApplicationUtility
{
	public dashboardPage(WebDriver driver) throws Exception
	{
		super(driver);
	}
	
  private String shareTab="a[href='/ShareArticle/Share']";private String trainingTab="#main_nav>li>a[href='/Training/Train']";private String discussTab="#main_nav>li>a[href='/Discuss/Discuss']";private String manageTab="a[href='/Manage/Manage']";private String peopleTab="#main_nav>li>a[href='/People/People']";
  private String gamesTab="#main_nav>li>a[href='/Games/Games']";private String reportsTab="#main_nav>li>a[href='/Reports/Reports']";
  //private String articleTab="#main_nav>li>a[href='/Articles/AdminPanel']";
  private String articleTab="a[href='/Articles/AdminPanel']";
  private String logOut_link=".logout>a";
  
  
  
  
    //verifyAdminDashboard
  
  public void goToArticlePage() throws Exception
  {
	  try
	  {
	  
	  if(checkIfElementIsPresent(By.cssSelector(articleTab),20))
	  {
		  clickElementByLocator(By.cssSelector(articleTab));
	  }
	  }
	  catch(NoSuchElementException e)
	  {
		  e.printStackTrace();
	  }
	  catch(TimeoutException e)
	  {
		  e.printStackTrace();
	  }
  }
  
  public void goToSharePage() throws Exception
  {
	  if(checkIfElementIsPresent(By.cssSelector(shareTab),20))
	  {
		  clickElementByLocator(By.cssSelector(shareTab));
	  }
  }
  
  public void goToManagePage() throws Exception
  {
	  if(checkIfElementIsPresent(By.cssSelector(manageTab),20))
	  {
		  clickElementByLocator(By.cssSelector(manageTab));
	  }
  }
  
  public void qSociallogout() throws Exception
  {
	  clickElementByLocator(By.cssSelector(logOut_link));
	  if(!checkIfElementIsPresent(By.cssSelector(logOut_link), 5))
	  {
		  extlogger.log(LogStatus.PASS, "verifyIf user is Loggedout", "Scuccesfull");
	  }
	  else
	  {
		  extlogger.log(LogStatus.FAIL, "verifyIf user is Loggedout", "Not Scuccesfull");

	  }
  }
}
