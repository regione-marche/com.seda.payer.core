/**
 * 
 */
package com.seda.tag.core;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

import com.seda.data.page.PageMetaData;
/**
 * @author f.ricci
 *
 */
public class Paging implements TagRenderInterface {

	public String action;
	public String rowPerPage;	
	public boolean showfirst=false;	
	public boolean showlast=false;
	public boolean showrowindex=false;
	public boolean showrows=false;	
	public boolean showpagesize=false;
	public String modifier = "";
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public PageMetaData pageMetaData;
	
	public String getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(String rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public PageMetaData getPageMetaData() {
		return pageMetaData;
	}
	public void setPageMetaData(PageMetaData pageMetaData) {
		this.pageMetaData = pageMetaData;
	}
	public boolean isShowfirst() {
		return showfirst;
	}
	public void setShowfirst(boolean first) {
		this.showfirst = first;
	}
	public boolean isShowlast() {
		return showlast;
	}
	public void setShowlast(boolean last) {
		this.showlast = last;
	}

	public boolean isShowrowindex() {
		return showrowindex;
	}
	public void setShowrowindex(boolean rowindex) {
		this.showrowindex = rowindex;
	}
	
	public boolean isShowpagesize() {
		return showpagesize;
	}
	public void setShowpagesize(boolean pagesize) {
		this.showpagesize = pagesize;
	}
	public boolean isShowrows() {
		return showrows;
	}
	public void setShowrows(boolean showrows) {
		this.showrows = showrows;
	}
	public String render() {
		String sHtml="";
		if (modifier==null)modifier= "";
		int iFrow;
		int iLrow;
		int iNumRow;
		int iNumPages;
		int iCurrentPage = 1;
		int iRowperPages;
		if(action!="")
		{
		action= normalizeHref(action);
		}
		// Gestione dell Header - PageInfo
		if (pageMetaData != null) {
			if (pageMetaData.getFirstRow() != 0 && pageMetaData.getLastRow() != 0
					&& pageMetaData.getPageNumber() != 0) {

				iFrow = pageMetaData.getFirstRow();
				iLrow = pageMetaData.getLastRow();
				iNumRow = pageMetaData.getTotalRows();
				iNumPages = pageMetaData.getTotalPages();
				iCurrentPage = pageMetaData.getPageNumber();
				iRowperPages = pageMetaData.getPageSize();

				int iPageLeft = iCurrentPage + 1;
				int iPageRight = iCurrentPage - 1;
				if (iCurrentPage == 1)
					iPageRight = 1;
				String[] sRowperPages = null;
				String sClasspageInfo = "";
				if (iNumPages == 1)
					sClasspageInfo = " seda-ui-disable";

				if (rowPerPage != null && rowPerPage.trim() != "")
					sRowperPages = rowPerPage.split(";");

				if (iCurrentPage == iNumPages)
					iPageLeft = iNumPages;
				sHtml += "<form action=\""
					+ action
					+ "\" class=\"seda-ui-formdg"
					+ sClasspageInfo
					+ "\" onsubmit=\"return false;\">";
				if (isShowrowindex()) {
					sHtml += "<span class=\"seda-ui-spandgrow\">Righe   "
						+ iFrow + " - " + iLrow + " su " + iNumRow + "</span>";					
				}
				if (isShowfirst()) {
					sHtml +=  "<a class=\"seda-ui-paginglnk seda-ui-leftleftarrow\" "
						+ "href=\""
						+ action
						+ "&#38;" + modifier + "pageNumber="
						+ 1
						+ "&#38;" + modifier + "pageSize="
						+ iRowperPages
						+ "\" ><span class=\"seda-ui-spanfirst\">Prima</span> </a>";

				}
				sHtml +="<a class=\"seda-ui-paginglnk  seda-ui-leftarrow\"  "
					+ "  href=\""
					+ action
					+ "&#38;" + modifier + "pageNumber="
					+ (iPageRight)
					+ "&#38;" + modifier + "pageSize="
					+ iRowperPages
					+ "\" ><span class=\"seda-ui-spanprev\">Indietro</span> </a>"
					+ ""
					
					+ "<span class=\"seda-ui-spandgpage\"> pagina "
					+ iCurrentPage
					+ " di "
					+ iNumPages
					+ " </span>"
					
					+ "<a class=\"seda-ui-paginglnk seda-ui-rightarrow\""
					+ "  href=\""
					+ action
					+ "&#38;"+ modifier +"pageNumber="
					+ iPageLeft
					+ "&#38;" + modifier + "pageSize="
					+ iRowperPages
					+ "\" ><span class=\"seda-ui-spannext\">Avanti</span> </a>"
					+ "";

				if (isShowlast()) {
					sHtml += " <a class=\"seda-ui-paginglnk  seda-ui-rightrightarrow\"  href=\""
						+ action
						+ "&#38;"+ modifier +"pageNumber="
						+ iNumPages
						+ "&#38;" + modifier + "pageSize="
						+ iRowperPages
						+ "\" ><span class=\"seda-ui-spanlast\">Ultima</span> </a>"
						+ "";
				}

				if (isShowpagesize()) {
					sHtml += "<label id=\"sedauilabeldgrow\" class=\"seda-ui-labeldgrow\" for=\"sedauiddlrow\">Righe per Pagina:</label>";
					sHtml += "<select id=\"sedauiddlrow\" class=\"seda-ui-ddlrow\" name=\"sedauiddlrow\" size=\"1\">";

					if (sRowperPages != null) {
						if (sRowperPages.length > 0) {
							for (int i = 0; i < sRowperPages.length; i++) {
								sHtml += "<option value=\""
									+ action
									+ "&#38;"+ modifier +"pageNumber=1&#38;" + modifier + "pageSize="
									+ sRowperPages[i] + "\"";

								if (Integer.valueOf(sRowperPages[i])
										.intValue() == iRowperPages) {
									sHtml += " selected=\"selected\"";
								}
								sHtml += " >" + sRowperPages[i]
								                             + "</option>";

							}
						}
					} else {
						for (int i = 1; i <= iNumRow; i++) {
							sHtml += "<option value=\""
								+ action
								+ "&#38;"+ modifier +"pageNumber=1&#38;" + modifier + "pageSize="
								+ i + "\"";
							if (i == iRowperPages) {
								sHtml += " selected=\"selected\"";
							}
							sHtml += " >" + i + "</option>";
						}					
					}

					sHtml += "</select><input name=\"sedauidgbuttonrow\" id=\"sedauidgbuttonrow\" type=\"button\" class=\"seda-ui-pagingbtn seda-ui-dgbuttonrow\" value=\"Vai\" onclick=\"location = this.form.sedauiddlrow.value\" /> ";
					sHtml += "<label id=\"sedauilabeldgpage\" class=\"seda-ui-labeldgpage\" for=\"sedauiddlpage\"> Pagina: </label>";
					sHtml += "<select id=\"sedauiddlpage\" name=\"sedauiddlpage\" class=\"seda-ui-ddlpage\"  size=\"1\">";
					for (int i = 1; i <= iNumPages; i++) {
						sHtml += "<option value=\"" + action
						+ "&#38;"+ modifier +"pageNumber=" + i
						+ "&#38;" + modifier + "pageSize=" + iRowperPages
						+ "\"";
						if (i == iCurrentPage) {
							sHtml += " selected=\"selected\"";
						}
						sHtml += " >" + i + "</option>";
					}
					sHtml += "</select><input name=\"sedauidgbuttonpage\" id=\"sedauidgbuttonpage\" type=\"button\" class=\"seda-ui-pagingbtn seda-ui-dgbuttonpage\" value=\"Vai\" onclick=\"location = this.form.sedauiddlpage.value\" />";					
				}
				if (isShowrows() && !isShowrowindex()) {
					sHtml += "<span class=\"seda-ui-spandgrow\">Righe:"+iNumRow+"</span>"
						+ "";
				}
				sHtml += "</form>";
			}

		}
		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());
	}
	
	public String normalizeHref(String sHref)
	{
		sHref =sHref.replaceAll("&amp;","&");
		sHref = sHref.replaceAll("&", "&amp;");
		return sHref;
	}
}
