//package com.kat.myapp.grid;
//
//
//import com.kat.myapp.backend.database.WorkOrder;
//import com.kat.myapp.view.UIStringUtils;
//import com.kat.myapp.view.CommonUiUtils;
//import com.vaadin.ui.Grid;
//import com.vaadin.ui.renderers.HtmlRenderer;
//
///**
// * Grid of products, handling the visual presentation and filtering of a set of
// * items. This version uses an in-memory data source that is suitable for small
// * data sets.
// */
//public class MyCarWorkOrderGrid extends Grid<WorkOrder> {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = -7214315301825012609L;
//
//	public MyCarWorkOrderGrid() {
//		setSizeFull();
//
//		addColumn(CommonUiUtils::htmlFormatAvailability, new HtmlRenderer()).setId(UIStringUtils.TYPE_TXT_ID)
//				.setCaption("TYPE").setMinimumWidth(100).setComparator((p1, p2) -> {
//					return p1.getMsgType().toString().compareTo(p2.getMsgType().toString());
//				});
//
//		addColumn(ConsoleEntry::getTimeStamp).setId(UIStringUtils.TIME_STAMP_TXT_ID).setCaption("TIME STAMP")
//				.setMinimumWidth(230);
//		addColumn(ConsoleEntry::getMsgId).setId(UIStringUtils.MSG_ID).setCaption("MSG ID").setMinimumWidth(100);
//		addColumn(ConsoleEntry::getMsgSystem).setId(UIStringUtils.SYSTEM_TXT_ID).setCaption("SYSTEM")
//				.setMinimumWidth(100);
//		addColumn(ConsoleEntry::getSeverity).setId(UIStringUtils.SEVERITY_TXT_ID).setCaption("SEVERITY")
//				.setMinimumWidth(50);
//		addColumn(ConsoleEntry::getNIH).setId(UIStringUtils.NIH_TXT_ID).setCaption("NIH").setMinimumWidth(45);
//		addColumn(ConsoleEntry::getJob).setId(UIStringUtils.JOB_TXT_ID).setCaption("JOB").setMinimumWidth(120);
//		addColumn(ConsoleEntry::getMsgText).setId(UIStringUtils.MSG_TXT_ID).setCaption("MESSAGE TEXT")
//				.setMinimumWidth(450);
//
//		addStyleName("smallgrid");
//
//	}
//
//	public ConsoleEntry getSelectedRow() {
//		return asSingleSelect().getValue();
//	}
//
//	public void refresh(ConsoleEntry consoleEntry) {
//		getDataCommunicator().refresh(consoleEntry);
//	}
//
//}
