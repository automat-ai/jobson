#!groovy
node {
  try {

    stage('Fetching sources') {
      println 'Processing branch => ' + env.BRANCH_Name
      checkout scm

      gitCommit = sh(returnStdout: true, script: 'git rev-parse --verify HEAD').trim()
      shortCommit = gitCommit.take(7)
      println gitCommit
    }

    if (env.BRANCH_NAME == 'master') {
        stage('Publishing to docker') {
          withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'private-docker-registry',
                                usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            imageName = "automatai/jobson:1.0.0-${shortCommit}"
            sh "docker login -u ${USERNAME} -p ${PASSWORD}"
            sh "docker images | grep jobson-builder 2>&1 > /dev/null || docker build -t jobson-builder -f container/Dockerfile-builder .
            sh "docker run -tv $(shell pwd)/container/tmp/artifact:/app/artifact -v $(shell pwd)/container/tmp/m2:/root/.m2 jobson-builder /bin/sh -c 'mvn package -DskipTests && cp target/jobson-0.0.2.jar /app/artifact/'
            sh "docker build -t ${imageName} -f container/Dockerfile ."
            sh "docker push ${imageName}"
          }
        }
    }
  } catch (e) {
    // If there was an exception thrown, the build failed
    currentBuild.result = "FAILED"
    throw e
  } finally {

  }
}
