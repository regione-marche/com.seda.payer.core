package com.seda.payer.core.bean;

import com.seda.data.spi.PageInfo;

public class BlackBoxPosLogPagelist extends PageInfo {
	
		private static final long serialVersionUID = 1L;
		PageInfo pageInfo = new PageInfo();
		String blackBoxPosLogPagelist;
		String blackBoxPosLogPagelistRiep;
		String retCode;
		String message;
		public BlackBoxPosLogPagelist() {
			
		}
		public BlackBoxPosLogPagelist(PageInfo pageInfo, String retCode,String message, String blackBoxPosLogPagelist) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.blackBoxPosLogPagelist = blackBoxPosLogPagelist;
		}
		public BlackBoxPosLogPagelist(PageInfo pageInfo, String retCode,String message, String[] blackBoxPosLogPagelist) {
			this.pageInfo = pageInfo;
			this.retCode = retCode;
			this.message = message;
			this.blackBoxPosLogPagelist = blackBoxPosLogPagelist[0];
			this.blackBoxPosLogPagelistRiep = blackBoxPosLogPagelist[1];
		}
		public PageInfo getPageInfo() {
			return pageInfo;
		}
		public void setPageInfo(PageInfo pageInfo) {
			this.pageInfo = pageInfo;
		}
		public String getblackBoxPosLogPagelist() {
			return blackBoxPosLogPagelist;
		}
		public void setblackBoxPosLogPagelist(String blackBoxPosLogPagelist) {
			this.blackBoxPosLogPagelist = blackBoxPosLogPagelist;
		}
		public String getblackBoxPosLogPagelistRiep() {
			return blackBoxPosLogPagelistRiep;
		}
		public void setblackBoxPosLogPagelistRiep(String blackBoxPosLogPagelistRiep) {
			this.blackBoxPosLogPagelistRiep = blackBoxPosLogPagelistRiep;
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


