# Bank Management System
This is a simple Bank Management System which was built using Java Spring Boot

## Technologies Used
- **Java**
- **Spring Boot**
- **Maven**
- **Postman**

## API Endpoints
- **Create an Account**: `POST /accounts/create `
- **Get an Account**: `GET /accounts/{id}`
- **Deposit Money**: `POST /accounts/deposit`
- **Withdraw Money**: `POST /accounts/withdraw`
- **Record Deposit Transaction**: `POST /transactions/record/deposit`
- **Record Withdraw Transaction**: `POST /transactions/record/withdraw`
- **Get Transactions**: `GET /transactions/{accountId}`

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/nagornuuu/BankManagementSystem.git
    ```

2. Build the project:
   ```sh
    mvn clean install
   ```
   
3. Run the application:
   ```sh
   mvn spring-boot:run
    ```
