package com.seda.tag.core;

import java.io.IOException;


import javax.servlet.jsp.JspWriter;

/**
 * @author dbadm
 *
 */
public interface TagRenderInterface {
	public abstract String render();
	public abstract void render(JspWriter writer) throws IOException; 
}
