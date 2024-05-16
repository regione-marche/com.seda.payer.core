package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class PrenotazioneFatturazioneList extends PageInfo {

		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		String prenotazioneElaborazioniListXml;
		String prenotazioneElaborazioniListXmlRiep;
		String retCode;
		String message;
		public PrenotazioneFatturazioneList() {

		}

		public PrenotazioneFatturazioneList(PageInfo pageInfo, String retCode, String message, String prenotazioneElaborazioniListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.prenotazioneElaborazioniListXml = prenotazioneElaborazioniListXml;
		}

		public PrenotazioneFatturazioneList(PageInfo pageInfo, String retCode, String message, String[] prenotazioneElaborazioniListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.prenotazioneElaborazioniListXml = prenotazioneElaborazioniListXml[0];
			this.prenotazioneElaborazioniListXmlRiep = prenotazioneElaborazioniListXml[1];
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}

		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}

		public String getPrenotazioneElaborazioniListXml() {
			return prenotazioneElaborazioniListXml;
		}

		public void setPrenotazioneElaborazioniListXml(String prenotazioneElaborazioniListXml) {
			this.prenotazioneElaborazioniListXml = prenotazioneElaborazioniListXml;
		}

		public String getPrenotazioneElaborazioniListXmlRiep() {
			return prenotazioneElaborazioniListXmlRiep;
		}

		public void setPrenotazioneElaborazioniListXmlRiep(String prenotazioneElaborazioniListXmlRiep) {
			this.prenotazioneElaborazioniListXmlRiep = prenotazioneElaborazioniListXmlRiep;
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


