//da com.seda.payer.core.dao;
package com.seda.payer.core.dao;

//import com.seda.payer.riversamento.facade.logger.LogWriter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;

import javax.sql.rowset.WebRowSet;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;

//import javax.sql.rowset.WebRowSet;

//import com.ibm.icu.math.BigDecimal;
import com.seda.data.helper.HelperException;
//import com.seda.payer.core.bean.EccedenzaDetailPage;
//import com.seda.payer.core.bean.EccedenzaDettaglioBean;
import com.seda.payer.core.bean.AnagraficaPartitaRuolo;
import com.seda.payer.core.bean.RuoliArticoliPage;
import com.seda.payer.core.bean.RuoliPagamentiCSV;
import com.seda.payer.core.bean.RuoliPagamentiPage;
import com.seda.payer.core.bean.PartitaRuolo;
import com.seda.payer.core.bean.PartitePage;
import com.seda.payer.core.bean.RuoliAnagrafichePage;
import com.seda.payer.core.bean.RuoliPage;
import com.seda.payer.core.bean.Ruolo;
//import com.seda.payer.core.bean.EccedenzePage;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

//@SuppressWarnings("unchecked")
public class RuoliBancaDatiDao extends BaseDaoHandler {

/*	
	private final String PYRRUSP_STAT = "PYRRUSP_STAT";
	private final String PYRRUSP_LST = "PYRRUSP_LST";
	private final String PYRRUSP_S_SEL = "PYRRUSP_S_SEL";
	private final String PYRPASP_LST_RRU = "PYRPASP_LST_RRU";
	private final String PYRPASP_STAT_RRU = "PYRPASP_STAT_RRU";
	private final String PYRPASP_SEL = "PYRPASP_SEL";
	private final String PYRARSP_LST = "PYRARSP_LST";
	private final String PYRARSP_STAT = "PYRARSP_STAT";
	private final String PYRMOSP_LST_DET = "PYRMOSP_LST_DET";
	private final String PYRPASP_LST = "PYRPASP_LST";
	private final String PYRPASP_STAT = "PYRPASP_STAT";
	private final String PYRANSP_LST = "PYRANSP_LST";
	private final String PYRANSP_SEL = "PYRANSP_SEL";
	private final String PYRPASP_LST_ANA = "PYRPASP_LST_ANA";
	private final String PYRMOSP_LST = "PYRMOSP_LST";
	private final String PYRMOSP_STAT = "PYRMOSP_STAT";	
	private final String PYRMOSP_LST_CSV = "PYRMOSP_LST_CSV";
	private final String PYRLGSP_SEL_DATE = "PYRLGSP_SEL_DATE";
*/	

	public RuoliBancaDatiDao(Connection connection, String schema) {
		super(connection, schema);
	}

	public RuoliPage getListaRuoli(RuoliPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
/*
			IN I_PAGENO SMALLINT,
			IN I_ROWSPERPAGE SMALLINT,
			IN I_ORDER VARCHAR(64),
			IN I_RRU_CSOCCSOC CHAR(5),
			IN I_RRU_CUTECUTE CHAR(5),
			IN I_RRU_KANEKENT CHAR(10),
			IN I_RRU_CRRUTUFF CHAR(1),
			IN I_RRU_CRRUCUFF CHAR(6),
			IN I_RRU_NRRUANNO CHAR(4),
			IN I_RRU_NRRUNUME CHAR(6),

		    IN I_RRU_GRRUGRUO_DA CHAR(10), --data CONSEGNA da
		    IN I_RRU_GRRUGRUO_A CHAR(10), --data CONSEGNA a
			OUT O_ROWINI INTEGER,
			OUT O_ROWEND INTEGER,
			OUT O_TOTROWS INTEGER,
			OUT O_TOTPAGES SMALLINT        
*/			
			//callableStatement = prepareCall(PYECTSP_LST);		
//			CallableStatement callableStatement = prepareCall(PYRRUSP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRRUSP_LST.routine());
			callableStatement = prepareCall(Routines.PYRRUSP_LST.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getCodiceUfficio());
			callableStatement.setString(9, dto.getAnnoRuolo());
			callableStatement.setString(10, dto.getNumeroRuolo());
			callableStatement.setString(11, dto.getDataConsegneDa());
			callableStatement.setString(12, dto.getDataConsegneA());

			/* we register row start */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(16, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(13), callableStatement.getInt(14), 
								 callableStatement.getInt(15), callableStatement.getInt(16));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}
	
