package com.qsocial.page;

import java.awt.image.TileObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.application.utility.ApplicationUtility;
import com.qsocial.utility.DataContainer;
import com.relevantcodes.extentreports.LogStatus;

public class articlePage extends ApplicationUtility{
	
	public articlePage(WebDriver driver) throws Exception
	{
		super(driver);
	}

	private String shared_link="ShareLink";
	private String choose_thumbnail_button=".share-link-media";
	private String use_media_button=".my-media";
	private String master_post_Title="Title";
	private String master_post_error="MasterPostReq";
	private String fb_master_post_disabled="input[id='facebookPostIconSelected'][value='0']";
	private String twitter_master_post_disabled="input[id='twitterPostIconSelected'][value='0']";
	private String linkedin_master_post_disabled="input[id='linkedinPostIconSelected'][value='0']";
	private String fb_master_post_button="facebookPostIcon";
	private String fb_master_post_text="fbPostContent";
	private String twitter_master_post_button="twitterPostIcon";
	private String twitter_master_post_text="twitterPostContent";
	private String linkedin_master_post_button="linkedinPostIcon";
	private String linkedin_master_post_text="liPostContent";
	private String share_media_twitter_checkbox="shareTwitterImageChkBox";
	private String author_by="Author";
	private String publish_now_button="#ui-datepicker-div>div:nth-of-type(3)>button[data-handler='today']";
	private String date_done_button="#ui-datepicker-div>div:nth-of-type(3)>button[data-handler='hide']";
	private String current_date="#ui-datepicker-div>table>tbody>tr:nth-of-type(4)>td:nth-of-type(5)>a";
	private String publish_date_field="PublishDate";
	private String segment_button="button[class='ms-choice'][type='button']";
	private String segment_list=".ms-drop.bottom>ul>li";
	private String select_segment_list=".ms-drop.bottom>ul>li input[type='checkbox'][name=";
	private String publish_button="button[class='publish-btn'][value='publish']";
	private String submit_Approval_button="button[class='save-draft-btn'][value='save']";
	private String thumbnail_image_locator=".thumb>div:nth-of-type(1)>img";
	private String thumnail_image_render_mesg="#thumbNailMessage";
	private String shareable_content_published_button="#publishedHeader";
	private String shareable_published_post_list="#newfeedpublish>div:nth-of-type(1)>div[id='box1']>a>div p";
	private String save_edit_button=".republish-edit-btn";
	private String shareable_content_scheduled_button="#scheduledHeader";
	private String shareable_scheduled_post_list="#newfeedScheduled>div:nth-of-type(1)>div[id='box1']>a>div p";
    private String shareable_scheduled_publish_button="#newfeedScheduled>div:nth-of-type(1)>div[id='box1']>a:nth-of-type(2)";
	private String hot_publish_window_button="#hotPublish";
	
	private String publish_Error_msg=".error";
	private String Post_Title="Indiatimes ad";
	
	//upload window loactors
	private String upload_window_locator=".modal.mediaSelect>.lightbox>div";
	private String none_locator=upload_window_locator+" #radioShareNone";
	private String image_locator=upload_window_locator+" #radioShareImage";
	private String video_locator=upload_window_locator+" #radioShareVideo";
	private String platform_locator="upload_window_locator+ #radioPlatformOnly";
	private String platform_social_locator="upload_window_locator+ #radioPlatformSocial";
	private String myComputer_location="#selectmedialocation #radio1";
	private String myComputer_browse_field="#file";
	private String web_location="#selectmedialocation #radio2";
	private String web_hosted_url_locator="#hostedurl";
	private String upload_continue_button=".continue-btn-green";
	
	

	 
	
