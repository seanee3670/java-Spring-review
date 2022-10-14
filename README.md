# Pojo JAVA DB연동

## Code

```java
public class Dao {
    public void add() throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/likelion-db", "root", dbPassword);
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?, ?, ?)");
        ps.setString(1, "01");
        ps.setString(2, "Kyeongrok");
        ps.setString(3, "password");

        ps.executeUpdate();

        ps.close();
        c.close();

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Map<String, String> env = System.getenv();
        String dbPassword = env.get("DB_PASSWORD");

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/likelion-db", "root", dbPassword);

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User(rs.getString("id"), rs.getString("name"), rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main (String[]args) throws SQLException, ClassNotFoundException {
        Dao dao = new Dao();
        dao.add();
    }
}
```


## Factory를 Bean으로

```java
@Configuration
public class UserDaoFactory2 {

    @Bean
    public UserDao05Interface userDao05Interface() {
        return new UserDao05Interface(() -> {
            return null;
        });
    }

}

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(UserDaoFactory2.class);
        UserDao05Interface userDao = ctx.getBean("userDao05Interface", UserDao05Interface.class);

    }
}
```

### 참고 
https://github.com/Kyeongrok/toby_spring

