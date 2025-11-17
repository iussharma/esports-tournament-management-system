# eSports Team Management System

A professional web application for managing eSports teams, players, tournaments, and matches. Built with Jakarta Servlet/JSP, MySQL, and Apache Tomcat 10.

## 🎮 Features

- **Team Management**: Create, edit, and manage eSports teams
- **Player Management**: Add players to teams with roles and skill ratings
- **Tournament Management**: Organize and track tournaments
- **Match Scheduling**: Schedule competitive matches and submit results
- **Admin Dashboard**: View statistics, top teams, and system overview
- **Leaderboards**: Dynamic team rankings based on points
- **Authentication**: Secure admin login system
- **Responsive Design**: Neon gaming-themed dark UI

## 🛠️ Technology Stack

- **Backend**: Java 17, Jakarta Servlet 6.0, Jakarta JSP
- **Web Server**: Apache Tomcat 10
- **Database**: MySQL 8.0+
- **Build Tool**: Maven 3.6+
- **Frontend**: JSP, JSTL, CSS3 with neon gaming theme

## 📋 Prerequisites

1. **Java Development Kit (JDK) 17**
   - Download from: https://www.oracle.com/java/technologies/downloads/

2. **Apache Tomcat 10**
   - Download from: https://tomcat.apache.org/download-10.cgi

3. **MySQL 8.0+ or XAMPP**
   - MySQL: https://dev.mysql.com/downloads/mysql/
   - XAMPP: https://www.apachefriends.org/

4. **Maven 3.6+**
   - Download from: https://maven.apache.org/download.cgi

5. **NetBeans IDE (Optional for development)**
   - Download from: https://netbeans.apache.org/

## 🚀 Quick Start Guide

### Step 1: Set Up Database

#### Option A: Using MySQL CLI
```bash
mysql -u root -p < src/main/resources/database.sql
```

