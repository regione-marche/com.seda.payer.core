package com.seda.payer.core.wallet.dao;

import java.io.Serializable;
import java.util.ArrayList;

import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.AbilitazioneDiscarico;
import com.seda.payer.core.wallet.bean.EsitoRisposte;
import com.seda.payer.core.wallet.bean.WalletPageList;

public interface AbilitazioneDiscaricoDAO extends Serializable {
	
	
	//AbilitazioneDiscarico selectAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico) throws  DaoException;
	WalletPageList abilitazioneList(AbilitazioneDiscarico abilitazioneDiscarico,int rowsPerPage, int pageNumber,String OrderBy) throws  DaoException;
    String insertAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico) throws  DaoException;
    int updateAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico) throws  DaoException;
    EsitoRisposte deleteAbilitazione(AbilitazioneDiscarico abilitazioneDiscarico) throws  DaoException;
}
