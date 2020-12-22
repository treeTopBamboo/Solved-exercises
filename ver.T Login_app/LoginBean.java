package loginapp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginBean implements Serializable
{
	private String loginName = "";
	private String loginPass = "";

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public String getLoginPass()
	{
		return loginPass;
	}

	public void setLoginPass(String loginPass)
	{
		this.loginPass = loginPass;
	}

	/* 練習問題04
	//* DBにあるユーザ情報を使用して認証
	public static LoginBean login() throws SQLException
	{
		Connection conn = null;
		LoginBean loginbean = null;

		try
		{
			loginbean = new LoginBean();
			conn = new ResourceFinder().getConnectionpuser();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT loginname, password From t_login");

			if ( rs.next() )
			{
				loginbean.setLoginName(rs.getString("loginname"));
				loginbean.setLoginPass(rs.getString("password"));
			}

			rs.close();
			st.close();
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		finally
		{
			conn.close();
		}

		return loginbean;
	}
	//*/


	/* -----------------------------------------------------------------
	  一つの LoginBean を 返却した login() に対し、
	  今回は 全てのレコードを持っている LoginBean のリストを返却します。
	----------------------------------------------------------------- */

	// 複数ユーザ対応（ArrayList型にして、DBの内容をすべて返却する）
	public static ArrayList<LoginBean> loginAllData() throws SQLException
	{
		Connection conn = null;

		// リストのインスタンスを定義
		ArrayList<LoginBean> loginAllList = new ArrayList<LoginBean>();

		try
		{
			conn = new ResourceFinder().getConnectionpuser();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT loginname, password From t_login");

			// DBのレコードを読み取る。レコードの終端まで処理
			while ( rs.next() )
			{
				// インスタンスの生成
				LoginBean loginbean = new LoginBean();

				// レコードの値を読み取る
				loginbean.setLoginName(rs.getString("loginname"));
				loginbean.setLoginPass(rs.getString("password"));

				// 取得したレコードの値を追記する
				loginAllList.add(loginbean);
			}
			rs.close();
			st.close();
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		finally
		{
			conn.close();
		}

		// リストを返却する
		return loginAllList;
	}

}
