package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.Ente;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
import com.seda.data.helper.HelperException;


public class EnteDao extends BaseDaoHandler {
	
	public EnteDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public Ente doDetail(String companyCode, String userCode, String chiaveEnte) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENT_DODETAIL.routine());
			callableStatement = prepareCall(Routines.ENT_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveEnte);			
			if (callableStatement.execute()) {
				System.out.println("Ente doDetail execute eseguita");
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new Ente(data);
			}
			return null;
		} catch (SQLException x) {
			x.printStackTrace();
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
	//inizio LP 20180629
	//inizio LP PG200060
	//public Ente doDetailToCodFis(String userCode, String codFis) throws DaoException {
	public Ente doDetailToCodFis(String userCode, String codFis, String dbSchemaCodSocieta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
	//fine LP PG200060
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENT_DODETAIL_CODFIS.routine());
			callableStatement = prepareCall(Routines.ENT_DODETAIL_CODFIS.routine());
			//fine LP PG21XX04 Leak
			//inizio LP PG200060
			//NOTA: le sp SV e RM sono diverse
			//      in RM si usa la condizione di ricerca con i 3 parametri passati
			//      in SV si usa la condizione di ricerca solo sul codFis
			//      � stata modificata la SP al suo interno esegue due diverse
			//      query con diverso uso dei parametri.
			//callableStatement.setString(1, userCode);
			//callableStatement.setString(2, codFis);		
			callableStatement.setString(1, userCode);
			callableStatement.setString(2, dbSchemaCodSocieta);
			callableStatement.setString(3, codFis);		
			//fine LP PG200060
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new Ente(data);
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
	//fine LP 20180629
	public void doRowSetsForAdd(Ente ente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENT_DOLIST_INS.routine());
			callableStatement = prepareCall(Routines.ENT_DOLIST_INS.routine());
			//fine LP PG21XX04 Leak
			/*callableStatement.setString(1, "");
			callableStatement.setString(2, "");
			callableStatement.setString(3, "");*/
			/* we execute procedure */
			if (callableStatement.execute())
				this.loadWebRowSets(callableStatement);

			System.out.println("[INFO] - invoke doRowSetsForAdd successfully");
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
	
	public void doRowSets(Ente ente,String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		rowSets(ente, 0, 0,strDescrEnte,strDescrSocieta,strDescrUtente);
	}

	public void doRowSets(Ente ente, int rowsPerPage, int pageNumber, String strDescrEnte, String strDescrSocieta, String strDescrUtente) throws DaoException {
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));

		rowSets(ente, rowsPerPage, pageNumber,strDescrEnte,strDescrSocieta,strDescrUtente);
	}

	public void rowSets(Ente ente, int rowsPerPage, int pageNumber,String strDescrEnte,String strDescrSocieta,String strDescrUtente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENT_DOLIST.routine());
			callableStatement = prepareCall(Routines.ENT_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, ente.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, ente.getUser().getUserCode());
			callableStatement.setString(3, ente.getAnagEnte().getChiaveEnte());
			callableStatement.setString(4, ente.getTipoEnte());
			callableStatement.setString(5, strDescrEnte);
			callableStatement.setString(6, strDescrSocieta);
			callableStatement.setString(7, strDescrUtente);
			callableStatement.setString(8, "");
			callableStatement.setInt(9, rowsPerPage);
			callableStatement.setInt(10, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
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

	public void doSave(Ente ente, String codOp) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (ente.getUser() == null || ente.getUser().getUserCode() == null || ente.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.user.userCode"));

			if (ente.getUser().getCompany() == null || ente.getUser().getCompany().getCompanyCode() == null || ente.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.user.company.companyCode"));

			if (ente.getAnagEnte() == null || ente.getAnagEnte().getChiaveEnte() == null || ente.getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.anagEnte.chiaveEnte"));

			Ente data = doDetail(ente.getUser().getCompany().getCompanyCode(), ente.getUser().getUserCode(), ente.getAnagEnte().getChiaveEnte());
			System.out.println("CORE ENTE doDetail dentro doSave eseguita");
			if (data != null && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0) 
				throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format(data.getUser().getCompany().getCompanyCode() + 
						" / " + data.getUser().getUserCode() + " / " + data.getAnagEnte().getChiaveEnte()));

			if (data != null)
				callableStatement = prepareCall(Routines.ENT_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.ENT_DOINSERT.routine());

			ente.save(callableStatement);
			//System.out.println("CORE ENTE save dentro doSave eseguita");
			callableStatement.execute();
			//System.out.println("CORE ENTE callableStatement dentro doSave eseguita");
			//commit();
		} catch (SQLException x) {
			x.printStackTrace();
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
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

	public void doDelete(Ente ente) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.ENT_DODELETE.routine());
			callableStatement = prepareCall(Routines.ENT_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (ente.getUser().getUserCode() == null || ente.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.user.userCode"));

			if (ente.getUser().getCompany() == null || ente.getUser().getCompany().getCompanyCode() == null || ente.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("ente.user.company.companyCode"));

			callableStatement.setString(1, ente.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, ente.getUser().getUserCode());
			callableStatement.setString(3, ente.getAnagEnte().getChiaveEnte());
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
	
	public Ente doDetailToCodFisAndKEnte(String userCode, String codFis, String chiaveEnte, String dbSchemaCodSocieta) throws DaoException {
		//fine LP PG200060
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
			try	{
				//inizio LP PG21XX04 Leak
				//CallableStatement callableStatement = prepareCall(Routines.ENT_DODETAIL_CODFIS_KENTE.routine());
				callableStatement = prepareCall(Routines.ENT_DODETAIL_CODFIS_KENTE.routine());
				//fine LP PG21XX04 Leak
				//inizio LP PG200060
				//NOTA: le sp SV e RM sono diverse
				//      in RM si usa la condizione di ricerca con i 3 parametri passati
				//      in SV si usa la condizione di ricerca solo sul codFis
				//      � stata modificata la SP al suo interno esegue due diverse
				//      query con diverso uso dei parametri.
				callableStatement.setString(1, userCode);
				callableStatement.setString(2, dbSchemaCodSocieta);
				callableStatement.setString(3, codFis);		
				callableStatement.setString(4, chiaveEnte);		
				//fine LP PG200060
				if (callableStatement.execute()) {
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					if (data.next())
						return new Ente(data);
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
}