	public RuoliPage getStatisticheRuoli(RuoliPage dto) throws DaoException 
	{
		
		/*
	IN I_RRU_CSOCCSOC CHAR(5),
	IN I_RRU_CUTECUTE CHAR(5),
	IN I_RRU_KANEKENT CHAR(10),
	IN I_RRU_CRRUTUFF CHAR(1),
	IN I_RRU_CRRUCUFF CHAR(6),
	IN I_RRU_NRRUANNO CHAR(4),
	IN I_RRU_NRRUNUME CHAR(6),

    IN I_RRU_GRRUGRUO_DA CHAR(10), --data CONSEGNA da
    IN I_RRU_GRRUGRUO_A CHAR(10), --data CONSEGNA a
	OUT O_TOT_CAR DECIMAL(15 , 2),
	OUT O_TOT_DIM_CAR DECIMAL(15 , 2),
	OUT O_TOT_RISC DECIMAL(15 , 2),
	OUT O_TOT_RIMB DECIMAL(15 , 2),
	OUT O_TOT_RESIDUO DECIMAL(15 , 2)
		*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{

//			CallableStatement callableStatement = prepareCall(PYRRUSP_STAT);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRRUSP_STAT.routine());
			callableStatement = prepareCall(Routines.PYRRUSP_STAT.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setString(8, dto.getDataConsegneDa());
			callableStatement.setString(9, dto.getDataConsegneA());

		
			callableStatement.registerOutParameter(10,Types.DECIMAL);
			callableStatement.registerOutParameter(11,Types.DECIMAL);
			callableStatement.registerOutParameter(12,Types.DECIMAL);
			callableStatement.registerOutParameter(13,Types.DECIMAL);
			callableStatement.registerOutParameter(14,Types.DECIMAL);

			callableStatement.execute();

			dto.setTotcarico(callableStatement.getBigDecimal(10));
			dto.setTotdimcarico(callableStatement.getBigDecimal(11));
			dto.setTotriscosso(callableStatement.getBigDecimal(12));
			dto.setTotrimborso(callableStatement.getBigDecimal(13));
			dto.setTotresiduo(callableStatement.getBigDecimal(14));
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public Ruolo getDettaglioRuolo_S(Ruolo dto) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
			IN I_RRU_KRRUKFLU BIGINT,
			IN I_RRU_CSOCCSOC CHAR(5),
			IN I_RRU_CUTECUTE CHAR(5),
			IN I_RRU_KANEKENT CHAR(10),
			IN I_RRU_CRRUCAGE CHAR(3),
			IN I_RRU_NRRUANNO CHAR(4),
			IN I_RRU_NRRUNUME CHAR(6),
			IN I_RRU_CRRUTOMB CHAR(1)
		*/			
			//callableStatement = prepareCall(PYECTSP_LST);		
//			CallableStatement callableStatement = prepareCall(PYRRUSP_S_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRRUSP_S_SEL.routine());
			callableStatement = prepareCall(Routines.PYRRUSP_S_SEL.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setBigDecimal(1, new java.math.BigDecimal(dto.getProgrFlusso()));
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getConcessione());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setString(8, dto.getCodiceTomb());

			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}

