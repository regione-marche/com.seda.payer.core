package com.seda.payer.core.riconciliazionemt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.sql.DataSource;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.Helper;
import com.seda.data.helper.HelperException;
import com.seda.data.spi.PageInfo;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.riconciliazionemt.bean.ContoList;
import com.seda.payer.core.riconciliazionemt.bean.FlussiAbbinatiList;
import com.seda.payer.core.riconciliazionemt.bean.Flusso;
import com.seda.payer.core.riconciliazionemt.bean.FlussoPageList;
import com.seda.payer.core.riconciliazionemt.bean.Flusso;
import com.seda.payer.core.riconciliazionemt.bean.FlussoPageList;
import com.seda.payer.core.riconciliazionemt.bean.GiornaleDiCassa;
import com.seda.payer.core.riconciliazionemt.bean.GiornaleDiCassaPageList;
import com.seda.payer.core.riconciliazionemt.bean.MittenteList;
import com.seda.payer.core.riconciliazionemt.bean.MovimentoDiCassa;
import com.seda.payer.core.riconciliazionemt.bean.MovimentoDiCassaPageList;
import com.seda.payer.core.riconciliazionemt.bean.NumeroDocumentoList;
import com.seda.payer.core.riconciliazionemt.bean.PspList;
import com.seda.payer.core.riconciliazionemt.bean.Transazione;
import com.seda.payer.core.riconciliazionemt.bean.TransazionePageList;
import com.seda.payer.core.riconciliazionemt.bean.TransazioniAbbinateList;

