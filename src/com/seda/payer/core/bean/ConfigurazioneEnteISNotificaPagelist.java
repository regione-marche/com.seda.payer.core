package com.seda.payer.core.bean;

import java.util.List;

import com.seda.data.spi.PageInfo;

public class ConfigurazioneEnteISNotificaPagelist extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		List<ConfigurazioneEnteISNotifica> listConfigurazioneEnteISNotifica;
		String configurazioneEnteISNotificaListXml;
		String configurazioneEnteISNotificaListXmlRiep;
		String retCode;
		String message;

		public ConfigurazioneEnteISNotificaPagelist() {
		}

		public ConfigurazioneEnteISNotificaPagelist(PageInfo pageInfo, String retCode,String message, String configurazioneEnteISNotificaListXml, List<ConfigurazioneEnteISNotifica> listConfigurazioneEnteISNotifica) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.configurazioneEnteISNotificaListXml = configurazioneEnteISNotificaListXml;
			this.listConfigurazioneEnteISNotifica = listConfigurazioneEnteISNotifica;
		}

		public ConfigurazioneEnteISNotificaPagelist(PageInfo pageInfo, String retCode,String message, String[] configurazioneEnteISNotificaListXml, List<ConfigurazioneEnteISNotifica> listConfigurazioneEnteISNotifica) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.configurazioneEnteISNotificaListXml = configurazioneEnteISNotificaListXml[0];
			this.configurazioneEnteISNotificaListXmlRiep = configurazioneEnteISNotificaListXml[1];
			this.listConfigurazioneEnteISNotifica = listConfigurazioneEnteISNotifica;
		}

		public PageInfo getPageInfo() {
			return pageInfo;
		}

		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}

		public String getConfigurazioneEnteISNotificaListXml() {
			return configurazioneEnteISNotificaListXml;
		}

		public void setConfigurazioneEnteISNotificaListXml(String configurazioneEnteISNotificaListXml) {
			this.configurazioneEnteISNotificaListXml = configurazioneEnteISNotificaListXml;
		}

		public String getConfigurazioneEnteISNotificaListXmlRiep() {
			return configurazioneEnteISNotificaListXmlRiep;
		}

		public void setConfigurazioneEnteISNotificaListXmlRiep(String configurazioneEnteISNotificaListXmlRiep) {
			this.configurazioneEnteISNotificaListXmlRiep = configurazioneEnteISNotificaListXmlRiep;
		}

		public List<ConfigurazioneEnteISNotifica> getListConfigurazioneEnteISNotifica() {
			return listConfigurazioneEnteISNotifica;
		}

		public void setListConfigurazioneEnteISNotifica(
				List<ConfigurazioneEnteISNotifica> listConfigurazioneEnteISNotifica) {
			this.listConfigurazioneEnteISNotifica = listConfigurazioneEnteISNotifica;
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


