curl \
  -X PUT -i \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  http://localhost:7979/v1/customerservice/updatecustomer \
  -d '{"username": "johndoe", "firstName": "John", "lastName": "Doe", "address": "123 Main Street", "city": "California", "pincode": "12345", "email": "johndoe@example.com"}'