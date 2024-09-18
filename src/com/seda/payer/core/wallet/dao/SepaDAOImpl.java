package com.seda.payer.core.wallet.dao;

import java.lang.reflect.UndeclaredThrowableException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import com.seda.commons.string.Convert;
import com.seda.data.helper.HelperException;
import com.seda.data.procedure.reflection.MetaProcedure;
import com.seda.data.procedure.reflection.ProcedureReflectorException;
import com.seda.payer.core.bean.AnagraficaSoggettoSEPA;
import com.seda.payer.core.bean.Autorizzazione;
import com.seda.payer.core.dao.Routines;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.rest.RestBaseDaoHandler;
import com.seda.payer.core.wallet.bean.Wallet;

public class SepaDAOImpl extends RestBaseDaoHandler implements SepaDAO  {
	private static final long serialVersionUID = 1L;
	protected CallableStatement callableStatementRID = null;
	protected CallableStatement callableStatementRIDSEL = null;
	//inizio LP PG21XX04 Leak
	@Deprecated
	//fine LP PG21XX04 Leak
	public SepaDAOImpl(DataSource dataSource, String schema) throws SQLException {
		super(dataSource.getConnection(), schema);
	}

	public SepaDAOImpl(Connection connection, String schema, boolean isRest, String baseUrl){
		super(connection, schema, isRest, baseUrl);
	}

	public SepaDAOImpl(Connection connection, String schema) throws SQLException {
		super(connection, schema);
	}
	//	public String selectRid(String cuteCute, String codBorsellino)  {
	//		CallableStatement callableStatement=null;
	//		Connection connection = null; 
	//		String rid = "";
	//		try {
	//			connection = getConnection();
	//
	//			callableStatement = Helper.prepareCall(connection, getSchema(), Routines.SDDDESP_INS_BRS.routine());
	//
	//			callableStatement.setString(1, "");
	//
	//			callableStatement.registerOutParameter(11, Types.VARCHAR);
	//			callableStatement.execute();
	//			rid = callableStatement.getString(11);
	//		} catch (SQLException e) {
	//			System.out.println(e);
	//		} catch (IllegalArgumentException e) {
	//			System.out.println(e);
	//		} catch (HelperException e) {
	//			System.out.println(e);
	//		} finally {
	//			DAOHelper.closeIgnoringException(connection);
	//		}
	//
	//		return rid;
	//	}

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	public Wallet selectSepa(Wallet wallet) throws DaoException {
	//inizio LP 20240913 - PGNTCORE-24
		return selectSepaTail(true, wallet);
	}

	public Wallet selectSepaBatch(boolean bFlagUpdateAutocommit, Wallet wallet) throws DaoException {
		return selectSepaTail(bFlagUpdateAutocommit, wallet);
	}

