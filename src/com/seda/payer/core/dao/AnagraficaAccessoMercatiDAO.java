package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seda.data.helper.HelperException;
import com.seda.payer.core.bean.AnagraficaAccessoMercati;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;

public class AnagraficaAccessoMercatiDAO extends BaseDaoHandler {

	public AnagraficaAccessoMercatiDAO(Connection connection, String schema) {
		super(connection, schema);
	}
	public AnagraficaAccessoMercati getAnagraficaAccesso( String codSocieta, String codUt, String codEnte, String codiceFiscale) throws Exception{
		if(codiceFiscale == null || codiceFiscale.equals("") || codUt == null || codUt.equals("") || codSocieta == null || codSocieta.equals("") || codEnte == null || codEnte.equals("")){
			throw new IllegalArgumentException("codiceFiscale, codUt, codSocieta, codEnte sono richiesti");
		}
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = null;
			//fine LP PG21XX04 Leak

			callableStatement = prepareCall(Routines.PYACMSP_SEL_CF.routine());

			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUt);
			callableStatement.setString(3, codEnte);
			callableStatement.setString(4, codiceFiscale);

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next()){
					AnagraficaAccessoMercati anagrafica = new AnagraficaAccessoMercati(data.getString(1), data.getString(2), data.getString(3), data.getString(4), data.getString(6), data.getString(5), data.getString(7), data.getFloat(8),null);
					Borsellino borsellino= recuperaBorsellino(codSocieta, codUt, codEnte, codiceFiscale, anagrafica.getTelefono(), anagrafica.getEmail(), anagrafica.getImportoMinimo());
					if(!anagrafica.getCodiceBorsellino().equals(borsellino.getChiaveBorsellino())){
						System.out.println("ATTENZIONE - CHIAVE BORSELLINO NON CORRISPONDE! BORSELLINO ASSOCIATO A CF: "+ borsellino.getChiaveBorsellino());
					}
					anagrafica.setResiduoBorsellino(borsellino.getResiduoBorsellino());
					return anagrafica;
				}
			}
			return null;
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		} catch (HelperException x) {
			throw new Exception(x);
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
			}
		}
		//fine LP PG21XX04 Leak
	}
	
	public List<String> getAutorizzazioneList(String codSocieta, String codUt, String codEnte, String codiceFiscale) throws Exception{
		if(codiceFiscale == null || codiceFiscale.equals("") || codUt == null || codUt.equals("") || codSocieta == null || codSocieta.equals("") || codEnte == null || codEnte.equals("")){
			throw new IllegalArgumentException("codiceFiscale, codUt, codSocieta, codEnte sono richiesti");
		}
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		List<String> autorizzazioni = new ArrayList<String>();
		try	{
			callableStatement = prepareCall(Routines.PYAUTSP_TOT.routine());

			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUt);
			callableStatement.setString(3, codEnte);
			callableStatement.setString(4, codiceFiscale);
			callableStatement.setString(5, "");
			callableStatement.setString(6, "");
			
			
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next()){
					autorizzazioni.add( data.getString(1));
				}
			}
			return autorizzazioni;
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		} catch (HelperException x) {
			throw new Exception(x);
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
			}
		}
		//fine LP PG21XX04 Leak
	}
		
	public AnagraficaAccessoMercati insertAnagraficaAccesso(AnagraficaAccessoMercati anagrafica) throws Exception{
		AnagraficaAccessoMercati anagraficaout = new AnagraficaAccessoMercati();
		
		if(anagrafica.getCodSocieta() == null || anagrafica.getCodSocieta().equals("") || anagrafica.getCodUt() == null || anagrafica.getCodUt().equals("") || anagrafica.getCodEnte() == null || anagrafica.getCodEnte().equals("") || anagrafica.getCodiceFiscale() == null || anagrafica.getCodiceFiscale().equals("")){
			throw new IllegalArgumentException("codiceFiscale, codUt, codSocieta, codEnte sono richiesti");
		}
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try	{
			Borsellino borsellino= recuperaBorsellino(anagrafica.getCodSocieta(), anagrafica.getCodUt(), anagrafica.getCodEnte(), anagrafica.getCodiceFiscale(), anagrafica.getTelefono(), anagrafica.getEmail(), anagrafica.getImportoMinimo());
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = null;
			//fine LP PG21XX04 Leak
			callableStatement = prepareCall(Routines.PYACMSP_INS.routine());
			callableStatement.setString(1, anagrafica.getCodSocieta());
			callableStatement.setString(2, anagrafica.getCodUt());
			callableStatement.setString(3, anagrafica.getCodEnte());
			callableStatement.setString(4, anagrafica.getCodiceFiscale());
			callableStatement.setString(5, anagrafica.getEmail()==null?"":anagrafica.getEmail());
			callableStatement.setString(6, borsellino.getChiaveBorsellino());
			callableStatement.setString(7, anagrafica.getTelefono()==null?"":anagrafica.getTelefono());
			callableStatement.setFloat(8, anagrafica.getImportoMinimo());
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(10, java.sql.Types.VARCHAR);
			
			if(callableStatement.execute()){
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if(rs.next()){
					anagraficaout.setCodSocieta(rs.getString(1));
					anagraficaout.setCodUt(rs.getString(2));
					anagraficaout.setCodEnte(rs.getString(3));
					anagraficaout.setCodiceFiscale(rs.getString(4));
					anagraficaout.setEmail(rs.getString(5));
					anagraficaout.setCodiceBorsellino(rs.getString(6));
					anagraficaout.setTelefono(rs.getString(7));
					anagraficaout.setImportoMinimo(rs.getFloat(8));
					anagraficaout.setResiduoBorsellino(borsellino.getResiduoBorsellino());
				}
			}
			if (!callableStatement.getString(9).equals("OK")){
				throw new Exception(callableStatement.getString(10));
			}
			return anagraficaout;
		} catch (SQLException x) {
			throw new Exception(x);
		} catch (IllegalArgumentException x) {
			throw new Exception(x);
		} catch (HelperException x) {
			throw new Exception(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(rs != null) {
				try {
					rs.close();
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
		}
		//fine LP PG21XX04 Leak
	}

	public AnagraficaAccessoMercati updateAnagraficaAccesso(AnagraficaAccessoMercati anagrafica) throws Exception{
		
		if(anagrafica.getCodSocieta() == null || anagrafica.getCodSocieta().equals("") || anagrafica.getCodUt() == null || anagrafica.getCodUt().equals("") || anagrafica.getCodEnte() == null || anagrafica.getCodEnte().equals("") || anagrafica.getCodiceFiscale() == null || anagrafica.getCodiceFiscale().equals("")){
			throw new IllegalArgumentException("codiceFiscale, codUt, codSocieta, codEnte sono richiesti");
		}
		CallableStatement callableStatement = null;
		try{
			callableStatement = prepareCall(Routines.PYACMSP_UPD.routine());
			callableStatement.setString(1, anagrafica.getCodSocieta());
			callableStatement.setString(2, anagrafica.getCodUt());
			callableStatement.setString(3, anagrafica.getCodEnte());
			callableStatement.setString(4, anagrafica.getCodiceFiscale());
			callableStatement.setString(5, anagrafica.getEmail()==null?"":anagrafica.getEmail());
			callableStatement.setString(6, anagrafica.getTelefono()==null?"":anagrafica.getTelefono());
			callableStatement.setFloat(7, anagrafica.getImportoMinimo());
			callableStatement.registerOutParameter(8, java.sql.Types.VARCHAR);
			callableStatement.registerOutParameter(9, java.sql.Types.VARCHAR);

			callableStatement.execute();
			if(!callableStatement.getString(8).equals("OK")){
				throw new DaoException(0, callableStatement.getString(9));
			}

		}catch (SQLException x) {
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
			}
		}
		//fine LP PG21XX04 Leak
		return getAnagraficaAccesso( anagrafica.getCodSocieta(), anagrafica.getCodUt(), anagrafica.getCodEnte(), anagrafica.getCodiceFiscale());
	}
	
	private Borsellino recuperaBorsellino(String codSocieta, String codUt, String codEnte, String codiceFiscale, String telefono, String email, float importoMinimo) throws DaoException{
		Borsellino borsellino = null;
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;
		ResultSet rs = null;
		//fine LP PG21XX04 Leak
		try{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = null;
			//fine LP PG21XX04 Leak
			callableStatement = prepareCall(Routines.PYBRSSP_AGG_OND.routine());
			
			callableStatement.setString(1, codSocieta);
			callableStatement.setString(2, codUt);
			callableStatement.setString(3, codEnte);
			callableStatement.setString(4, codiceFiscale);
			callableStatement.setString(5, telefono==null?"":telefono);
			callableStatement.setString(6, email==null?"":email);
			callableStatement.setFloat(7, importoMinimo);
			callableStatement.setString(8, "");
			callableStatement.setString(9, "");
			callableStatement.setString(10, "");
			callableStatement.setString(11, "");
			callableStatement.setString(12, "");
			callableStatement.setString(13, "");
			callableStatement.registerOutParameter(14, java.sql.Types.VARCHAR);
			if(callableStatement.execute()){
				//inizio LP PG21XX04 Leak
				//ResultSet rs = callableStatement.getResultSet();
				rs = callableStatement.getResultSet();
				//inizio LP PG21XX04 Leak
				if(rs.next()){
					borsellino = new Borsellino( rs.getString(1), rs.getFloat(8));
				}
			}

		}catch (SQLException x) {
			throw new DaoException(x);
		} catch (IllegalArgumentException x) {
			throw new DaoException(x);
		} catch (HelperException x) {
			throw new DaoException(x);
		}
		//inizio LP PG21XX04 Leak
		finally {
			if(rs != null) {
				try {
					rs.close();
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
		}
		//fine LP PG21XX04 Leak
		return borsellino;
	}
	
	
	class Borsellino{
		private String chiaveBorsellino;
		private Float residuoBorsellino;
		
		public Borsellino(String chiaveBorsellino, Float residuoBorsellino) {
			super();
			this.chiaveBorsellino = chiaveBorsellino;
			this.residuoBorsellino = residuoBorsellino;
		}

		public String getChiaveBorsellino() {
			return chiaveBorsellino;
		}

		public void setChiaveBorsellino(String chiaveBorsellino) {
			this.chiaveBorsellino = chiaveBorsellino;
		}

		public Float getResiduoBorsellino() {
			return residuoBorsellino;
		}

		public void setResiduoBorsellino(Float residuoBorsellino) {
			this.residuoBorsellino = residuoBorsellino;
		}
		
	}

}
