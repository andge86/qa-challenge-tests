# qa-challenge-tests

## Description of the framework:
1. Java + TestNG + Maven + Rest Assured + Allure
2. Included Dockerfile and docker-compose.yml
3. Added GitHub Actions CI
4. Added Allure reports


## How to run:

### Locally:
1. Clone the repo
2. Setup Java (21 version is recommended)
3. Spin up Python Server from this repo: https://github.com/openinnovationai/recruiting-qa-challenge
4. To execute tests run: "./mvnw clean test"
5. To open report run: "brew install allure" and "allure serve"

### Locally inside docker:
1. Clone the repo
2. Install Docker Desktop (also you can use command line docker and docker-compose)
3. Navigate to project root folder
4. To run tests inside docker: "docker-compose up --build"

### Remotely Manually using GitHub Actions
1. Get proper access to andge86/qa-challenge-tests repo to be able to run Actions
2. Open https://github.com/andge86/qa-challenge-tests
3. Navigate to Actions tab
4. Choose Models API Tests Workflow
5. Click on "Run workflow", choose run options (local is recommended)
6. Click on "Run workflow" button
7. Wait until CI is completed
8. Open GitHub Pages to see the report: https://andge86.github.io/qa-challenge-tests

### Remotely automatically using GitHub Actions
Uncomment schedular module in api_ci.yml file to run tests every night. 
Report would be autogenerated and shown on GitGub Pages: https://andge86.github.io/qa-challenge-tests


Remote run options:
1. local - will clone DEV repo and spin it up. Later will run tests against it
2. remote - will run tests against some already available environment
3. Uncomment schedular module in api_ci.yml file to run tests every night. Report would be autogenerated and shown on GitGub Pages: https://andge86.github.io/qa-challenge-tests


### Pipeline structure:

Local run:
1. Launches remote ubuntu machine
2. Clones TEST and DEV repos
3. Installs Java and Python
4. Runs Pyhton Server and waits until it spins up. While running Server the tests are also executed
5. Clones history for Allure reports
6. Generates Allure reports
7. Publishes Allure report to GitHub Pages

Remote run:
1. Launches remote ubuntu machine
2. Clones TEST repo
3. Installs Java
4. Runs tests (I assume that Python Service is somewhere already running)
5. Clones history for Allure reports
6. Generates Allure reports
7. Publishes Allure report to GitHub Pages

### Advantages/disadvantages of the approach:

Advantages:
1. GitHub Actions local run runs autonomously
2. Nightly runs can present nice test report in the morning to detect issues on early stages
3. Free of cost 

Disadvantages/TODO later things:
1. Implement triggering tests on push/commit to DEV repo or as a part of DEV CI/CD pipeline
2. Implement parallel execution
3. Include sharing short summary/report by Slack, Teams or Email
4. Add CI healthcheck implementation instead of long sleep while the server is spinning up
5. Add more parametrized tests
6. Add caching mechanism for CI pipeline
7. Improve the way of testing models responses