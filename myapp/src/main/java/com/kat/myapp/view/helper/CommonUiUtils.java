package com.kat.myapp.view.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;

public final class CommonUiUtils {

	public static final String MSG_TYPE_REPLYLE = "*REPLYLE";
	public static final String MSG_TYPE_REPLYDFT = "*REPLYDFT";
	public static final String MSG_TYPE_REPLY = "*REPLY";
	public static final String MSG_TYPE_ESCAPE = "*ESCAPE";
	public static final String MSG_TYPE_NOTIFY = "*NOTIFY";
	public static final String MSG_TYPE_REQUEST = "*RQS";
	public static final String MSG_TYPE_COPY = "*COPY";
	public static final String MSG_TYPE_INQUIRY = "*INQ";
	public static final String MSG_TYPE_INFO = "*INFO";
	public static final String MSG_TYPE_DIAGNOSTIC = "*DIAG";
	public static final String MSG_TYPE_COMPLETE = "*COMP";
	public static ThemeResource detailIcon = new ThemeResource("img/view-details.png");
	public static ThemeResource jobIcon = new ThemeResource("img/letter-j.png");
	public static ThemeResource aliasIcon = new ThemeResource("img/alias.gif");
	public static ThemeResource userIcon = new ThemeResource("img/UserIcon.png");
	public static ThemeResource envIcon = new ThemeResource("img/environment.jpg");
	public static ThemeResource deleteIcon = new ThemeResource("img/delete.png");
	public static ThemeResource reloadIcon = new ThemeResource("img/reload.png");
	public static ThemeResource filterIcon = new ThemeResource("img/filter.png");
	public static ThemeResource servicePointIcon = new ThemeResource("img/service_point_icon.png");
	public static final String ARCHIVE_VIEW = "ARCHIVE VIEW";
	public static final String NETWORK_VIEW = "NETWORK VIEW";
	public static final String LOCAL_VIEW = "LOCAL VIEW";
	
	public static final String INCLUDE = "INCLUDE";
	public static final String EXCLUDE = "EXCLUDE";
	public static final String ONLY = "ONLY";
	
	public static final String YELLOW_BG_LABEL = "Yellow";
	public static final String RED_BG_LABEL = "Red";
	public static final String GREEN_BG_LABEL = "Green";
	public static final String BLUE_BG_LABEL = "Blue";
	public static final String PINK_BG_LABEL = "Pink";
	public static final String TURQUOISE_BG_LABEL = "Cyan";
	public static final String GREY_BG_LABEL = "Grey";
	public static final String WHITE_BG_LABEL = "White";

	public static final String YELLOW_FG_LABEL = "Yellow_FG";
	public static final String RED_FG_LABEL = "Red_FG";
	public static final String GREEN_FG_LABEL = "Green_FG";
	public static final String BLUE_FG_LABEL = "Blue_FG";
	public static final String PINK_FG_LABEL = "Pink_FG";
	public static final String TURQUOISE_FG_LABEL = "Cyan_FG";
	public static final String GREY_FG_LABEL = "Grey_FG";
	public static final String WHITE_FG_LABEL = "White_FG";
	public static final String GREY1_BG_LABEL = "Grey1";
	public static final String BOLD_FONT_LABEL = "Bold_Font";
	public static final String UNDERLINE_FONT_LABEL = "Underline_Font";

	public static final String GREEN_BG_COLOR = "#CCFF99";
	public static final String YELLOW_BG_COLOR = "#FFFF99";
	public static final String RED_BG_COLOR = "#FF3333";
	public static final String BLUE_BG_COLOR = "#3333ff";
	public static final String PINK_BG_COLOR = "#ff3399";
	public static final String TURQUOISE_BG_COLOR = "#99ffff";
	public static final String WHITE_BG_COLOR = "#ffffff";
	public static final String GREY_BG_COLOR = "#cccccc";
	public static final String BLUE_FG_COLOR = "#0000ff";
	public static final String PINK_FG_COLOR = "#ff0066";
	public static final String TURQUOISE_FG_COLOR = "#009999";
	public static final String YELLOW_FG_COLOR = "#ffff99";
	public static final String GREY1_BG_COLOR = "#999999";
	public static final String GREEN_FG_COLOR = "#33cc33";
	public static final String DARK_GREEN_BG_COLOR = "#339933";

	static final String NULL_TEXT = "<null>";

	private CommonUiUtils() {
	}