	private Wallet selectSepaTail(boolean bFlagUpdateAutocommit, Wallet wallet) throws DaoException {
	//fine LP 20240913 - PGNTCORE-24
		//CallableStatement callableStatement=null;
		ResultSet rs = null; 
		//Connection connection = null;

		System.out.println("cutecute - " + wallet.getCuteCute());
		if(wallet.getCodiceRid()!=null) {
			System.out.println("rid05 - " + wallet.getCodiceRid().substring(0, 5));
			System.out.println("rid56 - " + wallet.getCodiceRid().substring(5,6));
			System.out.println("rid6 - " + wallet.getCodiceRid().substring(6));
		} else {
			System.out.println("codiceRid = null");
		}

		try {
			//connection = getConnection();
			if (callableStatementRIDSEL == null) {
				//inizio LP 20240913 - PGNTCORE-24
				//callableStatementRIDSEL = Helper.prepareCall(getConnection(), getSchema(), Routines.SDDAUSP_SEL_BRS.routine());
                callableStatementRIDSEL = prepareCall(bFlagUpdateAutocommit, Routines.SDDAUSP_SEL_BRS.routine(), "PUT","SEPA");
				//fine LP 20240913 - PGNTCORE-24
			}
			callableStatementRIDSEL.setString(1, wallet.getCuteCute());
			callableStatementRIDSEL.setString(2, wallet.getCodiceRid().substring(0,5));
			callableStatementRIDSEL.setString(3, wallet.getCodiceRid().substring(5,6));
			callableStatementRIDSEL.setString(4, wallet.getCodiceRid().substring(6));
			//PG22XX09_SB2 - inizio
			callableStatementRIDSEL.registerOutParameter(5, Types.VARCHAR);
			callableStatementRIDSEL.registerOutParameter(6, Types.VARCHAR);
			//PG22XX09_SB2 - fine
			callableStatementRIDSEL.registerOutParameter(7, Types.VARCHAR);

			callableStatementRIDSEL.execute();

			rs = callableStatementRIDSEL.getResultSet();
			
			//PG22XX09_SB2 - inizio
			
			loadWebRowSet(rs);
			WebRowSet wrs = Convert.stringToWebRowSet(getWebRowSetXml());
			wallet.setAttribute("sepaXML", getWebRowSetXml());
			if(wrs.next())	{
				wallet.setAttribute("stato", wrs.getString(4));
				wallet.setAttribute("dataAttivazione", wrs.getString(5));
			}
			wallet.setAttribute("voceIncasso", callableStatementRIDSEL.getString(5));
			wallet.setAttribute("codiceABI", callableStatementRIDSEL.getString(6));
			//PG22XX09_SB2 - fine
		} catch (SQLException e) {
			//System.out.println(e);
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			//System.out.println(e);
			throw new DaoException(e);
		} catch (HelperException e) {
			//System.out.println(e);
			throw new DaoException(e);
		} catch (Exception e) {
			//System.out.println(e);
			throw new DaoException(e);
		} finally {
			//DAOHelper.closeIgnoringException(connection);
			//inizio LP PG21XX04 Leak
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}
		System.out.println("ritorno wallet SDDAUSP_SEL_BRS" + wallet.toString());
		return wallet;
	}

