package com.seda.payer.core.bean;

import java.util.List;

import com.seda.data.spi.PageInfo;

public class ConfigurazioneModello3Pagelist extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		List<ConfigurazioneModello3> listConfigurazioneModello3;
		String configurazioneModello3ListXml;
		String configurazioneModello3ListXmlRiep;
		String retCode;
		String message;
		public ConfigurazioneModello3Pagelist() {
			
		}
		public ConfigurazioneModello3Pagelist(PageInfo pageInfo, String retCode,String message, String configurazioneModello3ListXml, List<ConfigurazioneModello3> listConfigurazioneModello3) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.configurazioneModello3ListXml = configurazioneModello3ListXml;
			this.listConfigurazioneModello3 = listConfigurazioneModello3;
		}
		public ConfigurazioneModello3Pagelist(PageInfo pageInfo, String retCode,String message, String[] configurazioneModello3ListXml, List<ConfigurazioneModello3> listConfigurazioneModello3) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.configurazioneModello3ListXml = configurazioneModello3ListXml[0];
			this.configurazioneModello3ListXmlRiep = configurazioneModello3ListXml[1];
			this.listConfigurazioneModello3 = listConfigurazioneModello3;
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		public String getConfigurazioneModello3ListXml() {
			return configurazioneModello3ListXml;
		}
		public void setConfigurazioneModello3ListXml(String configurazioneModello3ListXml) {
			this.configurazioneModello3ListXml = configurazioneModello3ListXml;
		}
		public String getConfigurazioneModello3ListXmlRiep() {
			return configurazioneModello3ListXmlRiep;
		}
		public void setConfigurazioneModello3ListXmlRiep(String configurazioneModello3ListXmlRiep) {
			this.configurazioneModello3ListXmlRiep = configurazioneModello3ListXmlRiep;
		}
		public List<ConfigurazioneModello3> getListConfigurazioneModello3() {
			return listConfigurazioneModello3;
		}
		public void setListConfigurazioneModello3(
				List<ConfigurazioneModello3> listConfigurazioneModello3) {
			this.listConfigurazioneModello3 = listConfigurazioneModello3;
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


