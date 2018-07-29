package com.kat.myapp.view.helper;

/*
 * MsgTextColors.java
 *
 * Created on April 8, 2014, 11:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 */
public final class ABSMsgTextColors {
	public String cssCode = "";
	public String fgColor = "black";
	public String bgColor = "white";
	public boolean isUnderlined = false;
	public boolean isBlinking = false;
	public boolean isBold = false;
	public boolean isColumned = false;
	private static ABSMsgTextColors m_blk = new ABSMsgTextColors(null);
	private static ABSMsgTextColors m_blu = new ABSMsgTextColors("BLU");
	private static ABSMsgTextColors m_blu_ri = new ABSMsgTextColors("BLU RI");
	private static ABSMsgTextColors m_blu_ul = new ABSMsgTextColors("BLU UL");
	private static ABSMsgTextColors m_grn = new ABSMsgTextColors("GRN");
	private static ABSMsgTextColors m_grn_ri = new ABSMsgTextColors("GRN RI");
	private static ABSMsgTextColors m_grn_ul = new ABSMsgTextColors("GRN UL");
	private static ABSMsgTextColors m_grn_ul_ri = new ABSMsgTextColors("GRN UL RI");
	private static ABSMsgTextColors m_pnk = new ABSMsgTextColors("PNK");
	private static ABSMsgTextColors m_pnk_ri = new ABSMsgTextColors("PNK RI");
	private static ABSMsgTextColors m_pnk_ul = new ABSMsgTextColors("PNK UL");
	private static ABSMsgTextColors m_pnk_ul_ri = new ABSMsgTextColors("PNK UL RI");
	private static ABSMsgTextColors m_red = new ABSMsgTextColors("RED");
	private static ABSMsgTextColors m_red_hi = new ABSMsgTextColors("RED HI");
	private static ABSMsgTextColors m_red_hi_ri = new ABSMsgTextColors("RED HI RI");
	private static ABSMsgTextColors m_red_ri = new ABSMsgTextColors("RED RI");
	private static ABSMsgTextColors m_red_ul = new ABSMsgTextColors("RED UL");
	private static ABSMsgTextColors m_red_ul_bl = new ABSMsgTextColors("RED UL BL");
	private static ABSMsgTextColors m_red_ul_ri = new ABSMsgTextColors("RED UL RI");
	private static ABSMsgTextColors m_trq = new ABSMsgTextColors("TRQ");
	private static ABSMsgTextColors m_trq_cs_ri = new ABSMsgTextColors("TRQ CS RI");
	private static ABSMsgTextColors m_trq_ul_cs = new ABSMsgTextColors("TRQ UL CS");
	private static ABSMsgTextColors m_trq_ul_csr = new ABSMsgTextColors("TRQ UL CSR");
	private static ABSMsgTextColors m_wht = new ABSMsgTextColors("WHT");
	private static ABSMsgTextColors m_wht_cs_ri = new ABSMsgTextColors("WHT CS RI");
	private static ABSMsgTextColors m_wht_ri = new ABSMsgTextColors("WHT RI");
	private static ABSMsgTextColors m_wht_ul = new ABSMsgTextColors("WHT UL");
	private static ABSMsgTextColors m_ylw = new ABSMsgTextColors("YLW");
	private static ABSMsgTextColors m_ylw_ul_cs = new ABSMsgTextColors("YLW UL CS");

