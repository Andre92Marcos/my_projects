package com.andre.isidoro.osm.selenium;

import java.util.Arrays;

import org.openqa.selenium.WebElement;

import com.andre.isidoro.osm.selenium.context.ContextManager;
import com.andre.isidoro.osm.selenium.logger.IMyLogger;
import com.andre.isidoro.osm.selenium.logger.MyLoggerFactory;
import com.andre.isidoro.osm.selenium.util.AppUtils;
import com.andre.isidoro.osm.selenium.util.SeleniumUtils;
import com.andre.isidoro.osm.selenium.util.propertiesFile.IOPropertiesFiles;
import com.andre.isidoro.osm.selenium.util.propertiesFile.IOPropertiesFilesFactory;

/**
 * Simple bot to train players in osm
 *
 */
public class App {

	private static final IMyLogger LOGGER = MyLoggerFactory.getMyLogger(App.class);
	private static IOPropertiesFiles OSM_CONFIG = IOPropertiesFilesFactory.getIOPropertiesFiles();

	private static final String MANAGER_NAME = OSM_CONFIG.getProperty("MANAGER_NAME");
	private static final String PASSWORD = OSM_CONFIG.getProperty("PASSWORD");

	private static final String PRIVATE_NOTICE_CHECKBOX_XPATH = ".//i[@class=\"custom-checkbox-icon fa fa-check\"]";
	private static final String PRIVATE_NOTICE_CONTINUE_BUTTON_XPATH = ".//button";
	private static final String LOGIN_PAGE_MANAGER_NAME_XPATH = ".//input[@id=\"manager-name\"]";
	private static final String LOGIN_PAGE_PASSWORD_XPATH = ".//input[@id=\"password\"]";

	// private static final String
	// HOME_PAGE_BUTTON_XPATH=".//div[@class=\"menu-title\" and contains(text(),
	// \"Home\")]";
	// private static final String
	// HOME_PAGE_BUTTON_XPATH=".//div[contains(text(),\"Home\")]";
	// private static final String HOME_PAGE_MATCH_TIMER_XPATH =
	// ".//div[@class=\"center col-xs-11 col-h-xs-24
	// pull-left\"]//span[@data-bind=\"time: secondsRemaining\"]";
	private static final String HOME_PAGE_MATCH_TIMER_XPATH = ".//span[@data-bind=\"time: secondsRemaining\"]";

	private static final String TRAINING_PAGE_TRAINING_NOT_DONE_BUTTON_XPATH = ".//button[@class=\"btn btn-success\"]";
	private static final String TRAINING_PAGE_TRAINING_BUTTON = ".//button";
	private static final String TRAINING_PAGE_CHOOSE_PLAYER_BUTTON = ".//tr";
	private static final String TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH = ".//div[contains(text(), \"Yes\")]";
	private static final String TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH = ".//button[@class=\"btn btn-success btn-show-result\"]";
	private static final String TRAINING_PAGE_START_TRAINNIG_BUTTON_XPATH = ".//button[@class=\"btn btn-primary\"]";

	private static final String TELEVISION_BUTTON_XPATH = ".//div[@class=\"television-loader center\"]";
	
	private static final String XPATH_NEW_BUTTON_VIDEO = ".//div[@class=\"row row-h-xs-150 row-h-sm-24 business-club-method-container method-container-small center watchvideo-method\"]";

