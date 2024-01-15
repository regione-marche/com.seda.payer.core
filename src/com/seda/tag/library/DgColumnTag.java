package com.seda.tag.library;

import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import com.seda.tag.core.DgColumn;
import com.seda.tag.core.IfSeda;

public class DgColumnTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;

	DgColumn dg = new DgColumn();

	public void setAsc(String value) {
		dg.setSAsc(value);
	}

	public void setDesc(String value) {
		dg.setSDesc(value);
	}

	public void setLabel(String label) {
		dg.setSLabel(label);
	}

	public void setIndex(int index) {
		dg.setIIndex(index);
	}

	public void setTitle(String title) {
		dg.setSTitle(title);
	}

	public void setifTag(IfSeda ifSeda) {
		dg.setIfTag(ifSeda);
	}

	public void setCss(String css) {
		dg.setSCss(css);
	}

	public void add(IfSeda ifTag) {
		dg.Add(ifTag);
	}

	public void setFormat(String format) {
		dg.setFormat(format);
	}

	public int doEndTag() {

		if (dg.getIIndex() != 0 && dg.getSTitle() != null) {

		} else {

			if (dg.getIIndex() == 0 && dg.getSTitle() == null) {
				BodyContent bodyContent = super.getBodyContent();
				String bodyString = "";
				String Prova = "";
				if (bodyContent != null)
					bodyString = bodyContent.getString();
				else
					bodyString = " ";

				Prova = bodyString;

				dg.setSContent(Prova);

			}

			if (dg.getIIndex() == 0 && dg.getSTitle() == null
					&& dg.getSLabel() == null) {
				dg.setSLabel("Colonna non ben definita");
				dg.setSContent("<span class=\"seda-ui-error\"> Attributi mancanti al Tag dgColumn </span>   ");
				dg.setIfTag(null);
			}

			Tag t = DgColumnTag.this.getParent();
			if (t.getClass() == com.seda.tag.library.DatagridTag.class) {
				com.seda.tag.library.DatagridTag Datag = (com.seda.tag.library.DatagridTag) t;

				Datag.Add(dg);
			} else if (t.getClass() == com.seda.tag.library.DgRecordTag.class) {
				com.seda.tag.library.DgRecordTag dRecord = (com.seda.tag.library.DgRecordTag) t;

				dRecord.Add(dg);
			}
			// Sistemare Meglio ma così dovrebbe essere Ok (Manca la gestione
			// dell errore)
			else if (t.getClass() == com.seda.tag.library.ElseDatagridTag.class) {
				com.seda.tag.library.IfDatagridTag ifTag = (com.seda.tag.library.IfDatagridTag) t
						.getParent();

				ifTag.addDgElse(dg);
			} else if (t.getClass() == com.seda.tag.library.ThenDatagridTag.class) {
				com.seda.tag.library.IfDatagridTag ifTag = (com.seda.tag.library.IfDatagridTag) t
						.getParent();

				ifTag.addDgThen(dg);
			}
		}

		dg = new DgColumn();
		return EVAL_PAGE;
	}

	public void release() {
		dg = new DgColumn();
	}
}