#### Option B: Using XAMPP phpMyAdmin
1. Start XAMPP MySQL service
2. Open phpMyAdmin (http://localhost/phpmyadmin)
3. Click "Import" tab
4. Upload `src/main/resources/database.sql`
5. Click Import

#### Option C: Using MySQL Workbench
1. Open MySQL Workbench
2. Create new connection or use existing
3. File → Run SQL Script
4. Select `src/main/resources/database.sql`

### Step 2: Configure Database Connection

Edit `src/main/resources/app.properties`:

```properties
# For MySQL with user/password
db.url=jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC
db.user=esports_user
db.pass=Esports@2025

# For XAMPP (root user, no password)
db.url=jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC
db.user=root
db.pass=
```

### Step 3: Build the Project

```bash
cd eSportsTeamManagementSystem
mvn clean package
```

This generates `target/eSportsTeamManagementSystem.war`

### Step 4: Deploy to Tomcat

#### Option A: Manual Deployment
1. Copy `target/eSportsTeamManagementSystem.war` to `TOMCAT_HOME/webapps/`
2. Restart Tomcat
3. Access application at: `http://localhost:8080/eSportsTeamManagementSystem`

#### Option B: Using Tomcat Manager GUI
1. Start Tomcat
2. Open Tomcat Manager: `http://localhost:8080/manager/html`
3. Scroll to "Deploy" section
4. Select WAR file: `target/eSportsTeamManagementSystem.war`
5. Click Deploy

#### Option C: Using Maven Plugin
```bash
mvn tomcat:deploy
```

### Step 5: Access Application

- **URL**: `http://localhost:8080/eSportsTeamManagementSystem`
- **Home Page**: `http://localhost:8080/eSportsTeamManagementSystem/`
- **Login**: `http://localhost:8080/eSportsTeamManagementSystem/login`

### Step 6: Default Login Credentials

```
Username: AYUSH1234
Password: Ayush@2908
```

## 📂 Project Structure

```
eSportsTeamManagementSystem/
├── pom.xml                                 # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/com/esports/
│   │   │   ├── dao/                        # Data Access Objects
│   │   │   │   ├── DBUtil.java             # Database connection utility
│   │   │   │   ├── AdminDAO.java           # Admin operations
│   │   │   │   ├── TeamDAO.java            # Team operations
│   │   │   │   ├── PlayerDAO.java          # Player operations
│   │   │   │   ├── MatchDAO.java           # Match operations
│   │   │   │   └── TournamentDAO.java      # Tournament operations
│   │   │   ├── model/                      # Model/Entity classes
│   │   │   │   ├── Admin.java
│   │   │   │   ├── Team.java
│   │   │   │   ├── Player.java
│   │   │   │   ├── Match.java
│   │   │   │   └── Tournament.java
│   │   │   └── web/                        # Servlet controllers
│   │   │       ├── LoginServlet.java
│   │   │       ├── LogoutServlet.java
│   │   │       ├── TeamServlet.java
│   │   │       ├── PlayerServlet.java
│   │   │       ├── MatchServlet.java
│   │   │       ├── DashboardServlet.java
│   │   │       └── TournamentServlet.java
│   │   ├── webapp/
│   │   │   ├── index.jsp                   # Home page
│   │   │   ├── login.jsp                   # Login page
│   │   │   ├── css/
│   │   │   │   └── gaming.css              # Neon gaming theme
│   │   │   └── WEB-INF/
│   │   │       ├── web.xml                 # Servlet configuration
│   │   │       ├── common/
│   │   │       │   ├── header.jsp          # Navigation header
│   │   │       │   └── footer.jsp          # Page footer
│   │   │       ├── team/
│   │   │       │   ├── list.jsp
│   │   │       │   ├── form.jsp
│   │   │       │   └── view.jsp
│   │   │       ├── player/
│   │   │       │   ├── list.jsp
│   │   │       │   └── form.jsp
│   │   │       ├── match/
│   │   │       │   ├── list.jsp
│   │   │       │   ├── form.jsp
│   │   │       │   └── result.jsp
│   │   │       ├── tournament/
│   │   │       │   ├── list.jsp
│   │   │       │   └── form.jsp
│   │   │       └── dashboard.jsp
│   │   └── resources/
│   │       ├── app.properties              # Configuration file
│   │       └── database.sql                # Database initialization
│   └── test/                               # Test files (optional)
├── target/
│   └── eSportsTeamManagementSystem.war    # Compiled WAR file
└── README.md                               # This file
```

## 🔧 Using NetBeans IDE

### Import Project

1. **File** → **Open Project**
2. Select the `eSportsTeamManagementSystem` folder
3. Click **Open Project**

### Set Java Platform

1. Right-click project → **Properties**
2. Under **Sources**, select **JDK 17** from dropdown
3. Click **OK**

### Build and Run

1. **Run** → **Build Project** (or `Ctrl+F11`)
2. **Run** → **Run Project** (or `F6`)
3. NetBeans will deploy to Tomcat automatically

## 🔑 Default Database Credentials

**Admin Account:**
- Username: `AYUSH1234`
- Password: `Ayush@2908`
- Email: `admin@esports.com`

**Sample Data:**
- 5 Teams with players and statistics
- 25 Players with roles and K/D ratios
- 5 Tournaments
- 8 Matches (scheduled and completed)

## 🌐 URL Endpoints

| Page | URL |
|------|-----|
| Home | `/` |
| Login | `/login` |
| Dashboard | `/dashboard` |
| Teams List | `/team?action=list` |
| New Team | `/team?action=form` |
| Edit Team | `/team?action=edit&id=1` |
| View Team | `/team?action=view&id=1` |
| Players List | `/player?action=list` |
| New Player | `/player?action=form` |
| Tournaments | `/tournament?action=list` |
| Matches | `/match?action=list` |
| Logout | `/logout` |

## 📊 Database Schema

### Key Tables:
- **admins**: Admin user credentials
- **teams**: eSports teams with statistics
- **players**: Team members with roles and stats
- **tournaments**: Tournament information
- **matches**: Match results and schedules
- **tournament_teams**: Teams participating in tournaments

### Views:
- **team_leaderboard**: Ranked teams by points
- **player_stats**: Player statistics
- **tournament_stats**: Tournament statistics

## 🔐 Security Features

- Password-based authentication
- Session management with 30-minute timeout
- HTTP-only cookies
- Input validation on forms
- SQL parameterized queries (preventing SQL injection)
- Protected admin pages requiring login

## ⚙️ Configuration

### Database Configuration (app.properties)

```properties
# MySQL connection settings
db.url=jdbc:mysql://localhost:3306/esports_db?useSSL=false&serverTimezone=UTC
db.user=esports_user
db.pass=Esports@2025

# Application settings
app.name=eSports Team Management System
app.version=1.0.0
```

### Tomcat Configuration

Edit `TOMCAT_HOME/conf/server.xml` to change port:
```xml
<Connector port="8080" protocol="HTTP/1.1" .../>
```

## 🐛 Troubleshooting

### Issue: Connection refused to database
**Solution**: Ensure MySQL is running and credentials in `app.properties` are correct

### Issue: Tomcat can't find MySQL driver
**Solution**: Maven should handle this; run `mvn clean package` again

### Issue: JSP pages show 404 error
**Solution**: Ensure WAR file is deployed in `TOMCAT_HOME/webapps/` and Tomcat is restarted

### Issue: Login fails with correct credentials
**Solution**: Check `esports_db` exists and contains `admins` table with data

## 📝 Dependencies

Key Maven dependencies:
- **jakarta.servlet-api**: 6.0.0 (provided by Tomcat 10)
- **jakarta.servlet.jsp.jstl**: 3.0.0 & 3.0.1
- **mysql-connector-j**: 8.0.33
- **junit**: 4.13.2 (for testing)

## 🚀 Production Deployment

1. Set `db.pass` to strong password in `app.properties`
2. Use `useSSL=true` in connection string
3. Configure Tomcat SSL certificates
4. Enable firewall rules for port 8080/443
5. Use reverse proxy (nginx/Apache) for load balancing
6. Enable HTTPS

## 📖 Additional Resources

- [Jakarta EE Documentation](https://jakarta.ee/)
- [Apache Tomcat 10 Documentation](https://tomcat.apache.org/tomcat-10.0-doc/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Maven Documentation](https://maven.apache.org/guides/)

## 👨‍💻 Developer Notes

- Use DAO pattern for database operations
- Session attributes store logged-in admin
- All forms use POST method for state-changing operations
- JSTL used for iteration and conditionals in JSPs
- CSS provides neon gaming theme with dark background

## 📄 License

This project is provided as-is for educational and development purposes.

## 🙏 Support

For issues or questions:
1. Check the troubleshooting section
2. Review the database.sql for initial setup
3. Verify app.properties configuration
4. Check Tomcat catalina.out logs

---

**Version**: 1.0.0  
**Last Updated**: November 2025  
**Platform**: Apache Tomcat 10, MySQL 8.0+, Java 17
