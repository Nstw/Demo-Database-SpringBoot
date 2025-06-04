run:
	gradle bootRun

curl-create-person:
	curl -X POST -H "Content-Type: application/json" -d '{"personId": "P004", "name": "John Doe", "age": 30}' http://localhost:8080/api/persons

curl-get-all-persons:
	curl http://localhost:8080/api/persons

curl-get-person-by-id:
	curl http://localhost:8080/api/persons/P001

curl-create-loan:
	curl -X POST -H "Content-Type: application/json" -d '{"loanId": "L004", "personId": "P003", "loanAmount": 10000.00, "loanTerm": 12, "status": "ACTIVE", "interestRate": 5.5}' http://localhost:8080/api/loans

curl-get-all-loans:
	curl http://localhost:8080/api/loans

curl-get-loan-by-id:
	curl http://localhost:8080/api/loans/L001

curl-loans-by-person-id:
	curl http://localhost:8080/api/loans/person/P001

curl-get-loans-by-status:
	curl http://localhost:8080/api/loans/status/ACTIVE
