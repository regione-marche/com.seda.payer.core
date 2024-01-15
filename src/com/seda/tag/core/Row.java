package com.seda.tag.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

public class Row  implements TagRenderInterface{
	public String sHtml;
	public ArrayList<Cell> alTcs = new ArrayList<Cell>();
	public ArrayList<CellHeader> alTcshd = new ArrayList<CellHeader>();
    public String sContent="";
    public String sCss;
    public String id;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSCss() {
		return sCss;
	}
	public void setSCss(String css) {
		sCss = css;
	}
	public void Add(Cell tc)
	{tc.createTag();
		alTcs.add(tc);
	sContent  += tc.render();
	}
	public void Add(CellHeader cell){
		cell.createTag();
		alTcshd.add(cell);
	sContent  += cell.render();
		   
		   
	   }
	public String hasCss()
	{
		if (sCss != null && sCss.trim()!="")
			return " "+sCss;
			else
				return"";
	}


	public String checkId(){
		if(id != null && id.trim() != "")	
			return " id=\""+id+"\"";
		else
			return"";

		}


	public String render() {
		
		sHtml=" <tr"+checkId()+" class=\"seda-ui-tr"+hasCss()+ "\">";
		
		sHtml += sContent;
		
		sHtml+=" </tr> ";
		return sHtml;
	}



	public void render(JspWriter writer) throws IOException {
		
		
		writer.println(render());
		
	}

}