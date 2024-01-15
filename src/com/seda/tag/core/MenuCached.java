package com.seda.tag.core;

import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;
import javax.sql.rowset.CachedRowSet;


public class MenuCached implements TagRenderInterface {

	String sHtml;
	String sId;
	String sCss;
	String sCachedRowSet;
	public String getSCachedRowSet() {
		return sCachedRowSet;
	}
	public void setSCachedRowSet(String cachedRowSet) {
		sCachedRowSet = cachedRowSet;
	}

	boolean bUseXml;
	CachedRowSet cRowSet ;
	ArrayList <MenuCachedItem> alMcItem = new ArrayList<MenuCachedItem>(); 

	public boolean bVertical;
	public boolean isBUseXml() {
		return bUseXml;
	}
	public void setBUseXml(boolean useXml) {
		bUseXml = useXml;
	}
	public CachedRowSet getCRowSet() {
		return cRowSet;
	}
	public void setCRowSet(CachedRowSet rowSet) {
		cRowSet = rowSet;
	}
	public boolean isBVertical() {
		return bVertical;
	}
	public void setBVertical(boolean vertical) {
		bVertical = vertical;
	}

	public boolean start;





	public String getSId() {
		return sId;
	}
	public String getSCss() {
		return sCss;
	}
	public void setSCss(String css) {
		sCss = css;
	}
	public void setSId(String id) {
		sId = id;
	}

	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return " "+sCss;
		else
			return"";
	}
	public void Add(MenuCachedItem mcItem)
	{ alMcItem.add(mcItem);

	}


	public String render() {
		sHtml= " <ul ";
		try{	


			if (bVertical)
				sHtml+= "class=\"seda-ui-menuv"+hasCss()+"\" >";
			else
				sHtml+= "class=\"seda-ui-menuo"+hasCss()+"\" >";

			if(alMcItem!= null && alMcItem.size()>0)
			{

				for(int i=0;i<alMcItem.size();i++)

				{
					MenuCachedItem mcItem =new MenuCachedItem();
					mcItem= alMcItem.get(i);


					if(cRowSet!= null )
					{

						if	((mcItem.getSText().contains("{") && mcItem.getSText().contains("}"))||(mcItem.getSHref().contains("{") && mcItem.getSHref().contains("}"))||(mcItem.hasCss().contains("{") && mcItem.hasCss().contains("}"))) {
							ResultSetMetaData rsMd = cRowSet.getMetaData();
							while(cRowSet.next()) {
								mcItem= alMcItem.get(i);
								String sValue =null;
								String content = null;
								content=	mcItem.getSText();

								if( content.contains("{")){
									while(content.contains("{"))
									{	
										int iSelint =	content.indexOf("{");
										int iSelend = content.indexOf("}");
										if(iSelint>=0 && iSelend >=0)
										{
											int iCol = Integer.parseInt( content.substring(iSelint+1, iSelend));

											if(rsMd.getColumnCount()>0)

												if(iCol <=rsMd.getColumnCount()&& iCol>0)
													sValue = cRowSet.getString(iCol);
											content =content.replace(content.substring(iSelint, iSelend+1),sValue);
										}
										else break;

									}

								}

								String	sContent = null;
								sContent = mcItem.getSHref();

								if( sContent.contains("{"))
								{
									while(sContent.contains("{"))
									{	int iSelint =	sContent.indexOf("{");
									int iSelend = sContent.indexOf("}");
									if(iSelint>=0 && iSelend >=0){
										int iCol = Integer.parseInt( sContent.substring(iSelint+1, iSelend));

										if(rsMd.getColumnCount()>0)

											if(iCol <=rsMd.getColumnCount()&& iCol>0)
												sValue = cRowSet.getString(iCol);
										sContent =sContent.replace(sContent.substring(iSelint, iSelend+1),sValue);
									}
									else break;

									}

								}

								String	cssContent = null;
								cssContent = mcItem.hasCss();

								if( cssContent.contains("{"))
								{
									while(cssContent.contains("{"))
									{	int iSelint =	cssContent.indexOf("{");
									int iSelend = cssContent.indexOf("}");
									if(iSelint>=0 && iSelend >=0){
										int iCol = Integer.parseInt( cssContent.substring(iSelint+1, iSelend));

										if(rsMd.getColumnCount()>0)

											if(iCol <=rsMd.getColumnCount()&& iCol>0)
												sValue = cRowSet.getString(iCol);
										cssContent =cssContent.replace(cssContent.substring(iSelint, iSelend+1),sValue);
									}
									else break;

									}

								}

								sHtml+=	renderitem(mcItem,content,sContent,cssContent);

							}
						}
						else
							if( !((mcItem.getSHref().contains("{") && mcItem.getSHref().contains("}"))||(mcItem.getSText().contains("{") && mcItem.getSText().contains("}"))))
							{sHtml+=	mcItem.render();

							}
							else{}

					}
					else
						if( !((mcItem.getSHref().contains("{") && mcItem.getSHref().contains("}"))||(mcItem.getSText().contains("{") && mcItem.getSText().contains("}"))))
						{sHtml+=	mcItem.render();

						}
						else{}




				}

			}
			sHtml += " </ul> ";


		}
		catch(Exception ex){
			sHtml="<div class=\"seda-ui-error\" >il tag menù cached non è ben definito</div>"	;
		}
		return sHtml;
	}

	public void render(JspWriter writer) throws IOException {
		writer.print(render());

	}
	public String renderitem(MenuCachedItem mcItem,String sText,String sHref, String sCss){

		try{mcItem.sHtml="";

		mcItem.sHtml+="<li class=\"seda-ui-menuitem";
		if(mcItem.hasSCss())
			mcItem.sHtml+= " " + sCss; //mcItem.hasCss();
		mcItem.sHtml+="\"> <a class=\"seda-ui-hlnkmenu";

		if(mcItem.hasSCss())
			mcItem.sHtml+= " " + sCss; //mcItem.hasCss();

		mcItem.sHtml+="\"";



		if(mcItem.hasSHref())
		{mcItem.sHtml += " href=\""+sHref+"\"";}
		if(mcItem.hasSCoords())
		{mcItem.sHtml += " coords=\""+mcItem.sCoords+"\"";}
		mcItem.sHtml +=" >" ;

		if(mcItem.hasSrc())
		{
			mcItem.sHtml+="<span class=\"seda-ui-lnkspan \">"+sText+ "</span> <img class=\"seda-ui-lnkimg\" alt=\""+sText+"\" src=\""+mcItem.src+" \" /> " ;

		}
		else
			if(mcItem.hasSText()){
				mcItem.sHtml += sText;
			}

		mcItem.sHtml +="</a> ";

		mcItem.sHtml+="</li>";
		}
		catch(Exception ex){
			mcItem.sHtml="<div class=\"seda-ui-error\" >il tag menucacheditem non è ben definito</div>"	;
		}

		String sEnd = mcItem.sHtml;

		return sEnd;

	}




}


