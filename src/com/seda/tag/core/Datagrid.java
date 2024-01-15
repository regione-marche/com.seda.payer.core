package com.seda.tag.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.jsp.JspWriter;
import javax.sql.rowset.CachedRowSet;

public class Datagrid implements TagRenderInterface {

	public String rsQuery;
	String sHtml;
	public ArrayList<DgColumn> dgList = new ArrayList<DgColumn>();
	public String sContent;
	public CachedRowSet cRowSet;
//	public PageInfo pInfo;
	public PageWrapper pInfo;
	public String viewstate;
	public String sAction;
	public String sCss;
	public String sRowperPage;
	public int iBorder = 1;
	public int iCellPadding;
	public int iCellSpacing;
	public boolean bUsexml = true;
	public String order;

	protected static String DECIMAL = "decimal";
	protected static String TIMESTAMP = "timestamp";
	protected static String DATETIME = "datetime";

	public void setPageInfo(Object pageInfo) {
		if(pageInfo!=null) {
			pInfo=new PageWrapper(pageInfo);
		}
	}
	
	public String getSCss() {
		return sCss;
	}

	public String getSRowperPage() {
		return sRowperPage;
	}

	public void setSRowperPage(String rowperPage) {
		sRowperPage = rowperPage;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String getSAction() {
		return sAction;
	}

	public void setSAction(String action) {
		sAction = action;
	}

	public String getViewstate() {
		return viewstate;
	}

	public void setViewstate(String viewstate) {
		this.viewstate = viewstate;
	}

	public int getIBorder() {
		return iBorder;
	}

	public void setIBorder(int border) {
		iBorder = border;
	}

	public int getICellPadding() {
		return iCellPadding;
	}

	public void setICellPadding(int cellPadding) {
		iCellPadding = cellPadding;
	}

	public int getICellSpacing() {
		return iCellSpacing;
	}

	public void setICellSpacing(int cellSpacing) {
		iCellSpacing = cellSpacing;
	}

	public String getRsQuery() {
		return rsQuery;
	}

	public void setRsQuery(String value) {
		this.rsQuery = value;
	}

	public ArrayList<DgColumn> getDgList() {
		return dgList;
	}

	public void setDgList(ArrayList<DgColumn> dgList) {
		this.dgList = dgList;
	}

	public boolean isBUsexml() {
		return bUsexml;
	}

	public void setBUsexml(boolean usexml) {
		bUsexml = usexml;
	}

	public String getSOrder() {
		return order;
	}

	public void setSOrder(String order) {
		this.order = order;
	}

	public String hasSOrder() {
		return (order == null ? "" : "&amp;order=" + getSOrder());
	}

	public void addDgColumn(DgColumn dg2) {
		dgList.add(dg2);

	}

	public String hasCss() {
		if (sCss != null && sCss.trim() != "")
			return " " + sCss;
		else
			return "";
	}

	public String render() {
		// cRowSet = rsQuery;
		sHtml = "";
		try {
			int iFrow;
			int iLrow;
			int iNumRow;
			int iNumPages;
			int iCurrentPage = 1;
			int iRowperPages = 5;
			String sViewstate = "";
			if (sAction != "") {
				sAction = normalizeHref(sAction);
			}
			if (cRowSet != null)
				if ((cRowSet.getMetaData() != null)) {
					ResultSetMetaData rsMd = cRowSet.getMetaData();

					// Aggiunta delle Colonne dentro la parte If

					sHtml += "<table class=\"seda-ui-datagrid" + hasCss()
							+ "\" border=\"" + iBorder + "\" cellpadding=\""
							+ iCellPadding + "\" cellspacing=\"" + iCellSpacing
							+ "\" >";
					sHtml += "<thead>";

					if (pInfo != null) {
						if (pInfo.getFirstRow() != 0 && pInfo.getLastRow() != 0
								&& pInfo.getPageNumber() != 0) {

							iFrow = pInfo.getFirstRow();
							iLrow = pInfo.getLastRow();
							iNumRow = pInfo.getNumRows();
							iNumPages = pInfo.getNumPages();
							iCurrentPage = pInfo.getPageNumber();
							iRowperPages = pInfo.getRowsPerPage();
						}
					}

					// Gestione dell Header - PageInfo
					if (pInfo != null) {
						if (pInfo.getFirstRow() != 0 && pInfo.getLastRow() != 0
								&& pInfo.getPageNumber() != 0) {

							iFrow = pInfo.getFirstRow();
							iLrow = pInfo.getLastRow();
							iNumRow = pInfo.getNumRows();
							iNumPages = pInfo.getNumPages();
							iCurrentPage = pInfo.getPageNumber();
							iRowperPages = pInfo.getRowsPerPage();

							if (sAction.contains("?"))
								sViewstate = "&amp;viewstate=" + viewstate;
							else
								sViewstate = "?viewstate=" + viewstate;

							int iPageLeft = iCurrentPage + 1;
							int iPageRight = iCurrentPage - 1;
							if (iCurrentPage == 1)
								iPageRight = 1;
							String[] sRowperPages = null;
							String sClasspageInfo = "";
							if (iNumPages == 1)
								sClasspageInfo = " seda-ui-disable";

							if (sRowperPage != null && sRowperPage.trim() != "")
								sRowperPages = sRowperPage.split(";");

							if (iCurrentPage == iNumPages)
								iPageLeft = iNumPages;
							sHtml += "<tr> <th class=\"seda-ui-controlheader\" colspan=\""
									+ (+dgList.size())
									+ "\"> <form action=\""
									+ sAction
									+ "\" class=\"seda-ui-formdg"
									+ sClasspageInfo
									+ "\" onsubmit=\"return false;\"> <span class=\"seda-ui-spandgrow\">Righe   "
									+ iFrow + " - " + iLrow + " su " + iNumRow;
							sHtml += "</span>"
									+ "<a class=\"seda-ui-paginglnk seda-ui-leftleftarrow\" "
									+ "href=\""
									+ sAction
									+ sViewstate
									+ "&amp;pageNumber="
									+ 1
									+ "&amp;rowsPerPage="
									+ iRowperPages
									+ hasSOrder()
									+ " \" ><span class=\"seda-ui-spanfirst\">First</span> </a>"
									+

									"<a class=\"seda-ui-paginglnk  seda-ui-leftarrow\"  "
									+

									"  href=\""
									+ sAction
									+ sViewstate
									+ "&amp;pageNumber="
									+ (iPageRight)
									+ "&amp;rowsPerPage="
									+ iRowperPages
									+ hasSOrder()
									+ " \" ><span class=\"seda-ui-spanprev\">Prev</span> </a>"
									+ ""
									+

									"<span class=\"seda-ui-spandgpage\"> pagina "
									+ iCurrentPage
									+ " di "
									+ iNumPages
									+ " </span>"
									+

									"<a class=\"seda-ui-paginglnk seda-ui-rightarrow\""
									+ "  href=\""
									+ sAction
									+ sViewstate
									+ "&#38;pageNumber="
									+ iPageLeft
									+ "&#38;rowsPerPage="
									+ iRowperPages
									+ hasSOrder()
									+ " \" ><span class=\"seda-ui-spannext\">Next</span> </a>"
									+ "<a class=\"seda-ui-paginglnk  seda-ui-rightrightarrow\" "
									+

									"   href=\""
									+ sAction
									+ sViewstate
									+ "&amp;pageNumber="
									+ iNumPages
									+ "&amp;rowsPerPage="
									+ iRowperPages
									+ hasSOrder()
									+ " \" ><span class=\"seda-ui-spanlast\">Last</span> </a>"
									+ ""
									+ "<label id=\"sedauilabeldgrow\" class=\"seda-ui-labeldgrow\" for=\"sedauiddlrow\"> Righe per Pagina: </label>";
							sHtml += "<select id=\"sedauiddlrow\" class=\"seda-ui-ddlrow\" name=\"sedauiddlrow\"  size=\"1\">";

							if (sRowperPages != null) {
								if (sRowperPages.length > 0)
									for (int i = 0; i < sRowperPages.length; i++) {
										sHtml += "<option value=\""
												+ sAction
												+ sViewstate
												+ "&amp;pageNumber=1&amp;rowsPerPage="
												+ sRowperPages[i] + hasSOrder()
												+ "\"";

										if (Integer.valueOf(sRowperPages[i]).intValue() == iRowperPages) {
											sHtml += " selected=\"selected\"";
										}
										sHtml += " >" + sRowperPages[i]
												+ "</option>";

									}
							} else
								for (int i = 1; i <= iNumRow; i++) {
									sHtml += "<option value=\""
											+ sAction
											+ sViewstate
											+ "&amp;pageNumber=1&&amp;rowsPerPage="
											+ i + hasSOrder() + "\"";
									if (i == iRowperPages) {
										sHtml += " selected=\"selected\"";
									}
									sHtml += " >" + i + "</option>";
								}
							sHtml += "</select><input name=\"sedauidgbuttonrow\" id=\"sedauidgbuttonrow\" type=\"button\" class=\"seda-ui-pagingbtn seda-ui-dgbuttonrow\" value=\"Vai\" onclick=\"location = this.form.sedauiddlrow.value\" /> ";
							sHtml += "<label id=\"sedauilabeldgpage\" class=\"seda-ui-labeldgpage\" for=\"sedauiddlpage\"> Pagina: </label>";
							sHtml += "<select id=\"sedauiddlpage\" name=\"sedauiddlpage\" class=\"seda-ui-ddlpage\"  size=\"1\">";
							for (int i = 1; i <= iNumPages; i++) {
								sHtml += "<option value=\"" + sAction
										+ sViewstate + "&amp;pageNumber=" + i
										+ "&amp;rowsPerPage=" + iRowperPages
										+ hasSOrder() + "\"";
								if (i == iCurrentPage) {
									sHtml += " selected=\"selected\"";
								}
								sHtml += " >" + i + "</option>";
							}
							sHtml += "</select><input name=\"sedauidgbuttonpage\" id=\"sedauidgbuttonpage\" type=\"button\" class=\"seda-ui-pagingbtn seda-ui-dgbuttonpage\" value=\"Vai\" onclick=\"location = this.form.sedauiddlpage.value\" /> </form>  "
									+ "</th> </tr> ";
						}

					}
					sHtml += "<tr> ";
					if (dgList != null) {
						boolean bFound = false;
						for (int k = 0; k < dgList.size(); k++) {
							bFound = false;
							DgColumn dg = (DgColumn) dgList.get(k);

							sHtml += " <th class=\"seda-ui-datagridheadercell"
									+ dg.Css() + "\" > ";
							// Titolo e Funzioni di Ordinamento delle colonne
							if (dg.sAsc != null && dg.sAsc.trim() != "")
								sHtml += "<a class=\"seda-ui-btnup\"  id=\"dgbtnup"
										+ k
										+ "\" name =\"dgbtnup"
										+ k
										+ " \"  href=\""
										+ sAction
										+ sViewstate
										+ "&amp;order="
										+ dg.sAsc
										+ "&amp;pageNumber=1" // + iCurrentPage
										+ "&amp;rowsPerPage="
										+ iRowperPages
										+ "\" ><span class=\"seda-ui-spanasc\">Asc</span></a>";
							if (dg.sLabel != null && dg.sLabel.trim() != "")
								sHtml += dg.sLabel + "&nbsp;";
							else if (dg.iIndex != 0)
								sHtml += rsMd.getColumnLabel(dg.iIndex)
										+ "&nbsp;";
							else if (dg.sTitle.trim() != null
									&& dg.sTitle != null) {
								for (int j = 1; j <= rsMd.getColumnCount(); j++) {
									if (dg.sTitle.equalsIgnoreCase((rsMd.getColumnName(j)))) {
										sHtml += rsMd.getColumnLabel(j)
												+ "&nbsp;";
									}
								}
							}
							if (dg.sDesc != null && dg.sDesc.trim() != "")
								sHtml += "<a class=\"seda-ui-btndown\"  id=\"dgbtndwb"
										+ k
										+ "\" name =\"dgbtndwn"
										+ k
										+ " \"  href=\""
										+ sAction
										+ sViewstate
										+ "&amp;order="
										+ dg.sDesc
										+ "&amp;pageNumber=1" // + iCurrentPage
										+ "&amp;rowsPerPage="
										+ iRowperPages
										+ "\" ><span class=\"seda-ui-spandesc\">Desc</span></a>";
							sHtml += "</th> ";
							bFound = true;

						}
						if (!bFound) {
						}
					} else {
					}

					sHtml += "</tr> ";

					sHtml += "</thead> <tbody> ";
					if (rsMd.getColumnCount() > 0) {
						int counter = 1;
						int rowSetSize = cRowSet.size();
						boolean bLastRow = false;
						if (cRowSet.size() > 0) {
							while (cRowSet.next()) {
								
								if (counter == rowSetSize)
									bLastRow = true;
								
								if (cRowSet.getRow() % 2 == 0)
									sHtml += " <tr class=\"seda-ui-datagridrowpari " + (bLastRow ? "seda-ui-datagridrowfooter" : "") + "\" > ";
								else
									sHtml += "<tr class=\"seda-ui-datagridrowdispari " + (bLastRow ? "seda-ui-datagridrowfooter" : "") + "\" > ";
								for (int i = 0; i <= dgList.size() - 1; i++)

								{
									DgColumn dobj = new DgColumn();
									dobj = (DgColumn) dgList.get(i);
									DgColumn dasd = new DgColumn();
									dasd = dobj;
									boolean bFind = false;
									if (dasd.iIndex != 0) // Gestione DgColumn
									// con Index
									{
										if (cRowSet.getString(dasd.iIndex) != null) {
											String sColName = (rsMd.getColumnTypeName(dasd.iIndex)).toLowerCase();
											String value;

											if (dasd.format != null) {
												if (sColName.equals(DECIMAL))
													value = formatDecimalNumber((BigDecimal) cRowSet.getObject(dasd.iIndex), dasd.format);
												else if (sColName.equals(TIMESTAMP)
														|| sColName.equals(DATETIME))
													value = formatDate((Timestamp) cRowSet.getObject(dasd.iIndex), dasd.format);
												else
													value = cRowSet.getString(dasd.iIndex);
											} else
												value = cRowSet.getString(dasd.iIndex);

											if (value.length() > 0)
												sHtml += " <td class=\"seda-ui-datagridcell " 
														+ (bLastRow ? "seda-ui-datagridcellfooter " : " ") 
														+ " seda-ui-datatype-"
														+ sColName
														+ dasd.Css()
														+ "\">"
														+ value
														+ "</td>";
											else
												sHtml += " <td class=\"seda-ui-datagridcell "
														+ (bLastRow ? "seda-ui-datagridcellfooter " : " ") 
														+ " seda-ui-datatype-"
														+ sColName
														+ dasd.Css()
														+ "\">" + "&nbsp;</td>";

										}
									} else if (dasd.iIndex == 0
											&& dasd.sTitle != null
											&& dasd.sTitle.trim() != "") // Gestione
									// DgColumn
									// con
									// Title
									{
										for (int j = 1; j <= rsMd.getColumnCount(); j++) {
											if (dasd.sTitle.equalsIgnoreCase((rsMd.getColumnName(j)))) {
												String sColName = rsMd.getColumnClassName(j);

												if (cRowSet.getString(j).length() > 0)
													sHtml += " <td class=\"seda-ui-datagridcell "
															+ (bLastRow ? "seda-ui-datagridcellfooter " : " ") 
															+ " seda-ui-datatype-"
															+ sColName
															+ dasd.Css()
															+ "\">"
															+ cRowSet.getString(j)
															+ "</td>";
												else
													sHtml += " <td class=\"seda-ui-datagridcell "
															+ (bLastRow ? "seda-ui-datagridcellfooter " : " ") 
															+ " seda-ui-datatype-"
															+ sColName
															+ dasd.Css()
															+ "\">"
															+ "&nbsp;"
															+ "</td>";
												bFind = true;
											}
										}
										if (!bFind)
											sHtml += " <td class=\"seda-ui-datagridcell"
													+ dasd.Css()
													+ (bLastRow ? " seda-ui-datagridcellfooter " : " ")
													+ " \">&nbsp;</td>";
									} else { // Gestione DgColumn con codice
										// all'interno
										String sValue = "";
										String content = "";

										if (dasd.sContent != null)
											content = dasd.sContent;
										if (dasd.IfTags.size() != 0
												&& dasd.IfTags.size() > 0)
											for (int z = 0; z < dasd.IfTags.size(); z++)

											{
												IfSeda ifTag = dasd.IfTags.get(z);
												String sRight = ifTag.right;
												while (sRight.contains("{")) {
													int iSelint = sRight.indexOf("{");
													int iSelend = sRight.indexOf("}");
													if (iSelint >= 0
															&& iSelend >= 0) {
														int iCol = Integer.parseInt(sRight.substring(iSelint + 1, iSelend));

														if (rsMd.getColumnCount() > 0)

															if (iCol <= rsMd.getColumnCount()
																	&& iCol > 0)
																sValue = cRowSet.getString(iCol);
														sRight = sRight.replace(sRight.substring(iSelint, iSelend + 1), sValue);
													} else
														break;

												}
												String sLeft = ifTag.left;
												while (sLeft.contains("{")) {
													int iSelint = sLeft.indexOf("{");
													int iSelend = sLeft.indexOf("}");
													if (iSelint >= 0
															&& iSelend >= 0) {
														int iCol = Integer.parseInt(sLeft.substring(iSelint + 1, iSelend));

														if (rsMd.getColumnCount() > 0)

															if (iCol <= rsMd.getColumnCount()
																	&& iCol > 0)
																sValue = cRowSet.getString(iCol);
														sLeft = sLeft.replace(sLeft.substring(iSelint, iSelend + 1), sValue);
													} else
														break;

												}
												boolean bsecondCondition = false;
												String sLeft2 = null;
												String sRight2 = null;
												if (ifTag.secondLeft != null
														&& ifTag.secondRight != null
														&& ifTag.secondControl != null) {
													sLeft2 = ifTag.secondLeft;
													sRight2 = ifTag.secondRight;

													while (sRight2.contains("{")) {
														int iSelint = sRight2.indexOf("{");
														int iSelend = sRight2.indexOf("}");
														if (iSelint >= 0
																&& iSelend >= 0) {
															int iCol = Integer.parseInt(sRight2.substring(iSelint + 1, iSelend));

															if (rsMd.getColumnCount() > 0)

																if (iCol <= rsMd.getColumnCount()
																		&& iCol > 0)
																	sValue = cRowSet.getString(iCol);
															sRight2 = sRight2.replace(sRight2.substring(iSelint, iSelend + 1), sValue);
														} else
															break;

													}

													while (sLeft2.contains("{")) {
														int iSelint = sLeft2.indexOf("{");
														int iSelend = sLeft2.indexOf("}");
														if (iSelint >= 0
																&& iSelend >= 0) {
															int iCol = Integer.parseInt(sLeft2.substring(iSelint + 1, iSelend));

															if (rsMd.getColumnCount() > 0)

																if (iCol <= rsMd.getColumnCount()
																		&& iCol > 0)
																	sValue = cRowSet.getString(iCol);
															sLeft2 = sLeft2.replace(sLeft2.substring(iSelint, iSelend + 1), sValue);
														} else
															break;

													}
													bsecondCondition = true;
												}

												if (bsecondCondition) {
													if (ifTag.operator.equalsIgnoreCase("and")) {
														if (ifTag.Check(sLeft, sRight, ifTag.control)
																&& ifTag.Check(sLeft2, sRight2, ifTag.secondControl)) {
															content += ifTag.sThen;
														} else {
															if (ifTag.sElse != null)
																content += ifTag.sElse;
														}

													} else {
														if (ifTag.Check(sLeft, sRight, ifTag.control) || ifTag.Check(sLeft2, sRight2, ifTag.secondControl)) {
															content += ifTag.sThen;
														} else {
															if (ifTag.sElse != null)
																content += ifTag.sElse;
														}
													}

												}

												else {
													if (ifTag.Check(sLeft, sRight, ifTag.control))
														content += ifTag.sThen;

													else {
														if (ifTag.sElse != null)
															content += ifTag.sElse;
													}
												}

											}
										while (content.contains("{")) {
											int iSelint = content.indexOf("{");
											int iSelend = content.indexOf("}");
											if (iSelint >= 0 && iSelend >= 0) {
												int iCol = Integer.parseInt(content.substring(iSelint + 1, iSelend));

												if (rsMd.getColumnCount() > 0)

													if (iCol <= rsMd.getColumnCount()
															&& iCol > 0)
														sValue = cRowSet.getString(iCol);
												content = content.replace(content.substring(iSelint, iSelend + 1), sValue);
											} else
												break;

										}
										if (content.trim() == "")
											content = "&nbsp;";
										sHtml += " <td class=\"seda-ui-datagridcell"
												+ dasd.Css()
												+ (bLastRow ? " seda-ui-datagridcellfooter " : " ") 
												+ "\"> "
												+ content
												+ " </td> ";
									}

								}
								sHtml += " </tr> ";
								counter++;
							}

						}

					}
					sHtml += " </tbody> </table> ";

				} else {
					sHtml = "<div class=\"seda-ui-error \"> Tag non caricato a causa di un errore di comunicazione con il Database </div>";
				}
			else {
				sHtml = "<div class=\"seda-ui-error \"> Tag non caricato a causa di un errore di comunicazione con il Database </div>";
			}
		}

		catch (Exception e) {
			sHtml += " </tr> ";
			sHtml += " </table>";
			int asd = 0;
			if (cRowSet != null) {
				try {
					ResultSetMetaData rsMderror = cRowSet.getMetaData();

					asd = rsMderror.getColumnCount();
				} catch (SQLException e1) {
					sHtml += "<div class=\"seda-ui-error \"> Tag non caricato a causa di un errore di comunicazione con il Database "
							+ e.getMessage()
							+ " size del Rowset :"
							+ cRowSet.size()
							+ "Column Count del getMetaData è nullo </div>";

				}
			}

			sHtml += "<div class=\"seda-ui-error \"> Tag non caricato a causa di un errore di comunicazione con il Database "
					+ e.getMessage()
					+ " size del Rowset :"
					+ cRowSet.size()
					+ "Column Count =" + asd + "    </div>";

		}

		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {

		writer.print(render());
	}

	protected String formatDecimalNumber(BigDecimal bdValue, String format) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		// DecimalFormat dcFormat = new DecimalFormat("#0.00", symbols);
		DecimalFormat dcFormat = new DecimalFormat(format, symbols);
		return dcFormat.format(bdValue);
	}

	protected String formatDate(Timestamp value, String format) {
		Timestamp date;
		if (value != null) {
			date = value;
		} else {
			date = new Timestamp(System.currentTimeMillis());
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer day = calendar.get(Calendar.DAY_OF_MONTH);
		Integer month = calendar.get(Calendar.MONTH) + 1;
		Integer year = calendar.get(Calendar.YEAR);
		// Gestione data di default 1000-01-01
		if (day.equals(1) && month.equals(1) && year.equals(1000)) {
			return "";
		}

		if (format == null) {
			format = "yyyy-MM-dd";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String formattedDate = null;
		try {
			formattedDate = dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return formattedDate;
		}
		return formattedDate;

	}

	public String normalizeHref(String sHref) {

		sHref = sHref.replaceAll("&amp;", "&");
		sHref = sHref.replaceAll("&", "&amp;");

		return sHref;

	}



}