	public static void main(String[] args) throws InterruptedException {
		System.out.println("test");
		LOGGER.info("args = " + Arrays.toString(args));
		LOGGER.info("Starting App");

		while (true) {

			try {
				LOGGER.info("Going to privacy notice page");
				ContextManager.getContextManager().startNewContext();
				ContextManager.getContextManager().getCurrentContext().getWebDriver()
						.get("https://en.onlinesoccermanager.com/PrivacyNotice");
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("IN privacy notice page");
				SeleniumUtils.findButtonAndClick(PRIVATE_NOTICE_CHECKBOX_XPATH);
				SeleniumUtils.findButtonAndClick(PRIVATE_NOTICE_CONTINUE_BUTTON_XPATH);
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				ContextManager.getContextManager().getCurrentContext().getWebDriver()
						.get("https://en.onlinesoccermanager.com/Login");
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("IN loggin page");
				SeleniumUtils.findInputAndWrite(LOGIN_PAGE_MANAGER_NAME_XPATH, MANAGER_NAME);
				SeleniumUtils.findInputAndWriteAndReturn(LOGIN_PAGE_PASSWORD_XPATH, PASSWORD);
				LOGGER.info("LOGGED IN");
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				Thread.sleep(5000);
				LOGGER.info("Going to Home Page");
				ContextManager.getContextManager().getCurrentContext().getWebDriver()
						.get("https://en.onlinesoccermanager.com/ControlCentre");
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("In Home page");

				watchVideo();
				watchVideo();
				watchVideo();

				LOGGER.info("Finished watching videos");
				LOGGER.info("Restarting");
				ContextManager.getContextManager().getCurrentContext().getWebDriver().quit();
				// System.exit(1);

			} catch (Exception e) {
				LOGGER.error("Critical error: ", e);
				ContextManager.getContextManager().getCurrentContext().getWebDriver().quit();
				LOGGER.info("Sleeping for 5 min and then Restarting");
				Thread.sleep(AppUtils.RECOVERY_TIMEOUT);
				LOGGER.info("Restarting");
				//System.exit(1);
			}
		}

	}

	private static final String PROFILE_BUTTON_XPATH = ".//div[@id=\"profile\"]";
	private static final String CHAT_BUTTON_XPATH = ".//li[@class=\"clickable border\"]"; // index 2
	private static final String CHAT_CHOOSE_MANAGER_BUTTON = ".//div[@class=\"pm-modal-conversation-item-name ellipsis\"]"; // choose
																															// index
	private static final String CHAT_TEXT_AREA = ".//textarea";
	private static final String CHAT_TEXT_SEND_BUTTON = ".//a[@id=\"pm-modal-message-input-button\"]";

	private static void train() throws InterruptedException {
		ContextManager.getContextManager().getCurrentContext().getWebDriver()
				.get("https://en.onlinesoccermanager.com/Training");
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
		LOGGER.info("In Trainning Page");

		if (SeleniumUtils.doesElementExist(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH)) {
			LOGGER.info("Completting Attack Training");
			SeleniumUtils.findButtonAndClick(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			LOGGER.info("Completted Attack Train");
		} else if (SeleniumUtils.doesElementExist(TRAINING_PAGE_START_TRAINNIG_BUTTON_XPATH)) {

			LOGGER.info("Training Attack");
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_TRAINING_BUTTON, 0);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_CHOOSE_PLAYER_BUTTON,
					Integer.parseInt(OSM_CONFIG.getProperty("TRAIN_ATTACK_PLAYER_INDEX")));
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			// check if confirmation to training is required
			if (SeleniumUtils.doesElementExist(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH)) {
				LOGGER.info("Confirmation Required to train Attack player");
				SeleniumUtils.findButtonAndClick(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH);
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("Confirmed  train Attack player");
			}
		} else {
			LOGGER.info("Attack Training in progress");
		}

		LOGGER.info("Trained Attack Complete");

