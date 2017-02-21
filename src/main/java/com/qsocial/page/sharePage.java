package com.qsocial.page;

//import org.apache.http.entity.mime.content.ContentDescriptor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import com.application.utility.ApplicationUtility;
import com.qsocial.utility.DataContainer;
import com.relevantcodes.extentreports.LogStatus;

public class sharePage extends ApplicationUtility{
	
	
	
	
	public sharePage(WebDriver driver) throws Exception
	{
		super(driver);
	}
	
	//obej repository-----------------
    private String articleTitle=".articleTitle";
    private String articleContent=".article-content>p>a";
    private String share_button="share";
    private String push_button="button[class='push-btn-chkd'][name='ButtonSubmit_Finished']";
	private String share_window_title=".lightbox.share>h3";
	private String share_linkedIn_button="linkedinButton";
	private String share_linkedIn_Text_Area="liPostContent";
    private String share_facebook_button="facebookButton";
    private String share_facebook_Text_Area="fbPostContent";
	private String share_twitter_button="twitterButtonShare";
	private String share_twitter_Text_Area="twitterPostContent";
	private String view_Article_url="viewArticleURL";
	private String share_submit_failure_message="shareSubmitFailureNetwork";
	private String share_noNetwork_message="shareNoNetworks";
	private String window_disabled_share_button="#disableShareBtn[style='']>#disableShareButton>span";
	private String window_enabled_share_button="#enableShareBtn[style=''] #shareSubmit";
	private String share_window_close="close";
	private String sharable_content="#NewsFeed1>#box";
	private String no_network_share_msg="Oops, you don't have any social networks connected. Click here to link an account so you can start sharing.";
	private String Error_Msg_Not_Select_Network="Please select at least one social network.";
	private String no_network_share_msg_locator="#shareNoNetworks";
	private String Link_Account_here_link="#shareNoNetworks>a";

	
	public boolean shareArticleonSocial(String socialSite) throws Exception
	{
		boolean ret=false;
	  if (socialSite.equalsIgnoreCase("facebook"))
	  {
		  ret=shareOnFB();
	  }
	  if (socialSite.equalsIgnoreCase("twitter"))
	  {
		  ret=shareOnTwitter();
	  }
	  if (socialSite.equalsIgnoreCase("linkedIn"))
	  {
		  ret=shareOnLinkedIn();
		  
	  }
	  return ret;
	}
	
	
	public boolean shareOnFB() throws Exception
	{
		
		boolean ret=false;
		if(checkIfElementIsPresent(By.id(share_facebook_button), 10))
		  {
			  ret=true;
			  clickElementByLocator(By.id(share_facebook_button));
			  enterValueInToTextField(By.id(share_facebook_Text_Area), DataContainer.shareContentmsg);
		  }
		return ret;
	}
	
	public boolean shareOnTwitter() throws Exception
	{
		boolean ret=false;

		if(checkIfElementIsPresent(By.id(share_twitter_button), 10))
		  {
			   ret=true;
			  clickElementByLocator(By.id(share_twitter_button));
			  enterValueInToTextField(By.id(share_twitter_Text_Area), DataContainer.shareContentmsg);
		  }
		return ret;
	}
	
	public boolean shareOnLinkedIn() throws Exception
	{
		boolean ret=false;

		if(checkIfElementIsPresent(By.id(share_linkedIn_button), 10))
		  {
			  ret=true;
			  clickElementByLocator(By.id(share_linkedIn_button));
			  enterValueInToTextField(By.id(share_linkedIn_Text_Area), DataContainer.shareContentmsg);
		  } 
		
		return ret;
	}
	
	public boolean clickshareButton() throws Exception
	{
		clickElementByLocator(By.id(share_button));
		return checkIfElementIsPresent(By.cssSelector(share_window_title),20);
	}
	
	public void clickWindowShareButton() throws Exception
	{
	   try
	   {
		clickElementByLocator(By.cssSelector(window_enabled_share_button));
		if(getTextOfElement(By.id(share_submit_failure_message)).length()!=0)
		{
			if(getTextOfElement(By.id(share_submit_failure_message)).trim().equalsIgnoreCase(Error_Msg_Not_Select_Network))
			{
				extlogger.log(LogStatus.PASS, "VerifyOnNotSelcting any network while sharing", "Message is correct");
			}
		}
		else
		{
			if(checkIfElementIsPresent(By.cssSelector(share_window_title), 5))
			{
				extlogger.log(LogStatus.FAIL, "is Window closed after sharring", "Share Window is not closed even after sharring");

			}
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
	public void closeShareWindow() throws Exception
	{
	
		clickElementByLocator(By.id(share_window_close));
		//return checkIfElementIsPresent(By.cssSelector(share_window_title));
	}
	public boolean fixVerifyNoNetworkShareWindow() throws Exception
	{
		boolean ret= true;
		if(clickshareButton())
		{
			if(checkIfElementIsPresent(By.cssSelector(no_network_share_msg_locator), 10))
			{
				if(getTextOfElement(By.cssSelector(no_network_share_msg_locator)).trim().equalsIgnoreCase(no_network_share_msg.trim()))
				{
					ret=false;
					extlogger.log(LogStatus.PASS, "verify if no_network_message is displayed", "no_network_message correctly as expected is displayed is  displayed share window");
					
				}
				
			}
			else
			{
				ret=false;
				extlogger.log(LogStatus.PASS, "verify if no_network_message is displayed", "no_network_message is displayed is missing from share window");
				
			}
			if(!checkIfElementIsPresent(By.cssSelector(window_disabled_share_button), 10))
				ret=false;
			{
				extlogger.log(LogStatus.PASS, "verify if disabled share button  is displayed", "disabled share button is not displayed");

			}
			
			closeShareWindow();
			
		}
		return ret;
	}
	
	
	
	public void fixverifyLatestPostPresntOnSharePage(String title) throws Exception
	{
		if(getTextOfElement(By.cssSelector(articleTitle)).equalsIgnoreCase(title))
				{
			      extlogger.log(LogStatus.PASS, "verify matching latest created post title on share tab", "latest post present");
				}
		else
				{
			      extlogger.log(LogStatus.FAIL, "verify matching latest created post title on share tab", "latest post not present");
				}
	}
	
	
	public void fixSharePostOnSocialNetwork(String socialSite) throws Exception
	{
		
		shareArticleonSocial(socialSite);
		
	}
	
    public void fixverifyIfSocialButtonExistOnShareWindow(String socialSite) throws Exception
    {
    	boolean ret=false;
    	try
    	{
    	if(socialSite.equalsIgnoreCase("twitter"))
    	{
    		if(!checkIfElementIsPresent(By.id(share_twitter_button), 5))
    		{
    			extlogger.log(LogStatus.FAIL, "verify twitter share button exist on share window", "not found");
    		}
    		
    	}
    	if(socialSite.equalsIgnoreCase("facebook"))
    	{
    		if(!checkIfElementIsPresent(By.id(share_facebook_button), 5))
    		{
    			extlogger.log(LogStatus.FAIL, "verify facebook share button exist on share window", "not found");
    		}
    		
    	}
    	if(socialSite.equalsIgnoreCase("linkedin"))
    	{
    		if(!checkIfElementIsPresent(By.id(share_linkedIn_button), 5))
    		{
    			extlogger.log(LogStatus.FAIL, "verify linkedin share button exist on share window", "not found");
    		}
    		
    	}
    	closeShareWindow();
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
    
	
}
