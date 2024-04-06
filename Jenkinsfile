pipeline {
  agent any

  environment {
    DOCKERHUB_CREDENTIALS = credentials('DOCKER_HUB_CREDENTIAL')
    VERSION = "${env.BUILD_ID}"

  }

  tools {
    maven "Maven"
  }

  stages {

    stage('Maven Build'){
        steps{
        sh 'mvn clean package  -DskipTests'
        }
    }

     stage('Run Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('SonarQube Analysis') {
     steps {
       sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar -Dsonar.host.url=http://3.15.204.4:9000/ -Dsonar.login=squ_4cae9ed7c9637d0d0f8586b187e5e939b01d749b'
        }
       }


   stage('Check code coverage') {
            steps {
                script {
                    def token = "squ_4cae9ed7c9637d0d0f8586b187e5e939b01d749b"
                    def sonarQubeUrl = "http://3.15.204.4:9000/api"
                    def componentKey = "com.elfn:restaurantListing"
                    def coverageThreshold = 60.0

                    def response = sh (
                        script: "curl -H 'Authorization: Bearer ${token}' '${sonarQubeUrl}/measures/component?component=${componentKey}&metricKeys=coverage'",
                        returnStdout: true
                    ).trim()

                    def coverage = sh (
                        script: "echo '${response}' | jq -r '.component.measures[0].value'",
                        returnStdout: true
                    ).trim().toDouble()

                    echo "Coverage: ${coverage}"

                    if (coverage < coverageThreshold) {
                        error "Coverage is below the threshold of ${coverageThreshold}%. Aborting the pipeline."
                    }
                }
            }
        }



      stage('Docker Build and Push') {
      steps {
          sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
          sh 'docker build -t  elfn/restaurant-srv:${VERSION} .'
          sh 'docker push  elfn/restaurant-srv:${VERSION}'
      }
    }


     stage('Cleanup Workspace') {
      steps {
        deleteDir()

      }
    }



    stage('Update Image Tag in GitOps') {
      steps {
         checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[ credentialsId: 'git-ssh', url: 'git@github.com:devopscourse24/deployments.git']])
        script {
       sh '''
          sed -i "s/image:.*/image: elfn\\/restaurant-srv:${VERSION}/" aws/restaurant-manifest.yaml
        '''
          sh 'git checkout main'
          sh 'git add .'
          sh 'git commit -m "Update image tag"'
        sshagent(['git-ssh'])
            {
                  sh('git push')
            }
        }
      }
    }

  }

}