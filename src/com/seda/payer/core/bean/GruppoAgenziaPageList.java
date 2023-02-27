
package com.seda.payer.core.bean;

import java.util.List;

import com.seda.data.spi.PageInfo;

public class GruppoAgenziaPageList extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		List<GruppoAgenzia> listGruppoAgenzia;
		String gruppoAgenziaListXml;
		String retCode;
		String message;
		public GruppoAgenziaPageList() {
			
		}
		public GruppoAgenziaPageList(PageInfo pageInfo, String retCode,String message, String gruppoAgenziaListXml, List<GruppoAgenzia> listGruppoAgenzia) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.gruppoAgenziaListXml = gruppoAgenziaListXml;
			this.listGruppoAgenzia = listGruppoAgenzia;
		}
		public GruppoAgenziaPageList(PageInfo pageInfo, String retCode,String message, String[] gruppoAgenziaListXml, List<GruppoAgenzia> listGruppoAgenzia) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.gruppoAgenziaListXml = gruppoAgenziaListXml[0];
			this.listGruppoAgenzia = listGruppoAgenzia;
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		public String getGruppoAgenziaListXml() {
			return gruppoAgenziaListXml;
		}
		public void setGruppoAgenziaListXml(String gruppoAgenziaListXml) {
			this.gruppoAgenziaListXml = gruppoAgenziaListXml;
		}
		public List<GruppoAgenzia> getListGruppoAgenzia() {
			return listGruppoAgenzia;
		}
		public void setListGruppoAgenzia(
				List<GruppoAgenzia> listGruppoAgenzia) {
			this.listGruppoAgenzia = listGruppoAgenzia;
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


