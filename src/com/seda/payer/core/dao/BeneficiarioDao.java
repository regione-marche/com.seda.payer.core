package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.Beneficiario;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

// 5/9/2011 cambio nome routine da PYATCBENSP_LST_DDL a PYATCSP_LST_BEN_DDL
// e da PYBENSP_LST_DDL a PYANESP_LST_BEN_DDL
public class BeneficiarioDao extends BaseDaoHandler {
	
/*	
	public static final String BEN_DODETAIL = "PYBENSP_SEL";
	public static final String BEN_DOLIST = "PYBENSP_LST";
	public static final String BEN_DOINSERT = "PYBENSP_INS";
	public static final String BEN_DOUPDATE = "PYBENSP_UPD";
	public static final String BEN_DODELETE = "PYBENSP_DEL";
	
//	public static final String BEN_DODDLIST = "PYBENSP_LST_DDL";
	public static final String BEN_DODDLIST = "PYANEBENSP_LST_BEN_DDL";

//	public static final String BEN_DOIMPDDLIST = "PYIMPBENSP_LST_DDL";
	public static final String BEN_DOIMPDDLIST = "PYANEBENSP_LST_IMP_DDL";

//	public static final String ATC_BENDODDLIST = "PYATCBENSP_LST_DDL";
	public static final String APC_BENDODDLIST = "PYAPCBENSP_LST_BEN_DDL";
	*/
	
