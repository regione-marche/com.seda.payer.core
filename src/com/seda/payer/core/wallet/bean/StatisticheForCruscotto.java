package com.seda.payer.core.wallet.bean;

import java.io.Serializable;

import com.seda.data.dao.ModelAttributes;

public class StatisticheForCruscotto extends ModelAttributes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int recordsScuole;
	private int recordsBorsellino;
	private int recordsRicariche;
	private int recordsDetteglioRicariche;
	private int recordsFigli;
	private int recordsAddebiti;
	private int recordsSolleciti;
	private int recordsPresenzeGiornaliere;
	private int recordsPresenzeForfettarie;

	public int getRecordsScuole() {
		return recordsScuole;
	}
	public void setRecordsScuole(int recordsScuole) {
		this.recordsScuole = recordsScuole;
	}
	public int getRecordsBorsellino() {
		return recordsBorsellino;
	}
	public void setRecordsBorsellino(int recordsBorsellino) {
		this.recordsBorsellino = recordsBorsellino;
	}
	public int getRecordsRicariche() {
		return recordsRicariche;
	}
	public void setRecordsRicariche(int recordsRicariche) {
		this.recordsRicariche = recordsRicariche;
	}
	public int getRecordsDetteglioRicariche() {
		return recordsDetteglioRicariche;
	}
	public void setRecordsDetteglioRicariche(int recordsDetteglioRicariche) {
		this.recordsDetteglioRicariche = recordsDetteglioRicariche;
	}
	public int getRecordsFigli() {
		return recordsFigli;
	}
	public void setRecordsFigli(int recordsFigli) {
		this.recordsFigli = recordsFigli;
	}
	public int getRecordsAddebiti() {
		return recordsAddebiti;
	}
	public void setRecordsAddebiti(int recordsAddebiti) {
		this.recordsAddebiti = recordsAddebiti;
	}
	public int getRecordsSolleciti() {
		return recordsSolleciti;
	}
	public void setRecordsSolleciti(int recordsSolleciti) {
		this.recordsSolleciti = recordsSolleciti;
	}
	public int getRecordsPresenzeGiornaliere() {
		return recordsPresenzeGiornaliere;
	}
	public void setRecordsPresenzeGiornaliere(int recordsPresenzeGiornaliere) {
		this.recordsPresenzeGiornaliere = recordsPresenzeGiornaliere;
	}
	public int getRecordsPresenzeForfettarie() {
		return recordsPresenzeForfettarie;
	}
	public void setRecordsPresenzeForfettarie(int recordsPresenzeForfettarie) {
		this.recordsPresenzeForfettarie = recordsPresenzeForfettarie;
	}
	
	public StatisticheForCruscotto(int recordsScuole,
			int recordsBorsellino, int recordsRicariche,
			int recordsDetteglioRicariche, int recordsFigli,
			int recordsAddebiti, int recordsSolleciti,
			int recordsPresenzeGiornaliere, int recordsPresenzeForfettarie) {
		super();
		this.recordsScuole = recordsScuole;
		this.recordsBorsellino = recordsBorsellino;
		this.recordsRicariche = recordsRicariche;
		this.recordsDetteglioRicariche = recordsDetteglioRicariche;
		this.recordsFigli = recordsFigli;
		this.recordsAddebiti = recordsAddebiti;
		this.recordsSolleciti = recordsSolleciti;
		this.recordsPresenzeGiornaliere = recordsPresenzeGiornaliere;
		this.recordsPresenzeForfettarie = recordsPresenzeForfettarie;
	}

	public StatisticheForCruscotto() {
		super();
	}

}
