package com.seda.payer.core.monitoraggiocruss.bean;

public class MonitoraggioCruscotto {

	private PagamentoList pagamentoList;
	private TransazioneList transazioneList;
	private NotificaList notificaList;
	private RendicontazioneList rendicontazioneList;
	
	public MonitoraggioCruscotto(PagamentoList pagamentoList,
			TransazioneList transazioneList, NotificaList notificaList,
			RendicontazioneList rendicontazioneList) {
		super();
		this.pagamentoList = pagamentoList;
		this.transazioneList = transazioneList;
		this.notificaList = notificaList;
		this.rendicontazioneList = rendicontazioneList;
	}

	public MonitoraggioCruscotto() {
	}

	public PagamentoList getPagamentoList() {
		return pagamentoList;
	}
	public void setPagamentoList(PagamentoList pagamentoList) {
		this.pagamentoList = pagamentoList;
	}
	public TransazioneList getTransazioneList() {
		return transazioneList;
	}
	public void setTransazioneList(TransazioneList transazioneList) {
		this.transazioneList = transazioneList;
	}
	public NotificaList getNotificaList() {
		return notificaList;
	}
	public void setNotificaList(NotificaList notificaList) {
		this.notificaList = notificaList;
	}
	public RendicontazioneList getRendicontazioneList() {
		return rendicontazioneList;
	}
	public void setRendicontazioneList(RendicontazioneList rendicontazioneList) {
		this.rendicontazioneList = rendicontazioneList;
	}
}