public class GiornaleDiCassaDAOImpl extends BaseDaoHandler implements GiornaleDiCassaDAO {
	private static final long serialVersionUID = 1L;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public GiornaleDiCassaDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}
	
	//inizio LP PG21XX04 Leak
	public GiornaleDiCassaDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//fine LP PG21XX04 Leak
	/*public List<Flusso> doList(Flusso gdc) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		List<Flusso> listGDC = null;
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYGDCSP_LST");
			callableStatement.setString(1, gdc.getCodSocieta());
			callableStatement.setString(2, gdc.getCodUtente());
			callableStatement.setString(3, gdc.getCodEnte());
			callableStatement.setString(4, gdc.getProvenienza());
			if (gdc.getDataGiornaleDa()==null) {
				callableStatement.setTimestamp(5, null);
			} else {
				callableStatement.setTimestamp(5, new java.sql.Timestamp(gdc.getDataGiornaleDa().getTimeInMillis()));
			}
			if (gdc.getDataGiornaleA()==null) {
				callableStatement.setTimestamp(6, null);
			} else {
				callableStatement.setTimestamp(6, new java.sql.Timestamp(gdc.getDataGiornaleA().getTimeInMillis()));
			}
			if (gdc.getDataMovimentoDa()==null) {
				callableStatement.setTimestamp(7, null);
			} else {
				callableStatement.setTimestamp(7, new java.sql.Timestamp(gdc.getDataMovimentoDa().getTimeInMillis()));
			}
			if (gdc.getDataMovimentoA()==null) {
				callableStatement.setTimestamp(8, null);
			} else {
				callableStatement.setTimestamp(8, new java.sql.Timestamp(gdc.getDataMovimentoA().getTimeInMillis()));
			}
			callableStatement.setString(9, gdc.getSospRegolarizzati());
			callableStatement.setString(10, gdc.getSospRendicontati());
			callableStatement.setString(11, gdc.getNumDocumento());
			
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			listGDC = new ArrayList<Flusso>();
			while(resultSet.next()) {
				Flusso item = new Flusso();
				item.setCodEnte(resultSet.getString("ANE_DANEDENT"));
				item.setProvenienza(resultSet.getString("GDC_CGDCPROV"));
				item.setEsercizio(resultSet.getInt("GDC_CGDCESER"));
				item.setIdFlusso(resultSet.getString("GDC_CGDCIDFL"));
				GregorianCalendar dataGiornale = new GregorianCalendar();
				dataGiornale.setTimeInMillis(resultSet.getTimestamp("GDC_GGDCDTIN").getTime());
				item.setDataGiornale(dataGiornale);
				item.setSospRegolarizzati(resultSet.getString("GDC_CMDCREGO"));
				item.setSospRendicontati(resultSet.getString("GDC_CMDCREND"));

				listGDC.add(item);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			DAOHelper.closeIgnoringException(connection);
		} 
		
		return listGDC;
		
	}
	*/
	public GiornaleDiCassaPageList ListGiornaliDiCassa(
			GiornaleDiCassa gdc,
			int rowsPerPage, int pageNumber, String order)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		GiornaleDiCassaPageList gdcPageList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYGDCSP_LST");                      
			callableStatement.setString(1, gdc.getCodSocieta());
			callableStatement.setString(2, gdc.getCodUtente());
			callableStatement.setString(3, gdc.getCodEnte());
			callableStatement.setString(4, gdc.getProvenienza());
			if (gdc.getDataGiornaleDa()==null) {
				callableStatement.setTimestamp(5, null);
			} else {
				callableStatement.setTimestamp(5, new java.sql.Timestamp(gdc.getDataGiornaleDa().getTimeInMillis()));
			}
			if (gdc.getDataGiornaleA()==null) {
				callableStatement.setTimestamp(6, null);
			} else {
				callableStatement.setTimestamp(6, new java.sql.Timestamp(gdc.getDataGiornaleA().getTimeInMillis()));
			}
			if (gdc.getDataMovimentoDa()==null) {
				callableStatement.setTimestamp(7, null);
			} else {
				callableStatement.setTimestamp(7, new java.sql.Timestamp(gdc.getDataMovimentoDa().getTimeInMillis()));
			}
			if (gdc.getDataMovimentoA()==null) {
				callableStatement.setTimestamp(8, null);
			} else {
				callableStatement.setTimestamp(8, new java.sql.Timestamp(gdc.getDataMovimentoA().getTimeInMillis()));
			}
			
			callableStatement.setString(9, gdc.getSospRegolarizzati());
			callableStatement.setString(10, gdc.getSospRendicontati());
			callableStatement.setString(11, gdc.getNumDocumento());
			callableStatement.setString(12, gdc.getPsp());
			
			callableStatement.setInt(13, pageNumber);                        
			callableStatement.setInt(14, rowsPerPage);
			callableStatement.setString(15, order == null ? "" : order);
			callableStatement.setString(16, gdc.getIdFlusso());
			callableStatement.setString(17, gdc.getChiaveRen());		
			
			/* we register row start */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(19, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(20, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(21, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(18));
				pageInfo.setLastRow(callableStatement.getInt(19));
				pageInfo.setNumRows(callableStatement.getInt(20));
				pageInfo.setNumPages(callableStatement.getInt(21));
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				gdcPageList = new GiornaleDiCassaPageList(pageInfo, "00","",getWebRowSetXml());
				return gdcPageList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			gdcPageList = new GiornaleDiCassaPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			gdcPageList = new GiornaleDiCassaPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			gdcPageList = new GiornaleDiCassaPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return gdcPageList;	
	}
	
	public PspList ListPsp(GiornaleDiCassa gdc) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PspList pspList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYMDCSP_LST_PSP");                      
			callableStatement.setString(1, gdc.getCodSocieta());
			callableStatement.setString(2, gdc.getCodUtente());
			callableStatement.setString(3, gdc.getCodEnte());
			callableStatement.setLong(4, gdc.getId());
			
			if(callableStatement.execute())	{
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				pspList = new PspList(getWebRowSetXml());
				return pspList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			pspList = new PspList("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			pspList = new PspList("");
		} catch (HelperException e) {
			e.printStackTrace();
			pspList = new PspList("");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return pspList;
	}
	
	public ContoList ListConto(long IDgdc) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		ContoList contoList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYMDCSP_LST_CONT");                      
			callableStatement.setLong(1, IDgdc);
			
			if(callableStatement.execute())	{
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				contoList = new ContoList(getWebRowSetXml());
				return contoList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			contoList = new ContoList("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			contoList = new ContoList("");
		} catch (HelperException e) {
			e.printStackTrace();
			contoList = new ContoList("");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return contoList;
	}
	
	public MovimentoDiCassaPageList ListMovimentiDiCassa(
			GiornaleDiCassa gdc,
			MovimentoDiCassa mdc,
			int rowsPerPage, int pageNumber, String order)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		MovimentoDiCassaPageList mdcPageList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYMDCSP_LST");                      
			callableStatement.setLong(1, gdc.getId());
			callableStatement.setString(2, mdc.getRegolarizzato());
			callableStatement.setString(3, mdc.getRendicontato());
			callableStatement.setString(4, mdc.getConto());
			callableStatement.setString(5, mdc.getStatoSospeso());
			callableStatement.setString(6, mdc.getNumDocumento());
			callableStatement.setString(7, mdc.getCliente());
			callableStatement.setBigDecimal(8, mdc.getImportoDa());
			callableStatement.setBigDecimal(9, mdc.getImportoA());
			callableStatement.setString(10, mdc.getSquadratura());
			callableStatement.setString(11, gdc.getProvenienza());
			if (gdc.getDataGiornaleDa()==null) {
				callableStatement.setTimestamp(12, null);
			} else {
				callableStatement.setTimestamp(12, new java.sql.Timestamp(gdc.getDataGiornaleDa().getTimeInMillis()));
			}
			if (gdc.getDataGiornaleA()==null) {
				callableStatement.setTimestamp(13, null);
			} else {
				callableStatement.setTimestamp(13, new java.sql.Timestamp(gdc.getDataGiornaleA().getTimeInMillis()));
			}		
			callableStatement.setString(14, gdc.getIdFlusso());
			callableStatement.setString(15, mdc.getAssociazioni());
			callableStatement.setInt(16, pageNumber);                        
			callableStatement.setInt(17, rowsPerPage);
			callableStatement.setString(18, order == null ? "" : order);
			
			
			/* we register row start */
			callableStatement.registerOutParameter(19, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(20, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(21, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(22, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(19));
				pageInfo.setLastRow(callableStatement.getInt(20));
				pageInfo.setNumRows(callableStatement.getInt(21));
				pageInfo.setNumPages(callableStatement.getInt(22));
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				mdcPageList = new MovimentoDiCassaPageList(pageInfo, "00","",getWebRowSetXml());
				return mdcPageList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			mdcPageList = new MovimentoDiCassaPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			mdcPageList = new MovimentoDiCassaPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			mdcPageList = new MovimentoDiCassaPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return mdcPageList;	
	}
	
	public MovimentoDiCassa dettaglioMovimentoDiCassa(long idMdc) throws DaoException {
		CallableStatement callableStatement = null;
		Connection connection = null;
		ResultSet resultSet = null;
		MovimentoDiCassa mdc = null;
		
		try {
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYMDCSP_SEL");
			callableStatement.setLong(1, idMdc);
			
			callableStatement.execute();
			resultSet=callableStatement.getResultSet();
			
			mdc = new MovimentoDiCassa();
			if(resultSet.next()) {
				mdc.setId(resultSet.getLong("MDC_PMDCPKEY"));
				mdc.setConto(resultSet.getString("MDC_CMDCCONT"));
				mdc.setStatoSospeso(resultSet.getString("MDC_CMDCSTAT"));
				mdc.setNumDocumento(resultSet.getString("MDC_IMDCDOCN"));
				mdc.setCliente(resultSet.getString("MDC_CMDCCLIE"));
				mdc.setImporto(resultSet.getBigDecimal("MDC_DMDCIMPO"));
				mdc.setImportoSquadratura(resultSet.getBigDecimal("MDC_DMDCSQUA"));
				mdc.setRendicontato(resultSet.getString("MDC_CMDCREND"));
				mdc.setRegolarizzato(resultSet.getString("MDC_CMDCREGO"));
				mdc.setProgressivoDoc(resultSet.getLong("MDC_IMDCPRDO"));
				mdc.setNumBolletta(resultSet.getString("MDC_CMDCNBOL"));	
				GregorianCalendar dataMovimento = new GregorianCalendar();
				dataMovimento.setTimeInMillis(resultSet.getTimestamp("MDC_GMDCDMOV").getTime());
				mdc.setDataMovimento(dataMovimento);
				if(resultSet.getTimestamp("MDC_GMDCDVAL")!=null) {
					GregorianCalendar dataValuta = new GregorianCalendar();
					dataValuta.setTimeInMillis(resultSet.getTimestamp("MDC_GMDCDVAL").getTime());
					mdc.setDataValuta(dataValuta);
				}
				mdc.setTipoEsecuzione(resultSet.getString("MDC_CMDCESEC"));
				mdc.setCodiceRiferimento(resultSet.getString("MDC_CMDCCRIF"));
				mdc.setCausale(resultSet.getString("MDC_CMDCCAUS"));
				
				mdc.setIdGiornale(resultSet.getLong("GDC_PGDCPKEY"));
				mdc.setEnte(resultSet.getString("ANE_DANEDENT"));
				mdc.setProvenienza(resultSet.getString("GDC_CGDCPROV"));
				GregorianCalendar dataGiornale = new GregorianCalendar();
				dataGiornale.setTimeInMillis(resultSet.getTimestamp("GDC_GGDCDTIN").getTime());
				mdc.setDataMovimento(dataGiornale);
				mdc.setIdFlusso(resultSet.getString("GDC_CGDCIDFL"));
				mdc.setEsercizio(resultSet.getInt("GDC_CGDCESER"));
				mdc.setSospRegolarizzati(resultSet.getString("GDC_CMDCREGO"));
				mdc.setSospRendicontati(resultSet.getString("GDC_CMDCREND"));
				
				mdc.setNota(resultSet.getString("MDC_CMDCNREN"));				
				if(resultSet.getTimestamp("MDC_GMDCDREN")!=null) {
					GregorianCalendar dataRendicontazione = new GregorianCalendar();
					dataRendicontazione.setTimeInMillis(resultSet.getTimestamp("MDC_GMDCDREN").getTime());
					mdc.setDataRendicontazione(dataRendicontazione);
				}
				mdc.setCutecute(resultSet.getString("MDC_CUTECUTE"));
				mdc.setSiglaProvincia(resultSet.getString("APC_CAPCSIGL"));
				mdc.setChiaveEnte(resultSet.getString("MDC_KANEKENT"));
				mdc.setCodiceSocieta(resultSet.getString("MDC_CSOCCSOC"));
				
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (resultSet != null) {
				try {
					resultSet.close();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		} 
		
		return mdc;
	}
	
	public FlussiAbbinatiList FlussiAbbinati(long IDmdc) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		FlussiAbbinatiList flussiAbbinatiList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMFSP_SEL");                      
			callableStatement.setLong(1, IDmdc);
			
			if(callableStatement.execute())	{
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				flussiAbbinatiList = new FlussiAbbinatiList(getWebRowSetXml());
				return flussiAbbinatiList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flussiAbbinatiList = new FlussiAbbinatiList("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			flussiAbbinatiList = new FlussiAbbinatiList("");
		} catch (HelperException e) {
			e.printStackTrace();
			flussiAbbinatiList = new FlussiAbbinatiList("");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return flussiAbbinatiList;
	}
	
	public TransazioniAbbinateList TransazioniAbbinate(long IDmdc) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		TransazioniAbbinateList transazioniAbbinateList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMTSP_SEL");                      
			callableStatement.setLong(1, IDmdc);
			
			if(callableStatement.execute())	{
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				transazioniAbbinateList = new TransazioniAbbinateList(getWebRowSetXml());
				return transazioniAbbinateList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			transazioniAbbinateList = new TransazioniAbbinateList("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			transazioniAbbinateList = new TransazioniAbbinateList("");
		} catch (HelperException e) {
			e.printStackTrace();
			transazioniAbbinateList = new TransazioniAbbinateList("");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return transazioniAbbinateList;
	}
	
	public NumeroDocumentoList ListNumeroDocumento(GiornaleDiCassa gdc) throws  DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		NumeroDocumentoList numdocList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYMDCSP_LST_DOCN");                      
			callableStatement.setString(1, gdc.getCodSocieta());
			callableStatement.setString(2, gdc.getCodUtente());
			callableStatement.setString(3, gdc.getCodEnte());
			callableStatement.setString(4, gdc.getProvenienza());
			if (gdc.getDataGiornaleDa()==null) {
				callableStatement.setTimestamp(5, null);
			} else {
				callableStatement.setTimestamp(5, new java.sql.Timestamp(gdc.getDataGiornaleDa().getTimeInMillis()));
			}
			if (gdc.getDataGiornaleA()==null) {
				callableStatement.setTimestamp(6, null);
			} else {
				callableStatement.setTimestamp(6, new java.sql.Timestamp(gdc.getDataGiornaleA().getTimeInMillis()));
			}
			
			callableStatement.setString(7, gdc.getIdFlusso());
	 
			/* we execute procedure */
			if(callableStatement.execute())	{		
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				numdocList = new NumeroDocumentoList(getWebRowSetXml());
				return numdocList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			numdocList = new NumeroDocumentoList("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			numdocList = new NumeroDocumentoList("");
		} catch (HelperException e) {
			e.printStackTrace();
			numdocList = new NumeroDocumentoList("");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return numdocList;	
	}
	
	public FlussoPageList ListFlusso(
			Flusso flusso,
			int rowsPerPage, int pageNumber, String order)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		FlussoPageList flussoPageList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMFSP_LST");                      
			callableStatement.setLong(1, flusso.getIdMdc());
			if (flusso.getDataFlussoDa()==null) {
				callableStatement.setTimestamp(2, null);
			} else {
				callableStatement.setTimestamp(2, new java.sql.Timestamp(flusso.getDataFlussoDa().getTimeInMillis()));
			}
			if (flusso.getDataFlussoA()==null) {
				callableStatement.setTimestamp(3, null);
			} else {
				callableStatement.setTimestamp(3, new java.sql.Timestamp(flusso.getDataFlussoA().getTimeInMillis()));
			}	
			callableStatement.setString(4, flusso.getFlusso());
			callableStatement.setString(5, flusso.getCodiceMittente());
			callableStatement.setBigDecimal(6, flusso.getImportoDa());
			callableStatement.setBigDecimal(7, flusso.getImportoA());

			callableStatement.setInt(8, pageNumber);                        
			callableStatement.setInt(9, rowsPerPage);
			callableStatement.setString(10, order == null ? "" : order);
			
			
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(11));
				pageInfo.setLastRow(callableStatement.getInt(12));
				pageInfo.setNumRows(callableStatement.getInt(13));
				pageInfo.setNumPages(callableStatement.getInt(14));
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				flussoPageList = new FlussoPageList(pageInfo, "00","",getWebRowSetXml());
				return flussoPageList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			flussoPageList = new FlussoPageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			flussoPageList = new FlussoPageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			flussoPageList = new FlussoPageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return flussoPageList;	
	}
	
	public TransazionePageList ListTransazione(
			Transazione transazione,
			int rowsPerPage, int pageNumber, String order)
			throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		PageInfo pageInfo = null;
		TransazionePageList transazionePageList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMTSP_LST");                      
			callableStatement.setLong(1, transazione.getIdMdc());
			if (transazione.getDataTransazioneDa()==null) {
				callableStatement.setTimestamp(2, null);
			} else {
				callableStatement.setTimestamp(2, new java.sql.Timestamp(transazione.getDataTransazioneDa().getTimeInMillis()));
			}
			if (transazione.getDataTransazioneA()==null) {
				callableStatement.setTimestamp(3, null);
			} else {
				callableStatement.setTimestamp(3, new java.sql.Timestamp(transazione.getDataTransazioneA().getTimeInMillis()));
			}	
			callableStatement.setString(4, transazione.getId());
			callableStatement.setString(5, transazione.getIuv());
			callableStatement.setString(6, transazione.getIur());
			callableStatement.setBigDecimal(7, transazione.getImportoDa());
			callableStatement.setBigDecimal(8, transazione.getImportoA());

			callableStatement.setInt(9, pageNumber);                        
			callableStatement.setInt(10, rowsPerPage);
			callableStatement.setString(11, order == null ? "" : order);
			
			
			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			 
			/* we execute procedure */
			if(callableStatement.execute())	{
				pageInfo = new PageInfo();
				pageInfo.setPageNumber(pageNumber);
				pageInfo.setRowsPerPage(rowsPerPage);
				pageInfo.setFirstRow(callableStatement.getInt(12));
				pageInfo.setLastRow(callableStatement.getInt(13));
				pageInfo.setNumRows(callableStatement.getInt(14));
				pageInfo.setNumPages(callableStatement.getInt(15));
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				transazionePageList = new TransazionePageList(pageInfo, "00","",getWebRowSetXml());
				return transazionePageList;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			transazionePageList = new TransazionePageList(pageInfo, "01","Sql-Exception","");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			transazionePageList = new TransazionePageList(pageInfo, "01","Sql-Exception","");
		} catch (HelperException e) {
			e.printStackTrace();
			transazionePageList = new TransazionePageList(pageInfo, "01","Sql-Exception","");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return transazionePageList;	
	}
	
	public void AggiungiFlusso(long idMdc, long idFlusso) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;

		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMFSP_INS");                      
			callableStatement.setLong(1, idMdc);
			callableStatement.setLong(2, idFlusso);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void EliminaFlusso(long idMdc, long idFlusso) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;

		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMFSP_DEL_QUN");                      
			callableStatement.setLong(1, idMdc);
			callableStatement.setLong(2, idFlusso);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void AggiungiTransazione(long idMdc, long idTransazione) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;

		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMTSP_INS");                      
			callableStatement.setLong(1, idMdc);
			callableStatement.setLong(2, idTransazione);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public void EliminaTransazione(long idMdc, long idTransazione) throws  DaoException{
		CallableStatement callableStatement=null;
		Connection connection = null;

		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYRMTSP_DEL_RPT");                     
			callableStatement.setLong(1, idMdc);
			callableStatement.setLong(2, idTransazione);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public MittenteList ListMittente(long idMdc) throws  DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;
		ResultSet data = null;
		MittenteList mittenteList = null;
		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYQUNSP_LST_MITT");                      
			callableStatement.setLong(1, idMdc);
			
			if(callableStatement.execute())	{
				
				data = callableStatement.getResultSet();
				loadWebRowSet(data);
				mittenteList = new MittenteList(getWebRowSetXml());
				return mittenteList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			mittenteList = new MittenteList("");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			mittenteList = new MittenteList("");
		} catch (HelperException e) {
			e.printStackTrace();
			mittenteList = new MittenteList("");
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return mittenteList;
	}
	
	public void RegolarizzaSospeso(long idMdc, String user, String nota) throws DaoException {
		CallableStatement callableStatement=null;
		Connection connection = null;

		try {			
			connection = getConnection();
			callableStatement = Helper.prepareCall(connection, getSchema(), "PYMDCSP_UPD_REND_DEF");                     
			callableStatement.setLong(1, idMdc);
			callableStatement.setString(2, user);
			callableStatement.setString(3, nota);
			callableStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (HelperException e) {
			e.printStackTrace();
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
	}
	
	public Connection getGDCConnection(){
		Connection connection = null;
		try {			
			connection = getConnection();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
