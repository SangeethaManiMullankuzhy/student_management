# student_management
Student Fee Management

This project consists of 4 microservices
1. student-service - allows users to add new students and view student details
2. fee-service - allows users to pay fee for students
3. receipt-service - allows users to view all payment receipts
4. shared-library - contains shared SO classes and Feign clients

How to run the project
----------------------
1. Clone the Repository
git clone https://github.com/SangeethaManiMullankuzhy/student_management.git
cd student_management
2. Start Microservices
cd shared-library && mvn spring-boot:run
cd student-service && mvn spring-boot:run
cd fee-service && mvn spring-boot:run
cd receipt-service && mvn spring-boot:run
