for i in {1..100}
do
  curl -X GET http://localhost:8081/greet/summary
  curl -X GET http://localhost:8081/greet/histogram
done