	/** Creates a new instance of MsgTextColors */
	private ABSMsgTextColors(String hex) {
		if (hex == null) {
			return;
		}
		if (hex.equalsIgnoreCase("BLU")) {
			fgColor = CommonUiUtils.BLUE_FG_COLOR;
			cssCode = "blu_fg_row";
			return;
		}
		if (hex.equalsIgnoreCase("BLU RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.BLUE_BG_COLOR;
			cssCode = "blu_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("BLU UL")) {
			fgColor = CommonUiUtils.BLUE_FG_COLOR;
			isUnderlined = true;
			cssCode = "blu_ul_row";
			return;
		}
		if (hex.equalsIgnoreCase("GRN")) {
			fgColor = CommonUiUtils.GREEN_FG_COLOR;
			cssCode = "grn_row";
			return;
		}
		if (hex.equalsIgnoreCase("GRN RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.GREEN_BG_COLOR;
			cssCode = "grn_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("GRN UL")) {
			fgColor = CommonUiUtils.GREEN_FG_COLOR;
			isUnderlined = true;
			cssCode = "grn_ul_row";
			return;
		}
		if (hex.equalsIgnoreCase("GRN UL RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.GREEN_BG_COLOR;
			isUnderlined = true;
			cssCode = "grn_ul_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("PNK")) {
			fgColor = CommonUiUtils.PINK_FG_COLOR;
			cssCode = "pnk_row";
			return;
		}
		if (hex.equalsIgnoreCase("PNK RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.PINK_BG_COLOR;
			cssCode = "pnk_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("PNK UL")) {
			fgColor = CommonUiUtils.PINK_FG_COLOR;
			isUnderlined = true;
			cssCode = "pnk_ul_row";
			return;
		}
		if (hex.equalsIgnoreCase("PNK UL RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.PINK_BG_COLOR;
			isUnderlined = true;
			cssCode = "pnk_ul_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED")) {
			fgColor = CommonUiUtils.RED_BG_COLOR;
			cssCode = "red_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED HI")) {
			fgColor = CommonUiUtils.RED_BG_COLOR;
			isBold = true;
			isBlinking = true;
			cssCode = "red_hi_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED HI RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.RED_BG_COLOR;
			isBold = true;
			isBlinking = true;
			cssCode = "red_hi_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.RED_BG_COLOR;
			cssCode = "red_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED UL")) {
			fgColor = CommonUiUtils.RED_BG_COLOR;
			isUnderlined = true;
			cssCode = "red_ul_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED UL BL")) {
			fgColor = CommonUiUtils.RED_BG_COLOR;
			isUnderlined = true;
			isBlinking = true;
			cssCode = "red_hi_row";
			return;
		}
		if (hex.equalsIgnoreCase("RED UL RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.RED_BG_COLOR;
			isUnderlined = true;
			cssCode = "red_ul_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("TRQ")) {
			fgColor = CommonUiUtils.TURQUOISE_FG_COLOR;
			cssCode = "trq_row";
			return;
		}
		if (hex.equalsIgnoreCase("TRQ CS RI")) {
			fgColor = "black";
			bgColor = CommonUiUtils.TURQUOISE_BG_COLOR;
			isColumned = true;
			cssCode = "trq_cs_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("TRQ UL CS")) {
			fgColor = CommonUiUtils.TURQUOISE_FG_COLOR;
			isUnderlined = true;
			isColumned = true;
			cssCode = "trq_ul_cs_row";
			return;
		}
		if (hex.equalsIgnoreCase("TRQ UL CSR")) {
			fgColor = "black";
			bgColor = CommonUiUtils.TURQUOISE_BG_COLOR;
			isUnderlined = true;
			isColumned = true;
			cssCode = "trq_ul_csr_row";
			return;
		}
		if (hex.equalsIgnoreCase("WHT")) {
			fgColor = "white";
			bgColor = "black";
			cssCode = "wht_row";
			return;
		}
		if (hex.equalsIgnoreCase("WHT CS RI")) {
			fgColor = "black";
			isColumned = true;
			cssCode = "wht_cs_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("WHT RI")) {
			fgColor = "black";
			cssCode = "wht_ri_row";
			return;
		}
		if (hex.equalsIgnoreCase("WHT UL")) {
			fgColor = "white";
			bgColor = "black";
			isUnderlined = true;
			cssCode = "wht_ul_row";
			return;
		}
		if (hex.equalsIgnoreCase("YLW")) {
			fgColor = CommonUiUtils.YELLOW_FG_COLOR;
			bgColor = "black";
			cssCode = "ylw_row";
			return;
		}
		if (hex.equalsIgnoreCase("YLW UL CS")) {
			fgColor = CommonUiUtils.YELLOW_FG_COLOR;
			isUnderlined = true;
			bgColor = "black";
			isColumned = true;
			cssCode = "ylw_ul_cs_row";
		}

	}

	public static ABSMsgTextColors convertSysColorToMsgTextColors(String hex) {
		if (hex == null) {
			return m_blk;
		}
		if (hex.equalsIgnoreCase("BLU")) {
			return m_blu;
		}
		if (hex.equalsIgnoreCase("BLU RI")) {
			return m_blu_ri;
		}
		if (hex.equalsIgnoreCase("BLU UL")) {
			return m_blu_ul;
		}
		if (hex.equalsIgnoreCase("GRN")) {
			return m_grn;
		}
		if (hex.equalsIgnoreCase("GRN RI")) {
			return m_grn_ri;
		}
		if (hex.equalsIgnoreCase("GRN UL")) {
			return m_grn_ul;
		}
		if (hex.equalsIgnoreCase("GRN UL RI")) {
			return m_grn_ul_ri;
		}
		if (hex.equalsIgnoreCase("PNK")) {
			return m_pnk;
		}
		if (hex.equalsIgnoreCase("PNK RI")) {
			return m_pnk_ri;
		}
		if (hex.equalsIgnoreCase("PNK UL")) {
			return m_pnk_ul;
		}
		if (hex.equalsIgnoreCase("PNK UL RI")) {
			return m_pnk_ul_ri;
		}
		if (hex.equalsIgnoreCase("RED")) {
			return m_red;
		}
		if (hex.equalsIgnoreCase("RED HI")) {
			return m_red_hi;
		}
		if (hex.equalsIgnoreCase("RED HI RI")) {
			return m_red_hi_ri;
		}
		if (hex.equalsIgnoreCase("RED RI")) {
			return m_red_ri;
		}
		if (hex.equalsIgnoreCase("RED UL")) {
			return m_red_ul;
		}
		if (hex.equalsIgnoreCase("RED UL BL")) {
			return m_red_ul_bl;
		}
		if (hex.equalsIgnoreCase("RED UL RI")) {
			return m_red_ul_ri;
		}
		if (hex.equalsIgnoreCase("TRQ")) {
			return m_trq;
		}
		if (hex.equalsIgnoreCase("TRQ CS RI")) {
			return m_trq_cs_ri;
		}
		if (hex.equalsIgnoreCase("TRQ UL CS")) {
			return m_trq_ul_cs;
		}
		if (hex.equalsIgnoreCase("TRQ UL CSR")) {
			return m_trq_ul_csr;
		}
		if (hex.equalsIgnoreCase("WHT")) {
			return m_wht;
		}
		if (hex.equalsIgnoreCase("WHT CS RI")) {
			return m_wht_cs_ri;
		}
		if (hex.equalsIgnoreCase("WHT RI")) {
			return m_wht_ri;
		}
		if (hex.equalsIgnoreCase("WHT UL")) {
			return m_wht_ul;
		}
		if (hex.equalsIgnoreCase("YLW")) {
			return m_ylw;
		}
		if (hex.equalsIgnoreCase("YLW UL CS")) {
			return m_ylw_ul_cs;
		}
		return m_blk;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		// String style = "border-bottom: 1px solid #bbbbbb; border-right: 1px
		// solid #bbbbbb;";
		String style = "background:" + bgColor+";";
		if (!fgColor.equals("black")) {
			style += "color: " + fgColor + ";";
		}
		if (isBold) {
			style += "font-weight: bold;";
		}
		if (isUnderlined) {
			style += "text-decoration: underline;";
		}

		/*
		 * if (isBlinking) { style += "outline: 1px solid "+fgColor+";"; }
		 */

		return style;
	}
	
	public String getCssCode(){
		return cssCode;
	}

	/**
	 * @return the isReversed
	 */
	public boolean isReversed() {
		return !bgColor.equals("white");
	}

}
