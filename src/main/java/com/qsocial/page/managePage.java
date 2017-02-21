package com.qsocial.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.application.utility.ApplicationUtility;
import com.relevantcodes.extentreports.LogStatus;

public class managePage extends ApplicationUtility{
	
	
	public managePage(WebDriver driver) throws Exception
	{
		super(driver);
	}
	private String fb_Link="FacebookDateUpdate6";
	private String fb_uname="email";
	private String fb_paswd="pass";
	private String fb_loginButton="loginbutton";
	private String fb_unlink_link="#FacebookDateUpdate:nth-of-type(2)";
	private String fb_update_paswd_link="#FacebookDateUpdate:nth-of-type(1)";

	private String twitter_Account_link="TwitterDateUpdate";
	private String twitter_uname="username_or_email";
	private String twitter_paswd="password";
	private String twitter_auth_button="allow";
	private String twitter_update_paswd_link="#TwitterDateUpdate:nth-of-type(1)";
	private String twitter_unlink_link="#TwitterDateUpdate:nth-of-type(2)";

	private String linkedIn_Account_Link="LinkedInDateUpdate";
	private String linkedIn_Uname="#frame-contents form[class='grant-access']>div[class='login']>ul>li[class='email-input'] input";//"session_key-oauthAuthorizeForm";
	private String linkedIn_Paswd="session_password-oauthAuthorizeForm";
	private String linkedIn_Allowaccess_button="input[class='allow btn-primary'][name='authorize']";
    private String linkedIn_update_paswd_link="#LinkedInDateUpdate:nth-of-type(1)";
    private String linkedIn_unlink_link="#LinkedInDateUpdate:nth-of-type(2)";
    private String twitter_first_stream_post_locator="#stream-items-id>li:nth-of-type(1)>div>div[class='content']>div:nth-of-type(2)>p";
    private String twitter_login_uname=".js-username-field.email-input.js-initial-focus";
    private String twitter_login_passwd=".js-password-field";
    private String twitter_login_button="button[class='submit btn primary-btn'][type='submit']";
    private String twitter_toggle_menu_link="user-dropdown-toggle";
    private String twitter_logout_button="#signout-button>button";
    private String linkedIn_login_uname="#session_key-login";
    private String linkedIn_login_passwd="#session_password-login";
    private String linkedIn_login_button="#btn-primary";
    private String linkedin_first_stream_post_locator="#ozfeed>ol>li:nth-of-type(1) a[class='title']";
    private String linkedin_account_image=".account-toggle.nav-link>img";
    private String linkedin_logout="#account-sub-nav li[class='self'] a[class='account-submenu-split-link']";
    
    public void fixVerifyPostExistonSocialSite(String socialSite,String post_title) throws Exception
    {
    	
     if(socialSite.equalsIgnoreCase("twitter"))
       {
    	driver.get("https://twitter.com/login?lang=en");
    	if(checkIfElementIsPresent(By.cssSelector(twitter_login_uname), 10))
    	{
    		enterValueInToTextField(By.cssSelector(twitter_login_uname), envProperties.getString("Twitter_Uname"));
    		enterValueInToTextField(By.cssSelector(twitter_login_passwd), envProperties.getString("Twitter_Password"));
    		clickElementByLocator(By.cssSelector(twitter_login_button));
    		if(checkIfElementIsPresent(By.cssSelector(twitter_first_stream_post_locator), 10))
    		{
    			if(getTextOfElement(By.cssSelector(twitter_first_stream_post_locator)).trim().contains(post_title))
    			{
    				extlogger.log(LogStatus.PASS, "verify if share post is displayed on twitter", "share post is displayed on twitter");
    			}
    			else
    			{
    				extlogger.log(LogStatus.FAIL, "verify if share post is displayed on twitter", "share post is not displayed on twitter");

    			}
    			//logout
    			clickElementByLocator(By.id(twitter_toggle_menu_link));
    			clickElementByLocator(By.cssSelector(twitter_logout_button));
    			driver.get(envProperties.getString("Prod_Url"));
    		}
    	}
    	
       }
     if(socialSite.equalsIgnoreCase("linkedin"))
     {
    	 driver.get("https://www.linkedin.com/uas/login");
     	if(checkIfElementIsPresent(By.cssSelector(linkedIn_login_uname), 10))
     	{
     		enterValueInToTextField(By.cssSelector(linkedIn_login_uname), envProperties.getString("LinkedIn_Uname"));
     		enterValueInToTextField(By.cssSelector(linkedIn_login_passwd), envProperties.getString("LinkedIn_Password"));
     		clickElementByLocator(By.cssSelector(linkedIn_login_button));
     	 
        }
    	
     }
    	
    }
    
    

