package com.seda.payer.core.dao;

import java.io.Serializable;
import com.seda.payer.core.bean.BlackBoxPagelist;
import com.seda.payer.core.bean.BlackBoxPosLog;
import com.seda.payer.core.bean.BlackBoxPosLogPagelist;
import com.seda.payer.core.bean.BlackBoxPosPagelist;
import com.seda.payer.core.bean.ConfigurazioneBlackBox;
import com.seda.payer.core.bean.ConfigurazioneBlackBoxPos;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public interface ConfigurazioneBlackBoxDao extends Serializable {

	public ConfigurazioneBlackBox select(ConfigurazioneBlackBox blackbox) throws  DaoException;
	public Integer update(ConfigurazioneBlackBox blackbox) throws  DaoException;
	public BlackBoxPagelist blackboxList(ConfigurazioneBlackBox blackbox, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public EsitoRisposte insert(ConfigurazioneBlackBox configurazioneBlackBox) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneBlackBox configurazioneBlackBox) throws  DaoException;
	
	//PG200120
	public ConfigurazioneBlackBoxPos select(ConfigurazioneBlackBoxPos blackboxpos) throws  DaoException;
	public Integer update(ConfigurazioneBlackBoxPos blackboxpos) throws  DaoException;
	public BlackBoxPosPagelist blackboxposList(ConfigurazioneBlackBoxPos blackboxpos, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public EsitoRisposte delete(ConfigurazioneBlackBoxPos configurazioneBlackBoxpos) throws  DaoException;
	public void updatePagPos(ConfigurazioneBlackBoxPos blackboxpos) throws  DaoException;

	public BlackBoxPosLogPagelist blackboxposlogList(BlackBoxPosLog blackboxpos, int rowsPerPage, int pageNumber, String OrderBy) throws  DaoException;
	public EsitoRisposte delete(BlackBoxPosLog configurazioneBlackBoxpos) throws  DaoException;
	public Integer insert(BlackBoxPosLog configurazioneBlackBoxpos) throws  DaoException;
	//FINE PG200120
	
	// PG200180
	public int getProgressivoIuv(String codiceEnte, String code) throws DaoException;
	public Integer insert(ConfigurazioneBlackBoxPos blackboxpos) throws DaoException;
	// FINE PG200180
	
	public int getProgressivoIuvGiornaliero(String codiceEnte, String code) throws DaoException;
	
}
