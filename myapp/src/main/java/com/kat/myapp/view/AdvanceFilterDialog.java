//
//package com.kat.myapp.view;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.sea.service.exception.ServiceException;
//import com.sea.service.product.ABSMessage;
//import com.sea.service.product.message.GlobalSelectionHeader;
//import com.sea.service.product.message.Selection;
//import com.sea.service.product.message.SystemGroup;
//import com.sea.service.product.message.console.Console;
//import com.sea.service.product.message.manage.ManageSystemGroups;
//import com.sea.service.product.scheduler.console.ConsoleContext;
//import com.sea.service.product.scheduler.console.ConsolePerspective;
//import com.kat.myapp.data.access.AccessControlIF;
//import com.vaadin.data.HasValue.ValueChangeEvent;
//import com.vaadin.data.HasValue.ValueChangeListener;
//import com.vaadin.ui.Alignment;
//import com.vaadin.ui.Button;
//import com.vaadin.ui.ComboBox;
//import com.vaadin.ui.Component;
//import com.vaadin.ui.CssLayout;
//import com.vaadin.ui.DateField;
//import com.vaadin.ui.GridLayout;
//import com.vaadin.ui.HorizontalLayout;
//import com.vaadin.ui.ItemCaptionGenerator;
//import com.vaadin.ui.Label;
//import com.vaadin.ui.Notification;
//import com.vaadin.ui.Panel;
//import com.vaadin.ui.RadioButtonGroup;
//import com.vaadin.ui.TextField;
//import com.vaadin.ui.themes.ValoTheme;
//
//public class AdvanceFilterDialog extends ResizableWindow {
//
//	private static final String RELATIVE = "RELATIVE";
//
//	private static final String ABSOLUTE = "ABSOLUTE";
//
//	private static final long serialVersionUID = 1L;
//
//	private MyCarMainView msgView;
//
//	private AccessControlIF accessControl;
//
//	private ComboBox<SystemGroup> systemField;
//
//	private RadioButtonGroup perspectiveRadioBtnGrp;
//
//	private RadioButtonGroup dateTimeRadioBtnGrp;
//
//	private DateField absDateField;
//
//	private Button saveToGlobeBtn;
//
//	private Button saveNRefreshBtn;
//
//	private Button clearBtn;
//
//	private TextField userTxtField;
//
//	private TextField jobTxtField;
//
//	private TextField jobNoTxtField;
//
//	private TextField msgIdTxtField;
//
//	private ComboBox msgTypeCmbField;
//
//	private ComboBox replyTypeCmbField;
//
//	private ComboBox ackCmbField;
//
//	private RadioButtonGroup ackSelectRadioBtnGrp;
//
//	private RadioButtonGroup severitySelectRadioBtnGrp;
//
//	private TextField severityTxtField;
//
//	private TextField msgQueueTxtField;
//
//	private RadioButtonGroup tstMsgRadioBtnGrp;
//
//	private RadioButtonGroup notesRadioBtnGrp;
//
//	private RadioButtonGroup absRadioBtnGrp;
//
//	private RadioButtonGroup relativeRadioBtnGrp;
//
//	private TextField relativeTxtField;
//
//	private ABSMessage message;
//
//	private Console console;
//
//	private Selection selectionObj;
//
//	private ComboBox<GlobalSelectionHeader> globalSelectField;
//
//	private RadioButtonGroup<String> dateFilterRadioBtnGrp;
//
//	protected GlobalSelectionHeader selectedGobalValue = null;
//
//	private HashMap<String, SystemGroup> systemGroupMap;
//
//	public AdvanceFilterDialog(MyCarMainView msgView, AccessControlIF accessControl) {
//		super(" Advance Filter", CommonUiUtils.filterIcon);
//		this.msgView = msgView;
//		this.accessControl = accessControl;
//		message = msgView.getMessage();
//		console = msgView.getConsole();
//
//		// Set window position.
//		this.setPositionX(200);
//		this.setPositionY(50);
//
//		Panel panel = new Panel();
//		panel.setStyleName(ValoTheme.PANEL_BORDERLESS);
//		panel.setSizeUndefined();
//
//		CssLayout content = new CssLayout();
//		// content.setSpacing(false);
//		// content.setMargin(false);
//
//		this.center();
//
//		createFilterDetailLayout(content);
//
//		panel.setContent(content);
//
//		setContent(panel);
//
//	}
//
//	protected Component createFilterDetailLayout(CssLayout mainLayout) {
//
//		Component detailForm = buildFilterForm(mainLayout);
//
//		return detailForm;
//
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private Component buildFilterForm(CssLayout mainLayout) {
//
//		GridLayout gridLayout = new GridLayout(15, 16);
//
//		// Row 0
//		int rowIndex = 0;
//		Label label = new Label("Global Select");
//		gridLayout.addComponent(label, 0, rowIndex);
//		globalSelectField = new ComboBox<>();
//		globalSelectField.setItemCaptionGenerator(new ItemCaptionGenerator() {
//			@Override
//			public String apply(Object item) {
//				// TODO Auto-generated method stub
//				if (item instanceof GlobalSelectionHeader) {
//					GlobalSelectionHeader global = (GlobalSelectionHeader) item;
//					return global.getName().trim() + " : " + global.getDesc().trim();
//				}
//				return null;
//			}
//		});
//		globalSelectField.setSizeFull();
//		gridLayout.addComponent(globalSelectField, 1, rowIndex, 12, rowIndex);
//
//		// Row 1
//		rowIndex = 1;
//		perspectiveRadioBtnGrp = new RadioButtonGroup<String>();
//		perspectiveRadioBtnGrp.setItems(CommonUiUtils.LOCAL_VIEW, CommonUiUtils.NETWORK_VIEW);
//		perspectiveRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		perspectiveRadioBtnGrp.setSizeUndefined();
//
//		label = new Label("Select Perspective");
//		gridLayout.addComponent(label, 0, rowIndex);
//		gridLayout.addComponent(perspectiveRadioBtnGrp, 1, rowIndex, 10, rowIndex);
//		gridLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
//
//		// Row 2
//		rowIndex = 2;
//		label = new Label("System");
//		gridLayout.addComponent(label, 0, rowIndex);
//		systemField = new ComboBox<>();
//		systemField.setItemCaptionGenerator(new ItemCaptionGenerator() {
//			@Override
//			public String apply(Object item) {
//				// TODO Auto-generated method stub
//				if (item instanceof SystemGroup) {
//					SystemGroup group = (SystemGroup) item;
//					return group.getId().trim() + " : " + group.getDesc().trim();
//				}
//				return null;
//			}
//		});
//
//		systemField.setItemCaptionGenerator(SystemGroup::getId);
//		systemField.setSizeFull();
//		gridLayout.addComponent(systemField, 1, rowIndex, 12, rowIndex);
//		gridLayout.setRowExpandRatio(1, 0.1F);
//
//		// Row 3
//		rowIndex = 3;
//		label = new Label("Date");
//		gridLayout.addComponent(label, 0, rowIndex);
//		absDateField = new DateField();
//		absDateField.setSizeFull();
//		gridLayout.addComponent(absDateField, 1, rowIndex, 10, rowIndex);
//
//		absRadioBtnGrp = new RadioButtonGroup<>();
//		absRadioBtnGrp.setItems(ABSOLUTE);
//		absRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		absRadioBtnGrp.setSizeUndefined();
//		gridLayout.addComponent(absRadioBtnGrp, 11, rowIndex, 12, rowIndex);
//
//		// Row 4
//		rowIndex = 4;
//		label = new Label("Include Last 'n' days");
//		gridLayout.addComponent(label, 0, rowIndex);
//		relativeTxtField = new TextField();
//		relativeTxtField.setSizeFull();
//		gridLayout.addComponent(relativeTxtField, 1, rowIndex, 10, rowIndex);
//
//		relativeRadioBtnGrp = new RadioButtonGroup<>();
//		relativeRadioBtnGrp.setItems(RELATIVE);
//		relativeRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		relativeRadioBtnGrp.setSizeUndefined();
//		gridLayout.addComponent(relativeRadioBtnGrp, 11, rowIndex, 12, rowIndex);
//
//		// Row 5
//		rowIndex = 5;
//		label = new Label("User");
//		gridLayout.addComponent(label, 0, rowIndex);
//		userTxtField = new TextField();
//		userTxtField.setSizeFull();
//		gridLayout.addComponent(userTxtField, 1, rowIndex, 10, rowIndex);
//		gridLayout.addComponent(new Label("(* = Wildcard)"), 11, rowIndex);
//
//		// Row 6
//		rowIndex = 6;
//		label = new Label("Job");
//		gridLayout.addComponent(label, 0, rowIndex);
//		jobTxtField = new TextField();
//		jobTxtField.setSizeFull();
//		gridLayout.addComponent(jobTxtField, 1, rowIndex, 10, rowIndex);
//		gridLayout.addComponent(new Label("(* = Wildcard)"), 11, rowIndex);
//
//		// Row 7
//		rowIndex = 7;
//		label = new Label("Job #");
//		gridLayout.addComponent(label, 0, rowIndex);
//		jobNoTxtField = new TextField();
//		jobNoTxtField.setSizeFull();
//		gridLayout.addComponent(jobNoTxtField, 1, rowIndex, 10, rowIndex);
//
//		// Row 8
//		rowIndex = 8;
//		label = new Label("Msg ID");
//		gridLayout.addComponent(label, 0, rowIndex);
//		msgIdTxtField = new TextField();
//		msgIdTxtField.setSizeFull();
//		gridLayout.addComponent(msgIdTxtField, 1, rowIndex, 10, rowIndex);
//		gridLayout.addComponent(new Label("(* = Wildcard)"), 11, rowIndex);
//
//		// Row 9
//		rowIndex = 9;
//		label = new Label("Msg Type");
//		gridLayout.addComponent(label, 0, rowIndex);
//		msgTypeCmbField = new ComboBox();
//		msgTypeCmbField.setSizeFull();
//		msgTypeCmbField.addStyleName(ValoTheme.COMBOBOX_SMALL);
//		gridLayout.addComponent(msgTypeCmbField, 1, rowIndex, 10, rowIndex);
//		gridLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
//		gridLayout.setComponentAlignment(msgTypeCmbField, Alignment.MIDDLE_LEFT);
//
//		replyTypeCmbField = new ComboBox();
//		replyTypeCmbField.addStyleName(ValoTheme.COMBOBOX_SMALL);
//		gridLayout.addComponent(replyTypeCmbField, 11, rowIndex, 12, rowIndex);
//		gridLayout.setComponentAlignment(replyTypeCmbField, Alignment.MIDDLE_LEFT);
//
//		// Row 10
//		rowIndex = 10;
//		label = new Label("Acknowledgement");
//		gridLayout.addComponent(label, 0, rowIndex);
//		ackCmbField = new ComboBox();
//		ackCmbField.setSizeFull();
//		ackCmbField.addStyleName(ValoTheme.COMBOBOX_SMALL);
//
//		gridLayout.addComponent(ackCmbField, 1, rowIndex, 10, rowIndex);
//
//		ackSelectRadioBtnGrp = new RadioButtonGroup<>();
//		ackSelectRadioBtnGrp.setItems("OR", "AND");
//		ackSelectRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		ackSelectRadioBtnGrp.setSizeUndefined();
//		gridLayout.addComponent(ackSelectRadioBtnGrp, 11, rowIndex, 12, rowIndex);
//		gridLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
//		gridLayout.setComponentAlignment(ackCmbField, Alignment.MIDDLE_LEFT);
//		// row 11
//		rowIndex = 11;
//		label = new Label("Msg Severity");
//		gridLayout.addComponent(label, 0, rowIndex);
//		severityTxtField = new TextField();
//		severityTxtField.setHeight(15, Unit.PIXELS);
//		severityTxtField.setSizeFull();
//		gridLayout.addComponent(severityTxtField, 1, rowIndex, 10, rowIndex);
//
//		severitySelectRadioBtnGrp = new RadioButtonGroup<>();
//		severitySelectRadioBtnGrp.setItems("GREATER THAN", "LESS THAN");
//		severitySelectRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		severitySelectRadioBtnGrp.setSizeUndefined();
//		gridLayout.addComponent(severitySelectRadioBtnGrp, 11, rowIndex, 12, rowIndex);
//		gridLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
//		gridLayout.setComponentAlignment(severityTxtField, Alignment.MIDDLE_LEFT);
//
//		// row 12
//		rowIndex = 12;
//		label = new Label("Msg Queue");
//		gridLayout.addComponent(label, 0, rowIndex);
//		msgQueueTxtField = new TextField();
//		msgQueueTxtField.setSizeFull();
//		gridLayout.addComponent(msgQueueTxtField, 1, rowIndex, 10, rowIndex);
//		gridLayout.addComponent(new Label("(* = Wildcard)"), 11, rowIndex);
//
//		// Row 13
//		rowIndex = 13;
//		tstMsgRadioBtnGrp = new RadioButtonGroup<>();
//		tstMsgRadioBtnGrp.setItems(CommonUiUtils.INCLUDE, CommonUiUtils.EXCLUDE, CommonUiUtils.ONLY);
//		tstMsgRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		label = new Label("Select Test Msg");
//		gridLayout.addComponent(label, 0, rowIndex);
//		gridLayout.addComponent(tstMsgRadioBtnGrp, 1, rowIndex, 10, rowIndex);
//		gridLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
//
//		// Row 14
//		rowIndex = 14;
//		notesRadioBtnGrp = new RadioButtonGroup<>();
//		notesRadioBtnGrp.setItems(CommonUiUtils.INCLUDE, CommonUiUtils.EXCLUDE, CommonUiUtils.ONLY);
//		notesRadioBtnGrp.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
//		label = new Label("Select Notes/Acks");
//		gridLayout.addComponent(label, 0, rowIndex);
//		gridLayout.addComponent(notesRadioBtnGrp, 1, rowIndex, 10, rowIndex);
//		gridLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
//
//		// Row 15
//		rowIndex = 15;
//		HorizontalLayout createBtnLayout = createBtnLayout();
//		gridLayout.addComponent(createBtnLayout, 8, rowIndex, 14, rowIndex);
//
//		setInput();
//
//		addPerspectiveRadioBtnListener();
//		addAbsRadioBtnListener();
//		addRelRadioBtnListener();
//		addGlobalSelectCmbListener();
//
//		gridLayout.setSpacing(false);
//		gridLayout.setMargin(true);
//
//		gridLayout.addStyleName("abs-grid-layout");
//
//		mainLayout.addComponent(gridLayout);
//
//		return mainLayout;
//	}
//
//	private void addGlobalSelectCmbListener() {
//		globalSelectField.addValueChangeListener(new ValueChangeListener<GlobalSelectionHeader>() {
//
//			@Override
//			public void valueChange(ValueChangeEvent<GlobalSelectionHeader> event) {
//				selectedGobalValue = event.getValue();
//				// TODO : pre fill the fields with GLobal Values
//
//				if (selectedGobalValue != null) {
//					try {
//						selectionObj.retrieveGlobalSelection(selectedGobalValue.getName());
//
//						perspectiveRadioBtnGrp.setSelectedItem(selectionObj.getFilterView());
//						String filterSystem = selectionObj.getFilterSystem();
//						if (filterSystem != null) {
//							systemField.setSelectedItem(systemGroupMap.get(filterSystem));
//						}
//
//						String filterDateType = selectionObj.getFilterDateType();
//						if (filterDateType.equalsIgnoreCase("ABSOLUTE")) {
//							absRadioBtnGrp.setSelectedItem("ABSOLUTE");
//						} else {
//							relativeRadioBtnGrp.setSelectedItem("RELATIVE");
//						}
//						String filterDate = selectionObj.getFilterDate();
//						if (filterDate != null) {
//							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSS000");
//							LocalDate localDate = LocalDate.parse(filterDate, formatter);
//							absDateField.setValue(localDate);
//						}
//						relativeTxtField.setValue(selectionObj.getFilterRelativeDate());
//						userTxtField.setValue(selectionObj.getFilterJobUser());
//						jobTxtField.setValue(selectionObj.getFilterJobName());
//						jobNoTxtField.setValue(selectionObj.getFilterJobNbr());
//						msgIdTxtField.setValue(selectionObj.getFilterMsgId());
//						msgTypeCmbField.setValue(selectionObj.getFilterMsgType());
//						replyTypeCmbField.setValue(selectionObj.getFilterInqStatus());
//						ackCmbField.setValue(selectionObj.getFilterAckStatus());
//						ackSelectRadioBtnGrp.setSelectedItem(selectionObj.getFilterAckOr());
//						severityTxtField.setValue(selectionObj.getFilterMsgSeverity());
//						severitySelectRadioBtnGrp.setSelectedItem(selectionObj.getFilterSevComparitor());
//						msgQueueTxtField.setValue(selectionObj.getFilterMsgQueue());
//						tstMsgRadioBtnGrp.setSelectedItem(selectionObj.getFilterTestMsg());
//						notesRadioBtnGrp.setSelectedItem(selectionObj.getFilterNoteAckInd());
//
//					} catch (ServiceException e) {
//						Notification.show("Error",
//								"Cannot Save User Selection.  Details : \n"
//										+ CommonUiUtils.processExceptionMsg(e.getLocalizedMessage()),
//								Notification.Type.ERROR_MESSAGE);
//					}
//				}
//
//			}
//		});
//
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void addPerspectiveRadioBtnListener() {
//		perspectiveRadioBtnGrp.addValueChangeListener(new ValueChangeListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -8642812295442783771L;
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//
//				String value = (String) event.getValue();
//				if (value.equals(CommonUiUtils.LOCAL_VIEW)) {
//					systemField.setSelectedItem(null);
//					systemField.setEnabled(false);
//				} else {
//					systemField.setEnabled(true);
//				}
//			}
//		});
//	}
//
//	@SuppressWarnings("serial")
//	private HorizontalLayout createBtnLayout() {
//		HorizontalLayout btnLayout = new HorizontalLayout();
//
//		// btnLayout.addComponent(new Label(" "));
//		//
//		// globalSelectBtn = new Button("Global Select");
//		// btnLayout.addComponent(globalSelectBtn);
//		// globalSelectBtn.setDisableOnClick(true);
//		// globalSelectBtn.addClickListener(new Button.ClickListener() {
//		// @Override
//		// public void buttonClick(Button.ClickEvent event) {
//		// try {
//		//
//		// } finally {
//		// globalSelectBtn.setEnabled(true);
//		// }
//		// }
//		// });
//
//		// saveToGlobeBtn = new Button("Save to Global");
//		// btnLayout.addComponent(saveToGlobeBtn);
//		// saveToGlobeBtn.setDisableOnClick(true);
//		// saveToGlobeBtn.addClickListener(new Button.ClickListener() {
//		// @Override
//		// public void buttonClick(Button.ClickEvent event) {
//		// try {
//		//
//		// } finally {
//		// saveToGlobeBtn.setEnabled(true);
//		// }
//		// }
//		// });
//
//		clearBtn = new Button("Clear");
//		btnLayout.addComponent(clearBtn);
//		clearBtn.setDisableOnClick(true);
//		clearBtn.addClickListener(new Button.ClickListener() {
//			@Override
//			public void buttonClick(Button.ClickEvent event) {
//				try {
//					clearFieldValues();
//				} finally {
//					clearBtn.setEnabled(true);
//				}
//			}
//		});
//
//		saveNRefreshBtn = new Button("Apply");
//		btnLayout.addComponent(saveNRefreshBtn);
//		saveNRefreshBtn.setDisableOnClick(true);
//		saveNRefreshBtn.addClickListener(new Button.ClickListener() {
//			@Override
//			public void buttonClick(Button.ClickEvent event) {
//				try {
//
//					if (selectionObj != null) {
//						selectionObj.setFilterView(perspectiveRadioBtnGrp.getValue().toString());
//						Object systemValue = systemField.getValue();
//						if (systemValue != null) {
//							selectionObj.setFilterSystem(systemValue.toString());
//						}
//
//						if (absRadioBtnGrp.isSelected(ABSOLUTE)) {
//							selectionObj.setFilterDateType(absRadioBtnGrp.getValue().toString());
//						} else if (relativeRadioBtnGrp.isSelected(RELATIVE)) {
//							selectionObj.setFilterDateType(relativeRadioBtnGrp.getValue().toString());
//						}
//
//						LocalDate absDate = absDateField.getValue();
//						if (absDate != null) {
//							// selectionObj
//							// .setFilterDate(absDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss.SSS000")));
//						}
//
//						selectionObj.setFilterRelativeDate(relativeTxtField.getValue());
//						selectionObj.setFilterJobUser(userTxtField.getValue());
//						selectionObj.setFilterJobName(jobTxtField.getValue());
//						selectionObj.setFilterJobNbr(jobNoTxtField.getValue());
//						selectionObj.setFilterMsgId(msgIdTxtField.getValue());
//						Object msgTypeValue = msgTypeCmbField.getValue();
//						if (msgTypeValue != null) {
//							selectionObj.setFilterMsgType(msgTypeValue.toString());
//						}
//
//						if (replyTypeCmbField.getValue() != null) {
//							selectionObj.setFilterInqStatus(replyTypeCmbField.getValue().toString());
//						}
//
//						if (ackCmbField.getValue() != null) {
//							selectionObj.setFilterAckStatus(ackCmbField.getValue().toString());
//						}
//
//						selectionObj.setFilterAckOr(ackSelectRadioBtnGrp.getValue().toString());
//						selectionObj.setFilterMsgSeverity(severityTxtField.getValue());
//
//						selectionObj.setFilterSevComparitor(severitySelectRadioBtnGrp.getValue().toString());
//
//						selectionObj.setFilterMsgQueue(msgQueueTxtField.getValue());
//
//						selectionObj.setFilterTestMsg(tstMsgRadioBtnGrp.getValue().toString());
//
//						selectionObj.setFilterNoteAckInd(notesRadioBtnGrp.getValue().toString());
//						try {
//							selectionObj.saveUserSelection();
//							// passing dummy Context and Perspective
//							msgView.refreshMsgGrid(ConsoleContext.HISTORY_CONTEXT, ConsolePerspective.NETWORK_VIEW,
//									true);
//						} catch (ServiceException e) {
//							Notification.show("Error",
//									"Cannot Save User Selection.  Details : \n"
//											+ CommonUiUtils.processExceptionMsg(e.getLocalizedMessage()),
//									Notification.Type.ERROR_MESSAGE);
//						}
//					}
//
//				} finally {
//					saveNRefreshBtn.setEnabled(true);
//				}
//			}
//		});
//
//		saveNRefreshBtn.addStyleName(ValoTheme.BUTTON_FRIENDLY);
//
//		Button cancelBtn = new Button("Cancel", new CloseButtonListener());
//		btnLayout.addComponent(cancelBtn);
//
//		return btnLayout;
//	}
//
//	@SuppressWarnings("unchecked")
//	protected void clearFieldValues() {
//		perspectiveRadioBtnGrp.setSelectedItem(CommonUiUtils.LOCAL_VIEW);
//		systemField.clear();
//		absDateField.clear();
//		relativeTxtField.clear();
//		userTxtField.clear();
//		jobTxtField.clear();
//		jobNoTxtField.clear();
//		msgIdTxtField.clear();
//		msgTypeCmbField.clear();
//		replyTypeCmbField.clear();
//		ackCmbField.clear();
//		ackSelectRadioBtnGrp.setSelectedItem("AND");
//		severityTxtField.clear();
//		msgQueueTxtField.clear();
//		tstMsgRadioBtnGrp.setSelectedItem(CommonUiUtils.INCLUDE);
//		notesRadioBtnGrp.setSelectedItem(CommonUiUtils.INCLUDE);
//		severitySelectRadioBtnGrp.setSelectedItem("GREATER THAN");
//
//	}
//
//	@SuppressWarnings("unchecked")
//	private void setInput() {
//
//		if (message != null && console != null) {
//			try {
//				selectionObj = console.getSelection();
//
//				List<GlobalSelectionHeader> globalSelectionHeaderList = selectionObj.getGlobalSelectionHeader();
//				globalSelectField.setItems(globalSelectionHeaderList);
//
//				ManageSystemGroups manageSystemGroups = message.getManageSystemGroups();
//				List<SystemGroup> getsystemGroupList = manageSystemGroups.getsystemGroupList();
//				systemField.setItems(getsystemGroupList);
//
//				systemGroupMap = new HashMap<String, SystemGroup>();
//				getsystemGroupList.forEach(e -> systemGroupMap.put(e.getId(), e));
//
//			} catch (ServiceException e) {
//				Notification.show("Error",
//						"Cannot load the System Group Information.  Details : \n"
//								+ CommonUiUtils.processExceptionMsg(e.getLocalizedMessage()),
//						Notification.Type.ERROR_MESSAGE);
//			}
//		}
//
//		perspectiveRadioBtnGrp.setSelectedItem(CommonUiUtils.NETWORK_VIEW);
//		absRadioBtnGrp.setSelectedItem(ABSOLUTE);
//		relativeTxtField.setEnabled(false);
//
//		List<String> msgTypeItems = new ArrayList<String>();
//		msgTypeItems.add("*");
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_COMPLETE);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_COPY);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_DIAGNOSTIC);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_ESCAPE);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_INFO);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_INQUIRY);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_NOTIFY);
//		msgTypeItems.add(CommonUiUtils.MSG_TYPE_REQUEST);
//		// msgTypeItems.add(CommonUiUtils.MSG_TYPE_REPLYDFT);
//		// msgTypeItems.add(CommonUiUtils.MSG_TYPE_REPLYLE);
//		// msgTypeItems.add(CommonUiUtils.MSG_TYPE_REQUEST);
//		msgTypeCmbField.setItems(msgTypeItems);
//
//		List<String> replyTypeItems = new ArrayList<String>();
//		replyTypeItems.add("ANSWERED");
//		replyTypeItems.add("DELETED");
//		replyTypeItems.add("PENDING");
//		replyTypeItems.add("WAITING");
//		replyTypeCmbField.setItems(replyTypeItems);
//
//		List<String> ackTypeItems = new ArrayList<String>();
//		ackTypeItems.add("*");
//		ackTypeItems.add("CLOSED");
//		ackTypeItems.add("OPEN");
//		ackTypeItems.add("PENDING");
//		ackCmbField.setItems(ackTypeItems);
//		ackSelectRadioBtnGrp.setSelectedItem("AND");
//
//		tstMsgRadioBtnGrp.setSelectedItem(CommonUiUtils.INCLUDE);
//		notesRadioBtnGrp.setSelectedItem(CommonUiUtils.INCLUDE);
//		severitySelectRadioBtnGrp.setSelectedItem("GREATER THAN");
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void addAbsRadioBtnListener() {
//		absRadioBtnGrp.addValueChangeListener(new ValueChangeListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -8642812295442783771L;
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//
//				String value = (String) event.getValue();
//
//				if (value != null && value.equals(ABSOLUTE)) {
//					absDateField.setEnabled(true);
//					relativeTxtField.setEnabled(false);
//					relativeRadioBtnGrp.setSelectedItem(null);
//				}
//			}
//		});
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	private void addRelRadioBtnListener() {
//		relativeRadioBtnGrp.addValueChangeListener(new ValueChangeListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -8642812295442783771L;
//
//			@Override
//			public void valueChange(ValueChangeEvent event) {
//
//				String value = (String) event.getValue();
//
//				if (value != null && value.equals(RELATIVE)) {
//					relativeTxtField.setEnabled(true);
//					absDateField.setEnabled(false);
//					absRadioBtnGrp.setSelectedItem(null);
//				}
//			}
//		});
//	}
//
//}
