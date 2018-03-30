package ConectionBD;
	import java.util.logging.Logger;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.logging.Level;

	public class ConexiuneBD {
		private static final Logger LOGGER=Logger.getLogger(ConexiuneBD.class.getName());
		private static final String DRIVER ="com.mysql.jdbc.Driver";
		private static final String DBURL ="jdbc:mysql://localhost:3306/agentie";
		private static final String USER="root";
		private static final String PASS="root";
		private static ConexiuneBD singleInstance=new ConexiuneBD();
		public ConexiuneBD()
		{
				try {
					Class.forName(DRIVER);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
		
		}
	private Connection createConnection()
	{
		Connection connection=null;
		try
		{
			connection=DriverManager.getConnection(DBURL,USER,PASS);

		} catch(SQLException e)
		{
			LOGGER.log(Level.WARNING,"eroare la deschiderea conexiuni");
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getConnection()
	{
		return singleInstance.createConnection();
	}

	public static void close(Connection connection)
	{
		if (connection !=null)
		{
			try{
				connection.close();
			}catch(SQLException e)
			{
				LOGGER.log(Level.WARNING,"eroare la inchiderea conexiuni");
			}
		}
	}

	public static void close(Statement statement)
	{
	if (statement!=null)
	{
		try
		{
			statement.close();
		}catch(SQLException e){
			LOGGER.log(Level.WARNING, "eroare la inchiderea unui statement");
		}
	}
	}

	public static void close(ResultSet resultSet)
	{
		if (resultSet!=null)
		{
			try{
			resultSet.close();
		       }catch(SQLException e)
			        {
		    	   LOGGER.log(Level.WARNING, "eroare la inchiderea unui resultSet");
			         }
			}
	}
}

