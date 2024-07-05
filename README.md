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

### Remotely using GitHub Actions
1. Get proper access to andge86/qa-challenge-tests repo to be able to run Actions
2. Open https://github.com/andge86/qa-challenge-tests
3. Navigate to Actions tab
4. Choose Models API Tests Workflow
5. Click on "Run workflow", choose run options (local is recommended)
6. Click on "Run workflow" button
7. Wait until CI is completed
8. Open GitHub Pages to see the report: https://andge86.github.io/qa-challenge-tests



Remote run options:
1. local - will clone DEV repo and spin it up. Later will run tests against it
2. remote - will run tests against some already available environment

TODO later:
1. Parallel execution
2. CI healthcheck implementation instead of long sleep while the server is spinning up
3. Add more parametrized tests
4. Improve the way of testing models responses