# candidate-matcher
Springboot project to match job with candidate   
Run ```mvn install``` to build   
The application opens at HOSTNAME:8082 

Navigate to http://52.91.38.113:8082/swagger-ui.html for testing deployed app
## List of Important apis and Example payload  
* **POST** ``/job/create`` Create new Job
```yaml
{
    "title": "Developer",
    "skills": [
      {
        "skill_name": "Java"
      },{
        "skill_name": "Python"
      }
    ]
}
``` 
* **POST** ``/candidate/create`` Create new Candidate
```yaml
{
  "firstName": "Biraj",
  "lastName": "Khatiwada",
  "skills": [
    {
      "skill_name": "Java"
    },{
      "skill_name": "Node"
    }

  ]
}
``` 
* **GET** ``/job/match/{id}`` Returns most matching candidate for the job id
