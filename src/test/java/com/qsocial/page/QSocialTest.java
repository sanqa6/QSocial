package com.qsocial.page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qsocial.utility.captureScreenshot;
import com.qsocial.page.loginPage;
import com.qsocial.page.articlePage;
import com.qsocial.page.managePage;
import com.qsocial.page.sharePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Unit test for simple App.
 */
public class QSocialTest extends loginPage
{
	public ITestResult result; 


   public QSocialTest() throws Exception {
		super(driver);
		// TODO Auto-generated constructor stub
	}
   private sharePage shareObj;
   private articlePage articleObj;
   private loginPage loginObj;
   private managePage manageObj;
   private dashboardPage dashObj;
   private String Post_Title1=null;
   private String Post_Title2=null;
   private List <String> social1=new ArrayList<String>();
   private List <String> social2=new ArrayList<String>();
   
   
   @Parameters("browser")
   @BeforeTest
   private void setUp(String browser) throws Exception
   {
	   shareObj=new sharePage(driver);
	   articleObj=new articlePage(driver);
	   manageObj=new managePage(driver);
	   loginObj=new loginPage(driver);
	   dashObj=new dashboardPage(driver);
	   extent=new ExtentReports(envProperties.getString("Test_Report")+"automationTestReport.html", true);
       System.out.println(extent.toString());
       extent.config().documentTitle("Test Automation");
	   extent.config().reportHeadline("QSocial Automation Test Report");
	   startWebDriver(browser);
	
	   driver.get(envProperties.getString("Prod_Url"));
	   //loginObj.loginQSocial("Admin", "testsan", "Password@12");

		
   }
  
   @Test
   private void loginToQSocial() throws Exception
   {
	   extlogger=extent.startTest(" Login to Q social ");
	   //loginObj.loginQSocial("Admin", "testsan", "Password@12");
	   loginObj.loginQSocial("Admin", "SodexoAdmin", "Test1234");
		  Assert.assertEquals(extlogger.getRunStatus(), LogStatus.PASS);

   }
   
   @Test
   private void VerifyCreateNewArticleAndPublish() throws Exception
   {
	   social1.add("facebook");
	   social1.add("linkedin");
	   social1.add("twitter");
	   
	   extlogger=extent.startTest("VerifyCreateNewArticle");
	   System.out.println("h1");
	   dashObj.goToArticlePage();
	   Post_Title1=articleObj.fixCreateNewArticle(social1,"date");
	   dashObj.goToSharePage();
	   shareObj.fixverifyLatestPostPresntOnSharePage(Post_Title1);
	   social1.clear();
	   
   }
   


   @Test
   private void VerifyShareWindowWithNoNetworkLinked() throws Exception
   {
	   extlogger=extent.startTest("VerifyShareWindowWithNoNetworkLinked");
	   dashObj.goToSharePage();
	   shareObj.fixverifyLatestPostPresntOnSharePage(Post_Title1);
	   shareObj.fixVerifyNoNetworkShareWindow();

   }
   
   @Test
   private void verifyLinkingSocialSite() throws Exception
   {
	   extlogger=extent.startTest("verifyLinkingSocialSite");

	   dashObj.goToManagePage();
	   //manageObj.fixLinkSocialAccount("twitter");
	   manageObj.fixLinkSocialAccount("facebook");
   }
   
   @Test
   
   private void verifyShareOnSocialNetwork() throws Exception
   {
	   extlogger=extent.startTest("verifyShareOnSocialNetwork");

	   dashObj.goToSharePage();
	   shareObj.clickshareButton();
	   shareObj.fixSharePostOnSocialNetwork("facebook");
	   shareObj.fixSharePostOnSocialNetwork("linkedin");
	   shareObj.fixSharePostOnSocialNetwork("twitter");
	   shareObj.clickWindowShareButton();
	   dashObj.qSociallogout();
	   manageObj.fixVerifyPostExistonSocialSite("twitter", Post_Title1);
   }
    
  @Test 
private void verifyAssociatedSocialIconWithArticleonSharePage() throws Exception
{
	  
	   extlogger=extent.startTest("verifyAssociatedSocialIconWithArticleonSharePage");
	   social2.add("facebook");
	   dashObj.goToArticlePage();
	   Post_Title2=articleObj.fixCreateNewArticle(social2,"date");
	   //write code to verify social icon at share page
	   dashObj.goToSharePage();
	   
	   shareObj.fixverifyLatestPostPresntOnSharePage(Post_Title2);
	   shareObj.clickshareButton();
	   shareObj.fixverifyIfSocialButtonExistOnShareWindow(social2.get(0));
	   //write code to edit same article
	   social2.clear();
	   social2.add("linkedin");
	   dashObj.goToArticlePage();
	  // 
	   articleObj.fixEditAnRticle(Post_Title2,social2);
	   dashObj.goToSharePage();
	   shareObj.fixverifyLatestPostPresntOnSharePage(Post_Title2);
	   shareObj.clickshareButton();
	   shareObj.fixverifyIfSocialButtonExistOnShareWindow(social2.get(0));
	   social2.clear();
	   social2.add("twitter");
	   dashObj.goToArticlePage();

	   articleObj.fixEditAnRticle(Post_Title2,social2);
	   dashObj.goToSharePage();
	   shareObj.fixverifyLatestPostPresntOnSharePage(Post_Title2);
	   shareObj.clickshareButton();
	   shareObj.fixverifyIfSocialButtonExistOnShareWindow(social2.get(0));
	   social2.clear();

}
   @Test
   private void verifyScheduleAnArticleAndPublish() throws Exception
   {
	   social1.add("facebook"); 
	   dashObj.goToArticlePage();
	   Post_Title1=articleObj.fixCreateNewArticle(social1,"now");
	   articleObj.fixSelectPostUnderScheduledList(Post_Title1);
	   dashObj.goToSharePage();
	   shareObj.fixverifyLatestPostPresntOnSharePage(Post_Title1);
	   social1.clear();

   }
   
   //@Test
   //private void verify
   
	@AfterMethod
	protected void afterEachTest(ITestResult result) {
		//closeWindow();

		//System.out.println("kyaaaa");

   	try
   	{
   	if(result.getStatus()==ITestResult.FAILURE||extlogger.getRunStatus()==LogStatus.ERROR)
   	{
   		String screenpath=captureScreenshot.captureSnapshot(result.getName());
   		//System.out.println("kya"+screenpath);
   		if(screenpath!=null)
   		{
   			extlogger.log(LogStatus.FAIL,"image stored at given path",screenpath);
   		}
   	}
   	}
   	catch(Exception e)
   	{
   		e.printStackTrace();
   	}
   	extent.endTest(extlogger);
       extent.flush();
   }

	@AfterTest
	public void shutdown() throws IOException 
	{
		//extent.close();
		quitWebDriver();
    }
}