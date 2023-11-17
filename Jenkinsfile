pipeline {
    agent any

    tools {
        maven 'M3'
        jdk 'openjdk-11'
    }
    stages {

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