	public static void styleTabSheet(TabSheet tabSheet) {
		tabSheet.setSizeFull();
		tabSheet.addStyleName(ValoTheme.TABSHEET_FRAMED);
		tabSheet.addStyleName(ValoTheme.TABSHEET_COMPACT_TABBAR);
		tabSheet.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
	}

	public static TabSheet createTabSheet() {
		TabSheet tabSheet = new TabSheet();
		styleTabSheet(tabSheet);
		return tabSheet;
	}

	public static Button createPrimaryButton(String name) {
		return createPrimaryButton(name, null);
	}

	public static Button createPrimaryButton(String name, Button.ClickListener listener) {
		Button button = new Button(name);
		if (listener != null) {
			button.addClickListener(listener);
		}
		button.addStyleName(ValoTheme.BUTTON_PRIMARY);
		return button;
	}

	public static void notify(String message) {
		notify("", message, com.vaadin.ui.Notification.Type.HUMANIZED_MESSAGE);
	}

	public static void notify(String caption, String message) {
		notify(caption, message, Type.HUMANIZED_MESSAGE);
	}

	public static void notify(String message, Type type) {
		notify("", message, type);
	}

	public static void notify(String caption, String message, Type type) {
		notify(caption, message, null, type);
	}

	public static void notify(String caption, String message, Throwable ex, Type type) {
		Page page = Page.getCurrent();
		if (page != null) {
			Notification notification = new Notification(caption, (UIStringUtils.wordWrap(message, 150, Locale.US)),
					Type.HUMANIZED_MESSAGE);
			// Notification notification = new Notification(caption,
			// contactWithLineFeed(message), Type.HUMANIZED_MESSAGE);
			notification.setPosition(Position.MIDDLE_CENTER);
			notification.setDelayMsec(-1);

			String style = ValoTheme.NOTIFICATION_SUCCESS;
			if (type == Type.ERROR_MESSAGE) {
				style = ValoTheme.NOTIFICATION_FAILURE;
			} else if (type == Type.WARNING_MESSAGE) {
				style = ValoTheme.NOTIFICATION_WARNING;
			}
			notification
					.setStyleName(notification.getStyleName() + " " + ValoTheme.NOTIFICATION_CLOSABLE + " " + style);
			notification.show(Page.getCurrent());
		}
	}

	public static String processExceptionMsg(String message) {

		// String replaceAll = message.replaceAll("&N", "\n");
		// String temp = replaceAll.replaceAll("&P", "\n\t\t");
		String temp1 = message.replace(". . . . .", "");
		String temp2 = temp1.replace(". . .", "");
		String text = temp2.replaceAll("\\.\\s?", "\\.\n");
		return text;
	}

	private static String contactWithLineFeed(String[] lines) {
		StringBuilder line = new StringBuilder();
		for (String l : lines) {
			line.append(l).append("\n");
		}
		return line.toString();
	}

	public static void notify(String message, Throwable ex) {
		notify("An error occurred", message, ex, Type.ERROR_MESSAGE);
	}

	public static void notify(Throwable ex) {
		notify("An unexpected error occurred", "See the log file for additional details", ex, Type.ERROR_MESSAGE);
	}

	public static String[] getHeaderCaptions(Grid grid) {
		List<String> headers = new ArrayList<String>();
		List<Column> columns = grid.getColumns();
		for (Column column : columns) {
			headers.add(column.getCaption());
		}
		return headers.toArray(new String[headers.size()]);
	}

	protected static String castToNumber(String value) {
		if ("NO".equalsIgnoreCase(value) || "FALSE".equalsIgnoreCase(value)) {
			return "0";
		} else if ("YES".equalsIgnoreCase(value) || "TRUE".equalsIgnoreCase(value)) {
			return "1";
		} else {
			return value.replace(",", ".");
		}
	}

	public static String formatDuration(long timeInMs) {
		if (timeInMs > 60000) {
			long minutes = timeInMs / 60000;
			long seconds = (timeInMs - (minutes * 60000)) / 1000;
			return minutes + " m " + seconds + " s";
		} else if (timeInMs > 1000) {
			long seconds = timeInMs / 1000;
			return seconds + " s";
		} else {
			return timeInMs + " ms";
		}
	}