		if (SeleniumUtils.doesElementExist(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH)) {
			LOGGER.info("Completting Midfield Training");
			SeleniumUtils.findButtonAndClick(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			LOGGER.info("Completted Midfield Train");
		} else if (SeleniumUtils.doesElementExist(TRAINING_PAGE_START_TRAINNIG_BUTTON_XPATH)) {

			LOGGER.info("Training Midfield");
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_TRAINING_BUTTON, 1);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_CHOOSE_PLAYER_BUTTON,
					Integer.parseInt(OSM_CONFIG.getProperty("TRAIN_MIDFIELD_PLAYER_INDEX")));
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			// check if confirmation to training is required
			if (SeleniumUtils.doesElementExist(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH)) {
				LOGGER.info("Confirmation Required to train midfield player");
				SeleniumUtils.findButtonAndClick(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH);
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("Confirmed  train midfield player");
			}
		} else {
			LOGGER.info("Midfield Training in progress");
		}
		LOGGER.info("Trained Midfield Complete");

		if (SeleniumUtils.doesElementExist(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH)) {
			LOGGER.info("Completting Defender Training");
			SeleniumUtils.findButtonAndClick(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			LOGGER.info("Completted Defender Train");
		} else if (SeleniumUtils.doesElementExist(TRAINING_PAGE_START_TRAINNIG_BUTTON_XPATH)) {
			LOGGER.info("Training Defender");
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_TRAINING_BUTTON, 2);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_CHOOSE_PLAYER_BUTTON,
					Integer.parseInt(OSM_CONFIG.getProperty("TRAIN_DEFENDER_PLAYER_INDEX")));
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			// check if confirmation to training is required
			if (SeleniumUtils.doesElementExist(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH)) {
				LOGGER.info("Confirmation Required to train Defender player");
				SeleniumUtils.findButtonAndClick(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH);
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("Confirmed  train Defender player");
			}
		} else {
			LOGGER.info("Defender Training in progress");
		}
		LOGGER.info("Trained Defender Complete");

		if (SeleniumUtils.doesElementExist(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH)) {
			LOGGER.info("Completting Goalkeeper Training");
			SeleniumUtils.findButtonAndClick(TRAINING_PAGE_COMPLETE_TRAINNIG_BUTTON_XPATH);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			LOGGER.info("Completted Goalkeeper Train");
		} else if (SeleniumUtils.doesElementExist(TRAINING_PAGE_START_TRAINNIG_BUTTON_XPATH)) {

			LOGGER.info("Training Goalkeeper");
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_TRAINING_BUTTON, 3);
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			SeleniumUtils.findButtonAndClickByIndex(TRAINING_PAGE_CHOOSE_PLAYER_BUTTON,
					Integer.parseInt(OSM_CONFIG.getProperty("TRAIN_GOALKEEPER_PLAYER_INDEX")));
			AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
			// check if confirmation to training is required
			if (SeleniumUtils.doesElementExist(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH)) {
				LOGGER.info("Confirmation Required to train Goalkeeper player");
				SeleniumUtils.findButtonAndClick(TRAINING_PAGE_CONFIRM_PLAYER_TO_TRAIN_BUTTON_XPATH);
				AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
				LOGGER.info("Confirmed  train Goalkeeper player");
			}
		} else {
			LOGGER.info("Goalkeeper Training in progress");
		}
		LOGGER.info("Trained Goalkeeper Complete");

		LOGGER.info("Training scheduled complete");
		LOGGER.info("Sleeping");
		Thread.sleep(AppUtils.RECOVERY_TIMEOUT);
	}

	private static void watchVideo() throws InterruptedException {
		ContextManager.getContextManager().getCurrentContext().getWebDriver()
				.get("https://en.onlinesoccermanager.com/BusinessClub");
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
		LOGGER.info("In BusinessClub page");
		SeleniumUtils.findButtonAndClickByIndex(XPATH_NEW_BUTTON_VIDEO, 0);
		AppUtils.waitForPageToFullyLoadAndBeforeNextAction();
		LOGGER.info("Sleeping for 0 seconds");
		Thread.sleep(40 * 1000);
		LOGGER.info("Watched video");
	}

	private static void sentMessagesBot(String nameOfTaget) {
		LOGGER.info("Started sending messages");
		SeleniumUtils.findButtonAndClick(PROFILE_BUTTON_XPATH);
		SeleniumUtils.findButtonAndClickByIndex(CHAT_BUTTON_XPATH, 2);
		SeleniumUtils.findButtonAndClickByIndex(CHAT_CHOOSE_MANAGER_BUTTON, 1);
		for (int i = 0; i < 101; i++) {
			SeleniumUtils.findInputAndWrite(CHAT_TEXT_AREA, "Test_" + i);
			SeleniumUtils.findButtonAndClick(CHAT_TEXT_SEND_BUTTON);
		}
		LOGGER.info("Finished sending messages");
	}

	private static boolean isMatchTimerGreaterThanTrainingTimer(String timer) {
		return (timer.length() > 7) ? true : false;
	}
}
