package com.seda.tag.core;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.sql.rowset.CachedRowSet;

import com.seda.tag.library.IfTag;

public class Repeater implements TagRenderInterface {

	public String sContent;
	public CachedRowSet cRowSet;
	public boolean bUseXml;
	public String rsQuery;
	public String sCss;
	public int iStartRow;
	public int iNumRow;
	public ArrayList <IfSeda> IfTags = new ArrayList<IfSeda>();

	public int getIStartRow() {
		return iStartRow;
	}

	public void setIStartRow(int startRow) {
		iStartRow = startRow;
	}

	public int getINumRow() {
		return iNumRow;
	}

	public void setINumRow(int endRow) {
		iNumRow = endRow;
	}

	public void setSContent(String prova) {
		// TODO Auto-generated method stub

	}

	public String getRsQuery() {
		return rsQuery;
	}

	public void setRsQuery(String value) {
		this.rsQuery = value;
	}

	public boolean isBUsexml() {
		return bUseXml;
	}
	public void AddIf(IfSeda IfTag)
	{
		IfTags.add(IfTag);
		
		
	}	
	public void setBUsexml(boolean usexml) {
		bUseXml = usexml;
	}

	public String render() {
		String sHtml = "";
		String sValue = "";

		String sContent1 = "";
		int iRowCount =0;
		boolean bCheckRow = true;
		try {
			if (cRowSet != null)
				if ((cRowSet.getMetaData() != null)) {
					ResultSetMetaData rsMd = cRowSet.getMetaData();
					if (rsMd.getColumnCount() > 0) {
						if (cRowSet.size() > 0) {
							while (cRowSet.next()) {
								
							if(iNumRow!=0)
							{
								if(iRowCount >= iStartRow && iRowCount <(iNumRow+iStartRow))
									bCheckRow= true;
								else
									bCheckRow= false;	
								
								
								iRowCount++;
							}
							if(bCheckRow)
							{
								sContent1 = "";
								sContent1 += sContent;

								if (this.IfTags.size() != 0
										&& this.IfTags.size() > 0)
								{
									ArrayList<IfSeda> ifTags2 = new ArrayList<IfSeda>(); 
								
									ifTags2 = this.IfTags;
									for (int z = 0; z < ifTags2
											.size(); z++)

									{
										IfSeda ifTag = ifTags2
												.get(z);
										String sRight = ifTag.right;
										while (sRight.contains("{")) {
											int iSelint = sRight
													.indexOf("{");
											int iSelend = sRight
													.indexOf("}");
											if (iSelint >= 0
													&& iSelend >= 0) {
												int iCol = Integer
														.parseInt(sRight
																.substring(
																		iSelint + 1,
																		iSelend));

												if (rsMd
														.getColumnCount() > 0)

													if (iCol <= rsMd
															.getColumnCount()
															&& iCol > 0)
														sValue = cRowSet
																.getString(iCol);
												sRight = sRight
														.replace(
																sRight
																		.substring(
																				iSelint,
																				iSelend + 1),
																sValue);
											} else
												break;

										}
										String sLeft = ifTag.left;
										while (sLeft.contains("{")) {
											int iSelint = sLeft
													.indexOf("{");
											int iSelend = sLeft
													.indexOf("}");
											if (iSelint >= 0
													&& iSelend >= 0) {
												int iCol = Integer
														.parseInt(sLeft
																.substring(
																		iSelint + 1,
																		iSelend));

												if (rsMd
														.getColumnCount() > 0)

													if (iCol <= rsMd
															.getColumnCount()
															&& iCol > 0)
														sValue = cRowSet
																.getString(iCol);
												sLeft = sLeft
														.replace(
																sLeft
																		.substring(
																				iSelint,
																				iSelend + 1),
																sValue);
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

											while (sRight2.contains("{")) 
											{
												int iSelint = sRight2.indexOf("{");
												int iSelend = sRight2.indexOf("}");
												if (iSelint >= 0 && iSelend >= 0)
												{
													int iCol = Integer.parseInt(sRight2
																	.substring(iSelint + 1,iSelend));

													if (rsMd.getColumnCount() > 0)

														if (iCol <= rsMd
																.getColumnCount()
																&& iCol > 0)
															sValue = cRowSet
																	.getString(iCol);
													sRight2 = sRight2.replace(
																	sRight2
																			.substring(
																					iSelint,
																					iSelend + 1),
																	sValue);
												} else
													break;

											}

											while (sLeft2.contains("{")) {
												int iSelint = sLeft2
														.indexOf("{");
												int iSelend = sLeft2
														.indexOf("}");
												if (iSelint >= 0
														&& iSelend >= 0) {
													int iCol = Integer
															.parseInt(sLeft2
																	.substring(
																			iSelint + 1,
																			iSelend));

													if (rsMd
															.getColumnCount() > 0)

														if (iCol <= rsMd
																.getColumnCount()
																&& iCol > 0)
															sValue = cRowSet
																	.getString(iCol);
													sLeft2 = sLeft2
															.replace(
																	sLeft2
																			.substring(
																					iSelint,
																					iSelend + 1),
																	sValue);
												} else
													break;

											}

											bsecondCondition = true;

										}

										if (bsecondCondition) {
											if (ifTag.operator
													.equalsIgnoreCase("and")) 
											{
												if (ifTag.Check(sLeft,sRight,ifTag.control)
														&& ifTag.Check(sLeft2,sRight2,ifTag.secondControl)) 
												{
												sContent1=	sContent1.replaceFirst("_IfSedaTag_", ifTag.sThen);

												} else {
													if (ifTag.sElse != null)
														
													sContent1=	sContent1.replaceFirst("_IfSedaTag_", ifTag.sElse);
													
													else
														sContent1=	sContent1.replaceFirst("_IfSedaTag_", "");
													
												}
												
												

											}
											else
											{

												if (ifTag.Check(sLeft,sRight,ifTag.control)
														|| ifTag.Check(sLeft2,sRight2,ifTag.secondControl)) {
													sContent1=	sContent1.replaceFirst("_IfSedaTag_", ifTag.sThen);

												} else {
													if (ifTag.sElse != null)
														sContent1=	sContent1.replaceFirst("_IfSedaTag_", ifTag.sElse);
													else
														sContent1=	sContent1.replaceFirst("_IfSedaTag_", "");
												}
											
												
											}

										

										}

										else {
											if (ifTag.Check(sLeft,
													sRight,
													ifTag.control))
												sContent1=	sContent1.replaceFirst("_IfSedaTag_", ifTag.sThen);

											else {
												if (ifTag.sElse != null)
													sContent1=	sContent1.replaceFirst("_IfSedaTag_", ifTag.sElse);
												else
													sContent1=	sContent1.replaceFirst("_IfSedaTag_", "");
											}
										}

									}
								
								
								}
								
								
								
						
								
								
								while (sContent1.contains("{")) {
									int iSelint = sContent1.indexOf("{");
									int iSelend = sContent1.indexOf("}");
									if (iSelint >= 0 && iSelend >= 0) {
										int iCol = Integer
												.parseInt(sContent1.substring(
														iSelint + 1, iSelend));

										if (rsMd.getColumnCount() > 0)

											if (iCol <= rsMd.getColumnCount()
													&& iCol > 0)
												sValue = cRowSet
														.getString(iCol);
										sContent1 = sContent1.replace(
												sContent1.substring(iSelint,
														iSelend + 1), sValue);
									} else
										break;
									

								}
								sHtml += sContent1;
							}
						}}
					}
				}
		} catch (Exception e) {

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

}