	public static Label createSeparator() {
		Label separator = new Label(" ");
		separator.setStyleName("vrule");
		separator.setHeight(100, Unit.PERCENTAGE);
		separator.setWidthUndefined();
		return separator;
	}

//	public static String htmlFormatAvailability(ConsoleEntry msgEntry) {
//		String msgType = msgEntry.getMsgType();
//		String ackStatus = msgEntry.getAckStatus();
//		String replyStatus = msgEntry.getReplyStatus();
//
//		String mainKey = msgType + replyStatus + ackStatus;
//
//		HashMap<String, String> consoleColorMap = null;
//
//		if (msgEntry.getConsole() != null && msgEntry.getConsole().getConsoleColor() != null) {
//			consoleColorMap = msgEntry.getConsole().getConsoleColor().getColorMap();
//
//		}
//
//		String iconCode = "";
//
//		String color = "";
//
//		String msgTypeText = msgEntry.getMsgTypeText();
//
//		switch (msgType) {
//		case "01":
//
//			color = DARK_GREEN_BG_COLOR;
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.MEDAL.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.MEDAL.getCodepoint()) + ";</span>";
//			break;
//		case "02":
//
//			color = BLUE_BG_COLOR;
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.EXCLAMATION.getFontFamily()
//					+ ";color:" + color + "\">&#x" + Integer.toHexString(VaadinIcons.EXCLAMATION.getCodepoint())
//					+ ";</span>";
//			break;
//		case "04":
//
//			String string = null;
//			color = BLUE_BG_COLOR;
//			if (mainKey.trim().contains("C")) {
//				string = consoleColorMap.get("04NC");
//				// color = YELLOW_BG_COLOR;
//			} else if (mainKey.trim().contains("O")) {
//				string = consoleColorMap.get("04NO");
//				// color = RED_BG_COLOR;
//			} else if (mainKey.trim().contains("P")) {
//				string = consoleColorMap.get("04NP");
//				// color = PINK_BG_COLOR;
//			}
//			if (string != null && string != "") {
//				color = processMsgTypeConsoleColor(string);
//			}
//
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.INFO.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.INFO.getCodepoint()) + ";</span>";
//			break;
//		case "05":
//
//			String string1 = null;
//			if (mainKey.trim().contains("W")) {
//				string1 = consoleColorMap.get("05W");
//				// color = RED_BG_COLOR;
//			} else if (mainKey.trim().contains("A")) {
//				string1 = consoleColorMap.get("05A");
//				// color = YELLOW_BG_COLOR;
//			} else if (mainKey.trim().contains("D")) {
//				string1 = consoleColorMap.get("05D");
//				// color = DARK_GREEN_BG_COLOR;
//			} else if (mainKey.trim().contains("P")) {
//				string1 = consoleColorMap.get("05P");
//				// color = PINK_BG_COLOR;
//			}
//
//			if (string1 != null && string1 != "") {
//				color = processMsgTypeConsoleColor(string1);
//			}
//
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.QUESTION.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.QUESTION.getCodepoint()) + ";</span>";
//			break;
//
//		case "14":
//
//			color = BLUE_BG_COLOR;
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.STAR.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.STAR.getCodepoint()) + ";</span>";
//			break;
//		case "15":
//
//			color = DARK_GREEN_BG_COLOR;
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.ESC.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.ESC.getCodepoint()) + ";</span>";
//			break;
//
//		default:
//			break;
//		}
//
//		return iconCode + "" + msgTypeText;
//	}

