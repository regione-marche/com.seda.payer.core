package com.seda.tag.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspWriter;

public class Theader implements TagRenderInterface {
	public ArrayList<Row> alTrs = new ArrayList<Row>();;
    public String sHtml;
    public String sContent="";
    public String id="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void Add(Row tr)
	{alTrs.add(tr);
    sContent+=tr.render();
		
		
	}
	public String checkId(){
		if(id != null && id.trim() != "")	
			return " id=\""+id+"\"";
		else
			return"";

		}

	public String render() {
		
		sHtml=" <thead"+checkId() +"> ";
		sHtml+=sContent;
		
		sHtml+=" </thead> ";
		return sHtml;
	}



	public void render(JspWriter writer) throws IOException {
	
		sHtml=" <thead"+checkId() +"> ";
		sHtml+=sContent;
		sHtml+=" </thead> ";
		
		writer.println(sHtml);
		
	}

}