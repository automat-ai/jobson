#!groovy
node {
  try {
    stage('Fetching sources') {
      println 'Processing branch => ' + env.BRANCH_Name
      checkout scm

      gitCommit = sh(returnStdout: true, script: 'git rev-parse --verify HEAD').trim()
      shortCommit = gitCommit.take(8)
      println gitCommit
    }

    if (env.BRANCH_NAME == 'master') {
        stage('Generating JAR') {
          def mvnHome = tool 'Maven'
          sh "${mvnHome}/bin/mvn package -DskipTests"
        }

        stage('Publishing to docker') {
          withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'private-docker-registry',
                                usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
            imageName = "automatai/jobson:1.0.0-${shortCommit}"
            sh "docker login -u ${USERNAME} -p ${PASSWORD}"
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
