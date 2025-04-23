# OBSWeb - Student Information System (JEE7 + JSF + PrimeFaces)

This is a web-based Student Information System built using Java EE 7, JSF with PrimeFaces, and EJB technologies. It enables managing student information, grades, and generating reports with JasperReports.

## 🛠 Technologies Used

- **Java EE 7**
- **JSF 2.2 (JavaServer Faces)** – UI layer with XHTML + Facelets
- **CDI (Contexts and Dependency Injection)** – for managed beans and dependency injection
- **PrimeFaces 7.0** – modern UI components
- **EJB (Stateless Session Beans)** – for business logic and transactions
- **JDBC (MySQL)** – database connectivity
- **JasperReports 6.19.0** – for dynamic PDF reporting
- **Servlet API** – for report streaming and file download
- **Lombok** – to reduce boilerplate in model classes
- **NetBeans IDE 8.2** – development environment
- **GlassFish 4.1.2** – Java EE-compliant application server
- **Maven** – for dependency and build management
- **Docker Compose (for MySQL container)** – for provisioning MySQL database with sample data


## 📦 Project Modules

- **Model Layer**: Java POJOs annotated with Lombok.
- **DAO Layer**: Repository classes for data access.
- **Service Layer**: Managed beans for UI binding and business logic.
- **View Layer**: JSF pages with PrimeFaces components.
- **Reporting**: JasperReports integration with PDF export.
- **Security**: Login authentication and role-based access.
- **Database**: MySQL, initialized via Docker Compose with sample data.

## 📂 Project Structure

OBSWeb/
├── .gitignore
├── pom.xml
├── README.md              
├── src/
│   └── main/
│       ├── java/
│       │   └── com.obs/
│       │       ├── dao/
│       │       │   ├── BaseRepository.java
│       │       │   └── ...
│       │       ├── model/
│       │       │   ├── Student.java
│       │       │   └── ...
│       │       ├── util/
│       │       │   └── AllStaticQueries.java
│       │       └── web/
│       │           ├── listener/
│       │           ├── mbeans/
│       │           └── servlets/
│       ├── resources/
│       │   ├── docker/
│       │   │   └── docker-compose.yml
│       │   ├── initscripts/
│       │   │   └── initscripts.txt
│       │   ├── jdbcdrivers/
│       │   │   └── mysql-connector-java-5.1.49.jar
│       │   └── reports/
│       │       └── grade_report.jrxml
│       └── webapp/
│           ├── layout.xhtml
│           ├── login.xhtml
│           ├── studentForm.xhtml
│           ├── studentSearch.xhtml
│           └── gradeReport.xhtml

## 🚀 How to Run

1. Clone the repo.
2. Start MySQL using Docker:  
   ```bash
   docker-compose -f src/main/resources/docker/docker-compose.yml up -d
   
3. Import the project in NetBeans.

4. Deploy to GlassFish Server and Run Netbeans IDE

5. Visit: http://localhost:8080/obs/
