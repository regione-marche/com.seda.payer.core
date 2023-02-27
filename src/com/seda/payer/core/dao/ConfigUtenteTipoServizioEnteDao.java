package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.commons.utility.StringUtility;
import com.seda.payer.core.bean.ConfigUtenteTipoServizioEnte;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;
/**
 * 
 * @author mmontisano
 *
 */
public class ConfigUtenteTipoServizioEnteDao extends BaseDaoHandler {
	
	public ConfigUtenteTipoServizioEnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public ConfigUtenteTipoServizioEnte doDetail(String companyCode, String userCode, String chiaveEnte, String codiceTipoServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CFE_DODETAIL.routine());
			System.out.println("companyCode = " + companyCode);
			System.out.println("userCode = " + userCode);
			System.out.println("chiaveEnte = " + chiaveEnte);
			System.out.println("codiceTipoServizio = " + codiceTipoServizio);
			callableStatement = prepareCall(Routines.CFE_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);		
			callableStatement.setString(4, codiceTipoServizio);			
			if (callableStatement.execute()) {
				System.out.println("companyexecute OK ");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new ConfigUtenteTipoServizioEnte(data);
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
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets(ConfigUtenteTipoServizioEnte config,String strDescrEnte,String strDescrSocieta,String strDescrUtente,String strDescrTipologiaServizio) throws DaoException {
		rowSets(config, 0, 0, strDescrEnte,strDescrSocieta,strDescrUtente,strDescrTipologiaServizio);
	}

	public void doRowSets(ConfigUtenteTipoServizioEnte config, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrSocieta,String strDescrUtente,String strDescrTipologiaServizio) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(config, rowsPerPage, pageNumber, strDescrEnte,strDescrSocieta,strDescrUtente,strDescrTipologiaServizio);
		
	}
	
