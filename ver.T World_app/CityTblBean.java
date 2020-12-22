package worldapp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CityTblBean implements Serializable
{
	private int id;
	private String name = null;

	private String countrycode = null;
	private String district = null;
	private long population;

	public int getId() {
		return id;
    }

	public void setId(int id) {
		this.id = id;
    }

	public String getName() {
		return name;
    }

	public void setName(String name) {
		this.name = name;
    }

	public String getCountrycode() {
		return countrycode;
    }

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
    }

	public String getDistrict() {
		return district;
    }

	public void setDistrict(String district) {
		this.district = district;
    }

	public long getPopulation() {
		return population;
    }

	public void setPopulation(long population) {
		this.population = population;
    }

	/* cityテーブルからすべての情報を取り出すメソッド */

	//* 最初の演習（使わなくなる）
	public static ArrayList<CityTblBean> selectAllList(int pos) throws SQLException
	{
		Connection conn = null;
		ArrayList<CityTblBean> citylist = new ArrayList<CityTblBean>();

		try
		{
			conn = new ResourceFinder().getConnectionpuser();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM city");
			rs.absolute(pos);

			for (int i = 0; i < 20; i++)
			{
				CityTblBean ctb = new CityTblBean();
				ctb.setId(rs.getInt("ID"));
				ctb.setName(rs.getString("Name"));
				ctb.setCountrycode(rs.getString("CountryCode"));
				ctb.setDistrict(rs.getString("District"));
				ctb.setPopulation(rs.getInt("Population"));
				citylist.add(ctb);
				boolean b = rs.next();

				if (!b)
				{
					break;
				}
			}

			rs.close();
			st.close();

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			conn.close();
		}

		return citylist;
	}
	//*/

	//* カントリーコードで検索機能
	public static ArrayList<CityTblBean> selectCcodeList(String ccode) throws SQLException
	{
		Connection conn = null;
		ArrayList<CityTblBean> citylist = new ArrayList<CityTblBean>();

		try
		{
		  conn = new ResourceFinder().getConnectionpuser();
		  PreparedStatement st = conn.prepareStatement("SELECT * FROM city WHERE CountryCode=?");
		  st.setString(1, ccode);
		  ResultSet rs = st.executeQuery();

		  while(rs.next())
		  {
			CityTblBean ctb = new CityTblBean();
			ctb.setId(rs.getInt("ID"));
			ctb.setName(rs.getString("Name"));
			ctb.setCountrycode(rs.getString("CountryCode"));
			ctb.setDistrict(rs.getString("District"));
			ctb.setPopulation(rs.getInt("Population"));
			citylist.add(ctb);
		  }

		  rs.close();
		  st.close();

		}
		catch(Exception ex)
		{
		  ex.printStackTrace();
		}
		finally
		{
		  conn.close();
		}

		return citylist;
	}
	//*/

	// レコードを編集する機能
	// 編集するレコードを検索
	public static CityTblBean selectEditRow(int id) throws SQLException
	{
		Connection conn = null;
		CityTblBean order = new CityTblBean();
		PreparedStatement st = null;

		try
		{
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.prepareStatement("SELECT * FROM city WHERE id=?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			rs.next();
			order.setId(rs.getInt("ID"));
			order.setName(rs.getString("Name"));
			order.setCountrycode(rs.getString("CountryCode"));
			order.setDistrict(rs.getString("District"));
			order.setPopulation(rs.getInt("Population"));

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

		return order;
	}

	// レコードを編集する機能
	// 検索したレコードを更新
	public static Integer updateEditRow(CityTblBean urec) throws SQLException
	{
		Connection conn = null;
		Integer result = new Integer(0);
		PreparedStatement st = null;

		try
		{
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.prepareStatement("UPDATE city SET name=?, countrycode=?, district=?, population=? WHERE id=?");
			st.setString(1, urec.getName());
			st.setString(2, urec.getCountrycode());
			st.setString(3, urec.getDistrict());
			st.setLong(4, urec.getPopulation());
			st.setInt(5, urec.getId());

			result = st.executeUpdate();

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

		return result;
	}

	// レコードの新規追加
	// レコードの新規追加
	public static Integer insertNewRow(CityTblBean nrec) throws SQLException
	{
		Connection conn = null;
		Integer result = new Integer(0);
		PreparedStatement st = null;

		try
		{
			conn = new ResourceFinder().getConnectionpuser();
			st = conn.prepareStatement("INSERT INTO city(name,countrycode,district,population) VALUES(?,?,?,?)");
			st.setString(1, nrec.getName());
			st.setString(2, nrec.getCountrycode());
			st.setString(3, nrec.getDistrict());
			st.setLong(4, nrec.getPopulation());

			result = st.executeUpdate();

			st.close();
		}
		catch(Exception ex)
		{
			// city.CountryCode - country.Code の制約に違反するとここに飛んでくる。
			System.out.println("制約違反");
			ex.printStackTrace();
		}
		finally
		{
			conn.close();
		}

		return result;
	}

	// レコードの新規追加
	// 新規追加したデータのidを取得する
	public static int getNewRowId(String name,String countrycode) throws SQLException
	{
		Connection conn = null;
		PreparedStatement st = null;
		int id = 0;

		try
		{
			// 追加前のテーブルに該当するレコードがないと、rs.getInt("ID") で Exception エラーとなる。
			// それは、city テーブルの CountryCode と countryテーブルの Code の間に制約があるから。
			conn = new ResourceFinder().getConnectionpuser();

 			st = conn.prepareStatement("SELECT id FROM city WHERE name=? and countrycode=?");
			st.setString(1, name);
			st.setString(2, countrycode);
			ResultSet rs = st.executeQuery();
			rs.next();
			id = rs.getInt("ID");

			rs.close();
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			conn.close();
		}

		return id;
	}

	// 指定したIDのレコードを削除する
	public static Integer deleteRow(int id) throws SQLException
	{
		Connection conn = null;
		Integer result = new Integer(0);
		PreparedStatement st = null;

		try
		{
		  conn = new ResourceFinder().getConnectionpuser();
		  st = conn.prepareStatement("DELETE FROM city WHERE id=?");
		  st.setInt(1, id);

		  result = st.executeUpdate();
		  st.close();
		}
		catch (Exception ex)
		{
		  ex.printStackTrace();
		}
		finally
		{
		  conn.close();
		}

		return result;
	}

}
