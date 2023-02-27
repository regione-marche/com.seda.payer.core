package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class BlackBoxPagelist extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		String blackboxListXml;
		String blackboxListXmlRiep;
		String retCode;
		String message;
		public BlackBoxPagelist() {
			
		}
		public BlackBoxPagelist(PageInfo pageInfo, String retCode,String message, String blackboxListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.blackboxListXml = blackboxListXml;
		}
		public BlackBoxPagelist(PageInfo pageInfo, String retCode,String message, String[] blackboxListXml) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.blackboxListXml = blackboxListXml[0];
			this.blackboxListXmlRiep = blackboxListXml[1];
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		public String getBlackboxListXml() {
			return blackboxListXml;
		}
		public void setBlackboxListXml(String blackboxListXml) {
			this.blackboxListXml = blackboxListXml;
		}
		public String getBlackboxListXmlRiep() {
			return blackboxListXmlRiep;
		}
		public void setBlackboxListXmlRiep(String blackboxListXmlRiep) {
			this.blackboxListXmlRiep = blackboxListXmlRiep;
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


