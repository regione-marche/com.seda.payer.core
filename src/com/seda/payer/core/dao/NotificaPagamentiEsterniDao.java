package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.ModuloIntegrazionePagamenti;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiContainer;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiOneri;
import com.seda.payer.core.bean.ModuloIntegrazionePagamentiPaymentStatus;
import com.seda.payer.core.bean.NotificaPagamentiEsterni;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;

public class NotificaPagamentiEsterniDao extends BaseDaoHandler {
	
	public NotificaPagamentiEsterniDao(Connection connection, String schema) {
		super(connection, schema);
	}
 
	public NotificaPagamentiEsterni doDetail(String chiaveTransazione, String chiaveDettaglioTransazione, String numeroDocumento, String numeroAvviso) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet data = null;
		try	{
			System.out.println("NotificaPagamentiEsterni doDetail - chiaveTransazione = " + chiaveTransazione);
			System.out.println("NotificaPagamentiEsterni doDetail - chiaveDettaglioTransazione = " + chiaveDettaglioTransazione);
			System.out.println("NotificaPagamentiEsterni doDetail - numeroDocumento = " + numeroDocumento);
			System.out.println("NotificaPagamentiEsterni doDetail - numeroAvviso = " + numeroAvviso);
			
			callableStatement = prepareCall(Routines.NEX_DODETAIL.routine());
			callableStatement.setString(1, chiaveTransazione);
			callableStatement.setString(2, chiaveDettaglioTransazione); //PG180110 FB
			callableStatement.setString(3, numeroDocumento);
			callableStatement.setString(4, numeroAvviso);
			
			if (callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if (data.next())
					return new NotificaPagamentiEsterni(data);
			}
			return null;
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			//if (data != null)
			//	DAOHelper.closeIgnoringException(data);
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
			//fine LP PG21XX04 Leak
		}
	}

	public void doSave(NotificaPagamentiEsterni mip) throws DaoException {
		CallableStatement callableStatement = null;
		try	{
			if (mip.getChiaveTransazione() == null || mip.getChiaveTransazione().length() == 0) 
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("NotificaPagamentiEsterni.chiaveTransazione"));
			
			NotificaPagamentiEsterni data = doDetail(mip.getChiaveTransazione(), mip.getChiaveDettaglioTransazione(), mip.getNumeroDocumento(), mip.getNumeroAvviso());
			
			if (data != null)  
			{
				System.out.println("NEX_DOUPDATE");
				callableStatement = prepareCall(Routines.NEX_DOUPDATE.routine());
				mip.update(callableStatement);
			}
			else
			{
				System.out.println("NEX_DOINSERT");
				callableStatement = prepareCall(Routines.NEX_DOINSERT.routine());
				mip.save(callableStatement);
			}

			callableStatement.execute();
			
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		finally {
			//inizio LP PG21XX04 Leak
			//if (callableStatement != null)
			//	DAOHelper.closeIgnoringException(callableStatement);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
//	public void doList(String codiceSocieta, String provincia, String codiceUtente, String chiaveEnte,
//			String chiaveTransazione, String numeroOperazione, String numeroDocumento, 
//			String esitoNotifica, String dataDa, String dataA,
//			int pageNumber, int rowsPerPage, String order) throws DaoException {
//		CallableStatement callableStatement = null;
//		try	{ 
//			callableStatement = prepareCall(Routines.NEX_DOLIST.routine());
//			callableStatement.setString(1, codiceSocieta);
//			callableStatement.setString(2, provincia);
//			callableStatement.setString(3, codiceUtente);
//			callableStatement.setString(4, chiaveEnte);
//			callableStatement.setString(5, chiaveTransazione);
//			callableStatement.setString(6, numeroOperazione);
//			callableStatement.setString(7, numeroDocumento);
//			callableStatement.setString(8, esitoNotifica);
//			callableStatement.setString(9, dataDa);
//			callableStatement.setString(10, dataA);
//			callableStatement.setString(11, order);
//			callableStatement.setInt(12, rowsPerPage);
//			callableStatement.setInt(13, pageNumber);
//			
//			 //OUT O_ROWINI INT
//			callableStatement.registerOutParameter(14, Types.INTEGER);
//			//OUT O_ROWEND INT
//			callableStatement.registerOutParameter(15, Types.INTEGER);
//			//OUT O_TOTROWS INT
//			callableStatement.registerOutParameter(16, Types.INTEGER);
//			//OUT O_TOTPAGES INT
//			callableStatement.registerOutParameter(17, Types.SMALLINT);
//			
//			if (callableStatement.execute()) {
//				this.loadWebRowSets(callableStatement);
//				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), 
//						callableStatement.getInt(15), callableStatement.getInt(16), callableStatement.getInt(17));
//			}
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		} finally {
//			if (callableStatement != null)
//				DAOHelper.closeIgnoringException(callableStatement);
//		}
//	}

}
