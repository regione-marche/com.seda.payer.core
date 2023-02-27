package com.seda.payer.core.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.seda.data.dao.DAOHelper;
import com.seda.data.helper.HelperException;
import com.seda.payer.commons.bean.TypeRequest;
import com.seda.payer.core.bean.User;
import com.seda.payer.core.exception.DaoException;
import com.seda.payer.core.handler.BaseDaoHandler;
import com.seda.payer.core.messages.Messages;
/**
 * 
 * @author mmontisano
 *
 */
public class UserDao extends BaseDaoHandler {
	
	public UserDao(Connection connection, String schema) {
		super(connection, schema);
	}

//	public User doDetail(String companyCode, String userCode) throws DaoException {
//		try	{
//			CallableStatement callableStatement = prepareCall(Routines.USER_DODETAIL.routine());
//			callableStatement.setString(1, companyCode);
//			callableStatement.setString(2, userCode);
//			if (callableStatement.execute()) 
//			{
//				return new User(callableStatement);
//			}
//			
//			return null;
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		}
//	}
	
	//INIZIO PG130100
	public ArrayList<String> getCodiceSocieta(String cuteCute, String ente){
		ArrayList<String> ret = new ArrayList<String>();
		CallableStatement callableStatement = null;
		//inizio LP PG21XX04 Leak
		ResultSet data = null;
		//fine LP PG21XX04 Leak
			try	
			{
				callableStatement = prepareCall(Routines.ANE_DODETAIL_ENTE.routine());
				callableStatement.setString(1, ente);
				callableStatement.setString(2, " ");
				callableStatement.setString(3, " ");
				callableStatement.setString(4, cuteCute);
				if (callableStatement.execute()) {
					//inizio LP PG21XX04 Leak
					//ResultSet data = callableStatement.getResultSet();
					data = callableStatement.getResultSet();
					//fine LP PG21XX04 Leak
					if (data.next())
						ret.add(data.getString(1));
						ret.add(data.getString(2));
				}			
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				//inizio LP PG21XX04 Leak
				//DAOHelper.closeIgnoringException(callableStatement);
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
			
		return ret;
	}
	
	//FINE PG130100
	
	
	public User doDetail(String companyCode, String userCode) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		ResultSet data = null;
		//fine LP PG21XX04 Leak
		try	
		{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.USER_DODETAIL.routine());
			callableStatement = prepareCall(Routines.USER_DODETAIL.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, companyCode);
			callableStatement.setString(2, userCode);
			if (callableStatement.execute()) {
				//inizio LP PG21XX04 Leak
				//ResultSet data = callableStatement.getResultSet();
				data = callableStatement.getResultSet();
				//fine LP PG21XX04 Leak
				if (data.next())
					return new User(data);
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

//	public List doList(User user, int rowsPerPage, int pageNumber) throws DaoException {
//		try	{
//			if (rowsPerPage <= 0)
//				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));
//
//			if (pageNumber <= 0)
//				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
// 
//			CallableStatement callableStatement = prepareCall(Routines.USER_DOLIST.routine());
//			callableStatement.setString(1, user.getCompany().getCompanyCode());
//			callableStatement.setString(2, user.getUserCode());
//			callableStatement.setString(3, user.getScopeCncCode());
//			callableStatement.setString(4, user.getUserDescription());
//			callableStatement.setString(5, "");
//			callableStatement.setInt(6, rowsPerPage);
//			callableStatement.setInt(7, pageNumber);
//			/* we register row start */
//			callableStatement.registerOutParameter(8, Types.INTEGER);
//			/* we register row end */
//			callableStatement.registerOutParameter(9, Types.INTEGER);
//			/* we register total rows */
//			callableStatement.registerOutParameter(10, Types.INTEGER);
//			/* we register total pages */
//			callableStatement.registerOutParameter(11, Types.SMALLINT);
//			List list = new ArrayList();
//			/* we execute procedure */
//			if (callableStatement.execute()) {
//				ResultSet data = callableStatement.getResultSet();
//				if (data.next()) {
//					do { list.add(new User(data));
//					} while (data.next());
//				}
//				/* we register page info */
//				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(8), callableStatement.getInt(9), 
//								 callableStatement.getInt(10), callableStatement.getInt(11));
//			}
//			return list;
//		} catch (SQLException x) {
//			throw new DaoException(x);
//		} catch (IllegalArgumentException x) {
//			throw new DaoException(x);
//		} catch (HelperException x) {
//			throw new DaoException(x);
//		}
//	}

	public void doRowSets(User user,String strDescrSocieta) throws DaoException {
		rowSets(user, 0, 0, strDescrSocieta);
	}

	public void doRowSets(User user, int rowsPerPage, int pageNumber,String strDescrSocieta) throws DaoException {
		
			if (rowsPerPage <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("rowsPerPage"));

			if (pageNumber <= 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("pageNumber"));
 
			rowSets(user, rowsPerPage, pageNumber, strDescrSocieta);
		
	}
	public void rowSets(User user, int rowsPerPage, int pageNumber,String strDescrSocieta) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{ 
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.USER_DOLIST.routine());
			callableStatement = prepareCall(Routines.USER_DOLIST.routine());
			//fine LP PG21XX04 Leak
			callableStatement.setString(1, user.getCompany().getCompanyCode());
			callableStatement.setString(2, user.getUserCode());
			callableStatement.setString(3, user.getScopeCncCode());
			callableStatement.setString(4, user.getUserDescription());
			callableStatement.setString(5, strDescrSocieta);
			callableStatement.setString(6, "");
			callableStatement.setInt(7, rowsPerPage);
			callableStatement.setInt(8, pageNumber);
			/* we register row start */
			callableStatement.registerOutParameter(9, Types.INTEGER);
			/* we register row end */
			callableStatement.registerOutParameter(10, Types.INTEGER);
			/* we register total rows */
			callableStatement.registerOutParameter(11, Types.INTEGER);
			/* we register total pages */
			callableStatement.registerOutParameter(12, Types.SMALLINT);
			/* we execute procedure */
			if (callableStatement.execute()) {
				this.loadWebRowSets(callableStatement);
				/* we register page info */
				registerPageInfo(rowsPerPage, pageNumber, callableStatement.getInt(9), callableStatement.getInt(10), 
								 callableStatement.getInt(11), callableStatement.getInt(12));
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

	/**
	 * @param user
	 * @param codOp - use <code>TypeRequest.scope</code>
	 * @throws DaoException
	 */
	public void doSave(User user, String codOp) throws DaoException {
		CallableStatement callableStatement=null;
		try	{
			if (user.getUserCode() == null || user.getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("user.userCode"));

			if (user.getCompany() == null || user.getCompany().getCompanyCode() == null || user.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("user.company.companyCode"));
			
			User data = doDetail(user.getCompany().getCompanyCode(), user.getUserCode());
			
			if ((data != null) && codOp!=null && codOp.compareTo(TypeRequest.ADD_SCOPE.scope())==0) throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("user.saveadd.error"));
			if (data != null) 
				callableStatement = prepareCall(Routines.USER_DOUPDATE.routine());
			else callableStatement = prepareCall(Routines.USER_DOINSERT.routine());

			user.save(callableStatement);
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

	public void doDelete(User user) throws DaoException {
		//inizio LP PG21XX04 Leak
		CallableStatement callableStatement = null;;
		//fine LP PG21XX04 Leak
		try	{
			//inizio LP PG21XX04 Leak
			//CallableStatement callableStatement = prepareCall(Routines.USER_DODELETE.routine());
			callableStatement = prepareCall(Routines.USER_DODELETE.routine());
			//fine LP PG21XX04 Leak
			if (user.getUserCode() == null || user.getUserCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("user.userCode"));

			if (user.getCompany() == null || user.getCompany().getCompanyCode() == null || user.getCompany().getCompanyCode().length() == 0)
				throw new IllegalArgumentException(Messages.INVALID_PARAMETER.format("user.company.companyCode"));

			callableStatement.setString(1, user.getCompany().getCompanyCode());
			callableStatement.setString(2, user.getUserCode());
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

}