	public void enterSharedlink()throws Exception
	{
		//DataContainer.articleUrl;
		try
		{
		enterValueInToTextField(By.id(shared_link), DataContainer.articleUrl);
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
	
	
	public void clickChooseThumbnail() throws Exception
	{
		try{
	  clickElementByLocator(By.cssSelector(choose_thumbnail_button));
	  
	      if(!checkIfElementIsPresent(By.className(thumbnail_image_locator),4))
			  {
		         extlogger.log(LogStatus.ERROR, "choose thumnail image", "No thumbnail image found");
			  }
		  else
		      {
			   clickElementByLocator(By.className(thumbnail_image_locator)); 
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
	
	
	public String enterMasterPost() throws Exception
	{
		Random r=new Random();
		String title=Post_Title+r.nextInt(10000);
		try
		{
		enterValueInToTextField(By.id(master_post_Title), title);
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
		catch(TimeoutException e)
		{
			e.printStackTrace();
		}
		return title;
	}
	
	public boolean selectFBPostbutton() throws Exception
	{
		
		clickElementByLocator(By.id(fb_master_post_button));
		return checkIfElementIsPresent(By.cssSelector(fb_master_post_disabled), 3);
	}
	
	public boolean selectTwitterPostbutton() throws Exception
	{
		clickElementByLocator(By.id(twitter_master_post_button));
		return checkIfElementIsPresent(By.cssSelector(twitter_master_post_disabled), 3);
	}
	
	public boolean selectLinkedinPostbutton() throws Exception
	{
		clickElementByLocator(By.id(linkedin_master_post_button));
		return checkIfElementIsPresent(By.cssSelector(linkedin_master_post_disabled), 3);
	}
	
	public boolean enterFBPostcontent() throws Exception
	{
		boolean ret=false;
		try
		{
		
		if(checkIfElementIsPresent(By.id(fb_master_post_text),20))
		{
			enterValueInToTextField(By.id(fb_master_post_text), DataContainer.shareContentmsg);
			ret=true;
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
		return ret;
	}
	
	public boolean enterTwitterPostcontent() throws Exception
	{
		boolean ret=false;
		if(checkIfElementIsPresent(By.id(twitter_master_post_text),10))
		{
			enterValueInToTextField(By.id(twitter_master_post_text), DataContainer.shareContentmsg);
			ret=true;
		}
		return ret;
	}
	
	public boolean enterLinkedinPostcontent() throws Exception
	{
		boolean ret=false;
		if(checkIfElementIsPresent(By.id(linkedin_master_post_text),10))
		{
			enterValueInToTextField(By.id(linkedin_master_post_text), DataContainer.shareContentmsg);
			ret=true;
		}
		return ret;
	}
	
	
	public void enterAuthor() throws Exception
	{
		enterValueInToTextField(By.id(author_by), DataContainer.authorName);
	}
	
	public boolean check_share_media_twitter() throws Exception
	{
		boolean ret=false;
		if(checkIfElementIsPresent(By.id(share_media_twitter_checkbox), 200))
		{
			clickElementByLocator(By.id(share_media_twitter_checkbox));
			ret=true;
		}
		return ret;
	}
	public boolean setSchedulepublishdate(String time) throws Exception
	{
		
		boolean ret=false;
		try
		{
		clickElementByLocator(By.id(publish_date_field));
		if(checkIfElementIsPresent(By.cssSelector(publish_now_button),10))
		{
			
			if(time.equalsIgnoreCase("now"))
			{
			clickElementByLocator(By.cssSelector(publish_now_button));
			}
			if(time.equalsIgnoreCase("date"))
			{
			clickElementByLocator(By.cssSelector(current_date));
			}
			
			clickElementByLocator(By.cssSelector(date_done_button));
			ret=true;
		}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("not found");
			e.printStackTrace();
		}
		catch(TimeoutException e)
		{
			System.out.println("time out found");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public void selectPublishSegment() throws Exception
	{
		String [] segments=DataContainer.segment;
		System.out.println("segments"+segments[0]);
		clickElementByLocator(By.cssSelector(segment_button));
		if(checkIfElementIsPresent(By.cssSelector(segment_list), 200))
		{
			for(String segmentName:segments)
			{
				clickElementByLocator(By.cssSelector(select_segment_list+segmentName+"]"));
			}
		}
	}
	
	public void clickPublishButton() throws Exception
	{
		clickElementByLocator(By.cssSelector(publish_button));
	}
	
	
	public void clickSubmitForApprovalButton() throws Exception
	{
		clickElementByLocator(By.cssSelector(submit_Approval_button));
	}
	
	public boolean verifyErrorMsgs(String message) throws Exception
	{
		boolean ret=false;
		String actualMesg=null;
		if(checkIfElementIsPresent(By.cssSelector(publish_Error_msg), 200))
			actualMesg=getTextOfElement(By.cssSelector(publish_Error_msg));
		{
			if(actualMesg.length()!=0)
			{
				if(actualMesg.contains(message))
				{
					ret=true;
				}
			}
		}
		return ret;
	}
	
	public void fixEditAnRticle(String articleName,List<String> socialSite) throws Exception
	{
		if(checkIfElementIsPresent(By.cssSelector(shareable_content_published_button), 5))
		{
			clickElementByLocator(By.cssSelector(shareable_content_published_button));
			if(checkIfElementIsPresent(By.cssSelector(shareable_published_post_list), 5))
			{
				if(getTextOfElement(By.cssSelector(shareable_published_post_list)).trim().equalsIgnoreCase(articleName))
						{
					      clickElementByLocator(By.cssSelector(shareable_published_post_list));
					      System.out.println("mast value"+getTextOfElement(By.id(master_post_Title)));
					      if(getAttribute(By.id(master_post_Title),"value").trim().equalsIgnoreCase(articleName))
							{
					    	  //System.out.println("goof");
					            selectSocialButtonAtArticlePage(socialSite);
					            saveEditButton();
							}
						}
			}
		}
	}
	public void saveEditButton() throws Exception
	{
		if(checkIfElementIsPresent(By.cssSelector(save_edit_button),5))
		{
		clickElementByLocator(By.cssSelector(save_edit_button));
		}
	}
	
	public String fixCreateNewArticle(List<String>socialSite,String time) throws Exception
	{
		enterSharedlink();
		clickChooseThumbnail();
		String title=enterMasterPost();
		//selectFBPostbutton();
		//enterFBPostcontent();
		selectSocialButtonAtArticlePage(socialSite);
		//selectLinkedinPostbutton();
		//enterLinkedinPostcontent();
		//selectTwitterPostbutton();
		//enterTwitterPostcontent();
		enterAuthor();
		check_share_media_twitter();
		setSchedulepublishdate(time);
		//selectPublishSegment();
         clickPublishButton();		
		return title;
	}
	
	public void fixSelectPostUnderScheduledList(String post_title) throws Exception
	{
		if(checkIfElementIsPresent(By.cssSelector(shareable_content_scheduled_button), 5))
		{
			clickElementByLocator(By.cssSelector(shareable_content_scheduled_button));
			if(checkIfElementIsPresent(By.cssSelector(shareable_scheduled_post_list), 5))
			{
				if(getTextOfElement(By.cssSelector(shareable_scheduled_post_list)).trim().equalsIgnoreCase(post_title))
				{
					clickElementByLocator(By.cssSelector(shareable_scheduled_publish_button));
					if(checkIfElementIsPresent(By.className(hot_publish_window_button), 5))
					{
						clickElementByLocator(By.className(hot_publish_window_button));
					}
				}
			}
		}
	}
	public void fixEditArticle(List<String>socialSite) throws Exception
	{
		selectSocialButtonAtArticlePage(socialSite);

	}

	
	public void selectSocialButtonAtArticlePage(List<String>socialSite) throws Exception
	{
	  for(String social:socialSite)
	  {
		if(social.endsWith("twitter"))
		{
			selectTwitterPostbutton();

		}
		if(social.endsWith("facebook"))
		{
			selectFBPostbutton();

		}

		if(social.endsWith("linkedin"))
		{
			selectLinkedinPostbutton();

		}
	  }

	}
	
	public boolean clickuploadMediaLink() throws Exception
	{
	
			clickElementByLocator(By.cssSelector(use_media_button));
			return checkIfElementIsPresent(By.cssSelector(upload_window_locator), 5);
	}
	public void fixchooseUploadImage() throws Exception
	{
		clickuploadMediaLink();
		
		clickElementByLocator(By.cssSelector(image_locator));
		clickElementByLocator(By.cssSelector(platform_locator));
		clickElementByLocator(By.cssSelector(myComputer_location));
	}
    public void uploadDoc(String fileName) throws Exception
    {
    	driver.findElement(By.cssSelector(myComputer_browse_field)).sendKeys(fileName);
    	//enterValueInToTextField(by, textValue);
    }


}
