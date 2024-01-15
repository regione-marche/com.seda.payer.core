package com.seda.tag.core;

import java.io.IOException;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.sql.rowset.CachedRowSet;

public class DgRecord implements TagRenderInterface {

	String sHtml;
	public CachedRowSet cRowSet ;
	public String sTitle;
	public int iBorder =1;
	public String sCss;
	public int iCellPadding;
	public int iCellSpacing;
	public ArrayList<DgColumn> dgList = new ArrayList<DgColumn>();
	public String sRowset;
	public boolean bUsexml =true;

	public String getSRowset() {
		return sRowset;
	}

	public void setSRowset(String rowset) {
		sRowset = rowset;
	}

	public boolean isBUsexml() {
		return bUsexml;
	}

	public void setBUsexml(boolean usexml) {
		bUsexml = usexml;
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

	public void addDgColumn(DgColumn dg)
	{
		dgList.add(dg);	

	}
	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return " "+sCss;
		else
			return"";
	}



	public CachedRowSet getCRowSet() {
		return cRowSet;
	}

	public void setCRowSet(CachedRowSet rowSet) {
		cRowSet = rowSet;
	}

	public String getSTitle() {
		return sTitle;
	}

	public void setSTitle(String title) {
		sTitle = title;
	}


	public String getSCss() {
		return sCss;
	}

	public void setSCss(String css) {
		sCss = css;
	}

	public String render()
	{ sHtml="";

	try{	int iCount=0;
	if(cRowSet!= null)
	{ResultSetMetaData rsMd =cRowSet.getMetaData();

	//Aggiunta delle Colonne dentro la parte If


	sHtml="<table class=\"seda-ui-dgrecord"+hasCss()+"\" border=\""+ iBorder+ "\" cellpadding=\""+ iCellPadding + "\" cellspacing=\""+ iCellSpacing  + "\" >";
	sHtml+="<thead>";
	sHtml+="<tr class=\"seda-ui-dgrecordheader\"><th colspan=\"" + (cRowSet.size()+1) + "\" > <span class=\"seda-ui-dgrecordspanheader\">" +
	sTitle +
	" </span> </th> </tr> </thead>";

	sHtml+=" <tbody>";
	if (dgList!= null)
	{

		for(int k=0;k<dgList.size();k++) {
			if(iCount==0)
				sHtml+="<tr class=\"seda-ui-dgrecordrowpari\">";
			else
				sHtml+="<tr class=\"seda-ui-dgrecordrowdispari\">";
			iCount++;
			DgColumn dg= (DgColumn)dgList.get(k);
			if(dg.iIndex!=0){ // DgColumn con Indice
				if(dg.sLabel!=null&&dg.sLabel.trim()!="") {
					sHtml+="<td  class=\"seda-ui-dgrecordsx\"><span class=\"seda-ui-dgrecordtitle\">"+dg.sLabel+"</span></td>";
				}
				else {
					sHtml+="<td  class=\"seda-ui-dgrecordsx\"><span class=\"seda-ui-dgrecordtitle\">"+rsMd.getColumnName(dg.iIndex)+"</span></td>";
				}
				cRowSet.beforeFirst();
				while(cRowSet.next()) {
					sHtml+="<td class=\"seda-ui-dgrecorddx"+ dg.Css() +"\"><span class=\"seda-ui-dgrecordlabel\">"+ cRowSet.getString(dg.iIndex)+"</span></td>";
				}
			}
			else
				if(dg.sTitle != null && dg.sTitle.trim()!="") // DgColumn con Title
					for(int j=1;j<=rsMd.getColumnCount();j++){
						if(dg.sTitle.equalsIgnoreCase((rsMd.getColumnName(j)))){
							if(dg.sLabel!=""&&dg.sLabel.trim()!="") {
								sHtml+="<td  class=\"seda-ui-dgrecordsx\"><span class=\"seda-ui-dgrecordtitle\">"+dg.sLabel+"</span></td>";
								cRowSet.beforeFirst();
								while(cRowSet.next()) {
									sHtml+="<td class=\"seda-ui-dgrecorddx"+ dg.Css() +"\"><span class=\"seda-ui-dgrecordlabel\">"+ cRowSet.getString(j)+"</span></td>";
								}
							}
							else {
								sHtml+="<td  class=\"seda-ui-dgrecordsx\">span class=\"seda-ui-dgrecordtitle\">"+rsMd.getColumnName(j)+"</span></td>";
								cRowSet.beforeFirst();
								while(cRowSet.next()) {
									sHtml+="<td class=\"seda-ui-dgrecorddx"+ dg.Css() +"\"><span class=\"seda-ui-dgrecordlabel\">"+ cRowSet.getString(j)+"</span></td>";
								}
							}

						}
					}
				else {  // DgColumn con codice all'interno

					DgColumn  dobj = new DgColumn();
					dobj= (DgColumn)dgList.get(k);
					DgColumn dasd = new DgColumn();
					dasd = dobj;
					String sValue ="";



					sHtml+="<td class=\"seda-ui-dgrecordsx\"><span class=\"seda-ui-dgrecordtitle\">"+dasd.sLabel +"</span> </td>";

					cRowSet.beforeFirst();
					while(cRowSet.next()) {
						String content ="";
						if(dasd.sContent!=null)
							content = dasd.sContent;

						while(content.contains("{")){
							int iSelint =	content.indexOf("{");
							int iSelend = content.indexOf("}");
							if(iSelint>=0 && iSelend >=0){
								int iCol = Integer.parseInt( content.substring(iSelint+1, iSelend));
								if(iCol <=rsMd.getColumnCount()&& iCol>0)
									sValue = cRowSet.getString(iCol);
								content =content.replace(content.substring(iSelint, iSelend+1),sValue);
							}
							else 
								break;
						}

						sHtml+="<td class=\"seda-ui-dgrecorddx"+ dg.Css() +"\">"+content+ "</td>";
					}
				}

			sHtml+=" </tr>";


		} //Fine ciclo colonne

	}

	sHtml+=" </tbody> </table>";
	}
	else
	{ sHtml =" </tbody> </table> <div class=\"seda-ui-error \"> Tag non caricato a causa di un errore di comunicazione con il Database   </div>";
	}

	}


	catch (SQLException e) {


		sHtml =" </tbody> </table> <div class=\"seda-ui-error \"> Elemento non caricato correttamente a causa di un errore di comunicazione con il Database " +e.getMessage()+  "</div>";
	}

	return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());

	}
}



