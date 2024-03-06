package com.seda.tag.core;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.sql.rowset.CachedRowSet;

public class DropDownList implements TagRenderInterface {
	String sHtml;
	String sSep;
	String sName;
	public ArrayList<DdlOption> dgList = new ArrayList<DdlOption>();
	String sLabel;
	String CachedRowSet;
	CachedRowSet cRowSet;
	String sCss;
	String sCssLabel;
	String sSelectedValue;

	String sOnChange;
	boolean bDisable;
	boolean bReadonly;
	boolean bMultiple;
	int iSize;
	String sSelectedText;
	boolean bUsexml = true;
	private String sValidator;
	String sTabIndex;
	public boolean showRequired;
	private String sMessage;
			

	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	public void setShowRequired(boolean showRequired) {
		this.showRequired = showRequired;
	}

	public String getSTabIndex() {
		return sTabIndex;
	}

	public void setSTabIndex(String tabIndex) {
		sTabIndex = tabIndex;
	}

	public boolean hasTabIndex() {
		return (sTabIndex != null && sTabIndex.trim().length() > 0);
	}

	public String getSOnChange() {
		return sOnChange;
	}

	public void setSOnChange(String onChange) {
		sOnChange = onChange;
	}

	public String getSValidator() {
		return sValidator;
	}

	public void setSValidator(String validator) {
		sValidator = validator;
	}

	public void addOption(DdlOption ddo) {
		dgList.add(ddo);

	}

	public boolean isBUsexml() {
		return bUsexml;
	}

	public void setBUsexml(boolean usexml) {
		bUsexml = usexml;
	}

	public String getSSelectedValue() {
		return sSelectedValue;
	}

	public void setSSelectedValue(String selectedValue) {
		sSelectedValue = selectedValue;
	}

	public String getSSelectedText() {
		return sSelectedText;
	}

	public void setSSelectedText(String value) {
		sSelectedText = value;
	}

	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String getSSep() {
		return sSep;
	}

	public void setSSep(String sep) {
		sSep = sep;
	}

	public String getSName() {
		return sName;
	}

	public void setSName(String name) {
		sName = name;
	}

	// public String getSValue() {
	// return sValue;
	// }
	// public void setSValue(String value) {
	// sValue = value;
	// }
	// public String getSText() {
	// return sText;
	// }
	// public void setSText(String text) {
	// sText = text;
	// }
	public String getSLabel() {
		return sLabel;
	}

	public void setSLabel(String label) {
		sLabel = label;
	}

	public boolean isBDisable() {
		return bDisable;
	}

	public void setBDisable(boolean disable) {
		bDisable = disable;
	}

	public boolean isBReadonly() {
		return bReadonly;
	}

	public void setBReadonly(boolean bReadonly) {
		this.bReadonly = bReadonly;
	}

	public boolean isBMultiple() {
		return bMultiple;
	}

	public void setBMultiple(boolean multiple) {
		bMultiple = multiple;
	}

	public int getISize() {
		return iSize;
	}

	public void setISize(int size) {
		iSize = size;
	}

	public String getSCssLabel() {
		return sCssLabel;
	}

	public void setSCssLabel(String cssLabel) {
		sCssLabel = cssLabel;
	}

	public String getCachedRowSet() {
		return CachedRowSet;
	}

	public void setCachedRowSet(String cachedRowSet) {
		CachedRowSet = cachedRowSet;
	}

	public CachedRowSet getCRowSet() {
		return cRowSet;
	}

	public void setCRowSet(CachedRowSet rowSet) {
		cRowSet = rowSet;
	}

	public boolean hasIsize() {
		return (iSize > 0);
	}

	public boolean hasCss() {
		return (sCss != null && sCss.trim() != "");
	}

	public boolean hasCssLabel() {
		return (sCssLabel != null && sCssLabel.trim() != "");
	}

	public boolean hasOnChange() {
		return (sOnChange != null && sOnChange.trim() != "");

	}

