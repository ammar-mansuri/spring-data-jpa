pipeline {
    agent any

    tools {
        jdk 'openjdk-11'
    }
    stages {
        stage('Example') {
            steps {
                sh '''
                    env | grep -e PATH -e JAVA_HOME
                    which JAVA
                    java -version
                '''
            }
        }

        stage('Build') {
            steps {
                echo 'Building..'
                withMaven{
                 sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}