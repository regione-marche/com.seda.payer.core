package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.AbilitaCanalePagamentoTipoServizioEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 *
 */
public class AbilitaCanalePagamentoTipoServizioEnteDao extends BaseDaoHandler {
	
	public AbilitaCanalePagamentoTipoServizioEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public AbilitaCanalePagamentoTipoServizioEnte doDetail(String companyCode, String userCode, String chiaveEnte, String canalePagamento, String codiceTipoServizio) throws DaoException {
		return doDetailTail(true, companyCode, userCode, chiaveEnte, canalePagamento, codiceTipoServizio); 
	}

	public AbilitaCanalePagamentoTipoServizioEnte doDetailTail(boolean bFlagUpdateAutocomit, String companyCode, String userCode, String chiaveEnte, String canalePagamento, String codiceTipoServizio) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CES_DODETAIL.routine());
			//inizio LP 20240909 - PGNTBOLDER-1
			//callableStatement = prepareCall(Routines.CES_DODETAIL.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CES_DODETAIL.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);	
			callableStatement.setString(4, canalePagamento);	
			callableStatement.setString(5, codiceTipoServizio);			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new AbilitaCanalePagamentoTipoServizioEnte(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTBOLDER-1
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets(AbilitaCanalePagamentoTipoServizioEnte abilita, String strDescrEnte,String strDescrUtente,String strDescrCanalePagamento,String strDescrTipologiaServizio,String strDescrSocieta) throws DaoException {
		rowSets(abilita, 0, 0,strDescrEnte,strDescrUtente,strDescrCanalePagamento,strDescrTipologiaServizio,strDescrSocieta);
	}

	public void doRowSets(AbilitaCanalePagamentoTipoServizioEnte abilita, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrUtente,String strDescrCanalePagamento,String strDescrTipologiaServizio,String strDescrSocieta) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		rowSets(abilita, rowsPerPage, pageNumber,strDescrEnte,strDescrUtente,strDescrCanalePagamento,strDescrTipologiaServizio,strDescrSocieta);
	}

	public void rowSets(AbilitaCanalePagamentoTipoServizioEnte abilita, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrUtente,String strDescrCanalePagamento,String strDescrTipologiaServizio,String strDescrSocieta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CES_DOLIST.routine());
			callableStatement = prepareCall(Routines.CES_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, abilita.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, abilita.getEnte().getUser().getUserCode());
			callableStatement.setString(3, abilita.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, abilita.getCanale().getChiaveCanalePagamento());
			callableStatement.setString(5, abilita.getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(6, strDescrEnte);
			callableStatement.setString(7, strDescrUtente);
			callableStatement.setString(8, strDescrCanalePagamento);
			callableStatement.setString(9, strDescrTipologiaServizio);
			callableStatement.setString(10, strDescrSocieta);	//21022011 GG
			callableStatement.setString(11, "");
			callableStatement.setInt(12, rowsPerPage);
			callableStatement.setInt(13, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTBOLDER-1
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets2(String dummy, String companyCode,String codiceUtente,String chiaveEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{  
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CES_DOLIST2.routine());
			callableStatement = prepareCall(Routines.CES_DOLIST2.routine());
			//fine LP PG21XX04 Leak
            /*callableStatement.setString(1, companyCode);
            callableStatement.setString(2, codiceUtente);
            callableStatement.setString(3, chiaveEnte);*/
			/* we execute procedure */
			if (callableStatement.execute())  
				this.loadWebRowSets(callableStatement);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTBOLDER-1
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	//inizio LP 20240909 - PGNTBOLDER-1
	public void doSave(AbilitaCanalePagamentoTipoServizioEnte abilita, String codOp) throws DaoException {
		doSaveTail(true, abilita, codOp);
	}

	public void doSaveTail(boolean bFlagUpdateAutocomit, AbilitaCanalePagamentoTipoServizioEnte abilita, String codOp) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = null;
			//fine LP PG21XX04 Leak
			if (abilita.getEnte() == null || abilita.getEnte().getUser() == null || abilita.getEnte().getUser().getCompany() == null || abilita.getEnte().getUser().getCompany().getCompanyCode() == null || abilita.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.ente.user.company.companyCode"));

			if (abilita.getEnte().getUser().getUserCode() == null || abilita.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.ente.user.userCode"));
			
			if (abilita.getEnte().getAnagEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.ente.anagEnte.chiaveEnte"));
						
			if (abilita.getCanale() == null || abilita.getCanale().getChiaveCanalePagamento() == null || abilita.getCanale().getChiaveCanalePagamento().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.canale.chiaveCanalePagamento"));

			if (abilita.getTipoServizio() == null || abilita.getTipoServizio().getCodiceTipologiaServizio() == null || abilita.getTipoServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.tipologiaServizio.codiceTipologiaServizio"));
			//21022011 GG inizio
			/*
			 * AbilitaCanalePagamentoTipoServizioEnte data = doDetail(abilita.getEnte().getUser().getCompany().getCompanyCode(),abilita.getEnte().getUser().getUserCode(),abilita.getEnte().getAnagEnte().getChiaveEnte(),abilita.getCanale().getChiaveCanalePagamento(),abilita.getTipoServizio().getCodiceTipologiaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.CES_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.CES_DOINSERT.routine());
			*/
			if (codOp.equals(TypeRequest.ADD_SCOPE.scope())) {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.CES_DOINSERT.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CES_DOINSERT.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			} else if (codOp.equals(TypeRequest.EDIT_SCOPE.scope())) {
				//inizio LP 20240909 - PGNTBOLDER-1
				//callableStatement = prepareCall(Routines.CES_DOUPDATE.routine());
				callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CES_DOUPDATE.routine());
				//fine LP 20240909 - PGNTBOLDER-1
			} else {
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.saveadd.error"));
			}
			//21022011 GG fine
			abilita.save(callableStatement);
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTBOLDER-1
			}
		}
		//fine LP PG21XX04 Leak
	}

	//inizio LP 20240909 - PGNTBOLDER-1
	public void doDelete(AbilitaCanalePagamentoTipoServizioEnte abilita) throws DaoException {
		doDeleteTail(true, abilita);
	}

	public void doDeleteTail(boolean bFlagUpdateAutocomit, AbilitaCanalePagamentoTipoServizioEnte abilita) throws DaoException {
	//fine LP 20240909 - PGNTBOLDER-1
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//inizio LP 20240909 - PGNTBOLDER-1
			//CallableStatement callableStatement = prepareCall(Routines.CES_DODELETE.routine());
			callableStatement = prepareCall(bFlagUpdateAutocomit, Routines.CES_DODELETE.routine());
			//fine LP 20240909 - PGNTBOLDER-1
			//fine LP PG21XX04 Leak
			if (abilita.getEnte() == null || abilita.getEnte().getUser() == null || abilita.getEnte().getUser().getCompany() == null || abilita.getEnte().getUser().getCompany().getCompanyCode() == null || abilita.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.ente.user.company.companyCode"));

			if (abilita.getEnte().getUser().getUserCode() == null || abilita.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.ente.user.userCode"));
			
			if (abilita.getEnte().getAnagEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte() == null || abilita.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.ente.anagEnte.chiaveEnte"));
						
			if (abilita.getCanale() == null || abilita.getCanale().getChiaveCanalePagamento() == null || abilita.getCanale().getChiaveCanalePagamento().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.canale.chiaveCanalePagamento"));

			if (abilita.getTipoServizio() == null || abilita.getTipoServizio().getCodiceTipologiaServizio() == null || abilita.getTipoServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("abilitaCanalePagamentoTipoServizioEnte.tipologiaServizio.codiceTipologiaServizio"));
			callableStatement.setString(1, abilita.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, abilita.getEnte().getUser().getUserCode());
			callableStatement.setString(3, abilita.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, abilita.getCanale().getChiaveCanalePagamento());
			callableStatement.setString(5, abilita.getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.registerOutParameter(6, Types.CHAR);
			callableStatement.execute();
			if(callableStatement.getString(6).equalsIgnoreCase("N")){
				throw new DaoException(Integer.parseInt(Messages.DELETE_ERR_CODE.format()),Messages.DELETE_ERR_MESSAGE.format());
			}
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatement = null; //LP 20240909 - PGNTBOLDER-1
			}
		}
		//fine LP PG21XX04 Leak
	}
}