	public String render() {
		boolean sel = false;
		try {
			sHtml = " <label class=\"seda-ui-label";
			if (hasCssLabel())
				sHtml += " " + sCssLabel + "\" ";
			else
				sHtml += "\" ";
			sHtml += " for=\"" + sName + "\" >" + sLabel;
			if (showRequired && HtmlUtil.isRequired(sValidator)) {
				this.sHtml += HtmlUtil.getRequired();
			}
			sHtml += "</label> ";
			sHtml += " <select class=\"seda-ui-ddl";
			if (hasCss())
				sHtml += " " + sCss + "";
			else
				sHtml += "";
			sHtml += "\" id=\"" + sName + "\"  name=\"" + sName + "\" ";
			if (isBMultiple())
				sHtml += "multiple=\"multiple\" ";
			if (hasIsize())
				sHtml += "size=\"" + iSize + " \" ";
			if (isBDisable())
				sHtml += "disabled=\"disabled\"";
			if (isBReadonly())
				sHtml += "disabled=\"disabled\"";
			if (hasTabIndex()) {
				sHtml += " tabindex=\"" + sTabIndex + "\" ";
			}
			String inputTag = "<input name=\"" + sName
			+ "\" type=\"hidden\" value=\"%s\"/>";
			if (hasOnChange())
				sHtml += " onchange=\"" + sOnChange + "\"";
			sHtml += ">";

			for (int i = 0; i < dgList.size(); i++) {
				DdlOption ddlo = new DdlOption();
				ddlo = dgList.get(i);

				if (cRowSet != null) {
					if ((ddlo.getSValue().contains("{") && ddlo.getSValue()
							.contains("}"))
							|| (ddlo.getSText().contains("{") && ddlo
									.getSText().contains("}"))) {
						ResultSetMetaData rsMd = cRowSet.getMetaData();

						while (cRowSet.next()) {
							String sValue = "";
							String content = ddlo.getSValue();

							if (content.contains("{")) {
								while (content.contains("{")) {
									int iSelint = content.indexOf("{");
									int iSelend = content.indexOf("}");
									if (iSelint >= 0 && iSelend >= 0) {
										int iCol = Integer
										.parseInt(content.substring(
												iSelint + 1, iSelend));

										if (rsMd.getColumnCount() > 0)

											if (iCol <= rsMd.getColumnCount()
													&& iCol > 0)
												sValue = cRowSet
												.getString(iCol);
										content = content.replace(
												content.substring(iSelint,
														iSelend + 1), sValue);
									} else
										break;

								}
							}

							sHtml += " <option value=\"" + content + "\"";
							// Controlli per l' attributo Select
							sValue = "";
							String sContenttxt = ddlo.getSText();

							if (sContenttxt.contains("{")) {
								while (sContenttxt.contains("{")) {
									int iSelint = sContenttxt.indexOf("{");
									int iSelend = sContenttxt.indexOf("}");
									if (iSelint >= 0 && iSelend >= 0) {
										int iCol = Integer
										.parseInt(sContenttxt
												.substring(iSelint + 1,
														iSelend));

										if (rsMd.getColumnCount() > 0)

											if (iCol <= rsMd.getColumnCount()
													&& iCol > 0)
												sValue = cRowSet
												.getString(iCol);
										sContenttxt = sContenttxt.replace(
												sContenttxt.substring(iSelint,
														iSelend + 1), sValue);
									} else
										break;

								}

							}
							if (sSelectedValue != null
									&& sSelectedValue.trim() != ""
										&& sSelectedValue.equals(content)) {
								sHtml += " selected=\"selected\" ";
								sel = true;
								inputTag = String.format(inputTag, content);
							} else if (sSelectedText != null
									&& sSelectedText.trim() != ""
										&& sSelectedText.equals(sContenttxt)) {
								sHtml += " selected=\"selected\" ";
								sel = true;
								inputTag = String.format(inputTag, sContenttxt);
							}

							sHtml += ">" + sContenttxt + "</option> ";
						}

					} else {
						sHtml += " <option value=\"" + ddlo.getSValue() + "\"";
						// Controlli per l' attributo Select

						if (sSelectedValue != null
								&& sSelectedValue.trim() != ""
									&& sSelectedValue.equals(ddlo.getSValue())) {
							sHtml += " selected=\"selected\" ";
							sel = true;
							inputTag = String
							.format(inputTag, ddlo.getSValue());
						} else if (sSelectedText != null
								&& sSelectedText.trim() != ""
									&& sSelectedText.equals(ddlo.getSText())) {
							sHtml += " selected=\"selected\" ";
							sel = true;
							inputTag = String.format(inputTag, ddlo.getSText());
						}

						sHtml += ">" + ddlo.getSText() + "</option> ";
					}
				} else {
					if (!((ddlo.getSValue().contains("{") && ddlo.getSValue()
							.contains("}")) || (ddlo.getSText().contains("{") && ddlo
									.getSText().contains("}")))) {
						sHtml += "<option value=\"" + ddlo.getSValue() + "\"";
						// Controlli per l' attributo Select

						if (sSelectedValue != null
								&& sSelectedValue.trim() != ""
									&& sSelectedValue.equals(ddlo.getSValue())) {
							sHtml += " selected=\"selected\" ";
							sel = true;
							inputTag = String
							.format(inputTag, ddlo.getSValue());
						}

						else if (sSelectedText != null
								&& sSelectedText.trim() != ""
									&& sSelectedText.equals(ddlo.getSText())) {
							sHtml += " selected=\"selected\" ";
							sel = true;
							inputTag = String.format(inputTag, ddlo.getSText());
						}

						sHtml += ">" + ddlo.getSText() + "</option> ";

					} else {
					}
				}
			}
			sHtml += " </select> ";
			if (bReadonly)
				sHtml += inputTag;
		}

		catch (Exception ex) {
			sHtml = " <div class=\"seda-ui-error\" >il tag DropDownList non è ben definito</div> ";
		}

		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.println(render());
	}

}