	public BeneficiarioDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public Beneficiario doDetail(String companyCode, String userCode, String chiaveBeneficiario) throws DaoException {
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			callableStatement = prepareCall(Routines.BEN_DODETAIL.routine());
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			callableStatement.setString(3, chiaveBeneficiario);			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new Beneficiario(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
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
			}
			//fine LP PG21XX04 Leak
		}
	}

	public void doRowSets(Beneficiario beneficiario, String ordine) throws DaoException {
		doRowSets(beneficiario, ordine, 0, 0);
	}

	public void doRowSets(Beneficiario beneficiario, String ordine, int rowsPerPage, int pageNumber) throws DaoException {
		CallableStatement callableStatement = null;
		if (rowsPerPage <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
		if (pageNumber <= 0)
			throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
		try	{
			callableStatement = prepareCall(Routines.BEN_DOLIST.routine());
			callableStatement.setString(1, beneficiario.getUser().getCompany().getCompanyCode());
			callableStatement.setString(2, beneficiario.getAnagEnte().getAnagProvCom().getCodiceBelfiore());
			callableStatement.setString(3, beneficiario.getUser().getUserCode());
			callableStatement.setString(4, beneficiario.getAnagEnte().getChiaveEnte());
			//Aggiungere gli altri campi ?
			/*
			callableStatement.setString(4, ente.getTipoEnte());
			callableStatement.setString(5, ente.getNumeroContoCorrente());
			callableStatement.setString(6, ente.getIntestatarioContoCorrente());
			callableStatement.setString(7, strDescrEnte);
			callableStatement.setString(8, strDescrSocieta);
			callableStatement.setString(9, strDescrUtente);
			*/
			callableStatement.setString(5, ordine);
			callableStatement.setInt(6, rowsPerPage);
			callableStatement.setInt(7, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(8, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(11, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(8), callableStatement.getInt(9), 
								 callableStatement.getInt(10), callableStatement.getInt(11));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	public int doSave(Beneficiario beneficiario, String codOp) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			if (beneficiario.getUser() == null || beneficiario.getUser().getUserCode() == null || beneficiario.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.userCode"));
			if (beneficiario.getUser().getCompany() == null || beneficiario.getUser().getCompany().getCompanyCode() == null || beneficiario.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.company.companyCode"));
			if (beneficiario.getAnagEnte() == null || beneficiario.getAnagEnte().getChiaveEnte() == null || beneficiario.getAnagEnte().getChiaveEnte().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.anagEnte.chiaveEnte"));
			Beneficiario data = doDetail(beneficiario.getUser().getCompany().getCompanyCode(), beneficiario.getUser().getUserCode(), beneficiario.getAnagEnte().getChiaveEnte());
			/*
			if (data != null && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0) 
				throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format(data.getUser().getCompany().getCompanyCode() + 
						" / " + data.getUser().getUserCode() + " / " + data.getAnagEnte().getChiaveEnte()));
			*/
			if (data != null && codOp != null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope()) == 0) 
				throw new IllegalArgumentException(Messages.UNIQUE_CONSTRAINT_VIOLATION.format("Scheda Beneficiario"));
			if (data != null)
				callableStatement = prepareCall(Routines.BEN_DOUPDATE.routine());
			else 
				callableStatement = prepareCall(Routines.BEN_DOINSERT.routine());
			beneficiario.save(callableStatement);
			callableStatement.execute();
			//commit();
			code = callableStatement.getInt(12);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			System.out.println("doSave failed generic error due to: " + x.getMessage());
			throw new DaoException(101, x.getMessage());
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}

	public int doDelete(Beneficiario beneficiario) throws DaoException {
		int code = 0;
		CallableStatement callableStatement = null;
		try	{
			callableStatement = prepareCall(Routines.BEN_DODELETE.routine());
			if (beneficiario.getUser().getUserCode() == null || beneficiario.getUser().getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.userCode"));
			if (beneficiario.getUser().getCompany() == null || beneficiario.getUser().getCompany().getCompanyCode() == null || beneficiario.getUser().getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("beneficiario.user.company.companyCode"));
			beneficiario.delete(callableStatement);
			callableStatement.execute();
			//commit();
			code = callableStatement.getInt(4);
		} catch (SQLException x) {
			throw new DaoException(x.getErrorCode(),x.getMessage(),x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(Integer.parseInt(Messages.ILL_ARG_ERR_CODE.format()),x.getMessage(),x);
		} catch (HelperException x) {
			throw new DaoException(Integer.parseInt(Messages.HELPER_ERR_CODE.format()),x.getMessage(),x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return code;
	}
	
	//lista dei beneficiari 
	public String doDDListBeneficiari(String codSocieta, String codProvincia, String codUtente) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BEN_DODDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			callableStatement.setString(2, codProvincia == null ? "" : codProvincia);
			callableStatement.setString(3, codUtente == null ? "" : codUtente);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			return this.getWebRowSetXml(BeneficiarioDao.IDX_DOLIST_LISTA);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}

	//lista dei beneficiari per impositori
	public String doDDListBeneficiariFromImp(String codSocieta, String codProvincia, String codUtente, String codImpositore,String codSocietaBen, String codProvinciaBen, String codUtenteBen) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BEN_DOIMPDDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			callableStatement.setString(2, codProvincia == null ? "" : codProvincia);
			callableStatement.setString(3, codUtente == null ? "" : codUtente);
			callableStatement.setString(4, codImpositore == null ? "" : codImpositore);
			callableStatement.setString(5, codSocietaBen == null ? "" : codSocietaBen);
			callableStatement.setString(6, codProvinciaBen == null ? "" : codProvinciaBen);
			callableStatement.setString(7, codUtenteBen == null ? "" : codUtenteBen);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			return this.getWebRowSetXml(BeneficiarioDao.IDX_DOLIST_LISTA);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//lista dei beneficiari cbp
	public String doDDListBeneficiariCBP(String codSocieta, String codProvincia, String codUtente) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.BEN_DOCBPDDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);
			callableStatement.setString(2, codProvincia == null ? "" : codProvincia);
			callableStatement.setString(3, codUtente == null ? "" : codUtente);
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			return this.getWebRowSetXml(BeneficiarioDao.IDX_DOLIST_LISTA);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	//lista dei province dai beneficiari a partire dalla tabella beneficiari 
	public String doDDListProvinceByBeneficiari(String codSocieta) throws DaoException {
		CallableStatement callableStatement = null;
		try	{ 
			callableStatement = prepareCall(Routines.APC_BENDODDLIST.routine());
			callableStatement.setString(1, codSocieta == null ? "" : codSocieta);

			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);	
			}
			return this.getWebRowSetXml(BeneficiarioDao.IDX_DOLIST_LISTA);
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			//closeConnection(callableStatement);
			if(callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
//	private void closeConnection(CallableStatement callableStatement)
//	{
//		if (callableStatement != null)
//			DAOHelper.closeIgnoringException(callableStatement);
//	}

}