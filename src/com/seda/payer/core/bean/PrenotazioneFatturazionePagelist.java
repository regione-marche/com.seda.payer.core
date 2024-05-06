package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class PrenotazioneFatturazionePagelist extends PageInfo {

		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		String prenotazioneFatturazioneListXml;
		String prenotazioneFatturazioneListXmlRiep;
		String retCode;
		String message;

		public PrenotazioneFatturazionePagelist() {

		}
		public PrenotazioneFatturazionePagelist(PageInfo pageInfo, String retCode, String message, String prenotazioneFatturazioneListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.prenotazioneFatturazioneListXml = prenotazioneFatturazioneListXml;
		}
		public PrenotazioneFatturazionePagelist(PageInfo pageInfo, String retCode, String message, String[] prenotazioneFatturazioneListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.prenotazioneFatturazioneListXml = prenotazioneFatturazioneListXml[0];
			this.prenotazioneFatturazioneListXmlRiep = prenotazioneFatturazioneListXml[1];
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		public String getPrenotazioneFatturazioneListXml() {
			return prenotazioneFatturazioneListXml;
		}

		public void setPrenotazioneFatturazioneListXml(String prenotazioneFatturazioneListXml) {
			this.prenotazioneFatturazioneListXml = prenotazioneFatturazioneListXml;
		}

		public String getPrenotazioneFatturazioneListXmlRiep() {
			return prenotazioneFatturazioneListXmlRiep;
		}

		public void setPrenotazioneFatturazioneListXmlRiep(String prenotazioneFatturazioneListXmlRiep) {
			this.prenotazioneFatturazioneListXmlRiep = prenotazioneFatturazioneListXmlRiep;
		}
		public String getRetCode() {
			return retCode;
		}
		public void setRetCode(String retCode) {
			this.retCode = retCode;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
}


