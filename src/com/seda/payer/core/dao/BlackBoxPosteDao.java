package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ConfigurazioneBlackBox;
import com.seda.payer.core.bean.ConfigurazionePosteBlackBoxPos;
import com.seda.payer.core.bean.PosteBlackBoxTes;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.wallet.bean.EsitoRisposte;

public class BlackBoxPosteDao extends BaseDaoHandler {

	public BlackBoxPosteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP 20240911 - PGNTBIPOS-1
	public List<ConfigurazioneBlackBox> configurazionePosteBlackBoxList() throws DaoException {
		return configurazionePosteBlackBoxListTail(true, true);
	}

	public List<ConfigurazioneBlackBox> configurazionePosteBlackBoxListBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat) throws DaoException {
		return configurazionePosteBlackBoxListTail(bFlagUpdateAutocomit, bCloseStat);
	}

	public List<ConfigurazioneBlackBox> configurazionePosteBlackBoxListTail(boolean bFlagUpdateAutocomit, boolean bCloseStat) throws DaoException {
	//fine LP 20240911 - PGNTBIPOS-1
		List<ConfigurazioneBlackBox> result = new ArrayList<ConfigurazioneBlackBox>();
		//Connection connection = getConnection(); //LP 20240911 - PGNTBIPOS-1
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		try {
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNCNFSP_PST_LST.routine());
			//inizio LP 20240911 - PGNTBIPOS-1
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNCNFSP_PST_LST.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CNCNFSP_PST_LST.routine());
			//fine LP 20240911 - PGNTBIPOS-1
			//PGNTCORE-24 - fine
			//inizio LP 20240811 - PGNTCORE-24
			//resultSet = callableStatement.executeQuery();
			if(callableStatement.execute()) {
				resultSet = callableStatement.getResultSet();
				if(resultSet != null) {
			//fine LP 20240811 - PGNTCORE-24
					while (resultSet.next()) {
						ConfigurazioneBlackBox conf = new ConfigurazioneBlackBox();
						conf.setCodiceEnte(resultSet.getString("CNF_CCNFCENT"));
						conf.setCodiceIdentificativoDominio(resultSet.getString("CNF_CCNFCIDD"));
						conf.setCodiceSegregazione(resultSet.getString("CNF_CCNFSEGC"));
						conf.setEmailPoste(resultSet.getString("CNF_CCNFEPST"));
						conf.setCodiceServizio(resultSet.getString("CNF_CCNFCODS"));
						result.add(conf);
					}
			//inizio LP 20240811 - PGNTCORE-24
				}
			}
			//fine LP 20240811 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) { //LP 20240911 - PGNTBIPOS-1
			throw new DaoException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240911 - PGNTBIPOS-1
				callableStatement = null;
			}
			//fine LP 20240911 - PGNTBIPOS-1
		}
		return result;
	}

	//inizio LP 20240911 - PGNTBIPOS-1
	public Integer insert(ConfigurazionePosteBlackBoxPos blackboxpos) throws DaoException {
		return insertTail(true, true, blackboxpos);
	}

	public Integer insertBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, ConfigurazionePosteBlackBoxPos blackboxpos) throws DaoException {
		return insertTail(bFlagUpdateAutocomit, bCloseStat, blackboxpos);
	}

	private Integer insertTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, ConfigurazionePosteBlackBoxPos blackboxpos) throws DaoException {
	//fine LP 20240911 - PGNTBIPOS-1
		//Connection connection = getConnection(); //LP 20240911 - PGNTBIPOS-1
		int ret = 0;
		CallableStatement callableStatement = null;
		try {
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_INS.routine());
			//inizio LP 20240911 - PGNTBIPOS-1
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_INS.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CNDOCSP_PST_INS.routine());
			//fine LP 20240911 - PGNTBIPOS-1
			//PGNTCORE-24 - fine
			callableStatement.setString(1, blackboxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, blackboxpos.getCodiceEnte());
			callableStatement.setString(3, blackboxpos.getNumeroAvviso());
			callableStatement.setString(4, blackboxpos.getCodiceIdentificativoFlusso());
			callableStatement.setDate(5, new java.sql.Date(blackboxpos.getDataCreazione().getTimeInMillis()));
			callableStatement.setString(6, blackboxpos.getTipoRecord());
			callableStatement.setString(7, blackboxpos.getCodiceIdentificativoDocumento());
			callableStatement.setString(8, blackboxpos.getNumeroRata());
			callableStatement.setDate(9, new java.sql.Date(blackboxpos.getDataScadenza().getTimeInMillis()));
			callableStatement.setString(10, blackboxpos.getCodiceFiscale());
			//callableStatement.setDouble(11, blackboxpos.getImporto());
			callableStatement.setBigDecimal(11, new Bigdecimal(blackboxpos.getImporto()));
			callableStatement.setString(12, blackboxpos.getDenominazioneDebitore());
			callableStatement.setString(13, blackboxpos.getIndirizzoContribuente());
			callableStatement.setString(14, blackboxpos.getLocalitaContribuente());
			callableStatement.setString(15, blackboxpos.getProvinciaContribuente());
			callableStatement.setString(16, blackboxpos.getFlagAnnullamento());
			callableStatement.setDate(17, new java.sql.Date(blackboxpos.getDataAggiornamentoRecord().getTimeInMillis()));
			callableStatement.setString(18, blackboxpos.getCodiceIbanAccredito());
			callableStatement.setString(19, blackboxpos.getCodiceIuv());
			callableStatement.setString(20, blackboxpos.getFlagPagato());
			//callableStatement.setDouble(21, blackboxpos.getImportoPagato());
			callableStatement.setBigDecimal(21, new Bigdecimal(blackboxpos.getImportoPagato()));
			callableStatement.setString(22, blackboxpos.getCodiceTipologiaServizio());
			callableStatement.setString(23, blackboxpos.getCodiceIBAN2() == null ? "" : blackboxpos.getCodiceIBAN2());
			callableStatement.setString(24, blackboxpos.getCausaleServizio() == null ? "" : blackboxpos.getCausaleServizio());
			callableStatement.setString(25, blackboxpos.getCespite() == null ? "" : blackboxpos.getCespite());
			callableStatement.setString(26, blackboxpos.getAnnoRif() == null ? "" : blackboxpos.getAnnoRif());
			callableStatement.setString(27, blackboxpos.getCittaCAP() == null ? "" : blackboxpos.getCittaCAP());
			callableStatement.setString(28, blackboxpos.getCodiceUtente() == null ? "" : blackboxpos.getCodiceUtente());
			callableStatement.setString(29, blackboxpos.getCodiceSocieta() == null ? "" : blackboxpos.getCodiceSocieta());
			callableStatement.setString(30, blackboxpos.getChiaveEnte() == null ? "" : blackboxpos.getChiaveEnte());
			callableStatement.setString(31,(blackboxpos.getTassonomia() != null ? blackboxpos.getTassonomia().trim() : ""));
			callableStatement.setInt(32, blackboxpos.getChiaveTestata());
			callableStatement.setString(33, blackboxpos.getFlagPoste() ? "Y" : "N");
			callableStatement.setString(34, blackboxpos.getFlagInviato() ? "Y" : "N");
			callableStatement.setString(35, blackboxpos.getCodiceServizio());
			callableStatement.registerOutParameter(36, Types.INTEGER);
			callableStatement.execute();
			ret = callableStatement.getInt(36);
		} catch (SQLException e) {
			e.printStackTrace();
			ret = e.getErrorCode();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) {//LP 20240911 - PGNTBIPOS-1
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240911 - PGNTBIPOS-1
				callableStatement = null;
			}
			//fine LP 20240911 - PGNTBIPOS-1
		}
		return ret;
	}

	//inizio LP 20240911 - PGNTBIPOS-1
	public List<ConfigurazionePosteBlackBoxPos> configurazionePosteBlackBoxPos(String idDominio, String codiceEnte, boolean inviato) throws DaoException {
		return configurazionePosteBlackBoxPosTail(true, true, idDominio, codiceEnte, inviato);
	}

	public List<ConfigurazionePosteBlackBoxPos> configurazionePosteBlackBoxPosBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, String idDominio, String codiceEnte, boolean inviato) throws DaoException {
		return configurazionePosteBlackBoxPosTail(bFlagUpdateAutocomit, bCloseStat, idDominio, codiceEnte, inviato);
	}

	private List<ConfigurazionePosteBlackBoxPos> configurazionePosteBlackBoxPosTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, String idDominio, String codiceEnte, boolean inviato) throws DaoException {
	//fine LP 20240911 - PGNTBIPOS-1
		List<ConfigurazionePosteBlackBoxPos> result = new ArrayList<ConfigurazionePosteBlackBoxPos>();
		//Connection connection = getConnection(); //LP 20240911 - PGNTBIPOS-1
		CallableStatement callableStatement = null;
		ResultSet resultSet = null;
		try {
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_LST.routine());
			//inizio LP 20240911 - PGNTBIPOS-1
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_LST.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CNDOCSP_PST_LST.routine());
			//fine LP 20240911 - PGNTBIPOS-1
			//PGNTCORE-24 - fine
			callableStatement.setString(1, idDominio);
			callableStatement.setString(2, codiceEnte);
			callableStatement.setString(3, inviato ? "Y" : "N");
			//inizio LP 20240811 - PGNTCORE-24
			//resultSet = callableStatement.executeQuery();
			if(callableStatement.execute()) {
				resultSet = callableStatement.getResultSet();
				if(resultSet != null) {
			//fine LP 20240811 - PGNTCORE-24
					while (resultSet.next()) {
						ConfigurazionePosteBlackBoxPos conf = new ConfigurazionePosteBlackBoxPos(resultSet);
						result.add(conf);
					}
			//inizio LP 20240811 - PGNTCORE-24
				}
			}
			//fine LP 20240811 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (HelperException e) { //LP 20240911 - PGNTBIPOS-1
			throw new DaoException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//inizio LP 20240911 - PGNTBIPOS-1
				callableStatement = null;
			}
			//fine LP 20240911 - PGNTBIPOS-1
		}
		return result;
	}

	//inizio LP 20240911 - PGNTBIPOS-1
	public ConfigurazionePosteBlackBoxPos select(String codiceIdentificativoDominio, String codiceEnte, String numeroAvviso) throws DaoException {
		return selectTail(true, true, codiceIdentificativoDominio, codiceEnte, numeroAvviso);
	}

	public ConfigurazionePosteBlackBoxPos selectBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, String codiceIdentificativoDominio, String codiceEnte, String numeroAvviso) throws DaoException {
		return selectTail(bFlagUpdateAutocomit, bCloseStat, codiceIdentificativoDominio, codiceEnte, numeroAvviso);
	}

	private ConfigurazionePosteBlackBoxPos selectTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, String codiceIdentificativoDominio, String codiceEnte, String numeroAvviso) throws DaoException {
	//fine LP 20240911 - PGNTBIPOS-1
		ConfigurazionePosteBlackBoxPos blackboxpos = null;
		//Connection connection = getConnection(); //LP 20240911 - PGNTBIPOS-1
		CallableStatement callableStatement = null;
		ResultSet resultSet =  null;
		try {
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_SEL.routine());
			//inizio LP 20240911 - PGNTBIPOS-1
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_SEL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CNDOCSP_PST_SEL.routine());
			//fine LP 20240911 - PGNTBIPOS-1
			//PGNTCORE-24 - fine
			callableStatement.setString(1, codiceIdentificativoDominio);
			callableStatement.setString(2, codiceEnte);
			callableStatement.setString(3, numeroAvviso);
			//inizio LP 20240811 - PGNTCORE-24
			//callableStatement.execute()
			if(callableStatement.execute()) {
				resultSet = callableStatement.getResultSet();
				if(resultSet != null) {
			//fine LP 20240811 - PGNTCORE-24
					if (resultSet.next()) {
						blackboxpos = new ConfigurazionePosteBlackBoxPos(resultSet);
					}
			//inizio LP 20240811 - PGNTCORE-24
				}
			}
			//fine LP 20240811 - PGNTCORE-24
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) { //LP 20240911 - PGNTBIPOS-1
			throw new DaoException(e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240911 - PGNTBIPOS-1
				callableStatement = null;
			}
			//fine LP 20240911 - PGNTBIPOS-1
		}
		return blackboxpos;
	}

	//inizio LP 20240911 - PGNTBIPOS-1
	public Integer insert(PosteBlackBoxTes testata) throws DaoException {
		return insertTail(true, true, testata); 
	}

	public Integer insertBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, PosteBlackBoxTes testata) throws DaoException {
		return insertTail(bFlagUpdateAutocomit, bCloseStat, testata); 
	}

	private Integer insertTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, PosteBlackBoxTes testata) throws DaoException {
	//fine LP 20240911 - PGNTBIPOS-1
		//Connection connection = getConnection(); //LP 20240911 - PGNTBIPOS-1
		int ret = 0;
		CallableStatement callableStatement = null;
		try {
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNPSTSP_INS.routine());
			//inizio LP 20240911 - PGNTBIPOS-1
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNPSTSP_INS.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CNPSTSP_INS.routine());
			//fine LP 20240911 - PGNTBIPOS-1
			//PGNTCORE-24 - fine
			callableStatement.setString(1, testata.getTipoFlusso());
			callableStatement.setString(2, testata.getCodiceIdDominio());
			callableStatement.setDate(3, new java.sql.Date(testata.getTimestampFlusso().getTimeInMillis()));
			callableStatement.setString(4, testata.getNomeFile());
			callableStatement.execute();
			ret = callableStatement.getInt(5);
		} catch (SQLException e) {
			e.printStackTrace();
			ret = e.getErrorCode();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) { //LP 20240911 - PGNTBIPOS-1
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240911 - PGNTBIPOS-1
				callableStatement = null;
			}
			//fine LP 20240911 - PGNTBIPOS-1
		}
		return ret;
	}

	//inizio LP 20240911 - PGNTBIPOS-1
	public EsitoRisposte delete(ConfigurazionePosteBlackBoxPos configurazionePosteBlackBoxpos) throws DaoException {
		return deleteTail(true, true, configurazionePosteBlackBoxpos);
	}

	public EsitoRisposte deleteBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, ConfigurazionePosteBlackBoxPos configurazionePosteBlackBoxpos) throws DaoException {
		return deleteTail(bFlagUpdateAutocomit, bCloseStat, configurazionePosteBlackBoxpos);
	}

	private EsitoRisposte deleteTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, ConfigurazionePosteBlackBoxPos configurazionePosteBlackBoxpos) throws DaoException {
	//fine LP 20240911 - PGNTBIPOS-1
		//Connection connection = getConnection(); //LP 20240911 - PGNTBIPOS-1
		EsitoRisposte esitoRisposte = new EsitoRisposte();
		CallableStatement callableStatement = null;
		try {
			//PGNTCORE-24 - inizio
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_DEL.routine());
			//inizio LP 20240911 - PGNTBIPOS-1
			//callableStatement = MetaProcedure.prepareCall(connection, getSchema(), Routines.CNDOCSP_DEL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CNDOCSP_DEL.routine());
			//fine LP 20240911 - PGNTBIPOS-1
			//PGNTCORE-24 - fine
			callableStatement.setString(1, configurazionePosteBlackBoxpos.getCodiceIdentificativoDominio());
			callableStatement.setString(2, configurazionePosteBlackBoxpos.getCodiceEnte());
			callableStatement.setString(3, configurazionePosteBlackBoxpos.getNumeroAvviso());
			callableStatement.registerOutParameter(4, Types.VARCHAR);
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.execute();
			esitoRisposte.setCodiceMessaggio(callableStatement.getString(4));
			esitoRisposte.setDescrizioneMessaggio(callableStatement.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (HelperException e) { //LP 20240911 - PGNTBIPOS-1
			e.printStackTrace();
			throw new DaoException(e);
		} finally {
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			//inizio LP 20240911 - PGNTBIPOS-1
				callableStatement = null;
			}
			//fine LP 20240911 - PGNTBIPOS-1
		}
		return esitoRisposte;
	}
	
	/**
	 * Metodo di aggiornamento della posizione debitoria, setta il flag INVIATO a Y
	 * 
	 * @param ConfigurazionePosteBlackBoxPos
	 * @throws DaoException 
	 * 
	 */
	public boolean aggiornaPosizioneDebitoria(ConfigurazionePosteBlackBoxPos posizioneDebitoria) throws DaoException {
	//inizio LP 20240912 - PAGONET-604
		return aggiornaPosizioneDebitoriaTail(true, true, posizioneDebitoria);
	}
	
	public boolean aggiornaPosizioneDebitoriaBatch(boolean bFlagUpdateAutocomit, boolean bCloseStat, ConfigurazionePosteBlackBoxPos posizioneDebitoria) throws DaoException {
		return aggiornaPosizioneDebitoriaTail(bFlagUpdateAutocomit, bCloseStat, posizioneDebitoria);
	}

	private boolean aggiornaPosizioneDebitoriaTail(boolean bFlagUpdateAutocomit, boolean bCloseStat, ConfigurazionePosteBlackBoxPos posizioneDebitoria) throws DaoException {
	//fine LP 20240912 - PAGONET-604
		boolean updated = false;
		CallableStatement stat = null;
		try {
			//inizio LP 20240912 - PAGONET-604
			//Connection connection = getConnection();
			//stat = Helper.prepareCall(connection, getSchema(), Routines.CNDOCSP_PST_UPD.routine());
			stat = prepareCall(bFlagUpdateAutocomit, Routines.CNDOCSP_PST_UPD.routine());
			stat.setString(1, posizioneDebitoria.getCodiceIdentificativoDominio());
			stat.setString(2, posizioneDebitoria.getCodiceEnte());
			stat.setString(3, posizioneDebitoria.getNumeroAvviso());
			stat.setInt(4, posizioneDebitoria.getChiaveTestataRT());
			stat.executeQuery();
			updated = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new DaoException(ex);
		} finally {
			//inizio LP 20240911 - PGNTBIPOS-1
			if(bCloseStat) {
			//fine LP 20240911 - PGNTBIPOS-1
				if (stat != null) {
					try {
						stat.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		//inizio LP 20240911 - PGNTBIPOS-1
				stat = null;
			}
		//fine LP 20240911 - PGNTBIPOS-1
		}
		return updated;
	}
}
