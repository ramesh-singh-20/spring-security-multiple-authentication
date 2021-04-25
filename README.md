# spring-security-multiple-authentication

In this project we implemented multiple authentication with spring security. First user is asked to enter username and password.
If this is success, user is asked to enter otp which is saved in the database.

If OTP entered is correct, a token is returned to user in the response header and also saved in the database.

Then user is required to enter correct token and username. Only then he will be able to access the requested resource.