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