pipeline {
    agent any

    tools {
        maven 'Maven'
    }
    stages {

        stage('Build') {
            steps {
                echo 'Building..'
                withMaven{
                 sh 'mvn clean compile'
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Package') {
            steps {
                echo 'Packaging..'
                sh 'mvn package'
            }
        }
    }
}