	public Ruolo getDettaglioRuolo(Ruolo dto) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		/*
			IN I_RRU_KRRUKFLU BIGINT,
			IN I_RRU_CSOCCSOC CHAR(5),
			IN I_RRU_CUTECUTE CHAR(5),
			IN I_RRU_KANEKENT CHAR(10),
			IN I_RRU_CRRUCAGE CHAR(3),
			IN I_RRU_NRRUANNO CHAR(4),
			IN I_RRU_NRRUNUME CHAR(6),
			IN I_RRU_CRRUTOMB CHAR(1)
		*/			
//			CallableStatement callableStatement = prepareCall(PYRRUSP_S_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRRUSP_S_SEL.routine());
			callableStatement = prepareCall(Routines.PYRRUSP_S_SEL.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setBigDecimal(1, new java.math.BigDecimal(dto.getProgrFlusso()));
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getConcessione());
			callableStatement.setBigDecimal(6, new java.math.BigDecimal(dto.getAnnoRuolo()));
			callableStatement.setBigDecimal(7, new java.math.BigDecimal(dto.getNumeroRuolo()));
			callableStatement.setString(8, dto.getCodiceTomb());
/*
			RU_CSOCCSOC, RRU_CUTECUTE,ANE_CANECENT,RRU_NRRUANNO,RRU_NRRUNUME,RRU_CRRUTUFF,
			RRU_CRRUCUFF,RRU_IRRUICAR,RRU_IRRUDCAR,RRU_IRRURISC,RRU_IRRURIMB,
		 (RRU_IRRUICAR - RRU_IRRUDCAR - RRU_IRRURISC + RRU_IRRURIMB) RESIDUO,RRU_GRRUGMIN,
		 RRU_CRRUCMIN,RRU_GRRUGRUO,RRU_IRRUSCAR,RRU_IRRUMORA,RRU_IRRUVCAR,COUNT(RPA_NRPANPAR) NUM_PARTITE
*/			
			if(callableStatement.execute()){
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setAnnoRuolo(rs.getString(4));
					dto.setNumeroRuolo(rs.getString(5));
					dto.setTipoUfficio(rs.getString(6));
					dto.setCodiceUfficio(rs.getString(7));
					dto.setDataMinuta(rs.getDate(13)== null?"":format.format(rs.getDate(13)));
					dto.setNumeroMinuta(rs.getString(14));
					dto.setNumeroPartite(rs.getString(19));
					dto.setDataConsegna(rs.getDate(15)== null?"":format.format(rs.getDate(15)));
					dto.setTotcarico(rs.getBigDecimal(8));
					dto.setTotdimcarico(rs.getBigDecimal(9));
					dto.setTotsgravio(rs.getBigDecimal(16));
					dto.setTotriscosso(rs.getBigDecimal(10));
					dto.setTotmora(rs.getBigDecimal(17));
					dto.setTotvarcarico(rs.getBigDecimal(18));
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (rs != null) {
				try {
					rs.close();
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
		
		return dto;
	}
	
	public PartitePage getListaPartiteRuoli(PartitePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
			IN I_PAGENO SMALLINT,
			IN I_ROWSPERPAGE SMALLINT,
			IN I_ORDER VARCHAR(64),
			IN I_RRU_KRRUFLU VARCHAR(12),
			IN I_RPA_CSOCCSOC CHAR(5),
			IN I_RPA_CUTECUTE CHAR(5),
			IN I_RPA_KANEKENT CHAR(10),
			IN I_RRU_CRRUCAGE CHAR(3),
			IN I_RRU_CRRUTOMB CHAR(1),
			IN I_RPA_CRRUTUFF CHAR(1),
			IN I_RPA_CRRUCUFF CHAR(6),
			IN I_RPA_NRRUANNO CHAR(4),
			IN I_RPA_NRRUNUME CHAR(6),
			OUT O_ROWINI INTEGER,
			OUT O_ROWEND INTEGER,
			OUT O_TOTROWS INTEGER,
			OUT O_TOTPAGES SMALLINT        
		*/			
//			CallableStatement callableStatement = prepareCall(PYRPASP_LST_RRU);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_LST_RRU.routine());
			callableStatement = prepareCall(Routines.PYRPASP_LST_RRU.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getProgFlussoRuolo());
			callableStatement.setString(5, dto.getCodiceSocieta());
			callableStatement.setString(6, dto.getCodiceUtente());
			callableStatement.setString(7, dto.getCodiceEnte());
			callableStatement.setString(8, dto.getProgrConcessione());
			callableStatement.setString(9, dto.getCodiceTombRuolo());
			callableStatement.setString(10, dto.getTipoUfficio());
			callableStatement.setString(11, dto.getCodiceUfficio());
			callableStatement.setString(12, dto.getAnnoRuolo());
			callableStatement.setString(13, dto.getNumeroRuolo());

			/* we register row start */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(15, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(16, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(17, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(14), callableStatement.getInt(15), 
								 callableStatement.getInt(16), callableStatement.getInt(17));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}
	
	public PartitePage getStatistichePartiteRuoli(PartitePage dto) throws DaoException 
	{
		
		/*
	IN I_RRU_KRRUFLU VARCHAR(12),
	IN I_RPA_CSOCCSOC CHAR(5),
	IN I_RPA_CUTECUTE CHAR(5),
	IN I_RPA_KANEKENT CHAR(10),
	IN I_RRU_CRRUCAGE CHAR(3),
	IN I_RRU_CRRUTOMB CHAR(1),
	IN I_RPA_CRRUTUFF CHAR(1),
	IN I_RPA_CRRUCUFF CHAR(6),
	IN I_RPA_NRRUANNO CHAR(4),
	IN I_RPA_NRRUNUME CHAR(6),
	OUT O_TOT_CAR DECIMAL(15 , 2),
	OUT O_TOT_DIM_CAR DECIMAL(15 , 2),
	OUT O_TOT_RISC DECIMAL(15 , 2),
	OUT O_TOT_RIMB DECIMAL(15 , 2),
	OUT O_TOT_RESIDUO DECIMAL(15 , 2)
		*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{

//			CallableStatement callableStatement = prepareCall(PYRPASP_STAT_RRU);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_STAT_RRU.routine());
			callableStatement = prepareCall(Routines.PYRPASP_STAT_RRU.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getProgFlussoRuolo());
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getProgrConcessione());
			callableStatement.setString(6, dto.getCodiceTombRuolo());
			callableStatement.setString(7, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getCodiceUfficio());
			callableStatement.setString(9, dto.getAnnoRuolo());
			callableStatement.setString(10, dto.getNumeroRuolo());
			
			callableStatement.registerOutParameter(11,Types.DECIMAL);
			callableStatement.registerOutParameter(12,Types.DECIMAL);
			callableStatement.registerOutParameter(13,Types.DECIMAL);
			callableStatement.registerOutParameter(14,Types.DECIMAL);
			callableStatement.registerOutParameter(15,Types.DECIMAL);
			
			callableStatement.execute();

			dto.setTotcarico(callableStatement.getBigDecimal(11));
			dto.setTotdimcarico(callableStatement.getBigDecimal(12));
			dto.setTotriscosso(callableStatement.getBigDecimal(13));
			dto.setTotrimborso(callableStatement.getBigDecimal(14));
			dto.setTotresiduo(callableStatement.getBigDecimal(15));
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public PartitaRuolo getDettaglioPartita_S(PartitaRuolo dto) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
			IN I_RPA_KRPAKFLU BIGINT,
			IN I_RPA_CSOCCSOC CHAR(5),
			IN I_RPA_CUTECUTE CHAR(5),
			IN I_RPA_KANEKENT CHAR(10),
			IN I_RPA_CRPACAGE CHAR(3),
			IN I_RPA_NRPAANNO DECIMAL(4,0),
			IN I_RPA_NRPANUME DECIMAL(6,0),
			IN I_RPA_NRPANPAR BIGINT,
			IN I_RPA_CRPATOMB CHAR(1)
		*/			
			//callableStatement = prepareCall(PYECTSP_LST);		
//			CallableStatement callableStatement = prepareCall(PYRPASP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_SEL.routine());
			callableStatement = prepareCall(Routines.PYRPASP_SEL.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setBigDecimal(1, new java.math.BigDecimal(dto.getProgrFlusso()));
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getConcessione());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setBigDecimal(8, new java.math.BigDecimal(dto.getCodicePartita()));
			callableStatement.setString(9, dto.getCodiceTomb());

			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}

	public PartitaRuolo getDettaglioPartita(PartitaRuolo dto) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		/*
			IN I_RRU_KRRUKFLU BIGINT,
			IN I_RRU_CSOCCSOC CHAR(5),
			IN I_RRU_CUTECUTE CHAR(5),
			IN I_RRU_KANEKENT CHAR(10),
			IN I_RRU_CRRUCAGE CHAR(3),
			IN I_RRU_NRRUANNO CHAR(4),
			IN I_RRU_NRRUNUME CHAR(6),
			IN I_RRU_CRRUTOMB CHAR(1)
		*/			
//			CallableStatement callableStatement = prepareCall(PYRPASP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_SEL.routine());
			callableStatement = prepareCall(Routines.PYRPASP_SEL.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setBigDecimal(1, new java.math.BigDecimal(dto.getProgrFlusso()));
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getConcessione());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setBigDecimal(8, new java.math.BigDecimal(dto.getCodicePartita()));
			callableStatement.setString(9, dto.getCodiceTomb());
/*
			RPA_CSOCCSOC,RPA_CUTECUTE,ANE_CANECENT,RAR_CRARCCAR,RPA_NRPAANNO,RPA_NRPANUME,
RRU_CRRUTUFF,RRU_CRRUCUFF,RPA_CRPACFIS,RPA_CRPACIDE,
SUM(RAR_IRARICAR) CARICO, SUM(RAR_IRARDCAR) DIM_CARICO,
SUM(RAR_IRARRISC) RISCOSSO, SUM(RAR_IRARRIMB) RIMBORSO, 
SUM(RAR_IRARRESI) RESIDUO,RRU_GRRUGRUO
*/			
			if(callableStatement.execute()){
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setAnnoRuolo(rs.getString(5));
					dto.setNumeroRuolo(rs.getString(6));
					dto.setTipoUfficio(rs.getString(7));
					dto.setCodiceUfficio(rs.getString(8));
					dto.setCodiceFiscale(rs.getString(9));
					dto.setDataConsegna(rs.getDate(16)== null?"":format.format(rs.getDate(16)));
					dto.setCodIdPartita(rs.getString(10));
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (rs != null) {
				try {
					rs.close();
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
		
		return dto;
	}
	
	public RuoliArticoliPage getListaArticoli(RuoliArticoliPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
		IN I_PAGENO SMALLINT,
		IN I_ROWSPERPAGE SMALLINT,
		IN I_ORDER VARCHAR(64),
		IN I_RAR_KRARKFLU VARCHAR(12),
		IN I_RAR_CSOCCSOC CHAR(5),
		IN I_RAR_CUTECUTE CHAR(5),
		IN I_RAR_KANEKENT CHAR(10),
		IN I_RAR_CRARCAGE CHAR(3),
		IN I_RAR_NRRAANNO CHAR(4),
		IN I_RAR_NRRANUME CHAR(6),
		IN I_RAR_NRARNPAR VARCHAR(12),
		OUT O_ROWINI INTEGER,
		OUT O_ROWEND INTEGER,
		OUT O_TOTROWS INTEGER,
		OUT O_TOTPAGES SMALLINT        
		*/			
//			CallableStatement callableStatement = prepareCall(PYRARSP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRARSP_LST.routine());
			callableStatement = prepareCall(Routines.PYRARSP_LST.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getProgFlussoRuolo());
			callableStatement.setString(5, dto.getCodiceSocieta());
			callableStatement.setString(6, dto.getCodiceUtente());
			callableStatement.setString(7, dto.getCodiceEnte());
			callableStatement.setString(8, dto.getProgrConcessione());
			callableStatement.setString(9, dto.getAnnoRuolo());
			callableStatement.setString(10, dto.getNumeroRuolo());
			callableStatement.setString(11, dto.getProgrPartita());

			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}
	
	public RuoliArticoliPage getStatisticheArticoli(RuoliArticoliPage dto) throws DaoException 
	{
		
		/*
		IN I_RAR_KRARKFLU VARCHAR(12),
		IN I_RAR_CSOCCSOC CHAR(5),
		IN I_RAR_CUTECUTE CHAR(5),
		IN I_RAR_KANEKENT CHAR(10),
		IN I_RAR_CRARCAGE CHAR(3),
		IN I_RAR_NRRAANNO CHAR(4),
		IN I_RAR_NRRANUME CHAR(6),
		IN I_RAR_NRARNPAR VARCHAR(12),
		OUT O_TOT_CAR DECIMAL(15 , 2),
		OUT O_TOT_DIM_CAR DECIMAL(15 , 2),
		OUT O_TOT_RISC DECIMAL(15 , 2),
		OUT O_TOT_RIMB DECIMAL(15 , 2),
		OUT O_TOT_MORA DECIMAL(15 , 2),
		OUT O_TOT_RESIDUO DECIMAL(15 , 2)
		*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{

//			CallableStatement callableStatement = prepareCall(PYRARSP_STAT);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRARSP_STAT.routine());
			callableStatement = prepareCall(Routines.PYRARSP_STAT.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getProgFlussoRuolo());
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getProgrConcessione());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setString(8, dto.getProgrPartita());
			
			callableStatement.registerOutParameter(9,Types.DECIMAL);
			callableStatement.registerOutParameter(10,Types.DECIMAL);
			callableStatement.registerOutParameter(11,Types.DECIMAL);
			callableStatement.registerOutParameter(12,Types.DECIMAL);
			callableStatement.registerOutParameter(13,Types.DECIMAL);
			callableStatement.registerOutParameter(14,Types.DECIMAL);
			
			callableStatement.execute();

			dto.setTotcarico(callableStatement.getBigDecimal(9));
			dto.setTotdimcarico(callableStatement.getBigDecimal(10));
			dto.setTotriscosso(callableStatement.getBigDecimal(11));
			dto.setTotrimborso(callableStatement.getBigDecimal(12));
			dto.setTotmora(callableStatement.getBigDecimal(13));
			dto.setTotresiduo(callableStatement.getBigDecimal(14));
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}

	public RuoliPagamentiPage getListaPagamentiDettaglio(RuoliPagamentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
		IN I_PAGENO SMALLINT,
		IN I_ROWSPERPAGE SMALLINT,
		IN I_ORDER VARCHAR(64),
		IN I_RAR_KRARKFLU VARCHAR(12),
		IN I_RAR_CSOCCSOC CHAR(5),
		IN I_RAR_CUTECUTE CHAR(5),
		IN I_RAR_KANEKENT CHAR(10),
		IN I_RAR_CRARCAGE CHAR(3),
		IN I_RAR_NRRAANNO CHAR(4),
		IN I_RAR_NRRANUME CHAR(6),
		IN I_RAR_NRARNPAR VARCHAR(12),
		OUT O_ROWINI INTEGER,
		OUT O_ROWEND INTEGER,
		OUT O_TOTROWS INTEGER,
		OUT O_TOTPAGES SMALLINT        
		*/			
//			CallableStatement callableStatement = prepareCall(PYRMOSP_LST_DET);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRMOSP_LST_DET.routine());
			callableStatement = prepareCall(Routines.PYRMOSP_LST_DET.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getProgFlussoRuolo());
			callableStatement.setString(5, dto.getCodiceSocieta());
			callableStatement.setString(6, dto.getCodiceUtente());
			callableStatement.setString(7, dto.getCodiceEnte());
			callableStatement.setString(8, dto.getProgrConcessione());
			callableStatement.setString(9, dto.getAnnoRuolo());
			callableStatement.setString(10, dto.getNumeroRuolo());
			callableStatement.setString(11, dto.getProgrPartita());

			/* we register row start */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(14, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(15, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(12), callableStatement.getInt(13), 
								 callableStatement.getInt(14), callableStatement.getInt(15));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}
	
	public PartitePage getListaPartite(PartitePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
		IN I_PAGENO SMALLINT,
		IN I_ROWSPERPAGE SMALLINT,
		IN I_ORDER VARCHAR(64),
		IN I_RPA_CSOCCSOC CHAR(5),
		IN I_RPA_CUTECUTE CHAR(5),
		IN I_RPA_KANEKENT CHAR(10),
		IN I_RRU_CRRUTUFF CHAR(1),
		IN I_RRU_CRRUCUFF CHAR(6),
		IN I_RPA_NRPAANNO CHAR(4),
		IN I_RPA_NRPANUME CHAR(6),
	    IN I_RRU_GRRUGRUO_DA CHAR(10), --data CONSEGNA da
	    IN I_RRU_GRRUGRUO_A CHAR(10), --data CONSEGNA a
		IN I_RPA_CRPACFIS VARCHAR(16),
		IN I_RPA_NRPANPAR VARCHAR(12),
		IN I_RAR_CRARCCAR VARCHAR(20),
		IN I_STAT_PART CHAR(1),
		IN I_FLAG_CART CHAR(1),
		OUT O_ROWINI INTEGER,
		OUT O_ROWEND INTEGER,
		OUT O_TOTROWS INTEGER,
		OUT O_TOTPAGES SMALLINT        
		*/			
//			CallableStatement callableStatement = prepareCall(PYRPASP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_LST.routine());
			callableStatement = prepareCall(Routines.PYRPASP_LST.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getCodiceUfficio());
			callableStatement.setString(9, dto.getAnnoRuolo());
			callableStatement.setString(10, dto.getNumeroRuolo());
			callableStatement.setString(11, dto.getDataConsegneDa());
			callableStatement.setString(12, dto.getDataConsegneA());
			callableStatement.setString(13, dto.getCodiceFiscale());
			callableStatement.setString(14, dto.getProgrPartita());
			callableStatement.setString(15, dto.getCodiceCartella());
			callableStatement.setString(16, dto.getStatoPartita());
			callableStatement.setString(17, dto.getFlagCartellazione());

			/* we register row start */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(19, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(20, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(21, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(18), callableStatement.getInt(19), 
								 callableStatement.getInt(20), callableStatement.getInt(21));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}
	
	public PartitePage getStatistichePartite(PartitePage dto) throws DaoException 
	{
		
		/*
		IN I_RPA_CSOCCSOC CHAR(5),
		IN I_RPA_CUTECUTE CHAR(5),
		IN I_RPA_KANEKENT CHAR(10),
		IN I_RRU_CRRUTUFF CHAR(1),
		IN I_RRU_CRRUCUFF CHAR(6),
		IN I_RPA_NRPAANNO CHAR(4),
		IN I_RPA_NRPANUME CHAR(6),
	    IN I_RRU_GRRUGRUO_DA CHAR(10), --data CONSEGNA da
	    IN I_RRU_GRRUGRUO_A CHAR(10), --data CONSEGNA a
		IN I_RPA_CRPACFIS VARCHAR(16),
		IN I_RPA_NRPANPAR VARCHAR(12),
		IN I_RAR_CRARCCAR VARCHAR(20),
		IN I_STAT_PART CHAR(1),
		IN I_FLAG_CART CHAR(1),
		OUT O_TOT_CAR DECIMAL(15 , 2),
		OUT O_TOT_DIM_CAR DECIMAL(15 , 2),
		OUT O_TOT_RISC DECIMAL(15 , 2),
		OUT O_TOT_RIMB DECIMAL(15 , 2),
		OUT O_TOT_RESIDUO DECIMAL(15 , 2)
		*/
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{

//			CallableStatement callableStatement = prepareCall(PYRPASP_STAT);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_STAT.routine());
			callableStatement = prepareCall(Routines.PYRPASP_STAT.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());
			callableStatement.setString(4, dto.getTipoUfficio());
			callableStatement.setString(5, dto.getCodiceUfficio());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setString(8, dto.getDataConsegneDa());
			callableStatement.setString(9, dto.getDataConsegneA());
			callableStatement.setString(10, dto.getCodiceFiscale());
			callableStatement.setString(11, dto.getProgrPartita());
			callableStatement.setString(12, dto.getCodiceCartella());
			callableStatement.setString(13, dto.getStatoPartita());
			callableStatement.setString(14, dto.getFlagCartellazione());
			
			callableStatement.registerOutParameter(15,Types.DECIMAL);
			callableStatement.registerOutParameter(16,Types.DECIMAL);
			callableStatement.registerOutParameter(17,Types.DECIMAL);
			callableStatement.registerOutParameter(18,Types.DECIMAL);
			callableStatement.registerOutParameter(19,Types.DECIMAL);
			
			callableStatement.execute();

			dto.setTotcarico(callableStatement.getBigDecimal(15));
			dto.setTotdimcarico(callableStatement.getBigDecimal(16));
			dto.setTotriscosso(callableStatement.getBigDecimal(17));
			dto.setTotrimborso(callableStatement.getBigDecimal(18));
			dto.setTotresiduo(callableStatement.getBigDecimal(19));
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}
	
	public RuoliAnagrafichePage getListaAnagrafiche(RuoliAnagrafichePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		/*
		IN I_PAGENO SMALLINT,
		IN I_ROWSPERPAGE SMALLINT,
		IN I_ORDER VARCHAR(64),
		IN I_RAN_CSOCCSOC CHAR(5),
		IN I_RAN_CUTECUTE CHAR(5),
		IN I_RAN_KANEKENT CHAR(10),
		IN I_RAN_CRANCFIS CHAR(16),
		IN I_RAN_DRANDENO VARCHAR(50),
		IN I_TIPO_RIC CHAR(1),
		OUT O_ROWINI INTEGER,
		OUT O_ROWEND INTEGER,
		OUT O_TOTROWS INTEGER,
		OUT O_TOTPAGES SMALLINT        
*/
		
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
//			CallableStatement callableStatement = prepareCall(PYRANSP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRANSP_LST.routine());
			callableStatement = prepareCall(Routines.PYRANSP_LST.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getCodiceFiscale());
			callableStatement.setString(8, dto.getDenominazione());
			callableStatement.setString(9, dto.getTipoRic());
			/* we register row start */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(13, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(10), callableStatement.getInt(11), 
								 callableStatement.getInt(12), callableStatement.getInt(13));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}

	public AnagraficaPartitaRuolo getDettaglioAnagrafica_S(AnagraficaPartitaRuolo dto) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
			IN I_RAN_KRANKFLU BIGINT,
			IN I_RAN_CSOCCSOC CHAR(5),
			IN I_RAN_CUTECUTE CHAR(5),
			IN I_RAN_KANEKENT CHAR(10),
			IN I_RAN_CRANCAGE CHAR(3),
			IN I_RAN_CRANCFIS DECIMAL(4,0),
			IN I_RAN_CRANTOMB CHAR(1)
		*/			
//			CallableStatement callableStatement = prepareCall(PYRANSP_SEL);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRANSP_SEL.routine());
			callableStatement = prepareCall(Routines.PYRANSP_SEL.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setBigDecimal(1, new java.math.BigDecimal(dto.getProgrFlusso()));
			callableStatement.setString(2, dto.getCodiceSocieta());
			callableStatement.setString(3, dto.getCodiceUtente());
			callableStatement.setString(4, dto.getCodiceEnte());
			callableStatement.setString(5, dto.getConcessione());
			callableStatement.setString(6, dto.getCodiceFiscale());
			callableStatement.setString(7, dto.getCodiceTomb());

			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}
	
	public PartitePage getListaPartiteAnagrafiche(PartitePage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
			IN I_PAGENO SMALLINT,
			IN I_ROWSPERPAGE SMALLINT,
			IN I_ORDER VARCHAR(64),
			IN I_RAN_KRRAKFLU VARCHAR(12),
			IN I_RAN_CSOCCSOC CHAR(5),
			IN I_RAN_CUTECUTE CHAR(5),
			IN I_RAN_KANEKENT CHAR(10),
			IN I_RAN_CRANCAGE CHAR(3),
			IN I_RAN_CRANCFIS VARCHAR(16),
			IN I_RAN_CRANTOMB CHAR(1),
			OUT O_ROWINI INTEGER,
			OUT O_ROWEND INTEGER,
			OUT O_TOTROWS INTEGER,
			OUT O_TOTPAGES SMALLINT        
		*/			
//			CallableStatement callableStatement = prepareCall(PYRPASP_LST_ANA);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRPASP_LST_ANA.routine());
			callableStatement = prepareCall(Routines.PYRPASP_LST_ANA.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getProgFlussoRuolo());
			callableStatement.setString(5, dto.getCodiceSocieta());
			callableStatement.setString(6, dto.getCodiceUtente());
			callableStatement.setString(7, dto.getCodiceEnte());
			callableStatement.setString(8, dto.getProgrConcessione());
			callableStatement.setString(9, dto.getCodiceFiscale());
			callableStatement.setString(10, dto.getCodiceTombRuolo());
			
			/* we register row start */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(12, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(13, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(14, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(11), callableStatement.getInt(12), 
								 callableStatement.getInt(13), callableStatement.getInt(14));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		return dto;
	}
	
	public RuoliPagamentiPage getListaPagamenti(RuoliPagamentiPage dto, String ordine, int rowsPerPage, int pageNumber) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
		/*
		IN I_PAGENO SMALLINT,
		IN I_ROWSPERPAGE SMALLINT,
		IN I_ORDER VARCHAR(64),
		IN I_RMO_CSOCCSOC CHAR(5),
		IN I_RMO_CUTECUTE CHAR(5),
		IN I_RMO_KANEKENT CHAR(10),
		IN I_RRU_CRRUTUFF CHAR(1),
		IN I_RRU_CRRUCUFF CHAR(6),
		IN I_RMO_CRMOANNO CHAR(4),
		IN I_RMO_NRMONUME CHAR(6),
		IN I_RPA_CRPACFIS VARCHAR(16),
		IN I_RMO_NRMONPAR VARCHAR(12), 
	    IN I_RMO_GRMOGEFF_DA CHAR(10), --data REGISTRAZIONE PAG da
	    IN I_RMO_GRMOGEFF_A CHAR(10), --data REGISTRAZIONE PAG a
	    IN I_RMO_GRMOGREG_DA CHAR(10), --data EFFETTIVA PAG da
	    IN I_RMO_GRMOGREG_A CHAR(10), --data EFFETTIVA PAG a
		OUT O_ROWINI INTEGER,
		OUT O_ROWEND INTEGER,
		OUT O_TOTROWS INTEGER,
		OUT O_TOTPAGES SMALLINT        
		*/			
			//callableStatement = prepareCall(PYECTSP_LST);		
//			CallableStatement callableStatement = prepareCall(PYRMOSP_LST);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRMOSP_LST.routine());
			callableStatement = prepareCall(Routines.PYRMOSP_LST.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setInt(1, pageNumber);
			callableStatement.setInt(2, rowsPerPage);
			callableStatement.setString(3, ordine);
			callableStatement.setString(4, dto.getCodiceSocieta());
			callableStatement.setString(5, dto.getCodiceUtente());
			callableStatement.setString(6, dto.getCodiceEnte());
			callableStatement.setString(7, dto.getCodiceUfficio());
			callableStatement.setString(8, dto.getTipoUfficio());
			callableStatement.setString(8, dto.getProgrConcessione());
			callableStatement.setString(9, dto.getAnnoRuolo());
			callableStatement.setString(10, dto.getNumeroRuolo());
			callableStatement.setString(11, dto.getCodiceFiscale());
			callableStatement.setString(12, dto.getProgrPartita());
			callableStatement.setString(13, dto.getDataRegPagDa());
			callableStatement.setString(14, dto.getDataRegPagA());
			callableStatement.setString(15, dto.getDataEffPagDa());
			callableStatement.setString(16, dto.getDataEffPagA());

			/* we register row start */
			callableStatement.registerOutParameter(17, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(18, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(19, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(20, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) 
			{
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(17), callableStatement.getInt(18), 
								 callableStatement.getInt(19), callableStatement.getInt(20));
				dto.setPageInfo(getPageInfo());
				dto.setListXml(getWebRowSetXml(EntrateBancaDatiDao.IDX_DOLIST_LISTA));
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (callableStatement != null) {
				try {
					callableStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		
		return dto;
	}

	public RuoliPagamentiPage getTotaliPagamenti(RuoliPagamentiPage dto) throws DaoException 
	{
		//CallableStatement callableStatement = null;
		/*
			IN I_RMO_CSOCCSOC CHAR(5),
			IN I_RMO_CUTECUTE CHAR(5),
			IN I_RMO_KANEKENT CHAR(10),
			IN I_RRU_CRRUTUFF CHAR(1),
			IN I_RRU_CRRUCUFF CHAR(6),
			IN I_RMO_CRMOANNO CHAR(4),
			IN I_RMO_NRMONUME CHAR(6),
			IN I_RPA_CRPACFIS VARCHAR(16),
			IN I_RMO_NRMONPAR VARCHAR(12), 
		    IN I_RMO_GRMOGEFF_DA CHAR(10), --data REGISTRAZIONE PAG da
		    IN I_RMO_GRMOGEFF_A CHAR(10), --data REGISTRAZIONE PAG a
		    IN I_RMO_GRMOGREG_DA CHAR(10), --data EFFETTIVA PAG da
		    IN I_RMO_GRMOGREG_A CHAR(10) --data EFFETTIVA PAG a
		 */
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		ResultSet rs = null;
		//fine LP PG21XX04 Leak

		try	{
//			CallableStatement callableStatement = prepareCall(PYRMOSP_STAT);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRMOSP_STAT.routine());
			callableStatement = prepareCall(Routines.PYRMOSP_STAT.routine());
			//fine LP PG21XX04 Leak

			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());
			callableStatement.setString(4, dto.getCodiceUfficio());
			callableStatement.setString(5, dto.getTipoUfficio());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setString(8, dto.getCodiceFiscale());
			callableStatement.setString(9, dto.getProgrPartita());
			callableStatement.setString(10, dto.getDataRegPagDa());
			callableStatement.setString(11, dto.getDataRegPagA());
			callableStatement.setString(12, dto.getDataEffPagDa());
			callableStatement.setString(13, dto.getDataEffPagA());
			
			if (callableStatement.execute()) 
			{
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					dto.setTotimporto(rs.getBigDecimal(1));
					dto.setTotmora(rs.getBigDecimal(2));
				}
			}
		} catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		} finally {
			//inizio LP PG21XX04 Leak
			if (rs != null) {
				try {
					rs.close();
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
		
		return dto;
	}
	
	public WebRowSet estraiPagamentiCsv(RuoliPagamentiCSV  dto) throws DaoException	
	{	
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		ResultSet data = null;
		WebRowSet rowSet = null;
		try {
			
//			CallableStatement callableStatement = prepareCall(PYRMOSP_LST_CSV);		
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRMOSP_LST_CSV.routine());
			callableStatement = prepareCall(Routines.PYRMOSP_LST_CSV.routine());
			//fine LP PG21XX04 Leak
			
			callableStatement.setString(1, dto.getCodiceSocieta());
			callableStatement.setString(2, dto.getCodiceUtente());
			callableStatement.setString(3, dto.getCodiceEnte());
			callableStatement.setString(4, dto.getCodiceUfficio());
			callableStatement.setString(5, dto.getTipoUfficio());
			callableStatement.setString(6, dto.getAnnoRuolo());
			callableStatement.setString(7, dto.getNumeroRuolo());
			callableStatement.setString(8, dto.getCodiceFiscale());
			callableStatement.setString(9, dto.getProgrPartita());
			callableStatement.setString(10, dto.getDataRegistrazioneDa());
			callableStatement.setString(11, dto.getDataRegistrazioneA());
			callableStatement.setString(12, dto.getDataEffettivaDa());
			callableStatement.setString(13, dto.getDataEffettivaA());
			//inizio LP 20240811 - PGNTCORE-24
			//data = callableStatement.executeQuery()
			if(callableStatement.execute()) {
				data = callableStatement.getResultSet();
				if(data != null) {
			//fine LP 20240811 - PGNTCORE-24
					if (data != null) {
						loadWebRowSet(data);
						rowSet = getWebRowSet();
					}
			//inizio LP 20240811 - PGNTCORE-24
				}
			}
			//fine LP 20240811 - PGNTCORE-24
	} 
	catch (SQLException x) {
		throw new DaoException(x);
	} catch (IllegalArgumentException x) {
		throw new DaoException(x);
	} catch (HelperException x) {
		throw new DaoException(x);
	} 
	//inizio LP PG21XX04 Leak
	//finally {}
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
		
	return rowSet;
}
	
	public java.sql.Date getDataUltimoAggiornamento(String codiceSocieta,String codiceUtente,String codiceEnte) throws DaoException 
	{
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{

//			CallableStatement callableStatement = prepareCall(PYRLGSP_SEL_DATE);
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.PYRLGSP_SEL_DATE.routine());
			callableStatement = prepareCall(Routines.PYRLGSP_SEL_DATE.routine());
			//fine LP PG21XX04 Leak
			//callableStatement = prepareCall(PYECTSP_LST);		
			callableStatement.setString(1, codiceSocieta);
			callableStatement.setString(2, codiceUtente);
			callableStatement.setString(3, codiceEnte);
			
			/* we execute procedure */
			if (callableStatement.execute()) 
			{				
				//inizio LP PG21XX04 Leak
				//callableStatement.getResultSet().next();
				//return callableStatement.getResultSet().getDate(1);
				data = callableStatement.getResultSet();
				if(data.next()) {
					return data.getDate(1);
				}
				//fine LP PG21XX04 Leak
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

}