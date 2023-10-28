curl \
  -X POST -i \
  -H "Content-Type: application/json" \
  -H "client-id: test" \
  http://localhost:7979/v1/customerservice/addcustomer \
  -d '{"username": "johndoe", "firstName": "John", "lastName": "Doe", "address": "123 Main Street", "city": "Anytown", "pincode": "12345", "email": "johndoe@example.com"}'