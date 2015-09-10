package de.fred4jupiter.fredbet.web;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Component
public class MessageUtil {

	private static final String MSG_ATTRIBUTE_NAME = "globalMessage";

	private static final String CSS_ALERT_ERROR = "alert-danger";

	private static final String CSS_ALERT_WARN = "alert-warning";

	private static final String CSS_ALT_SUCCESS = "alert-success";
	
	@Autowired
	private MessageSource messageSource;

	public void addPlainInfoMsg(RedirectAttributes redirect, String text) {
		addMessage(redirect, CSS_ALT_SUCCESS, text);
	}
	
	public void addInfoMsg(RedirectAttributes redirect, String msgKey, Object... params) {
		String message = getMessageFor(msgKey, params);
		addMessage(redirect, CSS_ALT_SUCCESS, message);
	}

	public void addPlainInfoMsg(ModelMap modelMap, String text) {
		modelMap.addAttribute(MSG_ATTRIBUTE_NAME, new WebMessage(CSS_ALT_SUCCESS, text));
	}
	
	public void addErrorMsg(ModelMap modelMap, String msgKey, Object... params) {
		String message = getMessageFor(msgKey, params);
		modelMap.addAttribute(MSG_ATTRIBUTE_NAME, new WebMessage(CSS_ALERT_ERROR, message));
	}
	
	public void addPlainErrorMsg(ModelMap modelMap, String text) {
		modelMap.addAttribute(MSG_ATTRIBUTE_NAME, new WebMessage(CSS_ALERT_ERROR, text));
	}

	private void addMessage(RedirectAttributes redirect, String cssClass, String text) {
		redirect.addFlashAttribute(MSG_ATTRIBUTE_NAME, new WebMessage(cssClass, text));
	}

	public void addPlainWarnMsg(RedirectAttributes redirect, String text) {
		addMessage(redirect, CSS_ALERT_WARN, text);
	}

	public void addPlainErrorMsg(RedirectAttributes redirect, String text) {
		addMessage(redirect, CSS_ALERT_ERROR, text);
	}

	public String getMessageFor(String msgKey, Object... params) {
		return messageSource.getMessage(msgKey, params, Locale.GERMAN);
	}

	public static final class WebMessage {
		private String cssClass;

		private String text;

		public WebMessage(String cssClass, String text) {
			super();
			this.cssClass = cssClass;
			this.text = text;
		}

		public String getCssClass() {
			return cssClass;
		}

		public String getText() {
			return text;
		}

	}
}