//	public static String processMsgTypeConsoleColor(String sysColorStyle) {
//		String fgColor = "";
//		String bgColor = "";
//		boolean isUnderlined = false;
//		boolean isColumned = false;
//		boolean isBold = false;
//		boolean isBlinking = false;
//
//		if (sysColorStyle.equalsIgnoreCase("BLU")) {
//			fgColor = BLUE_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("BLU RI")) {
//			fgColor = "black";
//			fgColor = bgColor = BLUE_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("BLU UL")) {
//			fgColor = BLUE_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("GRN")) {
//			fgColor = DARK_GREEN_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("GRN RI")) {
//			fgColor = "black";
//			fgColor = bgColor = DARK_GREEN_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("GRN UL")) {
//			fgColor = DARK_GREEN_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("GRN UL RI")) {
//			fgColor = "black";
//			fgColor = bgColor = DARK_GREEN_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("PNK") || sysColorStyle.equalsIgnoreCase("PNK RI")) {
//			fgColor = PINK_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("PNK RI")) {
//			fgColor = "black";
//			fgColor = bgColor = PINK_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("PNK UL")) {
//			fgColor = PINK_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("PNK UL RI")) {
//			fgColor = "black";
//			fgColor = bgColor = PINK_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED")) {
//			fgColor = RED_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED HI")) {
//			fgColor = RED_BG_COLOR;
//			isBold = true;
//			isBlinking = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED HI RI")) {
//			fgColor = "black";
//			fgColor = bgColor = RED_BG_COLOR;
//			isBold = true;
//			isBlinking = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED RI")) {
//			fgColor = "black";
//			fgColor = bgColor = RED_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED UL")) {
//			fgColor = RED_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED UL BL")) {
//			fgColor = RED_BG_COLOR;
//			isUnderlined = true;
//			isBlinking = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("RED UL RI")) {
//			fgColor = "black";
//			fgColor = bgColor = RED_BG_COLOR;
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("TRQ")) {
//			fgColor = TURQUOISE_BG_COLOR;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("TRQ CS RI")) {
//			fgColor = "black";
//			fgColor = bgColor = TURQUOISE_BG_COLOR;
//			isColumned = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("TRQ UL CS")) {
//			fgColor = TURQUOISE_BG_COLOR;
//			isUnderlined = true;
//			isColumned = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("TRQ UL CSR")) {
//			fgColor = "black";
//			fgColor = bgColor = TURQUOISE_BG_COLOR;
//			isUnderlined = true;
//			isColumned = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("WHT")) {
//			fgColor = "white";
//			fgColor = bgColor = "black";
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("WHT CS RI")) {
//			fgColor = "black";
//			isColumned = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("WHT RI")) {
//			fgColor = "black";
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("WHT UL")) {
//			fgColor = "white";
//			fgColor = bgColor = "black";
//			isUnderlined = true;
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("YLW")) {
//			fgColor = "yellow";
//			bgColor = "black";
//
//		}
//		if (sysColorStyle.equalsIgnoreCase("YLW UL CS")) {
//			fgColor = "yellow";
//			isUnderlined = true;
//			bgColor = "black";
//			isColumned = true;
//		}
//		return fgColor;
//	}

//	public static String filterHistoryHtmlFormatAvailability(FilterHistoryEntry msgEntry) {
//		String availability = msgEntry.getMsgType();
//
//		String iconCode = "";
//		String color = "";
//		String subStringAvailability = availability.substring(1);
//		switch (subStringAvailability) {
//		case "01":
//			// mtyp01.png
//			availability = MSG_TYPE_COMPLETE;
//			color = "#228B22";
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.MEDAL.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.MEDAL.getCodepoint()) + ";</span>";
//			break;
//		case "02":
//			availability = MSG_TYPE_DIAGNOSTIC;
//			color = "#3A82E4";
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.EXCLAMATION.getFontFamily()
//					+ ";color:" + color + "\">&#x" + Integer.toHexString(VaadinIcons.EXCLAMATION.getCodepoint())
//					+ ";</span>";
//			break;
//		case "04":
//			availability = MSG_TYPE_INFO;
//			color = "#3A82E4";
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.INFO.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.INFO.getCodepoint()) + ";</span>";
//			break;
//		case "05":
//			availability = MSG_TYPE_INQUIRY;
//			if (msgEntry.getMsgType().trim().startsWith("W")) {
//				color = "#ff0000";
//			} else {
//				color = "#228B22";
//			}
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.QUESTION.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.QUESTION.getCodepoint()) + ";</span>";
//			break;
//		case "06":
//			availability = MSG_TYPE_COPY;
//			break;
//		case "08":
//			availability = MSG_TYPE_REQUEST;
//			break;
//		case "10":
//			availability = MSG_TYPE_REQUEST;
//			break;
//		case "14":
//			availability = MSG_TYPE_NOTIFY;
//			color = "#3A82E4";
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.STAR.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.STAR.getCodepoint()) + ";</span>";
//			break;
//		case "15":
//			availability = MSG_TYPE_ESCAPE;
//			color = "#228B22";
//			iconCode = "<span class=\"v-icon\" style=\"font-family: " + VaadinIcons.ESC.getFontFamily() + ";color:"
//					+ color + "\">&#x" + Integer.toHexString(VaadinIcons.ESC.getCodepoint()) + ";</span>";
//			break;
//		case "17":
//			availability = MSG_TYPE_ESCAPE;
//			break;
//		case "16":
//			availability = MSG_TYPE_NOTIFY;
//			break;
//		case "21":
//			availability = MSG_TYPE_REPLY;
//		case "22":
//			availability = MSG_TYPE_REPLY;
//			break;
//		case "23":
//			availability = MSG_TYPE_REPLYDFT;
//			break;
//		case "24":
//			availability = MSG_TYPE_REPLYDFT;
//			break;
//		case "25":
//			availability = MSG_TYPE_REPLYLE;
//			break;
//		default:
//			break;
//		}
//
//		return iconCode + "" + availability;
//	}
//
}