	public void rowSets(ConfigUtenteTipoServizioEnte config, int rowsPerPage, int pageNumber, String strDescrEnte,String strDescrSocieta,String strDescrUtente,String strDescrTipologiaServizio) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CFE_DOLIST.routine());
			callableStatement = prepareCall(Routines.CFE_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, config.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getEnte().getUser().getUserCode());
			callableStatement.setString(3, config.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, config.getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.setString(5, strDescrEnte);
			callableStatement.setString(6, strDescrSocieta);
			callableStatement.setString(7, strDescrUtente);
			callableStatement.setString(8, strDescrTipologiaServizio);
			callableStatement.setString(9, "");
			callableStatement.setInt(10, rowsPerPage);
			callableStatement.setInt(11, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
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
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doRowSets2(String procName, String companyCode, String codiceUtente, String chiaveEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{  
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = 
			//	prepareCall(!StringUtility.isEmpty(procName) ? procName : Routines.CFE_DOLIST_INS_BASE.routine());
			callableStatement = prepareCall(!StringUtility.isEmpty(procName) ? procName : Routines.CFE_DOLIST_INS_BASE.routine());
			//fine LP PG21XX04 Leak
            callableStatement.setString(1, companyCode);
            callableStatement.setString(2, codiceUtente);
            callableStatement.setString(3, chiaveEnte);
            
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
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void doRowSets3(String companyCode) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{  
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = 
			//	prepareCall (Routines.CFS_DOLIST_INS_BASE2.routine());
			callableStatement = prepareCall(Routines.CFS_DOLIST_INS_BASE2.routine());
			//fine LP PG21XX04 Leak
	        callableStatement.setString(1, companyCode);
	        /* we execute procedure */
			if (callableStatement.execute())  
				this.loadWebRowSets(callableStatement);
		} catch (SQLException x) { throw new DaoException(x);
		} catch (IllegalArgumentException x) { throw new DaoException(x);
		} catch (HelperException x) { throw new DaoException(x); }
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public void doSave(ConfigUtenteTipoServizioEnte config, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			
			
			if (config.getEnte().getUser() == null || config.getEnte().getUser().getUserCode() == null || config.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.user.userCode"));

			if (                          config.getEnte().getUser().getCompany() == null || config.getEnte().getUser().getCompany().getCompanyCode() == null || config.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.user.company.companyCode"));
			
			if (config.getEnte().getAnagEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.anagEnte.chiaveEnte"));	
		
			if (config.getTipoServizio() == null || config.getTipoServizio().getCodiceTipologiaServizio() == null || config.getTipoServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.tipologiaServizio.codiceTipologiaServizio"));	
					
			ConfigUtenteTipoServizioEnte data = doDetail(config.getEnte().getUser().getCompany().getCompanyCode(),config.getEnte().getUser().getUserCode(),config.getEnte().getAnagEnte().getChiaveEnte(),config.getTipoServizio().getCodiceTipologiaServizio());
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.CFE_DOUPDATE.routine());
			else
				callableStatement = prepareCall(Routines.CFE_DOINSERT.routine());
			config.save(callableStatement);
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
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	public void doDelete(ConfigUtenteTipoServizioEnte config) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.CFE_DODELETE.routine());
			callableStatement = prepareCall(Routines.CFE_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (config.getEnte().getUser().getUserCode() == null || config.getEnte().getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.userCode"));

			if (config.getEnte().getUser().getCompany() == null || config.getEnte().getUser().getCompany().getCompanyCode() == null || config.getEnte().getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.user.company.companyCode"));

			if (config.getEnte().getAnagEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte() == null || config.getEnte().getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.anagEnte.chiaveEnte"));	
		
			if (config.getTipoServizio() == null || config.getTipoServizio().getCodiceTipologiaServizio() == null || config.getTipoServizio().getCodiceTipologiaServizio().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("configutentetiposervizioente.tipologiaServizio.codiceTipologiaServizio"));	
					
			callableStatement.setString(1, config.getEnte().getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, config.getEnte().getUser().getUserCode());
			callableStatement.setString(3, config.getEnte().getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, config.getTipoServizio().getCodiceTipologiaServizio());
			callableStatement.execute();
			//commit();
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//fine LP PG21XX04 Leak
	}

	//inizio LP PG210130
	public ConfigUtenteTipoServizioEnte doDetail2(String companyCode, String codiceUtente, String codiceFiscaleEnte, String codiceTipologiaServizio, String ibanBancario) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			System.out.println("companyCode = " + companyCode);
			System.out.println("codiceUtente = " + codiceUtente);
			System.out.println("codiceFiscaleEnte = " + codiceFiscaleEnte);
			System.out.println("codiceTipologiaServizio = " + codiceTipologiaServizio);
			System.out.println("ibanBancario = " + ibanBancario);
			callableStatement = prepareCall("PYCFESP_SE2");
			System.out.println("post prepareCall");
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceFiscaleEnte);		
			callableStatement.setString(4, codiceTipologiaServizio);			
			callableStatement.setString(5, ibanBancario);			
			
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new ConfigUtenteTipoServizioEnte(data);
				else
					System.out.println("NON ci sono record dalla PYCFESP_SE2");
			}
			return null;
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (HelperException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} finally {
			if (data != null) {
				try {
					data.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//fine LP PG210130
	//inizio SB PG210140
		public String downloadServiziAttiviCsv(String companyCode, String codiceUtente, String chiaveEnte, String codiceTipologiaServizio) throws DaoException {
			CallableStatement callableStatement = null;
			ResultSet data = null;
			
			String result="";
			try	{
				callableStatement = prepareCall("PYCFESP_LST_CSV");
				callableStatement.setString(1, companyCode);
				callableStatement.setString(2, codiceUtente);
				callableStatement.setString(3, chiaveEnte);		
				callableStatement.setString(4, codiceTipologiaServizio);
				
				
				StringBuffer sb = new StringBuffer();
				boolean resultsAvailable = callableStatement.execute();
				
				while (resultsAvailable) {
					
					data = callableStatement.getResultSet();
					
					while(data.next()) {
						
						sb.append(data.getString("RECORD"));
						sb.append("\r\n");
					}
					
					resultsAvailable = callableStatement.getMoreResults();
				
				}
			
				result = sb.toString();
				
			} catch (SQLException x) {
				throw new DaoException(x);
			} catch (IllegalArgumentException x) {
				throw new DaoException(x);
			} catch (HelperException x) {
				throw new DaoException(x);
			} finally {
				if (data != null) {
					try {
						data.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (callableStatement != null) {
					try {
						callableStatement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return result;
		}
		//fine SB PG210140
}