	//inizio LP PG21XX04 Leak
	public Wallet selectSepaWeb(Wallet wallet) throws DaoException {
		CallableStatement callableStatement = null;
		ResultSet rs = null; 
		Connection connection = null; 
		try {
			connection = getConnection();
		//	callableStatement = Helper.prepareCall(connection, getSchema(), Routines.SDDAUSP_SEL_BRS.routine());
            callableStatement = prepareCall(Routines.SDDAUSP_SEL_BRS.routine(), "PUT","SEPA");
			callableStatement.setString(1, wallet.getCuteCute());
			callableStatement.setString(2, wallet.getCodiceRid().substring(0,5));
			callableStatement.setString(3, wallet.getCodiceRid().substring(5,6));
			callableStatement.setString(4, wallet.getCodiceRid().substring(6));
			//PG22XX09_SB2 - inizio
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			//PG22XX09_SB2 - fine
			callableStatement.registerOutParameter(7, Types.VARCHAR);

			callableStatement.execute();

			rs=callableStatement.getResultSet();

			if (rs.next() ) {
				wallet.setAttribute("stato", rs.getString(4));
				wallet.setAttribute("dataAttivazione", rs.getString(5));
			}


		} catch (SQLException e) {
			throw new DaoException(e);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (HelperException e) {
			throw new DaoException(e);
		} finally {
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return wallet;
	}
	//fine LP PG21XX04 Leak

	public String selectSepa(String cuteCute, String rid)  {
		CallableStatement callableStatement=null;
		ResultSet rs = null; 
		Connection connection = null; 
		String sepaXml = "";

		try {
			connection = getConnection();

			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.SDDAUSP_SEL_BRS.routine());
            callableStatement = prepareCall(Routines.SDDAUSP_SEL_BRS.routine(), "PUT","SEPA");

			callableStatement.setString(1, cuteCute);
			callableStatement.setString(2, rid.substring(0,5));
			callableStatement.setString(3, rid.substring(5,6));
			callableStatement.setString(4, rid.substring(6));
			//PG22XX09_SB2 - inizio
			callableStatement.registerOutParameter(5, Types.VARCHAR);
			callableStatement.registerOutParameter(6, Types.VARCHAR);
			//PG22XX09_SB2 - fine
			callableStatement.registerOutParameter(7, Types.VARCHAR);

			callableStatement.execute();
			rs=callableStatement.getResultSet();
			loadWebRowSet(rs);
			sepaXml = getWebRowSetXml();


		} catch (SQLException e) {
			System.out.println(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		} catch (HelperException e) {
			System.out.println(e);
		} finally {
			//inizio LP PG21XX04 Leak
			//DAOHelper.closeIgnoringException(connection);
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
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//fine LP PG21XX04 Leak
		}

		return sepaXml;
	}
	public ArrayList<String> listNoRid(String cuteCute) throws DaoException {
		CallableStatement callableStatement=null;
		ArrayList<String> result = new ArrayList<String>();
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		Connection connection = null; 

		try {
			connection = getConnection();
			//inizio LP PGNTCORE-24
			//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL_NORID.routine());
			callableStatement =  MetaProcedure.prepareCall(connection, getSchema(), Routines.PYBRSSP_SEL_NORID.routine());
			//fine LP PGNTCORE-24
			callableStatement.setString(1, cuteCute);

			callableStatement.execute();

			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				while (data.next())
					result.add(data.getString(1));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		//inizio LP PGNTCORE-24
		//} catch (HelperException e) {
		//	System.out.println(e);
		} catch (ProcedureReflectorException e) {
			System.out.println(e);
		//fine LP PGNTCORE-24
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

		return result;
	}

	//inizio LP PG21XX04
	//Nota. La chiusura della connection è affidata al chiamante.
	//fine LP PG21XX04
	//inizio LP 20240828 - PGNTCORE-24/PGNTCORE-24
	public String selectNewRid(Wallet wallet) throws DaoException, SQLException, HelperException {
		return selectNewRidTail(true, wallet);
	}

	public String selectNewRidTail(boolean bFlagUpdateAutocommit, Wallet wallet) throws DaoException, SQLException, HelperException {
	//fine LP 20240828 - PGNTCORE-24/PGNTCORE-24
		//CallableStatement callableStatement=null;
		String rid = "";
		//Connection connection = null;
		System.out.println("cutecute - " + wallet.getCuteCute());
		System.out.println("idWallet - " + wallet.getIdWallet());
		System.out.println("operatore - " + wallet.getOperatore());
		try {
			//connection = getConnection();
			if (callableStatementRID == null) {
				//inizio LP 20240828 - PGNTCORE-24/PGNTCORE-24
				//callableStatementRID = Helper.prepareCall(getConnection(), getSchema(), Routines.SDDDESP_INS_BRS.routine());
				callableStatementRID = prepareCall(bFlagUpdateAutocommit, Routines.SDDDESP_INS_BRS.routine(), "POST","SEPA");				
				//fine LP 20240828 - PGNTCORE-24/PGNTCORE-24
			}
			callableStatementRID.setString(1, wallet.getCuteCute());
			callableStatementRID.setString(2, "");
			callableStatementRID.setString(3, wallet.getIdWallet());
			callableStatementRID.setString(4, wallet.getOperatore());
			callableStatementRID.registerOutParameter(5, Types.VARCHAR);
			callableStatementRID.registerOutParameter(6, Types.VARCHAR);
			callableStatementRID.registerOutParameter(7, Types.VARCHAR);
			callableStatementRID.execute();

			rid = callableStatementRID.getString(5);
			System.out.println(callableStatementRID.getString(7));

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			//DAOHelper.closeIgnoringException(connection);
		}
		return rid;
	}
	
	//inizio PG22XX09_SB2
		public Integer insertSepaWeb(Autorizzazione autorizzazione ) throws DaoException {
			CallableStatement callableStatement = null; 
			Connection connection = null;
			try {
				connection = getConnection();
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.SDDAUSP_INS.routine());
                callableStatement = prepareCall(Routines.SDDAUSP_INS.routine(), "POST","SEPA");
				//callableStatement.setString(1, autorizzazione.getCodice);
				callableStatement.setString(1, autorizzazione.getCodiceUtente());
				callableStatement.setString(2, autorizzazione.getCodiceSia());
				callableStatement.setString(3, autorizzazione.getTipoAutorizzazione());
				callableStatement.setString(4, autorizzazione.getCodiceAutorizzazione());
				callableStatement.setString(5, autorizzazione.getStato());
				callableStatement.setString(6, autorizzazione.getCodiceFiscaleSottoscrittore());
				callableStatement.setString(7, autorizzazione.getCodiceFiscaleIntestatario());
				callableStatement.setString(8, autorizzazione.getCodicePaeseCcAccredito());
				callableStatement.setBigDecimal(9, autorizzazione.getCodiceControlloCcAccredito());
				callableStatement.setString(10, autorizzazione.getCodiceAbiCcAccredito());
				callableStatement.setString(11, autorizzazione.getCodiceCabCcAccredito());
				callableStatement.setString(12, autorizzazione.getNumeroCcAccredito());
				callableStatement.setString(13, autorizzazione.getCodiceCinCcAccredito());
				callableStatement.setDate(14, new java.sql.Date(autorizzazione.getDataPrecedenteRevoca().getTime()));
				callableStatement.setString(15, autorizzazione.getCanaleProvenienza());
				callableStatement.setString(16, autorizzazione.getTipoProvenienza());
				callableStatement.setString(17, autorizzazione.getIdDocumentoOrigine());
				callableStatement.setDate(18, new java.sql.Date(autorizzazione.getDataInizioValidita().getTime()));
				callableStatement.setDate(19, new java.sql.Date(autorizzazione.getDataFineValidita().getTime()));
				callableStatement.setDate(20, new java.sql.Date(autorizzazione.getDataRevoca().getTime()));
				callableStatement.setString(21, autorizzazione.getTipoPagamento());
				callableStatement.setString(22, autorizzazione.getCodiceVoceIncasso());
				callableStatement.setString(23, autorizzazione.getIdDebitore());
				callableStatement.setString(24, autorizzazione.getCodiceAbiCCAllineamento());
				callableStatement.setString(25, autorizzazione.getFlgSospensione());
				callableStatement.setDate(26, new java.sql.Date(autorizzazione.getDataSospensione().getTime()));
				callableStatement.setString(27, autorizzazione.getOperatoreSospensione());
				callableStatement.setDate(28, new java.sql.Date(autorizzazione.getDataRevocaSospensione().getTime()));
				callableStatement.setString(29, autorizzazione.getOperatoreRevocaSospensione());
				callableStatement.setString(30, autorizzazione.getFlgFacoltaRimborso());
				callableStatement.setBigDecimal(31, autorizzazione.getImpPrefissato());
				callableStatement.setString(32, autorizzazione.getCodiceBic());
				callableStatement.setString(33, autorizzazione.getFlgClassificazioneConto());
				callableStatement.setDate(34, new java.sql.Date(autorizzazione.getDataRicAllineamento().getTime()));
				callableStatement.setString(35, autorizzazione.getEsitoRicAllineamento());
				callableStatement.setDate(36, new java.sql.Date(autorizzazione.getDataEsitoRicAllineamento().getTime()));
				callableStatement.setString(37, autorizzazione.getTipologiaMandato());
				callableStatement.setString(38, autorizzazione.getDescrizioneTipologiaMandato());
				callableStatement.setString(39, autorizzazione.getTipologiaIncasso());
				callableStatement.setString(40, autorizzazione.getOperatoreInserimento());
				callableStatement.setDate(41, new java.sql.Date(autorizzazione.getDataInserimento().getTime()));
				callableStatement.setString(42, autorizzazione.getOperatoreVariazione());
				callableStatement.setDate(43, new java.sql.Date(autorizzazione.getDataVariazione().getTime()));
				callableStatement.setDate(44, new java.sql.Date(autorizzazione.getDataSottoscrizione().getTime()));
				callableStatement.setString(45, "");
				callableStatement.setString(46, "");
				callableStatement.setString(47, autorizzazione.getEmail());
				callableStatement.registerOutParameter(48, Types.INTEGER);

				callableStatement.execute();
				
				return callableStatement.getInt(48);

			} catch (SQLException e)  {
				throw new DaoException(e);
			} catch (IllegalArgumentException e)  {
				throw new DaoException(e);
			} catch (HelperException e)  {
				throw new DaoException(e);
			} finally {
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
			}
			
		}
		
		
		public Integer insertSepaDas(AnagraficaSoggettoSEPA anagrafica)
				throws DaoException{
			CallableStatement callableStatement = null;
			Connection connection = null; 
			int returnCode = -1;
			try {
				connection = getConnection();
				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.SDDASSP_INS.routine());
				callableStatement = prepareCall(Routines.SDDASSP_INS.routine(), "POST","SEPA");
				callableStatement.setString(1, anagrafica.getCodiceUtente());
				callableStatement.setString(2, anagrafica.getCodiceFiscale());
				callableStatement.setString(3, anagrafica.getStato());
				callableStatement.setString(4, anagrafica.getDenominazione());
				callableStatement.setString(5, anagrafica.getIndirizzo());
				callableStatement.setString(6, anagrafica.getCap());
				callableStatement.setString(7, anagrafica.getLocalita()); 
				callableStatement.setString(8, anagrafica.getSiglaProvincia());
				callableStatement.setString(9, anagrafica.getTelefono());
				callableStatement.setString(10, anagrafica.getEmail());
				callableStatement.setString(11, anagrafica.getOperatoreInserimento());
				callableStatement.setDate(12, new java.sql.Date(anagrafica.getDataInserimento().getTime()));
				callableStatement.setString(13, anagrafica.getOperatoreVariazione());
				callableStatement.setDate(14, new java.sql.Date(anagrafica.getDataVariazione().getTime()));
				callableStatement.registerOutParameter(15, Types.INTEGER);
				callableStatement.execute();
				returnCode = callableStatement.getInt(15);

				//Dublicazione chiave
				if (returnCode == -803) {
					throw new DaoException(new Throwable("AnagrafeSoggetto (SDDASTB) con chiave duplicata"));
				}
			//inizio LP 20240811 - PGNTCORE-24
			} catch (UndeclaredThrowableException x) {
				//TODO: verificare SDDASSP_INS
				DaoException.makeIfDuplicateKeyError(x, "anagrafeSoggetto (SDDASTB) con chiave duplicata");
			//fine LP 20240811 - PGNTCORE-24
			} catch (SQLException e) {
				throw new DaoException(e);
			} catch (IllegalArgumentException e) {
				throw new DaoException(e);
			} catch (HelperException e) {
				throw new DaoException(e);
			} finally {
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
			}
			
			return returnCode;
			
		}
		
		public String selectSepaEC(String cuteCute, String rid)  {
			CallableStatement callableStatement=null;
			ResultSet rs = null; 
			Connection connection = null; 
			String sepaXml = "";

			try {
				connection = getConnection();

				//callableStatement = Helper.prepareCall(connection, getSchema(), Routines.SDDAUSP_SEL.routine());
				callableStatement = prepareCall(Routines.SDDAUSP_SEL.routine(), "PUT","SEPA");

				callableStatement.setString(1, cuteCute);
				callableStatement.setString(2, rid.substring(0,5));
				callableStatement.setString(3, rid.substring(5,6));
				callableStatement.setString(4, rid.substring(6));
				callableStatement.setString(5, "");
				callableStatement.setString(6, "");
				callableStatement.registerOutParameter(7, Types.VARCHAR);
				callableStatement.registerOutParameter(8, Types.VARCHAR);
				callableStatement.registerOutParameter(9, Types.VARCHAR);

				callableStatement.execute();
				rs=callableStatement.getResultSet();
				loadWebRowSet(rs);
				sepaXml = getWebRowSetXml();


			} catch (SQLException e) {
				System.out.println(e);
			} catch (IllegalArgumentException e) {
				System.out.println(e);
			} catch (HelperException e) {
				System.out.println(e);
			} finally {
				//inizio LP PG21XX04 Leak
				//DAOHelper.closeIgnoringException(connection);
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
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				//fine LP PG21XX04 Leak
			}

			return sepaXml;
		}
		//fine PG22XX09_SB2

		//inizio LP 20240913 - PGNTCORE-24
	    public void closeCallableStatementS()  {
		    if(callableStatementRID != null) {
				try {
					callableStatementRID.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatementRID = null;
		    }
		    if(callableStatementRIDSEL != null) {
				try {
					callableStatementRIDSEL.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				callableStatementRIDSEL = null;
		    }
	    }
	    //fine LP 20240913 - PGNTCORE-24

}