    public void LoginAndVerifyPostExistOnLinkedIn(String post_title) throws Exception
    {
    	if(checkIfElementIsPresent(By.cssSelector(linkedIn_login_uname), 10))
    	{
    		enterValueInToTextField(By.cssSelector(linkedIn_login_uname), envProperties.getString("LinkedIn_Uname"));
    		enterValueInToTextField(By.cssSelector(linkedIn_login_passwd), envProperties.getString("LinkedIn_Password"));
    		clickElementByLocator(By.cssSelector(linkedIn_login_button));
    		if(checkIfElementIsPresent(By.cssSelector(linkedin_first_stream_post_locator), 10))
    		{
    			if(getTextOfElement(By.cssSelector(twitter_first_stream_post_locator)).trim().contains(post_title))
    			{
    				extlogger.log(LogStatus.PASS, "verify if share post is displayed on twitter", "share post is displayed on twitter");
    			}
    			else
    			{
    				extlogger.log(LogStatus.FAIL, "verify if share post is displayed on twitter", "share post is not displayed on twitter");

    			}
    			//logout
    			clickElementByLocator(By.id(twitter_toggle_menu_link));
    			clickElementByLocator(By.cssSelector(twitter_logout_button));
    			driver.get(envProperties.getString("Prod_Url"));
    		}
    	}
    }
	public void fixLinkSocialAccount(String socialsite) throws Exception
	{
		if(socialsite.equalsIgnoreCase("facebook"))
		{
			linkFBAccount();
		}
		
		else if(socialsite.equalsIgnoreCase("twitter"))
		{
			linkTwitterAccount();
			
		}
		
		else if(socialsite.equalsIgnoreCase("linkedin"))
		{
			linkLinkedInAccount();
		}
		
	}
	
	
	public void linkFBAccount() throws Exception
	{
		clickElementByLocator(By.id(fb_Link));
		enterValueInToTextField(By.id(fb_uname), envProperties.getString("FB_Uname"));
		enterValueInToTextField(By.id(fb_paswd), envProperties.getString("FB_Password"));
		clickElementByLocator(By.id(fb_loginButton));
		if(checkIfElementIsPresent(By.className(fb_unlink_link)) && checkIfElementIsPresent(By.className(fb_update_paswd_link)))
		{
			extlogger.log(LogStatus.PASS, "verifyIf FB Account link is completed", "FB Account link is completed successfully");
		}
		else
		{
			extlogger.log(LogStatus.FAIL, "verifyIf FB Account link is completed", "FB Account link is not completed successfully");

		}
	}
	
	public void linkTwitterAccount() throws Exception
	{
		clickElementByLocator(By.id(twitter_Account_link));
		enterValueInToTextField(By.id(twitter_uname), envProperties.getString("Twitter_Uname"));
		enterValueInToTextField(By.id(twitter_paswd), envProperties.getString("Twitter_Password"));
		clickElementByLocator(By.id(twitter_auth_button));
		if(checkIfElementIsPresent(By.className(twitter_unlink_link)) && checkIfElementIsPresent(By.className(twitter_update_paswd_link)))
		{
			extlogger.log(LogStatus.PASS, "verifyIf Twitter Account link is completed", "Twitter Account link is completed successfully");
		}
		else
		{
			extlogger.log(LogStatus.FAIL, "verifyIf Twitter Account link is completed", "Twitter Account link is not completed successfully");

		}
	}
	
	
	public void linkLinkedInAccount() throws Exception
	{
		clickElementByLocator(By.id(linkedIn_Account_Link));
		enterValueInToTextField(By.cssSelector(linkedIn_Uname), envProperties.getString("LinkedIn_Uname"));
		enterValueInToTextField(By.id(linkedIn_Paswd), envProperties.getString("LinkedIn_Password"));
		clickElementByLocator(By.cssSelector(linkedIn_Allowaccess_button));
		if(checkIfElementIsPresent(By.className(linkedIn_unlink_link)) && checkIfElementIsPresent(By.className(linkedIn_update_paswd_link)))
		{
			extlogger.log(LogStatus.PASS, "verifyIf linkedIn Account link is completed", "linkedIn Account link is completed successfully");
		}
		else
		{
			extlogger.log(LogStatus.FAIL, "verifyIf linkedIn Account link is completed", "linkedIn Account link is not completed successfully");

		}
	}	

	public void linkedin_Logout() throws Exception
	{
		try
		{
		WebElement element=driver.findElement(By.cssSelector(linkedin_account_image));
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
		if(checkIfElementIsPresent(By.cssSelector(linkedin_logout), 5))
		{
			clickElementByLocator(By.cssSelector(linkedin_logout));
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


